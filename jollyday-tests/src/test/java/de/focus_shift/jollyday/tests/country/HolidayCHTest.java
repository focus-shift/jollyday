package de.focus_shift.jollyday.tests.country;

import org.junit.jupiter.api.Test;

import java.time.Year;

import static de.focus_shift.jollyday.core.HolidayCalendar.SWITZERLAND;
import static de.focus_shift.jollyday.core.HolidayType.BANK_HOLIDAY;
import static de.focus_shift.jollyday.core.HolidayType.OBSERVANCE;
import static de.focus_shift.jollyday.tests.CalendarCheckerApi.assertFor;
import static java.time.Month.AUGUST;
import static java.time.Month.DECEMBER;
import static java.time.Month.JANUARY;
import static java.time.Month.JUNE;
import static java.time.Month.MARCH;
import static java.time.Month.MAY;
import static java.time.Month.NOVEMBER;
import static java.time.Month.SEPTEMBER;


class HolidayCHTest {

  @Test
  void ensuresHolidays() {
    assertFor(SWITZERLAND)
      .hasFixedHoliday("NEW_YEAR", JANUARY, 1).and()
      .hasFixedHoliday("ST_BERCHTHOLD", JANUARY, 2, BANK_HOLIDAY).and()
      .hasFixedHoliday("NATIONAL_DAY", AUGUST, 1).and()
      .hasFixedHoliday("CHRISTMAS", DECEMBER, 25).and()
      .hasChristianHoliday("ASCENSION_DAY").and()
      .hasChristianHoliday("EASTER").and()

      /* Aargau */
      .hasFixedHoliday("ST_BERCHTHOLD", JANUARY, 2).inSubdivision("ag").and()
      .hasFixedHoliday("ASSUMPTION_DAY", AUGUST, 15).inSubdivision("ag").and()
      .hasFixedHoliday("ALL_SAINTS", NOVEMBER, 1).inSubdivision("ag").and()
      .hasFixedHoliday("STEPHENS", DECEMBER, 26).inSubdivision("ag").and()
      .hasFixedHoliday("IMMACULATE_CONCEPTION", DECEMBER, 8).inSubdivision("ag").and()
      .hasChristianHoliday("GOOD_FRIDAY").inSubdivision("ag").and()
      .hasChristianHoliday("EASTER_MONDAY").inSubdivision("ag").and()
      .hasChristianHoliday("WHIT_MONDAY").inSubdivision("ag").and()
      .hasChristianHoliday("CORPUS_CHRISTI").inSubdivision("ag").and()

      /* Vaud */
      .hasFixedHoliday("ST_BERCHTHOLD", JANUARY, 2).inSubdivision("vd").and()
      .hasChristianHoliday("GOOD_FRIDAY").inSubdivision("vd").and()
      .hasChristianHoliday("EASTER_MONDAY").inSubdivision("vd").and()
      .hasChristianHoliday("WHIT_MONDAY").inSubdivision("vd").and()

      /* Valais */
      .hasFixedHoliday("ST_JOSEPH", MARCH, 19).inSubdivision("vs").and()
      .hasFixedHoliday("ASSUMPTION_DAY", AUGUST, 15).inSubdivision("vs").and()
      .hasFixedHoliday("ALL_SAINTS", NOVEMBER, 1).inSubdivision("vs").and()
      .hasFixedHoliday("IMMACULATE_CONCEPTION", DECEMBER, 8).inSubdivision("vs").and()
      .hasChristianHoliday("CORPUS_CHRISTI").inSubdivision("vs").and()

      /* Innerrhoden */
      .hasFixedHoliday("ASSUMPTION_DAY", AUGUST, 15, OBSERVANCE).inSubdivision("ai").and()
      .hasFixedHoliday("MAURITIUS_DAY", SEPTEMBER, 22).inSubdivision("ai").and()
      .hasFixedHoliday("ALL_SAINTS", NOVEMBER, 1, OBSERVANCE).inSubdivision("ai").and()
      .hasFixedHoliday("IMMACULATE_CONCEPTION", DECEMBER, 8, OBSERVANCE).inSubdivision("ai").and()
      .hasFixedHoliday("STEPHENS", DECEMBER, 26).inSubdivision("ai").and()
      .hasChristianHoliday("GOOD_FRIDAY").inSubdivision("ai").and()
      .hasChristianHoliday("EASTER_MONDAY").inSubdivision("ai").and()
      .hasChristianHoliday("WHIT_MONDAY").inSubdivision("ai").and()
      .hasChristianHoliday("CORPUS_CHRISTI").inSubdivision("ai").and()

      /* Ausserrhoden */
      .hasFixedHoliday("STEPHENS", DECEMBER, 26).inSubdivision("ar").and()
      .hasChristianHoliday("GOOD_FRIDAY").inSubdivision("ar").and()
      .hasChristianHoliday("EASTER_MONDAY").inSubdivision("ar").and()
      .hasChristianHoliday("WHIT_MONDAY").inSubdivision("ar").and()

      /* Bern */
      .hasFixedHoliday("ST_BERCHTHOLD", JANUARY, 2).inSubdivision("be").and()
      .hasFixedHoliday("STEPHENS", DECEMBER, 26).inSubdivision("be").and()
      .hasChristianHoliday("GOOD_FRIDAY").inSubdivision("be").and()
      .hasChristianHoliday("EASTER_MONDAY").inSubdivision("be").and()
      .hasChristianHoliday("WHIT_MONDAY").inSubdivision("be").and()

      /* Basel -Landschaft*/
      .hasFixedHoliday("LABOUR_DAY", MAY, 1).inSubdivision("bl").and()
      .hasFixedHoliday("STEPHENS", DECEMBER, 26).inSubdivision("bl").and()
      .hasChristianHoliday("GOOD_FRIDAY").inSubdivision("bl").and()
      .hasChristianHoliday("EASTER_MONDAY").inSubdivision("bl").and()
      .hasChristianHoliday("WHIT_MONDAY").inSubdivision("bl").and()

      /* Basel -Stadt*/
      .hasFixedHoliday("LABOUR_DAY", MAY, 1).inSubdivision("bs").and()
      .hasFixedHoliday("STEPHENS", DECEMBER, 26).inSubdivision("bs").and()
      .hasChristianHoliday("GOOD_FRIDAY").inSubdivision("bs").and()
      .hasChristianHoliday("EASTER_MONDAY").inSubdivision("bs").and()
      .hasChristianHoliday("WHIT_MONDAY").inSubdivision("bs").and()

      /* Freiburg */
      .hasFixedHoliday("ST_BERCHTHOLD", JANUARY, 2).inSubdivision("fr").and()
      .hasFixedHoliday("ASSUMPTION_DAY", AUGUST, 15).inSubdivision("fr").and()
      .hasFixedHoliday("ALL_SAINTS", NOVEMBER, 1).inSubdivision("fr").and()
      .hasFixedHoliday("IMMACULATE_CONCEPTION", DECEMBER, 8).inSubdivision("fr").and()
      .hasFixedHoliday("STEPHENS", DECEMBER, 26).inSubdivision("fr").and()
      .hasChristianHoliday("GOOD_FRIDAY").inSubdivision("fr").and()
      .hasChristianHoliday("EASTER_MONDAY", OBSERVANCE).inSubdivision("fr").and()
      .hasChristianHoliday("WHIT_MONDAY").inSubdivision("fr").and()
      .hasChristianHoliday("CORPUS_CHRISTI").inSubdivision("fr").and()

      /* Genève */
      .hasFixedHoliday("RESTORATION_OF_THE_REPUBLIC", DECEMBER, 31).inSubdivision("ge").and()
      .hasChristianHoliday("GOOD_FRIDAY").inSubdivision("ge").and()
      .hasChristianHoliday("EASTER_MONDAY").inSubdivision("ge").and()
      .hasChristianHoliday("WHIT_MONDAY").inSubdivision("ge").and()

      /* Glarus */
      .hasFixedHoliday("ALL_SAINTS", NOVEMBER, 1).inSubdivision("gl").and()
      .hasFixedHoliday("STEPHENS", DECEMBER, 26).inSubdivision("gl").and()
      .hasChristianHoliday("GOOD_FRIDAY").inSubdivision("gl").and()
      .hasChristianHoliday("EASTER_MONDAY").inSubdivision("gl").and()
      .hasChristianHoliday("WHIT_MONDAY").inSubdivision("gl").and()

      /* Graubünden */
      .hasFixedHoliday("STEPHENS", DECEMBER, 26).inSubdivision("gr").and()
      .hasChristianHoliday("GOOD_FRIDAY").inSubdivision("gr").and()
      .hasChristianHoliday("EASTER_MONDAY").inSubdivision("gr").and()
      .hasChristianHoliday("WHIT_MONDAY").inSubdivision("gr").and()

      /* Jura */
      .hasFixedHoliday("LABOUR_DAY", MAY, 1).inSubdivision("ju").and()
      .hasFixedHoliday("INDEPENDENCE_DAY", JUNE, 23).inSubdivision("ju").and()
      .hasFixedHoliday("ASSUMPTION_DAY", AUGUST, 15, OBSERVANCE).inSubdivision("ju").and()
      .hasFixedHoliday("ALL_SAINTS", NOVEMBER, 1, OBSERVANCE).inSubdivision("ju").and()
      .hasFixedHoliday("IMMACULATE_CONCEPTION", DECEMBER, 8, OBSERVANCE).inSubdivision("ju").and()
      .hasChristianHoliday("GOOD_FRIDAY").inSubdivision("ju").and()
      .hasChristianHoliday("EASTER_MONDAY").inSubdivision("ju").and()
      .hasChristianHoliday("WHIT_MONDAY").inSubdivision("ju").and()
      .hasChristianHoliday("CORPUS_CHRISTI").inSubdivision("ju").and()

      /* Luzern */
      .hasFixedHoliday("ST_JOSEPH", MARCH, 19, OBSERVANCE).inSubdivision("lu").and()
      .hasFixedHoliday("ASSUMPTION_DAY", AUGUST, 15).inSubdivision("lu").and()
      .hasFixedHoliday("ALL_SAINTS", NOVEMBER, 1).inSubdivision("lu").and()
      .hasFixedHoliday("IMMACULATE_CONCEPTION", DECEMBER, 8).inSubdivision("lu").and()
      .hasFixedHoliday("STEPHENS", DECEMBER, 26).inSubdivision("lu").and()
      .hasChristianHoliday("GOOD_FRIDAY").inSubdivision("lu").and()
      .hasChristianHoliday("EASTER_MONDAY", OBSERVANCE).inSubdivision("lu").and()
      .hasChristianHoliday("CORPUS_CHRISTI").inSubdivision("lu").and()

      /* Neuchâtel */
      .hasFixedHoliday("REPUBLIC_DAY", MARCH, 1).inSubdivision("ne").and()
      .hasFixedHoliday("LABOUR_DAY", MAY, 1).inSubdivision("ne").and()
      .hasChristianHoliday("GOOD_FRIDAY").inSubdivision("ne").and()
      .hasChristianHoliday("EASTER_MONDAY", OBSERVANCE).inSubdivision("ne").and()

      /* Nidwalden */
      .hasFixedHoliday("ST_JOSEPH", MARCH, 19, OBSERVANCE).inSubdivision("nw").and()
      .hasFixedHoliday("ASSUMPTION_DAY", AUGUST, 15).inSubdivision("nw").and()
      .hasFixedHoliday("ALL_SAINTS", NOVEMBER, 1).inSubdivision("nw").and()
      .hasFixedHoliday("IMMACULATE_CONCEPTION", DECEMBER, 8).inSubdivision("nw").and()
      .hasFixedHoliday("STEPHENS", DECEMBER, 26).inSubdivision("nw").and()
      .hasChristianHoliday("GOOD_FRIDAY").inSubdivision("nw").and()
      .hasChristianHoliday("EASTER_MONDAY", OBSERVANCE).inSubdivision("nw").and()
      .hasChristianHoliday("CORPUS_CHRISTI").inSubdivision("nw").and()

      /* Obwalden */
      .hasFixedHoliday("ASSUMPTION_DAY", AUGUST, 15).inSubdivision("ow").and()
      .hasFixedHoliday("ST_NICHOLAS", SEPTEMBER, 25).validFrom(Year.of(1947)).inSubdivision("ow").and()
      .hasFixedHoliday("ALL_SAINTS", NOVEMBER, 1).inSubdivision("ow").and()
      .hasFixedHoliday("IMMACULATE_CONCEPTION", DECEMBER, 8).inSubdivision("ow").and()
      .hasFixedHoliday("STEPHENS", DECEMBER, 26).inSubdivision("ow").and()
      .hasChristianHoliday("GOOD_FRIDAY").inSubdivision("ow").and()
      .hasChristianHoliday("EASTER_MONDAY", OBSERVANCE).inSubdivision("ow").and()
      .hasChristianHoliday("WHIT_MONDAY", OBSERVANCE).inSubdivision("ow").and()
      .hasChristianHoliday("CORPUS_CHRISTI").inSubdivision("ow").and()

      /* Gallen */
      .hasFixedHoliday("ALL_SAINTS", NOVEMBER, 1).inSubdivision("sg").and()
      .hasFixedHoliday("STEPHENS", DECEMBER, 26).inSubdivision("sg").and()
      .hasChristianHoliday("GOOD_FRIDAY").inSubdivision("sg").and()
      .hasChristianHoliday("EASTER_MONDAY").inSubdivision("sg").and()
      .hasChristianHoliday("WHIT_MONDAY").inSubdivision("sg").and()

      /* Schaffhausen */
      .hasFixedHoliday("ST_BERCHTHOLD", JANUARY, 2).inSubdivision("sh").and()
      .hasFixedHoliday("LABOUR_DAY", MAY, 1).inSubdivision("sh").and()
      .hasFixedHoliday("STEPHENS", DECEMBER, 26).inSubdivision("sh").and()
      .hasChristianHoliday("GOOD_FRIDAY").inSubdivision("sh").and()
      .hasChristianHoliday("EASTER_MONDAY").inSubdivision("sh").and()
      .hasChristianHoliday("WHIT_MONDAY").inSubdivision("sh").and()

      /* Solothurn */
      .hasFixedHoliday("LABOUR_DAY", MAY, 1).inSubdivision("so").and()
      .hasFixedHoliday("ASSUMPTION_DAY", AUGUST, 15).inSubdivision("so").and()
      .hasFixedHoliday("ALL_SAINTS", NOVEMBER, 1).inSubdivision("so").and()
      .hasFixedHoliday("IMMACULATE_CONCEPTION", DECEMBER, 8).inSubdivision("so").and()
      .hasChristianHoliday("GOOD_FRIDAY").inSubdivision("so").and()
      .hasChristianHoliday("CORPUS_CHRISTI").inSubdivision("so").and()

      /* Schwyz */
      .hasFixedHoliday("EPIPHANY", JANUARY, 6, OBSERVANCE).inSubdivision("sz").and()
      .hasFixedHoliday("ST_JOSEPH", MARCH, 19).inSubdivision("sz").and()
      .hasFixedHoliday("ASSUMPTION_DAY", AUGUST, 15).inSubdivision("sz").and()
      .hasFixedHoliday("ALL_SAINTS", NOVEMBER, 1).inSubdivision("sz").and()
      .hasFixedHoliday("IMMACULATE_CONCEPTION", DECEMBER, 8).inSubdivision("sz").and()
      .hasFixedHoliday("STEPHENS", DECEMBER, 26).inSubdivision("sz").and()
      .hasChristianHoliday("GOOD_FRIDAY").inSubdivision("sz").and()
      .hasChristianHoliday("EASTER_MONDAY").inSubdivision("sz").and()
      .hasChristianHoliday("WHIT_MONDAY", OBSERVANCE).inSubdivision("sz").and()
      .hasChristianHoliday("CORPUS_CHRISTI").inSubdivision("sz").and()

      /* Thurgau */
      .hasFixedHoliday("ST_BERCHTHOLD", JANUARY, 2).inSubdivision("tg").and()
      .hasFixedHoliday("LABOUR_DAY", MAY, 1, OBSERVANCE).inSubdivision("tg").and()
      .hasFixedHoliday("STEPHENS", DECEMBER, 26).inSubdivision("tg").and()
      .hasChristianHoliday("GOOD_FRIDAY").inSubdivision("tg").and()
      .hasChristianHoliday("EASTER_MONDAY").inSubdivision("tg").and()
      .hasChristianHoliday("WHIT_MONDAY").inSubdivision("tg").and()

      /* Ticino */
      .hasFixedHoliday("EPIPHANY", JANUARY, 6).inSubdivision("ti").and()
      .hasFixedHoliday("ST_JOSEPH", MARCH, 19, OBSERVANCE).inSubdivision("ti").and()
      .hasFixedHoliday("LABOUR_DAY", MAY, 1, OBSERVANCE).inSubdivision("ti").and()
      .hasFixedHoliday("ASSUMPTION_DAY", AUGUST, 15).inSubdivision("ti").and()
      .hasFixedHoliday("ALL_SAINTS", NOVEMBER, 1).inSubdivision("ti").and()
      .hasFixedHoliday("IMMACULATE_CONCEPTION", DECEMBER, 8).inSubdivision("ti").and()
      .hasFixedHoliday("STEPHENS", DECEMBER, 26).inSubdivision("ti").and()
      .hasChristianHoliday("EASTER_MONDAY").inSubdivision("ti").and()
      .hasChristianHoliday("WHIT_MONDAY", OBSERVANCE).inSubdivision("ti").and()
      .hasChristianHoliday("CORPUS_CHRISTI", OBSERVANCE).inSubdivision("ti").and()

      /* Uri */
      .hasFixedHoliday("EPIPHANY", JANUARY, 6, OBSERVANCE).inSubdivision("ur").and()
      .hasFixedHoliday("ST_JOSEPH", MARCH, 19, OBSERVANCE).inSubdivision("ur").and()
      .hasFixedHoliday("ASSUMPTION_DAY", AUGUST, 15).inSubdivision("ur").and()
      .hasFixedHoliday("ALL_SAINTS", NOVEMBER, 1).inSubdivision("ur").and()
      .hasFixedHoliday("IMMACULATE_CONCEPTION", DECEMBER, 8).inSubdivision("ur").and()
      .hasFixedHoliday("STEPHENS", DECEMBER, 26).inSubdivision("ur").and()
      .hasChristianHoliday("GOOD_FRIDAY").inSubdivision("ur").and()
      .hasChristianHoliday("EASTER_MONDAY").inSubdivision("ur").and()
      .hasChristianHoliday("WHIT_MONDAY", OBSERVANCE).inSubdivision("ur").and()
      .hasChristianHoliday("CORPUS_CHRISTI").inSubdivision("ur").and()

      /* Vaud */
      .hasFixedHoliday("ST_BERCHTHOLD", JANUARY, 2).inSubdivision("vd").and()
      .hasChristianHoliday("GOOD_FRIDAY").inSubdivision("vd").and()
      .hasChristianHoliday("EASTER_MONDAY").inSubdivision("vd").and()
      .hasChristianHoliday("WHIT_MONDAY").inSubdivision("vd").and()

      /* Valais */
      .hasFixedHoliday("ST_JOSEPH", MARCH, 19).inSubdivision("vs").and()
      .hasFixedHoliday("ASSUMPTION_DAY", AUGUST, 15).inSubdivision("vs").and()
      .hasFixedHoliday("ALL_SAINTS", NOVEMBER, 1).inSubdivision("vs").and()
      .hasFixedHoliday("IMMACULATE_CONCEPTION", DECEMBER, 8).inSubdivision("vs").and()
      .hasChristianHoliday("CORPUS_CHRISTI").inSubdivision("vs").and()

      /* Zug */
      .hasFixedHoliday("ASSUMPTION_DAY", AUGUST, 15).inSubdivision("zg").and()
      .hasFixedHoliday("ALL_SAINTS", NOVEMBER, 1).inSubdivision("zg").and()
      .hasFixedHoliday("IMMACULATE_CONCEPTION", DECEMBER, 8).inSubdivision("zg").and()
      .hasFixedHoliday("STEPHENS", DECEMBER, 26).inSubdivision("zg").and()
      .hasChristianHoliday("GOOD_FRIDAY").inSubdivision("zg").and()
      .hasChristianHoliday("EASTER_MONDAY", OBSERVANCE).inSubdivision("zg").and()
      .hasChristianHoliday("CORPUS_CHRISTI").inSubdivision("zg").and()

      /* Zürich */
      .hasFixedHoliday("LABOUR_DAY", MAY, 1).inSubdivision("zh").and()
      .hasFixedHoliday("STEPHENS", DECEMBER, 26).inSubdivision("zh").and()
      .hasChristianHoliday("GOOD_FRIDAY").inSubdivision("zh").and()
      .hasChristianHoliday("EASTER_MONDAY").inSubdivision("zh").and()
      .hasChristianHoliday("WHIT_MONDAY").inSubdivision("zh")

      .check();
  }
}
