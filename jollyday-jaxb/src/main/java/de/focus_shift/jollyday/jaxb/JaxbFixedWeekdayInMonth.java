package de.focus_shift.jollyday.jaxb;

import de.focus_shift.jollyday.core.HolidayType;
import de.focus_shift.jollyday.core.spi.FixedWeekdayInMonth;
import de.focus_shift.jollyday.core.spi.Occurrance;
import de.focus_shift.jollyday.core.spi.YearCycle;

import java.time.DayOfWeek;
import java.time.Month;
import java.time.Year;

public class JaxbFixedWeekdayInMonth implements FixedWeekdayInMonth {

  private final de.focus_shift.jollyday.jaxb.mapping.FixedWeekdayInMonth fixedWeekdayInMonth;

  public JaxbFixedWeekdayInMonth(de.focus_shift.jollyday.jaxb.mapping.FixedWeekdayInMonth fixedWeekdayInMonth) {
    this.fixedWeekdayInMonth = fixedWeekdayInMonth;
  }

  @Override
  public DayOfWeek weekday() {
    return DayOfWeek.valueOf(fixedWeekdayInMonth.getWeekday().name());
  }

  @Override
  public Month month() {
    return Month.valueOf(fixedWeekdayInMonth.getMonth().name());
  }

  @Override
  public Occurrance which() {
    return Occurrance.valueOf(fixedWeekdayInMonth.getWhich().name());
  }

  @Override
  public String descriptionPropertiesKey() {
    return fixedWeekdayInMonth.getDescriptionPropertiesKey();
  }

  @Override
  public HolidayType officiality() {
    return fixedWeekdayInMonth.getLocalizedType() == null
      ? HolidayType.OFFICIAL_HOLIDAY
      : HolidayType.valueOf(fixedWeekdayInMonth.getLocalizedType().name());
  }

  @Override
  public Year validFrom() {
    return fixedWeekdayInMonth.getValidFrom() == null
      ? null
      : Year.of(fixedWeekdayInMonth.getValidFrom());
  }

  @Override
  public Year validTo() {
    return fixedWeekdayInMonth.getValidTo() == null
      ? null
      : Year.of(fixedWeekdayInMonth.getValidTo());
  }

  @Override
  public YearCycle cycle() {
    return fixedWeekdayInMonth.getEvery() == null
      ? YearCycle.EVERY_YEAR
      : YearCycle.valueOf(fixedWeekdayInMonth.getEvery().name());
  }
}
