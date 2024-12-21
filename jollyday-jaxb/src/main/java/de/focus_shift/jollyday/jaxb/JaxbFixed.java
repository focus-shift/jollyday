package de.focus_shift.jollyday.jaxb;

import de.focus_shift.jollyday.core.HolidayType;
import de.focus_shift.jollyday.core.spi.Fixed;
import de.focus_shift.jollyday.core.spi.MovingCondition;
import de.focus_shift.jollyday.core.spi.YearCycle;

import java.time.MonthDay;
import java.time.Year;
import java.util.List;

import static java.util.stream.Collectors.toList;

/**
 * {@inheritDoc}
 */
class JaxbFixed implements Fixed {

  private final XMLUtil xmlUtil = new XMLUtil();

  private final de.focus_shift.jollyday.jaxb.mapping.Fixed fixed;

  JaxbFixed(de.focus_shift.jollyday.jaxb.mapping.Fixed fixed) {
    this.fixed = fixed;
  }

  /**
   * {@inheritDoc}
   *
   * @return {@inheritDoc}
   */
  @Override
  public MonthDay day() {
    return MonthDay.of(xmlUtil.getMonth(fixed.getMonth()), fixed.getDay());
  }

  /**
   * {@inheritDoc}
   *
   * @return {@inheritDoc}
   */
  @Override
  public String descriptionPropertiesKey() {
    return fixed.getDescriptionPropertiesKey();
  }

  /**
   * {@inheritDoc}
   *
   * @return {@inheritDoc}
   */
  @Override
  public HolidayType officiality() {
    return fixed.getLocalizedType() == null
      ? HolidayType.PUBLIC_HOLIDAY
      : HolidayType.valueOf(fixed.getLocalizedType().name());
  }

  /**
   * {@inheritDoc}
   *
   * @return {@inheritDoc}
   */
  @Override
  public Year validFrom() {
    return fixed.getValidFrom() == null
      ? null
      : Year.of(fixed.getValidFrom());
  }

  /**
   * {@inheritDoc}
   *
   * @return {@inheritDoc}
   */
  @Override
  public Year validTo() {
    return fixed.getValidTo() == null
      ? null
      : Year.of(fixed.getValidTo());
  }
  /**
   * {@inheritDoc}
   *
   * @return {@inheritDoc}
   */

  @Override
  public YearCycle cycle() {
    return fixed.getEvery() == null
      ? YearCycle.EVERY_YEAR
      : YearCycle.valueOf(fixed.getEvery().name());
  }

  /**
   * {@inheritDoc}
   *
   * @return {@inheritDoc}
   */
  @Override
  public List<MovingCondition> conditions() {
    return fixed.getMovingCondition().stream()
      .map(JaxbMovingCondition::new)
      .collect(toList());
  }
}
