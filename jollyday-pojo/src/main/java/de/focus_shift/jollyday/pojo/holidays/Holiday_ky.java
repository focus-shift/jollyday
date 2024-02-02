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

public class Holiday_ky {

  public static JavaConfiguration configuration;

  static {
    configuration = new JavaConfiguration(new JavaHolidays()
      .addFixed(new JavaFixed("NEW_YEAR", HolidayType.OFFICIAL_HOLIDAY, null, null, YearCycle.EVERY_YEAR, List.of(new JavaMovingCondition(DayOfWeek.of(6), With.NEXT, DayOfWeek.of(1)), new JavaMovingCondition(DayOfWeek.of(7), With.NEXT, DayOfWeek.of(1))), MonthDay.of(1, 1)))
      .addFixed(new JavaFixed("KINGS_CORONATION", HolidayType.OFFICIAL_HOLIDAY, Year.of(2023), Year.of(2023), YearCycle.EVERY_YEAR, null, MonthDay.of(5, 8)))
      .addFixed(new JavaFixed("ELECTION_DAY", HolidayType.OFFICIAL_HOLIDAY, Year.of(2009), Year.of(2009), YearCycle.EVERY_YEAR, null, MonthDay.of(5, 20)))
      .addFixed(new JavaFixed("QUEENS_PLATINUM_JUBILEE", HolidayType.OFFICIAL_HOLIDAY, Year.of(2022), Year.of(2022), YearCycle.EVERY_YEAR, null, MonthDay.of(6, 3)))
      .addFixed(new JavaFixed("KINGS_DAY", HolidayType.OFFICIAL_HOLIDAY, Year.of(2023), null, YearCycle.EVERY_YEAR, null, MonthDay.of(6, 19)))
      .addFixed(new JavaFixed("CONSTITUTION_COMMENCEMENT_2009", HolidayType.OFFICIAL_HOLIDAY, Year.of(2009), Year.of(2009), YearCycle.EVERY_YEAR, null, MonthDay.of(11, 6)))
      .addFixed(new JavaFixed("CHRISTMAS", HolidayType.OFFICIAL_HOLIDAY, null, null, YearCycle.EVERY_YEAR, List.of(new JavaMovingCondition(DayOfWeek.of(6), With.NEXT, DayOfWeek.of(1)), new JavaMovingCondition(DayOfWeek.of(7), With.NEXT, DayOfWeek.of(1))), MonthDay.of(12, 25)))
      .addFixed(new JavaFixed("BOXING_DAY", HolidayType.OFFICIAL_HOLIDAY, null, null, YearCycle.EVERY_YEAR, List.of(new JavaMovingCondition(DayOfWeek.of(6), With.NEXT, DayOfWeek.of(1)), new JavaMovingCondition(DayOfWeek.of(7), With.NEXT, DayOfWeek.of(2)), new JavaMovingCondition(DayOfWeek.of(1), With.NEXT, DayOfWeek.of(2))), MonthDay.of(12, 26)))
      .addChristianHoliday(new JavaChristianHoliday("christian.ASH_WEDNESDAY", HolidayType.OFFICIAL_HOLIDAY, null, null, YearCycle.EVERY_YEAR, null, ChristianHolidayType.ASH_WEDNESDAY, Chronology.of("ISO")))
      .addChristianHoliday(new JavaChristianHoliday("christian.GOOD_FRIDAY", HolidayType.OFFICIAL_HOLIDAY, null, null, YearCycle.EVERY_YEAR, null, ChristianHolidayType.GOOD_FRIDAY, Chronology.of("ISO")))
      .addChristianHoliday(new JavaChristianHoliday("christian.EASTER_MONDAY", HolidayType.OFFICIAL_HOLIDAY, null, null, YearCycle.EVERY_YEAR, null, ChristianHolidayType.EASTER_MONDAY, Chronology.of("ISO")))
      .addFixedWeekdayBetweenFixed(new JavaFixedWeekdayBetweenFixed("REMEMBRANCE", HolidayType.OFFICIAL_HOLIDAY, Year.of(1919), null, YearCycle.EVERY_YEAR, new JavaFixed("null", HolidayType.OFFICIAL_HOLIDAY, null, null, YearCycle.EVERY_YEAR, null, MonthDay.of(11, 9)), new JavaFixed("null", HolidayType.OFFICIAL_HOLIDAY, null, null, YearCycle.EVERY_YEAR, null, MonthDay.of(11, 15)), DayOfWeek.of(1)))
      .addFixedWeekday(new JavaFixedWeekdayInMonth("NATIONAL_HEROES_DAY", HolidayType.OFFICIAL_HOLIDAY, null, null, YearCycle.EVERY_YEAR, DayOfWeek.of(1), Month.of(1), Occurrance.FOURTH))
      .addFixedWeekday(new JavaFixedWeekdayInMonth("ELECTION_DAY", HolidayType.OFFICIAL_HOLIDAY, Year.of(2021), null, YearCycle.FOUR_YEARS, DayOfWeek.of(3), Month.of(4), Occurrance.SECOND))
      .addFixedWeekday(new JavaFixedWeekdayInMonth("DISCOVERY_DAY", HolidayType.OFFICIAL_HOLIDAY, null, null, YearCycle.EVERY_YEAR, DayOfWeek.of(1), Month.of(5), Occurrance.THIRD))
      .addFixedWeekday(new JavaFixedWeekdayInMonth("ELECTION_DAY", HolidayType.OFFICIAL_HOLIDAY, Year.of(2013), Year.of(2017), YearCycle.FOUR_YEARS, DayOfWeek.of(3), Month.of(5), Occurrance.FOURTH))
      .addFixedWeekday(new JavaFixedWeekdayInMonth("QUEENS_BIRTHDAY", HolidayType.OFFICIAL_HOLIDAY, Year.of(1952), Year.of(2009), YearCycle.EVERY_YEAR, DayOfWeek.of(1), Month.of(6), Occurrance.THIRD))
      .addFixedWeekday(new JavaFixedWeekdayInMonth("QUEENS_BIRTHDAY", HolidayType.OFFICIAL_HOLIDAY, Year.of(2010), Year.of(2011), YearCycle.EVERY_YEAR, DayOfWeek.of(1), Month.of(6), Occurrance.SECOND))
      .addFixedWeekday(new JavaFixedWeekdayInMonth("QUEENS_BIRTHDAY", HolidayType.OFFICIAL_HOLIDAY, Year.of(2012), Year.of(2015), YearCycle.EVERY_YEAR, DayOfWeek.of(1), Month.of(6), Occurrance.THIRD))
      .addFixedWeekday(new JavaFixedWeekdayInMonth("QUEENS_BIRTHDAY", HolidayType.OFFICIAL_HOLIDAY, Year.of(2016), Year.of(2016), YearCycle.EVERY_YEAR, DayOfWeek.of(1), Month.of(6), Occurrance.SECOND))
      .addFixedWeekday(new JavaFixedWeekdayInMonth("QUEENS_BIRTHDAY", HolidayType.OFFICIAL_HOLIDAY, Year.of(2017), Year.of(2017), YearCycle.EVERY_YEAR, DayOfWeek.of(1), Month.of(6), Occurrance.THIRD))
      .addFixedWeekday(new JavaFixedWeekdayInMonth("QUEENS_BIRTHDAY", HolidayType.OFFICIAL_HOLIDAY, Year.of(2018), Year.of(2019), YearCycle.EVERY_YEAR, DayOfWeek.of(1), Month.of(6), Occurrance.SECOND))
      .addFixedWeekday(new JavaFixedWeekdayInMonth("QUEENS_BIRTHDAY", HolidayType.OFFICIAL_HOLIDAY, Year.of(2020), Year.of(2020), YearCycle.EVERY_YEAR, DayOfWeek.of(1), Month.of(6), Occurrance.THIRD))
      .addFixedWeekday(new JavaFixedWeekdayInMonth("QUEENS_BIRTHDAY", HolidayType.OFFICIAL_HOLIDAY, Year.of(2021), Year.of(2021), YearCycle.EVERY_YEAR, DayOfWeek.of(1), Month.of(6), Occurrance.SECOND))
      .addFixedWeekday(new JavaFixedWeekdayInMonth("QUEENS_BIRTHDAY", HolidayType.OFFICIAL_HOLIDAY, Year.of(2022), Year.of(2022), YearCycle.EVERY_YEAR, DayOfWeek.of(1), Month.of(6), Occurrance.FIRST))
      .addFixedWeekday(new JavaFixedWeekdayInMonth("CONSTITUTION_DAY", HolidayType.OFFICIAL_HOLIDAY, null, null, YearCycle.EVERY_YEAR, DayOfWeek.of(1), Month.of(7), Occurrance.FIRST))
      .addFixedWeekday(new JavaFixedWeekdayInMonth("CAYMAN_THANKSGIVING", HolidayType.OFFICIAL_HOLIDAY, Year.of(2023), null, YearCycle.EVERY_YEAR, DayOfWeek.of(7), Month.of(12), Occurrance.FIRST))
      , null, "ky", "Cayman Islands");
  }
}
