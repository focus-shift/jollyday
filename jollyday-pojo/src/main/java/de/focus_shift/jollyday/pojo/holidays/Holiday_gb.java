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

public class Holiday_gb {

  public static JavaConfiguration configuration;

  static {
    configuration = new JavaConfiguration(new JavaHolidays()
      .addFixed(new JavaFixed("NEW_YEAR", HolidayType.OFFICIAL_HOLIDAY, null, null, YearCycle.EVERY_YEAR, List.of(new JavaMovingCondition(DayOfWeek.of(6), With.NEXT, DayOfWeek.of(1)), new JavaMovingCondition(DayOfWeek.of(7), With.NEXT, DayOfWeek.of(1))), MonthDay.of(1, 1)))
      .addFixed(new JavaFixed("CHRISTMAS", HolidayType.OFFICIAL_HOLIDAY, null, Year.of(2010), YearCycle.EVERY_YEAR, List.of(new JavaMovingCondition(DayOfWeek.of(6), With.NEXT, DayOfWeek.of(1)), new JavaMovingCondition(DayOfWeek.of(7), With.NEXT, DayOfWeek.of(2))), MonthDay.of(12, 25)))
      .addFixed(new JavaFixed("CHRISTMAS", HolidayType.OFFICIAL_HOLIDAY, Year.of(2011), Year.of(2011), YearCycle.EVERY_YEAR, List.of(new JavaMovingCondition(DayOfWeek.of(6), With.NEXT, DayOfWeek.of(1)), new JavaMovingCondition(DayOfWeek.of(7), With.NEXT, DayOfWeek.of(1))), MonthDay.of(12, 25)))
      .addFixed(new JavaFixed("CHRISTMAS", HolidayType.OFFICIAL_HOLIDAY, Year.of(2012), null, YearCycle.EVERY_YEAR, List.of(new JavaMovingCondition(DayOfWeek.of(6), With.NEXT, DayOfWeek.of(1)), new JavaMovingCondition(DayOfWeek.of(7), With.NEXT, DayOfWeek.of(2))), MonthDay.of(12, 25)))
      .addFixed(new JavaFixed("BOXING_DAY", HolidayType.OFFICIAL_HOLIDAY, null, Year.of(2010), YearCycle.EVERY_YEAR, List.of(new JavaMovingCondition(DayOfWeek.of(6), With.NEXT, DayOfWeek.of(1)), new JavaMovingCondition(DayOfWeek.of(7), With.NEXT, DayOfWeek.of(2))), MonthDay.of(12, 26)))
      .addFixed(new JavaFixed("BOXING_DAY", HolidayType.OFFICIAL_HOLIDAY, Year.of(2011), Year.of(2011), YearCycle.EVERY_YEAR, List.of(new JavaMovingCondition(DayOfWeek.of(6), With.NEXT, DayOfWeek.of(1)), new JavaMovingCondition(DayOfWeek.of(7), With.NEXT, DayOfWeek.of(2)), new JavaMovingCondition(DayOfWeek.of(1), With.NEXT, DayOfWeek.of(2))), MonthDay.of(12, 26)))
      .addFixed(new JavaFixed("BOXING_DAY", HolidayType.OFFICIAL_HOLIDAY, Year.of(2012), null, YearCycle.EVERY_YEAR, List.of(new JavaMovingCondition(DayOfWeek.of(6), With.NEXT, DayOfWeek.of(1)), new JavaMovingCondition(DayOfWeek.of(7), With.NEXT, DayOfWeek.of(2))), MonthDay.of(12, 26)))
      .addFixed(new JavaFixed("ROYAL_WEDDING", HolidayType.OFFICIAL_HOLIDAY, Year.of(2011), Year.of(2011), YearCycle.EVERY_YEAR, null, MonthDay.of(4, 29)))
      .addFixed(new JavaFixed("EARLY_MAY_BANK_HOLIDAY", HolidayType.OFFICIAL_HOLIDAY, Year.of(2020), Year.of(2020), YearCycle.EVERY_YEAR, null, MonthDay.of(5, 8)))
      .addFixed(new JavaFixed("KINGS_CORONATION", HolidayType.OFFICIAL_HOLIDAY, Year.of(2023), Year.of(2023), YearCycle.EVERY_YEAR, null, MonthDay.of(5, 8)))
      .addFixed(new JavaFixed("SPRING_BANK_HOLIDAY", HolidayType.OFFICIAL_HOLIDAY, Year.of(2022), Year.of(2022), YearCycle.EVERY_YEAR, null, MonthDay.of(6, 2)))
      .addFixed(new JavaFixed("SPRING_BANK_HOLIDAY", HolidayType.OFFICIAL_HOLIDAY, Year.of(2012), Year.of(2012), YearCycle.EVERY_YEAR, null, MonthDay.of(6, 4)))
      .addFixed(new JavaFixed("QUEENS_PLATINUM_JUBILEE", HolidayType.OFFICIAL_HOLIDAY, Year.of(2022), Year.of(2022), YearCycle.EVERY_YEAR, null, MonthDay.of(6, 3)))
      .addFixed(new JavaFixed("QUEENS_DIAMOND_JUBILEE", HolidayType.OFFICIAL_HOLIDAY, Year.of(2012), Year.of(2012), YearCycle.EVERY_YEAR, null, MonthDay.of(6, 5)))
      .addFixed(new JavaFixed("QUEENS_STATE_FUNERAL", HolidayType.OFFICIAL_HOLIDAY, Year.of(2022), Year.of(2022), YearCycle.EVERY_YEAR, null, MonthDay.of(9, 19)))
      .addChristianHoliday(new JavaChristianHoliday("christian.GOOD_FRIDAY", HolidayType.OFFICIAL_HOLIDAY, null, null, YearCycle.EVERY_YEAR, null, ChristianHolidayType.GOOD_FRIDAY, Chronology.of("ISO")))
      .addFixedWeekday(new JavaFixedWeekdayInMonth("EARLY_MAY_BANK_HOLIDAY", HolidayType.OFFICIAL_HOLIDAY, Year.of(1900), Year.of(2019), YearCycle.EVERY_YEAR, DayOfWeek.of(1), Month.of(5), Occurrance.FIRST))
      .addFixedWeekday(new JavaFixedWeekdayInMonth("EARLY_MAY_BANK_HOLIDAY", HolidayType.OFFICIAL_HOLIDAY, Year.of(2021), null, YearCycle.EVERY_YEAR, DayOfWeek.of(1), Month.of(5), Occurrance.FIRST))
      .addFixedWeekday(new JavaFixedWeekdayInMonth("SPRING_BANK_HOLIDAY", HolidayType.OFFICIAL_HOLIDAY, null, Year.of(2011), YearCycle.EVERY_YEAR, DayOfWeek.of(1), Month.of(5), Occurrance.LAST))
      .addFixedWeekday(new JavaFixedWeekdayInMonth("SPRING_BANK_HOLIDAY", HolidayType.OFFICIAL_HOLIDAY, Year.of(2013), Year.of(2021), YearCycle.EVERY_YEAR, DayOfWeek.of(1), Month.of(5), Occurrance.LAST))
      .addFixedWeekday(new JavaFixedWeekdayInMonth("SPRING_BANK_HOLIDAY", HolidayType.OFFICIAL_HOLIDAY, Year.of(2023), null, YearCycle.EVERY_YEAR, DayOfWeek.of(1), Month.of(5), Occurrance.LAST))
      , List.of(new JavaConfiguration(new JavaHolidays()
      .addChristianHoliday(new JavaChristianHoliday("christian.EASTER_MONDAY", HolidayType.OFFICIAL_HOLIDAY, null, null, YearCycle.EVERY_YEAR, null, ChristianHolidayType.EASTER_MONDAY, Chronology.of("ISO")))
      .addFixedWeekday(new JavaFixedWeekdayInMonth("SUMMER_BANK_HOLIDAY", HolidayType.OFFICIAL_HOLIDAY, null, null, YearCycle.EVERY_YEAR, DayOfWeek.of(1), Month.of(8), Occurrance.LAST))
      , null, "eng", "England"), new JavaConfiguration(new JavaHolidays()
      .addChristianHoliday(new JavaChristianHoliday("christian.EASTER_MONDAY", HolidayType.OFFICIAL_HOLIDAY, null, null, YearCycle.EVERY_YEAR, null, ChristianHolidayType.EASTER_MONDAY, Chronology.of("ISO")))
      .addFixedWeekday(new JavaFixedWeekdayInMonth("SUMMER_BANK_HOLIDAY", HolidayType.OFFICIAL_HOLIDAY, null, null, YearCycle.EVERY_YEAR, DayOfWeek.of(1), Month.of(8), Occurrance.LAST))
      , null, "wls", "Wales"), new JavaConfiguration(new JavaHolidays()
      .addFixed(new JavaFixed("2ND_JANUARY", HolidayType.OFFICIAL_HOLIDAY, null, null, YearCycle.EVERY_YEAR, List.of(new JavaMovingCondition(DayOfWeek.of(6), With.NEXT, DayOfWeek.of(1)), new JavaMovingCondition(DayOfWeek.of(7), With.NEXT, DayOfWeek.of(2)), new JavaMovingCondition(DayOfWeek.of(1), With.NEXT, DayOfWeek.of(2))), MonthDay.of(1, 2)))
      .addFixed(new JavaFixed("ST_ANDREW", HolidayType.OFFICIAL_HOLIDAY, Year.of(2007), null, YearCycle.EVERY_YEAR, List.of(new JavaMovingCondition(DayOfWeek.of(6), With.NEXT, DayOfWeek.of(1)), new JavaMovingCondition(DayOfWeek.of(7), With.NEXT, DayOfWeek.of(1))), MonthDay.of(11, 30)))
      .addFixedWeekday(new JavaFixedWeekdayInMonth("SUMMER_BANK_HOLIDAY", HolidayType.OFFICIAL_HOLIDAY, null, null, YearCycle.EVERY_YEAR, DayOfWeek.of(1), Month.of(8), Occurrance.FIRST))
      , null, "sct", "Scotland"), new JavaConfiguration(new JavaHolidays()
      .addFixed(new JavaFixed("ST_PATRICK", HolidayType.OFFICIAL_HOLIDAY, Year.of(1903), null, YearCycle.EVERY_YEAR, List.of(new JavaMovingCondition(DayOfWeek.of(6), With.NEXT, DayOfWeek.of(1)), new JavaMovingCondition(DayOfWeek.of(7), With.NEXT, DayOfWeek.of(1))), MonthDay.of(3, 17)))
      .addFixed(new JavaFixed("BATTLE_BOYNE", HolidayType.OFFICIAL_HOLIDAY, null, null, YearCycle.EVERY_YEAR, List.of(new JavaMovingCondition(DayOfWeek.of(6), With.NEXT, DayOfWeek.of(1)), new JavaMovingCondition(DayOfWeek.of(7), With.NEXT, DayOfWeek.of(1))), MonthDay.of(7, 12)))
      .addChristianHoliday(new JavaChristianHoliday("christian.EASTER_MONDAY", HolidayType.OFFICIAL_HOLIDAY, null, null, YearCycle.EVERY_YEAR, null, ChristianHolidayType.EASTER_MONDAY, Chronology.of("ISO")))
      .addFixedWeekday(new JavaFixedWeekdayInMonth("SUMMER_BANK_HOLIDAY", HolidayType.OFFICIAL_HOLIDAY, null, null, YearCycle.EVERY_YEAR, DayOfWeek.of(1), Month.of(8), Occurrance.LAST))
      , null, "nir", "Northern Ireland")), "gb", "United Kingdom of Great Britain and Northern Ireland");
  }
}
