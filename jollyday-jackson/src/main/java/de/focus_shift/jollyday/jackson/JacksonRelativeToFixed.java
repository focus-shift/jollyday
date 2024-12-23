package de.focus_shift.jollyday.jackson;

import de.focus_shift.jollyday.core.HolidayType;
import de.focus_shift.jollyday.core.spi.Fixed;
import de.focus_shift.jollyday.core.spi.Relation;
import de.focus_shift.jollyday.core.spi.RelativeToFixed;
import org.threeten.extra.Days;

import java.time.DayOfWeek;
import java.time.Year;

class JacksonRelativeToFixed implements RelativeToFixed {

  private final de.focus_shift.jollyday.jackson.mapping.RelativeToFixed relativeToFixed;

  JacksonRelativeToFixed(de.focus_shift.jollyday.jackson.mapping.RelativeToFixed relativeToFixed) {
    this.relativeToFixed = relativeToFixed;
  }

  /**
   * {@inheritDoc}
   *
   * @return {@inheritDoc}
   */
  @Override
  public Fixed date() {
    return new JacksonFixed(relativeToFixed.getDate());
  }

  /**
   * {@inheritDoc}
   *
   * @return {@inheritDoc}
   */
  @Override
  public DayOfWeek weekday() {
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
  public Relation when() {
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
  public Days days() {
    return relativeToFixed.getDays() == null
      ? null
      : Days.of(relativeToFixed.getDays());
  }

  /**
   * {@inheritDoc}
   *
   * @return {@inheritDoc}
   */
  @Override
  public String descriptionPropertiesKey() {
    return relativeToFixed.getDescriptionPropertiesKey();
  }

  /**
   * {@inheritDoc}
   *
   * @return {@inheritDoc}
   */
  @Override
  public HolidayType officiality() {
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
  public Year validFrom() {
    return relativeToFixed.getValidFrom() == null
      ? null
      : Year.of(relativeToFixed.getValidFrom());
  }

  /**
   * {@inheritDoc}
   *
   * @return {@inheritDoc}
   */
  @Override
  public Year validTo() {
    return relativeToFixed.getValidTo() == null
      ? null
      : Year.of(relativeToFixed.getValidTo());
  }

  /**
   * {@inheritDoc}
   *
   * @return {@inheritDoc}
   */
  @Override
  public YearCycle cycle() {
    return relativeToFixed.getEvery() == null
      ? YearCycle.EVERY_YEAR
      : YearCycle.valueOf(relativeToFixed.getEvery().name());
  }
}
