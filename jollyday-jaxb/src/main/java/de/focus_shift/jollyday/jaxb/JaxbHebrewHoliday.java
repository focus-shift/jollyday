package de.focus_shift.jollyday.jaxb;

import de.focus_shift.jollyday.core.HolidayType;
import de.focus_shift.jollyday.core.spi.HebrewHolidayConfiguration;
import de.focus_shift.jollyday.jaxb.mapping.HebrewHoliday;
import org.jspecify.annotations.NonNull;

import java.time.Year;
import java.util.Optional;

/**
 * see {@link HebrewHolidayConfiguration}
 */
class JaxbHebrewHoliday implements HebrewHolidayConfiguration {

  private final HebrewHoliday hebrewHoliday;

  JaxbHebrewHoliday(HebrewHoliday hebrewHoliday) {
    this.hebrewHoliday = hebrewHoliday;
  }

  /**
   * {@inheritDoc}
   *
   * @return {@inheritDoc}
   */
  @Override
  public @NonNull String descriptionPropertiesKey() {
    return hebrewHoliday.getDescriptionPropertiesKey() == null
      ? descriptionPropertiesKeyPrefix() + descriptionPropertiesKeyPrefixSeparator() + type()
      : hebrewHoliday.getDescriptionPropertiesKey();
  }

  /**
   * {@inheritDoc}
   *
   * @return {@inheritDoc}
   */
  @Override
  public @NonNull HolidayType holidayType() {
    return hebrewHoliday.getLocalizedType() == null
      ? HolidayType.PUBLIC_HOLIDAY
      : HolidayType.valueOf(hebrewHoliday.getLocalizedType().name());
  }

  /**
   * {@inheritDoc}
   *
   * @return {@inheritDoc}
   */
  @Override
  public @NonNull HebrewHolidayType type() {
    return HebrewHolidayType.valueOf(hebrewHoliday.getType().name());
  }

  /**
   * {@inheritDoc}
   *
   * @return {@inheritDoc}
   */
  @Override
  public @NonNull Optional<Year> validFrom() {
    return hebrewHoliday.getValidFrom() == null
      ? Optional.empty()
      : Optional.of(Year.of(hebrewHoliday.getValidFrom()));
  }

  /**
   * {@inheritDoc}
   *
   * @return {@inheritDoc}
   */
  @Override
  public @NonNull Optional<Year> validTo() {
    return hebrewHoliday.getValidTo() == null
      ? Optional.empty()
      : Optional.of(Year.of(hebrewHoliday.getValidTo()));
  }

  /**
   * {@inheritDoc}
   *
   * @return {@inheritDoc}
   */
  @Override
  public @NonNull YearCycle cycle() {
    return hebrewHoliday.getEvery() == null
      ? YearCycle.EVERY_YEAR
      : YearCycle.valueOf(hebrewHoliday.getEvery().name());
  }
}
