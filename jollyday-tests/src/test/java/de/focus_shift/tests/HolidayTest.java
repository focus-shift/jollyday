package de.focus_shift.tests;

import de.focus_shift.CalendarHierarchy;
import de.focus_shift.Holiday;
import de.focus_shift.HolidayCalendar;
import de.focus_shift.HolidayManager;
import de.focus_shift.ManagerParameter;
import de.focus_shift.ManagerParameters;
import de.focus_shift.util.CalendarUtil;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDate;
import java.util.Calendar;
import java.util.HashSet;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;

import static de.focus_shift.HolidayType.OFFICIAL_HOLIDAY;
import static de.focus_shift.HolidayType.UNOFFICIAL_HOLIDAY;
import static java.time.Month.APRIL;
import static java.time.Month.AUGUST;
import static java.time.Month.DECEMBER;
import static java.time.Month.FEBRUARY;
import static java.time.Month.JANUARY;
import static java.time.Month.JULY;
import static java.time.Month.NOVEMBER;
import static java.time.Month.SEPTEMBER;
import static java.util.Locale.ENGLISH;
import static java.util.Locale.GERMAN;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.fail;


class HolidayTest {

  private static final Logger LOG = LoggerFactory.getLogger(HolidayTest.class);

  private static final Set<LocalDate> test_days = new HashSet<>();
  private static final Set<LocalDate> test_days_l1 = new HashSet<>();
  private static final Set<LocalDate> test_days_l2 = new HashSet<>();
  private static final Set<LocalDate> test_days_l11 = new HashSet<>();

  private final static CalendarUtil calendarUtil = new CalendarUtil();

  static {
    test_days.add(LocalDate.of(2010, FEBRUARY, 17));
    test_days.add(LocalDate.of(2010, AUGUST, 30));
    test_days.add(LocalDate.of(2010, APRIL, 2));
    test_days.add(LocalDate.of(2010, APRIL, 5));
    test_days.add(LocalDate.of(2010, NOVEMBER, 17));
    test_days.add(LocalDate.of(2010, NOVEMBER, 28));
    test_days.add(LocalDate.of(2010, JANUARY, 1));
    test_days.add(LocalDate.of(2010, JANUARY, 18));
    test_days.add(LocalDate.of(2010, NOVEMBER, 26));
    test_days_l1.addAll(test_days);
    test_days_l1.add(LocalDate.of(2010, JANUARY, 2));
    test_days_l2.addAll(test_days_l1);
    test_days_l2.add(LocalDate.of(2010, JANUARY, 3));

    test_days_l11.addAll(test_days);
    test_days_l11.add(LocalDate.of(2010, JULY, 27));
    test_days_l11.add(LocalDate.of(2010, JULY, 9));
    test_days_l11.add(LocalDate.of(2010, FEBRUARY, 26));
    test_days_l11.add(LocalDate.of(2010, AUGUST, 11));
    test_days_l11.add(LocalDate.of(2010, SEPTEMBER, 6));
    test_days_l11.add(LocalDate.of(2010, SEPTEMBER, 10));
    test_days_l11.add(LocalDate.of(2010, NOVEMBER, 17));
    test_days_l11.add(LocalDate.of(2010, DECEMBER, 7));
    test_days_l11.add(LocalDate.of(2010, DECEMBER, 16));
  }

  private Locale defaultLocale;

  @BeforeEach
  void init() {
    System.setProperty(" de.focus_shift.jaxb.mapping.urls", "file:./src/test/resources/test.app.properties");
    defaultLocale = Locale.getDefault();
    Locale.setDefault(GERMAN);
  }

  @AfterEach
  void destroy() {
    Locale.setDefault(defaultLocale);
    System.clearProperty(" de.focus_shift.jaxb.mapping.urls");
  }

  @Test
  void testMissingCountry() {
    final ManagerParameter parameter = ManagerParameters.create("XXX");
    assertThatThrownBy(() -> HolidayManager.getInstance(parameter))
      .isInstanceOf(IllegalStateException.class);
  }

  @Test
  void testBaseStructure() {
    final HolidayManager holidayManager = HolidayManager.getInstance(ManagerParameters.create("test"));
    final CalendarHierarchy calendarHierarchy = holidayManager.getCalendarHierarchy();
    assertThat(calendarHierarchy.getId()).isEqualTo("test");
    assertThat(calendarHierarchy.getChildren()).hasSize(2);
    for (CalendarHierarchy hi : calendarHierarchy.getChildren().values()) {
      if (hi.getId().equalsIgnoreCase("level1")) {
        assertThat(hi.getChildren()).hasSize(1);
      } else if (hi.getId().equalsIgnoreCase("level11")) {
        assertThat(hi.getChildren()).isEmpty();
      }
    }
  }

  @Test
  void testHierarchyDescriptionsDefined() {
    for (HolidayCalendar holidayCalendar : HolidayCalendar.values()) {
      final HolidayManager holidayManager = HolidayManager.getInstance(ManagerParameters.create(holidayCalendar));
      assertNotUndefined(holidayCalendar, holidayManager.getCalendarHierarchy());
    }
  }

  private void assertNotUndefined(HolidayCalendar calendar, CalendarHierarchy c) {
    assertThat(c.getDescription()).isNotEqualTo("undefined");
    for (Map.Entry<String, CalendarHierarchy> element : c.getChildren().entrySet()) {
      assertNotUndefined(calendar, element.getValue());
    }
  }

  @Test
  void testIsHolidayPerformanceMultithreaded() throws InterruptedException {
    LocalDate date = LocalDate.of(2010, 1, 1);
    final AtomicLong count = new AtomicLong(0);
    final AtomicLong sumDuration = new AtomicLong(0);
    ExecutorService executorService = Executors.newCachedThreadPool();
    while (date.getYear() < 2013) {
      final LocalDate localDate = date;
      executorService.submit(() -> {
        long start = System.currentTimeMillis();
        HolidayManager m = HolidayManager.getInstance(ManagerParameters.create("test"));
        m.isHoliday(localDate);
        long duration = System.currentTimeMillis() - start;
        count.incrementAndGet();
        sumDuration.addAndGet(duration);
      });
      date = date.plusDays(1);
    }
    executorService.shutdown();
    executorService.awaitTermination(5, TimeUnit.SECONDS);
    LOG.info("isHoliday took {} millis average tested with {} calls.", sumDuration.doubleValue() / count.doubleValue(), count.longValue());
  }

  @Test
  void testCalendarChronology() {
    final HolidayManager holidayManager = HolidayManager.getInstance(ManagerParameters.create("test"));
    final Calendar calendar = Calendar.getInstance();
    calendar.set(Calendar.YEAR, 2010);
    calendar.set(Calendar.MONTH, Calendar.FEBRUARY);
    calendar.set(Calendar.DAY_OF_MONTH, 16);
    assertThat(holidayManager.isHoliday(calendar)).isFalse();

    calendar.add(Calendar.DAY_OF_YEAR, 1);
    assertThat(holidayManager.isHoliday(calendar)).isTrue();
  }

  @Test
  void testBaseDates() {
    final HolidayManager holidayManager = HolidayManager.getInstance(ManagerParameters.create("test"));
    final Set<Holiday> holidays = holidayManager.getHolidays(2010);
    assertThat(holidays).isNotNull();
    assertDates(test_days, holidays);
  }

  private void assertDates(Set<LocalDate> expected, Set<Holiday> holidays) {
    for (LocalDate localDate : expected) {
      if (!calendarUtil.contains(holidays, localDate)) {
        fail("Missing " + localDate + " in " + holidays);
      }
    }
    assertThat(holidays).hasSameSizeAs(expected);
  }

  @Test
  void testLevel1() {
    final HolidayManager holidayManager = HolidayManager.getInstance(ManagerParameters.create("test"));
    final Set<Holiday> holidays = holidayManager.getHolidays(2010, "level1");
    assertThat(holidays).isNotNull();
    assertDates(test_days_l1, holidays);
  }

  @Test
  void testLevel2() {
    final HolidayManager holidayManager = HolidayManager.getInstance(ManagerParameters.create("test"));
    final Set<Holiday> holidays = holidayManager.getHolidays(2010, "level1", "level2");
    assertThat(holidays).isNotNull();
    assertDates(test_days_l2, holidays);
  }

  @Test
  void testLevel11() {
    final HolidayManager holidayManager = HolidayManager.getInstance(ManagerParameters.create("test"));
    final Set<Holiday> holidays = holidayManager.getHolidays(2010, "level11");
    assertThat(holidays).isNotNull();
    assertDates(test_days_l11, holidays);
  }

  @Test
  void ensureThatExceptionIsThrownOnSubConfigurationWithSameId() {
    final ManagerParameter parameter = ManagerParameters.create("test_fail");
    assertThatThrownBy(() -> HolidayManager.getInstance(parameter))
      .isInstanceOf(IllegalArgumentException.class);
  }

  @Test
  void testAllAvailableManagers() {
    final Set<String> supportedCalendarCodes = HolidayManager.getSupportedCalendarCodes();
    assertThat(supportedCalendarCodes)
      .isNotNull()
      .isNotEmpty();
    for (String calendar : supportedCalendarCodes) {
      final HolidayManager manager = HolidayManager.getInstance(ManagerParameters.create(calendar));
      assertThat(manager).isNotNull();
    }
  }

  @Test
  void testHolidayDescription() {
    Locale.setDefault(ENGLISH);

    final Holiday holiday = new Holiday(LocalDate.of(2011, 2, 2), "CHRISTMAS", OFFICIAL_HOLIDAY);
    assertThat(holiday.getDescription()).isEqualTo("Christmas");
    assertThat(holiday.getDescription(GERMAN)).isEqualTo("Weihnachten");
    assertThat(holiday.getDescription(new Locale("nl"))).isEqualTo("Kerstmis");
  }

  @Test
  void testHolidayEquals() {
    final Holiday h1 = new Holiday(LocalDate.of(2011, 2, 2), "CHRISTMAS", OFFICIAL_HOLIDAY);
    assertThat(h1).isEqualTo(h1);

    final Holiday h2b = new Holiday(LocalDate.of(2011, 2, 2), "CHRISTMAS", OFFICIAL_HOLIDAY);
    assertThat(h1).isEqualTo(h2b);

    final Holiday h2 = new Holiday(LocalDate.of(2011, 2, 1), "CHRISTMAS", OFFICIAL_HOLIDAY);
    assertThat(h1).isNotEqualTo(h2);

    final Holiday h3 = new Holiday(LocalDate.of(2011, 2, 2), "NEW_YEAR", OFFICIAL_HOLIDAY);
    assertThat(h1).isNotEqualTo(h3);

    final Holiday h4 = new Holiday(LocalDate.of(2011, 2, 2), "CHRISTMAS", UNOFFICIAL_HOLIDAY);
    assertThat(h1).isNotEqualTo(h4);
  }
}
