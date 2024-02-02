package de.focus_shift.jollyday.pojo.holidays;

import java.time.MonthDay;

import de.focus_shift.jollyday.core.HolidayType;
import de.focus_shift.jollyday.core.spi.IslamicHolidayType;
import de.focus_shift.jollyday.core.spi.YearCycle;
import de.focus_shift.jollyday.pojo.JavaConfiguration;
import de.focus_shift.jollyday.pojo.JavaFixed;
import de.focus_shift.jollyday.pojo.JavaHolidays;
import de.focus_shift.jollyday.pojo.JavaIslamicHoliday;

public class Holiday_tr {

  public static JavaConfiguration configuration;

  static {
    configuration = new JavaConfiguration(new JavaHolidays()
      .addFixed(new JavaFixed("NEW_YEAR", HolidayType.OFFICIAL_HOLIDAY, null, null, YearCycle.EVERY_YEAR, null, MonthDay.of(1, 1)))
      .addFixed(new JavaFixed("TURKEY_CHILDRENS_DAY", HolidayType.OFFICIAL_HOLIDAY, null, null, YearCycle.EVERY_YEAR, null, MonthDay.of(4, 23)))
      .addFixed(new JavaFixed("LABOUR_DAY", HolidayType.OFFICIAL_HOLIDAY, null, null, YearCycle.EVERY_YEAR, null, MonthDay.of(5, 1)))
      .addFixed(new JavaFixed("TURKEY_COMMEMORATION_OF_ATATURK", HolidayType.OFFICIAL_HOLIDAY, null, null, YearCycle.EVERY_YEAR, null, MonthDay.of(5, 19)))
      .addFixed(new JavaFixed("TURKEY_DEMOCRATIC_UNITY_DAY", HolidayType.OFFICIAL_HOLIDAY, null, null, YearCycle.EVERY_YEAR, null, MonthDay.of(7, 15)))
      .addFixed(new JavaFixed("TURKEY_VICTORY_DAY", HolidayType.OFFICIAL_HOLIDAY, null, null, YearCycle.EVERY_YEAR, null, MonthDay.of(8, 30)))
      .addFixed(new JavaFixed("TURKEY_REPUBLIC_DAY", HolidayType.OFFICIAL_HOLIDAY, null, null, YearCycle.EVERY_YEAR, null, MonthDay.of(10, 29)))
      .addIslamicHoliday(new JavaIslamicHoliday("islamic.ID_AL_FITR", HolidayType.OFFICIAL_HOLIDAY, null, null, YearCycle.EVERY_YEAR, null, IslamicHolidayType.ID_AL_FITR))
      .addIslamicHoliday(new JavaIslamicHoliday("islamic.ID_UL_ADHA", HolidayType.OFFICIAL_HOLIDAY, null, null, YearCycle.EVERY_YEAR, null, IslamicHolidayType.ID_UL_ADHA))
      , null, "tr", "Turkey");
  }
}
