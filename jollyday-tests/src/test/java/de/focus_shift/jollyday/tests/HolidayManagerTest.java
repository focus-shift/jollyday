package de.focus_shift.jollyday.tests;

import de.focus_shift.jollyday.core.CalendarHierarchy;
import de.focus_shift.jollyday.core.Holiday;
import de.focus_shift.jollyday.core.HolidayCalendar;
import de.focus_shift.jollyday.core.HolidayManager;
import de.focus_shift.jollyday.core.ManagerParameter;
import de.focus_shift.jollyday.core.impl.JapaneseHolidayManager;
import de.focus_shift.jollyday.core.util.CalendarUtil;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDate;
import java.util.Calendar;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Stream;

import static de.focus_shift.jollyday.core.ManagerParameters.create;
import static java.time.Month.APRIL;
import static java.time.Month.AUGUST;
import static java.time.Month.DECEMBER;
import static java.time.Month.FEBRUARY;
import static java.time.Month.JANUARY;
import static java.time.Month.JULY;
import static java.time.Month.NOVEMBER;
import static java.time.Month.SEPTEMBER;
import static java.util.Calendar.DAY_OF_MONTH;
import static java.util.Calendar.DAY_OF_YEAR;
import static java.util.Calendar.MONTH;
import static java.util.Calendar.YEAR;
import static java.util.concurrent.TimeUnit.SECONDS;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.fail;

class HolidayManagerTest {

  private static final Logger LOG = LoggerFactory.getLogger(HolidayManagerTest.class);

  private static final Set<LocalDate> test_days_base = new HashSet<>();
  private static final Set<LocalDate> test_days_l1_1 = new HashSet<>();
  private static final Set<LocalDate> test_days_l1_1_l2 = new HashSet<>();
  private static final Set<LocalDate> test_days_l1_2 = new HashSet<>();

  static {
    test_days_base.add(LocalDate.of(2010, FEBRUARY, 17));
    test_days_base.add(LocalDate.of(2010, AUGUST, 30));
    test_days_base.add(LocalDate.of(2010, APRIL, 2));
    test_days_base.add(LocalDate.of(2010, APRIL, 5));
    test_days_base.add(LocalDate.of(2010, NOVEMBER, 17));
    test_days_base.add(LocalDate.of(2010, NOVEMBER, 28));
    test_days_base.add(LocalDate.of(2010, JANUARY, 1));
    test_days_base.add(LocalDate.of(2010, JANUARY, 18));
    test_days_base.add(LocalDate.of(2010, NOVEMBER, 26));

    test_days_l1_1.addAll(test_days_base);
    test_days_l1_1.add(LocalDate.of(2010, JANUARY, 2));

    test_days_l1_1_l2.addAll(test_days_l1_1);
    test_days_l1_1_l2.add(LocalDate.of(2010, JANUARY, 3));

    test_days_l1_2.addAll(test_days_base);
    test_days_l1_2.add(LocalDate.of(2010, JULY, 27));
    test_days_l1_2.add(LocalDate.of(2010, JULY, 9));
    test_days_l1_2.add(LocalDate.of(2010, FEBRUARY, 26));
    test_days_l1_2.add(LocalDate.of(2010, AUGUST, 11));
    test_days_l1_2.add(LocalDate.of(2010, SEPTEMBER, 6));
    test_days_l1_2.add(LocalDate.of(2010, SEPTEMBER, 10));
    test_days_l1_2.add(LocalDate.of(2010, NOVEMBER, 17));
    test_days_l1_2.add(LocalDate.of(2010, DECEMBER, 7));
    test_days_l1_2.add(LocalDate.of(2010, DECEMBER, 16));
  }

  @Nested
  class Caching {
    @Test
    void ensureThatCachingIsEnabledByDefault() {
      assertThat(HolidayManager.isManagerCachingEnabled()).isTrue();
    }

    @Test
    void ensureThatTheCachedHolidayManagerWillReturnedOnSecondCall() {
      HolidayManager.setManagerCachingEnabled(true);
      final HolidayManager first = HolidayManager.getInstance(create("test"));
      final HolidayManager second = HolidayManager.getInstance(create("test"));
      assertThat(second).isSameAs(first);
    }

    @Test
    void ensureThatDisablingTheCacheDoesNotReturnCachedHolidayManager() {

      final HolidayManager holidayManagerCached = HolidayManager.getInstance(create("test"));

      HolidayManager.setManagerCachingEnabled(false);
      assertThat(HolidayManager.getInstance(create("test"))).isNotSameAs(holidayManagerCached);
      HolidayManager.setManagerCachingEnabled(true);
    }

    @Test
    void ensureThatTheCachedHolidayManagerWillBeCleared() {
      HolidayManager.setManagerCachingEnabled(true);
      final HolidayManager first = HolidayManager.getInstance(create("test"));
      final HolidayManager second = HolidayManager.getInstance(create("test"));
      assertThat(first).isSameAs(second);
      HolidayManager.clearManagerCache();
      final HolidayManager third = HolidayManager.getInstance(create("test"));
      assertThat(third).isNotSameAs(first).isNotSameAs(second);
    }
  }

  @Test
  void ensureToInstantiateHolidayManagerImplementationBasedOnCountry() {
    System.setProperty("config.urls", "file:./src/test/resources/test.app.properties");

    HolidayManager.setManagerCachingEnabled(false);
    assertThat(HolidayManager.getInstance(create("test"))).isInstanceOf(JapaneseHolidayManager.class);
    HolidayManager.setManagerCachingEnabled(true);

    System.clearProperty("config.urls");
  }

  @Test
  void ensureToThrowIllegalStateExceptionIfCountryIsMissing() {
    final ManagerParameter parameter = create("NoCountry");
    assertThatThrownBy(() -> HolidayManager.getInstance(parameter))
      .isInstanceOf(IllegalStateException.class);
  }

  @Test
  void ensureBaseStructureCanBeTraversed() {
    final HolidayManager holidayManager = HolidayManager.getInstance(create("test"));
    final CalendarHierarchy calendarHierarchy = holidayManager.getCalendarHierarchy();
    assertThat(calendarHierarchy.getId()).isEqualTo("test");
    assertThat(calendarHierarchy.getChildren()).hasSize(2);

    for (CalendarHierarchy calendarHierarchyChild : calendarHierarchy.getChildren().values()) {
      if (calendarHierarchyChild.getId().equalsIgnoreCase("level1")) {
        assertThat(calendarHierarchyChild.getChildren()).hasSize(1);
      } else if (calendarHierarchyChild.getId().equalsIgnoreCase("level11")) {
        assertThat(calendarHierarchyChild.getChildren()).isEmpty();
      }
    }
  }

  @Test
  void ensureHierarchyDescriptionsIsDefinedForAllHolidayCalendars() {
    for (HolidayCalendar holidayCalendar : HolidayCalendar.values()) {
      final HolidayManager holidayManager = HolidayManager.getInstance(create(holidayCalendar));
      assertNotUndefined(holidayCalendar, holidayManager.getCalendarHierarchy());
    }
  }

  private void assertNotUndefined(HolidayCalendar holidayCalendar, CalendarHierarchy calendarHierarchy) {
    assertThat(calendarHierarchy.getDescription()).isNotEqualTo("undefined");
    for (CalendarHierarchy calendarHierarchyChild : calendarHierarchy.getChildren().values()) {
      assertNotUndefined(holidayCalendar, calendarHierarchyChild);
    }
  }

  @Test
  void testIsHolidayPerformanceMultiThread() throws InterruptedException {

    LocalDate date = LocalDate.of(2010, 1, 1);
    final AtomicLong count = new AtomicLong(0);
    final AtomicLong sumDuration = new AtomicLong(0);

    final ExecutorService executorService = Executors.newCachedThreadPool();
    while (date.getYear() < 2013) {
      final LocalDate localDate = date;
      executorService.submit(() -> {
        final long start = System.currentTimeMillis();

        final HolidayManager holidayManager = HolidayManager.getInstance(create("test"));
        holidayManager.isHoliday(localDate);

        final long duration = System.currentTimeMillis() - start;
        count.incrementAndGet();
        sumDuration.addAndGet(duration);
      });
      date = date.plusDays(1);
    }
    executorService.shutdown();
    executorService.awaitTermination(5, SECONDS);

    LOG.info("isHoliday took {} millis average tested with {} calls.", sumDuration.doubleValue() / count.doubleValue(), count.longValue());
  }

  @Test
  void ensureIsHolidayMethodReturnsTrueFalseForCalendarChronology() {
    final HolidayManager sut = HolidayManager.getInstance(create("test"));

    final Calendar calendar = Calendar.getInstance();
    calendar.set(YEAR, 2010);
    calendar.set(MONTH, Calendar.FEBRUARY);
    calendar.set(DAY_OF_MONTH, 16);
    assertThat(sut.isHoliday(calendar)).isFalse();

    calendar.add(DAY_OF_YEAR, 1);
    assertThat(sut.isHoliday(calendar)).isTrue();
  }

  @Test
  void ensureToRetrieveHolidaysFromBaseLevelHierarchy() {
    final HolidayManager holidayManager = HolidayManager.getInstance(create("test"));
    final Set<Holiday> holidays = holidayManager.getHolidays(2010);
    assertThat(holidays).isNotNull();
    assertDates(test_days_base, holidays);
  }

  private static Stream<Arguments> firstLevel() {
    return Stream.of(
      Arguments.of("level1", test_days_l1_1),
      Arguments.of("level11", test_days_l1_2)
    );
  }

  @ParameterizedTest
  @MethodSource("firstLevel")
  void ensureToRetrieveHolidaysFromFirstLevelHierarchies(final String firstLevelName, final Set<LocalDate> expectedHolidays) {
    final HolidayManager holidayManager = HolidayManager.getInstance(create("test"));
    final Set<Holiday> actualHolidays = holidayManager.getHolidays(2010, firstLevelName);
    assertThat(actualHolidays).isNotNull();
    assertDates(expectedHolidays, actualHolidays);
  }

  @Test
  void ensureToRetrieveHolidaysFromSecondLevelHierarchy() {
    final HolidayManager holidayManager = HolidayManager.getInstance(create("test"));
    final Set<Holiday> holidays = holidayManager.getHolidays(2010, "level1", "level2");
    assertThat(holidays).isNotNull();
    assertDates(test_days_l1_1_l2, holidays);
  }

  @Test
  void testLevel11() {
    final HolidayManager holidayManager = HolidayManager.getInstance(create("test"));
    final Set<Holiday> holidays = holidayManager.getHolidays(2010, "level11");
    assertThat(holidays).isNotNull();
    assertDates(test_days_l1_2, holidays);
  }

  @Test
  void ensureThatExceptionIsThrownOnSubConfigurationWithSameId() {
    final ManagerParameter parameter = create("test_fail");
    assertThatThrownBy(() -> HolidayManager.getInstance(parameter))
      .isInstanceOf(IllegalArgumentException.class);
  }

  @Test
  void ensureThatHolidayManagerCanBeInstantiatedWithValuesFromHolidayCalendar() {
    final Set<String> supportedCalendarCodes = HolidayManager.getSupportedCalendarCodes();
    for (String calendar : supportedCalendarCodes) {
      final HolidayManager manager = HolidayManager.getInstance(create(calendar));
      assertThat(manager).isNotNull();
    }
  }

  private void assertDates(final Set<LocalDate> expectedHolidays, final Set<Holiday> holidays) {
    for (final LocalDate localDate : expectedHolidays) {
      if (!CalendarUtil.contains(holidays, localDate)) {
        fail("Missing " + localDate + " in " + holidays);
      }
    }
    assertThat(holidays).hasSameSizeAs(expectedHolidays);
  }
}
