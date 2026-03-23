package de.focus_shift.jollyday.jackson;

import de.focus_shift.jollyday.core.HolidayType;
import de.focus_shift.jollyday.core.spi.ChristianHolidayConfiguration;
import de.focus_shift.jollyday.jackson.mapping.ChristianHoliday;
import de.focus_shift.jollyday.jackson.mapping.ChronologyType;
import org.jspecify.annotations.NonNull;
import org.threeten.extra.chrono.JulianChronology;

import java.time.Year;
import java.time.chrono.Chronology;
import java.time.chrono.IsoChronology;
import java.util.List;
import java.util.Optional;

/**
 * see {@link ChristianHolidayConfiguration}
 */
class JacksonChristianHoliday implements ChristianHolidayConfiguration {

  private final ChristianHoliday christianHoliday;

  JacksonChristianHoliday(ChristianHoliday christianHoliday) {
    this.christianHoliday = christianHoliday;
  }

  /**
   * {@inheritDoc}
   *
   * @return {@inheritDoc}
   */
  @Override
  public @NonNull ChristianHolidayType type() {
    return ChristianHolidayType.valueOf(christianHoliday.getType().name());
  }

  /**
   * {@inheritDoc}
   *
   * @return {@inheritDoc}
   */
  @Override
  public @NonNull Chronology chronology() {
    return christianHoliday.getChronology() == ChronologyType.JULIAN
      ? JulianChronology.INSTANCE
      : IsoChronology.INSTANCE;
  }

  /**
   * {@inheritDoc}
   *
   * @return {@inheritDoc}
   */
  @Override
  public @NonNull String descriptionPropertiesKey() {
    return christianHoliday.getDescriptionPropertiesKey() == null
      ? descriptionPropertiesKeyPrefix() + descriptionPropertiesKeyPrefixSeparator() + type()
      : christianHoliday.getDescriptionPropertiesKey();
  }

  /**
   * {@inheritDoc}
   *
   * @return {@inheritDoc}
   */
  @Override
  public @NonNull HolidayType holidayType() {
    return christianHoliday.getLocalizedType() == null
      ? HolidayType.PUBLIC_HOLIDAY
      : HolidayType.valueOf(christianHoliday.getLocalizedType().name());
  }

  /**
   * {@inheritDoc}
   *
   * @return {@inheritDoc}
   */
  @Override
  public @NonNull Optional<Year> validFrom() {
    return christianHoliday.getValidFrom() == null
      ? Optional.empty()
      : Optional.of(Year.of(christianHoliday.getValidFrom()));
  }

  /**
   * {@inheritDoc}
   *
   * @return {@inheritDoc}
   */
  @Override
  public @NonNull Optional<Year> validTo() {
    return christianHoliday.getValidTo() == null
      ? Optional.empty()
      : Optional.of(Year.of(christianHoliday.getValidTo()));
  }

  /**
   * {@inheritDoc}
   *
   * @return {@inheritDoc}
   */
  @Override
  public @NonNull YearCycle cycle() {
    return christianHoliday.getEvery() == null
      ? YearCycle.EVERY_YEAR
      : YearCycle.valueOf(christianHoliday.getEvery().name());
  }

  /**
   * {@inheritDoc}
   *
   * @return {@inheritDoc}
   */
  @Override
  public @NonNull List<MovingCondition> conditions() {
    return christianHoliday.getMovingCondition().stream()
      .map(JacksonMovingCondition::new)
      .map(MovingCondition.class::cast)
      .toList();
  }
}
