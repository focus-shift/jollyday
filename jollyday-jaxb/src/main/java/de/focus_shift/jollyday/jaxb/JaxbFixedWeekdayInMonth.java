package de.focus_shift.jollyday.jaxb;

import de.focus_shift.jollyday.core.HolidayType;
import de.focus_shift.jollyday.core.spi.FixedWeekdayInMonthHolidayConfiguration;
import de.focus_shift.jollyday.core.spi.Occurrence;
import de.focus_shift.jollyday.jaxb.mapping.FixedWeekdayInMonth;
import org.jspecify.annotations.NonNull;

import java.time.DayOfWeek;
import java.time.Month;
import java.time.Year;
import java.util.Optional;

/**
 * see {@link FixedWeekdayInMonthHolidayConfiguration}
 */
class JaxbFixedWeekdayInMonth implements FixedWeekdayInMonthHolidayConfiguration {

  private final FixedWeekdayInMonth fixedWeekdayInMonth;

  JaxbFixedWeekdayInMonth(FixedWeekdayInMonth fixedWeekdayInMonth) {
    this.fixedWeekdayInMonth = fixedWeekdayInMonth;
  }

  @Override
  public @NonNull DayOfWeek weekday() {
    return DayOfWeek.valueOf(fixedWeekdayInMonth.getWeekday().name());
  }

  @Override
  public @NonNull Month month() {
    return Month.valueOf(fixedWeekdayInMonth.getMonth().name());
  }

  @Override
  public @NonNull Occurrence which() {
    return Occurrence.valueOf(fixedWeekdayInMonth.getWhich().name());
  }

  @Override
  public @NonNull String descriptionPropertiesKey() {
    return fixedWeekdayInMonth.getDescriptionPropertiesKey();
  }

  @Override
  public @NonNull HolidayType holidayType() {
    return fixedWeekdayInMonth.getLocalizedType() == null
      ? HolidayType.PUBLIC_HOLIDAY
      : HolidayType.valueOf(fixedWeekdayInMonth.getLocalizedType().name());
  }

  @Override
  public @NonNull Optional<Year> validFrom() {
    return fixedWeekdayInMonth.getValidFrom() == null
      ? Optional.empty()
      : Optional.of(Year.of(fixedWeekdayInMonth.getValidFrom()));
  }

  @Override
  public @NonNull Optional<Year> validTo() {
    return fixedWeekdayInMonth.getValidTo() == null
      ? Optional.empty()
      : Optional.of(Year.of(fixedWeekdayInMonth.getValidTo()));
  }

  @Override
  public @NonNull YearCycle cycle() {
    return fixedWeekdayInMonth.getEvery() == null
      ? YearCycle.EVERY_YEAR
      : YearCycle.valueOf(fixedWeekdayInMonth.getEvery().name());
  }
}
