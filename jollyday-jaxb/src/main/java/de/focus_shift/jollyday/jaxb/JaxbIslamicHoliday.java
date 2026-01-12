package de.focus_shift.jollyday.jaxb;

import de.focus_shift.jollyday.core.HolidayType;
import de.focus_shift.jollyday.core.spi.IslamicHoliday;

import java.time.Year;
import java.util.List;

import static java.util.stream.Collectors.toList;

/**
 * see {@link IslamicHoliday}
 */
class JaxbIslamicHoliday implements IslamicHoliday {

  private final de.focus_shift.jollyday.jaxb.mapping.IslamicHoliday islamicHoliday;

  JaxbIslamicHoliday(de.focus_shift.jollyday.jaxb.mapping.IslamicHoliday christianHoliday) {
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
    return islamicHoliday.getDescriptionPropertiesKey() == null
      ? descriptionPropertiesKeyPrefix() + descriptionPropertiesKeyPrefixSeparator() + type()
      : islamicHoliday.getDescriptionPropertiesKey();
  }

  /**
   * {@inheritDoc}
   *
   * @return {@inheritDoc}
   */
  @Override
  public HolidayType holidayType() {
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
      .map(JaxbMovingCondition::new)
      .collect(toList());
  }
}
