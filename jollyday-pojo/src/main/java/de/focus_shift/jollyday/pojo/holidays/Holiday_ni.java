package de.focus_shift.jollyday.pojo.holidays;

import java.time.MonthDay;
import java.time.chrono.Chronology;
import java.util.List;

import de.focus_shift.jollyday.core.HolidayType;
import de.focus_shift.jollyday.core.spi.ChristianHolidayType;
import de.focus_shift.jollyday.core.spi.YearCycle;
import de.focus_shift.jollyday.pojo.JavaChristianHoliday;
import de.focus_shift.jollyday.pojo.JavaConfiguration;
import de.focus_shift.jollyday.pojo.JavaFixed;
import de.focus_shift.jollyday.pojo.JavaHolidays;

public class Holiday_ni {

  public static JavaConfiguration configuration;

  static {
    configuration = new JavaConfiguration(new JavaHolidays()
      .addFixed(new JavaFixed("NEW_YEAR", HolidayType.OFFICIAL_HOLIDAY, null, null, YearCycle.EVERY_YEAR, null, MonthDay.of(1, 1)))
      .addFixed(new JavaFixed("AIR_FORCE_DAY", HolidayType.OFFICIAL_HOLIDAY, null, null, YearCycle.EVERY_YEAR, null, MonthDay.of(2, 1)))
      .addFixed(new JavaFixed("LABOUR_DAY", HolidayType.OFFICIAL_HOLIDAY, null, null, YearCycle.EVERY_YEAR, null, MonthDay.of(5, 1)))
      .addFixed(new JavaFixed("ARMY_DAY", HolidayType.OFFICIAL_HOLIDAY, null, null, YearCycle.EVERY_YEAR, null, MonthDay.of(5, 27)))
      .addFixed(new JavaFixed("LIBERATION", HolidayType.OFFICIAL_HOLIDAY, null, null, YearCycle.EVERY_YEAR, null, MonthDay.of(7, 19)))
      .addFixed(new JavaFixed("BATTLE_JACINTO", HolidayType.OFFICIAL_HOLIDAY, null, null, YearCycle.EVERY_YEAR, null, MonthDay.of(9, 14)))
      .addFixed(new JavaFixed("INDEPENDENCE_DAY", HolidayType.OFFICIAL_HOLIDAY, null, null, YearCycle.EVERY_YEAR, null, MonthDay.of(9, 15)))
      .addFixed(new JavaFixed("COLUMBUS_DAY", HolidayType.OFFICIAL_HOLIDAY, null, null, YearCycle.EVERY_YEAR, null, MonthDay.of(10, 12)))
      .addFixed(new JavaFixed("IMMACULATE_CONCEPTION", HolidayType.OFFICIAL_HOLIDAY, null, null, YearCycle.EVERY_YEAR, null, MonthDay.of(12, 8)))
      .addFixed(new JavaFixed("CHRISTMAS", HolidayType.OFFICIAL_HOLIDAY, null, null, YearCycle.EVERY_YEAR, null, MonthDay.of(12, 25)))
      .addFixed(new JavaFixed("NEW_YEARS_EVE", HolidayType.OFFICIAL_HOLIDAY, null, null, YearCycle.EVERY_YEAR, null, MonthDay.of(12, 31)))
      .addChristianHoliday(new JavaChristianHoliday("christian.EASTER", HolidayType.OFFICIAL_HOLIDAY, null, null, YearCycle.EVERY_YEAR, null, ChristianHolidayType.EASTER, Chronology.of("ISO")))
      .addChristianHoliday(new JavaChristianHoliday("christian.MAUNDY_THURSDAY", HolidayType.OFFICIAL_HOLIDAY, null, null, YearCycle.EVERY_YEAR, null, ChristianHolidayType.MAUNDY_THURSDAY, Chronology.of("ISO")))
      , List.of(new JavaConfiguration(new JavaHolidays()
      .addFixed(new JavaFixed("SANTIAGO", HolidayType.OFFICIAL_HOLIDAY, null, null, YearCycle.EVERY_YEAR, null, MonthDay.of(7, 25)))
      , null, "bo", "Boaco"), new JavaConfiguration(new JavaHolidays()
      , List.of(new JavaConfiguration(new JavaHolidays()
      .addFixed(new JavaFixed("SANTA_ANA", HolidayType.OFFICIAL_HOLIDAY, null, null, YearCycle.EVERY_YEAR, null, MonthDay.of(7, 26)))
      , null, "na", "Nandaime")), "gr", "Granada"), new JavaConfiguration(new JavaHolidays()
      , List.of(new JavaConfiguration(new JavaHolidays()
      .addFixed(new JavaFixed("SANTIAGO", HolidayType.OFFICIAL_HOLIDAY, null, null, YearCycle.EVERY_YEAR, null, MonthDay.of(7, 25)))
      , null, "so", "Somoto")), "md", "Madriz"), new JavaConfiguration(new JavaHolidays()
      .addFixed(new JavaFixed("SANTIAGO", HolidayType.OFFICIAL_HOLIDAY, null, null, YearCycle.EVERY_YEAR, null, MonthDay.of(7, 25)))
      .addFixed(new JavaFixed("SANTO_DOMINGO", HolidayType.OFFICIAL_HOLIDAY, null, null, YearCycle.EVERY_YEAR, null, MonthDay.of(8, 1)))
      , null, "mn", "Managua"), new JavaConfiguration(new JavaHolidays()
      , List.of(new JavaConfiguration(new JavaHolidays()
      .addFixed(new JavaFixed("SANTA_ANA", HolidayType.OFFICIAL_HOLIDAY, null, null, YearCycle.EVERY_YEAR, null, MonthDay.of(7, 26)))
      , null, "ni", "Niquinohomo")), "ms", "Masaya"), new JavaConfiguration(new JavaHolidays()
      , List.of(new JavaConfiguration(new JavaHolidays()
      .addFixed(new JavaFixed("SANTA_ANA", HolidayType.OFFICIAL_HOLIDAY, null, null, YearCycle.EVERY_YEAR, null, MonthDay.of(7, 26)))
      , null, "mo", "Moyogalpa"), new JavaConfiguration(new JavaHolidays()
      .addFixed(new JavaFixed("SANTA_ANA", HolidayType.OFFICIAL_HOLIDAY, null, null, YearCycle.EVERY_YEAR, null, MonthDay.of(7, 26)))
      , null, "om", "Ometepe")), "ri", "Rivas")), "ni", "Nicaragua");
  }
}
