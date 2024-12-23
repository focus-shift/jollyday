package de.focus_shift.jollyday.jackson;

import de.focus_shift.jollyday.core.HolidayType;
import de.focus_shift.jollyday.core.spi.IslamicHoliday;
import de.focus_shift.jollyday.core.spi.MovingCondition;

import java.time.Year;
import java.util.List;

import static java.util.stream.Collectors.toList;

/**
 * {@inheritDoc}
 */
class JacksonIslamicHoliday implements IslamicHoliday {

  private final de.focus_shift.jollyday.jackson.mapping.IslamicHoliday islamicHoliday;

  JacksonIslamicHoliday(de.focus_shift.jollyday.jackson.mapping.IslamicHoliday christianHoliday) {
    this.islamicHoliday = christianHoliday;
  }

  /**
   * {@inheritDoc}
   *
   * @return {@inheritDoc}
   */
  @Override
  public IslamicHolidayType type() {
    return IslamicHolidayType.valueOf(islamicHoliday.getType().name());
  }

  /**
   * {@inheritDoc}
   *
   * @return {@inheritDoc}
   */
  @Override
  public String descriptionPropertiesKey() {
    return islamicHoliday.getDescriptionPropertiesKey();
  }

  /**
   * {@inheritDoc}
   *
   * @return {@inheritDoc}
   */
  @Override
  public HolidayType officiality() {
    return islamicHoliday.getLocalizedType() == null
      ? HolidayType.PUBLIC_HOLIDAY
      : HolidayType.valueOf(islamicHoliday.getLocalizedType().name());
  }

  /**
   * {@inheritDoc}
   *
   * @return {@inheritDoc}
   */
  @Override
  public Year validFrom() {
    return islamicHoliday.getValidFrom() == null
      ? null
      : Year.of(islamicHoliday.getValidFrom());
  }

  /**
   * {@inheritDoc}
   *
   * @return {@inheritDoc}
   */
  @Override
  public Year validTo() {
    return islamicHoliday.getValidTo() == null
      ? null
      : Year.of(islamicHoliday.getValidTo());
  }

  /**
   * {@inheritDoc}
   *
   * @return {@inheritDoc}
   */
  @Override
  public YearCycle cycle() {
    return islamicHoliday.getEvery() == null
      ? YearCycle.EVERY_YEAR
      : YearCycle.valueOf(islamicHoliday.getEvery().name());
  }

  /**
   * {@inheritDoc}
   *
   * @return {@inheritDoc}
   */
  @Override
  public List<MovingCondition> conditions() {
    return islamicHoliday.getMovingCondition().stream()
      .map(JacksonMovingCondition::new)
      .collect(toList());
  }
}
