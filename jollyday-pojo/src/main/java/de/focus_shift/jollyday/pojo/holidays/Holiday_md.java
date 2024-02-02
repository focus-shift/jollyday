package de.focus_shift.jollyday.pojo.holidays;

import java.time.MonthDay;
import java.time.Year;
import java.time.chrono.Chronology;
import java.util.List;

import de.focus_shift.jollyday.core.HolidayType;
import de.focus_shift.jollyday.core.spi.ChristianHolidayType;
import de.focus_shift.jollyday.core.spi.YearCycle;
import de.focus_shift.jollyday.pojo.JavaChristianHoliday;
import de.focus_shift.jollyday.pojo.JavaConfiguration;
import de.focus_shift.jollyday.pojo.JavaFixed;
import de.focus_shift.jollyday.pojo.JavaHolidays;

public class Holiday_md {

  public static JavaConfiguration configuration;

  static {
    configuration = new JavaConfiguration(new JavaHolidays()
      .addFixed(new JavaFixed("NEW_YEAR", HolidayType.OFFICIAL_HOLIDAY, null, null, YearCycle.EVERY_YEAR, null, MonthDay.of(1, 1)))
      .addFixed(new JavaFixed("CHRISTMAS_EVE", HolidayType.OFFICIAL_HOLIDAY, null, null, YearCycle.EVERY_YEAR, null, MonthDay.of(1, 7)))
      .addFixed(new JavaFixed("CHRISTMAS", HolidayType.OFFICIAL_HOLIDAY, null, null, YearCycle.EVERY_YEAR, null, MonthDay.of(1, 8)))
      .addFixed(new JavaFixed("INTERNATIONAL_WOMAN", HolidayType.OFFICIAL_HOLIDAY, Year.of(1977), null, YearCycle.EVERY_YEAR, null, MonthDay.of(3, 8)))
      .addFixed(new JavaFixed("LABOUR_DAY", HolidayType.OFFICIAL_HOLIDAY, null, null, YearCycle.EVERY_YEAR, null, MonthDay.of(5, 1)))
      .addFixed(new JavaFixed("VICTORY", HolidayType.OFFICIAL_HOLIDAY, Year.of(1965), null, YearCycle.EVERY_YEAR, null, MonthDay.of(5, 9)))
      .addFixed(new JavaFixed("CHILDRENS_DAY", HolidayType.OFFICIAL_HOLIDAY, Year.of(2016), null, YearCycle.EVERY_YEAR, null, MonthDay.of(6, 1)))
      .addFixed(new JavaFixed("INDEPENDENCE_DAY", HolidayType.OFFICIAL_HOLIDAY, Year.of(1991), null, YearCycle.EVERY_YEAR, null, MonthDay.of(8, 27)))
      .addFixed(new JavaFixed("LANGUAGE_DAY", HolidayType.OFFICIAL_HOLIDAY, Year.of(1990), null, YearCycle.EVERY_YEAR, null, MonthDay.of(8, 31)))
      .addFixed(new JavaFixed("CHRISTMAS", HolidayType.OFFICIAL_HOLIDAY, null, null, YearCycle.EVERY_YEAR, null, MonthDay.of(12, 25)))
      .addChristianHoliday(new JavaChristianHoliday("christian.EASTER", HolidayType.OFFICIAL_HOLIDAY, null, null, YearCycle.EVERY_YEAR, null, ChristianHolidayType.EASTER, Chronology.of("Julian")))
      .addChristianHoliday(new JavaChristianHoliday("christian.EASTER_MONDAY", HolidayType.OFFICIAL_HOLIDAY, null, null, YearCycle.EVERY_YEAR, null, ChristianHolidayType.EASTER_MONDAY, Chronology.of("Julian")))
      , List.of(new JavaConfiguration(new JavaHolidays()
      .addFixed(new JavaFixed("REGIONAL", HolidayType.OFFICIAL_HOLIDAY, null, null, YearCycle.EVERY_YEAR, null, MonthDay.of(5, 22)))
      , null, "ba", "Bălți")), "md", "Moldova");
  }
}
