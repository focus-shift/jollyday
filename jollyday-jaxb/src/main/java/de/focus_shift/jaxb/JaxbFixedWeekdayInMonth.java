package de.focus_shift.jaxb;

import de.focus_shift.HolidayType;
import de.focus_shift.spi.FixedWeekdayInMonth;
import de.focus_shift.spi.Occurrance;
import de.focus_shift.spi.YearCycle;

import java.time.DayOfWeek;
import java.time.Month;
import java.time.Year;

/**
 * @author sdiedrichsen
 * @version $
 * @since 15.03.20
 */
public class JaxbFixedWeekdayInMonth implements FixedWeekdayInMonth {

  private final de.focus_shift.jaxb.mapping.FixedWeekdayInMonth fixedWeekdayInMonth;

  public JaxbFixedWeekdayInMonth(de.focus_shift.jaxb.mapping.FixedWeekdayInMonth fixedWeekdayInMonth) {
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
    return Year.of(fixedWeekdayInMonth.getValidFrom());
  }

  @Override
  public Year validTo() {
    return Year.of(fixedWeekdayInMonth.getValidTo());
  }

  @Override
  public YearCycle cycle() {
    return fixedWeekdayInMonth.getEvery() == null
      ? YearCycle.EVERY_YEAR
      : YearCycle.valueOf(fixedWeekdayInMonth.getEvery());
  }
}
