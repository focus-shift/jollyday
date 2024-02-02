package de.focus_shift.jollyday.pojo.holidays;

import java.time.DayOfWeek;
import java.time.Month;
import java.time.MonthDay;
import java.time.chrono.Chronology;
import java.util.List;

import de.focus_shift.jollyday.core.HolidayType;
import de.focus_shift.jollyday.core.spi.ChristianHolidayType;
import de.focus_shift.jollyday.core.spi.Occurrance;
import de.focus_shift.jollyday.core.spi.Relation;
import de.focus_shift.jollyday.core.spi.YearCycle;
import de.focus_shift.jollyday.pojo.JavaChristianHoliday;
import de.focus_shift.jollyday.pojo.JavaConfiguration;
import de.focus_shift.jollyday.pojo.JavaFixed;
import de.focus_shift.jollyday.pojo.JavaFixedWeekdayInMonth;
import de.focus_shift.jollyday.pojo.JavaHolidays;
import de.focus_shift.jollyday.pojo.JavaRelativeToFixed;

public class Holiday_ca {

  public static JavaConfiguration configuration;

  static {
    configuration = new JavaConfiguration(new JavaHolidays()
      .addFixed(new JavaFixed("NEW_YEAR", HolidayType.OFFICIAL_HOLIDAY, null, null, YearCycle.EVERY_YEAR, null, MonthDay.of(1, 1)))
      .addFixed(new JavaFixed("NATIONAL_DAY", HolidayType.OFFICIAL_HOLIDAY, null, null, YearCycle.EVERY_YEAR, null, MonthDay.of(7, 1)))
      .addFixed(new JavaFixed("CHRISTMAS", HolidayType.OFFICIAL_HOLIDAY, null, null, YearCycle.EVERY_YEAR, null, MonthDay.of(12, 25)))
      .addChristianHoliday(new JavaChristianHoliday("christian.GOOD_FRIDAY", HolidayType.OFFICIAL_HOLIDAY, null, null, YearCycle.EVERY_YEAR, null, ChristianHolidayType.GOOD_FRIDAY, Chronology.of("ISO")))
      .addChristianHoliday(new JavaChristianHoliday("christian.EASTER", HolidayType.OFFICIAL_HOLIDAY, null, null, YearCycle.EVERY_YEAR, null, ChristianHolidayType.EASTER, Chronology.of("ISO")))
      .addFixedWeekday(new JavaFixedWeekdayInMonth("LABOUR_DAY", HolidayType.OFFICIAL_HOLIDAY, null, null, YearCycle.EVERY_YEAR, DayOfWeek.of(1), Month.of(9), Occurrance.FIRST))
      , List.of(new JavaConfiguration(new JavaHolidays()
      .addFixed(new JavaFixed("BOXING_DAY", HolidayType.OFFICIAL_HOLIDAY, null, null, YearCycle.EVERY_YEAR, null, MonthDay.of(12, 26)))
      .addFixedWeekday(new JavaFixedWeekdayInMonth("FAMILY_DAY", HolidayType.OFFICIAL_HOLIDAY, null, null, YearCycle.EVERY_YEAR, DayOfWeek.of(1), Month.of(2), Occurrance.THIRD))
      .addFixedWeekday(new JavaFixedWeekdayInMonth("THANKSGIVING", HolidayType.OFFICIAL_HOLIDAY, null, null, YearCycle.EVERY_YEAR, DayOfWeek.of(1), Month.of(10), Occurrance.SECOND))
      .addRelativeToFixed(new JavaRelativeToFixed("VICTORIA_DAY", HolidayType.OFFICIAL_HOLIDAY, null, null, YearCycle.EVERY_YEAR, new JavaFixed("null", HolidayType.OFFICIAL_HOLIDAY, null, null, YearCycle.EVERY_YEAR, null, MonthDay.of(5, 25)), DayOfWeek.of(1), Relation.BEFORE, null))
      , null, "on", "Ontario"), new JavaConfiguration(new JavaHolidays()
      .addFixed(new JavaFixed("REMEMBRANCE", HolidayType.OFFICIAL_HOLIDAY, null, null, YearCycle.EVERY_YEAR, null, MonthDay.of(11, 11)))
      .addFixedWeekday(new JavaFixedWeekdayInMonth("THANKSGIVING", HolidayType.OFFICIAL_HOLIDAY, null, null, YearCycle.EVERY_YEAR, DayOfWeek.of(1), Month.of(10), Occurrance.SECOND))
      .addRelativeToFixed(new JavaRelativeToFixed("VICTORIA_DAY", HolidayType.OFFICIAL_HOLIDAY, null, null, YearCycle.EVERY_YEAR, new JavaFixed("null", HolidayType.OFFICIAL_HOLIDAY, null, null, YearCycle.EVERY_YEAR, null, MonthDay.of(5, 25)), DayOfWeek.of(1), Relation.BEFORE, null))
      , null, "yt", "Yukon"), new JavaConfiguration(new JavaHolidays()
      .addFixed(new JavaFixed("SAINT_JEAN_BAPTISTE_DAY", HolidayType.OFFICIAL_HOLIDAY, null, null, YearCycle.EVERY_YEAR, null, MonthDay.of(6, 24)))
      .addChristianHoliday(new JavaChristianHoliday("christian.EASTER_MONDAY", HolidayType.OFFICIAL_HOLIDAY, null, null, YearCycle.EVERY_YEAR, null, ChristianHolidayType.EASTER_MONDAY, Chronology.of("ISO")))
      .addFixedWeekday(new JavaFixedWeekdayInMonth("THANKSGIVING", HolidayType.OFFICIAL_HOLIDAY, null, null, YearCycle.EVERY_YEAR, DayOfWeek.of(1), Month.of(10), Occurrance.SECOND))
      .addRelativeToFixed(new JavaRelativeToFixed("VICTORIA_DAY", HolidayType.OFFICIAL_HOLIDAY, null, null, YearCycle.EVERY_YEAR, new JavaFixed("null", HolidayType.OFFICIAL_HOLIDAY, null, null, YearCycle.EVERY_YEAR, null, MonthDay.of(5, 25)), DayOfWeek.of(1), Relation.BEFORE, null))
      , null, "qc", "Quebec"), new JavaConfiguration(new JavaHolidays()
      .addFixed(new JavaFixed("REMEMBRANCE", HolidayType.OFFICIAL_HOLIDAY, null, null, YearCycle.EVERY_YEAR, null, MonthDay.of(11, 11)))
      , null, "ns", "Nova Scotia"), new JavaConfiguration(new JavaHolidays()
      .addFixed(new JavaFixed("REMEMBRANCE", HolidayType.OFFICIAL_HOLIDAY, null, null, YearCycle.EVERY_YEAR, null, MonthDay.of(11, 11)))
      .addFixedWeekday(new JavaFixedWeekdayInMonth("NATIONAL_DAY", HolidayType.OFFICIAL_HOLIDAY, null, null, YearCycle.EVERY_YEAR, DayOfWeek.of(1), Month.of(8), Occurrance.FIRST))
      , null, "nb", "New Brunswick"), new JavaConfiguration(new JavaHolidays()
      .addFixed(new JavaFixed("REMEMBRANCE", HolidayType.OFFICIAL_HOLIDAY, null, null, YearCycle.EVERY_YEAR, null, MonthDay.of(11, 11)))
      .addFixedWeekday(new JavaFixedWeekdayInMonth("THANKSGIVING", HolidayType.OFFICIAL_HOLIDAY, null, null, YearCycle.EVERY_YEAR, DayOfWeek.of(1), Month.of(10), Occurrance.SECOND))
      .addFixedWeekday(new JavaFixedWeekdayInMonth("FAMILY_DAY", HolidayType.OFFICIAL_HOLIDAY, null, null, YearCycle.EVERY_YEAR, DayOfWeek.of(1), Month.of(2), Occurrance.THIRD))
      .addRelativeToFixed(new JavaRelativeToFixed("VICTORIA_DAY", HolidayType.OFFICIAL_HOLIDAY, null, null, YearCycle.EVERY_YEAR, new JavaFixed("null", HolidayType.OFFICIAL_HOLIDAY, null, null, YearCycle.EVERY_YEAR, null, MonthDay.of(5, 25)), DayOfWeek.of(1), Relation.BEFORE, null))
      , null, "mb", "Manitoba"), new JavaConfiguration(new JavaHolidays()
      .addFixed(new JavaFixed("REMEMBRANCE", HolidayType.OFFICIAL_HOLIDAY, null, null, YearCycle.EVERY_YEAR, null, MonthDay.of(11, 11)))
      .addFixedWeekday(new JavaFixedWeekdayInMonth("THANKSGIVING", HolidayType.OFFICIAL_HOLIDAY, null, null, YearCycle.EVERY_YEAR, DayOfWeek.of(1), Month.of(10), Occurrance.SECOND))
      .addFixedWeekday(new JavaFixedWeekdayInMonth("CIVIC", HolidayType.OFFICIAL_HOLIDAY, null, null, YearCycle.EVERY_YEAR, DayOfWeek.of(1), Month.of(8), Occurrance.FIRST))
      .addRelativeToFixed(new JavaRelativeToFixed("VICTORIA_DAY", HolidayType.OFFICIAL_HOLIDAY, null, null, YearCycle.EVERY_YEAR, new JavaFixed("null", HolidayType.OFFICIAL_HOLIDAY, null, null, YearCycle.EVERY_YEAR, null, MonthDay.of(5, 25)), DayOfWeek.of(1), Relation.BEFORE, null))
      , null, "nt", "Northwest Territories"), new JavaConfiguration(new JavaHolidays()
      .addFixed(new JavaFixed("REMEMBRANCE", HolidayType.OFFICIAL_HOLIDAY, null, null, YearCycle.EVERY_YEAR, null, MonthDay.of(11, 11)))
      .addFixedWeekday(new JavaFixedWeekdayInMonth("THANKSGIVING", HolidayType.OFFICIAL_HOLIDAY, null, null, YearCycle.EVERY_YEAR, DayOfWeek.of(1), Month.of(10), Occurrance.SECOND))
      .addFixedWeekday(new JavaFixedWeekdayInMonth("CIVIC", HolidayType.OFFICIAL_HOLIDAY, null, null, YearCycle.EVERY_YEAR, DayOfWeek.of(1), Month.of(8), Occurrance.FIRST))
      .addRelativeToFixed(new JavaRelativeToFixed("VICTORIA_DAY", HolidayType.OFFICIAL_HOLIDAY, null, null, YearCycle.EVERY_YEAR, new JavaFixed("null", HolidayType.OFFICIAL_HOLIDAY, null, null, YearCycle.EVERY_YEAR, null, MonthDay.of(5, 25)), DayOfWeek.of(1), Relation.BEFORE, null))
      , null, "nu", "Nunavut"), new JavaConfiguration(new JavaHolidays()
      .addFixed(new JavaFixed("REMEMBRANCE", HolidayType.OFFICIAL_HOLIDAY, null, null, YearCycle.EVERY_YEAR, null, MonthDay.of(11, 11)))
      .addFixedWeekday(new JavaFixedWeekdayInMonth("CIVIC", HolidayType.OFFICIAL_HOLIDAY, null, null, YearCycle.EVERY_YEAR, DayOfWeek.of(1), Month.of(8), Occurrance.FIRST))
      .addFixedWeekday(new JavaFixedWeekdayInMonth("THANKSGIVING", HolidayType.OFFICIAL_HOLIDAY, null, null, YearCycle.EVERY_YEAR, DayOfWeek.of(1), Month.of(10), Occurrance.SECOND))
      .addRelativeToFixed(new JavaRelativeToFixed("VICTORIA_DAY", HolidayType.OFFICIAL_HOLIDAY, null, null, YearCycle.EVERY_YEAR, new JavaFixed("null", HolidayType.OFFICIAL_HOLIDAY, null, null, YearCycle.EVERY_YEAR, null, MonthDay.of(5, 25)), DayOfWeek.of(1), Relation.BEFORE, null))
      , null, "bc", "British Columbia"), new JavaConfiguration(new JavaHolidays()
      .addFixed(new JavaFixed("REMEMBRANCE", HolidayType.OFFICIAL_HOLIDAY, null, null, YearCycle.EVERY_YEAR, null, MonthDay.of(11, 11)))
      , null, "pe", "Prince Edward Island"), new JavaConfiguration(new JavaHolidays()
      .addFixed(new JavaFixed("REMEMBRANCE", HolidayType.OFFICIAL_HOLIDAY, null, null, YearCycle.EVERY_YEAR, null, MonthDay.of(11, 11)))
      .addFixedWeekday(new JavaFixedWeekdayInMonth("FAMILY_DAY", HolidayType.OFFICIAL_HOLIDAY, null, null, YearCycle.EVERY_YEAR, DayOfWeek.of(1), Month.of(2), Occurrance.THIRD))
      .addFixedWeekday(new JavaFixedWeekdayInMonth("THANKSGIVING", HolidayType.OFFICIAL_HOLIDAY, null, null, YearCycle.EVERY_YEAR, DayOfWeek.of(1), Month.of(10), Occurrance.SECOND))
      .addFixedWeekday(new JavaFixedWeekdayInMonth("NATIONAL_DAY", HolidayType.OFFICIAL_HOLIDAY, null, null, YearCycle.EVERY_YEAR, DayOfWeek.of(1), Month.of(8), Occurrance.FIRST))
      .addRelativeToFixed(new JavaRelativeToFixed("VICTORIA_DAY", HolidayType.OFFICIAL_HOLIDAY, null, null, YearCycle.EVERY_YEAR, new JavaFixed("null", HolidayType.OFFICIAL_HOLIDAY, null, null, YearCycle.EVERY_YEAR, null, MonthDay.of(5, 25)), DayOfWeek.of(1), Relation.BEFORE, null))
      , null, "sk", "Saskatchewan"), new JavaConfiguration(new JavaHolidays()
      .addFixed(new JavaFixed("REMEMBRANCE", HolidayType.OFFICIAL_HOLIDAY, null, null, YearCycle.EVERY_YEAR, null, MonthDay.of(11, 11)))
      .addFixedWeekday(new JavaFixedWeekdayInMonth("HERITAGE", HolidayType.OFFICIAL_HOLIDAY, null, null, YearCycle.EVERY_YEAR, DayOfWeek.of(1), Month.of(8), Occurrance.FIRST))
      .addFixedWeekday(new JavaFixedWeekdayInMonth("FAMILY_DAY", HolidayType.OFFICIAL_HOLIDAY, null, null, YearCycle.EVERY_YEAR, DayOfWeek.of(1), Month.of(2), Occurrance.THIRD))
      .addFixedWeekday(new JavaFixedWeekdayInMonth("THANKSGIVING", HolidayType.OFFICIAL_HOLIDAY, null, null, YearCycle.EVERY_YEAR, DayOfWeek.of(1), Month.of(10), Occurrance.SECOND))
      .addRelativeToFixed(new JavaRelativeToFixed("VICTORIA_DAY", HolidayType.OFFICIAL_HOLIDAY, null, null, YearCycle.EVERY_YEAR, new JavaFixed("null", HolidayType.OFFICIAL_HOLIDAY, null, null, YearCycle.EVERY_YEAR, null, MonthDay.of(5, 25)), DayOfWeek.of(1), Relation.BEFORE, null))
      , null, "ab", "Alberta"), new JavaConfiguration(new JavaHolidays()
      .addFixed(new JavaFixed("REMEMBRANCE", HolidayType.OFFICIAL_HOLIDAY, null, null, YearCycle.EVERY_YEAR, null, MonthDay.of(11, 11)))
      , null, "nl", "Newfoundland and Labrador")), "ca", "Canada");
  }
}
