package de.focus_shift.jollyday.jackson;

import de.focus_shift.jollyday.core.HolidayType;
import de.focus_shift.jollyday.core.spi.RelativeToEasterSunday;
import de.focus_shift.jollyday.jackson.mapping.ChronologyType;
import org.threeten.extra.Days;
import org.threeten.extra.chrono.JulianChronology;

import java.time.Year;
import java.time.chrono.Chronology;
import java.time.chrono.IsoChronology;

/**
 * {@inheritDoc}
 */
class JacksonRelativeToEasterSunday implements RelativeToEasterSunday {

  private final de.focus_shift.jollyday.jackson.mapping.RelativeToEasterSunday relativeToEasterSunday;

  JacksonRelativeToEasterSunday(de.focus_shift.jollyday.jackson.mapping.RelativeToEasterSunday relativeToEasterSunday) {
    this.relativeToEasterSunday = relativeToEasterSunday;
  }

  /**
   * {@inheritDoc}
   *
   * @return {@inheritDoc}
   */
  @Override
  public String descriptionPropertiesKey() {
    return relativeToEasterSunday.getDescriptionPropertiesKey();
  }

  /**
   * {@inheritDoc}
   *
   * @return {@inheritDoc}
   */
  @Override
  public HolidayType officiality() {
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
  public Year validFrom() {
    return relativeToEasterSunday.getValidFrom() == null
      ? null
      : Year.of(relativeToEasterSunday.getValidFrom());
  }

  /**
   * {@inheritDoc}
   *
   * @return {@inheritDoc}
   */
  @Override
  public Year validTo() {
    return relativeToEasterSunday.getValidTo() == null
      ? null
      : Year.of(relativeToEasterSunday.getValidTo());
  }

  /**
   * {@inheritDoc}
   *
   * @return {@inheritDoc}
   */
  @Override
  public YearCycle cycle() {
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
  public Chronology chronology() {
    return relativeToEasterSunday.getChronology() == ChronologyType.JULIAN ? JulianChronology.INSTANCE : IsoChronology.INSTANCE;
  }

  /**
   * {@inheritDoc}
   *
   * @return {@inheritDoc}
   */
  @Override
  public Days days() {
    return Days.of(relativeToEasterSunday.getDays());
  }
}
