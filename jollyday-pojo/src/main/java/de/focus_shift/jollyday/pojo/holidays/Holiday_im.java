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

public class Holiday_im {

  public static JavaConfiguration configuration;

  static {
    configuration = new JavaConfiguration(new JavaHolidays()
      .addFixed(new JavaFixed("NEW_YEAR", HolidayType.OFFICIAL_HOLIDAY, null, null, YearCycle.EVERY_YEAR, List.of(new JavaMovingCondition(DayOfWeek.of(6), With.NEXT, DayOfWeek.of(1)), new JavaMovingCondition(DayOfWeek.of(7), With.NEXT, DayOfWeek.of(1))), MonthDay.of(1, 1)))
      .addFixed(new JavaFixed("TYNWALD", HolidayType.OFFICIAL_HOLIDAY, null, null, YearCycle.EVERY_YEAR, List.of(new JavaMovingCondition(DayOfWeek.of(6), With.NEXT, DayOfWeek.of(1)), new JavaMovingCondition(DayOfWeek.of(7), With.NEXT, DayOfWeek.of(1))), MonthDay.of(7, 5)))
      .addFixed(new JavaFixed("CHRISTMAS", HolidayType.OFFICIAL_HOLIDAY, null, null, YearCycle.EVERY_YEAR, List.of(new JavaMovingCondition(DayOfWeek.of(6), With.NEXT, DayOfWeek.of(1)), new JavaMovingCondition(DayOfWeek.of(7), With.NEXT, DayOfWeek.of(2))), MonthDay.of(12, 25)))
      .addFixed(new JavaFixed("BOXING_DAY", HolidayType.OFFICIAL_HOLIDAY, null, null, YearCycle.EVERY_YEAR, List.of(new JavaMovingCondition(DayOfWeek.of(6), With.NEXT, DayOfWeek.of(1)), new JavaMovingCondition(DayOfWeek.of(7), With.NEXT, DayOfWeek.of(2))), MonthDay.of(12, 26)))
      .addFixed(new JavaFixed("EARLY_MAY_BANK_HOLIDAY", HolidayType.OFFICIAL_HOLIDAY, Year.of(2020), Year.of(2020), YearCycle.EVERY_YEAR, null, MonthDay.of(5, 8)))
      .addFixed(new JavaFixed("KINGS_CORONATION", HolidayType.OFFICIAL_HOLIDAY, Year.of(2023), Year.of(2023), YearCycle.EVERY_YEAR, null, MonthDay.of(5, 8)))
      .addFixed(new JavaFixed("SPRING_BANK_HOLIDAY", HolidayType.OFFICIAL_HOLIDAY, Year.of(2022), Year.of(2022), YearCycle.EVERY_YEAR, null, MonthDay.of(6, 2)))
      .addFixed(new JavaFixed("QUEENS_PLATINUM_JUBILEE", HolidayType.OFFICIAL_HOLIDAY, Year.of(2022), Year.of(2022), YearCycle.EVERY_YEAR, null, MonthDay.of(6, 3)))
      .addFixed(new JavaFixed("TT_BANK_HOLIDAY", HolidayType.OFFICIAL_HOLIDAY, Year.of(2015), Year.of(2015), YearCycle.EVERY_YEAR, null, MonthDay.of(6, 12)))
      .addFixed(new JavaFixed("TT_BANK_HOLIDAY", HolidayType.OFFICIAL_HOLIDAY, Year.of(2020), Year.of(2020), YearCycle.EVERY_YEAR, null, MonthDay.of(6, 12)))
      .addFixed(new JavaFixed("TT_BANK_HOLIDAY", HolidayType.OFFICIAL_HOLIDAY, Year.of(2021), Year.of(2021), YearCycle.EVERY_YEAR, null, MonthDay.of(8, 27)))
      .addFixed(new JavaFixed("STATE_FUNERAL_QUEEN_ELIZABETH_II", HolidayType.OFFICIAL_HOLIDAY, Year.of(2022), Year.of(2022), YearCycle.EVERY_YEAR, null, MonthDay.of(9, 19)))
      .addChristianHoliday(new JavaChristianHoliday("christian.GOOD_FRIDAY", HolidayType.OFFICIAL_HOLIDAY, null, null, YearCycle.EVERY_YEAR, null, ChristianHolidayType.GOOD_FRIDAY, Chronology.of("ISO")))
      .addChristianHoliday(new JavaChristianHoliday("christian.EASTER_MONDAY", HolidayType.OFFICIAL_HOLIDAY, null, null, YearCycle.EVERY_YEAR, null, ChristianHolidayType.EASTER_MONDAY, Chronology.of("ISO")))
      .addFixedWeekdayBetweenFixed(new JavaFixedWeekdayBetweenFixed("TT_BANK_HOLIDAY", HolidayType.OFFICIAL_HOLIDAY, Year.of(1907), Year.of(2014), YearCycle.EVERY_YEAR, new JavaFixed("null", HolidayType.OFFICIAL_HOLIDAY, null, null, YearCycle.EVERY_YEAR, null, MonthDay.of(6, 5)), new JavaFixed("null", HolidayType.OFFICIAL_HOLIDAY, null, null, YearCycle.EVERY_YEAR, null, MonthDay.of(6, 11)), DayOfWeek.of(5)))
      .addFixedWeekdayBetweenFixed(new JavaFixedWeekdayBetweenFixed("TT_BANK_HOLIDAY", HolidayType.OFFICIAL_HOLIDAY, Year.of(2016), Year.of(2019), YearCycle.EVERY_YEAR, new JavaFixed("null", HolidayType.OFFICIAL_HOLIDAY, null, null, YearCycle.EVERY_YEAR, null, MonthDay.of(6, 5)), new JavaFixed("null", HolidayType.OFFICIAL_HOLIDAY, null, null, YearCycle.EVERY_YEAR, null, MonthDay.of(6, 11)), DayOfWeek.of(5)))
      .addFixedWeekdayBetweenFixed(new JavaFixedWeekdayBetweenFixed("TT_BANK_HOLIDAY", HolidayType.OFFICIAL_HOLIDAY, Year.of(2022), null, YearCycle.EVERY_YEAR, new JavaFixed("null", HolidayType.OFFICIAL_HOLIDAY, null, null, YearCycle.EVERY_YEAR, null, MonthDay.of(6, 5)), new JavaFixed("null", HolidayType.OFFICIAL_HOLIDAY, null, null, YearCycle.EVERY_YEAR, null, MonthDay.of(6, 11)), DayOfWeek.of(5)))
      .addFixedWeekday(new JavaFixedWeekdayInMonth("EARLY_MAY_BANK_HOLIDAY", HolidayType.OFFICIAL_HOLIDAY, Year.of(1900), Year.of(2019), YearCycle.EVERY_YEAR, DayOfWeek.of(1), Month.of(5), Occurrance.FIRST))
      .addFixedWeekday(new JavaFixedWeekdayInMonth("EARLY_MAY_BANK_HOLIDAY", HolidayType.OFFICIAL_HOLIDAY, Year.of(2021), null, YearCycle.EVERY_YEAR, DayOfWeek.of(1), Month.of(5), Occurrance.FIRST))
      .addFixedWeekday(new JavaFixedWeekdayInMonth("LATE_MAY_BANK_HOLIDAY", HolidayType.OFFICIAL_HOLIDAY, null, Year.of(2021), YearCycle.EVERY_YEAR, DayOfWeek.of(1), Month.of(5), Occurrance.LAST))
      .addFixedWeekday(new JavaFixedWeekdayInMonth("SPRING_BANK_HOLIDAY", HolidayType.OFFICIAL_HOLIDAY, Year.of(2023), null, YearCycle.EVERY_YEAR, DayOfWeek.of(1), Month.of(5), Occurrance.LAST))
      .addFixedWeekday(new JavaFixedWeekdayInMonth("SUMMER_BANK_HOLIDAY", HolidayType.OFFICIAL_HOLIDAY, null, null, YearCycle.EVERY_YEAR, DayOfWeek.of(1), Month.of(8), Occurrance.LAST))
      , null, "im", "Isle of Man");
  }
}
