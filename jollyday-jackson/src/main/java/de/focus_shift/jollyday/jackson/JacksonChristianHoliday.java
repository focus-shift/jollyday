package de.focus_shift.jollyday.jackson;

import de.focus_shift.jollyday.core.HolidayType;
import de.focus_shift.jollyday.core.spi.ChristianHolidayConfiguration;
import de.focus_shift.jollyday.jackson.mapping.ChristianHoliday;
import de.focus_shift.jollyday.jackson.mapping.ChronologyType;
import org.threeten.extra.chrono.JulianChronology;

import java.time.Year;
import java.time.chrono.Chronology;
import java.time.chrono.IsoChronology;
import java.util.List;

import static java.util.stream.Collectors.toList;

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
  public String descriptionPropertiesKey() {
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
  public HolidayType holidayType() {
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
      .map(JacksonMovingCondition::new)
      .collect(toList());
  }
}
