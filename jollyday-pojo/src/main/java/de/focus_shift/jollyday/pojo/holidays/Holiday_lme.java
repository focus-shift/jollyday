package de.focus_shift.jollyday.pojo.holidays;

import java.time.DayOfWeek;
import java.time.Month;
import java.time.MonthDay;
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

public class Holiday_lme {

  public static JavaConfiguration configuration;

  static {
    configuration = new JavaConfiguration(new JavaHolidays()
      .addFixed(new JavaFixed("NEW_YEAR", HolidayType.OFFICIAL_HOLIDAY, null, null, YearCycle.EVERY_YEAR, null, MonthDay.of(1, 1)))
      .addFixed(new JavaFixed("BANK_HOLIDAY", HolidayType.OFFICIAL_HOLIDAY, null, null, YearCycle.EVERY_YEAR, null, MonthDay.of(8, 28)))
      .addFixed(new JavaFixed("BOXING_DAY", HolidayType.OFFICIAL_HOLIDAY, null, null, YearCycle.EVERY_YEAR, List.of(new JavaMovingCondition(DayOfWeek.of(6), With.NEXT, DayOfWeek.of(1)), new JavaMovingCondition(DayOfWeek.of(7), With.NEXT, DayOfWeek.of(2)), new JavaMovingCondition(DayOfWeek.of(1), With.NEXT, DayOfWeek.of(2))), MonthDay.of(12, 26)))
      .addFixed(new JavaFixed("NEW_YEARS_EVE", HolidayType.OFFICIAL_HOLIDAY, null, null, YearCycle.EVERY_YEAR, null, MonthDay.of(12, 31)))
      .addFixed(new JavaFixed("CHRISTMAS_EVE", HolidayType.OFFICIAL_HOLIDAY, null, null, YearCycle.EVERY_YEAR, null, MonthDay.of(12, 24)))
      .addFixed(new JavaFixed("CHRISTMAS", HolidayType.OFFICIAL_HOLIDAY, null, null, YearCycle.EVERY_YEAR, List.of(new JavaMovingCondition(DayOfWeek.of(6), With.NEXT, DayOfWeek.of(1)), new JavaMovingCondition(DayOfWeek.of(7), With.NEXT, DayOfWeek.of(1))), MonthDay.of(12, 25)))
      .addChristianHoliday(new JavaChristianHoliday("christian.EASTER", HolidayType.OFFICIAL_HOLIDAY, null, null, YearCycle.EVERY_YEAR, null, ChristianHolidayType.EASTER, Chronology.of("ISO")))
      .addChristianHoliday(new JavaChristianHoliday("christian.GOOD_FRIDAY", HolidayType.OFFICIAL_HOLIDAY, null, null, YearCycle.EVERY_YEAR, null, ChristianHolidayType.GOOD_FRIDAY, Chronology.of("ISO")))
      .addFixedWeekday(new JavaFixedWeekdayInMonth("BANK_HOLIDAY", HolidayType.OFFICIAL_HOLIDAY, null, null, YearCycle.EVERY_YEAR, DayOfWeek.of(1), Month.of(5), Occurrance.FIRST))
      .addFixedWeekday(new JavaFixedWeekdayInMonth("BANK_HOLIDAY", HolidayType.OFFICIAL_HOLIDAY, null, null, YearCycle.EVERY_YEAR, DayOfWeek.of(1), Month.of(5), Occurrance.LAST))
      , null, "lme", "London metal exchange");
  }
}
