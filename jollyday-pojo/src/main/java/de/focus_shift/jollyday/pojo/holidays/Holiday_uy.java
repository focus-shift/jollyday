package de.focus_shift.jollyday.pojo.holidays;

import java.time.DayOfWeek;
import java.time.MonthDay;
import java.time.chrono.Chronology;
import java.util.List;

import de.focus_shift.jollyday.core.HolidayType;
import de.focus_shift.jollyday.core.spi.ChristianHolidayType;
import de.focus_shift.jollyday.core.spi.With;
import de.focus_shift.jollyday.core.spi.YearCycle;
import de.focus_shift.jollyday.pojo.JavaChristianHoliday;
import de.focus_shift.jollyday.pojo.JavaConfiguration;
import de.focus_shift.jollyday.pojo.JavaFixed;
import de.focus_shift.jollyday.pojo.JavaHolidays;
import de.focus_shift.jollyday.pojo.JavaMovingCondition;

public class Holiday_uy {

  public static JavaConfiguration configuration;

  static {
    configuration = new JavaConfiguration(new JavaHolidays()
      .addFixed(new JavaFixed("NEW_YEAR", HolidayType.OFFICIAL_HOLIDAY, null, null, YearCycle.EVERY_YEAR, null, MonthDay.of(1, 1)))
      .addFixed(new JavaFixed("EPIPHANY", HolidayType.OFFICIAL_HOLIDAY, null, null, YearCycle.EVERY_YEAR, null, MonthDay.of(1, 6)))
      .addFixed(new JavaFixed("LANDING_33_EASTERNERS", HolidayType.OFFICIAL_HOLIDAY, null, null, YearCycle.EVERY_YEAR, List.of(new JavaMovingCondition(DayOfWeek.of(2), With.PREVIOUS, DayOfWeek.of(1)), new JavaMovingCondition(DayOfWeek.of(3), With.PREVIOUS, DayOfWeek.of(1)), new JavaMovingCondition(DayOfWeek.of(4), With.NEXT, DayOfWeek.of(1)), new JavaMovingCondition(DayOfWeek.of(5), With.NEXT, DayOfWeek.of(1))), MonthDay.of(4, 19)))
      .addFixed(new JavaFixed("LABOUR_DAY", HolidayType.OFFICIAL_HOLIDAY, null, null, YearCycle.EVERY_YEAR, null, MonthDay.of(5, 1)))
      .addFixed(new JavaFixed("LAS_PIEDRAS", HolidayType.OFFICIAL_HOLIDAY, null, null, YearCycle.EVERY_YEAR, List.of(new JavaMovingCondition(DayOfWeek.of(2), With.PREVIOUS, DayOfWeek.of(1)), new JavaMovingCondition(DayOfWeek.of(3), With.PREVIOUS, DayOfWeek.of(1)), new JavaMovingCondition(DayOfWeek.of(4), With.NEXT, DayOfWeek.of(1)), new JavaMovingCondition(DayOfWeek.of(5), With.NEXT, DayOfWeek.of(1))), MonthDay.of(5, 18)))
      .addFixed(new JavaFixed("ARTIGAS", HolidayType.OFFICIAL_HOLIDAY, null, null, YearCycle.EVERY_YEAR, null, MonthDay.of(6, 19)))
      .addFixed(new JavaFixed("CONSTITUTION_DAY", HolidayType.OFFICIAL_HOLIDAY, null, null, YearCycle.EVERY_YEAR, null, MonthDay.of(7, 18)))
      .addFixed(new JavaFixed("INDEPENDENCE_DAY", HolidayType.OFFICIAL_HOLIDAY, null, null, YearCycle.EVERY_YEAR, null, MonthDay.of(8, 25)))
      .addFixed(new JavaFixed("ALL_SOULS", HolidayType.OFFICIAL_HOLIDAY, null, null, YearCycle.EVERY_YEAR, null, MonthDay.of(11, 2)))
      .addFixed(new JavaFixed("CHRISTMAS", HolidayType.OFFICIAL_HOLIDAY, null, null, YearCycle.EVERY_YEAR, null, MonthDay.of(12, 25)))
      .addFixed(new JavaFixed("RACE", HolidayType.OFFICIAL_HOLIDAY, null, null, YearCycle.EVERY_YEAR, List.of(new JavaMovingCondition(DayOfWeek.of(2), With.PREVIOUS, DayOfWeek.of(1)), new JavaMovingCondition(DayOfWeek.of(3), With.PREVIOUS, DayOfWeek.of(1)), new JavaMovingCondition(DayOfWeek.of(4), With.NEXT, DayOfWeek.of(1)), new JavaMovingCondition(DayOfWeek.of(5), With.NEXT, DayOfWeek.of(1))), MonthDay.of(10, 12)))
      .addChristianHoliday(new JavaChristianHoliday("christian.EASTER", HolidayType.OFFICIAL_HOLIDAY, null, null, YearCycle.EVERY_YEAR, null, ChristianHolidayType.EASTER, Chronology.of("ISO")))
      .addChristianHoliday(new JavaChristianHoliday("christian.SHROVE_MONDAY", HolidayType.OFFICIAL_HOLIDAY, null, null, YearCycle.EVERY_YEAR, null, ChristianHolidayType.SHROVE_MONDAY, Chronology.of("ISO")))
      .addChristianHoliday(new JavaChristianHoliday("christian.CARNIVAL", HolidayType.OFFICIAL_HOLIDAY, null, null, YearCycle.EVERY_YEAR, null, ChristianHolidayType.CARNIVAL, Chronology.of("ISO")))
      .addChristianHoliday(new JavaChristianHoliday("christian.MAUNDY_THURSDAY", HolidayType.OFFICIAL_HOLIDAY, null, null, YearCycle.EVERY_YEAR, null, ChristianHolidayType.MAUNDY_THURSDAY, Chronology.of("ISO")))
      .addChristianHoliday(new JavaChristianHoliday("christian.GOOD_FRIDAY", HolidayType.OFFICIAL_HOLIDAY, null, null, YearCycle.EVERY_YEAR, null, ChristianHolidayType.GOOD_FRIDAY, Chronology.of("ISO")))
      , null, "uy", "Uruguay");
  }
}
