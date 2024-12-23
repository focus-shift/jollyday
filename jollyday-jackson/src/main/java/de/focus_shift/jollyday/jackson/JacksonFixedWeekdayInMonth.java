package de.focus_shift.jollyday.jackson;

import de.focus_shift.jollyday.core.HolidayType;
import de.focus_shift.jollyday.core.spi.FixedWeekdayInMonth;
import de.focus_shift.jollyday.core.spi.Occurrance;

import java.time.DayOfWeek;
import java.time.Month;
import java.time.Year;

class JacksonFixedWeekdayInMonth implements FixedWeekdayInMonth {

  private final de.focus_shift.jollyday.jackson.mapping.FixedWeekdayInMonth fixedWeekdayInMonth;

  JacksonFixedWeekdayInMonth(de.focus_shift.jollyday.jackson.mapping.FixedWeekdayInMonth fixedWeekdayInMonth) {
    this.fixedWeekdayInMonth = fixedWeekdayInMonth;
  }

  /**
   * {@inheritDoc}
   *
   * @return {@inheritDoc}
   */
  @Override
  public DayOfWeek weekday() {
    return DayOfWeek.valueOf(fixedWeekdayInMonth.getWeekday().name());
  }

  /**
   * {@inheritDoc}
   *
   * @return {@inheritDoc}
   */
  @Override
  public Month month() {
    return Month.valueOf(fixedWeekdayInMonth.getMonth().name());
  }

  /**
   * {@inheritDoc}
   *
   * @return {@inheritDoc}
   */
  @Override
  public Occurrance which() {
    return Occurrance.valueOf(fixedWeekdayInMonth.getWhich().name());
  }

  /**
   * {@inheritDoc}
   *
   * @return {@inheritDoc}
   */
  @Override
  public String descriptionPropertiesKey() {
    return fixedWeekdayInMonth.getDescriptionPropertiesKey();
  }

  /**
   * {@inheritDoc}
   *
   * @return {@inheritDoc}
   */
  @Override
  public HolidayType officiality() {
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
  public Year validFrom() {
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
  public Year validTo() {
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
  public YearCycle cycle() {
    return fixedWeekdayInMonth.getEvery() == null
      ? YearCycle.EVERY_YEAR
      : YearCycle.valueOf(fixedWeekdayInMonth.getEvery().name());
  }
}
