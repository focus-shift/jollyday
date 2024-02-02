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

public class Holiday_za {

  public static JavaConfiguration configuration;

  static {
    configuration = new JavaConfiguration(new JavaHolidays()
      .addFixed(new JavaFixed("ELECTION_DAY", HolidayType.OFFICIAL_HOLIDAY, Year.of(1999), Year.of(1999), YearCycle.EVERY_YEAR, null, MonthDay.of(6, 2)))
      .addFixed(new JavaFixed("ELECTION_DAY", HolidayType.OFFICIAL_HOLIDAY, Year.of(2004), Year.of(2004), YearCycle.EVERY_YEAR, null, MonthDay.of(4, 14)))
      .addFixed(new JavaFixed("ELECTION_DAY", HolidayType.OFFICIAL_HOLIDAY, Year.of(2006), Year.of(2006), YearCycle.EVERY_YEAR, null, MonthDay.of(3, 1)))
      .addFixed(new JavaFixed("ELECTION_DAY", HolidayType.OFFICIAL_HOLIDAY, Year.of(2009), Year.of(2009), YearCycle.EVERY_YEAR, null, MonthDay.of(4, 22)))
      .addFixed(new JavaFixed("ELECTION_DAY", HolidayType.OFFICIAL_HOLIDAY, Year.of(2011), Year.of(2011), YearCycle.EVERY_YEAR, null, MonthDay.of(5, 18)))
      .addFixed(new JavaFixed("ELECTION_DAY", HolidayType.OFFICIAL_HOLIDAY, Year.of(2014), Year.of(2014), YearCycle.EVERY_YEAR, null, MonthDay.of(5, 7)))
      .addFixed(new JavaFixed("ELECTION_DAY", HolidayType.OFFICIAL_HOLIDAY, Year.of(2016), Year.of(2016), YearCycle.EVERY_YEAR, null, MonthDay.of(8, 3)))
      .addFixed(new JavaFixed("ELECTION_DAY", HolidayType.OFFICIAL_HOLIDAY, Year.of(2019), Year.of(2019), YearCycle.EVERY_YEAR, null, MonthDay.of(5, 8)))
      .addFixed(new JavaFixed("NEW_YEAR", HolidayType.OFFICIAL_HOLIDAY, Year.of(1999), Year.of(1999), YearCycle.EVERY_YEAR, List.of(new JavaMovingCondition(DayOfWeek.of(7), With.NEXT, DayOfWeek.of(1))), MonthDay.of(12, 31)))
      .addFixed(new JavaFixed("NEW_YEAR", HolidayType.OFFICIAL_HOLIDAY, Year.of(2000), Year.of(2000), YearCycle.EVERY_YEAR, List.of(new JavaMovingCondition(DayOfWeek.of(7), With.NEXT, DayOfWeek.of(1))), MonthDay.of(1, 2)))
      .addFixed(new JavaFixed("HUMAN_RIGHTS", HolidayType.OFFICIAL_HOLIDAY, Year.of(2008), Year.of(2008), YearCycle.EVERY_YEAR, List.of(new JavaMovingCondition(DayOfWeek.of(7), With.NEXT, DayOfWeek.of(1))), MonthDay.of(5, 2)))
      .addFixed(new JavaFixed("NEW_YEAR", HolidayType.OFFICIAL_HOLIDAY, Year.of(2011), Year.of(2011), YearCycle.EVERY_YEAR, List.of(new JavaMovingCondition(DayOfWeek.of(7), With.NEXT, DayOfWeek.of(1))), MonthDay.of(12, 27)))
      .addFixed(new JavaFixed("NEW_YEAR", HolidayType.OFFICIAL_HOLIDAY, Year.of(2016), Year.of(2016), YearCycle.EVERY_YEAR, List.of(new JavaMovingCondition(DayOfWeek.of(7), With.NEXT, DayOfWeek.of(1))), MonthDay.of(12, 27)))
      .addFixed(new JavaFixed("NEW_YEAR", HolidayType.OFFICIAL_HOLIDAY, null, null, YearCycle.EVERY_YEAR, List.of(new JavaMovingCondition(DayOfWeek.of(7), With.NEXT, DayOfWeek.of(1))), MonthDay.of(1, 1)))
      .addFixed(new JavaFixed("HUMAN_RIGHTS", HolidayType.OFFICIAL_HOLIDAY, null, null, YearCycle.EVERY_YEAR, List.of(new JavaMovingCondition(DayOfWeek.of(7), With.NEXT, DayOfWeek.of(1))), MonthDay.of(3, 21)))
      .addFixed(new JavaFixed("RIEBEECK", HolidayType.OFFICIAL_HOLIDAY, Year.of(1952), Year.of(1974), YearCycle.EVERY_YEAR, null, MonthDay.of(4, 6)))
      .addFixed(new JavaFixed("FOUNDATION", HolidayType.OFFICIAL_HOLIDAY, Year.of(1980), Year.of(1994), YearCycle.EVERY_YEAR, null, MonthDay.of(4, 6)))
      .addFixed(new JavaFixed("FREEDOM", HolidayType.OFFICIAL_HOLIDAY, null, null, YearCycle.EVERY_YEAR, List.of(new JavaMovingCondition(DayOfWeek.of(7), With.NEXT, DayOfWeek.of(1))), MonthDay.of(4, 27)))
      .addFixed(new JavaFixed("LABOUR_DAY", HolidayType.OFFICIAL_HOLIDAY, Year.of(1990), null, YearCycle.EVERY_YEAR, List.of(new JavaMovingCondition(DayOfWeek.of(7), With.NEXT, DayOfWeek.of(1))), MonthDay.of(5, 1)))
      .addFixed(new JavaFixed("EMPIRE", HolidayType.OFFICIAL_HOLIDAY, Year.of(1910), Year.of(1951), YearCycle.EVERY_YEAR, null, MonthDay.of(5, 24)))
      .addFixed(new JavaFixed("REPUBLIC_DAY", HolidayType.OFFICIAL_HOLIDAY, Year.of(1910), Year.of(1993), YearCycle.EVERY_YEAR, null, MonthDay.of(5, 31)))
      .addFixed(new JavaFixed("YOUTH", HolidayType.OFFICIAL_HOLIDAY, null, null, YearCycle.EVERY_YEAR, List.of(new JavaMovingCondition(DayOfWeek.of(7), With.NEXT, DayOfWeek.of(1))), MonthDay.of(6, 16)))
      .addFixed(new JavaFixed("null", HolidayType.OFFICIAL_HOLIDAY, Year.of(1961), Year.of(1974), YearCycle.EVERY_YEAR, null, MonthDay.of(7, 10)))
      .addFixed(new JavaFixed("INTERNATIONAL_WOMAN", HolidayType.OFFICIAL_HOLIDAY, null, null, YearCycle.EVERY_YEAR, List.of(new JavaMovingCondition(DayOfWeek.of(7), With.NEXT, DayOfWeek.of(1))), MonthDay.of(8, 9)))
      .addFixed(new JavaFixed("HERITAGE", HolidayType.OFFICIAL_HOLIDAY, null, null, YearCycle.EVERY_YEAR, List.of(new JavaMovingCondition(DayOfWeek.of(7), With.NEXT, DayOfWeek.of(1))), MonthDay.of(9, 24)))
      .addFixed(new JavaFixed("KRUGER", HolidayType.OFFICIAL_HOLIDAY, Year.of(1952), Year.of(1993), YearCycle.EVERY_YEAR, null, MonthDay.of(10, 10)))
      .addFixed(new JavaFixed("RECONCILIATION", HolidayType.OFFICIAL_HOLIDAY, null, null, YearCycle.EVERY_YEAR, List.of(new JavaMovingCondition(DayOfWeek.of(7), With.NEXT, DayOfWeek.of(1))), MonthDay.of(12, 16)))
      .addFixed(new JavaFixed("CHRISTMAS", HolidayType.OFFICIAL_HOLIDAY, null, null, YearCycle.EVERY_YEAR, List.of(new JavaMovingCondition(DayOfWeek.of(7), With.NEXT, DayOfWeek.of(1))), MonthDay.of(12, 25)))
      .addFixed(new JavaFixed("GOODWILL", HolidayType.OFFICIAL_HOLIDAY, null, null, YearCycle.EVERY_YEAR, List.of(new JavaMovingCondition(DayOfWeek.of(7), With.NEXT, DayOfWeek.of(1))), MonthDay.of(12, 26)))
      .addChristianHoliday(new JavaChristianHoliday("christian.EASTER", HolidayType.OFFICIAL_HOLIDAY, null, null, YearCycle.EVERY_YEAR, null, ChristianHolidayType.EASTER, Chronology.of("ISO")))
      .addChristianHoliday(new JavaChristianHoliday("christian.GOOD_FRIDAY", HolidayType.OFFICIAL_HOLIDAY, null, null, YearCycle.EVERY_YEAR, null, ChristianHolidayType.GOOD_FRIDAY, Chronology.of("ISO")))
      .addChristianHoliday(new JavaChristianHoliday("christian.EASTER_MONDAY", HolidayType.OFFICIAL_HOLIDAY, null, null, YearCycle.EVERY_YEAR, null, ChristianHolidayType.EASTER_MONDAY, Chronology.of("ISO")))
      .addChristianHoliday(new JavaChristianHoliday("christian.ASCENSION_DAY", HolidayType.OFFICIAL_HOLIDAY, Year.of(1910), Year.of(1993), YearCycle.EVERY_YEAR, null, ChristianHolidayType.ASCENSION_DAY, Chronology.of("ISO")))
      .addFixedWeekday(new JavaFixedWeekdayInMonth("LABOUR_DAY", HolidayType.OFFICIAL_HOLIDAY, Year.of(1987), Year.of(1989), YearCycle.EVERY_YEAR, DayOfWeek.of(5), Month.of(5), Occurrance.FIRST))
      .addFixedWeekday(new JavaFixedWeekdayInMonth("QUEENS_BIRTHDAY", HolidayType.OFFICIAL_HOLIDAY, Year.of(1952), Year.of(1960), YearCycle.EVERY_YEAR, DayOfWeek.of(1), Month.of(7), Occurrance.SECOND))
      .addFixedWeekday(new JavaFixedWeekdayInMonth("KINGS_DAY", HolidayType.OFFICIAL_HOLIDAY, null, Year.of(1951), YearCycle.EVERY_YEAR, DayOfWeek.of(1), Month.of(8), Occurrance.FIRST))
      .addFixedWeekday(new JavaFixedWeekdayInMonth("SETTLER", HolidayType.OFFICIAL_HOLIDAY, Year.of(1952), Year.of(1979), YearCycle.EVERY_YEAR, DayOfWeek.of(1), Month.of(9), Occurrance.FIRST))
      , null, "za", "South Africa");
  }
}
