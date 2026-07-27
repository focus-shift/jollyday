package de.focus_shift.jollyday.jackson;

import de.focus_shift.jollyday.core.HolidayType;
import de.focus_shift.jollyday.core.spi.HebrewHolidayConfiguration;
import de.focus_shift.jollyday.jackson.mapping.HebrewHoliday;
import org.jspecify.annotations.NonNull;

import java.time.Year;
import java.util.Optional;

/**
 * see {@link HebrewHolidayConfiguration}
 */
class JacksonHebrewHoliday implements HebrewHolidayConfiguration {

  private final HebrewHoliday hebrewHoliday;

  JacksonHebrewHoliday(HebrewHoliday hebrewHoliday) {
    this.hebrewHoliday = hebrewHoliday;
  }

  @Override
  public @NonNull String descriptionPropertiesKey() {
    return hebrewHoliday.getDescriptionPropertiesKey() == null
      ? descriptionPropertiesKeyPrefix() + descriptionPropertiesKeyPrefixSeparator() + type()
      : hebrewHoliday.getDescriptionPropertiesKey();
  }

  @Override
  public @NonNull HolidayType holidayType() {
    return hebrewHoliday.getLocalizedType() == null
      ? HolidayType.PUBLIC_HOLIDAY
      : HolidayType.valueOf(hebrewHoliday.getLocalizedType().name());
  }

  @Override
  public @NonNull HebrewHolidayType type() {
    return HebrewHolidayType.valueOf(hebrewHoliday.getType().name());
  }

  @Override
  public @NonNull Optional<Year> validFrom() {
    return hebrewHoliday.getValidFrom() == null
      ? Optional.empty()
      : Optional.of(Year.of(hebrewHoliday.getValidFrom()));
  }

  @Override
  public @NonNull Optional<Year> validTo() {
    return hebrewHoliday.getValidTo() == null
      ? Optional.empty()
      : Optional.of(Year.of(hebrewHoliday.getValidTo()));
  }

  @Override
  public @NonNull YearCycle cycle() {
    return hebrewHoliday.getEvery() == null
      ? YearCycle.EVERY_YEAR
      : YearCycle.valueOf(hebrewHoliday.getEvery().name());
  }
}
