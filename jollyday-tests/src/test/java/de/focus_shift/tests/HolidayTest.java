package de.focus_shift.tests;

import de.focus_shift.CalendarHierarchy;
import de.focus_shift.Holiday;
import de.focus_shift.HolidayCalendar;
import de.focus_shift.HolidayManager;
import de.focus_shift.ManagerParameters;
import de.focus_shift.util.CalendarUtil;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.Calendar;
import java.util.HashSet;
import java.util.Locale;
import java.util.Map;
import java.util.Properties;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;
import java.util.logging.Level;
import java.util.logging.Logger;

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
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

/**
 * @author Sven
 */
class HolidayTest {

  private final static Logger LOG = Logger.getLogger(HolidayTest.class.getName());

  private static final Set<LocalDate> test_days = new HashSet<>();
  private static final Set<LocalDate> test_days_l1 = new HashSet<>();
  private static final Set<LocalDate> test_days_l2 = new HashSet<>();
  private static final Set<LocalDate> test_days_l11 = new HashSet<>();

  private final static CalendarUtil calendarUtil = new CalendarUtil();

  static {
    test_days
      .add(LocalDate.of(2010, FEBRUARY, 17));
    test_days.add(LocalDate.of(2010, AUGUST, 30));
    test_days.add(LocalDate.of(2010, APRIL, 2));
    test_days.add(LocalDate.of(2010, APRIL, 5));
    test_days
      .add(LocalDate.of(2010, NOVEMBER, 17));
    test_days
      .add(LocalDate.of(2010, NOVEMBER, 28));
    test_days.add(LocalDate.of(2010, JANUARY, 1));
    test_days.add(LocalDate.of(2010, JANUARY, 18));
    test_days
      .add(LocalDate.of(2010, NOVEMBER, 26));
    test_days_l1.addAll(test_days);
    test_days_l1.add(LocalDate.of(2010, JANUARY, 2));
    test_days_l2.addAll(test_days_l1);
    test_days_l2.add(LocalDate.of(2010, JANUARY, 3));

    test_days_l11.addAll(test_days);
    test_days_l11
      .add(LocalDate.of(2010, JULY, 27));
    test_days_l11.add(LocalDate.of(2010, JULY, 9));
    test_days_l11.add(LocalDate.of(2010, FEBRUARY,
      26));
    test_days_l11.add(LocalDate.of(2010, AUGUST,
      11));
    test_days_l11.add(LocalDate.of(2010,
      SEPTEMBER, 6));
    test_days_l11.add(LocalDate.of(2010,
      SEPTEMBER, 10));
    test_days_l11.add(LocalDate.of(2010, NOVEMBER,
      17));
    test_days_l11.add(LocalDate.of(2010, DECEMBER,
      7));
    test_days_l11.add(LocalDate.of(2010, DECEMBER,
      16));
  }

  private Locale defaultLocale;

  @BeforeEach
  void init() {
    System.setProperty(" de.focus_shift.jaxb.mapping.urls",
      "file:./src/test/resources/test.app.properties");
    defaultLocale = Locale.getDefault();
    Locale.setDefault(Locale.GERMAN);
  }

  @AfterEach
  void destroy() {
    Locale.setDefault(defaultLocale);
    System.clearProperty(" de.focus_shift.jaxb.mapping.urls");
  }

  @Test
  void testMissingCountry() {
    assertThrows(IllegalStateException.class, () -> HolidayManager.getInstance("XXX"));
  }

  @Test
  void testBaseStructure() {
    HolidayManager m = HolidayManager.getInstance("test");
    CalendarHierarchy h = m.getCalendarHierarchy();
    assertEquals("test", h.getId(), "Wrong id.");
    assertEquals(2, h.getChildren().size(), "Wrong number of children on first level.");
    for (CalendarHierarchy hi : h.getChildren().values()) {
      if (hi.getId().equalsIgnoreCase("level1")) {
        assertEquals(1, hi.getChildren().size(), "Wrong number of children on second level of level 1.");
      } else if (hi.getId().equalsIgnoreCase("level11")) {
        assertEquals(0, hi.getChildren().size(), "Wrong number of children on second level of level 11.");
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
    assertFalse("undefined".equals(c.getDescription()), "Calendar " + calendar + " Hierarchy " + c.getId() + " is lacking a description.");
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
        HolidayManager m = HolidayManager.getInstance("test");
        m.isHoliday(localDate);
        long duration = System.currentTimeMillis() - start;
        count.incrementAndGet();
        sumDuration.addAndGet(duration);
      });
      date = date.plusDays(1);
    }
    executorService.shutdown();
    executorService.awaitTermination(5, TimeUnit.SECONDS);
    LOG.log(Level.INFO,
      "isHoliday took " + sumDuration.doubleValue()
        / count.doubleValue() + " millis average tested with "
        + count.longValue() + " calls.");
  }

  @Test
  void testCalendarChronology() {
    HolidayManager m = HolidayManager.getInstance("test");
    Calendar c = Calendar.getInstance();
    c.set(Calendar.YEAR, 2010);
    c.set(Calendar.MONTH, Calendar.FEBRUARY);
    c.set(Calendar.DAY_OF_MONTH, 16);
    assertFalse(m.isHoliday(c), "This date should NOT be a holiday.");
    c.add(Calendar.DAY_OF_YEAR, 1);
    assertTrue(m.isHoliday(c), "This date should be a holiday.");
  }

  @Test
  void testBaseDates() {
    HolidayManager m = HolidayManager.getInstance("test");
    Set<Holiday> holidays = m.getHolidays(2010);
    assertNotNull(holidays);
    assertDates(test_days, holidays);
  }

  private void assertDates(Set<LocalDate> expected, Set<Holiday> holidays) {
    for (LocalDate localDate : expected) {
      if (!calendarUtil.contains(holidays, localDate)) {
        fail("Missing " + localDate + " in " + holidays);
      }
    }
    assertEquals(expected.size(), holidays.size(), "Wrong number of dates.");
  }

  @Test
  void testLevel1() {
    final HolidayManager holidayManager = HolidayManager.getInstance("test");
    final Set<Holiday> holidays = holidayManager.getHolidays(2010, "level1");
    assertNotNull(holidays);
    assertDates(test_days_l1, holidays);
  }

  @Test
  void testLevel2() {
    HolidayManager m = HolidayManager.getInstance("test");
    Set<Holiday> holidays = m.getHolidays(2010, "level1", "level2");
    assertNotNull(holidays);
    assertDates(test_days_l2, holidays);
  }

  @Test
  void testLevel11() {
    HolidayManager m = HolidayManager.getInstance("test");
    Set<Holiday> holidays = m.getHolidays(2010, "level11");
    assertNotNull(holidays);
    assertDates(test_days_l11, holidays);
  }

  @Test
  void testFail() {
    assertThrows(IllegalArgumentException.class, () -> HolidayManager.getInstance("test_fail"));
  }

  @Test
  void testAllAvailableManagers() {
    Set<String> supportedCalendarCodes = HolidayManager.getSupportedCalendarCodes();
    assertNotNull(supportedCalendarCodes);
    assertFalse(supportedCalendarCodes.isEmpty());
    for (String calendar : supportedCalendarCodes) {
      HolidayManager manager = HolidayManager.getInstance(calendar);
      assertNotNull(manager, "Manager for calendar " + calendar + " is NULL.");
    }
  }

  @Test
  void testHolidayDescription() {
    Locale.setDefault(ENGLISH);

    final Holiday holiday = new Holiday(LocalDate.of(2011, 2, 2), "CHRISTMAS", OFFICIAL_HOLIDAY);
    assertEquals("Christmas", holiday.getDescription(), "Wrong description");
    assertEquals("Weihnachten", holiday.getDescription(Locale.GERMAN), "Wrong description");
    assertEquals("Kerstmis", holiday.getDescription(new Locale("nl")), "Wrong description");
  }

  @Test
  void testHolidayEquals() {
    Holiday h1 = new Holiday(LocalDate.of(2011, 2, 2), "CHRISTMAS", OFFICIAL_HOLIDAY);
    assertTrue(h1.equals(h1), "Wrong equals implementation");
    Holiday h2b = new Holiday(LocalDate.of(2011, 2, 2), "CHRISTMAS", OFFICIAL_HOLIDAY);
    assertTrue(h1.equals(h2b), "Wrong equals implementation");
    Holiday h2 = new Holiday(LocalDate.of(2011, 2, 1), "CHRISTMAS", OFFICIAL_HOLIDAY);
    assertFalse(h1.equals(h2), "Wrong equals implementation");
    Holiday h3 = new Holiday(LocalDate.of(2011, 2, 2), "NEW_YEAR", OFFICIAL_HOLIDAY);
    assertFalse(h1.equals(h3), "Wrong equals implementation");
    Holiday h4 = new Holiday(LocalDate.of(2011, 2, 2), "CHRISTMAS", UNOFFICIAL_HOLIDAY);
    assertFalse(h1.equals(h4), "Wrong equals implementation");
  }
}
