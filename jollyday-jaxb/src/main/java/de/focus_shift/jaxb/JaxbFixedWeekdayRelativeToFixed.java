package de.focus_shift.jaxb;

import de.focus_shift.HolidayType;
import de.focus_shift.spi.Fixed;
import de.focus_shift.spi.FixedWeekdayRelativeToFixed;
import de.focus_shift.spi.Occurrance;
import de.focus_shift.spi.Relation;
import de.focus_shift.spi.YearCycle;

import java.time.DayOfWeek;
import java.time.Year;


public class JaxbFixedWeekdayRelativeToFixed implements FixedWeekdayRelativeToFixed {

  private final de.focus_shift.jaxb.mapping.FixedWeekdayRelativeToFixed fixedWeekdayRelativeToFixed;

  public JaxbFixedWeekdayRelativeToFixed(de.focus_shift.jaxb.mapping.FixedWeekdayRelativeToFixed fixedWeekdayRelativeToFixed) {
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
    return new JaxbFixed(fixedWeekdayRelativeToFixed.getDay());
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
      ? HolidayType.OFFICIAL_HOLIDAY
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
