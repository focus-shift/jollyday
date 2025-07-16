package de.focus_shift.jollyday.tests;

import de.focus_shift.jollyday.core.Holiday;
import de.focus_shift.jollyday.core.HolidayCalendar;
import de.focus_shift.jollyday.core.HolidayManager;
import de.focus_shift.jollyday.core.HolidayType;
import net.jqwik.api.Arbitraries;
import net.jqwik.time.api.arbitraries.YearArbitrary;
import org.junit.jupiter.api.Assertions;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.Month;
import java.time.Year;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.Set;

import static de.focus_shift.jollyday.core.HolidayType.PUBLIC_HOLIDAY;
import static de.focus_shift.jollyday.core.ManagerParameters.create;
import static de.focus_shift.jollyday.tests.CalendarChecker.Adjuster.NEXT;
import static de.focus_shift.jollyday.tests.CalendarChecker.Category.BY_DAY;
import static de.focus_shift.jollyday.tests.CalendarChecker.Category.BY_KEY;
import static java.time.temporal.TemporalAdjusters.nextOrSame;
import static java.time.temporal.TemporalAdjusters.previousOrSame;
import static java.util.Collections.unmodifiableList;
import static org.assertj.core.api.Assertions.assertThat;

public class CalendarChecker implements CalendarCheckerApi.Holiday, CalendarCheckerApi.Between, CalendarCheckerApi.Properties {

  enum Category {
    BY_DAY,
    BY_KEY
  }

  public enum Adjuster {
    NEXT,
    PREVIOUS
  }

  private final HolidayCalendar calendar;
  private String propertyKey;
  private Month month;
  private int day;
  private HolidayType type;
  private Category category;
  private String[] subdivisions = new String[]{};
  private List<YearRange> validRanges = new ArrayList<>();
  private List<YearRange> invalidRanges = new ArrayList<>();
  private List<WeekDayFromTo> validShifts = new ArrayList<>();

  private final List<HolidayCalendarCheck> checks = new ArrayList<>();

  public CalendarChecker(HolidayCalendar calendar) {
    this.calendar = calendar;
  }

  @Override
  public CalendarCheckerApi.Properties hasChristianHoliday(final String propertyKey) {
    return hasChristianHoliday(propertyKey, PUBLIC_HOLIDAY);
  }

  @Override
  public CalendarCheckerApi.Properties hasChristianHoliday(final String propertyKey, final HolidayType type) {
    Objects.requireNonNull(propertyKey, "propertyKey is required");
    Objects.requireNonNull(type, "holiday type is required");

    this.category = BY_KEY;
    this.propertyKey = "christian." + propertyKey;
    this.type = type;

    return this;
  }

  @Override
  public CalendarCheckerApi.Properties hasIslamicHoliday(final String propertyKey) {
    return hasIslamicHoliday(propertyKey, PUBLIC_HOLIDAY);
  }

  @Override
  public CalendarCheckerApi.Properties hasIslamicHoliday(final String propertyKey, final HolidayType type) {
    Objects.requireNonNull(propertyKey, "propertyKey is required");
    Objects.requireNonNull(type, "holiday type is required");

    this.category = BY_KEY;
    this.propertyKey = "islamic." + propertyKey;
    this.type = type;

    return this;
  }

  @Override
  public CalendarCheckerApi.Properties hasEthiopianOrthodoxHoliday(final String propertyKey) {
    return hasEthiopianOrthodoxHoliday(propertyKey, PUBLIC_HOLIDAY);
  }

  @Override
  public CalendarCheckerApi.Properties hasEthiopianOrthodoxHoliday(final String propertyKey, final HolidayType type) {
    Objects.requireNonNull(propertyKey, "propertyKey is required");
    Objects.requireNonNull(type, "holiday type is required");

    this.category = BY_KEY;
    this.propertyKey = "ethiopian.orthodox." + propertyKey;
    this.type = type;

    return this;
  }

  @Override
  public CalendarCheckerApi.Properties hasFixedHoliday(final String propertyKey, final Month month, final int day) {
    return hasFixedHoliday(propertyKey, month, day, PUBLIC_HOLIDAY);
  }

  @Override
  public CalendarCheckerApi.Properties hasFixedHoliday(final String propertyKey, final Month month, final int day, final HolidayType type) {

    Objects.requireNonNull(propertyKey, "propertyKey is required");
    Objects.requireNonNull(month, "month is required");
    if (day >= 32 || day <= 0) {
      throw new IllegalArgumentException("day must be between 1 and 31");
    }
    Objects.requireNonNull(type, "holiday type is required");

    this.category = BY_DAY;
    this.propertyKey = propertyKey;
    this.month = month;
    this.day = day;
    this.type = type;

    return this;
  }

  @Override
  public CalendarCheckerApi.Properties between(final Year from, Year to) {
    this.validRanges.add(new YearRange(from, to));
    return this;
  }

  @Override
  public CalendarCheckerApi.Properties notBetween(final Year from, Year to) {
    this.invalidRanges.add(new YearRange(from, to));
    return this;
  }

  @Override
  public CalendarCheckerApi.Properties inSubdivision(String... subdivisions) {
    this.subdivisions = subdivisions;
    return this;
  }

  @Override
  public CalendarCheckerApi.Properties canBeShiftedFrom(DayOfWeek from, DayOfWeek to) {
    this.validShifts.add(new WeekDayFromTo(from, to, NEXT));
    return this;
  }

  @Override
  public CalendarCheckerApi.Properties canBeShiftedFrom(DayOfWeek from, Adjuster adjuster, DayOfWeek to) {
    this.validShifts.add(new WeekDayFromTo(from, to, adjuster));
    return this;
  }

  @Override
  public CalendarCheckerApi.Holiday and() {
    checks.add(new HolidayCalendarCheck(
      this.calendar,
      this.propertyKey,
      this.month,
      this.day,
      this.type,
      new ArrayList<>(this.validRanges),
      new ArrayList<>(this.invalidRanges),
      new ArrayList<>(this.validShifts),
      this.subdivisions.clone(),
      this.category
    ));

    clearProperties();

    return this;
  }

  @Override
  public void check() {
    checks.add(new HolidayCalendarCheck(
      calendar,
      propertyKey,
      month,
      day,
      type,
      new ArrayList<>(validRanges),
      new ArrayList<>(invalidRanges),
      new ArrayList<>(validShifts),
      this.subdivisions.clone(),
      category
    ));

    clearProperties();

    final HolidayManager holidayManager = HolidayManager.getInstance(create(checks.get(0).calendar()));

    for (HolidayCalendarCheck check : checks) {
      switch (check.category) {
        case BY_DAY:
          checkByDate(check, holidayManager);
          break;
        case BY_KEY:
          checkByKey(check, holidayManager);
          break;
        default:
          throw new IllegalStateException("Unexpected value: " + check.category);
      }
    }

    this.checks.clear();
  }

  private void checkByDate(final HolidayCalendarCheck check, final HolidayManager holidayManager) {
    final YearArbitrary yearArbitrary = createYearArbitrary();

    for (final YearRange invalidRange : check.invalidRanges()) {
      yearArbitrary
        .between(invalidRange.from().getValue(), invalidRange.to().getValue())
        .forEachValue(year -> {
            final Set<Holiday> holidays = holidayManager.getHolidays(year, check.subdivisions());
            final LocalDate dateToCheck = LocalDate.of(year.getValue(), check.month(), check.day());
            final LocalDate shiftLocalDate = shiftLocalDate(check, dateToCheck);
            final Holiday holiday = new Holiday(shiftLocalDate, check.propertiesKey(), check.holidayType());
            assertHolidayNotPresent(holidays, holiday, holidayManager, check.subdivisions());
          }
        );
    }

    for (final YearRange validRange : check.validRanges()) {
      yearArbitrary
        .between(validRange.from().getValue(), validRange.to().getValue())
        .forEachValue(year -> {
            final Set<Holiday> holidays = holidayManager.getHolidays(year, check.subdivisions());
            final LocalDate dateToCheck = LocalDate.of(year.getValue(), check.month(), check.day());
            final LocalDate shiftLocalDate = shiftLocalDate(check, dateToCheck);
            final Holiday holiday = new Holiday(shiftLocalDate, check.propertiesKey(), check.holidayType());
            assertHolidayPresent(holidays, holiday, holidayManager, check.subdivisions());
          }
        );
    }
  }

  private void assertHolidayNotPresent(Set<Holiday> holidays, Holiday holiday, HolidayManager holidayManager, String[] subdivisions) {
    assertThat(holidays)
      .isNotEmpty()
      .as(() -> buildAssertionMessage(holidayManager, subdivisions))
      .doesNotContain(holiday);
  }

  private void assertHolidayPresent(Set<Holiday> holidays, Holiday holiday, HolidayManager holidayManager, String[] subdivisions) {
    assertThat(holidays)
      .isNotEmpty()
      .as(() -> buildAssertionMessage(holidayManager, subdivisions))
      .contains(holiday);
  }

  private String buildAssertionMessage(HolidayManager holidayManager, String[] subdivisions) {
    final String displayName = holidayManager != null && holidayManager.getManagerParameter() != null ? holidayManager.getManagerParameter().getDisplayName() : "UnknownManager";
    final String subdivisionsStr = subdivisions != null ? Arrays.toString(subdivisions) : "[]";
    return "Failure in a holiday for " + displayName + " " + subdivisionsStr;
  }

  private static LocalDate shiftLocalDate(final HolidayCalendarCheck check, LocalDate date) {
    if (check == null || date == null) {
      return date;
    }

    LocalDate shiftedDate = date;
    for (WeekDayFromTo shift : check.validShifts) {
      if (shift != null && date.getDayOfWeek().equals(shift.from())) {
        if (shift.adjuster == NEXT) {
          shiftedDate = date.with(nextOrSame(shift.to()));
        } else {
          shiftedDate = date.with(previousOrSame(shift.to()));
        }
      }
    }

    return shiftedDate;
  }

  private void checkByKey(final HolidayCalendarCheck check, final HolidayManager holidayManager) {
    final YearArbitrary yearArbitrary = createYearArbitrary();

    for (final YearRange invalidRange : check.invalidRanges()) {
      yearArbitrary
        .between(invalidRange.from().getValue(), invalidRange.to().getValue())
        .forEachValue(year -> {
            final Set<Holiday> holidays = holidayManager.getHolidays(year, check.subdivisions());
            assertThat(holidays)
              .isNotEmpty()
              .filteredOn(holiday -> holiday.getPropertiesKey().equals(check.propertiesKey()))
              .extracting(Holiday::getType)
              .withFailMessage("Holiday '" + check.propertiesKey() + "' with holiday type '" + check.holidayType + "' in year '" + year + "' not found.")
              .doesNotContain(check.holidayType());
          }
        );
    }

    for (final YearRange validRange : check.validRanges()) {
      yearArbitrary
        .between(validRange.from().getValue(), validRange.to().getValue())
        .forEachValue(year -> {
            final Set<Holiday> holidays = holidayManager.getHolidays(year, check.subdivisions());
            assertThat(holidays)
              .isNotEmpty()
              .filteredOn(holiday -> holiday.getPropertiesKey().equals(check.propertiesKey()))
              .extracting(Holiday::getType)
              .withFailMessage("Holiday '" + check.propertiesKey() + "' with holiday type '" + check.holidayType + "' in year '" + year + "' and in subdivision '" + Arrays.toString(check.subdivisions()) + "' not found.")
              .contains(check.holidayType());
          }
        );
    }
  }

  private static YearArbitrary createYearArbitrary() {
    return (YearArbitrary) Arbitraries.defaultFor(Year.class);
  }

  private void clearProperties() {
    this.propertyKey = null;
    this.month = null;
    this.day = 0;
    this.type = null;
    this.category = null;
    this.subdivisions = new String[]{};
    this.validRanges = new ArrayList<>();
    this.invalidRanges = new ArrayList<>();
    this.validShifts = new ArrayList<>();
  }

  private record HolidayCalendarCheck(
    HolidayCalendar calendar, String propertiesKey, Month month, int day,
    HolidayType holidayType, List<YearRange> validRanges,
    List<YearRange> invalidRanges, List<WeekDayFromTo> validShifts,
    String[] subdivisions, Category category
  ) {

    private static final YearRange DEFAULT_YEAR_RANGE = new YearRange(Year.of(1900), Year.of(2500));

    private HolidayCalendarCheck(
      HolidayCalendar calendar,
      String propertiesKey, Month month, int day, HolidayType holidayType,
      List<YearRange> validRanges, List<YearRange> invalidRanges,
      List<WeekDayFromTo> validShifts, String[] subdivisions, Category category
    ) {
      this.calendar = calendar;
      this.propertiesKey = propertiesKey;
      this.month = month;
      this.day = day;
      this.holidayType = holidayType;
      this.validRanges = validRanges.isEmpty() ? List.of(DEFAULT_YEAR_RANGE) : unmodifiableList(validRanges);
      this.invalidRanges = unmodifiableList(invalidRanges);
      this.validShifts = unmodifiableList(validShifts);
      this.subdivisions = subdivisions;
      this.category = category;
    }
  }

  private record YearRange(Year from, Year to) {
    private YearRange {
      if (from != null && to != null) {
        Assertions.assertFalse(from.isAfter(to), "To must be greater than or equal to the from year.");
      }
    }
  }

  private record WeekDayFromTo(DayOfWeek from, DayOfWeek to, Adjuster adjuster) {
  }
}
