package de.focus_shift.jollyday.jaxb;

import de.focus_shift.jollyday.core.HolidayType;
import de.focus_shift.jollyday.core.spi.RelativeToEasterSundayHolidayConfiguration;
import de.focus_shift.jollyday.jaxb.mapping.ChronologyType;
import de.focus_shift.jollyday.jaxb.mapping.RelativeToEasterSunday;
import org.jspecify.annotations.NonNull;
import org.threeten.extra.Days;
import org.threeten.extra.chrono.JulianChronology;

import java.time.Year;
import java.time.chrono.Chronology;
import java.time.chrono.IsoChronology;
import java.util.Optional;

/**
 * see {@link RelativeToEasterSundayHolidayConfiguration}
 */
class JaxbRelativeToEasterSunday implements RelativeToEasterSundayHolidayConfiguration {

  private final RelativeToEasterSunday relativeToEasterSunday;

  JaxbRelativeToEasterSunday(RelativeToEasterSunday relativeToEasterSunday) {
    this.relativeToEasterSunday = relativeToEasterSunday;
  }

  /**
   * {@inheritDoc}
   *
   * @return {@inheritDoc}
   */
  @Override
  public @NonNull String descriptionPropertiesKey() {
    return relativeToEasterSunday.getDescriptionPropertiesKey();
  }

  /**
   * {@inheritDoc}
   *
   * @return {@inheritDoc}
   */
  @Override
  public @NonNull HolidayType holidayType() {
    return relativeToEasterSunday.getLocalizedType() == null
      ? HolidayType.PUBLIC_HOLIDAY
      : HolidayType.valueOf(relativeToEasterSunday.getLocalizedType().name());
  }

  /**
   * {@inheritDoc}
   *
   * @return {@inheritDoc}
   */
  @Override
  public @NonNull Optional<Year> validFrom() {
    return relativeToEasterSunday.getValidFrom() == null
      ? Optional.empty()
      : Optional.of(Year.of(relativeToEasterSunday.getValidFrom()));
  }

  /**
   * {@inheritDoc}
   *
   * @return {@inheritDoc}
   */
  @Override
  public @NonNull Optional<Year> validTo() {
    return relativeToEasterSunday.getValidTo() == null
      ? Optional.empty()
      : Optional.of(Year.of(relativeToEasterSunday.getValidTo()));
  }

  /**
   * {@inheritDoc}
   *
   * @return {@inheritDoc}
   */
  @Override
  public @NonNull YearCycle cycle() {
    return relativeToEasterSunday.getEvery() == null
      ? YearCycle.EVERY_YEAR
      : YearCycle.valueOf(relativeToEasterSunday.getEvery().name());
  }

  /**
   * {@inheritDoc}
   *
   * @return {@inheritDoc}
   */
  @Override
  public @NonNull Chronology chronology() {
    return relativeToEasterSunday.getChronology() == ChronologyType.JULIAN ? JulianChronology.INSTANCE : IsoChronology.INSTANCE;
  }

  /**
   * {@inheritDoc}
   *
   * @return {@inheritDoc}
   */
  @Override
  public @NonNull Days days() {
    return Days.of(relativeToEasterSunday.getDays());
  }
}
