package de.focus_shift.jollyday.jaxb;

import de.focus_shift.jollyday.core.HolidayType;
import de.focus_shift.jollyday.core.spi.Fixed;
import de.focus_shift.jollyday.core.spi.FixedWeekdayRelativeToFixed;
import de.focus_shift.jollyday.core.spi.Occurrence;
import de.focus_shift.jollyday.core.spi.Relation;

import java.time.DayOfWeek;
import java.time.Year;

/**
 * see {@link FixedWeekdayRelativeToFixed}
 */
class JaxbFixedWeekdayRelativeToFixed implements FixedWeekdayRelativeToFixed {

  private final de.focus_shift.jollyday.jaxb.mapping.FixedWeekdayRelativeToFixed fixedWeekdayRelativeToFixed;

  JaxbFixedWeekdayRelativeToFixed(de.focus_shift.jollyday.jaxb.mapping.FixedWeekdayRelativeToFixed fixedWeekdayRelativeToFixed) {
    this.fixedWeekdayRelativeToFixed = fixedWeekdayRelativeToFixed;
  }

  /**
   * {@inheritDoc}
   *
   * @return {@inheritDoc}
   */
  @Override
  public DayOfWeek weekday() {
    return DayOfWeek.valueOf(fixedWeekdayRelativeToFixed.getWeekday().name());
  }

  /**
   * {@inheritDoc}
   *
   * @return {@inheritDoc}
   */
  @Override
  public Relation when() {
    return Relation.valueOf(fixedWeekdayRelativeToFixed.getWhen().name());
  }

  /**
   * {@inheritDoc}
   *
   * @return {@inheritDoc}
   */
  @Override
  public Fixed day() {
    return new JaxbFixed(fixedWeekdayRelativeToFixed.getDay());
  }

  /**
   * {@inheritDoc}
   *
   * @return {@inheritDoc}
   */
  @Override
  public Occurrence which() {
    return Occurrence.valueOf(fixedWeekdayRelativeToFixed.getWhich().name());
  }

  /**
   * {@inheritDoc}
   *
   * @return {@inheritDoc}
   */
  @Override
  public String descriptionPropertiesKey() {
    return fixedWeekdayRelativeToFixed.getDescriptionPropertiesKey();
  }

  /**
   * {@inheritDoc}
   *
   * @return {@inheritDoc}
   */
  @Override
  public HolidayType holidayType() {
    return fixedWeekdayRelativeToFixed.getLocalizedType() == null
      ? HolidayType.PUBLIC_HOLIDAY
      : HolidayType.valueOf(fixedWeekdayRelativeToFixed.getLocalizedType().name());
  }

  /**
   * {@inheritDoc}
   *
   * @return {@inheritDoc}
   */
  @Override
  public Year validFrom() {
    return fixedWeekdayRelativeToFixed.getValidFrom() == null
      ? null
      : Year.of(fixedWeekdayRelativeToFixed.getValidFrom());
  }

  /**
   * {@inheritDoc}
   *
   * @return {@inheritDoc}
   */
  @Override
  public Year validTo() {
    return fixedWeekdayRelativeToFixed.getValidTo() == null
      ? null
      : Year.of(fixedWeekdayRelativeToFixed.getValidTo());
  }

  /**
   * {@inheritDoc}
   *
   * @return {@inheritDoc}
   */
  @Override
  public YearCycle cycle() {
    return fixedWeekdayRelativeToFixed.getEvery() == null
      ? YearCycle.EVERY_YEAR
      : YearCycle.valueOf(fixedWeekdayRelativeToFixed.getEvery().name());
  }
}
