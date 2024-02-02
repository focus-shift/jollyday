package de.focus_shift.jollyday.pojo.holidays;

import java.time.MonthDay;
import java.time.Year;
import java.time.chrono.Chronology;

import de.focus_shift.jollyday.core.HolidayType;
import de.focus_shift.jollyday.core.spi.ChristianHolidayType;
import de.focus_shift.jollyday.core.spi.YearCycle;
import de.focus_shift.jollyday.pojo.JavaChristianHoliday;
import de.focus_shift.jollyday.pojo.JavaConfiguration;
import de.focus_shift.jollyday.pojo.JavaFixed;
import de.focus_shift.jollyday.pojo.JavaHolidays;

public class Holiday_lt {

  public static JavaConfiguration configuration;

  static {
    configuration = new JavaConfiguration(new JavaHolidays()
      .addFixed(new JavaFixed("NEW_YEAR", HolidayType.OFFICIAL_HOLIDAY, null, null, YearCycle.EVERY_YEAR, null, MonthDay.of(1, 1)))
      .addFixed(new JavaFixed("UNIFICATION", HolidayType.OFFICIAL_HOLIDAY, null, null, YearCycle.EVERY_YEAR, null, MonthDay.of(2, 16)))
      .addFixed(new JavaFixed("INDEPENDENCE_DAY", HolidayType.OFFICIAL_HOLIDAY, null, null, YearCycle.EVERY_YEAR, null, MonthDay.of(3, 11)))
      .addFixed(new JavaFixed("LABOUR_DAY", HolidayType.OFFICIAL_HOLIDAY, null, null, YearCycle.EVERY_YEAR, null, MonthDay.of(5, 1)))
      .addFixed(new JavaFixed("JOHANNIS_DAY", HolidayType.OFFICIAL_HOLIDAY, null, null, YearCycle.EVERY_YEAR, null, MonthDay.of(6, 24)))
      .addFixed(new JavaFixed("STATEHOOD", HolidayType.OFFICIAL_HOLIDAY, null, null, YearCycle.EVERY_YEAR, null, MonthDay.of(7, 6)))
      .addFixed(new JavaFixed("ASSUMPTION_DAY", HolidayType.OFFICIAL_HOLIDAY, null, null, YearCycle.EVERY_YEAR, null, MonthDay.of(8, 15)))
      .addFixed(new JavaFixed("ALL_SAINTS", HolidayType.OFFICIAL_HOLIDAY, null, null, YearCycle.EVERY_YEAR, null, MonthDay.of(11, 1)))
      .addFixed(new JavaFixed("ALL_SOULS", HolidayType.OFFICIAL_HOLIDAY, Year.of(2020), null, YearCycle.EVERY_YEAR, null, MonthDay.of(11, 2)))
      .addFixed(new JavaFixed("CHRISTMAS_EVE", HolidayType.OFFICIAL_HOLIDAY, null, null, YearCycle.EVERY_YEAR, null, MonthDay.of(12, 25)))
      .addFixed(new JavaFixed("CHRISTMAS", HolidayType.OFFICIAL_HOLIDAY, null, null, YearCycle.EVERY_YEAR, null, MonthDay.of(12, 26)))
      .addChristianHoliday(new JavaChristianHoliday("christian.EASTER", HolidayType.OFFICIAL_HOLIDAY, null, null, YearCycle.EVERY_YEAR, null, ChristianHolidayType.EASTER, Chronology.of("ISO")))
      .addChristianHoliday(new JavaChristianHoliday("christian.EASTER_MONDAY", HolidayType.OFFICIAL_HOLIDAY, null, null, YearCycle.EVERY_YEAR, null, ChristianHolidayType.EASTER_MONDAY, Chronology.of("ISO")))
      , null, "lt", "Lithuania");
  }
}
