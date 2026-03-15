package de.focus_shift.jollyday.jaxb;

import static de.focus_shift.jollyday.jaxb.mapping.ChronologyType.JULIAN;

import de.focus_shift.jollyday.core.HolidayType;
import de.focus_shift.jollyday.core.spi.ChristianHolidayConfiguration;
import de.focus_shift.jollyday.jaxb.mapping.ChristianHoliday;
import java.time.Year;
import java.time.chrono.Chronology;
import java.time.chrono.IsoChronology;
import java.util.List;
import org.jspecify.annotations.NonNull;
import org.jspecify.annotations.Nullable;
import org.threeten.extra.chrono.JulianChronology;

/** see {@link ChristianHolidayConfiguration} */
class JaxbChristianHoliday implements ChristianHolidayConfiguration {

  private final ChristianHoliday christianHoliday;

  JaxbChristianHoliday(ChristianHoliday christianHoliday) {
    this.christianHoliday = christianHoliday;
  }

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
    return christianHoliday.getChronology() == JULIAN
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
  public @Nullable Year validFrom() {
    return christianHoliday.getValidFrom() == null
        ? null
        : Year.of(christianHoliday.getValidFrom());
  }

  /**
   * {@inheritDoc}
   *
   * @return {@inheritDoc}
   */
  @Override
  public @Nullable Year validTo() {
    return christianHoliday.getValidTo() == null ? null : Year.of(christianHoliday.getValidTo());
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
        .map(JaxbMovingCondition::new)
        .map(MovingCondition.class::cast)
        .toList();
  }
}
