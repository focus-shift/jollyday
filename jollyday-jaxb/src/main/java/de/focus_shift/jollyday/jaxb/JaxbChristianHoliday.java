package de.focus_shift.jollyday.jaxb;

import de.focus_shift.jollyday.core.HolidayType;
import de.focus_shift.jollyday.core.spi.ChristianHoliday;
import de.focus_shift.jollyday.core.spi.MovingCondition;
import org.threeten.extra.chrono.JulianChronology;

import java.time.Year;
import java.time.chrono.Chronology;
import java.time.chrono.IsoChronology;
import java.util.List;

import static de.focus_shift.jollyday.jaxb.mapping.ChronologyType.JULIAN;
import static java.util.stream.Collectors.toList;

/**
 * {@inheritDoc}
 */
class JaxbChristianHoliday implements ChristianHoliday {

  private final de.focus_shift.jollyday.jaxb.mapping.ChristianHoliday christianHoliday;

  JaxbChristianHoliday(de.focus_shift.jollyday.jaxb.mapping.ChristianHoliday christianHoliday) {
    this.christianHoliday = christianHoliday;
  }

  @Override
  public ChristianHolidayType type() {
    return ChristianHolidayType.valueOf(christianHoliday.getType().name());
  }

  /**
   * {@inheritDoc}
   *
   * @return {@inheritDoc}
   */
  @Override
  public Chronology chronology() {
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
  public String descriptionPropertiesKey() {
    return christianHoliday.getDescriptionPropertiesKey();
  }

  /**
   * {@inheritDoc}
   *
   * @return {@inheritDoc}
   */
  @Override
  public HolidayType officiality() {
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
  public Year validFrom() {
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
  public Year validTo() {
    return christianHoliday.getValidTo() == null
      ? null
      : Year.of(christianHoliday.getValidTo());
  }

  /**
   * {@inheritDoc}
   *
   * @return {@inheritDoc}
   */
  @Override
  public YearCycle cycle() {
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
  public List<MovingCondition> conditions() {
    return christianHoliday.getMovingCondition().stream()
      .map(JaxbMovingCondition::new)
      .collect(toList());
  }
}
