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

public class Holiday_cl {

  public static JavaConfiguration configuration;

  static {
    configuration = new JavaConfiguration(new JavaHolidays()
      .addFixed(new JavaFixed("NEW_YEAR", HolidayType.OFFICIAL_HOLIDAY, null, null, YearCycle.EVERY_YEAR, null, MonthDay.of(1, 1)))
      .addFixed(new JavaFixed("ELECTION_DAY", HolidayType.OFFICIAL_HOLIDAY, Year.of(2010), Year.of(2010), YearCycle.EVERY_YEAR, null, MonthDay.of(1, 17)))
      .addFixed(new JavaFixed("LABOUR_DAY", HolidayType.OFFICIAL_HOLIDAY, null, null, YearCycle.EVERY_YEAR, null, MonthDay.of(5, 1)))
      .addFixed(new JavaFixed("NAVY_DAY", HolidayType.OFFICIAL_HOLIDAY, null, null, YearCycle.EVERY_YEAR, null, MonthDay.of(5, 21)))
      .addFixed(new JavaFixed("ST_PETER_PAUL", HolidayType.OFFICIAL_HOLIDAY, null, null, YearCycle.EVERY_YEAR, null, MonthDay.of(6, 28)))
      .addFixed(new JavaFixed("VIRGIN_CARMEN", HolidayType.OFFICIAL_HOLIDAY, Year.of(2007), null, YearCycle.EVERY_YEAR, null, MonthDay.of(7, 16)))
      .addFixed(new JavaFixed("ASSUMPTION_MARY", HolidayType.OFFICIAL_HOLIDAY, null, null, YearCycle.EVERY_YEAR, null, MonthDay.of(8, 15)))
      .addFixed(new JavaFixed("NATIONAL_DAY", HolidayType.OFFICIAL_HOLIDAY, null, null, YearCycle.EVERY_YEAR, null, MonthDay.of(9, 18)))
      .addFixed(new JavaFixed("ARMY_DAY", HolidayType.OFFICIAL_HOLIDAY, null, null, YearCycle.EVERY_YEAR, null, MonthDay.of(9, 19)))
      .addFixed(new JavaFixed("COLUMBUS_DAY", HolidayType.OFFICIAL_HOLIDAY, null, null, YearCycle.EVERY_YEAR, null, MonthDay.of(10, 11)))
      .addFixed(new JavaFixed("REFORMATION_DAY", HolidayType.OFFICIAL_HOLIDAY, Year.of(2008), null, YearCycle.EVERY_YEAR, null, MonthDay.of(10, 31)))
      .addFixed(new JavaFixed("ALL_SAINTS", HolidayType.OFFICIAL_HOLIDAY, null, null, YearCycle.EVERY_YEAR, null, MonthDay.of(11, 1)))
      .addFixed(new JavaFixed("IMMACULATE_CONCEPTION", HolidayType.OFFICIAL_HOLIDAY, null, null, YearCycle.EVERY_YEAR, null, MonthDay.of(12, 8)))
      .addFixed(new JavaFixed("CHRISTMAS", HolidayType.OFFICIAL_HOLIDAY, null, null, YearCycle.EVERY_YEAR, null, MonthDay.of(12, 25)))
      .addFixed(new JavaFixed("NEW_YEARS_EVE", HolidayType.OFFICIAL_HOLIDAY, null, null, YearCycle.EVERY_YEAR, null, MonthDay.of(12, 31)))
      .addChristianHoliday(new JavaChristianHoliday("christian.EASTER", HolidayType.OFFICIAL_HOLIDAY, null, null, YearCycle.EVERY_YEAR, null, ChristianHolidayType.EASTER, Chronology.of("ISO")))
      .addChristianHoliday(new JavaChristianHoliday("christian.GOOD_FRIDAY", HolidayType.OFFICIAL_HOLIDAY, null, null, YearCycle.EVERY_YEAR, null, ChristianHolidayType.GOOD_FRIDAY, Chronology.of("ISO")))
      .addChristianHoliday(new JavaChristianHoliday("christian.EASTER_SATURDAY", HolidayType.OFFICIAL_HOLIDAY, null, null, YearCycle.EVERY_YEAR, null, ChristianHolidayType.EASTER_SATURDAY, Chronology.of("ISO")))
      .addChristianHoliday(new JavaChristianHoliday("christian.CORPUS_CHRISTI", HolidayType.OFFICIAL_HOLIDAY, null, Year.of(2006), YearCycle.EVERY_YEAR, null, ChristianHolidayType.CORPUS_CHRISTI, Chronology.of("ISO")))
      , null, "cl", "Chile");
  }
}
