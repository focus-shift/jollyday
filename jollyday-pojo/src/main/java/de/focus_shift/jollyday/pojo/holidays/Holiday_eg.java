package de.focus_shift.jollyday.pojo.holidays;

import java.time.MonthDay;
import java.time.Year;
import java.time.chrono.Chronology;

import de.focus_shift.jollyday.core.HolidayType;
import de.focus_shift.jollyday.core.spi.ChristianHolidayType;
import de.focus_shift.jollyday.core.spi.IslamicHolidayType;
import de.focus_shift.jollyday.core.spi.YearCycle;
import de.focus_shift.jollyday.pojo.JavaChristianHoliday;
import de.focus_shift.jollyday.pojo.JavaConfiguration;
import de.focus_shift.jollyday.pojo.JavaFixed;
import de.focus_shift.jollyday.pojo.JavaHolidays;
import de.focus_shift.jollyday.pojo.JavaIslamicHoliday;

public class Holiday_eg {

  public static JavaConfiguration configuration;

  static {
    configuration = new JavaConfiguration(new JavaHolidays()
      .addFixed(new JavaFixed("EGYPT_COPTIC_CHRISTMAS", HolidayType.OFFICIAL_HOLIDAY, null, null, YearCycle.EVERY_YEAR, null, MonthDay.of(1, 7)))
      .addFixed(new JavaFixed("EGYPT_25_JAN_REVOLUTION", HolidayType.OFFICIAL_HOLIDAY, Year.of(2012), null, YearCycle.EVERY_YEAR, null, MonthDay.of(1, 25)))
      .addFixed(new JavaFixed("EGYPT_SINAI_LIBERATION", HolidayType.OFFICIAL_HOLIDAY, null, null, YearCycle.EVERY_YEAR, null, MonthDay.of(4, 25)))
      .addFixed(new JavaFixed("LABOUR_DAY", HolidayType.OFFICIAL_HOLIDAY, null, null, YearCycle.EVERY_YEAR, null, MonthDay.of(5, 1)))
      .addFixed(new JavaFixed("EGYPT_30_JUNE_REVOLUTION", HolidayType.OFFICIAL_HOLIDAY, Year.of(2015), null, YearCycle.EVERY_YEAR, null, MonthDay.of(6, 30)))
      .addFixed(new JavaFixed("EGYPT_23_JULY_REVOLUTION", HolidayType.OFFICIAL_HOLIDAY, null, null, YearCycle.EVERY_YEAR, null, MonthDay.of(7, 23)))
      .addFixed(new JavaFixed("EGYPT_ARMED_FORCES_DAY", HolidayType.OFFICIAL_HOLIDAY, null, null, YearCycle.EVERY_YEAR, null, MonthDay.of(10, 6)))
      .addChristianHoliday(new JavaChristianHoliday("christian.EASTER_MONDAY", HolidayType.OFFICIAL_HOLIDAY, null, null, YearCycle.EVERY_YEAR, null, ChristianHolidayType.EASTER_MONDAY, Chronology.of("Julian")))
      .addIslamicHoliday(new JavaIslamicHoliday("islamic.ID_AL_FITR", HolidayType.OFFICIAL_HOLIDAY, null, null, YearCycle.EVERY_YEAR, null, IslamicHolidayType.ID_AL_FITR))
      .addIslamicHoliday(new JavaIslamicHoliday("islamic.ID_AL_FITR_2", HolidayType.OFFICIAL_HOLIDAY, null, null, YearCycle.EVERY_YEAR, null, IslamicHolidayType.ID_AL_FITR_2))
      .addIslamicHoliday(new JavaIslamicHoliday("islamic.ID_AL_FITR_3", HolidayType.OFFICIAL_HOLIDAY, null, null, YearCycle.EVERY_YEAR, null, IslamicHolidayType.ID_AL_FITR_3))
      .addIslamicHoliday(new JavaIslamicHoliday("islamic.ARAFAAT", HolidayType.OFFICIAL_HOLIDAY, null, null, YearCycle.EVERY_YEAR, null, IslamicHolidayType.ARAFAAT))
      .addIslamicHoliday(new JavaIslamicHoliday("islamic.ID_UL_ADHA", HolidayType.OFFICIAL_HOLIDAY, null, null, YearCycle.EVERY_YEAR, null, IslamicHolidayType.ID_UL_ADHA))
      .addIslamicHoliday(new JavaIslamicHoliday("islamic.ID_UL_ADHA_2", HolidayType.OFFICIAL_HOLIDAY, null, null, YearCycle.EVERY_YEAR, null, IslamicHolidayType.ID_UL_ADHA_2))
      .addIslamicHoliday(new JavaIslamicHoliday("islamic.ID_UL_ADHA_3", HolidayType.OFFICIAL_HOLIDAY, null, null, YearCycle.EVERY_YEAR, null, IslamicHolidayType.ID_UL_ADHA_3))
      .addIslamicHoliday(new JavaIslamicHoliday("islamic.NEWYEAR", HolidayType.OFFICIAL_HOLIDAY, null, null, YearCycle.EVERY_YEAR, null, IslamicHolidayType.NEWYEAR))
      .addIslamicHoliday(new JavaIslamicHoliday("islamic.MAWLID_AN_NABI", HolidayType.OFFICIAL_HOLIDAY, null, null, YearCycle.EVERY_YEAR, null, IslamicHolidayType.MAWLID_AN_NABI))
      , null, "eg", "Egypt");
  }
}
