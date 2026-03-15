package de.focus_shift.jollyday.jackson;

import de.focus_shift.jollyday.core.HolidayType;
import de.focus_shift.jollyday.core.spi.FixedHolidayConfiguration;
import de.focus_shift.jollyday.core.spi.Relation;
import de.focus_shift.jollyday.core.spi.RelativeToFixedHolidayConfiguration;
import de.focus_shift.jollyday.jackson.mapping.RelativeToFixed;
import java.time.DayOfWeek;
import java.time.Year;
import org.jspecify.annotations.NonNull;
import org.jspecify.annotations.Nullable;
import org.threeten.extra.Days;

/** see {@link RelativeToFixedHolidayConfiguration} */
class JacksonRelativeToFixed implements RelativeToFixedHolidayConfiguration {

  private final RelativeToFixed relativeToFixed;

  JacksonRelativeToFixed(RelativeToFixed relativeToFixed) {
    this.relativeToFixed = relativeToFixed;
  }

  /**
   * {@inheritDoc}
   *
   * @return {@inheritDoc}
   */
  @Override
  public @NonNull FixedHolidayConfiguration date() {
    return new JacksonFixed(relativeToFixed.getDate());
  }

  /**
   * {@inheritDoc}
   *
   * @return {@inheritDoc}
   */
  @Override
  public @Nullable DayOfWeek weekday() {
    return relativeToFixed.getWeekday() == null
        ? null
        : DayOfWeek.valueOf(relativeToFixed.getWeekday().name());
  }

  /**
   * {@inheritDoc}
   *
   * @return {@inheritDoc}
   */
  @Override
  public @Nullable Relation when() {
    return relativeToFixed.getWhen() == null
        ? null
        : Relation.valueOf(relativeToFixed.getWhen().name());
  }

  /**
   * {@inheritDoc}
   *
   * @return {@inheritDoc}
   */
  @Override
  public @Nullable Days days() {
    return relativeToFixed.getDays() == null ? null : Days.of(relativeToFixed.getDays());
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
  public @Nullable Year validFrom() {
    return relativeToFixed.getValidFrom() == null ? null : Year.of(relativeToFixed.getValidFrom());
  }

  /**
   * {@inheritDoc}
   *
   * @return {@inheritDoc}
   */
  @Override
  public @Nullable Year validTo() {
    return relativeToFixed.getValidTo() == null ? null : Year.of(relativeToFixed.getValidTo());
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
