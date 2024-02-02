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

public class Holiday_jp {

  public static JavaConfiguration configuration;

  static {
    configuration = new JavaConfiguration(new JavaHolidays()
      .addFixed(new JavaFixed("NEW_YEAR", HolidayType.OFFICIAL_HOLIDAY, Year.of(1948), null, YearCycle.EVERY_YEAR, null, MonthDay.of(1, 1)))
      .addFixed(new JavaFixed("COMING_OF_AGE", HolidayType.OFFICIAL_HOLIDAY, Year.of(1948), Year.of(1999), YearCycle.EVERY_YEAR, null, MonthDay.of(1, 15)))
      .addFixed(new JavaFixed("FOUNDATION", HolidayType.OFFICIAL_HOLIDAY, Year.of(1967), null, YearCycle.EVERY_YEAR, null, MonthDay.of(2, 11)))
      .addFixed(new JavaFixed("SHOWA_DAY", HolidayType.OFFICIAL_HOLIDAY, null, null, YearCycle.EVERY_YEAR, null, MonthDay.of(4, 29)))
      .addFixed(new JavaFixed("CONSTITUTION_DAY", HolidayType.OFFICIAL_HOLIDAY, Year.of(1948), null, YearCycle.EVERY_YEAR, null, MonthDay.of(5, 3)))
      .addFixed(new JavaFixed("GREENERY_DAY", HolidayType.OFFICIAL_HOLIDAY, Year.of(2007), null, YearCycle.EVERY_YEAR, null, MonthDay.of(5, 4)))
      .addFixed(new JavaFixed("CHILDRENS_DAY", HolidayType.OFFICIAL_HOLIDAY, Year.of(1948), null, YearCycle.EVERY_YEAR, null, MonthDay.of(5, 5)))
      .addFixed(new JavaFixed("NAVY_DAY", HolidayType.OFFICIAL_HOLIDAY, Year.of(1996), Year.of(2002), YearCycle.EVERY_YEAR, null, MonthDay.of(7, 20)))
      .addFixed(new JavaFixed("RESPECT_AGED_DAY", HolidayType.OFFICIAL_HOLIDAY, Year.of(1966), Year.of(2002), YearCycle.EVERY_YEAR, null, MonthDay.of(9, 15)))
      .addFixed(new JavaFixed("HEALTH_SPORTS", HolidayType.OFFICIAL_HOLIDAY, Year.of(1966), Year.of(1999), YearCycle.EVERY_YEAR, null, MonthDay.of(10, 10)))
      .addFixed(new JavaFixed("CULTURE_DAY", HolidayType.OFFICIAL_HOLIDAY, Year.of(1948), null, YearCycle.EVERY_YEAR, List.of(new JavaMovingCondition(DayOfWeek.of(7), With.NEXT, DayOfWeek.of(1))), MonthDay.of(11, 3)))
      .addFixed(new JavaFixed("LABOUR_DAY", HolidayType.OFFICIAL_HOLIDAY, Year.of(1948), null, YearCycle.EVERY_YEAR, null, MonthDay.of(11, 23)))
      .addFixed(new JavaFixed("EMPERORS_BIRTHDAY", HolidayType.OFFICIAL_HOLIDAY, Year.of(1990), null, YearCycle.EVERY_YEAR, null, MonthDay.of(12, 23)))
      .addFixed(new JavaFixed("IMPERIAL_DAY", HolidayType.OFFICIAL_HOLIDAY, Year.of(1959), Year.of(1959), YearCycle.EVERY_YEAR, null, MonthDay.of(4, 10)))
      .addFixed(new JavaFixed("IMPERIAL_DAY", HolidayType.OFFICIAL_HOLIDAY, Year.of(1989), Year.of(1989), YearCycle.EVERY_YEAR, null, MonthDay.of(2, 24)))
      .addFixed(new JavaFixed("IMPERIAL_DAY", HolidayType.OFFICIAL_HOLIDAY, Year.of(1990), Year.of(1990), YearCycle.EVERY_YEAR, null, MonthDay.of(11, 12)))
      .addFixed(new JavaFixed("IMPERIAL_DAY", HolidayType.OFFICIAL_HOLIDAY, Year.of(1993), Year.of(1993), YearCycle.EVERY_YEAR, null, MonthDay.of(6, 9)))
      .addFixed(new JavaFixed("MOUNTAIN_DAY", HolidayType.OFFICIAL_HOLIDAY, Year.of(2016), null, YearCycle.EVERY_YEAR, null, MonthDay.of(8, 11)))
      .addFixedWeekday(new JavaFixedWeekdayInMonth("COMING_OF_AGE", HolidayType.OFFICIAL_HOLIDAY, Year.of(2000), null, YearCycle.EVERY_YEAR, DayOfWeek.of(1), Month.of(1), Occurrance.SECOND))
      .addFixedWeekday(new JavaFixedWeekdayInMonth("NAVY_DAY", HolidayType.OFFICIAL_HOLIDAY, Year.of(2003), null, YearCycle.EVERY_YEAR, DayOfWeek.of(1), Month.of(7), Occurrance.THIRD))
      .addFixedWeekday(new JavaFixedWeekdayInMonth("RESPECT_AGED_DAY", HolidayType.OFFICIAL_HOLIDAY, Year.of(2003), null, YearCycle.EVERY_YEAR, DayOfWeek.of(1), Month.of(9), Occurrance.THIRD))
      .addFixedWeekday(new JavaFixedWeekdayInMonth("HEALTH_SPORTS", HolidayType.OFFICIAL_HOLIDAY, Year.of(2000), null, YearCycle.EVERY_YEAR, DayOfWeek.of(1), Month.of(10), Occurrance.SECOND))
      , null, "jp", "Japan");
  }
}
