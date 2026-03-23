package de.focus_shift.jollyday.jaxb;

import de.focus_shift.jollyday.core.HolidayType;
import de.focus_shift.jollyday.core.spi.IslamicHolidayConfiguration;
import de.focus_shift.jollyday.jaxb.mapping.IslamicHoliday;
import org.jspecify.annotations.NonNull;

import java.time.Year;
import java.util.List;
import java.util.Optional;

/**
 * see {@link IslamicHolidayConfiguration}
 */
class JaxbIslamicHoliday implements IslamicHolidayConfiguration {

  private final IslamicHoliday islamicHoliday;

  JaxbIslamicHoliday(IslamicHoliday christianHoliday) {
    this.islamicHoliday = christianHoliday;
  }

  /**
   * {@inheritDoc}
   *
   * @return {@inheritDoc}
   */
  @Override
  public @NonNull IslamicHolidayType type() {
    return IslamicHolidayType.valueOf(islamicHoliday.getType().name());
  }

  /**
   * {@inheritDoc}
   *
   * @return {@inheritDoc}
   */
  @Override
  public @NonNull String descriptionPropertiesKey() {
    return islamicHoliday.getDescriptionPropertiesKey() == null
      ? descriptionPropertiesKeyPrefix() + descriptionPropertiesKeyPrefixSeparator() + type()
      : islamicHoliday.getDescriptionPropertiesKey();
  }

  /**
   * {@inheritDoc}
   *
   * @return {@inheritDoc}
   */
  @Override
  public @NonNull HolidayType holidayType() {
    return islamicHoliday.getLocalizedType() == null
      ? HolidayType.PUBLIC_HOLIDAY
      : HolidayType.valueOf(islamicHoliday.getLocalizedType().name());
  }

  /**
   * {@inheritDoc}
   *
   * @return {@inheritDoc}
   */
  @Override
  public @NonNull Optional<Year> validFrom() {
    return islamicHoliday.getValidFrom() == null
      ? Optional.empty()
      : Optional.of(Year.of(islamicHoliday.getValidFrom()));
  }

  /**
   * {@inheritDoc}
   *
   * @return {@inheritDoc}
   */
  @Override
  public @NonNull Optional<Year> validTo() {
    return islamicHoliday.getValidTo() == null
      ? Optional.empty()
      : Optional.of(Year.of(islamicHoliday.getValidTo()));
  }

  /**
   * {@inheritDoc}
   *
   * @return {@inheritDoc}
   */
  @Override
  public @NonNull YearCycle cycle() {
    return islamicHoliday.getEvery() == null
      ? YearCycle.EVERY_YEAR
      : YearCycle.valueOf(islamicHoliday.getEvery().name());
  }

  /**
   * {@inheritDoc}
   *
   * @return {@inheritDoc}
   */
  @Override
  public @NonNull List<MovingCondition> conditions() {
    return islamicHoliday.getMovingCondition().stream()
      .map(JaxbMovingCondition::new)
      .map(MovingCondition.class::cast)
      .toList();
  }
}
