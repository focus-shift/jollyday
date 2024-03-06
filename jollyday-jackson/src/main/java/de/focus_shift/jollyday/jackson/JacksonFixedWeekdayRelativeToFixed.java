package de.focus_shift.jollyday.jackson;

import de.focus_shift.jollyday.core.HolidayType;
import de.focus_shift.jollyday.core.spi.Fixed;
import de.focus_shift.jollyday.core.spi.FixedWeekdayRelativeToFixed;
import de.focus_shift.jollyday.core.spi.Occurrance;
import de.focus_shift.jollyday.core.spi.Relation;
import de.focus_shift.jollyday.core.spi.YearCycle;

import java.time.DayOfWeek;
import java.time.Year;


public class JacksonFixedWeekdayRelativeToFixed implements FixedWeekdayRelativeToFixed {

  private final de.focus_shift.jollyday.jackson.mapping.FixedWeekdayRelativeToFixed fixedWeekdayRelativeToFixed;

  public JacksonFixedWeekdayRelativeToFixed(de.focus_shift.jollyday.jackson.mapping.FixedWeekdayRelativeToFixed fixedWeekdayRelativeToFixed) {
    this.fixedWeekdayRelativeToFixed = fixedWeekdayRelativeToFixed;
  }

  @Override
  public DayOfWeek weekday() {
    return DayOfWeek.valueOf(fixedWeekdayRelativeToFixed.getWeekday().name());
  }

  @Override
  public Relation when() {
    return Relation.valueOf(fixedWeekdayRelativeToFixed.getWhen().name());
  }

  @Override
  public Fixed day() {
    return new JacksonFixed(fixedWeekdayRelativeToFixed.getDay());
  }

  @Override
  public Occurrance which() {
    return Occurrance.valueOf(fixedWeekdayRelativeToFixed.getWhich().name());
  }

  @Override
  public String descriptionPropertiesKey() {
    return fixedWeekdayRelativeToFixed.getDescriptionPropertiesKey();
  }

  @Override
  public HolidayType officiality() {
    return fixedWeekdayRelativeToFixed.getLocalizedType() == null
      ? HolidayType.PUBLIC_HOLIDAY
      : HolidayType.valueOf(fixedWeekdayRelativeToFixed.getLocalizedType().name());
  }

  @Override
  public Year validFrom() {
    return fixedWeekdayRelativeToFixed.getValidFrom() == null
      ? null
      : Year.of(fixedWeekdayRelativeToFixed.getValidFrom());
  }

  @Override
  public Year validTo() {
    return fixedWeekdayRelativeToFixed.getValidTo() == null
      ? null
      : Year.of(fixedWeekdayRelativeToFixed.getValidTo());
  }

  @Override
  public YearCycle cycle() {
    return fixedWeekdayRelativeToFixed.getEvery() == null
      ? YearCycle.EVERY_YEAR
      : YearCycle.valueOf(fixedWeekdayRelativeToFixed.getEvery().name());
  }
}
