package de.focus_shift.jollyday.pojo.holidays;

import java.time.DayOfWeek;
import java.time.Month;
import java.time.MonthDay;
import java.time.Year;
import java.util.List;

import de.focus_shift.jollyday.core.HolidayType;
import de.focus_shift.jollyday.core.spi.Occurrance;
import de.focus_shift.jollyday.core.spi.With;
import de.focus_shift.jollyday.core.spi.YearCycle;
import de.focus_shift.jollyday.pojo.JavaConfiguration;
import de.focus_shift.jollyday.pojo.JavaFixed;
import de.focus_shift.jollyday.pojo.JavaFixedWeekdayInMonth;
import de.focus_shift.jollyday.pojo.JavaHolidays;
import de.focus_shift.jollyday.pojo.JavaMovingCondition;

public class Holiday_mx {

  public static JavaConfiguration configuration;

  static {
    configuration = new JavaConfiguration(new JavaHolidays()
      .addFixed(new JavaFixed("ARMY_DAY", HolidayType.UNOFFICIAL_HOLIDAY, null, null, YearCycle.EVERY_YEAR, null, MonthDay.of(2, 19)))
      .addFixed(new JavaFixed("FLAG_DAY", HolidayType.UNOFFICIAL_HOLIDAY, Year.of(1937), null, YearCycle.EVERY_YEAR, null, MonthDay.of(2, 24)))
      .addFixed(new JavaFixed("OIL_EXPROPRIATION_DAY", HolidayType.UNOFFICIAL_HOLIDAY, Year.of(1938), null, YearCycle.EVERY_YEAR, null, MonthDay.of(3, 18)))
      .addFixed(new JavaFixed("COLUMBUS_DAY", HolidayType.UNOFFICIAL_HOLIDAY, null, null, YearCycle.EVERY_YEAR, null, MonthDay.of(10, 12)))
      .addFixed(new JavaFixed("NEW_YEAR", HolidayType.OFFICIAL_HOLIDAY, null, null, YearCycle.EVERY_YEAR, List.of(new JavaMovingCondition(DayOfWeek.of(6), With.PREVIOUS, DayOfWeek.of(5)), new JavaMovingCondition(DayOfWeek.of(7), With.NEXT, DayOfWeek.of(1))), MonthDay.of(1, 1)))
      .addFixed(new JavaFixed("LABOUR_DAY", HolidayType.OFFICIAL_HOLIDAY, null, null, YearCycle.EVERY_YEAR, List.of(new JavaMovingCondition(DayOfWeek.of(6), With.PREVIOUS, DayOfWeek.of(5)), new JavaMovingCondition(DayOfWeek.of(7), With.NEXT, DayOfWeek.of(1))), MonthDay.of(5, 1)))
      .addFixed(new JavaFixed("INDEPENDENCE_DAY", HolidayType.OFFICIAL_HOLIDAY, null, null, YearCycle.EVERY_YEAR, List.of(new JavaMovingCondition(DayOfWeek.of(6), With.PREVIOUS, DayOfWeek.of(5)), new JavaMovingCondition(DayOfWeek.of(7), With.NEXT, DayOfWeek.of(1))), MonthDay.of(9, 16)))
      .addFixed(new JavaFixed("GOVERNMENT_CHANGE", HolidayType.OFFICIAL_HOLIDAY, Year.of(1934), null, YearCycle.SIX_YEARS, List.of(new JavaMovingCondition(DayOfWeek.of(6), With.PREVIOUS, DayOfWeek.of(5)), new JavaMovingCondition(DayOfWeek.of(7), With.NEXT, DayOfWeek.of(1))), MonthDay.of(12, 1)))
      .addFixed(new JavaFixed("CHRISTMAS", HolidayType.OFFICIAL_HOLIDAY, null, null, YearCycle.EVERY_YEAR, List.of(new JavaMovingCondition(DayOfWeek.of(6), With.PREVIOUS, DayOfWeek.of(5)), new JavaMovingCondition(DayOfWeek.of(7), With.NEXT, DayOfWeek.of(1))), MonthDay.of(12, 25)))
      .addFixedWeekday(new JavaFixedWeekdayInMonth("CONSTITUTION_DAY", HolidayType.OFFICIAL_HOLIDAY, null, null, YearCycle.EVERY_YEAR, DayOfWeek.of(1), Month.of(2), Occurrance.FIRST))
      .addFixedWeekday(new JavaFixedWeekdayInMonth("JUAREZ_BIRTHDAY", HolidayType.OFFICIAL_HOLIDAY, null, null, YearCycle.EVERY_YEAR, DayOfWeek.of(1), Month.of(3), Occurrance.THIRD))
      .addFixedWeekday(new JavaFixedWeekdayInMonth("REVOLUTION", HolidayType.OFFICIAL_HOLIDAY, null, null, YearCycle.EVERY_YEAR, DayOfWeek.of(1), Month.of(11), Occurrance.THIRD))
      , List.of(new JavaConfiguration(new JavaHolidays()
      .addFixed(new JavaFixed("MAY_DAY", HolidayType.UNOFFICIAL_HOLIDAY, null, null, YearCycle.EVERY_YEAR, null, MonthDay.of(5, 5)))
      , null, "pue", "Puebla")), "mx", "Mexico");
  }
}
