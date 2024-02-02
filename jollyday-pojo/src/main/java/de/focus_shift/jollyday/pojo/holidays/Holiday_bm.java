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
import de.focus_shift.jollyday.pojo.JavaFixedWeekdayBetweenFixed;
import de.focus_shift.jollyday.pojo.JavaFixedWeekdayInMonth;
import de.focus_shift.jollyday.pojo.JavaHolidays;
import de.focus_shift.jollyday.pojo.JavaMovingCondition;

public class Holiday_bm {

  public static JavaConfiguration configuration;

  static {
    configuration = new JavaConfiguration(new JavaHolidays()
      .addFixed(new JavaFixed("NEW_YEAR", HolidayType.OFFICIAL_HOLIDAY, null, null, YearCycle.EVERY_YEAR, List.of(new JavaMovingCondition(DayOfWeek.of(6), With.NEXT, DayOfWeek.of(1)), new JavaMovingCondition(DayOfWeek.of(7), With.NEXT, DayOfWeek.of(1))), MonthDay.of(1, 1)))
      .addFixed(new JavaFixed("KINGS_CORONATION", HolidayType.OFFICIAL_HOLIDAY, Year.of(2023), Year.of(2023), YearCycle.EVERY_YEAR, null, MonthDay.of(5, 8)))
      .addFixed(new JavaFixed("BERMUDA_DAY", HolidayType.OFFICIAL_HOLIDAY, Year.of(1979), Year.of(2017), YearCycle.EVERY_YEAR, List.of(new JavaMovingCondition(DayOfWeek.of(6), With.NEXT, DayOfWeek.of(1)), new JavaMovingCondition(DayOfWeek.of(7), With.NEXT, DayOfWeek.of(1))), MonthDay.of(5, 24)))
      .addFixed(new JavaFixed("BERMUDA_DAY", HolidayType.OFFICIAL_HOLIDAY, Year.of(2019), Year.of(2019), YearCycle.EVERY_YEAR, null, MonthDay.of(5, 24)))
      .addFixed(new JavaFixed("NATIONAL_HEROES_DAY", HolidayType.OFFICIAL_HOLIDAY, Year.of(2008), Year.of(2008), YearCycle.EVERY_YEAR, null, MonthDay.of(10, 12)))
      .addFixed(new JavaFixed("170_ANNIVERSARY_ARRIVAL_BERMUDA", HolidayType.OFFICIAL_HOLIDAY, Year.of(2019), Year.of(2019), YearCycle.EVERY_YEAR, null, MonthDay.of(11, 4)))
      .addFixed(new JavaFixed("REMEMBRANCE", HolidayType.OFFICIAL_HOLIDAY, Year.of(1919), null, YearCycle.EVERY_YEAR, List.of(new JavaMovingCondition(DayOfWeek.of(6), With.NEXT, DayOfWeek.of(1)), new JavaMovingCondition(DayOfWeek.of(7), With.NEXT, DayOfWeek.of(1))), MonthDay.of(11, 11)))
      .addFixed(new JavaFixed("CHRISTMAS", HolidayType.OFFICIAL_HOLIDAY, null, Year.of(2015), YearCycle.EVERY_YEAR, List.of(new JavaMovingCondition(DayOfWeek.of(6), With.NEXT, DayOfWeek.of(1)), new JavaMovingCondition(DayOfWeek.of(7), With.NEXT, DayOfWeek.of(1))), MonthDay.of(12, 25)))
      .addFixed(new JavaFixed("CHRISTMAS", HolidayType.OFFICIAL_HOLIDAY, Year.of(2016), Year.of(2016), YearCycle.EVERY_YEAR, List.of(new JavaMovingCondition(DayOfWeek.of(6), With.NEXT, DayOfWeek.of(1)), new JavaMovingCondition(DayOfWeek.of(7), With.NEXT, DayOfWeek.of(2))), MonthDay.of(12, 25)))
      .addFixed(new JavaFixed("CHRISTMAS", HolidayType.OFFICIAL_HOLIDAY, Year.of(2017), null, YearCycle.EVERY_YEAR, List.of(new JavaMovingCondition(DayOfWeek.of(6), With.NEXT, DayOfWeek.of(1)), new JavaMovingCondition(DayOfWeek.of(7), With.NEXT, DayOfWeek.of(1))), MonthDay.of(12, 25)))
      .addFixed(new JavaFixed("BOXING_DAY", HolidayType.OFFICIAL_HOLIDAY, null, Year.of(2015), YearCycle.EVERY_YEAR, List.of(new JavaMovingCondition(DayOfWeek.of(6), With.NEXT, DayOfWeek.of(1)), new JavaMovingCondition(DayOfWeek.of(7), With.NEXT, DayOfWeek.of(2)), new JavaMovingCondition(DayOfWeek.of(1), With.NEXT, DayOfWeek.of(2))), MonthDay.of(12, 26)))
      .addFixed(new JavaFixed("BOXING_DAY", HolidayType.OFFICIAL_HOLIDAY, Year.of(2016), Year.of(2016), YearCycle.EVERY_YEAR, List.of(new JavaMovingCondition(DayOfWeek.of(6), With.NEXT, DayOfWeek.of(1)), new JavaMovingCondition(DayOfWeek.of(7), With.NEXT, DayOfWeek.of(2))), MonthDay.of(12, 26)))
      .addFixed(new JavaFixed("BOXING_DAY", HolidayType.OFFICIAL_HOLIDAY, Year.of(2017), null, YearCycle.EVERY_YEAR, List.of(new JavaMovingCondition(DayOfWeek.of(6), With.NEXT, DayOfWeek.of(1)), new JavaMovingCondition(DayOfWeek.of(7), With.NEXT, DayOfWeek.of(2)), new JavaMovingCondition(DayOfWeek.of(1), With.NEXT, DayOfWeek.of(2))), MonthDay.of(12, 26)))
      .addChristianHoliday(new JavaChristianHoliday("christian.GOOD_FRIDAY", HolidayType.OFFICIAL_HOLIDAY, null, null, YearCycle.EVERY_YEAR, null, ChristianHolidayType.GOOD_FRIDAY, Chronology.of("ISO")))
      .addFixedWeekdayBetweenFixed(new JavaFixedWeekdayBetweenFixed("EMANCIPATION_DAY", HolidayType.OFFICIAL_HOLIDAY, Year.of(1947), null, YearCycle.EVERY_YEAR, new JavaFixed("null", HolidayType.OFFICIAL_HOLIDAY, null, null, YearCycle.EVERY_YEAR, null, MonthDay.of(7, 28)), new JavaFixed("null", HolidayType.OFFICIAL_HOLIDAY, null, null, YearCycle.EVERY_YEAR, null, MonthDay.of(8, 3)), DayOfWeek.of(4)))
      .addFixedWeekdayBetweenFixed(new JavaFixedWeekdayBetweenFixed("SOMERS_DAY", HolidayType.OFFICIAL_HOLIDAY, Year.of(1947), Year.of(2019), YearCycle.EVERY_YEAR, new JavaFixed("null", HolidayType.OFFICIAL_HOLIDAY, null, null, YearCycle.EVERY_YEAR, null, MonthDay.of(7, 29)), new JavaFixed("null", HolidayType.OFFICIAL_HOLIDAY, null, null, YearCycle.EVERY_YEAR, null, MonthDay.of(8, 4)), DayOfWeek.of(5)))
      .addFixedWeekdayBetweenFixed(new JavaFixedWeekdayBetweenFixed("MARY_PRINCE_DAY", HolidayType.OFFICIAL_HOLIDAY, Year.of(2020), null, YearCycle.EVERY_YEAR, new JavaFixed("null", HolidayType.OFFICIAL_HOLIDAY, null, null, YearCycle.EVERY_YEAR, null, MonthDay.of(7, 29)), new JavaFixed("null", HolidayType.OFFICIAL_HOLIDAY, null, null, YearCycle.EVERY_YEAR, null, MonthDay.of(8, 4)), DayOfWeek.of(5)))
      .addFixedWeekday(new JavaFixedWeekdayInMonth("BERMUDA_DAY", HolidayType.OFFICIAL_HOLIDAY, Year.of(2018), Year.of(2018), YearCycle.EVERY_YEAR, DayOfWeek.of(5), Month.of(5), Occurrance.LAST))
      .addFixedWeekday(new JavaFixedWeekdayInMonth("BERMUDA_DAY", HolidayType.OFFICIAL_HOLIDAY, Year.of(2020), Year.of(2020), YearCycle.EVERY_YEAR, DayOfWeek.of(5), Month.of(5), Occurrance.LAST))
      .addFixedWeekday(new JavaFixedWeekdayInMonth("BERMUDA_DAY", HolidayType.OFFICIAL_HOLIDAY, Year.of(2021), null, YearCycle.EVERY_YEAR, DayOfWeek.of(5), Month.of(5), Occurrance.FOURTH))
      .addFixedWeekday(new JavaFixedWeekdayInMonth("NATIONAL_HEROES_DAY", HolidayType.OFFICIAL_HOLIDAY, Year.of(2009), null, YearCycle.EVERY_YEAR, DayOfWeek.of(1), Month.of(6), Occurrance.THIRD))
      .addFixedWeekday(new JavaFixedWeekdayInMonth("LABOUR_DAY", HolidayType.OFFICIAL_HOLIDAY, null, null, YearCycle.EVERY_YEAR, DayOfWeek.of(1), Month.of(9), Occurrance.FIRST))
      , null, "bm", "Bermuda");
  }
}
