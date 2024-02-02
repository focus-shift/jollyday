package de.focus_shift.jollyday.pojo.holidays;

import java.time.DayOfWeek;
import java.time.MonthDay;
import java.time.Year;
import java.time.chrono.Chronology;
import java.util.List;

import de.focus_shift.jollyday.core.HolidayType;
import de.focus_shift.jollyday.core.spi.ChristianHolidayType;
import de.focus_shift.jollyday.core.spi.IslamicHolidayType;
import de.focus_shift.jollyday.core.spi.With;
import de.focus_shift.jollyday.core.spi.YearCycle;
import de.focus_shift.jollyday.pojo.JavaChristianHoliday;
import de.focus_shift.jollyday.pojo.JavaConfiguration;
import de.focus_shift.jollyday.pojo.JavaFixed;
import de.focus_shift.jollyday.pojo.JavaHolidays;
import de.focus_shift.jollyday.pojo.JavaIslamicHoliday;
import de.focus_shift.jollyday.pojo.JavaMovingCondition;

public class Holiday_sg {

  public static JavaConfiguration configuration;

  static {
    configuration = new JavaConfiguration(new JavaHolidays()
      .addFixed(new JavaFixed("NEW_YEAR", HolidayType.OFFICIAL_HOLIDAY, null, null, YearCycle.EVERY_YEAR, List.of(new JavaMovingCondition(DayOfWeek.of(7), With.NEXT, DayOfWeek.of(1))), MonthDay.of(1, 1)))
      .addFixed(new JavaFixed("CHINESE_NEW_YEAR", HolidayType.OFFICIAL_HOLIDAY, Year.of(2013), Year.of(2013), YearCycle.EVERY_YEAR, List.of(new JavaMovingCondition(DayOfWeek.of(7), With.NEXT, DayOfWeek.of(2))), MonthDay.of(2, 10)))
      .addFixed(new JavaFixed("CHINESE_NEW_YEAR", HolidayType.OFFICIAL_HOLIDAY, Year.of(2013), Year.of(2013), YearCycle.EVERY_YEAR, List.of(new JavaMovingCondition(DayOfWeek.of(7), With.NEXT, DayOfWeek.of(1))), MonthDay.of(2, 11)))
      .addFixed(new JavaFixed("CHINESE_NEW_YEAR", HolidayType.OFFICIAL_HOLIDAY, Year.of(2014), Year.of(2014), YearCycle.EVERY_YEAR, List.of(new JavaMovingCondition(DayOfWeek.of(7), With.NEXT, DayOfWeek.of(2))), MonthDay.of(1, 31)))
      .addFixed(new JavaFixed("CHINESE_NEW_YEAR", HolidayType.OFFICIAL_HOLIDAY, Year.of(2014), Year.of(2014), YearCycle.EVERY_YEAR, List.of(new JavaMovingCondition(DayOfWeek.of(7), With.NEXT, DayOfWeek.of(1))), MonthDay.of(2, 1)))
      .addFixed(new JavaFixed("CHINESE_NEW_YEAR", HolidayType.OFFICIAL_HOLIDAY, Year.of(2015), Year.of(2015), YearCycle.EVERY_YEAR, List.of(new JavaMovingCondition(DayOfWeek.of(7), With.NEXT, DayOfWeek.of(2))), MonthDay.of(2, 19)))
      .addFixed(new JavaFixed("CHINESE_NEW_YEAR", HolidayType.OFFICIAL_HOLIDAY, Year.of(2015), Year.of(2015), YearCycle.EVERY_YEAR, List.of(new JavaMovingCondition(DayOfWeek.of(7), With.NEXT, DayOfWeek.of(1))), MonthDay.of(2, 20)))
      .addFixed(new JavaFixed("CHINESE_NEW_YEAR", HolidayType.OFFICIAL_HOLIDAY, Year.of(2016), Year.of(2016), YearCycle.EVERY_YEAR, List.of(new JavaMovingCondition(DayOfWeek.of(7), With.NEXT, DayOfWeek.of(2))), MonthDay.of(2, 8)))
      .addFixed(new JavaFixed("CHINESE_NEW_YEAR", HolidayType.OFFICIAL_HOLIDAY, Year.of(2016), Year.of(2016), YearCycle.EVERY_YEAR, List.of(new JavaMovingCondition(DayOfWeek.of(7), With.NEXT, DayOfWeek.of(1))), MonthDay.of(2, 9)))
      .addFixed(new JavaFixed("CHINESE_NEW_YEAR", HolidayType.OFFICIAL_HOLIDAY, Year.of(2017), Year.of(2017), YearCycle.EVERY_YEAR, List.of(new JavaMovingCondition(DayOfWeek.of(7), With.NEXT, DayOfWeek.of(2))), MonthDay.of(1, 28)))
      .addFixed(new JavaFixed("CHINESE_NEW_YEAR", HolidayType.OFFICIAL_HOLIDAY, Year.of(2017), Year.of(2017), YearCycle.EVERY_YEAR, List.of(new JavaMovingCondition(DayOfWeek.of(7), With.NEXT, DayOfWeek.of(1))), MonthDay.of(1, 29)))
      .addFixed(new JavaFixed("CHINESE_NEW_YEAR", HolidayType.OFFICIAL_HOLIDAY, Year.of(2018), Year.of(2018), YearCycle.EVERY_YEAR, List.of(new JavaMovingCondition(DayOfWeek.of(7), With.NEXT, DayOfWeek.of(2))), MonthDay.of(2, 16)))
      .addFixed(new JavaFixed("CHINESE_NEW_YEAR", HolidayType.OFFICIAL_HOLIDAY, Year.of(2018), Year.of(2018), YearCycle.EVERY_YEAR, List.of(new JavaMovingCondition(DayOfWeek.of(7), With.NEXT, DayOfWeek.of(1))), MonthDay.of(2, 17)))
      .addFixed(new JavaFixed("CHINESE_NEW_YEAR", HolidayType.OFFICIAL_HOLIDAY, Year.of(2019), Year.of(2019), YearCycle.EVERY_YEAR, List.of(new JavaMovingCondition(DayOfWeek.of(7), With.NEXT, DayOfWeek.of(2))), MonthDay.of(2, 5)))
      .addFixed(new JavaFixed("CHINESE_NEW_YEAR", HolidayType.OFFICIAL_HOLIDAY, Year.of(2019), Year.of(2019), YearCycle.EVERY_YEAR, List.of(new JavaMovingCondition(DayOfWeek.of(7), With.NEXT, DayOfWeek.of(1))), MonthDay.of(2, 6)))
      .addFixed(new JavaFixed("CHINESE_NEW_YEAR", HolidayType.OFFICIAL_HOLIDAY, Year.of(2020), Year.of(2020), YearCycle.EVERY_YEAR, List.of(new JavaMovingCondition(DayOfWeek.of(7), With.NEXT, DayOfWeek.of(2))), MonthDay.of(1, 25)))
      .addFixed(new JavaFixed("CHINESE_NEW_YEAR", HolidayType.OFFICIAL_HOLIDAY, Year.of(2020), Year.of(2020), YearCycle.EVERY_YEAR, List.of(new JavaMovingCondition(DayOfWeek.of(7), With.NEXT, DayOfWeek.of(1))), MonthDay.of(1, 26)))
      .addFixed(new JavaFixed("CHINESE_NEW_YEAR", HolidayType.OFFICIAL_HOLIDAY, Year.of(2021), Year.of(2021), YearCycle.EVERY_YEAR, List.of(new JavaMovingCondition(DayOfWeek.of(7), With.NEXT, DayOfWeek.of(2))), MonthDay.of(2, 12)))
      .addFixed(new JavaFixed("CHINESE_NEW_YEAR", HolidayType.OFFICIAL_HOLIDAY, Year.of(2021), Year.of(2021), YearCycle.EVERY_YEAR, List.of(new JavaMovingCondition(DayOfWeek.of(7), With.NEXT, DayOfWeek.of(1))), MonthDay.of(2, 13)))
      .addFixed(new JavaFixed("CHINESE_NEW_YEAR", HolidayType.OFFICIAL_HOLIDAY, Year.of(2022), Year.of(2022), YearCycle.EVERY_YEAR, List.of(new JavaMovingCondition(DayOfWeek.of(7), With.NEXT, DayOfWeek.of(2))), MonthDay.of(2, 1)))
      .addFixed(new JavaFixed("CHINESE_NEW_YEAR", HolidayType.OFFICIAL_HOLIDAY, Year.of(2022), Year.of(2022), YearCycle.EVERY_YEAR, List.of(new JavaMovingCondition(DayOfWeek.of(7), With.NEXT, DayOfWeek.of(1))), MonthDay.of(2, 2)))
      .addFixed(new JavaFixed("CHINESE_NEW_YEAR", HolidayType.OFFICIAL_HOLIDAY, Year.of(2023), Year.of(2023), YearCycle.EVERY_YEAR, List.of(new JavaMovingCondition(DayOfWeek.of(7), With.NEXT, DayOfWeek.of(2))), MonthDay.of(1, 22)))
      .addFixed(new JavaFixed("CHINESE_NEW_YEAR", HolidayType.OFFICIAL_HOLIDAY, Year.of(2023), Year.of(2023), YearCycle.EVERY_YEAR, List.of(new JavaMovingCondition(DayOfWeek.of(7), With.NEXT, DayOfWeek.of(1))), MonthDay.of(1, 23)))
      .addFixed(new JavaFixed("CHINESE_NEW_YEAR", HolidayType.OFFICIAL_HOLIDAY, Year.of(2024), Year.of(2024), YearCycle.EVERY_YEAR, List.of(new JavaMovingCondition(DayOfWeek.of(7), With.NEXT, DayOfWeek.of(2))), MonthDay.of(2, 10)))
      .addFixed(new JavaFixed("CHINESE_NEW_YEAR", HolidayType.OFFICIAL_HOLIDAY, Year.of(2024), Year.of(2024), YearCycle.EVERY_YEAR, List.of(new JavaMovingCondition(DayOfWeek.of(7), With.NEXT, DayOfWeek.of(1))), MonthDay.of(2, 11)))
      .addFixed(new JavaFixed("LABOUR_DAY", HolidayType.OFFICIAL_HOLIDAY, null, null, YearCycle.EVERY_YEAR, List.of(new JavaMovingCondition(DayOfWeek.of(7), With.NEXT, DayOfWeek.of(1))), MonthDay.of(5, 1)))
      .addFixed(new JavaFixed("VESAK_DAY", HolidayType.OFFICIAL_HOLIDAY, Year.of(2013), Year.of(2013), YearCycle.EVERY_YEAR, List.of(new JavaMovingCondition(DayOfWeek.of(7), With.NEXT, DayOfWeek.of(1))), MonthDay.of(5, 24)))
      .addFixed(new JavaFixed("VESAK_DAY", HolidayType.OFFICIAL_HOLIDAY, Year.of(2014), Year.of(2014), YearCycle.EVERY_YEAR, List.of(new JavaMovingCondition(DayOfWeek.of(7), With.NEXT, DayOfWeek.of(1))), MonthDay.of(5, 13)))
      .addFixed(new JavaFixed("VESAK_DAY", HolidayType.OFFICIAL_HOLIDAY, Year.of(2015), Year.of(2015), YearCycle.EVERY_YEAR, List.of(new JavaMovingCondition(DayOfWeek.of(7), With.NEXT, DayOfWeek.of(1))), MonthDay.of(6, 1)))
      .addFixed(new JavaFixed("VESAK_DAY", HolidayType.OFFICIAL_HOLIDAY, Year.of(2016), Year.of(2016), YearCycle.EVERY_YEAR, List.of(new JavaMovingCondition(DayOfWeek.of(7), With.NEXT, DayOfWeek.of(1))), MonthDay.of(5, 21)))
      .addFixed(new JavaFixed("VESAK_DAY", HolidayType.OFFICIAL_HOLIDAY, Year.of(2017), Year.of(2017), YearCycle.EVERY_YEAR, List.of(new JavaMovingCondition(DayOfWeek.of(7), With.NEXT, DayOfWeek.of(1))), MonthDay.of(5, 10)))
      .addFixed(new JavaFixed("VESAK_DAY", HolidayType.OFFICIAL_HOLIDAY, Year.of(2018), Year.of(2018), YearCycle.EVERY_YEAR, List.of(new JavaMovingCondition(DayOfWeek.of(7), With.NEXT, DayOfWeek.of(1))), MonthDay.of(5, 29)))
      .addFixed(new JavaFixed("VESAK_DAY", HolidayType.OFFICIAL_HOLIDAY, Year.of(2019), Year.of(2019), YearCycle.EVERY_YEAR, List.of(new JavaMovingCondition(DayOfWeek.of(7), With.NEXT, DayOfWeek.of(1))), MonthDay.of(5, 19)))
      .addFixed(new JavaFixed("VESAK_DAY", HolidayType.OFFICIAL_HOLIDAY, Year.of(2020), Year.of(2020), YearCycle.EVERY_YEAR, List.of(new JavaMovingCondition(DayOfWeek.of(7), With.NEXT, DayOfWeek.of(1))), MonthDay.of(5, 7)))
      .addFixed(new JavaFixed("VESAK_DAY", HolidayType.OFFICIAL_HOLIDAY, Year.of(2021), Year.of(2021), YearCycle.EVERY_YEAR, List.of(new JavaMovingCondition(DayOfWeek.of(7), With.NEXT, DayOfWeek.of(1))), MonthDay.of(5, 26)))
      .addFixed(new JavaFixed("VESAK_DAY", HolidayType.OFFICIAL_HOLIDAY, Year.of(2022), Year.of(2022), YearCycle.EVERY_YEAR, List.of(new JavaMovingCondition(DayOfWeek.of(7), With.NEXT, DayOfWeek.of(1))), MonthDay.of(5, 15)))
      .addFixed(new JavaFixed("VESAK_DAY", HolidayType.OFFICIAL_HOLIDAY, Year.of(2023), Year.of(2023), YearCycle.EVERY_YEAR, List.of(new JavaMovingCondition(DayOfWeek.of(7), With.NEXT, DayOfWeek.of(1))), MonthDay.of(6, 2)))
      .addFixed(new JavaFixed("VESAK_DAY", HolidayType.OFFICIAL_HOLIDAY, Year.of(2024), Year.of(2024), YearCycle.EVERY_YEAR, List.of(new JavaMovingCondition(DayOfWeek.of(7), With.NEXT, DayOfWeek.of(1))), MonthDay.of(5, 22)))
      .addFixed(new JavaFixed("SG50_PUBLIC_HOLIDAY", HolidayType.OFFICIAL_HOLIDAY, Year.of(2015), Year.of(2015), YearCycle.EVERY_YEAR, null, MonthDay.of(8, 7)))
      .addFixed(new JavaFixed("NATIONAL_DAY", HolidayType.OFFICIAL_HOLIDAY, null, null, YearCycle.EVERY_YEAR, List.of(new JavaMovingCondition(DayOfWeek.of(7), With.NEXT, DayOfWeek.of(1))), MonthDay.of(8, 9)))
      .addFixed(new JavaFixed("POLLING_DAY", HolidayType.OFFICIAL_HOLIDAY, Year.of(2015), Year.of(2015), YearCycle.EVERY_YEAR, null, MonthDay.of(9, 11)))
      .addFixed(new JavaFixed("POLLING_DAY", HolidayType.OFFICIAL_HOLIDAY, Year.of(2020), Year.of(2020), YearCycle.EVERY_YEAR, null, MonthDay.of(7, 10)))
      .addFixed(new JavaFixed("POLLING_DAY", HolidayType.OFFICIAL_HOLIDAY, Year.of(2023), Year.of(2023), YearCycle.EVERY_YEAR, null, MonthDay.of(9, 1)))
      .addFixed(new JavaFixed("DEEPAVALI", HolidayType.OFFICIAL_HOLIDAY, Year.of(2013), Year.of(2013), YearCycle.EVERY_YEAR, List.of(new JavaMovingCondition(DayOfWeek.of(7), With.NEXT, DayOfWeek.of(1))), MonthDay.of(11, 2)))
      .addFixed(new JavaFixed("DEEPAVALI", HolidayType.OFFICIAL_HOLIDAY, Year.of(2014), Year.of(2014), YearCycle.EVERY_YEAR, List.of(new JavaMovingCondition(DayOfWeek.of(7), With.NEXT, DayOfWeek.of(1))), MonthDay.of(10, 22)))
      .addFixed(new JavaFixed("DEEPAVALI", HolidayType.OFFICIAL_HOLIDAY, Year.of(2015), Year.of(2015), YearCycle.EVERY_YEAR, List.of(new JavaMovingCondition(DayOfWeek.of(7), With.NEXT, DayOfWeek.of(1))), MonthDay.of(11, 10)))
      .addFixed(new JavaFixed("DEEPAVALI", HolidayType.OFFICIAL_HOLIDAY, Year.of(2016), Year.of(2016), YearCycle.EVERY_YEAR, List.of(new JavaMovingCondition(DayOfWeek.of(7), With.NEXT, DayOfWeek.of(1))), MonthDay.of(10, 29)))
      .addFixed(new JavaFixed("DEEPAVALI", HolidayType.OFFICIAL_HOLIDAY, Year.of(2017), Year.of(2017), YearCycle.EVERY_YEAR, List.of(new JavaMovingCondition(DayOfWeek.of(7), With.NEXT, DayOfWeek.of(1))), MonthDay.of(10, 18)))
      .addFixed(new JavaFixed("DEEPAVALI", HolidayType.OFFICIAL_HOLIDAY, Year.of(2018), Year.of(2018), YearCycle.EVERY_YEAR, List.of(new JavaMovingCondition(DayOfWeek.of(7), With.NEXT, DayOfWeek.of(1))), MonthDay.of(11, 6)))
      .addFixed(new JavaFixed("DEEPAVALI", HolidayType.OFFICIAL_HOLIDAY, Year.of(2019), Year.of(2019), YearCycle.EVERY_YEAR, List.of(new JavaMovingCondition(DayOfWeek.of(7), With.NEXT, DayOfWeek.of(1))), MonthDay.of(10, 27)))
      .addFixed(new JavaFixed("DEEPAVALI", HolidayType.OFFICIAL_HOLIDAY, Year.of(2020), Year.of(2020), YearCycle.EVERY_YEAR, List.of(new JavaMovingCondition(DayOfWeek.of(7), With.NEXT, DayOfWeek.of(1))), MonthDay.of(11, 14)))
      .addFixed(new JavaFixed("DEEPAVALI", HolidayType.OFFICIAL_HOLIDAY, Year.of(2021), Year.of(2021), YearCycle.EVERY_YEAR, List.of(new JavaMovingCondition(DayOfWeek.of(7), With.NEXT, DayOfWeek.of(1))), MonthDay.of(11, 4)))
      .addFixed(new JavaFixed("DEEPAVALI", HolidayType.OFFICIAL_HOLIDAY, Year.of(2022), Year.of(2022), YearCycle.EVERY_YEAR, List.of(new JavaMovingCondition(DayOfWeek.of(7), With.NEXT, DayOfWeek.of(1))), MonthDay.of(10, 24)))
      .addFixed(new JavaFixed("DEEPAVALI", HolidayType.OFFICIAL_HOLIDAY, Year.of(2023), Year.of(2023), YearCycle.EVERY_YEAR, List.of(new JavaMovingCondition(DayOfWeek.of(7), With.NEXT, DayOfWeek.of(1))), MonthDay.of(11, 12)))
      .addFixed(new JavaFixed("DEEPAVALI", HolidayType.OFFICIAL_HOLIDAY, Year.of(2024), Year.of(2024), YearCycle.EVERY_YEAR, List.of(new JavaMovingCondition(DayOfWeek.of(7), With.NEXT, DayOfWeek.of(1))), MonthDay.of(10, 31)))
      .addFixed(new JavaFixed("CHRISTMAS", HolidayType.OFFICIAL_HOLIDAY, null, null, YearCycle.EVERY_YEAR, List.of(new JavaMovingCondition(DayOfWeek.of(7), With.NEXT, DayOfWeek.of(1))), MonthDay.of(12, 25)))
      .addChristianHoliday(new JavaChristianHoliday("christian.GOOD_FRIDAY", HolidayType.OFFICIAL_HOLIDAY, null, null, YearCycle.EVERY_YEAR, null, ChristianHolidayType.GOOD_FRIDAY, Chronology.of("ISO")))
      .addIslamicHoliday(new JavaIslamicHoliday("HARI_RAYA_PUASA", HolidayType.OFFICIAL_HOLIDAY, null, Year.of(2018), YearCycle.EVERY_YEAR, List.of(new JavaMovingCondition(DayOfWeek.of(7), With.NEXT, DayOfWeek.of(1))), IslamicHolidayType.ID_AL_FITR))
      .addIslamicHoliday(new JavaIslamicHoliday("HARI_RAYA_PUASA", HolidayType.OFFICIAL_HOLIDAY, Year.of(2019), Year.of(2019), YearCycle.EVERY_YEAR, List.of(new JavaMovingCondition(DayOfWeek.of(7), With.NEXT, DayOfWeek.of(1))), IslamicHolidayType.ID_AL_FITR_2))
      .addIslamicHoliday(new JavaIslamicHoliday("HARI_RAYA_PUASA", HolidayType.OFFICIAL_HOLIDAY, Year.of(2020), Year.of(2021), YearCycle.EVERY_YEAR, List.of(new JavaMovingCondition(DayOfWeek.of(7), With.NEXT, DayOfWeek.of(1))), IslamicHolidayType.ID_AL_FITR))
      .addIslamicHoliday(new JavaIslamicHoliday("HARI_RAYA_PUASA", HolidayType.OFFICIAL_HOLIDAY, Year.of(2022), Year.of(2023), YearCycle.EVERY_YEAR, List.of(new JavaMovingCondition(DayOfWeek.of(7), With.NEXT, DayOfWeek.of(1))), IslamicHolidayType.ID_AL_FITR_2))
      .addIslamicHoliday(new JavaIslamicHoliday("HARI_RAYA_PUASA", HolidayType.OFFICIAL_HOLIDAY, Year.of(2024), null, YearCycle.EVERY_YEAR, List.of(new JavaMovingCondition(DayOfWeek.of(7), With.NEXT, DayOfWeek.of(1))), IslamicHolidayType.ID_AL_FITR))
      .addIslamicHoliday(new JavaIslamicHoliday("HARI_RAYA_HAJI", HolidayType.OFFICIAL_HOLIDAY, null, Year.of(2012), YearCycle.EVERY_YEAR, List.of(new JavaMovingCondition(DayOfWeek.of(7), With.NEXT, DayOfWeek.of(1))), IslamicHolidayType.ID_UL_ADHA_2))
      .addIslamicHoliday(new JavaIslamicHoliday("HARI_RAYA_HAJI", HolidayType.OFFICIAL_HOLIDAY, Year.of(2013), Year.of(2013), YearCycle.EVERY_YEAR, List.of(new JavaMovingCondition(DayOfWeek.of(7), With.NEXT, DayOfWeek.of(1))), IslamicHolidayType.ID_UL_ADHA))
      .addIslamicHoliday(new JavaIslamicHoliday("HARI_RAYA_HAJI", HolidayType.OFFICIAL_HOLIDAY, Year.of(2014), Year.of(2016), YearCycle.EVERY_YEAR, List.of(new JavaMovingCondition(DayOfWeek.of(7), With.NEXT, DayOfWeek.of(1))), IslamicHolidayType.ID_UL_ADHA_2))
      .addIslamicHoliday(new JavaIslamicHoliday("HARI_RAYA_HAJI", HolidayType.OFFICIAL_HOLIDAY, Year.of(2017), Year.of(2017), YearCycle.EVERY_YEAR, List.of(new JavaMovingCondition(DayOfWeek.of(7), With.NEXT, DayOfWeek.of(1))), IslamicHolidayType.ID_UL_ADHA))
      .addIslamicHoliday(new JavaIslamicHoliday("HARI_RAYA_HAJI", HolidayType.OFFICIAL_HOLIDAY, Year.of(2018), Year.of(2019), YearCycle.EVERY_YEAR, List.of(new JavaMovingCondition(DayOfWeek.of(7), With.NEXT, DayOfWeek.of(1))), IslamicHolidayType.ID_UL_ADHA_2))
      .addIslamicHoliday(new JavaIslamicHoliday("HARI_RAYA_HAJI", HolidayType.OFFICIAL_HOLIDAY, Year.of(2020), Year.of(2021), YearCycle.EVERY_YEAR, List.of(new JavaMovingCondition(DayOfWeek.of(7), With.NEXT, DayOfWeek.of(1))), IslamicHolidayType.ID_UL_ADHA))
      .addIslamicHoliday(new JavaIslamicHoliday("HARI_RAYA_HAJI", HolidayType.OFFICIAL_HOLIDAY, Year.of(2022), null, YearCycle.EVERY_YEAR, List.of(new JavaMovingCondition(DayOfWeek.of(7), With.NEXT, DayOfWeek.of(1))), IslamicHolidayType.ID_UL_ADHA_2))
      , null, "sg", "Singapore");
  }
}
