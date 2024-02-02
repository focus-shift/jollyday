package de.focus_shift.jollyday.pojo.holidays;

import java.time.MonthDay;

import de.focus_shift.jollyday.core.HolidayType;
import de.focus_shift.jollyday.core.spi.IslamicHolidayType;
import de.focus_shift.jollyday.core.spi.YearCycle;
import de.focus_shift.jollyday.pojo.JavaConfiguration;
import de.focus_shift.jollyday.pojo.JavaFixed;
import de.focus_shift.jollyday.pojo.JavaHolidays;
import de.focus_shift.jollyday.pojo.JavaIslamicHoliday;

public class Holiday_sa {

  public static JavaConfiguration configuration;

  static {
    configuration = new JavaConfiguration(new JavaHolidays()
      .addFixed(new JavaFixed("NATIONAL_DAY", HolidayType.OFFICIAL_HOLIDAY, null, null, YearCycle.EVERY_YEAR, null, MonthDay.of(9, 23)))
      .addIslamicHoliday(new JavaIslamicHoliday("islamic.RAMADAN_END", HolidayType.OFFICIAL_HOLIDAY, null, null, YearCycle.EVERY_YEAR, null, IslamicHolidayType.RAMADAN_END))
      .addIslamicHoliday(new JavaIslamicHoliday("islamic.ID_AL_FITR", HolidayType.OFFICIAL_HOLIDAY, null, null, YearCycle.EVERY_YEAR, null, IslamicHolidayType.ID_AL_FITR))
      .addIslamicHoliday(new JavaIslamicHoliday("islamic.ID_AL_FITR_2", HolidayType.OFFICIAL_HOLIDAY, null, null, YearCycle.EVERY_YEAR, null, IslamicHolidayType.ID_AL_FITR_2))
      .addIslamicHoliday(new JavaIslamicHoliday("islamic.ID_AL_FITR_3", HolidayType.OFFICIAL_HOLIDAY, null, null, YearCycle.EVERY_YEAR, null, IslamicHolidayType.ID_AL_FITR_3))
      .addIslamicHoliday(new JavaIslamicHoliday("islamic.ARAFAAT", HolidayType.OFFICIAL_HOLIDAY, null, null, YearCycle.EVERY_YEAR, null, IslamicHolidayType.ARAFAAT))
      .addIslamicHoliday(new JavaIslamicHoliday("islamic.ID_UL_ADHA", HolidayType.OFFICIAL_HOLIDAY, null, null, YearCycle.EVERY_YEAR, null, IslamicHolidayType.ID_UL_ADHA))
      .addIslamicHoliday(new JavaIslamicHoliday("islamic.ID_UL_ADHA_2", HolidayType.OFFICIAL_HOLIDAY, null, null, YearCycle.EVERY_YEAR, null, IslamicHolidayType.ID_UL_ADHA_2))
      .addIslamicHoliday(new JavaIslamicHoliday("islamic.ID_UL_ADHA_3", HolidayType.OFFICIAL_HOLIDAY, null, null, YearCycle.EVERY_YEAR, null, IslamicHolidayType.ID_UL_ADHA_3))
      , null, "sa", "Saudi Arabia");
  }
}
