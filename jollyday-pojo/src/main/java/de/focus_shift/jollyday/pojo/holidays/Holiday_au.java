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
import de.focus_shift.jollyday.pojo.JavaFixedWeekdayBetweenFixed;
import de.focus_shift.jollyday.pojo.JavaFixedWeekdayInMonth;
import de.focus_shift.jollyday.pojo.JavaFixedWeekdayRelativeToFixed;
import de.focus_shift.jollyday.pojo.JavaHolidays;
import de.focus_shift.jollyday.pojo.JavaMovingCondition;

public class Holiday_au {

  public static JavaConfiguration configuration;

  static {
    configuration = new JavaConfiguration(new JavaHolidays()
      .addFixed(new JavaFixed("NEW_YEAR", HolidayType.OFFICIAL_HOLIDAY, null, Year.of(2007), YearCycle.EVERY_YEAR, List.of(new JavaMovingCondition(DayOfWeek.of(6), With.NEXT, DayOfWeek.of(1)), new JavaMovingCondition(DayOfWeek.of(7), With.NEXT, DayOfWeek.of(1))), MonthDay.of(1, 1)))
      .addFixed(new JavaFixed("NEW_YEAR", HolidayType.OFFICIAL_HOLIDAY, Year.of(2020), null, YearCycle.EVERY_YEAR, null, MonthDay.of(1, 1)))
      .addFixed(new JavaFixed("NEW_YEAR", HolidayType.OFFICIAL_HOLIDAY, Year.of(2020), null, YearCycle.EVERY_YEAR, List.of(new JavaMovingCondition(DayOfWeek.of(6), With.NEXT, DayOfWeek.of(1)), new JavaMovingCondition(DayOfWeek.of(7), With.NEXT, DayOfWeek.of(1))), MonthDay.of(1, 1)))
      .addFixed(new JavaFixed("NATIONAL_DAY", HolidayType.OFFICIAL_HOLIDAY, null, Year.of(2007), YearCycle.EVERY_YEAR, null, MonthDay.of(1, 26)))
      .addFixed(new JavaFixed("NATIONAL_DAY", HolidayType.OFFICIAL_HOLIDAY, Year.of(2008), null, YearCycle.EVERY_YEAR, List.of(new JavaMovingCondition(DayOfWeek.of(6), With.NEXT, DayOfWeek.of(1)), new JavaMovingCondition(DayOfWeek.of(7), With.NEXT, DayOfWeek.of(1))), MonthDay.of(1, 26)))
      .addFixed(new JavaFixed("ANZAC", HolidayType.OFFICIAL_HOLIDAY, null, Year.of(2007), YearCycle.EVERY_YEAR, null, MonthDay.of(4, 25)))
      .addFixed(new JavaFixed("ANZAC", HolidayType.OFFICIAL_HOLIDAY, Year.of(2020), null, YearCycle.EVERY_YEAR, null, MonthDay.of(4, 25)))
      .addFixed(new JavaFixed("CHRISTMAS", HolidayType.OFFICIAL_HOLIDAY, null, Year.of(2007), YearCycle.EVERY_YEAR, List.of(new JavaMovingCondition(DayOfWeek.of(7), With.NEXT, DayOfWeek.of(1))), MonthDay.of(12, 25)))
      .addFixed(new JavaFixed("BOXING_DAY", HolidayType.OFFICIAL_HOLIDAY, null, Year.of(2007), YearCycle.EVERY_YEAR, List.of(new JavaMovingCondition(DayOfWeek.of(7), With.NEXT, DayOfWeek.of(1))), MonthDay.of(12, 26)))
      .addFixed(new JavaFixed("CHRISTMAS", HolidayType.OFFICIAL_HOLIDAY, Year.of(2020), null, YearCycle.EVERY_YEAR, null, MonthDay.of(12, 25)))
      .addFixed(new JavaFixed("CHRISTMAS", HolidayType.OFFICIAL_HOLIDAY, Year.of(2020), null, YearCycle.EVERY_YEAR, List.of(new JavaMovingCondition(DayOfWeek.of(6), With.NEXT, DayOfWeek.of(1)), new JavaMovingCondition(DayOfWeek.of(7), With.NEXT, DayOfWeek.of(1))), MonthDay.of(12, 25)))
      .addFixed(new JavaFixed("BOXING_DAY", HolidayType.OFFICIAL_HOLIDAY, Year.of(2020), null, YearCycle.EVERY_YEAR, null, MonthDay.of(12, 26)))
      .addFixed(new JavaFixed("BOXING_DAY", HolidayType.OFFICIAL_HOLIDAY, Year.of(2020), null, YearCycle.EVERY_YEAR, List.of(new JavaMovingCondition(DayOfWeek.of(6), With.NEXT, DayOfWeek.of(1)), new JavaMovingCondition(DayOfWeek.of(7), With.NEXT, DayOfWeek.of(2)), new JavaMovingCondition(DayOfWeek.of(1), With.NEXT, DayOfWeek.of(2))), MonthDay.of(12, 26)))
      .addChristianHoliday(new JavaChristianHoliday("christian.EASTER", HolidayType.OFFICIAL_HOLIDAY, null, null, YearCycle.EVERY_YEAR, null, ChristianHolidayType.EASTER, Chronology.of("ISO")))
      .addChristianHoliday(new JavaChristianHoliday("christian.GOOD_FRIDAY", HolidayType.OFFICIAL_HOLIDAY, null, null, YearCycle.EVERY_YEAR, null, ChristianHolidayType.GOOD_FRIDAY, Chronology.of("ISO")))
      .addChristianHoliday(new JavaChristianHoliday("christian.EASTER_SATURDAY", HolidayType.OFFICIAL_HOLIDAY, null, null, YearCycle.EVERY_YEAR, null, ChristianHolidayType.EASTER_SATURDAY, Chronology.of("ISO")))
      .addChristianHoliday(new JavaChristianHoliday("christian.EASTER_MONDAY", HolidayType.OFFICIAL_HOLIDAY, null, null, YearCycle.EVERY_YEAR, null, ChristianHolidayType.EASTER_MONDAY, Chronology.of("ISO")))
      , List.of(new JavaConfiguration(new JavaHolidays()
      .addFixed(new JavaFixed("NEW_YEAR", HolidayType.OFFICIAL_HOLIDAY, Year.of(2008), Year.of(2019), YearCycle.EVERY_YEAR, List.of(new JavaMovingCondition(DayOfWeek.of(6), With.NEXT, DayOfWeek.of(1)), new JavaMovingCondition(DayOfWeek.of(7), With.NEXT, DayOfWeek.of(1))), MonthDay.of(1, 1)))
      .addFixed(new JavaFixed("ANZAC", HolidayType.OFFICIAL_HOLIDAY, null, Year.of(2007), YearCycle.EVERY_YEAR, List.of(new JavaMovingCondition(DayOfWeek.of(6), With.NEXT, DayOfWeek.of(1)), new JavaMovingCondition(DayOfWeek.of(7), With.NEXT, DayOfWeek.of(1))), MonthDay.of(4, 25)))
      .addFixed(new JavaFixed("ANZAC", HolidayType.OFFICIAL_HOLIDAY, Year.of(2020), null, YearCycle.EVERY_YEAR, List.of(new JavaMovingCondition(DayOfWeek.of(6), With.NEXT, DayOfWeek.of(1)), new JavaMovingCondition(DayOfWeek.of(7), With.NEXT, DayOfWeek.of(1))), MonthDay.of(4, 25)))
      .addFixed(new JavaFixed("CHRISTMAS", HolidayType.OFFICIAL_HOLIDAY, Year.of(2008), Year.of(2019), YearCycle.EVERY_YEAR, List.of(new JavaMovingCondition(DayOfWeek.of(6), With.NEXT, DayOfWeek.of(1)), new JavaMovingCondition(DayOfWeek.of(7), With.NEXT, DayOfWeek.of(1))), MonthDay.of(12, 25)))
      .addFixed(new JavaFixed("BOXING_DAY", HolidayType.OFFICIAL_HOLIDAY, Year.of(2008), Year.of(2019), YearCycle.EVERY_YEAR, List.of(new JavaMovingCondition(DayOfWeek.of(6), With.NEXT, DayOfWeek.of(1)), new JavaMovingCondition(DayOfWeek.of(7), With.NEXT, DayOfWeek.of(1))), MonthDay.of(12, 26)))
      .addFixedWeekday(new JavaFixedWeekdayInMonth("CANBERRA_DAY", HolidayType.OFFICIAL_HOLIDAY, null, null, YearCycle.EVERY_YEAR, DayOfWeek.of(1), Month.of(3), Occurrance.SECOND))
      .addFixedWeekday(new JavaFixedWeekdayInMonth("QUEENS_BIRTHDAY", HolidayType.OFFICIAL_HOLIDAY, null, null, YearCycle.EVERY_YEAR, DayOfWeek.of(1), Month.of(6), Occurrance.SECOND))
      .addFixedWeekday(new JavaFixedWeekdayInMonth("LABOUR_DAY", HolidayType.OFFICIAL_HOLIDAY, null, null, YearCycle.EVERY_YEAR, DayOfWeek.of(1), Month.of(10), Occurrance.FIRST))
      .addFixedWeekday(new JavaFixedWeekdayInMonth("FAMILY_COMMUNITY", HolidayType.OFFICIAL_HOLIDAY, null, Year.of(2019), YearCycle.EVERY_YEAR, DayOfWeek.of(2), Month.of(11), Occurrance.FIRST))
      .addFixedWeekdayRelativeToFixed(new JavaFixedWeekdayRelativeToFixed("RECONCILIATION", HolidayType.OFFICIAL_HOLIDAY, Year.of(2020), null, YearCycle.EVERY_YEAR, DayOfWeek.of(1), Relation.AFTER, new JavaFixed("null", HolidayType.OFFICIAL_HOLIDAY, null, null, YearCycle.EVERY_YEAR, null, MonthDay.of(5, 26)), Occurrance.FIRST))
      , null, "act", "Australian Capital Territory"), new JavaConfiguration(new JavaHolidays()
      .addFixed(new JavaFixed("NEW_YEAR", HolidayType.OFFICIAL_HOLIDAY, Year.of(2008), Year.of(2019), YearCycle.EVERY_YEAR, List.of(new JavaMovingCondition(DayOfWeek.of(7), With.NEXT, DayOfWeek.of(1))), MonthDay.of(1, 1)))
      .addFixed(new JavaFixed("ANZAC", HolidayType.OFFICIAL_HOLIDAY, null, Year.of(2007), YearCycle.EVERY_YEAR, List.of(new JavaMovingCondition(DayOfWeek.of(7), With.NEXT, DayOfWeek.of(1))), MonthDay.of(4, 25)))
      .addFixed(new JavaFixed("CHRISTMAS", HolidayType.OFFICIAL_HOLIDAY, Year.of(2008), Year.of(2019), YearCycle.EVERY_YEAR, List.of(new JavaMovingCondition(DayOfWeek.of(6), With.NEXT, DayOfWeek.of(1)), new JavaMovingCondition(DayOfWeek.of(7), With.NEXT, DayOfWeek.of(1))), MonthDay.of(12, 25)))
      .addFixed(new JavaFixed("BOXING_DAY", HolidayType.OFFICIAL_HOLIDAY, Year.of(2008), Year.of(2019), YearCycle.EVERY_YEAR, List.of(new JavaMovingCondition(DayOfWeek.of(6), With.NEXT, DayOfWeek.of(1)), new JavaMovingCondition(DayOfWeek.of(7), With.NEXT, DayOfWeek.of(1))), MonthDay.of(12, 26)))
      .addFixedWeekday(new JavaFixedWeekdayInMonth("QUEENS_BIRTHDAY", HolidayType.OFFICIAL_HOLIDAY, null, null, YearCycle.EVERY_YEAR, DayOfWeek.of(1), Month.of(6), Occurrance.SECOND))
      .addFixedWeekday(new JavaFixedWeekdayInMonth("LABOUR_DAY", HolidayType.OFFICIAL_HOLIDAY, null, null, YearCycle.EVERY_YEAR, DayOfWeek.of(1), Month.of(10), Occurrance.FIRST))
      .addFixedWeekday(new JavaFixedWeekdayInMonth("BANK_HOLIDAY", HolidayType.OFFICIAL_HOLIDAY, Year.of(2020), null, YearCycle.EVERY_YEAR, DayOfWeek.of(1), Month.of(8), Occurrance.FIRST))
      , null, "nsw", "New South Wales"), new JavaConfiguration(new JavaHolidays()
      .addFixed(new JavaFixed("NEW_YEAR", HolidayType.OFFICIAL_HOLIDAY, Year.of(2008), Year.of(2019), YearCycle.EVERY_YEAR, List.of(new JavaMovingCondition(DayOfWeek.of(6), With.NEXT, DayOfWeek.of(1)), new JavaMovingCondition(DayOfWeek.of(7), With.NEXT, DayOfWeek.of(1))), MonthDay.of(1, 1)))
      .addFixed(new JavaFixed("ANZAC", HolidayType.OFFICIAL_HOLIDAY, null, Year.of(2007), YearCycle.EVERY_YEAR, List.of(new JavaMovingCondition(DayOfWeek.of(7), With.NEXT, DayOfWeek.of(1))), MonthDay.of(4, 25)))
      .addFixed(new JavaFixed("ANZAC", HolidayType.OFFICIAL_HOLIDAY, Year.of(2020), null, YearCycle.EVERY_YEAR, List.of(new JavaMovingCondition(DayOfWeek.of(7), With.NEXT, DayOfWeek.of(1))), MonthDay.of(4, 25)))
      .addFixed(new JavaFixed("CHRISTMAS", HolidayType.OFFICIAL_HOLIDAY, Year.of(2008), Year.of(2019), YearCycle.EVERY_YEAR, List.of(new JavaMovingCondition(DayOfWeek.of(6), With.NEXT, DayOfWeek.of(1)), new JavaMovingCondition(DayOfWeek.of(7), With.NEXT, DayOfWeek.of(1))), MonthDay.of(12, 25)))
      .addFixed(new JavaFixed("BOXING_DAY", HolidayType.OFFICIAL_HOLIDAY, Year.of(2008), Year.of(2019), YearCycle.EVERY_YEAR, List.of(new JavaMovingCondition(DayOfWeek.of(6), With.NEXT, DayOfWeek.of(1)), new JavaMovingCondition(DayOfWeek.of(7), With.NEXT, DayOfWeek.of(1))), MonthDay.of(12, 26)))
      .addFixedWeekday(new JavaFixedWeekdayInMonth("MAY_DAY", HolidayType.OFFICIAL_HOLIDAY, null, null, YearCycle.EVERY_YEAR, DayOfWeek.of(1), Month.of(5), Occurrance.FIRST))
      .addFixedWeekday(new JavaFixedWeekdayInMonth("QUEENS_BIRTHDAY", HolidayType.OFFICIAL_HOLIDAY, null, null, YearCycle.EVERY_YEAR, DayOfWeek.of(1), Month.of(6), Occurrance.SECOND))
      .addFixedWeekday(new JavaFixedWeekdayInMonth("PICNIC", HolidayType.OFFICIAL_HOLIDAY, null, null, YearCycle.EVERY_YEAR, DayOfWeek.of(1), Month.of(8), Occurrance.FIRST))
      , null, "nt", "Nothern Territory"), new JavaConfiguration(new JavaHolidays()
      .addFixed(new JavaFixed("NEW_YEAR", HolidayType.OFFICIAL_HOLIDAY, Year.of(2008), Year.of(2019), YearCycle.EVERY_YEAR, List.of(new JavaMovingCondition(DayOfWeek.of(7), With.NEXT, DayOfWeek.of(1))), MonthDay.of(1, 1)))
      .addFixed(new JavaFixed("ANZAC", HolidayType.OFFICIAL_HOLIDAY, null, Year.of(2007), YearCycle.EVERY_YEAR, List.of(new JavaMovingCondition(DayOfWeek.of(7), With.NEXT, DayOfWeek.of(1))), MonthDay.of(4, 25)))
      .addFixed(new JavaFixed("ANZAC", HolidayType.OFFICIAL_HOLIDAY, Year.of(2020), null, YearCycle.EVERY_YEAR, List.of(new JavaMovingCondition(DayOfWeek.of(7), With.NEXT, DayOfWeek.of(1))), MonthDay.of(4, 25)))
      .addFixed(new JavaFixed("CHRISTMAS", HolidayType.OFFICIAL_HOLIDAY, Year.of(2008), Year.of(2019), YearCycle.EVERY_YEAR, List.of(new JavaMovingCondition(DayOfWeek.of(7), With.NEXT, DayOfWeek.of(1))), MonthDay.of(12, 25)))
      .addFixed(new JavaFixed("BOXING_DAY", HolidayType.OFFICIAL_HOLIDAY, Year.of(2008), Year.of(2019), YearCycle.EVERY_YEAR, List.of(new JavaMovingCondition(DayOfWeek.of(7), With.NEXT, DayOfWeek.of(1))), MonthDay.of(12, 26)))
      .addFixedWeekday(new JavaFixedWeekdayInMonth("LABOUR_DAY", HolidayType.OFFICIAL_HOLIDAY, null, null, YearCycle.EVERY_YEAR, DayOfWeek.of(1), Month.of(5), Occurrance.FIRST))
      .addFixedWeekday(new JavaFixedWeekdayInMonth("QUEENS_BIRTHDAY", HolidayType.OFFICIAL_HOLIDAY, null, Year.of(2019), YearCycle.EVERY_YEAR, DayOfWeek.of(1), Month.of(6), Occurrance.SECOND))
      .addFixedWeekday(new JavaFixedWeekdayInMonth("QUEENS_BIRTHDAY", HolidayType.OFFICIAL_HOLIDAY, Year.of(2020), null, YearCycle.EVERY_YEAR, DayOfWeek.of(1), Month.of(10), Occurrance.FIRST))
      , List.of(new JavaConfiguration(new JavaHolidays()
      .addFixedWeekdayBetweenFixed(new JavaFixedWeekdayBetweenFixed("EKKA", HolidayType.OFFICIAL_HOLIDAY, Year.of(2020), null, YearCycle.EVERY_YEAR, new JavaFixed("null", HolidayType.OFFICIAL_HOLIDAY, null, null, YearCycle.EVERY_YEAR, null, MonthDay.of(8, 10)), new JavaFixed("null", HolidayType.OFFICIAL_HOLIDAY, null, null, YearCycle.EVERY_YEAR, null, MonthDay.of(8, 16)), DayOfWeek.of(3)))
      , null, "br", "Brisbane Area")), "qld", "Queensland"), new JavaConfiguration(new JavaHolidays()
      .addFixed(new JavaFixed("NEW_YEAR", HolidayType.OFFICIAL_HOLIDAY, Year.of(2008), Year.of(2019), YearCycle.EVERY_YEAR, List.of(new JavaMovingCondition(DayOfWeek.of(6), With.NEXT, DayOfWeek.of(1)), new JavaMovingCondition(DayOfWeek.of(7), With.NEXT, DayOfWeek.of(1))), MonthDay.of(1, 1)))
      .addFixed(new JavaFixed("NATIONAL_DAY", HolidayType.OFFICIAL_HOLIDAY, Year.of(2020), null, YearCycle.EVERY_YEAR, null, MonthDay.of(1, 26)))
      .addFixed(new JavaFixed("ANZAC", HolidayType.OFFICIAL_HOLIDAY, null, Year.of(2007), YearCycle.EVERY_YEAR, List.of(new JavaMovingCondition(DayOfWeek.of(7), With.NEXT, DayOfWeek.of(1))), MonthDay.of(4, 25)))
      .addFixed(new JavaFixed("ANZAC", HolidayType.OFFICIAL_HOLIDAY, Year.of(2020), null, YearCycle.EVERY_YEAR, List.of(new JavaMovingCondition(DayOfWeek.of(7), With.NEXT, DayOfWeek.of(1))), MonthDay.of(4, 25)))
      .addFixed(new JavaFixed("CHRISTMAS", HolidayType.OFFICIAL_HOLIDAY, Year.of(2008), Year.of(2019), YearCycle.EVERY_YEAR, List.of(new JavaMovingCondition(DayOfWeek.of(6), With.NEXT, DayOfWeek.of(1)), new JavaMovingCondition(DayOfWeek.of(7), With.NEXT, DayOfWeek.of(1))), MonthDay.of(12, 25)))
      .addFixed(new JavaFixed("PROCLAMATION", HolidayType.OFFICIAL_HOLIDAY, Year.of(2008), Year.of(2019), YearCycle.EVERY_YEAR, List.of(new JavaMovingCondition(DayOfWeek.of(6), With.NEXT, DayOfWeek.of(1)), new JavaMovingCondition(DayOfWeek.of(7), With.NEXT, DayOfWeek.of(1))), MonthDay.of(12, 26)))
      .addFixed(new JavaFixed("PROCLAMATION", HolidayType.OFFICIAL_HOLIDAY, Year.of(2020), null, YearCycle.EVERY_YEAR, List.of(new JavaMovingCondition(DayOfWeek.of(6), With.NEXT, DayOfWeek.of(1)), new JavaMovingCondition(DayOfWeek.of(7), With.NEXT, DayOfWeek.of(2)), new JavaMovingCondition(DayOfWeek.of(1), With.NEXT, DayOfWeek.of(2))), MonthDay.of(12, 26)))
      .addFixedWeekday(new JavaFixedWeekdayInMonth("ADELAIDE_CUP", HolidayType.OFFICIAL_HOLIDAY, null, null, YearCycle.EVERY_YEAR, DayOfWeek.of(1), Month.of(3), Occurrance.SECOND))
      .addFixedWeekday(new JavaFixedWeekdayInMonth("QUEENS_BIRTHDAY", HolidayType.OFFICIAL_HOLIDAY, null, null, YearCycle.EVERY_YEAR, DayOfWeek.of(1), Month.of(6), Occurrance.SECOND))
      .addFixedWeekday(new JavaFixedWeekdayInMonth("LABOUR_DAY", HolidayType.OFFICIAL_HOLIDAY, null, null, YearCycle.EVERY_YEAR, DayOfWeek.of(1), Month.of(10), Occurrance.FIRST))
      , null, "sa", "South Australia"), new JavaConfiguration(new JavaHolidays()
      .addFixed(new JavaFixed("ANZAC", HolidayType.OFFICIAL_HOLIDAY, null, Year.of(2019), YearCycle.EVERY_YEAR, null, MonthDay.of(4, 25)))
      .addFixed(new JavaFixed("NEW_YEAR", HolidayType.OFFICIAL_HOLIDAY, Year.of(2008), Year.of(2019), YearCycle.EVERY_YEAR, List.of(new JavaMovingCondition(DayOfWeek.of(6), With.NEXT, DayOfWeek.of(1)), new JavaMovingCondition(DayOfWeek.of(7), With.NEXT, DayOfWeek.of(1))), MonthDay.of(1, 1)))
      .addFixed(new JavaFixed("CHRISTMAS", HolidayType.OFFICIAL_HOLIDAY, Year.of(2008), Year.of(2019), YearCycle.EVERY_YEAR, List.of(new JavaMovingCondition(DayOfWeek.of(6), With.NEXT, DayOfWeek.of(1)), new JavaMovingCondition(DayOfWeek.of(7), With.NEXT, DayOfWeek.of(1))), MonthDay.of(12, 25)))
      .addFixed(new JavaFixed("BOXING_DAY", HolidayType.OFFICIAL_HOLIDAY, Year.of(2008), Year.of(2019), YearCycle.EVERY_YEAR, List.of(new JavaMovingCondition(DayOfWeek.of(6), With.NEXT, DayOfWeek.of(1)), new JavaMovingCondition(DayOfWeek.of(7), With.NEXT, DayOfWeek.of(1))), MonthDay.of(12, 26)))
      .addChristianHoliday(new JavaChristianHoliday("christian.EASTER_TUESDAY", HolidayType.OFFICIAL_HOLIDAY, null, null, YearCycle.EVERY_YEAR, null, ChristianHolidayType.EASTER_TUESDAY, Chronology.of("ISO")))
      .addFixedWeekday(new JavaFixedWeekdayInMonth("EIGHT", HolidayType.OFFICIAL_HOLIDAY, null, null, YearCycle.EVERY_YEAR, DayOfWeek.of(1), Month.of(3), Occurrance.SECOND))
      .addFixedWeekday(new JavaFixedWeekdayInMonth("QUEENS_BIRTHDAY", HolidayType.OFFICIAL_HOLIDAY, null, null, YearCycle.EVERY_YEAR, DayOfWeek.of(1), Month.of(6), Occurrance.SECOND))
      , List.of(new JavaConfiguration(new JavaHolidays()
      .addFixedWeekday(new JavaFixedWeekdayInMonth("HOBART", HolidayType.OFFICIAL_HOLIDAY, null, null, YearCycle.EVERY_YEAR, DayOfWeek.of(1), Month.of(2), Occurrance.SECOND))
      , null, "ho", "Hobart Area"), new JavaConfiguration(new JavaHolidays()
      .addFixedWeekday(new JavaFixedWeekdayInMonth("RECREATION", HolidayType.OFFICIAL_HOLIDAY, null, null, YearCycle.EVERY_YEAR, DayOfWeek.of(1), Month.of(11), Occurrance.FIRST))
      , null, "nh", "Non-Hobart Area")), "tas", "Tasmania"), new JavaConfiguration(new JavaHolidays()
      .addFixed(new JavaFixed("ANZAC", HolidayType.OFFICIAL_HOLIDAY, null, Year.of(2019), YearCycle.EVERY_YEAR, null, MonthDay.of(4, 25)))
      .addFixed(new JavaFixed("NEW_YEAR", HolidayType.OFFICIAL_HOLIDAY, Year.of(2008), Year.of(2019), YearCycle.EVERY_YEAR, List.of(new JavaMovingCondition(DayOfWeek.of(7), With.NEXT, DayOfWeek.of(1))), MonthDay.of(1, 1)))
      .addFixed(new JavaFixed("CHRISTMAS", HolidayType.OFFICIAL_HOLIDAY, Year.of(2008), Year.of(2019), YearCycle.EVERY_YEAR, List.of(new JavaMovingCondition(DayOfWeek.of(6), With.NEXT, DayOfWeek.of(1)), new JavaMovingCondition(DayOfWeek.of(7), With.NEXT, DayOfWeek.of(1))), MonthDay.of(12, 25)))
      .addFixed(new JavaFixed("BOXING_DAY", HolidayType.OFFICIAL_HOLIDAY, Year.of(2008), Year.of(2019), YearCycle.EVERY_YEAR, List.of(new JavaMovingCondition(DayOfWeek.of(6), With.NEXT, DayOfWeek.of(1)), new JavaMovingCondition(DayOfWeek.of(7), With.NEXT, DayOfWeek.of(1))), MonthDay.of(12, 26)))
      .addFixedWeekday(new JavaFixedWeekdayInMonth("LABOUR_DAY", HolidayType.OFFICIAL_HOLIDAY, null, null, YearCycle.EVERY_YEAR, DayOfWeek.of(1), Month.of(3), Occurrance.SECOND))
      .addFixedWeekday(new JavaFixedWeekdayInMonth("QUEENS_BIRTHDAY", HolidayType.OFFICIAL_HOLIDAY, null, null, YearCycle.EVERY_YEAR, DayOfWeek.of(1), Month.of(6), Occurrance.SECOND))
      .addFixedWeekday(new JavaFixedWeekdayInMonth("MELBOURNE_CUP", HolidayType.OFFICIAL_HOLIDAY, null, null, YearCycle.EVERY_YEAR, DayOfWeek.of(2), Month.of(11), Occurrance.FIRST))
      .addFixedWeekday(new JavaFixedWeekdayInMonth("AFL", HolidayType.OFFICIAL_HOLIDAY, Year.of(2020), null, YearCycle.EVERY_YEAR, DayOfWeek.of(5), Month.of(9), Occurrance.LAST))
      , null, "vic", "Victoria"), new JavaConfiguration(new JavaHolidays()
      .addFixed(new JavaFixed("NEW_YEAR", HolidayType.OFFICIAL_HOLIDAY, Year.of(2008), Year.of(2019), YearCycle.EVERY_YEAR, List.of(new JavaMovingCondition(DayOfWeek.of(6), With.NEXT, DayOfWeek.of(1)), new JavaMovingCondition(DayOfWeek.of(7), With.NEXT, DayOfWeek.of(1))), MonthDay.of(1, 1)))
      .addFixed(new JavaFixed("ANZAC", HolidayType.OFFICIAL_HOLIDAY, null, Year.of(2007), YearCycle.EVERY_YEAR, List.of(new JavaMovingCondition(DayOfWeek.of(6), With.NEXT, DayOfWeek.of(1)), new JavaMovingCondition(DayOfWeek.of(7), With.NEXT, DayOfWeek.of(1))), MonthDay.of(4, 25)))
      .addFixed(new JavaFixed("ANZAC", HolidayType.OFFICIAL_HOLIDAY, Year.of(2020), null, YearCycle.EVERY_YEAR, List.of(new JavaMovingCondition(DayOfWeek.of(6), With.NEXT, DayOfWeek.of(1)), new JavaMovingCondition(DayOfWeek.of(7), With.NEXT, DayOfWeek.of(1))), MonthDay.of(4, 25)))
      .addFixed(new JavaFixed("CHRISTMAS", HolidayType.OFFICIAL_HOLIDAY, Year.of(2008), Year.of(2019), YearCycle.EVERY_YEAR, List.of(new JavaMovingCondition(DayOfWeek.of(6), With.NEXT, DayOfWeek.of(1)), new JavaMovingCondition(DayOfWeek.of(7), With.NEXT, DayOfWeek.of(1))), MonthDay.of(12, 25)))
      .addFixed(new JavaFixed("BOXING_DAY", HolidayType.OFFICIAL_HOLIDAY, Year.of(2008), Year.of(2019), YearCycle.EVERY_YEAR, List.of(new JavaMovingCondition(DayOfWeek.of(6), With.NEXT, DayOfWeek.of(1)), new JavaMovingCondition(DayOfWeek.of(7), With.NEXT, DayOfWeek.of(1))), MonthDay.of(12, 26)))
      .addFixedWeekday(new JavaFixedWeekdayInMonth("LABOUR_DAY", HolidayType.OFFICIAL_HOLIDAY, null, null, YearCycle.EVERY_YEAR, DayOfWeek.of(1), Month.of(3), Occurrance.FIRST))
      .addFixedWeekday(new JavaFixedWeekdayInMonth("FOUNDATION", HolidayType.OFFICIAL_HOLIDAY, null, Year.of(2019), YearCycle.EVERY_YEAR, DayOfWeek.of(1), Month.of(6), Occurrance.FIRST))
      .addFixedWeekday(new JavaFixedWeekdayInMonth("WESTERN_AUSTRALIA", HolidayType.OFFICIAL_HOLIDAY, Year.of(2020), null, YearCycle.EVERY_YEAR, DayOfWeek.of(1), Month.of(6), Occurrance.FIRST))
      .addFixedWeekday(new JavaFixedWeekdayInMonth("QUEENS_BIRTHDAY", HolidayType.OFFICIAL_HOLIDAY, null, null, YearCycle.EVERY_YEAR, DayOfWeek.of(1), Month.of(9), Occurrance.LAST))
      , null, "wa", "Western Australia")), "au", "Australia");
  }
}
