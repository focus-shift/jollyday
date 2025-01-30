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
import java.time.temporal.TemporalAdjusters;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Set;

import static de.focus_shift.jollyday.core.ManagerParameters.create;
import static de.focus_shift.jollyday.tests.CalendarChecker.Category.BY_DAY;
import static de.focus_shift.jollyday.tests.CalendarChecker.Category.BY_KEY;
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
    return hasChristianHoliday(propertyKey, HolidayType.PUBLIC_HOLIDAY);
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
    return hasIslamicHoliday(propertyKey, HolidayType.PUBLIC_HOLIDAY);
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
  public CalendarCheckerApi.Properties hasFixedHoliday(final String propertyKey, final Month month, final int day) {
    return hasFixedHoliday(propertyKey, month, day, HolidayType.PUBLIC_HOLIDAY);
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
  public CalendarCheckerApi.Properties canBeenShiftedFrom(DayOfWeek from, DayOfWeek to) {
    this.validShifts.add(new WeekDayFromTo(from, to, Adjuster.NEXT));
    return this;
  }

  @Override
  public CalendarCheckerApi.Properties canBeenShiftedFrom(DayOfWeek from, Adjuster adjuster, DayOfWeek to) {
    this.validShifts.add(new WeekDayFromTo(from, to, adjuster));
    return this;
  }

  @Override
  public CalendarCheckerApi.Holiday and() {
    checks.add(new HolidayCalendarCheck(this.calendar, this.propertyKey, this.month, this.day, this.type, this.validRanges, this.invalidRanges, this.validShifts, this.subdivisions, this.category));

    clearProperties();

    return this;
  }

  @Override
  public void check() {
    checks.add(new HolidayCalendarCheck(calendar, propertyKey, month, day, type, validRanges, invalidRanges, validShifts, subdivisions, category));

    clearProperties();

    final HolidayManager holidayManager = HolidayManager.getInstance(create(checks.get(0).getCalendar()));

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

    for (final YearRange invalidRange : check.getInvalidRanges()) {
      yearArbitrary
        .between(invalidRange.getFrom().getValue(), invalidRange.getTo().getValue())
        .forEachValue(year -> {
            final Set<Holiday> holidays = holidayManager.getHolidays(year, check.getSubdivisions());

            LocalDate date = LocalDate.of(year.getValue(), check.getMonth(), check.getDay());
            date = shiftLocalDate(check, date);

            assertThat(holidays)
              .isNotEmpty()
              .doesNotContain(new Holiday(date, check.getPropertiesKey(), check.getHolidayType()));
          }
        );
    }

    for (final YearRange validRange : check.getValidRanges()) {
      yearArbitrary
        .between(validRange.getFrom().getValue(), validRange.getTo().getValue())
        .forEachValue(year -> {
            final Set<Holiday> holidays = holidayManager.getHolidays(year, check.getSubdivisions());

            LocalDate date = LocalDate.of(year.getValue(), check.getMonth(), check.getDay());
            date = shiftLocalDate(check, date);

            assertThat(holidays)
              .isNotEmpty()
              .contains(new Holiday(date, check.getPropertiesKey(), check.getHolidayType()));
          }
        );
    }
  }

  private static LocalDate shiftLocalDate(final HolidayCalendarCheck check, LocalDate date) {

    if (!check.validShifts.isEmpty()) {
      for (WeekDayFromTo shift : check.validShifts) {
        if (date.getDayOfWeek().equals(shift.getFrom())) {
          if (shift.adjuster == Adjuster.NEXT) {
            date = date.with(TemporalAdjusters.nextOrSame(shift.getTo()));
          } else {
            date = date.with(TemporalAdjusters.previousOrSame(shift.getTo()));
          }
        }
      }
    }
    return date;
  }

  private void checkByKey(final HolidayCalendarCheck check, final HolidayManager holidayManager) {
    final YearArbitrary yearArbitrary = createYearArbitrary();

    for (final YearRange invalidRange : check.getInvalidRanges()) {
      yearArbitrary
        .between(invalidRange.getFrom().getValue(), invalidRange.getTo().getValue())
        .forEachValue(year -> {
            final Set<Holiday> holidays = holidayManager.getHolidays(year, check.getSubdivisions());
            assertThat(holidays)
              .isNotEmpty()
              .filteredOn(holiday -> holiday.getPropertiesKey().equals(check.getPropertiesKey()))
              .extracting(Holiday::getType)
              .withFailMessage("Holiday '" + check.getPropertiesKey() + "' with holiday type '" + check.holidayType + "' in year '" + year + "' not found.")
              .doesNotContain(check.getHolidayType());
          }
        );
    }

    for (final YearRange validRange : check.getValidRanges()) {
      yearArbitrary
        .between(validRange.getFrom().getValue(), validRange.getTo().getValue())
        .forEachValue(year -> {
            final Set<Holiday> holidays = holidayManager.getHolidays(year, check.getSubdivisions());
            assertThat(holidays)
              .isNotEmpty()
              .filteredOn(holiday -> holiday.getPropertiesKey().equals(check.getPropertiesKey()))
              .extracting(Holiday::getType)
              .withFailMessage("Holiday '" + check.getPropertiesKey() + "' with holiday type '" + check.holidayType + "' in year '" + year + "' not found.")
              .contains(check.getHolidayType());
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

  private static final class HolidayCalendarCheck {

    private static final YearRange DEFAULT_YEAR_RANGE = new YearRange(Year.of(1900), Year.of(2500));

    private final HolidayCalendar calendar;
    private final List<YearRange> validRanges;
    private final List<YearRange> invalidRanges;
    private final List<WeekDayFromTo> validShifts;
    private final Month month;
    private final int day;
    private final String propertiesKey;
    private final HolidayType holidayType;
    private final String[] subdivisions;
    private final Category category;

    private HolidayCalendarCheck(HolidayCalendar calendar,
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

    public HolidayCalendar getCalendar() {
      return calendar;
    }

    public List<YearRange> getValidRanges() {
      return validRanges;
    }

    public List<YearRange> getInvalidRanges() {
      return invalidRanges;
    }

    public List<WeekDayFromTo> getValidShifts() {
      return validShifts;
    }

    public Month getMonth() {
      return month;
    }

    public int getDay() {
      return day;
    }

    public String getPropertiesKey() {
      return propertiesKey;
    }

    public HolidayType getHolidayType() {
      return holidayType;
    }

    public String[] getSubdivisions() {
      return subdivisions;
    }

    public Category getCategory() {
      return category;
    }
  }

  private static final class YearRange {

    private final Year from;
    private final Year to;

    private YearRange(final Year from, final Year to) {
      if (from != null && to != null) {
        Assertions.assertFalse(from.isAfter(to), "To must be greater than or equal to the from year.");
      }
      this.from = from;
      this.to = to;
    }

    public Year getFrom() {
      return from;
    }

    public Year getTo() {
      return to;
    }
  }

  private static final class WeekDayFromTo {

    private final DayOfWeek from;
    private final DayOfWeek to;
    private final Adjuster adjuster;

    private WeekDayFromTo(final DayOfWeek from, final DayOfWeek to, final Adjuster adjuster) {
      this.from = from;
      this.to = to;
      this.adjuster = adjuster;
    }

    public DayOfWeek getFrom() {
      return from;
    }

    public DayOfWeek getTo() {
      return to;
    }

    public Adjuster getAdjuster() {
      return adjuster;
    }
  }
}
