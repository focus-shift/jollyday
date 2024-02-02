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
import de.focus_shift.jollyday.pojo.JavaHolidays;
import de.focus_shift.jollyday.pojo.JavaMovingCondition;
import de.focus_shift.jollyday.pojo.JavaRelativeToWeekdayInMonth;

public class Holiday_vg {

  public static JavaConfiguration configuration;

  static {
    configuration = new JavaConfiguration(new JavaHolidays()
      .addFixed(new JavaFixed("NEW_YEAR", HolidayType.OFFICIAL_HOLIDAY, null, null, YearCycle.EVERY_YEAR, List.of(new JavaMovingCondition(DayOfWeek.of(6), With.NEXT, DayOfWeek.of(1)), new JavaMovingCondition(DayOfWeek.of(7), With.NEXT, DayOfWeek.of(1))), MonthDay.of(1, 1)))
      .addFixed(new JavaFixed("STOUTTS_BIRTHDAY", HolidayType.OFFICIAL_HOLIDAY, Year.of(1995), null, YearCycle.EVERY_YEAR, List.of(new JavaMovingCondition(DayOfWeek.of(2), With.PREVIOUS, DayOfWeek.of(1)), new JavaMovingCondition(DayOfWeek.of(3), With.PREVIOUS, DayOfWeek.of(1)), new JavaMovingCondition(DayOfWeek.of(4), With.PREVIOUS, DayOfWeek.of(1)), new JavaMovingCondition(DayOfWeek.of(5), With.PREVIOUS, DayOfWeek.of(1)), new JavaMovingCondition(DayOfWeek.of(6), With.PREVIOUS, DayOfWeek.of(1)), new JavaMovingCondition(DayOfWeek.of(7), With.PREVIOUS, DayOfWeek.of(1))), MonthDay.of(3, 7)))
      .addFixed(new JavaFixed("SOVEREIGNS_BIRTHDAY", HolidayType.OFFICIAL_HOLIDAY, Year.of(2016), Year.of(2016), YearCycle.EVERY_YEAR, null, MonthDay.of(4, 21)))
      .addFixed(new JavaFixed("KINGS_CORONATION", HolidayType.OFFICIAL_HOLIDAY, Year.of(2023), Year.of(2023), YearCycle.EVERY_YEAR, null, MonthDay.of(5, 8)))
      .addFixed(new JavaFixed("QUEENS_PLATINUM_JUBILEE", HolidayType.OFFICIAL_HOLIDAY, Year.of(2022), Year.of(2022), YearCycle.EVERY_YEAR, null, MonthDay.of(6, 3)))
      .addFixed(new JavaFixed("SOVEREIGNS_BIRTHDAY", HolidayType.OFFICIAL_HOLIDAY, Year.of(2017), Year.of(2017), YearCycle.EVERY_YEAR, null, MonthDay.of(6, 17)))
      .addFixed(new JavaFixed("SOVEREIGNS_BIRTHDAY", HolidayType.OFFICIAL_HOLIDAY, Year.of(2019), Year.of(2019), YearCycle.EVERY_YEAR, null, MonthDay.of(6, 7)))
      .addFixed(new JavaFixed("SOVEREIGNS_BIRTHDAY", HolidayType.OFFICIAL_HOLIDAY, Year.of(2023), Year.of(2023), YearCycle.EVERY_YEAR, null, MonthDay.of(6, 16)))
      .addFixed(new JavaFixed("COLONY_DAY", HolidayType.OFFICIAL_HOLIDAY, Year.of(1956), Year.of(1977), YearCycle.EVERY_YEAR, List.of(new JavaMovingCondition(DayOfWeek.of(3), With.PREVIOUS, DayOfWeek.of(1)), new JavaMovingCondition(DayOfWeek.of(6), With.PREVIOUS, DayOfWeek.of(5)), new JavaMovingCondition(DayOfWeek.of(7), With.NEXT, DayOfWeek.of(1)), new JavaMovingCondition(DayOfWeek.of(2), With.PREVIOUS, DayOfWeek.of(1)), new JavaMovingCondition(DayOfWeek.of(4), With.NEXT, DayOfWeek.of(5))), MonthDay.of(7, 1)))
      .addFixed(new JavaFixed("TERRITORY_DAY", HolidayType.OFFICIAL_HOLIDAY, Year.of(1978), Year.of(2020), YearCycle.EVERY_YEAR, List.of(new JavaMovingCondition(DayOfWeek.of(3), With.PREVIOUS, DayOfWeek.of(1)), new JavaMovingCondition(DayOfWeek.of(6), With.PREVIOUS, DayOfWeek.of(5)), new JavaMovingCondition(DayOfWeek.of(7), With.NEXT, DayOfWeek.of(1)), new JavaMovingCondition(DayOfWeek.of(2), With.PREVIOUS, DayOfWeek.of(1)), new JavaMovingCondition(DayOfWeek.of(4), With.NEXT, DayOfWeek.of(5))), MonthDay.of(7, 1)))
      .addFixed(new JavaFixed("VIRGIN_ISLANDS_DAY", HolidayType.OFFICIAL_HOLIDAY, Year.of(2021), null, YearCycle.EVERY_YEAR, List.of(new JavaMovingCondition(DayOfWeek.of(2), With.NEXT, DayOfWeek.of(1)), new JavaMovingCondition(DayOfWeek.of(3), With.NEXT, DayOfWeek.of(1)), new JavaMovingCondition(DayOfWeek.of(4), With.NEXT, DayOfWeek.of(1)), new JavaMovingCondition(DayOfWeek.of(5), With.NEXT, DayOfWeek.of(1)), new JavaMovingCondition(DayOfWeek.of(6), With.NEXT, DayOfWeek.of(1)), new JavaMovingCondition(DayOfWeek.of(7), With.NEXT, DayOfWeek.of(1))), MonthDay.of(7, 1)))
      .addFixed(new JavaFixed("ST_URSULA", HolidayType.OFFICIAL_HOLIDAY, null, Year.of(2015), YearCycle.EVERY_YEAR, List.of(new JavaMovingCondition(DayOfWeek.of(3), With.PREVIOUS, DayOfWeek.of(1)), new JavaMovingCondition(DayOfWeek.of(6), With.PREVIOUS, DayOfWeek.of(5)), new JavaMovingCondition(DayOfWeek.of(7), With.NEXT, DayOfWeek.of(1)), new JavaMovingCondition(DayOfWeek.of(2), With.PREVIOUS, DayOfWeek.of(1)), new JavaMovingCondition(DayOfWeek.of(4), With.NEXT, DayOfWeek.of(5))), MonthDay.of(10, 21)))
      .addFixed(new JavaFixed("ST_URSULA", HolidayType.OFFICIAL_HOLIDAY, Year.of(2016), Year.of(2020), YearCycle.EVERY_YEAR, List.of(new JavaMovingCondition(DayOfWeek.of(3), With.NEXT, DayOfWeek.of(5)), new JavaMovingCondition(DayOfWeek.of(6), With.PREVIOUS, DayOfWeek.of(5)), new JavaMovingCondition(DayOfWeek.of(7), With.NEXT, DayOfWeek.of(1)), new JavaMovingCondition(DayOfWeek.of(2), With.PREVIOUS, DayOfWeek.of(1)), new JavaMovingCondition(DayOfWeek.of(4), With.NEXT, DayOfWeek.of(5))), MonthDay.of(10, 21)))
      .addFixed(new JavaFixed("CHRISTMAS", HolidayType.OFFICIAL_HOLIDAY, null, Year.of(2015), YearCycle.EVERY_YEAR, List.of(new JavaMovingCondition(DayOfWeek.of(6), With.NEXT, DayOfWeek.of(1)), new JavaMovingCondition(DayOfWeek.of(7), With.NEXT, DayOfWeek.of(2))), MonthDay.of(12, 25)))
      .addFixed(new JavaFixed("CHRISTMAS", HolidayType.OFFICIAL_HOLIDAY, Year.of(2016), Year.of(2016), YearCycle.EVERY_YEAR, List.of(new JavaMovingCondition(DayOfWeek.of(6), With.NEXT, DayOfWeek.of(1)), new JavaMovingCondition(DayOfWeek.of(7), With.NEXT, DayOfWeek.of(1))), MonthDay.of(12, 25)))
      .addFixed(new JavaFixed("CHRISTMAS", HolidayType.OFFICIAL_HOLIDAY, Year.of(2017), null, YearCycle.EVERY_YEAR, List.of(new JavaMovingCondition(DayOfWeek.of(6), With.NEXT, DayOfWeek.of(1)), new JavaMovingCondition(DayOfWeek.of(7), With.NEXT, DayOfWeek.of(2))), MonthDay.of(12, 25)))
      .addFixed(new JavaFixed("BOXING_DAY", HolidayType.OFFICIAL_HOLIDAY, null, Year.of(2015), YearCycle.EVERY_YEAR, List.of(new JavaMovingCondition(DayOfWeek.of(6), With.NEXT, DayOfWeek.of(1)), new JavaMovingCondition(DayOfWeek.of(7), With.NEXT, DayOfWeek.of(2))), MonthDay.of(12, 26)))
      .addFixed(new JavaFixed("BOXING_DAY", HolidayType.OFFICIAL_HOLIDAY, Year.of(2016), Year.of(2016), YearCycle.EVERY_YEAR, List.of(new JavaMovingCondition(DayOfWeek.of(6), With.NEXT, DayOfWeek.of(1)), new JavaMovingCondition(DayOfWeek.of(7), With.NEXT, DayOfWeek.of(2)), new JavaMovingCondition(DayOfWeek.of(1), With.NEXT, DayOfWeek.of(2))), MonthDay.of(12, 26)))
      .addFixed(new JavaFixed("BOXING_DAY", HolidayType.OFFICIAL_HOLIDAY, Year.of(2017), null, YearCycle.EVERY_YEAR, List.of(new JavaMovingCondition(DayOfWeek.of(6), With.NEXT, DayOfWeek.of(1)), new JavaMovingCondition(DayOfWeek.of(7), With.NEXT, DayOfWeek.of(2))), MonthDay.of(12, 26)))
      .addChristianHoliday(new JavaChristianHoliday("christian.GOOD_FRIDAY", HolidayType.OFFICIAL_HOLIDAY, null, null, YearCycle.EVERY_YEAR, null, ChristianHolidayType.GOOD_FRIDAY, Chronology.of("ISO")))
      .addChristianHoliday(new JavaChristianHoliday("christian.EASTER_MONDAY", HolidayType.OFFICIAL_HOLIDAY, null, null, YearCycle.EVERY_YEAR, null, ChristianHolidayType.EASTER_MONDAY, Chronology.of("ISO")))
      .addChristianHoliday(new JavaChristianHoliday("christian.WHIT_MONDAY", HolidayType.OFFICIAL_HOLIDAY, null, null, YearCycle.EVERY_YEAR, null, ChristianHolidayType.WHIT_MONDAY, Chronology.of("ISO")))
      .addFixedWeekday(new JavaFixedWeekdayInMonth("COMMONWEALTH_DAY", HolidayType.OFFICIAL_HOLIDAY, Year.of(1977), Year.of(2020), YearCycle.EVERY_YEAR, DayOfWeek.of(1), Month.of(3), Occurrance.SECOND))
      .addFixedWeekday(new JavaFixedWeekdayInMonth("SOVEREIGNS_BIRTHDAY", HolidayType.OFFICIAL_HOLIDAY, null, Year.of(2015), YearCycle.EVERY_YEAR, DayOfWeek.of(6), Month.of(6), Occurrance.SECOND))
      .addFixedWeekday(new JavaFixedWeekdayInMonth("SOVEREIGNS_BIRTHDAY", HolidayType.OFFICIAL_HOLIDAY, Year.of(2018), Year.of(2018), YearCycle.EVERY_YEAR, DayOfWeek.of(6), Month.of(6), Occurrance.SECOND))
      .addFixedWeekday(new JavaFixedWeekdayInMonth("SOVEREIGNS_BIRTHDAY", HolidayType.OFFICIAL_HOLIDAY, Year.of(2020), Year.of(2022), YearCycle.EVERY_YEAR, DayOfWeek.of(5), Month.of(6), Occurrance.SECOND))
      .addFixedWeekday(new JavaFixedWeekdayInMonth("SOVEREIGNS_BIRTHDAY", HolidayType.OFFICIAL_HOLIDAY, Year.of(2024), null, YearCycle.EVERY_YEAR, DayOfWeek.of(5), Month.of(6), Occurrance.SECOND))
      .addFixedWeekday(new JavaFixedWeekdayInMonth("FESTIVAL_MONDAY", HolidayType.OFFICIAL_HOLIDAY, null, Year.of(2020), YearCycle.EVERY_YEAR, DayOfWeek.of(1), Month.of(8), Occurrance.FIRST))
      .addFixedWeekday(new JavaFixedWeekdayInMonth("EMANCIPATION_MONDAY", HolidayType.OFFICIAL_HOLIDAY, Year.of(2021), null, YearCycle.EVERY_YEAR, DayOfWeek.of(1), Month.of(8), Occurrance.FIRST))
      .addFixedWeekday(new JavaFixedWeekdayInMonth("HEROES_AND_FOREPARENTS_DAY", HolidayType.OFFICIAL_HOLIDAY, Year.of(2021), null, YearCycle.EVERY_YEAR, DayOfWeek.of(1), Month.of(10), Occurrance.THIRD))
      .addFixedWeekday(new JavaFixedWeekdayInMonth("1949_GREAT_MARCH_AND_RESTORATION", HolidayType.OFFICIAL_HOLIDAY, Year.of(2021), null, YearCycle.EVERY_YEAR, DayOfWeek.of(1), Month.of(11), Occurrance.FOURTH))
      .addRelativeToWeekdayInMonth(new JavaRelativeToWeekdayInMonth("FESTIVAL_TUESDAY", HolidayType.OFFICIAL_HOLIDAY, null, Year.of(2020), YearCycle.EVERY_YEAR, new JavaFixedWeekdayInMonth("null", HolidayType.OFFICIAL_HOLIDAY, null, null, YearCycle.EVERY_YEAR, DayOfWeek.of(1), Month.of(8), Occurrance.FIRST), DayOfWeek.of(2), Relation.AFTER))
      .addRelativeToWeekdayInMonth(new JavaRelativeToWeekdayInMonth("FESTIVAL_WEDNESDAY", HolidayType.OFFICIAL_HOLIDAY, null, Year.of(2020), YearCycle.EVERY_YEAR, new JavaFixedWeekdayInMonth("null", HolidayType.OFFICIAL_HOLIDAY, null, null, YearCycle.EVERY_YEAR, DayOfWeek.of(1), Month.of(8), Occurrance.FIRST), DayOfWeek.of(3), Relation.AFTER))
      .addRelativeToWeekdayInMonth(new JavaRelativeToWeekdayInMonth("EMANCIPATION_TUESDAY", HolidayType.OFFICIAL_HOLIDAY, Year.of(2021), null, YearCycle.EVERY_YEAR, new JavaFixedWeekdayInMonth("null", HolidayType.OFFICIAL_HOLIDAY, null, null, YearCycle.EVERY_YEAR, DayOfWeek.of(1), Month.of(8), Occurrance.FIRST), DayOfWeek.of(2), Relation.AFTER))
      .addRelativeToWeekdayInMonth(new JavaRelativeToWeekdayInMonth("EMANCIPATION_WEDNESDAY", HolidayType.OFFICIAL_HOLIDAY, Year.of(2021), null, YearCycle.EVERY_YEAR, new JavaFixedWeekdayInMonth("null", HolidayType.OFFICIAL_HOLIDAY, null, null, YearCycle.EVERY_YEAR, DayOfWeek.of(1), Month.of(8), Occurrance.FIRST), DayOfWeek.of(3), Relation.AFTER))
      , null, "vg", "Virgin Islands (British)");
  }
}
