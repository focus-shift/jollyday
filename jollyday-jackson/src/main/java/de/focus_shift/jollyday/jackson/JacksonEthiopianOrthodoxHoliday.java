package de.focus_shift.jollyday.jackson;

import de.focus_shift.jollyday.core.HolidayType;
import de.focus_shift.jollyday.core.spi.EthiopianOrthodoxHolidayConfiguration;
import de.focus_shift.jollyday.jackson.mapping.EthiopianOrthodoxHoliday;

import java.time.Year;

/**
 * see {@link EthiopianOrthodoxHolidayConfiguration}
 */
class JacksonEthiopianOrthodoxHoliday implements EthiopianOrthodoxHolidayConfiguration {

  private final EthiopianOrthodoxHoliday ethiopianOrthodoxHoliday;

  JacksonEthiopianOrthodoxHoliday(EthiopianOrthodoxHoliday ethiopianOrthodoxHoliday) {
    this.ethiopianOrthodoxHoliday = ethiopianOrthodoxHoliday;
  }

  /**
   * {@inheritDoc}
   *
   * @return {@inheritDoc}
   */
  @Override
  public String descriptionPropertiesKey() {
    return ethiopianOrthodoxHoliday.getDescriptionPropertiesKey() == null
      ? descriptionPropertiesKeyPrefix() + descriptionPropertiesKeyPrefixSeparator() + type()
      : ethiopianOrthodoxHoliday.getDescriptionPropertiesKey();
  }

  /**
   * {@inheritDoc}
   *
   * @return {@inheritDoc}
   */
  @Override
  public HolidayType holidayType() {
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
