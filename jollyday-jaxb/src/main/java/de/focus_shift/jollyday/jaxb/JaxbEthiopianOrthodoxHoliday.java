package de.focus_shift.jollyday.jaxb;

import de.focus_shift.jollyday.core.HolidayType;
import de.focus_shift.jollyday.core.spi.EthiopianOrthodoxHolidayConfiguration;
import de.focus_shift.jollyday.jaxb.mapping.EthiopianOrthodoxHoliday;
import java.time.Year;
import org.jspecify.annotations.NonNull;
import org.jspecify.annotations.Nullable;

/** see {@link EthiopianOrthodoxHolidayConfiguration} */
class JaxbEthiopianOrthodoxHoliday implements EthiopianOrthodoxHolidayConfiguration {

  private final EthiopianOrthodoxHoliday ethiopianOrthodoxHoliday;

  JaxbEthiopianOrthodoxHoliday(EthiopianOrthodoxHoliday ethiopianOrthodoxHoliday) {
    this.ethiopianOrthodoxHoliday = ethiopianOrthodoxHoliday;
  }

  /**
   * {@inheritDoc}
   *
   * @return {@inheritDoc}
   */
  @Override
  public @NonNull String descriptionPropertiesKey() {
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
  public @NonNull HolidayType holidayType() {
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
  public @NonNull EthiopianOrthodoxHolidayType type() {
    return EthiopianOrthodoxHolidayType.valueOf(ethiopianOrthodoxHoliday.getType().name());
  }

  /**
   * {@inheritDoc}
   *
   * @return {@inheritDoc}
   */
  @Override
  public @Nullable Year validFrom() {
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
  public @Nullable Year validTo() {
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
  public @NonNull YearCycle cycle() {
    return ethiopianOrthodoxHoliday.getEvery() == null
        ? YearCycle.EVERY_YEAR
        : YearCycle.valueOf(ethiopianOrthodoxHoliday.getEvery().name());
  }
}
