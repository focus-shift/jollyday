package de.focus_shift.jollyday.jaxb;

import de.focus_shift.jollyday.core.HolidayType;
import de.focus_shift.jollyday.core.spi.FixedWeekdayInMonth;
import de.focus_shift.jollyday.core.spi.Relation;
import de.focus_shift.jollyday.core.spi.RelativeToWeekdayInMonth;

import java.time.DayOfWeek;
import java.time.Year;

/**
 * see {@link RelativeToWeekdayInMonth}
 */
class JaxbRelativeToWeekdayInMonth implements RelativeToWeekdayInMonth {

  private final de.focus_shift.jollyday.jaxb.mapping.RelativeToWeekdayInMonth relativeToWeekdayInMonth;

  JaxbRelativeToWeekdayInMonth(de.focus_shift.jollyday.jaxb.mapping.RelativeToWeekdayInMonth relativeToWeekdayInMonth) {
    this.relativeToWeekdayInMonth = relativeToWeekdayInMonth;
  }

  /**
   * {@inheritDoc}
   *
   * @return {@inheritDoc}
   */
  @Override
  public FixedWeekdayInMonth weekdayInMonth() {
    return new JaxbFixedWeekdayInMonth(relativeToWeekdayInMonth.getFixedWeekday());
  }

  /**
   * {@inheritDoc}
   *
   * @return {@inheritDoc}
   */
  @Override
  public DayOfWeek weekday() {
    return DayOfWeek.valueOf(relativeToWeekdayInMonth.getWeekday().name());
  }

  /**
   * {@inheritDoc}
   *
   * @return {@inheritDoc}
   */
  @Override
  public Relation when() {
    return Relation.valueOf(relativeToWeekdayInMonth.getWhen().name());
  }

  /**
   * {@inheritDoc}
   *
   * @return {@inheritDoc}
   */
  @Override
  public String descriptionPropertiesKey() {
    return relativeToWeekdayInMonth.getDescriptionPropertiesKey();
  }

  /**
   * {@inheritDoc}
   *
   * @return {@inheritDoc}
   */
  @Override
  public HolidayType holidayType() {
    return relativeToWeekdayInMonth.getLocalizedType() == null
      ? HolidayType.PUBLIC_HOLIDAY
      : HolidayType.valueOf(relativeToWeekdayInMonth.getLocalizedType().name());
  }

  /**
   * {@inheritDoc}
   *
   * @return {@inheritDoc}
   */
  @Override
  public Year validFrom() {
    return relativeToWeekdayInMonth.getValidFrom() == null
      ? null
      : Year.of(relativeToWeekdayInMonth.getValidFrom());
  }

  /**
   * {@inheritDoc}
   *
   * @return {@inheritDoc}
   */
  @Override
  public Year validTo() {
    return relativeToWeekdayInMonth.getValidTo() == null
      ? null
      : Year.of(relativeToWeekdayInMonth.getValidTo());
  }

  /**
   * {@inheritDoc}
   *
   * @return {@inheritDoc}
   */
  @Override
  public YearCycle cycle() {
    return relativeToWeekdayInMonth.getEvery() == null
      ? YearCycle.EVERY_YEAR
      : YearCycle.valueOf(relativeToWeekdayInMonth.getEvery().name());
  }
}
