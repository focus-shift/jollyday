package de.focus_shift.jollyday.tests.country;

import org.junit.jupiter.api.Test;

import java.time.Year;

import static de.focus_shift.jollyday.core.HolidayCalendar.ITALY;
import static de.focus_shift.jollyday.tests.CalendarCheckerApi.assertFor;
import static java.time.Month.APRIL;
import static java.time.Month.AUGUST;
import static java.time.Month.DECEMBER;
import static java.time.Month.JANUARY;
import static java.time.Month.JULY;
import static java.time.Month.JUNE;
import static java.time.Month.MAY;
import static java.time.Month.NOVEMBER;
import static java.time.Month.OCTOBER;
import static java.time.Month.SEPTEMBER;

class HolidayITTest {

  @Test
  void ensuresItalianHolidays() {
    assertFor(ITALY)
      .hasFixedHoliday("NEW_YEAR", JANUARY, 1)
        .notValidBetween(Year.of(1900), Year.of(1966))
        .validFrom(Year.of(1967))
      .and()
      .hasFixedHoliday("EPIPHANY", JANUARY, 6).and()
      .hasFixedHoliday("LIBERATION", APRIL, 25).and()
      .hasFixedHoliday("LABOUR_DAY", MAY, 1).and()
      .hasFixedHoliday("REPUBLIC_DAY", JUNE, 2).and()
      .hasFixedHoliday("ASSUMPTION_DAY", AUGUST, 15).and()
      .hasFixedHoliday("ST_FRANCIS_OF_ASSISI", OCTOBER, 4)
        .notValidBetween(Year.of(1900), Year.of(2025))
        .validBetween(Year.of(2026), Year.of(2500))
      .and()
      .hasFixedHoliday("ALL_SAINTS", NOVEMBER, 1).and()
      .hasFixedHoliday("IMMACULATE_CONCEPTION", DECEMBER, 8).and()
      .hasFixedHoliday("CHRISTMAS", DECEMBER, 25).and()
      .hasFixedHoliday("STEPHENS", DECEMBER, 26).and()
      .hasChristianHoliday("EASTER").and()
      .hasChristianHoliday("EASTER_MONDAY")
        .validFrom(Year.of(1642))
      .and()

      /*Trentino-South Tyrol*/
      .hasChristianHoliday("WHIT_MONDAY").inSubdivision("32").and()

      // patronal festivals of the regional capitals
      /*Turin*/
      .hasFixedHoliday("ST_JOHN", JUNE, 24).inSubdivision("21", "to").and()
      /*Aosta*/
      .hasFixedHoliday("ST_GRATUS", SEPTEMBER, 7).inSubdivision("23", "ao").and()
      /*Milan*/
      .hasFixedHoliday("ST_AMBROSE", DECEMBER, 7).inSubdivision("25", "mi").and()
      /*Trento*/
      .hasFixedHoliday("ST_VIGILIUS", JUNE, 26).inSubdivision("32", "tn").and()
      /*Venice*/
      .hasFixedHoliday("MADONNA_DELLA_SALUTE", NOVEMBER, 21).inSubdivision("34", "ve").and()
      /*Trieste*/
      .hasFixedHoliday("ST_JUST", NOVEMBER, 3).inSubdivision("36", "ts").and()
      /*Genoa*/
      .hasFixedHoliday("ST_JOHN", JUNE, 24).inSubdivision("42", "ge").and()
      /*Bologna*/
      .hasFixedHoliday("ST_PETRONIUS", OCTOBER, 4).inSubdivision("45", "bo").and()
      /*Florence*/
      .hasFixedHoliday("ST_JOHN", JUNE, 24).inSubdivision("52", "fi").and()
      /*Perugia*/
      .hasFixedHoliday("ST_CONSTANTIUS", JANUARY, 29).inSubdivision("55", "pg").and()
      /*Ancona*/
      .hasFixedHoliday("ST_CYRIACUS", MAY, 4).inSubdivision("57", "an").and()
      /*Rome*/
      .hasFixedHoliday("ST_PETER_PAUL", JUNE, 29).inSubdivision("62", "rm").and()
      /*L'Aquila*/
      .hasFixedHoliday("ST_MAXIMUS", JUNE, 10).inSubdivision("65", "aq").and()
      /*Campobasso*/
      .hasFixedHoliday("ST_GEORGE", APRIL, 23).inSubdivision("67", "cb").and()
      /*Naples*/
      .hasFixedHoliday("ST_JANUARIUS", SEPTEMBER, 19).inSubdivision("72", "na").and()
      /*Bari*/
      .hasFixedHoliday("ST_NICHOLAS", MAY, 8).inSubdivision("75", "ba").and()
      /*Potenza*/
      .hasFixedHoliday("ST_GERARD", MAY, 30).inSubdivision("77", "pz").and()
      /*Catanzaro*/
      .hasFixedHoliday("ST_VITALIAN", JULY, 16).inSubdivision("78", "cz").and()
      /*Palermo*/
      .hasFixedHoliday("ST_ROSALIA", JULY, 15).inSubdivision("82", "pa").and()
      /*Cagliari*/
      .hasFixedHoliday("ST_SATURNINUS", OCTOBER, 30).inSubdivision("88", "ca")

      .check();
  }
}
