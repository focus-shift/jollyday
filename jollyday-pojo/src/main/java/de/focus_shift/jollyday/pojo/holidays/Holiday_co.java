package de.focus_shift.jollyday.pojo.holidays;

import java.time.DayOfWeek;
import java.time.MonthDay;
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

public class Holiday_co {

  public static JavaConfiguration configuration;

  static {
    configuration = new JavaConfiguration(new JavaHolidays()
      .addFixed(new JavaFixed("NEW_YEAR", HolidayType.OFFICIAL_HOLIDAY, null, null, YearCycle.EVERY_YEAR, null, MonthDay.of(1, 1)))
      .addFixed(new JavaFixed("EPIPHANY", HolidayType.OFFICIAL_HOLIDAY, null, null, YearCycle.EVERY_YEAR, List.of(new JavaMovingCondition(DayOfWeek.of(2), With.NEXT, DayOfWeek.of(1)), new JavaMovingCondition(DayOfWeek.of(3), With.NEXT, DayOfWeek.of(1)), new JavaMovingCondition(DayOfWeek.of(4), With.NEXT, DayOfWeek.of(1)), new JavaMovingCondition(DayOfWeek.of(5), With.NEXT, DayOfWeek.of(1)), new JavaMovingCondition(DayOfWeek.of(6), With.NEXT, DayOfWeek.of(1)), new JavaMovingCondition(DayOfWeek.of(7), With.NEXT, DayOfWeek.of(1))), MonthDay.of(1, 6)))
      .addFixed(new JavaFixed("ST_JOSEPH", HolidayType.OFFICIAL_HOLIDAY, null, null, YearCycle.EVERY_YEAR, List.of(new JavaMovingCondition(DayOfWeek.of(2), With.NEXT, DayOfWeek.of(1)), new JavaMovingCondition(DayOfWeek.of(3), With.NEXT, DayOfWeek.of(1)), new JavaMovingCondition(DayOfWeek.of(4), With.NEXT, DayOfWeek.of(1)), new JavaMovingCondition(DayOfWeek.of(5), With.NEXT, DayOfWeek.of(1)), new JavaMovingCondition(DayOfWeek.of(6), With.NEXT, DayOfWeek.of(1)), new JavaMovingCondition(DayOfWeek.of(7), With.NEXT, DayOfWeek.of(1))), MonthDay.of(3, 19)))
      .addFixed(new JavaFixed("LABOUR_DAY", HolidayType.OFFICIAL_HOLIDAY, null, null, YearCycle.EVERY_YEAR, null, MonthDay.of(5, 1)))
      .addFixed(new JavaFixed("ST_PETER_PAUL", HolidayType.OFFICIAL_HOLIDAY, null, null, YearCycle.EVERY_YEAR, List.of(new JavaMovingCondition(DayOfWeek.of(2), With.NEXT, DayOfWeek.of(1)), new JavaMovingCondition(DayOfWeek.of(3), With.NEXT, DayOfWeek.of(1)), new JavaMovingCondition(DayOfWeek.of(4), With.NEXT, DayOfWeek.of(1)), new JavaMovingCondition(DayOfWeek.of(5), With.NEXT, DayOfWeek.of(1)), new JavaMovingCondition(DayOfWeek.of(6), With.NEXT, DayOfWeek.of(1)), new JavaMovingCondition(DayOfWeek.of(7), With.NEXT, DayOfWeek.of(1))), MonthDay.of(6, 29)))
      .addFixed(new JavaFixed("INDEPENDENCE_DAY", HolidayType.OFFICIAL_HOLIDAY, null, null, YearCycle.EVERY_YEAR, null, MonthDay.of(7, 20)))
      .addFixed(new JavaFixed("BOYACA", HolidayType.OFFICIAL_HOLIDAY, null, null, YearCycle.EVERY_YEAR, null, MonthDay.of(8, 7)))
      .addFixed(new JavaFixed("ASSUMPTION_MARY", HolidayType.OFFICIAL_HOLIDAY, null, null, YearCycle.EVERY_YEAR, List.of(new JavaMovingCondition(DayOfWeek.of(2), With.NEXT, DayOfWeek.of(1)), new JavaMovingCondition(DayOfWeek.of(3), With.NEXT, DayOfWeek.of(1)), new JavaMovingCondition(DayOfWeek.of(4), With.NEXT, DayOfWeek.of(1)), new JavaMovingCondition(DayOfWeek.of(5), With.NEXT, DayOfWeek.of(1)), new JavaMovingCondition(DayOfWeek.of(6), With.NEXT, DayOfWeek.of(1)), new JavaMovingCondition(DayOfWeek.of(7), With.NEXT, DayOfWeek.of(1))), MonthDay.of(8, 15)))
      .addFixed(new JavaFixed("COLUMBUS_DAY", HolidayType.OFFICIAL_HOLIDAY, null, null, YearCycle.EVERY_YEAR, List.of(new JavaMovingCondition(DayOfWeek.of(2), With.NEXT, DayOfWeek.of(1)), new JavaMovingCondition(DayOfWeek.of(3), With.NEXT, DayOfWeek.of(1)), new JavaMovingCondition(DayOfWeek.of(4), With.NEXT, DayOfWeek.of(1)), new JavaMovingCondition(DayOfWeek.of(5), With.NEXT, DayOfWeek.of(1)), new JavaMovingCondition(DayOfWeek.of(6), With.NEXT, DayOfWeek.of(1)), new JavaMovingCondition(DayOfWeek.of(7), With.NEXT, DayOfWeek.of(1))), MonthDay.of(10, 12)))
      .addFixed(new JavaFixed("ALL_SAINTS", HolidayType.OFFICIAL_HOLIDAY, null, null, YearCycle.EVERY_YEAR, List.of(new JavaMovingCondition(DayOfWeek.of(2), With.NEXT, DayOfWeek.of(1)), new JavaMovingCondition(DayOfWeek.of(3), With.NEXT, DayOfWeek.of(1)), new JavaMovingCondition(DayOfWeek.of(4), With.NEXT, DayOfWeek.of(1)), new JavaMovingCondition(DayOfWeek.of(5), With.NEXT, DayOfWeek.of(1)), new JavaMovingCondition(DayOfWeek.of(6), With.NEXT, DayOfWeek.of(1)), new JavaMovingCondition(DayOfWeek.of(7), With.NEXT, DayOfWeek.of(1))), MonthDay.of(11, 1)))
      .addFixed(new JavaFixed("CARTAGENA", HolidayType.OFFICIAL_HOLIDAY, null, null, YearCycle.EVERY_YEAR, List.of(new JavaMovingCondition(DayOfWeek.of(2), With.NEXT, DayOfWeek.of(1)), new JavaMovingCondition(DayOfWeek.of(3), With.NEXT, DayOfWeek.of(1)), new JavaMovingCondition(DayOfWeek.of(4), With.NEXT, DayOfWeek.of(1)), new JavaMovingCondition(DayOfWeek.of(5), With.NEXT, DayOfWeek.of(1)), new JavaMovingCondition(DayOfWeek.of(6), With.NEXT, DayOfWeek.of(1)), new JavaMovingCondition(DayOfWeek.of(7), With.NEXT, DayOfWeek.of(1))), MonthDay.of(11, 11)))
      .addFixed(new JavaFixed("IMMACULATE_CONCEPTION", HolidayType.OFFICIAL_HOLIDAY, null, null, YearCycle.EVERY_YEAR, null, MonthDay.of(12, 8)))
      .addFixed(new JavaFixed("CHRISTMAS", HolidayType.OFFICIAL_HOLIDAY, null, null, YearCycle.EVERY_YEAR, null, MonthDay.of(12, 25)))
      .addChristianHoliday(new JavaChristianHoliday("christian.EASTER", HolidayType.OFFICIAL_HOLIDAY, null, null, YearCycle.EVERY_YEAR, null, ChristianHolidayType.EASTER, Chronology.of("ISO")))
      .addChristianHoliday(new JavaChristianHoliday("christian.MAUNDY_THURSDAY", HolidayType.OFFICIAL_HOLIDAY, null, null, YearCycle.EVERY_YEAR, null, ChristianHolidayType.MAUNDY_THURSDAY, Chronology.of("ISO")))
      .addChristianHoliday(new JavaChristianHoliday("christian.GOOD_FRIDAY", HolidayType.OFFICIAL_HOLIDAY, null, null, YearCycle.EVERY_YEAR, null, ChristianHolidayType.GOOD_FRIDAY, Chronology.of("ISO")))
      .addChristianHoliday(new JavaChristianHoliday("christian.ASCENSION_DAY", HolidayType.OFFICIAL_HOLIDAY, null, null, YearCycle.EVERY_YEAR, List.of(new JavaMovingCondition(DayOfWeek.of(2), With.NEXT, DayOfWeek.of(1)), new JavaMovingCondition(DayOfWeek.of(3), With.NEXT, DayOfWeek.of(1)), new JavaMovingCondition(DayOfWeek.of(4), With.NEXT, DayOfWeek.of(1)), new JavaMovingCondition(DayOfWeek.of(5), With.NEXT, DayOfWeek.of(1)), new JavaMovingCondition(DayOfWeek.of(6), With.NEXT, DayOfWeek.of(1)), new JavaMovingCondition(DayOfWeek.of(7), With.NEXT, DayOfWeek.of(1))), ChristianHolidayType.ASCENSION_DAY, Chronology.of("ISO")))
      .addChristianHoliday(new JavaChristianHoliday("christian.CORPUS_CHRISTI", HolidayType.OFFICIAL_HOLIDAY, null, null, YearCycle.EVERY_YEAR, List.of(new JavaMovingCondition(DayOfWeek.of(2), With.NEXT, DayOfWeek.of(1)), new JavaMovingCondition(DayOfWeek.of(3), With.NEXT, DayOfWeek.of(1)), new JavaMovingCondition(DayOfWeek.of(4), With.NEXT, DayOfWeek.of(1)), new JavaMovingCondition(DayOfWeek.of(5), With.NEXT, DayOfWeek.of(1)), new JavaMovingCondition(DayOfWeek.of(6), With.NEXT, DayOfWeek.of(1)), new JavaMovingCondition(DayOfWeek.of(7), With.NEXT, DayOfWeek.of(1))), ChristianHolidayType.CORPUS_CHRISTI, Chronology.of("ISO")))
      .addChristianHoliday(new JavaChristianHoliday("christian.SACRED_HEART", HolidayType.OFFICIAL_HOLIDAY, null, null, YearCycle.EVERY_YEAR, List.of(new JavaMovingCondition(DayOfWeek.of(2), With.NEXT, DayOfWeek.of(1)), new JavaMovingCondition(DayOfWeek.of(3), With.NEXT, DayOfWeek.of(1)), new JavaMovingCondition(DayOfWeek.of(4), With.NEXT, DayOfWeek.of(1)), new JavaMovingCondition(DayOfWeek.of(5), With.NEXT, DayOfWeek.of(1)), new JavaMovingCondition(DayOfWeek.of(6), With.NEXT, DayOfWeek.of(1)), new JavaMovingCondition(DayOfWeek.of(7), With.NEXT, DayOfWeek.of(1))), ChristianHolidayType.SACRED_HEART, Chronology.of("ISO")))
      , null, "co", "Colombia");
  }
}
