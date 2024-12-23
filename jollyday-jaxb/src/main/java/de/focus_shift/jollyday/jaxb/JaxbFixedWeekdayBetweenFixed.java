package de.focus_shift.jollyday.jaxb;

import de.focus_shift.jollyday.core.HolidayType;
import de.focus_shift.jollyday.core.spi.Fixed;
import de.focus_shift.jollyday.core.spi.FixedWeekdayBetweenFixed;

import java.time.DayOfWeek;
import java.time.Year;

/**
 * {@inheritDoc}
 */
class JaxbFixedWeekdayBetweenFixed implements FixedWeekdayBetweenFixed {

  private final de.focus_shift.jollyday.jaxb.mapping.FixedWeekdayBetweenFixed fixedWeekdayBetweenFixed;

  JaxbFixedWeekdayBetweenFixed(de.focus_shift.jollyday.jaxb.mapping.FixedWeekdayBetweenFixed fixedWeekdayInMonth) {
    this.fixedWeekdayBetweenFixed = fixedWeekdayInMonth;
  }

  /**
   * {@inheritDoc}
   *
   * @return {@inheritDoc}
   */
  @Override
  public Fixed from() {
    return new JaxbFixed(fixedWeekdayBetweenFixed.getFrom());
  }

  /**
   * {@inheritDoc}
   *
   * @return {@inheritDoc}
   */
  @Override
  public Fixed to() {
    return new JaxbFixed(fixedWeekdayBetweenFixed.getTo());
  }

  /**
   * {@inheritDoc}
   *
   * @return {@inheritDoc}
   */
  @Override
  public DayOfWeek weekday() {
    return DayOfWeek.valueOf(fixedWeekdayBetweenFixed.getWeekday().name());
  }

  /**
   * {@inheritDoc}
   *
   * @return {@inheritDoc}
   */
  @Override
  public String descriptionPropertiesKey() {
    return fixedWeekdayBetweenFixed.getDescriptionPropertiesKey();
  }

  /**
   * {@inheritDoc}
   *
   * @return {@inheritDoc}
   */
  @Override
  public HolidayType officiality() {
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
  public Year validFrom() {
    return fixedWeekdayBetweenFixed.getValidFrom() == null
      ? null
      : Year.of(fixedWeekdayBetweenFixed.getValidFrom());
  }

  /**
   * {@inheritDoc}
   *
   * @return {@inheritDoc}
   */
  @Override
  public Year validTo() {
    return fixedWeekdayBetweenFixed.getValidTo() == null
      ? null
      : Year.of(fixedWeekdayBetweenFixed.getValidTo());
  }

  /**
   * {@inheritDoc}
   *
   * @return {@inheritDoc}
   */
  @Override
  public YearCycle cycle() {
    return fixedWeekdayBetweenFixed.getEvery() == null
      ? YearCycle.EVERY_YEAR
      : YearCycle.valueOf(fixedWeekdayBetweenFixed.getEvery().name());
  }
}
