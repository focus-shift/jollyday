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

public class Holiday_bs {

  public static JavaConfiguration configuration;

  static {
    configuration = new JavaConfiguration(new JavaHolidays()
      .addFixed(new JavaFixed("NEW_YEAR", HolidayType.OFFICIAL_HOLIDAY, null, null, YearCycle.EVERY_YEAR, List.of(new JavaMovingCondition(DayOfWeek.of(6), With.NEXT, DayOfWeek.of(1)), new JavaMovingCondition(DayOfWeek.of(7), With.NEXT, DayOfWeek.of(1)), new JavaMovingCondition(DayOfWeek.of(2), With.PREVIOUS, DayOfWeek.of(1)), new JavaMovingCondition(DayOfWeek.of(3), With.NEXT, DayOfWeek.of(5)), new JavaMovingCondition(DayOfWeek.of(4), With.NEXT, DayOfWeek.of(5))), MonthDay.of(1, 1)))
      .addFixed(new JavaFixed("MAJORITY_RULE_DAY", HolidayType.OFFICIAL_HOLIDAY, Year.of(2014), null, YearCycle.EVERY_YEAR, List.of(new JavaMovingCondition(DayOfWeek.of(6), With.NEXT, DayOfWeek.of(1)), new JavaMovingCondition(DayOfWeek.of(7), With.NEXT, DayOfWeek.of(1)), new JavaMovingCondition(DayOfWeek.of(2), With.PREVIOUS, DayOfWeek.of(1)), new JavaMovingCondition(DayOfWeek.of(3), With.NEXT, DayOfWeek.of(5)), new JavaMovingCondition(DayOfWeek.of(4), With.NEXT, DayOfWeek.of(5))), MonthDay.of(1, 10)))
      .addFixed(new JavaFixed("INDEPENDENCE_DAY", HolidayType.OFFICIAL_HOLIDAY, Year.of(1973), null, YearCycle.EVERY_YEAR, List.of(new JavaMovingCondition(DayOfWeek.of(6), With.NEXT, DayOfWeek.of(1)), new JavaMovingCondition(DayOfWeek.of(7), With.NEXT, DayOfWeek.of(1)), new JavaMovingCondition(DayOfWeek.of(2), With.PREVIOUS, DayOfWeek.of(1))), MonthDay.of(7, 10)))
      .addFixed(new JavaFixed("CHRISTMAS", HolidayType.OFFICIAL_HOLIDAY, null, null, YearCycle.EVERY_YEAR, List.of(new JavaMovingCondition(DayOfWeek.of(6), With.NEXT, DayOfWeek.of(1)), new JavaMovingCondition(DayOfWeek.of(7), With.NEXT, DayOfWeek.of(1))), MonthDay.of(12, 25)))
      .addFixed(new JavaFixed("BOXING_DAY", HolidayType.OFFICIAL_HOLIDAY, null, null, YearCycle.EVERY_YEAR, List.of(new JavaMovingCondition(DayOfWeek.of(7), With.NEXT, DayOfWeek.of(2)), new JavaMovingCondition(DayOfWeek.of(1), With.NEXT, DayOfWeek.of(2)), new JavaMovingCondition(DayOfWeek.of(6), With.NEXT, DayOfWeek.of(1))), MonthDay.of(12, 26)))
      .addChristianHoliday(new JavaChristianHoliday("christian.GOOD_FRIDAY", HolidayType.OFFICIAL_HOLIDAY, null, null, YearCycle.EVERY_YEAR, null, ChristianHolidayType.GOOD_FRIDAY, Chronology.of("ISO")))
      .addChristianHoliday(new JavaChristianHoliday("christian.EASTER_MONDAY", HolidayType.OFFICIAL_HOLIDAY, null, null, YearCycle.EVERY_YEAR, null, ChristianHolidayType.EASTER_MONDAY, Chronology.of("ISO")))
      .addChristianHoliday(new JavaChristianHoliday("christian.WHIT_MONDAY", HolidayType.OFFICIAL_HOLIDAY, null, null, YearCycle.EVERY_YEAR, null, ChristianHolidayType.WHIT_MONDAY, Chronology.of("ISO")))
      .addFixedWeekday(new JavaFixedWeekdayInMonth("LABOUR_DAY", HolidayType.OFFICIAL_HOLIDAY, null, null, YearCycle.EVERY_YEAR, DayOfWeek.of(5), Month.of(6), Occurrance.FIRST))
      .addFixedWeekday(new JavaFixedWeekdayInMonth("EMANCIPATION_DAY", HolidayType.OFFICIAL_HOLIDAY, null, null, YearCycle.EVERY_YEAR, DayOfWeek.of(1), Month.of(8), Occurrance.FIRST))
      .addFixedWeekday(new JavaFixedWeekdayInMonth("NATIONAL_HEROES_DAY", HolidayType.OFFICIAL_HOLIDAY, Year.of(2013), null, YearCycle.EVERY_YEAR, DayOfWeek.of(1), Month.of(10), Occurrance.SECOND))
      , null, "bs", "Bahamas");
  }
}
