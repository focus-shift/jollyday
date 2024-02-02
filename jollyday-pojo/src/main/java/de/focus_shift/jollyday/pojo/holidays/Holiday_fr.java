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

public class Holiday_fr {

  public static JavaConfiguration configuration;

  static {
    configuration = new JavaConfiguration(new JavaHolidays()
      .addFixed(new JavaFixed("NEW_YEAR", HolidayType.OFFICIAL_HOLIDAY, null, null, YearCycle.EVERY_YEAR, null, MonthDay.of(1, 1)))
      .addFixed(new JavaFixed("LABOUR_DAY", HolidayType.OFFICIAL_HOLIDAY, null, null, YearCycle.EVERY_YEAR, null, MonthDay.of(5, 1)))
      .addFixed(new JavaFixed("VICTORY_DAY", HolidayType.OFFICIAL_HOLIDAY, null, null, YearCycle.EVERY_YEAR, null, MonthDay.of(5, 8)))
      .addFixed(new JavaFixed("NATIONAL_DAY", HolidayType.OFFICIAL_HOLIDAY, null, null, YearCycle.EVERY_YEAR, null, MonthDay.of(7, 14)))
      .addFixed(new JavaFixed("ASSUMPTION_MARY", HolidayType.OFFICIAL_HOLIDAY, null, null, YearCycle.EVERY_YEAR, null, MonthDay.of(8, 15)))
      .addFixed(new JavaFixed("ALL_SAINTS", HolidayType.OFFICIAL_HOLIDAY, null, null, YearCycle.EVERY_YEAR, null, MonthDay.of(11, 1)))
      .addFixed(new JavaFixed("REMEMBRANCE", HolidayType.OFFICIAL_HOLIDAY, null, null, YearCycle.EVERY_YEAR, null, MonthDay.of(11, 11)))
      .addFixed(new JavaFixed("CHRISTMAS", HolidayType.OFFICIAL_HOLIDAY, null, null, YearCycle.EVERY_YEAR, null, MonthDay.of(12, 25)))
      .addChristianHoliday(new JavaChristianHoliday("christian.EASTER_MONDAY", HolidayType.OFFICIAL_HOLIDAY, null, null, YearCycle.EVERY_YEAR, null, ChristianHolidayType.EASTER_MONDAY, Chronology.of("ISO")))
      .addChristianHoliday(new JavaChristianHoliday("christian.ASCENSION_DAY", HolidayType.OFFICIAL_HOLIDAY, null, null, YearCycle.EVERY_YEAR, null, ChristianHolidayType.ASCENSION_DAY, Chronology.of("ISO")))
      .addChristianHoliday(new JavaChristianHoliday("christian.WHIT_MONDAY", HolidayType.OFFICIAL_HOLIDAY, null, Year.of(2003), YearCycle.EVERY_YEAR, null, ChristianHolidayType.WHIT_MONDAY, Chronology.of("ISO")))
      .addChristianHoliday(new JavaChristianHoliday("christian.WHIT_MONDAY", HolidayType.OFFICIAL_HOLIDAY, Year.of(2008), null, YearCycle.EVERY_YEAR, null, ChristianHolidayType.WHIT_MONDAY, Chronology.of("ISO")))
      , List.of(new JavaConfiguration(new JavaHolidays()
      , List.of(new JavaConfiguration(new JavaHolidays()
      .addFixed(new JavaFixed("null", HolidayType.OFFICIAL_HOLIDAY, null, null, YearCycle.EVERY_YEAR, null, MonthDay.of(12, 26)))
      , null, "57", "Moselle"), new JavaConfiguration(new JavaHolidays()
      .addFixed(new JavaFixed("null", HolidayType.OFFICIAL_HOLIDAY, null, null, YearCycle.EVERY_YEAR, null, MonthDay.of(12, 26)))
      .addChristianHoliday(new JavaChristianHoliday("christian.GOOD_FRIDAY", HolidayType.OFFICIAL_HOLIDAY, null, null, YearCycle.EVERY_YEAR, null, ChristianHolidayType.GOOD_FRIDAY, Chronology.of("ISO")))
      , null, "67", "Bas-Rhin"), new JavaConfiguration(new JavaHolidays()
      .addFixed(new JavaFixed("null", HolidayType.OFFICIAL_HOLIDAY, null, null, YearCycle.EVERY_YEAR, null, MonthDay.of(12, 26)))
      .addChristianHoliday(new JavaChristianHoliday("christian.GOOD_FRIDAY", HolidayType.OFFICIAL_HOLIDAY, null, null, YearCycle.EVERY_YEAR, null, ChristianHolidayType.GOOD_FRIDAY, Chronology.of("ISO")))
      , null, "68", "Haut-Rhin")), "ges", "Grand Est"), new JavaConfiguration(new JavaHolidays()
      .addFixed(new JavaFixed("null", HolidayType.OFFICIAL_HOLIDAY, null, null, YearCycle.EVERY_YEAR, null, MonthDay.of(5, 27)))
      , null, "971", "Guadeloupe"), new JavaConfiguration(new JavaHolidays()
      .addFixed(new JavaFixed("null", HolidayType.OFFICIAL_HOLIDAY, null, null, YearCycle.EVERY_YEAR, null, MonthDay.of(5, 22)))
      , null, "972", "Martinique"), new JavaConfiguration(new JavaHolidays()
      .addFixed(new JavaFixed("null", HolidayType.OFFICIAL_HOLIDAY, null, null, YearCycle.EVERY_YEAR, null, MonthDay.of(6, 10)))
      , null, "973", "Guyane"), new JavaConfiguration(new JavaHolidays()
      .addFixed(new JavaFixed("null", HolidayType.OFFICIAL_HOLIDAY, null, null, YearCycle.EVERY_YEAR, null, MonthDay.of(12, 20)))
      , null, "974", "La Reunion")), "fr", "France");
  }
}
