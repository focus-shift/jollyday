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

public class Holiday_al {

  public static JavaConfiguration configuration;

  static {
    configuration = new JavaConfiguration(new JavaHolidays()
      .addFixed(new JavaFixed("NEW_YEAR", HolidayType.OFFICIAL_HOLIDAY, null, null, YearCycle.EVERY_YEAR, List.of(new JavaMovingCondition(DayOfWeek.of(6), With.NEXT, DayOfWeek.of(1)), new JavaMovingCondition(DayOfWeek.of(7), With.NEXT, DayOfWeek.of(1))), MonthDay.of(1, 1)))
      .addFixed(new JavaFixed("NEW_YEAR", HolidayType.OFFICIAL_HOLIDAY, null, null, YearCycle.EVERY_YEAR, List.of(new JavaMovingCondition(DayOfWeek.of(6), With.NEXT, DayOfWeek.of(1)), new JavaMovingCondition(DayOfWeek.of(7), With.NEXT, DayOfWeek.of(1))), MonthDay.of(1, 2)))
      .addFixed(new JavaFixed("SPRING_DAY", HolidayType.OFFICIAL_HOLIDAY, Year.of(2004), null, YearCycle.EVERY_YEAR, List.of(new JavaMovingCondition(DayOfWeek.of(6), With.NEXT, DayOfWeek.of(1)), new JavaMovingCondition(DayOfWeek.of(7), With.NEXT, DayOfWeek.of(1))), MonthDay.of(3, 14)))
      .addFixed(new JavaFixed("LABOUR_DAY", HolidayType.OFFICIAL_HOLIDAY, null, null, YearCycle.EVERY_YEAR, List.of(new JavaMovingCondition(DayOfWeek.of(6), With.NEXT, DayOfWeek.of(1)), new JavaMovingCondition(DayOfWeek.of(7), With.NEXT, DayOfWeek.of(1))), MonthDay.of(5, 1)))
      .addFixed(new JavaFixed("MOTHER_TERESA", HolidayType.OFFICIAL_HOLIDAY, Year.of(2003), null, YearCycle.EVERY_YEAR, List.of(new JavaMovingCondition(DayOfWeek.of(6), With.NEXT, DayOfWeek.of(1)), new JavaMovingCondition(DayOfWeek.of(7), With.NEXT, DayOfWeek.of(1))), MonthDay.of(10, 19)))
      .addFixed(new JavaFixed("INDEPENDENCE_DAY", HolidayType.OFFICIAL_HOLIDAY, null, null, YearCycle.EVERY_YEAR, null, MonthDay.of(11, 28)))
      .addFixed(new JavaFixed("LIBERATION", HolidayType.OFFICIAL_HOLIDAY, null, null, YearCycle.EVERY_YEAR, null, MonthDay.of(11, 29)))
      .addFixed(new JavaFixed("CHRISTMAS", HolidayType.OFFICIAL_HOLIDAY, null, null, YearCycle.EVERY_YEAR, null, MonthDay.of(12, 25)))
      .addChristianHoliday(new JavaChristianHoliday("christian.EASTER", HolidayType.OFFICIAL_HOLIDAY, null, null, YearCycle.EVERY_YEAR, null, ChristianHolidayType.EASTER, Chronology.of("ISO")))
      .addChristianHoliday(new JavaChristianHoliday("christian.EASTER_MONDAY", HolidayType.OFFICIAL_HOLIDAY, null, null, YearCycle.EVERY_YEAR, null, ChristianHolidayType.EASTER_MONDAY, Chronology.of("ISO")))
      .addChristianHoliday(new JavaChristianHoliday("christian.GOOD_FRIDAY", HolidayType.OFFICIAL_HOLIDAY, null, null, YearCycle.EVERY_YEAR, null, ChristianHolidayType.GOOD_FRIDAY, Chronology.of("ISO")))
      .addIslamicHoliday(new JavaIslamicHoliday("islamic.ID_UL_ADHA", HolidayType.OFFICIAL_HOLIDAY, null, null, YearCycle.EVERY_YEAR, null, IslamicHolidayType.ID_UL_ADHA))
      .addIslamicHoliday(new JavaIslamicHoliday("islamic.ID_AL_FITR", HolidayType.OFFICIAL_HOLIDAY, null, null, YearCycle.EVERY_YEAR, null, IslamicHolidayType.ID_AL_FITR))
      , null, "al", "Albania");
  }
}
