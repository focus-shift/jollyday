package de.focus_shift.jollyday.jackson;

import de.focus_shift.jollyday.core.HolidayType;
import de.focus_shift.jollyday.core.spi.FixedWeekdayInMonthHolidayConfiguration;
import de.focus_shift.jollyday.core.spi.Occurrence;
import de.focus_shift.jollyday.jackson.mapping.FixedWeekdayInMonth;
import java.time.DayOfWeek;
import java.time.Month;
import java.time.Year;
import org.jspecify.annotations.NonNull;
import org.jspecify.annotations.Nullable;

/** see {@link FixedWeekdayInMonthHolidayConfiguration} */
class JacksonFixedWeekdayInMonth implements FixedWeekdayInMonthHolidayConfiguration {

  private final FixedWeekdayInMonth fixedWeekdayInMonth;

  JacksonFixedWeekdayInMonth(FixedWeekdayInMonth fixedWeekdayInMonth) {
    this.fixedWeekdayInMonth = fixedWeekdayInMonth;
  }

  /**
   * {@inheritDoc}
   *
   * @return {@inheritDoc}
   */
  @Override
  public @NonNull DayOfWeek weekday() {
    return DayOfWeek.valueOf(fixedWeekdayInMonth.getWeekday().name());
  }

  /**
   * {@inheritDoc}
   *
   * @return {@inheritDoc}
   */
  @Override
  public @NonNull Month month() {
    return Month.valueOf(fixedWeekdayInMonth.getMonth().name());
  }

  /**
   * {@inheritDoc}
   *
   * @return {@inheritDoc}
   */
  @Override
  public @NonNull Occurrence which() {
    return Occurrence.valueOf(fixedWeekdayInMonth.getWhich().name());
  }

  /**
   * {@inheritDoc}
   *
   * @return {@inheritDoc}
   */
  @Override
  public @NonNull String descriptionPropertiesKey() {
    return fixedWeekdayInMonth.getDescriptionPropertiesKey();
  }

  /**
   * {@inheritDoc}
   *
   * @return {@inheritDoc}
   */
  @Override
  public @NonNull HolidayType holidayType() {
    return fixedWeekdayInMonth.getLocalizedType() == null
        ? HolidayType.PUBLIC_HOLIDAY
        : HolidayType.valueOf(fixedWeekdayInMonth.getLocalizedType().name());
  }

  /**
   * {@inheritDoc}
   *
   * @return {@inheritDoc}
   */
  @Override
  public @Nullable Year validFrom() {
    return fixedWeekdayInMonth.getValidFrom() == null
        ? null
        : Year.of(fixedWeekdayInMonth.getValidFrom());
  }

  /**
   * {@inheritDoc}
   *
   * @return {@inheritDoc}
   */
  @Override
  public @Nullable Year validTo() {
    return fixedWeekdayInMonth.getValidTo() == null
        ? null
        : Year.of(fixedWeekdayInMonth.getValidTo());
  }

  /**
   * {@inheritDoc}
   *
   * @return {@inheritDoc}
   */
  @Override
  public @NonNull YearCycle cycle() {
    return fixedWeekdayInMonth.getEvery() == null
        ? YearCycle.EVERY_YEAR
        : YearCycle.valueOf(fixedWeekdayInMonth.getEvery().name());
  }
}
