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
import de.focus_shift.jollyday.core.spi.Relation;
import de.focus_shift.jollyday.core.spi.With;
import de.focus_shift.jollyday.core.spi.YearCycle;
import de.focus_shift.jollyday.pojo.JavaChristianHoliday;
import de.focus_shift.jollyday.pojo.JavaConfiguration;
import de.focus_shift.jollyday.pojo.JavaFixed;
import de.focus_shift.jollyday.pojo.JavaFixedWeekdayInMonth;
import de.focus_shift.jollyday.pojo.JavaFixedWeekdayRelativeToFixed;
import de.focus_shift.jollyday.pojo.JavaHolidays;
import de.focus_shift.jollyday.pojo.JavaMovingCondition;
import de.focus_shift.jollyday.pojo.JavaRelativeToWeekdayInMonth;

public class Holiday_nz {

  public static JavaConfiguration configuration;

  static {
    configuration = new JavaConfiguration(new JavaHolidays()
      .addFixed(new JavaFixed("NEW_YEAR", HolidayType.OFFICIAL_HOLIDAY, null, null, YearCycle.EVERY_YEAR, List.of(new JavaMovingCondition(DayOfWeek.of(6), With.NEXT, DayOfWeek.of(1)), new JavaMovingCondition(DayOfWeek.of(7), With.NEXT, DayOfWeek.of(1))), MonthDay.of(1, 1)))
      .addFixed(new JavaFixed("DAY_AFTER_NEW_YEAR", HolidayType.OFFICIAL_HOLIDAY, null, null, YearCycle.EVERY_YEAR, List.of(new JavaMovingCondition(DayOfWeek.of(6), With.NEXT, DayOfWeek.of(1)), new JavaMovingCondition(DayOfWeek.of(7), With.NEXT, DayOfWeek.of(2))), MonthDay.of(1, 2)))
      .addFixed(new JavaFixed("WAITANGI_DAY", HolidayType.OFFICIAL_HOLIDAY, null, Year.of(2015), YearCycle.EVERY_YEAR, null, MonthDay.of(2, 6)))
      .addFixed(new JavaFixed("WAITANGI_DAY", HolidayType.OFFICIAL_HOLIDAY, Year.of(2016), null, YearCycle.EVERY_YEAR, List.of(new JavaMovingCondition(DayOfWeek.of(6), With.NEXT, DayOfWeek.of(1)), new JavaMovingCondition(DayOfWeek.of(7), With.NEXT, DayOfWeek.of(1))), MonthDay.of(2, 6)))
      .addFixed(new JavaFixed("ANZAC", HolidayType.OFFICIAL_HOLIDAY, null, Year.of(2014), YearCycle.EVERY_YEAR, null, MonthDay.of(4, 25)))
      .addFixed(new JavaFixed("ANZAC", HolidayType.OFFICIAL_HOLIDAY, Year.of(2015), null, YearCycle.EVERY_YEAR, List.of(new JavaMovingCondition(DayOfWeek.of(6), With.NEXT, DayOfWeek.of(1)), new JavaMovingCondition(DayOfWeek.of(7), With.NEXT, DayOfWeek.of(1))), MonthDay.of(4, 25)))
      .addFixed(new JavaFixed("QUEEN_ELIZABETH_II_MEMORIAL_DAY", HolidayType.OFFICIAL_HOLIDAY, Year.of(2022), Year.of(2022), YearCycle.EVERY_YEAR, null, MonthDay.of(9, 26)))
      .addFixed(new JavaFixed("CHRISTMAS", HolidayType.OFFICIAL_HOLIDAY, null, null, YearCycle.EVERY_YEAR, List.of(new JavaMovingCondition(DayOfWeek.of(6), With.NEXT, DayOfWeek.of(1)), new JavaMovingCondition(DayOfWeek.of(7), With.NEXT, DayOfWeek.of(1))), MonthDay.of(12, 25)))
      .addFixed(new JavaFixed("BOXING_DAY", HolidayType.OFFICIAL_HOLIDAY, null, null, YearCycle.EVERY_YEAR, List.of(new JavaMovingCondition(DayOfWeek.of(6), With.NEXT, DayOfWeek.of(1)), new JavaMovingCondition(DayOfWeek.of(7), With.NEXT, DayOfWeek.of(2))), MonthDay.of(12, 26)))
      .addFixed(new JavaFixed("MATARIKI", HolidayType.OFFICIAL_HOLIDAY, Year.of(2022), Year.of(2022), YearCycle.EVERY_YEAR, null, MonthDay.of(6, 24)))
      .addFixed(new JavaFixed("MATARIKI", HolidayType.OFFICIAL_HOLIDAY, Year.of(2023), Year.of(2023), YearCycle.EVERY_YEAR, null, MonthDay.of(7, 14)))
      .addFixed(new JavaFixed("MATARIKI", HolidayType.OFFICIAL_HOLIDAY, Year.of(2024), Year.of(2024), YearCycle.EVERY_YEAR, null, MonthDay.of(6, 28)))
      .addFixed(new JavaFixed("MATARIKI", HolidayType.OFFICIAL_HOLIDAY, Year.of(2025), Year.of(2025), YearCycle.EVERY_YEAR, null, MonthDay.of(6, 20)))
      .addFixed(new JavaFixed("MATARIKI", HolidayType.OFFICIAL_HOLIDAY, Year.of(2026), Year.of(2026), YearCycle.EVERY_YEAR, null, MonthDay.of(7, 10)))
      .addFixed(new JavaFixed("MATARIKI", HolidayType.OFFICIAL_HOLIDAY, Year.of(2027), Year.of(2027), YearCycle.EVERY_YEAR, null, MonthDay.of(6, 25)))
      .addFixed(new JavaFixed("MATARIKI", HolidayType.OFFICIAL_HOLIDAY, Year.of(2028), Year.of(2028), YearCycle.EVERY_YEAR, null, MonthDay.of(7, 14)))
      .addFixed(new JavaFixed("MATARIKI", HolidayType.OFFICIAL_HOLIDAY, Year.of(2029), Year.of(2029), YearCycle.EVERY_YEAR, null, MonthDay.of(7, 6)))
      .addFixed(new JavaFixed("MATARIKI", HolidayType.OFFICIAL_HOLIDAY, Year.of(2030), Year.of(2030), YearCycle.EVERY_YEAR, null, MonthDay.of(6, 21)))
      .addFixed(new JavaFixed("MATARIKI", HolidayType.OFFICIAL_HOLIDAY, Year.of(2031), Year.of(2031), YearCycle.EVERY_YEAR, null, MonthDay.of(7, 11)))
      .addFixed(new JavaFixed("MATARIKI", HolidayType.OFFICIAL_HOLIDAY, Year.of(2032), Year.of(2032), YearCycle.EVERY_YEAR, null, MonthDay.of(7, 2)))
      .addFixed(new JavaFixed("MATARIKI", HolidayType.OFFICIAL_HOLIDAY, Year.of(2033), Year.of(2033), YearCycle.EVERY_YEAR, null, MonthDay.of(6, 24)))
      .addFixed(new JavaFixed("MATARIKI", HolidayType.OFFICIAL_HOLIDAY, Year.of(2034), Year.of(2034), YearCycle.EVERY_YEAR, null, MonthDay.of(7, 7)))
      .addFixed(new JavaFixed("MATARIKI", HolidayType.OFFICIAL_HOLIDAY, Year.of(2035), Year.of(2035), YearCycle.EVERY_YEAR, null, MonthDay.of(6, 29)))
      .addFixed(new JavaFixed("MATARIKI", HolidayType.OFFICIAL_HOLIDAY, Year.of(2036), Year.of(2036), YearCycle.EVERY_YEAR, null, MonthDay.of(7, 18)))
      .addFixed(new JavaFixed("MATARIKI", HolidayType.OFFICIAL_HOLIDAY, Year.of(2037), Year.of(2037), YearCycle.EVERY_YEAR, null, MonthDay.of(7, 10)))
      .addFixed(new JavaFixed("MATARIKI", HolidayType.OFFICIAL_HOLIDAY, Year.of(2038), Year.of(2038), YearCycle.EVERY_YEAR, null, MonthDay.of(6, 25)))
      .addFixed(new JavaFixed("MATARIKI", HolidayType.OFFICIAL_HOLIDAY, Year.of(2039), Year.of(2039), YearCycle.EVERY_YEAR, null, MonthDay.of(7, 15)))
      .addFixed(new JavaFixed("MATARIKI", HolidayType.OFFICIAL_HOLIDAY, Year.of(2040), Year.of(2040), YearCycle.EVERY_YEAR, null, MonthDay.of(7, 6)))
      .addFixed(new JavaFixed("MATARIKI", HolidayType.OFFICIAL_HOLIDAY, Year.of(2041), Year.of(2041), YearCycle.EVERY_YEAR, null, MonthDay.of(7, 19)))
      .addFixed(new JavaFixed("MATARIKI", HolidayType.OFFICIAL_HOLIDAY, Year.of(2042), Year.of(2042), YearCycle.EVERY_YEAR, null, MonthDay.of(7, 11)))
      .addFixed(new JavaFixed("MATARIKI", HolidayType.OFFICIAL_HOLIDAY, Year.of(2043), Year.of(2043), YearCycle.EVERY_YEAR, null, MonthDay.of(7, 3)))
      .addFixed(new JavaFixed("MATARIKI", HolidayType.OFFICIAL_HOLIDAY, Year.of(2044), Year.of(2044), YearCycle.EVERY_YEAR, null, MonthDay.of(6, 24)))
      .addFixed(new JavaFixed("MATARIKI", HolidayType.OFFICIAL_HOLIDAY, Year.of(2045), Year.of(2045), YearCycle.EVERY_YEAR, null, MonthDay.of(7, 7)))
      .addFixed(new JavaFixed("MATARIKI", HolidayType.OFFICIAL_HOLIDAY, Year.of(2046), Year.of(2046), YearCycle.EVERY_YEAR, null, MonthDay.of(6, 29)))
      .addFixed(new JavaFixed("MATARIKI", HolidayType.OFFICIAL_HOLIDAY, Year.of(2047), Year.of(2047), YearCycle.EVERY_YEAR, null, MonthDay.of(7, 19)))
      .addFixed(new JavaFixed("MATARIKI", HolidayType.OFFICIAL_HOLIDAY, Year.of(2048), Year.of(2048), YearCycle.EVERY_YEAR, null, MonthDay.of(7, 3)))
      .addFixed(new JavaFixed("MATARIKI", HolidayType.OFFICIAL_HOLIDAY, Year.of(2049), Year.of(2049), YearCycle.EVERY_YEAR, null, MonthDay.of(6, 25)))
      .addFixed(new JavaFixed("MATARIKI", HolidayType.OFFICIAL_HOLIDAY, Year.of(2050), Year.of(2050), YearCycle.EVERY_YEAR, null, MonthDay.of(7, 15)))
      .addFixed(new JavaFixed("MATARIKI", HolidayType.OFFICIAL_HOLIDAY, Year.of(2051), Year.of(2051), YearCycle.EVERY_YEAR, null, MonthDay.of(6, 30)))
      .addFixed(new JavaFixed("MATARIKI", HolidayType.OFFICIAL_HOLIDAY, Year.of(2052), Year.of(2052), YearCycle.EVERY_YEAR, null, MonthDay.of(6, 21)))
      .addChristianHoliday(new JavaChristianHoliday("christian.GOOD_FRIDAY", HolidayType.OFFICIAL_HOLIDAY, null, null, YearCycle.EVERY_YEAR, null, ChristianHolidayType.GOOD_FRIDAY, Chronology.of("ISO")))
      .addChristianHoliday(new JavaChristianHoliday("christian.EASTER_MONDAY", HolidayType.OFFICIAL_HOLIDAY, null, null, YearCycle.EVERY_YEAR, null, ChristianHolidayType.EASTER_MONDAY, Chronology.of("ISO")))
      .addFixedWeekday(new JavaFixedWeekdayInMonth("QUEENS_BIRTHDAY", HolidayType.OFFICIAL_HOLIDAY, null, Year.of(2022), YearCycle.EVERY_YEAR, DayOfWeek.of(1), Month.of(6), Occurrance.FIRST))
      .addFixedWeekday(new JavaFixedWeekdayInMonth("KINGS_DAY", HolidayType.OFFICIAL_HOLIDAY, Year.of(2023), null, YearCycle.EVERY_YEAR, DayOfWeek.of(1), Month.of(6), Occurrance.FIRST))
      .addFixedWeekday(new JavaFixedWeekdayInMonth("LABOUR_DAY", HolidayType.OFFICIAL_HOLIDAY, null, null, YearCycle.EVERY_YEAR, DayOfWeek.of(1), Month.of(10), Occurrance.FOURTH))
      , List.of(new JavaConfiguration(new JavaHolidays()
      .addFixedWeekdayRelativeToFixed(new JavaFixedWeekdayRelativeToFixed("AUCKLAND_ANNIVERSARY", HolidayType.OFFICIAL_HOLIDAY, null, null, YearCycle.EVERY_YEAR, DayOfWeek.of(1), Relation.CLOSEST, new JavaFixed("null", HolidayType.OFFICIAL_HOLIDAY, null, null, YearCycle.EVERY_YEAR, null, MonthDay.of(1, 29)), Occurrance.FIRST))
      , null, "auk", "Auckland"), new JavaConfiguration(new JavaHolidays()
      .addFixedWeekdayRelativeToFixed(new JavaFixedWeekdayRelativeToFixed("AUCKLAND_ANNIVERSARY", HolidayType.OFFICIAL_HOLIDAY, null, null, YearCycle.EVERY_YEAR, DayOfWeek.of(1), Relation.CLOSEST, new JavaFixed("null", HolidayType.OFFICIAL_HOLIDAY, null, null, YearCycle.EVERY_YEAR, null, MonthDay.of(1, 29)), Occurrance.FIRST))
      , null, "bop", "Bay of Plenty"), new JavaConfiguration(new JavaHolidays()
      .addFixedWeekday(new JavaFixedWeekdayInMonth("CANTERBURY_SOUTH_ANNIVERSARY", HolidayType.OFFICIAL_HOLIDAY, null, null, YearCycle.EVERY_YEAR, DayOfWeek.of(1), Month.of(9), Occurrance.FOURTH))
      , null, "can", "Canterbury"), new JavaConfiguration(new JavaHolidays()
      .addFixedWeekdayRelativeToFixed(new JavaFixedWeekdayRelativeToFixed("CHATHAM_ISLANDS_ANNIVERSARY", HolidayType.OFFICIAL_HOLIDAY, null, null, YearCycle.EVERY_YEAR, DayOfWeek.of(1), Relation.CLOSEST, new JavaFixed("null", HolidayType.OFFICIAL_HOLIDAY, null, null, YearCycle.EVERY_YEAR, null, MonthDay.of(11, 30)), Occurrance.FIRST))
      , null, "cit", "Chatham Islands Territory"), new JavaConfiguration(new JavaHolidays()
      .addFixedWeekdayRelativeToFixed(new JavaFixedWeekdayRelativeToFixed("AUCKLAND_ANNIVERSARY", HolidayType.OFFICIAL_HOLIDAY, null, null, YearCycle.EVERY_YEAR, DayOfWeek.of(1), Relation.CLOSEST, new JavaFixed("null", HolidayType.OFFICIAL_HOLIDAY, null, null, YearCycle.EVERY_YEAR, null, MonthDay.of(1, 29)), Occurrance.FIRST))
      , null, "gis", "Gisborne"), new JavaConfiguration(new JavaHolidays()
      .addFixedWeekdayRelativeToFixed(new JavaFixedWeekdayRelativeToFixed("AUCKLAND_ANNIVERSARY", HolidayType.OFFICIAL_HOLIDAY, null, null, YearCycle.EVERY_YEAR, DayOfWeek.of(1), Relation.CLOSEST, new JavaFixed("null", HolidayType.OFFICIAL_HOLIDAY, null, null, YearCycle.EVERY_YEAR, null, MonthDay.of(1, 29)), Occurrance.FIRST))
      .addRelativeToWeekdayInMonth(new JavaRelativeToWeekdayInMonth("HAWKES_BAY_ANNIVERSARY", HolidayType.OFFICIAL_HOLIDAY, null, null, YearCycle.EVERY_YEAR, new JavaFixedWeekdayInMonth("LABOUR_DAY", HolidayType.OFFICIAL_HOLIDAY, null, null, YearCycle.EVERY_YEAR, DayOfWeek.of(1), Month.of(10), Occurrance.FOURTH), DayOfWeek.of(5), Relation.BEFORE))
      , null, "hkb", "Hawke's Bay"), new JavaConfiguration(new JavaHolidays()
      .addRelativeToWeekdayInMonth(new JavaRelativeToWeekdayInMonth("MARLBOROUGH_ANNIVERSARY", HolidayType.OFFICIAL_HOLIDAY, null, null, YearCycle.EVERY_YEAR, new JavaFixedWeekdayInMonth("LABOUR_DAY", HolidayType.OFFICIAL_HOLIDAY, null, null, YearCycle.EVERY_YEAR, DayOfWeek.of(1), Month.of(10), Occurrance.FOURTH), DayOfWeek.of(1), Relation.AFTER))
      , null, "mbh", "Marlborough"), new JavaConfiguration(new JavaHolidays()
      .addFixedWeekdayRelativeToFixed(new JavaFixedWeekdayRelativeToFixed("WELLINGTON_ANNIVERSARY", HolidayType.OFFICIAL_HOLIDAY, null, null, YearCycle.EVERY_YEAR, DayOfWeek.of(1), Relation.CLOSEST, new JavaFixed("null", HolidayType.OFFICIAL_HOLIDAY, null, null, YearCycle.EVERY_YEAR, null, MonthDay.of(1, 22)), Occurrance.FIRST))
      .addFixedWeekdayRelativeToFixed(new JavaFixedWeekdayRelativeToFixed("AUCKLAND_ANNIVERSARY", HolidayType.OFFICIAL_HOLIDAY, null, null, YearCycle.EVERY_YEAR, DayOfWeek.of(1), Relation.CLOSEST, new JavaFixed("null", HolidayType.OFFICIAL_HOLIDAY, null, null, YearCycle.EVERY_YEAR, null, MonthDay.of(1, 29)), Occurrance.FIRST))
      , null, "mwt", "Manawatu-Wanganui"), new JavaConfiguration(new JavaHolidays()
      .addFixedWeekdayRelativeToFixed(new JavaFixedWeekdayRelativeToFixed("NELSON_ANNIVERSARY", HolidayType.OFFICIAL_HOLIDAY, null, null, YearCycle.EVERY_YEAR, DayOfWeek.of(1), Relation.CLOSEST, new JavaFixed("null", HolidayType.OFFICIAL_HOLIDAY, null, null, YearCycle.EVERY_YEAR, null, MonthDay.of(2, 1)), Occurrance.FIRST))
      , null, "nsn", "Nelson"), new JavaConfiguration(new JavaHolidays()
      .addFixedWeekdayRelativeToFixed(new JavaFixedWeekdayRelativeToFixed("AUCKLAND_ANNIVERSARY", HolidayType.OFFICIAL_HOLIDAY, null, null, YearCycle.EVERY_YEAR, DayOfWeek.of(1), Relation.CLOSEST, new JavaFixed("null", HolidayType.OFFICIAL_HOLIDAY, null, null, YearCycle.EVERY_YEAR, null, MonthDay.of(1, 29)), Occurrance.FIRST))
      , null, "ntl", "Northland"), new JavaConfiguration(new JavaHolidays()
      .addFixedWeekdayRelativeToFixed(new JavaFixedWeekdayRelativeToFixed("OTAGO_ANNIVERSARY", HolidayType.OFFICIAL_HOLIDAY, null, null, YearCycle.EVERY_YEAR, DayOfWeek.of(1), Relation.CLOSEST, new JavaFixed("null", HolidayType.OFFICIAL_HOLIDAY, null, null, YearCycle.EVERY_YEAR, null, MonthDay.of(3, 23)), Occurrance.FIRST))
      , null, "ota", "Otago"), new JavaConfiguration(new JavaHolidays()
      .addChristianHoliday(new JavaChristianHoliday("SOUTHLAND_ANNIVERSARY", HolidayType.OFFICIAL_HOLIDAY, Year.of(2012), null, YearCycle.EVERY_YEAR, null, ChristianHolidayType.EASTER_TUESDAY, Chronology.of("ISO")))
      .addFixedWeekdayRelativeToFixed(new JavaFixedWeekdayRelativeToFixed("SOUTHLAND_ANNIVERSARY", HolidayType.OFFICIAL_HOLIDAY, null, Year.of(2011), YearCycle.EVERY_YEAR, DayOfWeek.of(1), Relation.CLOSEST, new JavaFixed("null", HolidayType.OFFICIAL_HOLIDAY, null, null, YearCycle.EVERY_YEAR, null, MonthDay.of(1, 17)), Occurrance.FIRST))
      , null, "stl", "Southland"), new JavaConfiguration(new JavaHolidays()
      , null, "tas", "Tasman"), new JavaConfiguration(new JavaHolidays()
      .addFixedWeekday(new JavaFixedWeekdayInMonth("TARANAKI_ANNIVERSARY", HolidayType.OFFICIAL_HOLIDAY, null, null, YearCycle.EVERY_YEAR, DayOfWeek.of(1), Month.of(3), Occurrance.SECOND))
      , null, "tki", "Taranaki"), new JavaConfiguration(new JavaHolidays()
      .addFixedWeekdayRelativeToFixed(new JavaFixedWeekdayRelativeToFixed("WELLINGTON_ANNIVERSARY", HolidayType.OFFICIAL_HOLIDAY, null, null, YearCycle.EVERY_YEAR, DayOfWeek.of(1), Relation.CLOSEST, new JavaFixed("null", HolidayType.OFFICIAL_HOLIDAY, null, null, YearCycle.EVERY_YEAR, null, MonthDay.of(1, 22)), Occurrance.FIRST))
      , null, "wgn", "Wellington"), new JavaConfiguration(new JavaHolidays()
      .addFixedWeekdayRelativeToFixed(new JavaFixedWeekdayRelativeToFixed("AUCKLAND_ANNIVERSARY", HolidayType.OFFICIAL_HOLIDAY, null, null, YearCycle.EVERY_YEAR, DayOfWeek.of(1), Relation.CLOSEST, new JavaFixed("null", HolidayType.OFFICIAL_HOLIDAY, null, null, YearCycle.EVERY_YEAR, null, MonthDay.of(1, 29)), Occurrance.FIRST))
      , null, "wko", "Waikato"), new JavaConfiguration(new JavaHolidays()
      .addFixedWeekdayRelativeToFixed(new JavaFixedWeekdayRelativeToFixed("WESTLAND_ANNIVERSARY", HolidayType.OFFICIAL_HOLIDAY, null, null, YearCycle.EVERY_YEAR, DayOfWeek.of(1), Relation.CLOSEST, new JavaFixed("null", HolidayType.OFFICIAL_HOLIDAY, null, null, YearCycle.EVERY_YEAR, null, MonthDay.of(12, 1)), Occurrance.FIRST))
      , null, "wtc", "West Coast")), "nz", "New Zealand");
  }
}
