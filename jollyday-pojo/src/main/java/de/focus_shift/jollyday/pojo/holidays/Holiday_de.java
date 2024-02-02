package de.focus_shift.jollyday.pojo.holidays;

import java.time.DayOfWeek;
import java.time.MonthDay;
import java.time.Year;
import java.time.chrono.Chronology;
import java.util.List;

import de.focus_shift.jollyday.core.HolidayType;
import de.focus_shift.jollyday.core.spi.ChristianHolidayType;
import de.focus_shift.jollyday.core.spi.Relation;
import de.focus_shift.jollyday.core.spi.YearCycle;
import de.focus_shift.jollyday.pojo.JavaChristianHoliday;
import de.focus_shift.jollyday.pojo.JavaConfiguration;
import de.focus_shift.jollyday.pojo.JavaFixed;
import de.focus_shift.jollyday.pojo.JavaHolidays;
import de.focus_shift.jollyday.pojo.JavaRelativeToFixed;

public class Holiday_de {

  public static JavaConfiguration configuration;

  static {
    configuration = new JavaConfiguration(new JavaHolidays()
      .addFixed(new JavaFixed("NEW_YEAR", HolidayType.OFFICIAL_HOLIDAY, null, null, YearCycle.EVERY_YEAR, null, MonthDay.of(1, 1)))
      .addFixed(new JavaFixed("LABOUR_DAY", HolidayType.OFFICIAL_HOLIDAY, null, null, YearCycle.EVERY_YEAR, null, MonthDay.of(5, 1)))
      .addFixed(new JavaFixed("UNIFICATION", HolidayType.OFFICIAL_HOLIDAY, Year.of(1954), Year.of(1990), YearCycle.EVERY_YEAR, null, MonthDay.of(6, 17)))
      .addFixed(new JavaFixed("UNIFICATION_GERMANY", HolidayType.OFFICIAL_HOLIDAY, Year.of(1990), null, YearCycle.EVERY_YEAR, null, MonthDay.of(10, 3)))
      .addFixed(new JavaFixed("REFORMATION_DAY", HolidayType.OFFICIAL_HOLIDAY, Year.of(2017), Year.of(2017), YearCycle.EVERY_YEAR, null, MonthDay.of(10, 31)))
      .addFixed(new JavaFixed("FIRST_CHRISTMAS_DAY", HolidayType.OFFICIAL_HOLIDAY, null, null, YearCycle.EVERY_YEAR, null, MonthDay.of(12, 25)))
      .addFixed(new JavaFixed("SECOND_CHRISTMAS_DAY", HolidayType.OFFICIAL_HOLIDAY, null, null, YearCycle.EVERY_YEAR, null, MonthDay.of(12, 26)))
      .addChristianHoliday(new JavaChristianHoliday("christian.GOOD_FRIDAY", HolidayType.OFFICIAL_HOLIDAY, null, null, YearCycle.EVERY_YEAR, null, ChristianHolidayType.GOOD_FRIDAY, Chronology.of("ISO")))
      .addChristianHoliday(new JavaChristianHoliday("christian.EASTER_MONDAY", HolidayType.OFFICIAL_HOLIDAY, null, null, YearCycle.EVERY_YEAR, null, ChristianHolidayType.EASTER_MONDAY, Chronology.of("ISO")))
      .addChristianHoliday(new JavaChristianHoliday("christian.ASCENSION_DAY", HolidayType.OFFICIAL_HOLIDAY, null, null, YearCycle.EVERY_YEAR, null, ChristianHolidayType.ASCENSION_DAY, Chronology.of("ISO")))
      .addChristianHoliday(new JavaChristianHoliday("christian.WHIT_MONDAY", HolidayType.OFFICIAL_HOLIDAY, null, null, YearCycle.EVERY_YEAR, null, ChristianHolidayType.WHIT_MONDAY, Chronology.of("ISO")))
      .addRelativeToFixed(new JavaRelativeToFixed("REPENTANCE_PRAYER", HolidayType.OFFICIAL_HOLIDAY, Year.of(1934), Year.of(1938), YearCycle.EVERY_YEAR, new JavaFixed("null", HolidayType.OFFICIAL_HOLIDAY, null, null, YearCycle.EVERY_YEAR, null, MonthDay.of(11, 23)), DayOfWeek.of(3), Relation.BEFORE, null))
      .addRelativeToFixed(new JavaRelativeToFixed("REPENTANCE_PRAYER", HolidayType.OFFICIAL_HOLIDAY, Year.of(1990), Year.of(1994), YearCycle.EVERY_YEAR, new JavaFixed("null", HolidayType.OFFICIAL_HOLIDAY, null, null, YearCycle.EVERY_YEAR, null, MonthDay.of(11, 23)), DayOfWeek.of(3), Relation.BEFORE, null))
      , List.of(new JavaConfiguration(new JavaHolidays()
      .addFixed(new JavaFixed("INTERNATIONAL_WOMAN", HolidayType.OFFICIAL_HOLIDAY, Year.of(2019), null, YearCycle.EVERY_YEAR, null, MonthDay.of(3, 8)))
      .addFixed(new JavaFixed("LIBERATION", HolidayType.OFFICIAL_HOLIDAY, Year.of(2020), Year.of(2020), YearCycle.EVERY_YEAR, null, MonthDay.of(5, 8)))
      .addRelativeToFixed(new JavaRelativeToFixed("REPENTANCE_PRAYER", HolidayType.OFFICIAL_HOLIDAY, Year.of(1945), Year.of(1989), YearCycle.EVERY_YEAR, new JavaFixed("null", HolidayType.OFFICIAL_HOLIDAY, null, null, YearCycle.EVERY_YEAR, null, MonthDay.of(11, 23)), DayOfWeek.of(3), Relation.BEFORE, null))
      , null, "be", "Berlin"), new JavaConfiguration(new JavaHolidays()
      .addFixed(new JavaFixed("REFORMATION_DAY", HolidayType.OFFICIAL_HOLIDAY, null, null, YearCycle.EVERY_YEAR, null, MonthDay.of(10, 31)))
      .addChristianHoliday(new JavaChristianHoliday("christian.EASTER", HolidayType.OFFICIAL_HOLIDAY, null, null, YearCycle.EVERY_YEAR, null, ChristianHolidayType.EASTER, Chronology.of("ISO")))
      .addChristianHoliday(new JavaChristianHoliday("christian.PENTECOST", HolidayType.OFFICIAL_HOLIDAY, null, null, YearCycle.EVERY_YEAR, null, ChristianHolidayType.PENTECOST, Chronology.of("ISO")))
      , null, "bb", "Brandenburg"), new JavaConfiguration(new JavaHolidays()
      .addFixed(new JavaFixed("EPIPHANY", HolidayType.OFFICIAL_HOLIDAY, null, null, YearCycle.EVERY_YEAR, null, MonthDay.of(1, 6)))
      .addFixed(new JavaFixed("ALL_SAINTS", HolidayType.OFFICIAL_HOLIDAY, null, null, YearCycle.EVERY_YEAR, null, MonthDay.of(11, 1)))
      .addChristianHoliday(new JavaChristianHoliday("christian.CORPUS_CHRISTI", HolidayType.OFFICIAL_HOLIDAY, null, null, YearCycle.EVERY_YEAR, null, ChristianHolidayType.CORPUS_CHRISTI, Chronology.of("ISO")))
      .addRelativeToFixed(new JavaRelativeToFixed("REPENTANCE_PRAYER", HolidayType.OFFICIAL_HOLIDAY, Year.of(1945), Year.of(1989), YearCycle.EVERY_YEAR, new JavaFixed("null", HolidayType.OFFICIAL_HOLIDAY, null, null, YearCycle.EVERY_YEAR, null, MonthDay.of(11, 23)), DayOfWeek.of(3), Relation.BEFORE, null))
      , null, "bw", "Baden-Württemberg"), new JavaConfiguration(new JavaHolidays()
      .addFixed(new JavaFixed("EPIPHANY", HolidayType.OFFICIAL_HOLIDAY, null, null, YearCycle.EVERY_YEAR, null, MonthDay.of(1, 6)))
      .addFixed(new JavaFixed("ALL_SAINTS", HolidayType.OFFICIAL_HOLIDAY, null, null, YearCycle.EVERY_YEAR, null, MonthDay.of(11, 1)))
      .addChristianHoliday(new JavaChristianHoliday("christian.CORPUS_CHRISTI", HolidayType.OFFICIAL_HOLIDAY, null, null, YearCycle.EVERY_YEAR, null, ChristianHolidayType.CORPUS_CHRISTI, Chronology.of("ISO")))
      .addRelativeToFixed(new JavaRelativeToFixed("REPENTANCE_PRAYER", HolidayType.OFFICIAL_HOLIDAY, Year.of(1981), Year.of(1989), YearCycle.EVERY_YEAR, new JavaFixed("null", HolidayType.OFFICIAL_HOLIDAY, null, null, YearCycle.EVERY_YEAR, null, MonthDay.of(11, 23)), DayOfWeek.of(3), Relation.BEFORE, null))
      , List.of(new JavaConfiguration(new JavaHolidays()
      .addFixed(new JavaFixed("ASSUMPTION_DAY", HolidayType.OFFICIAL_HOLIDAY, null, null, YearCycle.EVERY_YEAR, null, MonthDay.of(8, 15)))
      , null, "mu", "Munich"), new JavaConfiguration(new JavaHolidays()
      .addFixed(new JavaFixed("PEACE", HolidayType.OFFICIAL_HOLIDAY, null, null, YearCycle.EVERY_YEAR, null, MonthDay.of(8, 8)))
      .addFixed(new JavaFixed("ASSUMPTION_DAY", HolidayType.OFFICIAL_HOLIDAY, null, null, YearCycle.EVERY_YEAR, null, MonthDay.of(8, 15)))
      , null, "ag", "Augsburg"), new JavaConfiguration(new JavaHolidays()
      .addFixed(new JavaFixed("ASSUMPTION_DAY", HolidayType.OFFICIAL_HOLIDAY, null, null, YearCycle.EVERY_YEAR, null, MonthDay.of(8, 15)))
      , null, "wu", "Würzburg"), new JavaConfiguration(new JavaHolidays()
      .addFixed(new JavaFixed("ASSUMPTION_DAY", HolidayType.OFFICIAL_HOLIDAY, null, null, YearCycle.EVERY_YEAR, null, MonthDay.of(8, 15)))
      , null, "re", "Regensburg"), new JavaConfiguration(new JavaHolidays()
      .addFixed(new JavaFixed("ASSUMPTION_DAY", HolidayType.OFFICIAL_HOLIDAY, null, null, YearCycle.EVERY_YEAR, null, MonthDay.of(8, 15)))
      , null, "in", "Ingolstadt")), "by", "Bavaria"), new JavaConfiguration(new JavaHolidays()
      .addChristianHoliday(new JavaChristianHoliday("christian.EASTER", HolidayType.OFFICIAL_HOLIDAY, null, null, YearCycle.EVERY_YEAR, null, ChristianHolidayType.EASTER, Chronology.of("ISO")))
      .addChristianHoliday(new JavaChristianHoliday("christian.CORPUS_CHRISTI", HolidayType.OFFICIAL_HOLIDAY, null, null, YearCycle.EVERY_YEAR, null, ChristianHolidayType.CORPUS_CHRISTI, Chronology.of("ISO")))
      .addChristianHoliday(new JavaChristianHoliday("christian.PENTECOST", HolidayType.OFFICIAL_HOLIDAY, null, null, YearCycle.EVERY_YEAR, null, ChristianHolidayType.PENTECOST, Chronology.of("ISO")))
      .addRelativeToFixed(new JavaRelativeToFixed("REPENTANCE_PRAYER", HolidayType.OFFICIAL_HOLIDAY, Year.of(1945), Year.of(1989), YearCycle.EVERY_YEAR, new JavaFixed("null", HolidayType.OFFICIAL_HOLIDAY, null, null, YearCycle.EVERY_YEAR, null, MonthDay.of(11, 23)), DayOfWeek.of(3), Relation.BEFORE, null))
      , null, "he", "Hessen"), new JavaConfiguration(new JavaHolidays()
      .addFixed(new JavaFixed("INTERNATIONAL_WOMAN", HolidayType.OFFICIAL_HOLIDAY, Year.of(2023), null, YearCycle.EVERY_YEAR, null, MonthDay.of(3, 8)))
      .addFixed(new JavaFixed("REFORMATION_DAY", HolidayType.OFFICIAL_HOLIDAY, null, null, YearCycle.EVERY_YEAR, null, MonthDay.of(10, 31)))
      , null, "mv", "Mecklenburg-Vorpommern"), new JavaConfiguration(new JavaHolidays()
      .addFixed(new JavaFixed("ALL_SAINTS", HolidayType.OFFICIAL_HOLIDAY, null, null, YearCycle.EVERY_YEAR, null, MonthDay.of(11, 1)))
      .addChristianHoliday(new JavaChristianHoliday("christian.CORPUS_CHRISTI", HolidayType.OFFICIAL_HOLIDAY, null, null, YearCycle.EVERY_YEAR, null, ChristianHolidayType.CORPUS_CHRISTI, Chronology.of("ISO")))
      .addRelativeToFixed(new JavaRelativeToFixed("REPENTANCE_PRAYER", HolidayType.OFFICIAL_HOLIDAY, Year.of(1945), Year.of(1989), YearCycle.EVERY_YEAR, new JavaFixed("null", HolidayType.OFFICIAL_HOLIDAY, null, null, YearCycle.EVERY_YEAR, null, MonthDay.of(11, 23)), DayOfWeek.of(3), Relation.BEFORE, null))
      , null, "nw", "North Rhine-Westphalia"), new JavaConfiguration(new JavaHolidays()
      .addFixed(new JavaFixed("ALL_SAINTS", HolidayType.OFFICIAL_HOLIDAY, null, null, YearCycle.EVERY_YEAR, null, MonthDay.of(11, 1)))
      .addChristianHoliday(new JavaChristianHoliday("christian.CORPUS_CHRISTI", HolidayType.OFFICIAL_HOLIDAY, null, null, YearCycle.EVERY_YEAR, null, ChristianHolidayType.CORPUS_CHRISTI, Chronology.of("ISO")))
      .addRelativeToFixed(new JavaRelativeToFixed("REPENTANCE_PRAYER", HolidayType.OFFICIAL_HOLIDAY, Year.of(1945), Year.of(1989), YearCycle.EVERY_YEAR, new JavaFixed("null", HolidayType.OFFICIAL_HOLIDAY, null, null, YearCycle.EVERY_YEAR, null, MonthDay.of(11, 23)), DayOfWeek.of(3), Relation.BEFORE, null))
      , null, "rp", "Rhineland-Palatinate"), new JavaConfiguration(new JavaHolidays()
      .addFixed(new JavaFixed("ALL_SAINTS", HolidayType.OFFICIAL_HOLIDAY, null, null, YearCycle.EVERY_YEAR, null, MonthDay.of(11, 1)))
      .addFixed(new JavaFixed("ASSUMPTION_DAY", HolidayType.OFFICIAL_HOLIDAY, null, null, YearCycle.EVERY_YEAR, null, MonthDay.of(8, 15)))
      .addChristianHoliday(new JavaChristianHoliday("christian.CORPUS_CHRISTI", HolidayType.OFFICIAL_HOLIDAY, null, null, YearCycle.EVERY_YEAR, null, ChristianHolidayType.CORPUS_CHRISTI, Chronology.of("ISO")))
      .addRelativeToFixed(new JavaRelativeToFixed("REPENTANCE_PRAYER", HolidayType.OFFICIAL_HOLIDAY, Year.of(1945), Year.of(1989), YearCycle.EVERY_YEAR, new JavaFixed("null", HolidayType.OFFICIAL_HOLIDAY, null, null, YearCycle.EVERY_YEAR, null, MonthDay.of(11, 23)), DayOfWeek.of(3), Relation.BEFORE, null))
      , null, "sl", "Saarland"), new JavaConfiguration(new JavaHolidays()
      .addFixed(new JavaFixed("REFORMATION_DAY", HolidayType.OFFICIAL_HOLIDAY, null, null, YearCycle.EVERY_YEAR, null, MonthDay.of(10, 31)))
      .addRelativeToFixed(new JavaRelativeToFixed("REPENTANCE_PRAYER", HolidayType.OFFICIAL_HOLIDAY, Year.of(1995), null, YearCycle.EVERY_YEAR, new JavaFixed("null", HolidayType.OFFICIAL_HOLIDAY, null, null, YearCycle.EVERY_YEAR, null, MonthDay.of(11, 23)), DayOfWeek.of(3), Relation.BEFORE, null))
      , null, "sn", "Saxony"), new JavaConfiguration(new JavaHolidays()
      .addFixed(new JavaFixed("REFORMATION_DAY", HolidayType.OFFICIAL_HOLIDAY, null, null, YearCycle.EVERY_YEAR, null, MonthDay.of(10, 31)))
      .addFixed(new JavaFixed("EPIPHANY", HolidayType.OFFICIAL_HOLIDAY, null, null, YearCycle.EVERY_YEAR, null, MonthDay.of(1, 6)))
      , null, "st", "Saxony-Anhalt"), new JavaConfiguration(new JavaHolidays()
      .addFixed(new JavaFixed("CHILDRENS_DAY", HolidayType.OFFICIAL_HOLIDAY, Year.of(2019), null, YearCycle.EVERY_YEAR, null, MonthDay.of(9, 20)))
      .addFixed(new JavaFixed("REFORMATION_DAY", HolidayType.OFFICIAL_HOLIDAY, null, null, YearCycle.EVERY_YEAR, null, MonthDay.of(10, 31)))
      , null, "th", "Thuringia"), new JavaConfiguration(new JavaHolidays()
      .addFixed(new JavaFixed("REFORMATION_DAY", HolidayType.OFFICIAL_HOLIDAY, Year.of(2018), null, YearCycle.EVERY_YEAR, null, MonthDay.of(10, 31)))
      .addRelativeToFixed(new JavaRelativeToFixed("REPENTANCE_PRAYER", HolidayType.OFFICIAL_HOLIDAY, Year.of(1945), Year.of(1989), YearCycle.EVERY_YEAR, new JavaFixed("null", HolidayType.OFFICIAL_HOLIDAY, null, null, YearCycle.EVERY_YEAR, null, MonthDay.of(11, 23)), DayOfWeek.of(3), Relation.BEFORE, null))
      , null, "sh", "Schleswig-Holstein"), new JavaConfiguration(new JavaHolidays()
      .addFixed(new JavaFixed("REFORMATION_DAY", HolidayType.OFFICIAL_HOLIDAY, Year.of(2018), null, YearCycle.EVERY_YEAR, null, MonthDay.of(10, 31)))
      .addRelativeToFixed(new JavaRelativeToFixed("REPENTANCE_PRAYER", HolidayType.OFFICIAL_HOLIDAY, Year.of(1945), Year.of(1989), YearCycle.EVERY_YEAR, new JavaFixed("null", HolidayType.OFFICIAL_HOLIDAY, null, null, YearCycle.EVERY_YEAR, null, MonthDay.of(11, 23)), DayOfWeek.of(3), Relation.BEFORE, null))
      , null, "hh", "Hamburg"), new JavaConfiguration(new JavaHolidays()
      .addFixed(new JavaFixed("REFORMATION_DAY", HolidayType.OFFICIAL_HOLIDAY, Year.of(2018), null, YearCycle.EVERY_YEAR, null, MonthDay.of(10, 31)))
      .addRelativeToFixed(new JavaRelativeToFixed("REPENTANCE_PRAYER", HolidayType.OFFICIAL_HOLIDAY, Year.of(1945), Year.of(1989), YearCycle.EVERY_YEAR, new JavaFixed("null", HolidayType.OFFICIAL_HOLIDAY, null, null, YearCycle.EVERY_YEAR, null, MonthDay.of(11, 23)), DayOfWeek.of(3), Relation.BEFORE, null))
      , null, "hb", "Bremen"), new JavaConfiguration(new JavaHolidays()
      .addFixed(new JavaFixed("REFORMATION_DAY", HolidayType.OFFICIAL_HOLIDAY, Year.of(2018), null, YearCycle.EVERY_YEAR, null, MonthDay.of(10, 31)))
      .addRelativeToFixed(new JavaRelativeToFixed("REPENTANCE_PRAYER", HolidayType.OFFICIAL_HOLIDAY, Year.of(1945), Year.of(1989), YearCycle.EVERY_YEAR, new JavaFixed("null", HolidayType.OFFICIAL_HOLIDAY, null, null, YearCycle.EVERY_YEAR, null, MonthDay.of(11, 23)), DayOfWeek.of(3), Relation.BEFORE, null))
      , null, "ni", "Lower-Saxony")), "de", "Germany");
  }
}
