package de.focus_shift.jollyday.tests.country;

import org.junit.jupiter.api.Test;

import java.time.Year;

import static de.focus_shift.jollyday.core.HolidayCalendar.BRAZIL;
import static de.focus_shift.jollyday.core.HolidayType.OBSERVANCE;
import static de.focus_shift.jollyday.tests.CalendarCheckerApi.assertFor;
import static java.time.DayOfWeek.FRIDAY;
import static java.time.DayOfWeek.MONDAY;
import static java.time.DayOfWeek.SUNDAY;
import static java.time.DayOfWeek.THURSDAY;
import static java.time.DayOfWeek.TUESDAY;
import static java.time.DayOfWeek.WEDNESDAY;
import static java.time.Month.APRIL;
import static java.time.Month.AUGUST;
import static java.time.Month.DECEMBER;
import static java.time.Month.JANUARY;
import static java.time.Month.JULY;
import static java.time.Month.JUNE;
import static java.time.Month.MARCH;
import static java.time.Month.MAY;
import static java.time.Month.NOVEMBER;
import static java.time.Month.OCTOBER;
import static java.time.Month.SEPTEMBER;

class HolidayBRTest {

  @Test
  void ensuresHolidays() {
    assertFor(BRAZIL)
      .hasFixedHoliday("NEW_YEAR", JANUARY, 1).and()
      .hasFixedHoliday("TIRADENTES", APRIL, 21).and()
      .hasFixedHoliday("LABOUR_DAY", MAY, 1).and()
      .hasFixedHoliday("INDEPENDENCE_DAY", SEPTEMBER, 7).and()
      .hasFixedHoliday("APARECIDA", OCTOBER, 12)
        .notValidBetween(Year.of(1900), Year.of(1979))
        .validFrom(Year.of(1980))
      .and()
      .hasFixedHoliday("ALL_SOULS", NOVEMBER, 2).and()
      .hasFixedHoliday("NATIONAL_DAY", NOVEMBER, 15).and()
      .hasFixedHoliday("NATIONAL_DAY_OF_ZUMBI_AND_BLACK_AWARENESS", NOVEMBER, 20)
        .notValidBetween(Year.of(1900), Year.of(2023))
        .validFrom(Year.of(2024))
      .and()
      .hasFixedHoliday("CHRISTMAS_EVE", DECEMBER, 24, OBSERVANCE).and()
      .hasFixedHoliday("CHRISTMAS", DECEMBER, 25).and()
      .hasFixedHoliday("NEW_YEARS_EVE", DECEMBER, 31, OBSERVANCE).and()
      .hasChristianHoliday("EASTER").and()
      .hasChristianHoliday("CARNIVAL").and()
      .hasChristianHoliday("ASH_WEDNESDAY").and()
      .hasChristianHoliday("GOOD_FRIDAY").and()
      .hasChristianHoliday("CORPUS_CHRISTI")
      .check();
  }

  @Test
  void ensuresHolidaysAcre() {
    assertFor(BRAZIL)
      // Lei Estadual (AC) 3.137/2016 (validFrom=2017), transfer rule Lei 2.126/2009
      .hasFixedHoliday("CATHOLIC_DAY", JANUARY, 20)
        .notValidBetween(Year.of(1900), Year.of(2016))
        .validFrom(Year.of(2017))
        .canBeMovedFrom(TUESDAY, FRIDAY)
        .canBeMovedFrom(WEDNESDAY, FRIDAY)
        .canBeMovedFrom(THURSDAY, FRIDAY)
        .inSubdivision("ac")
      .and()
      // Lei Estadual (AC) 1.538/2004, transfer rule Lei 2.126/2009
      .hasFixedHoliday("EVANGELICAL_DAY", JANUARY, 23)
        .canBeMovedFrom(TUESDAY, FRIDAY)
        .canBeMovedFrom(WEDNESDAY, FRIDAY)
        .canBeMovedFrom(THURSDAY, FRIDAY)
        .inSubdivision("ac")
      .and()
      // Lei Estadual (AC) 1.411/2001, transfer rule Lei 2.126/2009
      .hasFixedHoliday("INTERNATIONAL_WOMAN", MARCH, 8)
        .canBeMovedFrom(TUESDAY, FRIDAY)
        .canBeMovedFrom(WEDNESDAY, FRIDAY)
        .canBeMovedFrom(THURSDAY, FRIDAY)
        .inSubdivision("ac")
      .and()
      // Lei Estadual (AC) 14/1964 — exempt from transfer rule
      .hasFixedHoliday("FOUNDATION", JUNE, 15).inSubdivision("ac").and()
      // Lei Estadual (AC) 1.526/2004, transfer rule Lei 2.126/2009
      .hasFixedHoliday("AMAZON_DAY", SEPTEMBER, 5)
        .canBeMovedFrom(TUESDAY, FRIDAY)
        .canBeMovedFrom(WEDNESDAY, FRIDAY)
        .canBeMovedFrom(THURSDAY, FRIDAY)
        .inSubdivision("ac")
      .and()
      // Lei Estadual (AC) 57/1965, transfer rule Lei 2.126/2009
      .hasFixedHoliday("TREATY_OF_PETROPOLIS", NOVEMBER, 17)
        .canBeMovedFrom(TUESDAY, FRIDAY)
        .canBeMovedFrom(WEDNESDAY, FRIDAY)
        .canBeMovedFrom(THURSDAY, FRIDAY)
        .inSubdivision("ac")
      .check();
  }

  @Test
  void ensuresHolidaysNorthAndNortheast() {
    assertFor(BRAZIL)
      // Alagoas
      .hasFixedHoliday("ST_JOHN", JUNE, 24).inSubdivision("al").and()
      .hasFixedHoliday("ST_PETER", JUNE, 29).inSubdivision("al").and()
      .hasFixedHoliday("POLITICAL_EMANCIPATION", SEPTEMBER, 16).inSubdivision("al").and()
      .hasFixedHoliday("EVANGELICAL_DAY", NOVEMBER, 30).inSubdivision("al").and()
      // Amazonas
      .hasFixedHoliday("FOUNDATION", SEPTEMBER, 5).inSubdivision("am").and()
      // Amapá
      .hasFixedHoliday("ST_JOSEPH", MARCH, 19).inSubdivision("ap").and()
      .hasFixedHoliday("CABRALZINHO_DAY", MAY, 15).inSubdivision("ap").and()
      .hasFixedHoliday("FOUNDATION", SEPTEMBER, 13).inSubdivision("ap").and()
      // Bahia
      .hasFixedHoliday("BAHIA_INDEPENDENCE", JULY, 2).inSubdivision("ba").and()
      // Ceará
      .hasFixedHoliday("ABOLITION_OF_SLAVERY", MARCH, 25).inSubdivision("ce").and()
      // Maranhão
      .hasFixedHoliday("ADHESION_TO_INDEPENDENCE", JULY, 28).inSubdivision("ma").and()
      // Pará
      .hasFixedHoliday("ADHESION_TO_INDEPENDENCE", AUGUST, 15).inSubdivision("pa").and()
      // Pernambuco
      .hasFixedHoliday("PERNAMBUCAN_REVOLUTION", MARCH, 6).inSubdivision("pe").and()
      .hasFixedHoliday("ST_JOHN", JUNE, 24).inSubdivision("pe").and()
      .hasFixedHoliday("LADY_CONCEICAO", DECEMBER, 8).inSubdivision("pe", "rec").and()
      // Paraíba
      .hasFixedHoliday("JOAO_PESSOA_DAY", JULY, 26).inSubdivision("pb", "jpa").and()
      // Piauí
      .hasFixedHoliday("PIAUI_DAY", OCTOBER, 19).inSubdivision("pi").and()
      // Rio Grande do Norte
      .hasFixedHoliday("MARTYRS_DAY", OCTOBER, 3).inSubdivision("rn").and()
      // Rondônia
      .hasFixedHoliday("FOUNDATION", JANUARY, 4).inSubdivision("ro").and()
      .hasFixedHoliday("EVANGELICAL_DAY", JUNE, 18).inSubdivision("ro").and()
      // Roraima
      .hasFixedHoliday("FOUNDATION", OCTOBER, 5).inSubdivision("rr").and()
      // Sergipe
      .hasFixedHoliday("POLITICAL_EMANCIPATION", JULY, 8).inSubdivision("se").and()
      // Tocantins
      .hasFixedHoliday("AUTONOMY_DAY", MARCH, 18).inSubdivision("to").and()
      .hasFixedHoliday("LORD_OF_BONFIM", AUGUST, 15)
        .notValidBetween(Year.of(1900), Year.of(2024))
        .validFrom(Year.of(2025))
        .inSubdivision("to")
      .and()
      .hasFixedHoliday("LADY_NATIVIDADE", SEPTEMBER, 8).inSubdivision("to").and()
      .hasFixedHoliday("FOUNDATION", OCTOBER, 5).inSubdivision("to")
      .check();
  }

  @Test
  void ensuresHolidaysCentralSouthAndSoutheast() {
    assertFor(BRAZIL)
      // Distrito Federal
      .hasFixedHoliday("EVANGELICAL_DAY", NOVEMBER, 30).inSubdivision("df").and()
      // Espírito Santo — Our Lady of Penha: Easter+8 (Lei 11.010/2019)
      .hasRelativeToEasterSundayHoliday("LADY_PENHA").inSubdivision("es").and()
      // Goiás
      .hasFixedHoliday("ST_ANNE", JULY, 26).inSubdivision("go").and()
      .hasFixedHoliday("FOUNDATION", OCTOBER, 24).inSubdivision("go", "gyn").and()
      // Mato Grosso do Sul
      .hasFixedHoliday("FOUNDATION", OCTOBER, 11).inSubdivision("ms").and()
      // Rio de Janeiro — St. George (Lei 5.243/2008 makes Carnival Tuesday a state holiday; date
      // overlaps the national Carnival ponto facultativo already in the baseline, so no extra entry needed)
      .hasFixedHoliday("ST_GEORGE", APRIL, 23)
        .notValidBetween(Year.of(1900), Year.of(2006))
        .validFrom(Year.of(2007))
        .inSubdivision("rj")
      .and()
      // Rio Grande do Sul
      .hasFixedHoliday("FARROUS_REVOLUTION", SEPTEMBER, 20).inSubdivision("rs").and()
      // Santa Catarina — Data Magna moved to following Sunday when on a weekday
      .hasFixedHoliday("SANTA_CATARINA_DAY", AUGUST, 11)
        .canBeMovedFrom(MONDAY, SUNDAY)
        .canBeMovedFrom(TUESDAY, SUNDAY)
        .canBeMovedFrom(WEDNESDAY, SUNDAY)
        .canBeMovedFrom(THURSDAY, SUNDAY)
        .canBeMovedFrom(FRIDAY, SUNDAY)
        .inSubdivision("sc")
      .and()
      // São Paulo
      .hasFixedHoliday("CONST_REVOLUTION", JULY, 9).inSubdivision("sp")
      .check();
  }
}
