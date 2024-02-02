package de.focus_shift.jollyday.pojo.holidays;

import java.time.DayOfWeek;
import java.time.MonthDay;
import java.time.Year;
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

public class Holiday_nl {

  public static JavaConfiguration configuration;

  static {
    configuration = new JavaConfiguration(new JavaHolidays()
      .addFixed(new JavaFixed("NEW_YEAR", HolidayType.OFFICIAL_HOLIDAY, Year.of(1967), null, YearCycle.EVERY_YEAR, null, MonthDay.of(1, 1)))
      .addFixed(new JavaFixed("KINGS_DAY", HolidayType.OFFICIAL_HOLIDAY, Year.of(2014), null, YearCycle.EVERY_YEAR, List.of(new JavaMovingCondition(DayOfWeek.of(7), With.PREVIOUS, DayOfWeek.of(6))), MonthDay.of(4, 27)))
      .addFixed(new JavaFixed("QUEENS_BIRTHDAY", HolidayType.OFFICIAL_HOLIDAY, Year.of(1885), Year.of(1947), YearCycle.EVERY_YEAR, null, MonthDay.of(8, 31)))
      .addFixed(new JavaFixed("QUEENS_BIRTHDAY", HolidayType.OFFICIAL_HOLIDAY, Year.of(1948), Year.of(1979), YearCycle.EVERY_YEAR, List.of(new JavaMovingCondition(DayOfWeek.of(7), With.NEXT, DayOfWeek.of(1))), MonthDay.of(4, 30)))
      .addFixed(new JavaFixed("QUEENS_BIRTHDAY", HolidayType.OFFICIAL_HOLIDAY, Year.of(1980), Year.of(2013), YearCycle.EVERY_YEAR, List.of(new JavaMovingCondition(DayOfWeek.of(7), With.PREVIOUS, DayOfWeek.of(6))), MonthDay.of(4, 30)))
      .addFixed(new JavaFixed("LIBERATION", HolidayType.OFFICIAL_HOLIDAY, Year.of(1945), Year.of(1989), YearCycle.FIVE_YEARS, null, MonthDay.of(5, 5)))
      .addFixed(new JavaFixed("LIBERATION", HolidayType.OFFICIAL_HOLIDAY, Year.of(1990), null, YearCycle.EVERY_YEAR, null, MonthDay.of(5, 5)))
      .addFixed(new JavaFixed("FIRST_CHRISTMAS_DAY", HolidayType.OFFICIAL_HOLIDAY, null, null, YearCycle.EVERY_YEAR, null, MonthDay.of(12, 25)))
      .addFixed(new JavaFixed("SECOND_CHRISTMAS_DAY", HolidayType.OFFICIAL_HOLIDAY, null, null, YearCycle.EVERY_YEAR, null, MonthDay.of(12, 26)))
      .addChristianHoliday(new JavaChristianHoliday("christian.GOOD_FRIDAY", HolidayType.OFFICIAL_HOLIDAY, null, null, YearCycle.EVERY_YEAR, null, ChristianHolidayType.GOOD_FRIDAY, Chronology.of("ISO")))
      .addChristianHoliday(new JavaChristianHoliday("christian.EASTER", HolidayType.OFFICIAL_HOLIDAY, null, null, YearCycle.EVERY_YEAR, null, ChristianHolidayType.EASTER, Chronology.of("ISO")))
      .addChristianHoliday(new JavaChristianHoliday("christian.EASTER_MONDAY", HolidayType.OFFICIAL_HOLIDAY, Year.of(1642), null, YearCycle.EVERY_YEAR, null, ChristianHolidayType.EASTER_MONDAY, Chronology.of("ISO")))
      .addChristianHoliday(new JavaChristianHoliday("christian.ASCENSION_DAY", HolidayType.OFFICIAL_HOLIDAY, null, null, YearCycle.EVERY_YEAR, null, ChristianHolidayType.ASCENSION_DAY, Chronology.of("ISO")))
      .addChristianHoliday(new JavaChristianHoliday("christian.PENTECOST", HolidayType.OFFICIAL_HOLIDAY, null, null, YearCycle.EVERY_YEAR, null, ChristianHolidayType.PENTECOST, Chronology.of("ISO")))
      .addChristianHoliday(new JavaChristianHoliday("christian.PENTECOST_MONDAY", HolidayType.OFFICIAL_HOLIDAY, null, null, YearCycle.EVERY_YEAR, null, ChristianHolidayType.PENTECOST_MONDAY, Chronology.of("ISO")))
      , null, "nl", "Netherlands");
  }
}
