package de.focus_shift.jollyday.jackson;

import de.focus_shift.jollyday.core.HolidayType;
import de.focus_shift.jollyday.core.spi.FixedHolidayConfiguration;
import de.focus_shift.jollyday.core.spi.FixedWeekdayBetweenFixedHolidayConfiguration;
import de.focus_shift.jollyday.jackson.mapping.FixedWeekdayBetweenFixed;
import org.jspecify.annotations.NonNull;

import java.time.DayOfWeek;
import java.time.Year;
import java.util.Optional;

/**
 * see {@link FixedWeekdayBetweenFixedHolidayConfiguration}
 */
class JacksonFixedWeekdayBetweenFixed implements FixedWeekdayBetweenFixedHolidayConfiguration {

  private final FixedWeekdayBetweenFixed fixedWeekdayBetweenFixed;

  JacksonFixedWeekdayBetweenFixed(FixedWeekdayBetweenFixed fixedWeekdayInMonth) {
    this.fixedWeekdayBetweenFixed = fixedWeekdayInMonth;
  }

  /**
   * {@inheritDoc}
   *
   * @return {@inheritDoc}
   */
  @Override
  public @NonNull FixedHolidayConfiguration from() {
    return new JacksonFixed(fixedWeekdayBetweenFixed.getFrom());
  }

  /**
   * {@inheritDoc}
   *
   * @return {@inheritDoc}
   */
  @Override
  public @NonNull FixedHolidayConfiguration to() {
    return new JacksonFixed(fixedWeekdayBetweenFixed.getTo());
  }

  /**
   * {@inheritDoc}
   *
   * @return {@inheritDoc}
   */
  @Override
  public @NonNull DayOfWeek weekday() {
    return DayOfWeek.valueOf(fixedWeekdayBetweenFixed.getWeekday().name());
  }

  /**
   * {@inheritDoc}
   *
   * @return {@inheritDoc}
   */
  @Override
  public @NonNull String descriptionPropertiesKey() {
    return fixedWeekdayBetweenFixed.getDescriptionPropertiesKey();
  }

  /**
   * {@inheritDoc}
   *
   * @return {@inheritDoc}
   */
  @Override
  public @NonNull HolidayType holidayType() {
    return fixedWeekdayBetweenFixed.getLocalizedType() == null
      ? HolidayType.PUBLIC_HOLIDAY
      : HolidayType.valueOf(fixedWeekdayBetweenFixed.getLocalizedType().name());
  }

  /**
   * {@inheritDoc}
   *
   * @return {@inheritDoc}
   */
  @Override
  public @NonNull Optional<Year> validFrom() {
    return fixedWeekdayBetweenFixed.getValidFrom() == null
      ? Optional.empty()
      : Optional.of(Year.of(fixedWeekdayBetweenFixed.getValidFrom()));
  }

  /**
   * {@inheritDoc}
   *
   * @return {@inheritDoc}
   */
  @Override
  public @NonNull Optional<Year> validTo() {
    return fixedWeekdayBetweenFixed.getValidTo() == null
      ? Optional.empty()
      : Optional.of(Year.of(fixedWeekdayBetweenFixed.getValidTo()));
  }

  /**
   * {@inheritDoc}
   *
   * @return {@inheritDoc}
   */
  @Override
  public @NonNull YearCycle cycle() {
    return fixedWeekdayBetweenFixed.getEvery() == null
      ? YearCycle.EVERY_YEAR
      : YearCycle.valueOf(fixedWeekdayBetweenFixed.getEvery().name());
  }
}
