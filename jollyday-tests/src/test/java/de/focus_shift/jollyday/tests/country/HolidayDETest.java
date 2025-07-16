package de.focus_shift.jollyday.tests.country;

import org.junit.jupiter.api.Test;

import java.time.Year;

import static de.focus_shift.jollyday.core.HolidayCalendar.GERMANY;
import static de.focus_shift.jollyday.tests.CalendarCheckerApi.assertFor;
import static java.time.Month.AUGUST;
import static java.time.Month.DECEMBER;
import static java.time.Month.JANUARY;
import static java.time.Month.JUNE;
import static java.time.Month.MARCH;
import static java.time.Month.MAY;
import static java.time.Month.NOVEMBER;
import static java.time.Month.OCTOBER;
import static java.time.Month.SEPTEMBER;

class HolidayDETest {

  @Test
  void ensuresHolidays() {
    assertFor(GERMANY)
      .hasFixedHoliday("NEW_YEAR", JANUARY, 1).and()
      .hasFixedHoliday("LABOUR_DAY", MAY, 1).and()
      .hasFixedHoliday("UNIFICATION", JUNE, 17)
        .notBetween(Year.of(1900), Year.of(1953))
        .between(Year.of(1954), Year.of(1990))
        .notBetween(Year.of(1991), Year.of(2500))
      .and()
      .hasFixedHoliday("UNIFICATION_GERMANY", OCTOBER, 3)
        .notBetween(Year.of(1900), Year.of(1989))
        .from(Year.of(1990))
      .and()
      .hasFixedHoliday("REFORMATION_DAY", OCTOBER, 31)
        .between(Year.of(2017), Year.of(2017))
      .and()
      .hasFixedHoliday("FIRST_CHRISTMAS_DAY", DECEMBER, 25).and()
      .hasFixedHoliday("SECOND_CHRISTMAS_DAY", DECEMBER, 26).and()
      .hasChristianHoliday("GOOD_FRIDAY").and()
      .hasChristianHoliday("EASTER_MONDAY").and()
      .hasChristianHoliday("ASCENSION_DAY").and()
      .hasChristianHoliday("WHIT_MONDAY").and()

      /* Berlin */

      /* Brandenburg */
      .hasFixedHoliday("REFORMATION_DAY", OCTOBER, 31).inSubdivision("bb").and()
      .hasChristianHoliday("EASTER").inSubdivision("bb").and()
      .hasChristianHoliday("PENTECOST").inSubdivision("bb").and()

      /* Baden-Württemberg */
      .hasFixedHoliday("EPIPHANY", JANUARY, 6).inSubdivision("bw").and()
      .hasFixedHoliday("ALL_SAINTS", NOVEMBER, 1).inSubdivision("bw").and()
      .hasChristianHoliday("CORPUS_CHRISTI").inSubdivision("bw").and()

      /* Bavaria */
      .hasFixedHoliday("EPIPHANY", JANUARY, 6).inSubdivision("by").and()
      .hasFixedHoliday("ALL_SAINTS", NOVEMBER, 1).inSubdivision("by").and()
      .hasChristianHoliday("CORPUS_CHRISTI").inSubdivision("by").and()

      /* Munich */
      .hasFixedHoliday("ASSUMPTION_DAY", AUGUST, 15).inSubdivision("by", "mu").and()
      /* Augsburg */
      .hasFixedHoliday("PEACE", AUGUST, 8).inSubdivision("by", "ag").and()
      .hasFixedHoliday("ASSUMPTION_DAY", AUGUST, 15).inSubdivision("by", "ag").and()
      /* Würzburg */
      .hasFixedHoliday("ASSUMPTION_DAY", AUGUST, 15).inSubdivision("by", "wu").and()
      /* Regensburg */
      .hasFixedHoliday("ASSUMPTION_DAY", AUGUST, 15).inSubdivision("by", "re").and()
      /* Ingolstadt */
      .hasFixedHoliday("ASSUMPTION_DAY", AUGUST, 15).inSubdivision("by", "in").and()

      /* Hessen */
      .hasChristianHoliday("CORPUS_CHRISTI").inSubdivision("he").and()

      /* Mecklenburg-Vorpommern */
      .hasFixedHoliday("INTERNATIONAL_WOMAN", MARCH, 8).from(Year.of(2023)).inSubdivision("mv").and()
      .hasFixedHoliday("REFORMATION_DAY", OCTOBER, 31).inSubdivision("mv").and()

      /* North Rhine-Westphalia */
      .hasFixedHoliday("ALL_SAINTS", NOVEMBER, 1).inSubdivision("nw").and()
      .hasChristianHoliday("CORPUS_CHRISTI").inSubdivision("nw").and()

      /* Rhineland-Palatinate */
      .hasFixedHoliday("ALL_SAINTS", NOVEMBER, 1).inSubdivision("rp").and()
      .hasChristianHoliday("CORPUS_CHRISTI").inSubdivision("rp").and()

      /* Saarland */
      .hasFixedHoliday("ALL_SAINTS", NOVEMBER, 1).inSubdivision("sl").and()
      .hasFixedHoliday("ASSUMPTION_DAY", AUGUST, 15).inSubdivision("sl").and()
      .hasChristianHoliday("CORPUS_CHRISTI").inSubdivision("sl").and()

      /* Saxony */
      .hasFixedHoliday("REFORMATION_DAY", OCTOBER, 31).inSubdivision("sn").and()

      /* Saxony-Anhalt */
      .hasFixedHoliday("EPIPHANY", JANUARY, 6).inSubdivision("st").and()
      .hasFixedHoliday("REFORMATION_DAY", OCTOBER, 31).inSubdivision("st").and()

      /* Thuringia */
      .hasFixedHoliday("REFORMATION_DAY", OCTOBER, 31).inSubdivision("th").and()
      .hasFixedHoliday("CHILDRENS_DAY", SEPTEMBER, 20).from(Year.of(2019)).inSubdivision("th").and()

      /* Schleswig-Holstein */
      .hasFixedHoliday("REFORMATION_DAY", OCTOBER, 31).from(Year.of(2018)).inSubdivision("sh").and()

      /* Hamburg */
      .hasFixedHoliday("REFORMATION_DAY", OCTOBER, 31).from(Year.of(2018)).inSubdivision("hh").and()

      /* Bremen */
      .hasFixedHoliday("REFORMATION_DAY", OCTOBER, 31).from(Year.of(2018)).inSubdivision("hb").and()

      /* Lower-Saxony */
      .hasFixedHoliday("REFORMATION_DAY", OCTOBER, 31).from(Year.of(2018)).inSubdivision("ni")

      .check();
  }
}
