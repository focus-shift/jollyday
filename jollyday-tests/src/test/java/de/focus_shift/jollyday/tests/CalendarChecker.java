package de.focus_shift.jollyday.tests;

import de.focus_shift.jollyday.core.Holiday;
import de.focus_shift.jollyday.core.HolidayCalendar;
import de.focus_shift.jollyday.core.HolidayManager;
import de.focus_shift.jollyday.core.HolidayType;
import de.focus_shift.jollyday.core.spi.Occurrence;
import de.focus_shift.jollyday.core.spi.Relation;
import net.jqwik.api.Arbitraries;
import net.jqwik.time.api.arbitraries.YearArbitrary;
import org.junit.jupiter.api.Assertions;
import org.threeten.extra.Days;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.Month;
import java.time.MonthDay;
import java.time.Year;
import java.time.temporal.Temporal;
import java.time.temporal.TemporalAdjuster;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.function.Function;

import static de.focus_shift.jollyday.core.HolidayType.PUBLIC_HOLIDAY;
import static de.focus_shift.jollyday.core.ManagerParameters.create;
import static de.focus_shift.jollyday.core.spi.Occurrence.LAST;
import static de.focus_shift.jollyday.tests.CalendarChecker.Adjuster.NEXT;
import static de.focus_shift.jollyday.tests.CalendarChecker.Category.BY_DAY;
import static de.focus_shift.jollyday.tests.CalendarChecker.Category.BY_KEY;
import static de.focus_shift.jollyday.tests.CalendarChecker.Category.BY_WEEKDAY_BETWEEN_FIXED;
import static de.focus_shift.jollyday.tests.CalendarChecker.Category.BY_WEEKDAY_IN_MONTH;
import static de.focus_shift.jollyday.tests.CalendarChecker.Category.BY_WEEKDAY_RELATIVE_TO_FIXED;
import static de.focus_shift.jollyday.tests.CalendarChecker.Category.BY_WEEKDAY_RELATIVE_TO_WEEKDAY_IN_MONTH;
import static java.time.temporal.TemporalAdjusters.dayOfWeekInMonth;
import static java.time.temporal.TemporalAdjusters.lastInMonth;
import static java.time.temporal.TemporalAdjusters.next;
import static java.time.temporal.TemporalAdjusters.nextOrSame;
import static java.time.temporal.TemporalAdjusters.previous;
import static java.time.temporal.TemporalAdjusters.previousOrSame;
import static java.util.Collections.unmodifiableList;
import static org.assertj.core.api.Assertions.assertThat;

public class CalendarChecker implements
  CalendarCheckerApi.Holiday,
  CalendarCheckerApi.Between,
  CalendarCheckerApi.Properties {

  enum Category {
    BY_DAY,
    BY_KEY,
    BY_WEEKDAY_IN_MONTH,
    BY_WEEKDAY_BETWEEN_FIXED,
    BY_WEEKDAY_RELATIVE_TO_FIXED,
    BY_WEEKDAY_RELATIVE_TO_WEEKDAY_IN_MONTH
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
  private Occurrence which;
  private DayOfWeek weekday;
  private MonthDay from;
  private MonthDay to;
  private Relation when;
  private MonthDay anchor;
  private DayOfWeek anchorWeekday;
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
    return hasChristianHoliday(propertyKey, PUBLIC_HOLIDAY, false);
  }

  @Override
  public CalendarCheckerApi.Properties hasChristianHoliday(final String propertyKey, boolean overriddenPropertiesKey) {
    return hasChristianHoliday(propertyKey, PUBLIC_HOLIDAY, overriddenPropertiesKey);
  }

  @Override
  public CalendarCheckerApi.Properties hasChristianHoliday(final String propertyKey, final HolidayType type) {
    return hasChristianHoliday(propertyKey, type, false);
  }

  @Override
  public CalendarCheckerApi.Properties hasChristianHoliday(final String propertyKey, final HolidayType type, boolean overriddenPropertiesKey) {
    Objects.requireNonNull(propertyKey, "propertyKey is required");
    Objects.requireNonNull(type, "holiday type is required");

    this.category = BY_KEY;
    this.propertyKey = overriddenPropertiesKey ? propertyKey : "christian." + propertyKey;
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
  public CalendarCheckerApi.Properties hasFixedWeekdayHoliday(final String propertyKey, final Occurrence which, final DayOfWeek weekday, final Month month) {
    return hasFixedWeekdayHoliday(propertyKey, which, weekday, month, PUBLIC_HOLIDAY);
  }

  @Override
  public CalendarCheckerApi.Properties hasFixedWeekdayHoliday(final String propertyKey, final Occurrence which, final DayOfWeek weekday, final Month month, final HolidayType type) {
    Objects.requireNonNull(propertyKey, "propertyKey is required");
    Objects.requireNonNull(which, "which is required");
    Objects.requireNonNull(weekday, "weekday is required");
    Objects.requireNonNull(month, "month is required");
    Objects.requireNonNull(type, "holiday type is required");

    this.category = BY_WEEKDAY_IN_MONTH;
    this.propertyKey = propertyKey;
    this.which = which;
    this.weekday = weekday;
    this.month = month;
    this.type = type;

    return this;
  }

  @Override
  public CalendarCheckerApi.Properties hasFixedWeekdayBetweenFixedHoliday(final String propertyKey, final DayOfWeek weekday, final MonthDay from, final MonthDay to) {
    return hasFixedWeekdayBetweenFixedHoliday(propertyKey, weekday, from, to, PUBLIC_HOLIDAY);
  }

  @Override
  public CalendarCheckerApi.Properties hasFixedWeekdayBetweenFixedHoliday(final String propertyKey, final DayOfWeek weekday, final MonthDay from, final MonthDay to, final HolidayType type) {
    Objects.requireNonNull(propertyKey, "propertyKey is required");
    Objects.requireNonNull(weekday, "weekday is required");
    Objects.requireNonNull(from, "from is required");
    Objects.requireNonNull(to, "to is required");
    Objects.requireNonNull(type, "holiday type is required");

    this.category = BY_WEEKDAY_BETWEEN_FIXED;
    this.propertyKey = propertyKey;
    this.weekday = weekday;
    this.from = from;
    this.to = to;
    this.type = type;

    return this;
  }

  @Override
  public CalendarCheckerApi.Properties hasFixedWeekdayRelativeToFixedHoliday(final String propertyKey, final Occurrence which, final DayOfWeek weekday, final Relation when, final MonthDay anchor) {
    return hasFixedWeekdayRelativeToFixedHoliday(propertyKey, which, weekday, when, anchor, PUBLIC_HOLIDAY);
  }

  @Override
  public CalendarCheckerApi.Properties hasFixedWeekdayRelativeToFixedHoliday(final String propertyKey, final Occurrence which, final DayOfWeek weekday, final Relation when, final MonthDay anchor, final HolidayType type) {
    Objects.requireNonNull(propertyKey, "propertyKey is required");
    Objects.requireNonNull(which, "which is required");
    Objects.requireNonNull(weekday, "weekday is required");
    Objects.requireNonNull(when, "when is required");
    Objects.requireNonNull(anchor, "anchor is required");
    Objects.requireNonNull(type, "holiday type is required");

    this.category = BY_WEEKDAY_RELATIVE_TO_FIXED;
    this.propertyKey = propertyKey;
    this.which = which;
    this.weekday = weekday;
    this.when = when;
    this.anchor = anchor;
    this.type = type;

    return this;
  }

  @Override
  public CalendarCheckerApi.Properties hasRelativeToWeekdayInMonthHoliday(final String propertyKey, final DayOfWeek weekday, final Relation when, final Occurrence anchorWhich, final DayOfWeek anchorWeekday, final Month anchorMonth) {
    return hasRelativeToWeekdayInMonthHoliday(propertyKey, weekday, when, anchorWhich, anchorWeekday, anchorMonth, PUBLIC_HOLIDAY);
  }

  @Override
  public CalendarCheckerApi.Properties hasRelativeToWeekdayInMonthHoliday(final String propertyKey, final DayOfWeek weekday, final Relation when, final Occurrence anchorWhich, final DayOfWeek anchorWeekday, final Month anchorMonth, final HolidayType type) {
    Objects.requireNonNull(propertyKey, "propertyKey is required");
    Objects.requireNonNull(weekday, "weekday is required");
    Objects.requireNonNull(when, "when is required");
    Objects.requireNonNull(anchorWhich, "anchorWhich is required");
    Objects.requireNonNull(anchorWeekday, "anchorWeekday is required");
    Objects.requireNonNull(anchorMonth, "anchorMonth is required");
    Objects.requireNonNull(type, "holiday type is required");

    this.category = BY_WEEKDAY_RELATIVE_TO_WEEKDAY_IN_MONTH;
    this.propertyKey = propertyKey;
    this.weekday = weekday;
    this.when = when;
    this.which = anchorWhich;
    this.anchorWeekday = anchorWeekday;
    this.month = anchorMonth;
    this.type = type;

    return this;
  }

  @Override
  public CalendarCheckerApi.Properties validFrom(final Year from) {
    this.validRanges.add(new YearRange(from, Year.of(2500)));
    return this;
  }

  @Override
  public CalendarCheckerApi.Properties validTo(final Year to) {
    this.validRanges.add(new YearRange(Year.of(1900), to));
    return this;
  }

  @Override
  public CalendarCheckerApi.Properties validBetween(final Year from, final Year to) {
    this.validRanges.add(new YearRange(from, to));
    return this;
  }

  @Override
  public CalendarCheckerApi.Properties notValidBetween(final Year from, final Year to) {
    this.invalidRanges.add(new YearRange(from, to));
    return this;
  }

  @Override
  public CalendarCheckerApi.Properties inSubdivision(String... subdivisions) {
    this.subdivisions = subdivisions;
    return this;
  }

  @Override
  public CalendarCheckerApi.Properties canBeMovedFrom(DayOfWeek from, DayOfWeek to) {
    this.validShifts.add(new WeekDayFromTo(from, to, NEXT));
    return this;
  }

  @Override
  public CalendarCheckerApi.Properties canBeMovedFrom(DayOfWeek from, Adjuster adjuster, DayOfWeek to) {
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
      this.which,
      this.weekday,
      this.from,
      this.to,
      this.when,
      this.anchor,
      this.anchorWeekday,
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
    and();

    final HolidayManager holidayManager = HolidayManager.getInstance(create(checks.get(0).calendar()));

    for (HolidayCalendarCheck check : checks) {

      if (check.category == null) {
        continue;
      }

      switch (check.category) {
        case BY_DAY:
          checkByDate(check, holidayManager);
          break;
        case BY_KEY:
          checkByKey(check, holidayManager);
          break;
        case BY_WEEKDAY_IN_MONTH:
          checkByWeekdayInMonth(check, holidayManager);
          break;
        case BY_WEEKDAY_BETWEEN_FIXED:
          checkByWeekdayBetweenFixed(check, holidayManager);
          break;
        case BY_WEEKDAY_RELATIVE_TO_FIXED:
          checkByWeekdayRelativeToFixed(check, holidayManager);
          break;
        case BY_WEEKDAY_RELATIVE_TO_WEEKDAY_IN_MONTH:
          checkByWeekdayRelativeToWeekdayInMonth(check, holidayManager);
          break;
        default:
          throw new IllegalStateException("Unexpected value: " + check.category);
      }
    }

    this.checks.clear();
  }

  private void checkByDate(final HolidayCalendarCheck check, final HolidayManager holidayManager) {
    checkComputedDate(check, holidayManager, year -> LocalDate.of(year.getValue(), check.month(), check.day()));
  }

  private void checkByWeekdayInMonth(final HolidayCalendarCheck check, final HolidayManager holidayManager) {
    checkComputedDate(check, holidayManager, year -> weekdayInMonth(year, check.month(), check.which(), check.weekday()));
  }

  private void checkByWeekdayBetweenFixed(final HolidayCalendarCheck check, final HolidayManager holidayManager) {
    checkComputedDate(check, holidayManager, year -> weekdayBetween(year, check.from(), check.to(), check.weekday()));
  }

  private void checkByWeekdayRelativeToFixed(final HolidayCalendarCheck check, final HolidayManager holidayManager) {
    checkComputedDate(check, holidayManager, year -> weekdayRelativeTo(year, check.anchor(), check.which(), check.weekday(), check.when()));
  }

  private void checkByWeekdayRelativeToWeekdayInMonth(final HolidayCalendarCheck check, final HolidayManager holidayManager) {
    checkComputedDate(check, holidayManager, year -> weekdayRelativeToWeekdayInMonth(year, check.month(), check.which(), check.anchorWeekday(), check.weekday(), check.when()));
  }

  private void checkComputedDate(final HolidayCalendarCheck check, final HolidayManager holidayManager, final Function<Year, LocalDate> dateSupplier) {
    final YearArbitrary yearArbitrary = createYearArbitrary();

    for (final YearRange invalidRange : check.invalidRanges()) {
      yearArbitrary
        .between(invalidRange.from().getValue(), invalidRange.to().getValue())
        .forEachValue(year -> {
            final Set<Holiday> holidays = holidayManager.getHolidays(year, check.subdivisions());
            final LocalDate shiftLocalDate = shiftLocalDate(check, dateSupplier.apply(year));
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
            final LocalDate shiftLocalDate = shiftLocalDate(check, dateSupplier.apply(year));
            final Holiday holiday = new Holiday(shiftLocalDate, check.propertiesKey(), check.holidayType());
            assertHolidayPresent(holidays, holiday, holidayManager, check.subdivisions());
          }
        );
    }
  }

  private static LocalDate weekdayInMonth(final Year year, final Month month, final Occurrence which, final DayOfWeek weekday) {
    final LocalDate firstOfMonth = LocalDate.of(year.getValue(), month, 1);
    return which == LAST ? firstOfMonth.with(lastInMonth(weekday)) : firstOfMonth.with(dayOfWeekInMonth(which.ordinal() + 1, weekday));
  }

  private static LocalDate weekdayBetween(final Year year, final MonthDay from, final MonthDay to, final DayOfWeek weekday) {
    final LocalDate start = from.atYear(year.getValue());
    final LocalDate end = to.atYear(year.getValue());
    return start.datesUntil(end.plusDays(1))
      .filter(date -> date.getDayOfWeek() == weekday)
      .findFirst()
      .orElseThrow(() -> new IllegalStateException("No " + weekday + " found between " + start + " and " + end));
  }

  private static LocalDate weekdayRelativeTo(final Year year, final MonthDay anchorMonthDay, final Occurrence which, final DayOfWeek weekday, final Relation when) {
    if (which == LAST) {
      throw new UnsupportedOperationException("Occurrence.LAST is not supported for a weekday relative to a fixed date");
    }

    final LocalDate anchor = anchorMonthDay.atYear(year.getValue());
    final TemporalAdjuster adjuster = switch (when) {
      case AFTER -> next(weekday);
      case BEFORE -> previous(weekday);
      case CLOSEST -> closestWeekday(weekday);
    };
    LocalDate result = anchor.with(adjuster);

    if (when != Relation.CLOSEST) {
      final int days = switch (which) {
        case SECOND -> 7;
        case THIRD -> 14;
        case FOURTH -> 21;
        default -> 0;
      };
      result = when == Relation.AFTER ? result.plusDays(days) : result.minusDays(days);
    }

    return result;
  }

  private static LocalDate weekdayRelativeToWeekdayInMonth(final Year year, final Month anchorMonth, final Occurrence anchorWhich, final DayOfWeek anchorWeekday, final DayOfWeek weekday, final Relation when) {
    if (when == Relation.CLOSEST) {
      throw new UnsupportedOperationException("Relation.CLOSEST is not supported for a weekday relative to a weekday-in-month anchor");
    }

    final LocalDate baseDate = weekdayInMonth(year, anchorMonth, anchorWhich, anchorWeekday);

    final int currentDayValue = baseDate.getDayOfWeek().getValue();
    final int targetDayValue = weekday.getValue();
    final int direction = when == Relation.BEFORE ? -1 : 1;

    int daysDifference = targetDayValue - currentDayValue;
    if (direction < 0 && daysDifference >= 0) {
      daysDifference -= 7;
    } else if (direction > 0 && daysDifference <= 0) {
      daysDifference += 7;
    }

    return baseDate.plusDays(daysDifference);
  }

  private static TemporalAdjuster closestWeekday(final DayOfWeek weekday) {
    return temporal -> {
      final Temporal previousOccurrence = temporal.with(previousOrSame(weekday));
      final Temporal nextOccurrence = temporal.with(nextOrSame(weekday));
      final int previousDays = Days.between(temporal, previousOccurrence).abs().getAmount();
      final int nextDays = Days.between(temporal, nextOccurrence).abs().getAmount();
      return previousDays <= nextDays ? previousOccurrence : nextOccurrence;
    };
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
    final String displayName = holidayManager != null ? holidayManager.getManagerParameter().getDisplayName() : "UnknownManager";
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
    this.which = null;
    this.weekday = null;
    this.from = null;
    this.to = null;
    this.when = null;
    this.anchor = null;
    this.anchorWeekday = null;
    this.subdivisions = new String[]{};
    this.validRanges = new ArrayList<>();
    this.invalidRanges = new ArrayList<>();
    this.validShifts = new ArrayList<>();
  }

  private record HolidayCalendarCheck(
    HolidayCalendar calendar, String propertiesKey, Month month, int day,
    HolidayType holidayType, Occurrence which, DayOfWeek weekday,
    MonthDay from, MonthDay to, Relation when, MonthDay anchor, DayOfWeek anchorWeekday,
    List<YearRange> validRanges,
    List<YearRange> invalidRanges, List<WeekDayFromTo> validShifts,
    String[] subdivisions, Category category
  ) {

    private static final YearRange DEFAULT_YEAR_RANGE = new YearRange(Year.of(1900), Year.of(2500));

    private HolidayCalendarCheck(
      HolidayCalendar calendar,
      String propertiesKey, Month month, int day, HolidayType holidayType,
      Occurrence which, DayOfWeek weekday, MonthDay from, MonthDay to, Relation when, MonthDay anchor, DayOfWeek anchorWeekday,
      List<YearRange> validRanges, List<YearRange> invalidRanges,
      List<WeekDayFromTo> validShifts, String[] subdivisions, Category category
    ) {
      this.calendar = calendar;
      this.propertiesKey = propertiesKey;
      this.month = month;
      this.day = day;
      this.holidayType = holidayType;
      this.which = which;
      this.weekday = weekday;
      this.from = from;
      this.to = to;
      this.when = when;
      this.anchor = anchor;
      this.anchorWeekday = anchorWeekday;
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
