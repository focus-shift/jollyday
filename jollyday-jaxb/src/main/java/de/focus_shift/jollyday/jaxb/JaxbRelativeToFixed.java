package de.focus_shift.jollyday.jaxb;

import de.focus_shift.jollyday.core.HolidayType;
import de.focus_shift.jollyday.core.spi.FixedHolidayConfiguration;
import de.focus_shift.jollyday.core.spi.Relation;
import de.focus_shift.jollyday.core.spi.RelativeToFixedHolidayConfiguration;
import de.focus_shift.jollyday.jaxb.mapping.RelativeToFixed;
import org.jspecify.annotations.NonNull;
import org.threeten.extra.Days;

import java.time.DayOfWeek;
import java.time.Year;
import java.util.Optional;

/**
 * see {@link RelativeToFixedHolidayConfiguration}
 */
class JaxbRelativeToFixed implements RelativeToFixedHolidayConfiguration {

  private final RelativeToFixed relativeToFixed;

  JaxbRelativeToFixed(RelativeToFixed relativeToFixed) {
    this.relativeToFixed = relativeToFixed;
  }

  /**
   * {@inheritDoc}
   *
   * @return {@inheritDoc}
   */
  @Override
  public @NonNull FixedHolidayConfiguration date() {
    return new JaxbFixed(relativeToFixed.getDate());
  }

  /**
   * {@inheritDoc}
   *
   * @return {@inheritDoc}
   */
  @Override
  public @NonNull Optional<DayOfWeek> weekday() {
    return relativeToFixed.getWeekday() == null
      ? Optional.empty()
      : Optional.of(DayOfWeek.valueOf(relativeToFixed.getWeekday().name()));
  }

  /**
   * {@inheritDoc}
   *
   * @return {@inheritDoc}
   */
  @Override
  public @NonNull Optional<Relation> when() {
    return relativeToFixed.getWhen() == null
      ? Optional.empty()
      : Optional.of(Relation.valueOf(relativeToFixed.getWhen().name()));
  }

  /**
   * {@inheritDoc}
   *
   * @return {@inheritDoc}
   */
  @Override
  public @NonNull Optional<Days> days() {
    return relativeToFixed.getDays() == null
      ? Optional.empty()
      : Optional.of(Days.of(relativeToFixed.getDays()));
  }

  /**
   * {@inheritDoc}
   *
   * @return {@inheritDoc}
   */
  @Override
  public @NonNull String descriptionPropertiesKey() {
    return relativeToFixed.getDescriptionPropertiesKey();
  }

  /**
   * {@inheritDoc}
   *
   * @return {@inheritDoc}
   */
  @Override
  public @NonNull HolidayType holidayType() {
    return relativeToFixed.getLocalizedType() == null
      ? HolidayType.PUBLIC_HOLIDAY
      : HolidayType.valueOf(relativeToFixed.getLocalizedType().name());
  }

  /**
   * {@inheritDoc}
   *
   * @return {@inheritDoc}
   */
  @Override
  public @NonNull Optional<Year> validFrom() {
    return relativeToFixed.getValidFrom() == null
      ? Optional.empty()
      : Optional.of(Year.of(relativeToFixed.getValidFrom()));
  }

  /**
   * {@inheritDoc}
   *
   * @return {@inheritDoc}
   */
  @Override
  public @NonNull Optional<Year> validTo() {
    return relativeToFixed.getValidTo() == null
      ? Optional.empty()
      : Optional.of(Year.of(relativeToFixed.getValidTo()));
  }

  /**
   * {@inheritDoc}
   *
   * @return {@inheritDoc}
   */
  @Override
  public @NonNull YearCycle cycle() {
    return relativeToFixed.getEvery() == null
      ? YearCycle.EVERY_YEAR
      : YearCycle.valueOf(relativeToFixed.getEvery().name());
  }
}
