package de.focus_shift.jollyday.jaxb;

import de.focus_shift.jollyday.core.HolidayType;
import de.focus_shift.jollyday.core.spi.EthiopianOrthodoxHoliday;
import de.focus_shift.jollyday.core.spi.EthiopianOrthodoxHolidayType;
import de.focus_shift.jollyday.core.spi.YearCycle;

import java.time.Year;

/**
 * {@inheritDoc}
 */
class JaxbEthiopianOrthodoxHoliday implements EthiopianOrthodoxHoliday {

  private final de.focus_shift.jollyday.jaxb.mapping.EthiopianOrthodoxHoliday ethiopianOrthodoxHoliday;

  JaxbEthiopianOrthodoxHoliday(de.focus_shift.jollyday.jaxb.mapping.EthiopianOrthodoxHoliday ethiopianOrthodoxHoliday) {
    this.ethiopianOrthodoxHoliday = ethiopianOrthodoxHoliday;
  }

  /**
   * {@inheritDoc}
   *
   * @return {@inheritDoc}
   */
  @Override
  public String descriptionPropertiesKey() {
    return ethiopianOrthodoxHoliday.getDescriptionPropertiesKey();
  }

  /**
   * {@inheritDoc}
   *
   * @return {@inheritDoc}
   */
  @Override
  public HolidayType officiality() {
    return ethiopianOrthodoxHoliday.getLocalizedType() == null
      ? HolidayType.PUBLIC_HOLIDAY
      : HolidayType.valueOf(ethiopianOrthodoxHoliday.getLocalizedType().name());
  }

  /**
   * {@inheritDoc}
   *
   * @return {@inheritDoc}
   */
  @Override
  public EthiopianOrthodoxHolidayType type() {
    return EthiopianOrthodoxHolidayType.valueOf(ethiopianOrthodoxHoliday.getType().name());
  }

  /**
   * {@inheritDoc}
   *
   * @return {@inheritDoc}
   */
  @Override
  public Year validFrom() {
    return ethiopianOrthodoxHoliday.getValidFrom() == null
      ? null
      : Year.of(ethiopianOrthodoxHoliday.getValidFrom());
  }

  /**
   * {@inheritDoc}
   *
   * @return {@inheritDoc}
   */
  @Override
  public Year validTo() {
    return ethiopianOrthodoxHoliday.getValidTo() == null
      ? null
      : Year.of(ethiopianOrthodoxHoliday.getValidTo());
  }

  /**
   * {@inheritDoc}
   *
   * @return {@inheritDoc}
   */
  @Override
  public YearCycle cycle() {
    return ethiopianOrthodoxHoliday.getEvery() == null
      ? YearCycle.EVERY_YEAR
      : YearCycle.valueOf(ethiopianOrthodoxHoliday.getEvery().name());
  }
}
