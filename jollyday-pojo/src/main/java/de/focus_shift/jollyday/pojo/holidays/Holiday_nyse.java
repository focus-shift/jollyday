package de.focus_shift.jollyday.pojo.holidays;

import java.time.DayOfWeek;
import java.time.Month;
import java.time.MonthDay;
import java.time.Year;
import java.time.chrono.Chronology;
import java.util.List;

import de.focus_shift.jollyday.core.HolidayType;
import de.focus_shift.jollyday.core.spi.ChristianHolidayType;
import de.focus_shift.jollyday.core.spi.Occurrance;
import de.focus_shift.jollyday.core.spi.With;
import de.focus_shift.jollyday.core.spi.YearCycle;
import de.focus_shift.jollyday.pojo.JavaChristianHoliday;
import de.focus_shift.jollyday.pojo.JavaConfiguration;
import de.focus_shift.jollyday.pojo.JavaFixed;
import de.focus_shift.jollyday.pojo.JavaFixedWeekdayInMonth;
import de.focus_shift.jollyday.pojo.JavaHolidays;
import de.focus_shift.jollyday.pojo.JavaMovingCondition;

public class Holiday_nyse {

  public static JavaConfiguration configuration;

  static {
    configuration = new JavaConfiguration(new JavaHolidays()
      .addFixed(new JavaFixed("NEW_YEAR", HolidayType.OFFICIAL_HOLIDAY, null, null, YearCycle.EVERY_YEAR, List.of(new JavaMovingCondition(DayOfWeek.of(6), With.PREVIOUS, DayOfWeek.of(5)), new JavaMovingCondition(DayOfWeek.of(7), With.NEXT, DayOfWeek.of(1))), MonthDay.of(1, 1)))
      .addFixed(new JavaFixed("JUNETEENTH", HolidayType.OFFICIAL_HOLIDAY, Year.of(2022), null, YearCycle.EVERY_YEAR, List.of(new JavaMovingCondition(DayOfWeek.of(6), With.PREVIOUS, DayOfWeek.of(5)), new JavaMovingCondition(DayOfWeek.of(7), With.NEXT, DayOfWeek.of(1))), MonthDay.of(6, 19)))
      .addFixed(new JavaFixed("INDEPENDENCE_DAY", HolidayType.OFFICIAL_HOLIDAY, null, null, YearCycle.EVERY_YEAR, List.of(new JavaMovingCondition(DayOfWeek.of(6), With.PREVIOUS, DayOfWeek.of(5)), new JavaMovingCondition(DayOfWeek.of(7), With.NEXT, DayOfWeek.of(1))), MonthDay.of(7, 4)))
      .addFixed(new JavaFixed("CHRISTMAS", HolidayType.OFFICIAL_HOLIDAY, null, null, YearCycle.EVERY_YEAR, List.of(new JavaMovingCondition(DayOfWeek.of(6), With.PREVIOUS, DayOfWeek.of(5)), new JavaMovingCondition(DayOfWeek.of(7), With.NEXT, DayOfWeek.of(1))), MonthDay.of(12, 25)))
      .addFixed(new JavaFixed("FUNERAL_OF_PRESIDENT_REAGAN", HolidayType.OFFICIAL_HOLIDAY, Year.of(2004), Year.of(2004), YearCycle.EVERY_YEAR, null, MonthDay.of(6, 11)))
      .addFixed(new JavaFixed("REMEMBERANCE_OF_PRESIDENT_FORD", HolidayType.OFFICIAL_HOLIDAY, Year.of(2007), Year.of(2007), YearCycle.EVERY_YEAR, null, MonthDay.of(1, 2)))
      .addFixed(new JavaFixed("HURRICANE_SANDY", HolidayType.OFFICIAL_HOLIDAY, Year.of(2012), Year.of(2012), YearCycle.EVERY_YEAR, null, MonthDay.of(10, 29)))
      .addFixed(new JavaFixed("HURRICANE_SANDY", HolidayType.OFFICIAL_HOLIDAY, Year.of(2012), Year.of(2012), YearCycle.EVERY_YEAR, null, MonthDay.of(10, 30)))
      .addChristianHoliday(new JavaChristianHoliday("christian.GOOD_FRIDAY", HolidayType.OFFICIAL_HOLIDAY, null, null, YearCycle.EVERY_YEAR, null, ChristianHolidayType.GOOD_FRIDAY, Chronology.of("ISO")))
      .addFixedWeekday(new JavaFixedWeekdayInMonth("MARTIN_LUTHER_KING", HolidayType.OFFICIAL_HOLIDAY, null, null, YearCycle.EVERY_YEAR, DayOfWeek.of(1), Month.of(1), Occurrance.THIRD))
      .addFixedWeekday(new JavaFixedWeekdayInMonth("WASHINGTONS_BIRTHDAY", HolidayType.OFFICIAL_HOLIDAY, null, null, YearCycle.EVERY_YEAR, DayOfWeek.of(1), Month.of(2), Occurrance.THIRD))
      .addFixedWeekday(new JavaFixedWeekdayInMonth("MEMORIAL_DAY", HolidayType.OFFICIAL_HOLIDAY, null, null, YearCycle.EVERY_YEAR, DayOfWeek.of(1), Month.of(5), Occurrance.LAST))
      .addFixedWeekday(new JavaFixedWeekdayInMonth("LABOUR_DAY", HolidayType.OFFICIAL_HOLIDAY, null, null, YearCycle.EVERY_YEAR, DayOfWeek.of(1), Month.of(9), Occurrance.FIRST))
      .addFixedWeekday(new JavaFixedWeekdayInMonth("THANKSGIVING", HolidayType.OFFICIAL_HOLIDAY, null, null, YearCycle.EVERY_YEAR, DayOfWeek.of(4), Month.of(11), Occurrance.FOURTH))
      , null, "nyse", "New York Stock Exchange");
  }
}
