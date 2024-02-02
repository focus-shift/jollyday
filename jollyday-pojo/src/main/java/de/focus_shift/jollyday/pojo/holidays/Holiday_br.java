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

public class Holiday_br {

  public static JavaConfiguration configuration;

  static {
    configuration = new JavaConfiguration(new JavaHolidays()
      .addFixed(new JavaFixed("NEW_YEAR", HolidayType.OFFICIAL_HOLIDAY, null, null, YearCycle.EVERY_YEAR, null, MonthDay.of(1, 1)))
      .addFixed(new JavaFixed("TIRADENTES", HolidayType.OFFICIAL_HOLIDAY, null, null, YearCycle.EVERY_YEAR, null, MonthDay.of(4, 21)))
      .addFixed(new JavaFixed("LABOUR_DAY", HolidayType.OFFICIAL_HOLIDAY, null, null, YearCycle.EVERY_YEAR, null, MonthDay.of(5, 1)))
      .addFixed(new JavaFixed("INDEPENDENCE_DAY", HolidayType.OFFICIAL_HOLIDAY, null, null, YearCycle.EVERY_YEAR, null, MonthDay.of(9, 7)))
      .addFixed(new JavaFixed("APARECIDA", HolidayType.OFFICIAL_HOLIDAY, Year.of(1980), null, YearCycle.EVERY_YEAR, null, MonthDay.of(10, 12)))
      .addFixed(new JavaFixed("ALL_SOULS", HolidayType.OFFICIAL_HOLIDAY, null, null, YearCycle.EVERY_YEAR, null, MonthDay.of(11, 2)))
      .addFixed(new JavaFixed("NATIONAL_DAY", HolidayType.OFFICIAL_HOLIDAY, null, null, YearCycle.EVERY_YEAR, null, MonthDay.of(11, 15)))
      .addFixed(new JavaFixed("CHRISTMAS", HolidayType.OFFICIAL_HOLIDAY, null, null, YearCycle.EVERY_YEAR, null, MonthDay.of(12, 25)))
      .addChristianHoliday(new JavaChristianHoliday("christian.EASTER", HolidayType.OFFICIAL_HOLIDAY, null, null, YearCycle.EVERY_YEAR, null, ChristianHolidayType.EASTER, Chronology.of("ISO")))
      .addChristianHoliday(new JavaChristianHoliday("christian.CARNIVAL", HolidayType.OFFICIAL_HOLIDAY, null, null, YearCycle.EVERY_YEAR, null, ChristianHolidayType.CARNIVAL, Chronology.of("ISO")))
      .addChristianHoliday(new JavaChristianHoliday("christian.ASH_WEDNESDAY", HolidayType.OFFICIAL_HOLIDAY, null, null, YearCycle.EVERY_YEAR, null, ChristianHolidayType.ASH_WEDNESDAY, Chronology.of("ISO")))
      .addChristianHoliday(new JavaChristianHoliday("christian.GOOD_FRIDAY", HolidayType.OFFICIAL_HOLIDAY, null, null, YearCycle.EVERY_YEAR, null, ChristianHolidayType.GOOD_FRIDAY, Chronology.of("ISO")))
      .addChristianHoliday(new JavaChristianHoliday("christian.CORPUS_CHRISTI", HolidayType.OFFICIAL_HOLIDAY, null, null, YearCycle.EVERY_YEAR, null, ChristianHolidayType.CORPUS_CHRISTI, Chronology.of("ISO")))
      , List.of(new JavaConfiguration(new JavaHolidays()
      .addFixed(new JavaFixed("ST_GEORGE", HolidayType.OFFICIAL_HOLIDAY, Year.of(2007), null, YearCycle.EVERY_YEAR, null, MonthDay.of(4, 23)))
      .addFixed(new JavaFixed("BLACK_AWARENESS", HolidayType.OFFICIAL_HOLIDAY, null, null, YearCycle.EVERY_YEAR, null, MonthDay.of(11, 20)))
      , List.of(new JavaConfiguration(new JavaHolidays()
      .addFixed(new JavaFixed("ST_SEBASTIAN", HolidayType.OFFICIAL_HOLIDAY, null, null, YearCycle.EVERY_YEAR, null, MonthDay.of(1, 20)))
      .addFixed(new JavaFixed("ST_GEORGE", HolidayType.OFFICIAL_HOLIDAY, null, Year.of(2006), YearCycle.EVERY_YEAR, null, MonthDay.of(4, 23)))
      , null, "crj", "City of Rio de Janeiro")), "rj", "Rio de Janeiro"), new JavaConfiguration(new JavaHolidays()
      .addFixed(new JavaFixed("CONST_REVOLUTION", HolidayType.OFFICIAL_HOLIDAY, null, null, YearCycle.EVERY_YEAR, null, MonthDay.of(7, 9)))
      .addFixed(new JavaFixed("BLACK_AWARENESS", HolidayType.OFFICIAL_HOLIDAY, null, null, YearCycle.EVERY_YEAR, null, MonthDay.of(11, 20)))
      , List.of(new JavaConfiguration(new JavaHolidays()
      .addFixed(new JavaFixed("FOUNDATION", HolidayType.OFFICIAL_HOLIDAY, null, null, YearCycle.EVERY_YEAR, null, MonthDay.of(1, 25)))
      , null, "csp", "City of Sao Paulo")), "sp", "Sao Paulo"), new JavaConfiguration(new JavaHolidays()
      , List.of(new JavaConfiguration(new JavaHolidays()
      .addFixed(new JavaFixed("LADY_GOOD_VOYAGE", HolidayType.OFFICIAL_HOLIDAY, null, null, YearCycle.EVERY_YEAR, null, MonthDay.of(8, 15)))
      .addFixed(new JavaFixed("LADY_CONCEICAO", HolidayType.OFFICIAL_HOLIDAY, null, null, YearCycle.EVERY_YEAR, null, MonthDay.of(12, 8)))
      , null, "bh", "Belo Horizonte")), "mg", "Minas Gerais"), new JavaConfiguration(new JavaHolidays()
      .addFixed(new JavaFixed("FOUNDATION", HolidayType.OFFICIAL_HOLIDAY, null, null, YearCycle.EVERY_YEAR, null, MonthDay.of(10, 11)))
      , List.of(new JavaConfiguration(new JavaHolidays()
      .addFixed(new JavaFixed("FOUNDATION", HolidayType.OFFICIAL_HOLIDAY, null, null, YearCycle.EVERY_YEAR, null, MonthDay.of(8, 26)))
      , null, "cg", "Campo Grande"), new JavaConfiguration(new JavaHolidays()
      .addFixed(new JavaFixed("LADY_CONCEICAO", HolidayType.OFFICIAL_HOLIDAY, null, null, YearCycle.EVERY_YEAR, null, MonthDay.of(12, 8)))
      , null, "do", "Dourados")), "ms", "Mato Grosso do Sul"), new JavaConfiguration(new JavaHolidays()
      , List.of(new JavaConfiguration(new JavaHolidays()
      .addFixed(new JavaFixed("LADY_LIGHT_PINE", HolidayType.OFFICIAL_HOLIDAY, null, null, YearCycle.EVERY_YEAR, null, MonthDay.of(9, 8)))
      , null, "cu", "Curitiba")), "pr", "Parana"), new JavaConfiguration(new JavaHolidays()
      .addFixed(new JavaFixed("FARROUS_REVOLUTION", HolidayType.OFFICIAL_HOLIDAY, null, null, YearCycle.EVERY_YEAR, null, MonthDay.of(9, 20)))
      , List.of(new JavaConfiguration(new JavaHolidays()
      .addFixed(new JavaFixed("LADY_CONCEICAO", HolidayType.OFFICIAL_HOLIDAY, null, null, YearCycle.EVERY_YEAR, null, MonthDay.of(12, 8)))
      , null, "sm", "Santa Maria")), "rs", "Rio Grande do Sul"), new JavaConfiguration(new JavaHolidays()
      .addFixed(new JavaFixed("MARTYRS_DAY", HolidayType.OFFICIAL_HOLIDAY, null, null, YearCycle.EVERY_YEAR, null, MonthDay.of(10, 3)))
      , List.of(new JavaConfiguration(new JavaHolidays()
      .addFixed(new JavaFixed("LADY_APRESENTACAO", HolidayType.OFFICIAL_HOLIDAY, null, null, YearCycle.EVERY_YEAR, null, MonthDay.of(11, 21)))
      , null, "na", "Natal")), "rn", "Rio Grande do Norte"), new JavaConfiguration(new JavaHolidays()
      .addFixed(new JavaFixed("FOUNDATION", HolidayType.OFFICIAL_HOLIDAY, null, null, YearCycle.EVERY_YEAR, null, MonthDay.of(10, 24)))
      , null, "go", "Goiás"), new JavaConfiguration(new JavaHolidays()
      .addFixed(new JavaFixed("LADY_CONCEICAO", HolidayType.OFFICIAL_HOLIDAY, null, null, YearCycle.EVERY_YEAR, null, MonthDay.of(12, 8)))
      , null, "pe", "Pernambuco"), new JavaConfiguration(new JavaHolidays()
      .addFixed(new JavaFixed("JOAO_PESSOA_DAY", HolidayType.OFFICIAL_HOLIDAY, null, null, YearCycle.EVERY_YEAR, null, MonthDay.of(7, 26)))
      .addFixed(new JavaFixed("FOUNDATION", HolidayType.OFFICIAL_HOLIDAY, null, null, YearCycle.EVERY_YEAR, null, MonthDay.of(8, 5)))
      , List.of(new JavaConfiguration(new JavaHolidays()
      .addFixed(new JavaFixed("ST_JOHN", HolidayType.OFFICIAL_HOLIDAY, null, null, YearCycle.EVERY_YEAR, null, MonthDay.of(6, 24)))
      .addFixed(new JavaFixed("LADY_CONCEICAO", HolidayType.OFFICIAL_HOLIDAY, null, null, YearCycle.EVERY_YEAR, null, MonthDay.of(12, 8)))
      , null, "jpa", "João Pessoa")), "pb", "Paraíba")), "br", "Brazil");
  }
}
