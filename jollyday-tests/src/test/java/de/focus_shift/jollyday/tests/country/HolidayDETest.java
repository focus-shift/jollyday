package de.focus_shift.jollyday.tests.country;

import de.focus_shift.jollyday.core.HolidayManager;
import de.focus_shift.jollyday.core.ManagerParameters;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.MonthDay;
import java.time.Year;

import static de.focus_shift.jollyday.core.HolidayCalendar.GERMANY;
import static de.focus_shift.jollyday.core.spi.Relation.BEFORE;
import static de.focus_shift.jollyday.tests.CalendarCheckerApi.assertFor;
import static java.time.DayOfWeek.WEDNESDAY;
import static java.time.Month.AUGUST;
import static java.time.Month.DECEMBER;
import static java.time.Month.JANUARY;
import static java.time.Month.JUNE;
import static java.time.Month.MARCH;
import static java.time.Month.MAY;
import static java.time.Month.NOVEMBER;
import static java.time.Month.OCTOBER;
import static java.time.Month.SEPTEMBER;
import static org.assertj.core.api.Assertions.assertThat;

class HolidayDETest {

  @Test
  void keepsRegionalHolidaysScopedToTheirSubdivisions() {
    final HolidayManager holidayManager = HolidayManager.getInstance(ManagerParameters.create(GERMANY));
    final LocalDate assumptionDay = LocalDate.of(2026, AUGUST, 15);
    final LocalDate corpusChristi = LocalDate.of(2026, JUNE, 4);

    assertThat(holidayManager.isHoliday(assumptionDay, "by")).isFalse();
    assertThat(holidayManager.isHoliday(assumptionDay, "by", "rk")).isTrue();

    for (final String subdivision : new String[] {"sn", "th"}) {
      assertThat(holidayManager.isHoliday(corpusChristi, subdivision))
        .as("Corpus Christi must remain regional in %s", subdivision)
        .isFalse();
      assertThat(holidayManager.isHoliday(corpusChristi, subdivision, "rk"))
        .as("Corpus Christi must be present in %s/rk", subdivision)
        .isTrue();
    }

    assertThat(holidayManager.isHoliday(LocalDate.of(2026, AUGUST, 8), "by", "rk"))
      .as("The regional Bavaria profile must not inherit Augsburg's Peace Festival")
      .isFalse();

    for (final String subdivision : new String[] {"by", "sn", "th"}) {
      assertThat(holidayManager.isHoliday(LocalDate.of(2026, JANUARY, 1), subdivision, "rk"))
        .as("The regional profile %s/rk must inherit nationwide holidays", subdivision)
        .isTrue();
    }
  }

  @Test
  void ensuresHolidays() {
    assertFor(GERMANY)
      .hasFixedHoliday("NEW_YEAR", JANUARY, 1).and()
      .hasFixedHoliday("LABOUR_DAY", MAY, 1).and()
      .hasFixedHoliday("UNIFICATION", JUNE, 17)
        .notValidBetween(Year.of(1900), Year.of(1953))
        .validBetween(Year.of(1954), Year.of(1990))
        .notValidBetween(Year.of(1991), Year.of(2500))
      .and()
      .hasFixedHoliday("UNIFICATION_GERMANY", OCTOBER, 3)
        .notValidBetween(Year.of(1900), Year.of(1989))
        .validFrom(Year.of(1990))
      .and()
      .hasFixedHoliday("REFORMATION_DAY", OCTOBER, 31)
        .validBetween(Year.of(2017), Year.of(2017))
      .and()
      .hasFixedHoliday("FIRST_CHRISTMAS_DAY", DECEMBER, 25).and()
      .hasFixedHoliday("SECOND_CHRISTMAS_DAY", DECEMBER, 26).and()
      .hasChristianHoliday("GOOD_FRIDAY").and()
      .hasChristianHoliday("EASTER_MONDAY").and()
      .hasChristianHoliday("ASCENSION_DAY").and()
      .hasChristianHoliday("WHIT_MONDAY").and()
      .hasRelativeToFixedHoliday("REPENTANCE_PRAYER", WEDNESDAY, BEFORE, MonthDay.of(NOVEMBER, 23))
        .validBetween(Year.of(1934), Year.of(1938))
        .validBetween(Year.of(1990), Year.of(1994))
      .and()

      /* Berlin */
      .hasRelativeToFixedHoliday("REPENTANCE_PRAYER", WEDNESDAY, BEFORE, MonthDay.of(NOVEMBER, 23)).validBetween(Year.of(1945), Year.of(1989)).inSubdivision("be").and()

      /* Brandenburg */
      .hasFixedHoliday("REFORMATION_DAY", OCTOBER, 31).validFrom(Year.of(1990)).inSubdivision("bb").and()
      .hasChristianHoliday("EASTER").validFrom(Year.of(1991)).inSubdivision("bb").and()
      .hasChristianHoliday("PENTECOST").validFrom(Year.of(1991)).inSubdivision("bb").and()

      /* Baden-Württemberg */
      .hasFixedHoliday("EPIPHANY", JANUARY, 6).inSubdivision("bw").and()
      .hasFixedHoliday("ALL_SAINTS", NOVEMBER, 1).inSubdivision("bw").and()
      .hasRelativeToFixedHoliday("REPENTANCE_PRAYER", WEDNESDAY, BEFORE, MonthDay.of(NOVEMBER, 23)).validBetween(Year.of(1945), Year.of(1989)).inSubdivision("bw").and()
      .hasChristianHoliday("CORPUS_CHRISTI").inSubdivision("bw").and()

      /* Bavaria */
      .hasFixedHoliday("EPIPHANY", JANUARY, 6).inSubdivision("by").and()
      .hasFixedHoliday("ALL_SAINTS", NOVEMBER, 1).inSubdivision("by").and()
      .hasRelativeToFixedHoliday("REPENTANCE_PRAYER", WEDNESDAY, BEFORE, MonthDay.of(NOVEMBER, 23)).validBetween(Year.of(1981), Year.of(1989)).inSubdivision("by").and()
      .hasChristianHoliday("CORPUS_CHRISTI").inSubdivision("by").and()
      .hasFixedHoliday("ASSUMPTION_DAY", AUGUST, 15).inSubdivision("by", "rk").and()

      /* Munich */
      .hasFixedHoliday("ASSUMPTION_DAY", AUGUST, 15).inSubdivision("by", "mu").and()
      /* Augsburg */
      .hasFixedHoliday("PEACE", AUGUST, 8)
        .notValidBetween(Year.of(1900), Year.of(1949))
        .validFrom(Year.of(1950))
        .inSubdivision("by", "ag").and()
      .hasFixedHoliday("ASSUMPTION_DAY", AUGUST, 15).inSubdivision("by", "ag").and()
      /* Würzburg */
      .hasFixedHoliday("ASSUMPTION_DAY", AUGUST, 15).inSubdivision("by", "wu").and()
      /* Regensburg */
      .hasFixedHoliday("ASSUMPTION_DAY", AUGUST, 15).inSubdivision("by", "re").and()
      /* Ingolstadt */
      .hasFixedHoliday("ASSUMPTION_DAY", AUGUST, 15).inSubdivision("by", "in").and()

      /* Hessen */
      .hasRelativeToFixedHoliday("REPENTANCE_PRAYER", WEDNESDAY, BEFORE, MonthDay.of(NOVEMBER, 23)).validBetween(Year.of(1945), Year.of(1989)).inSubdivision("he").and()
      .hasChristianHoliday("CORPUS_CHRISTI").inSubdivision("he").and()

      /* Mecklenburg-Vorpommern */
      .hasFixedHoliday("INTERNATIONAL_WOMAN", MARCH, 8).validFrom(Year.of(2023)).inSubdivision("mv").and()
      .hasFixedHoliday("REFORMATION_DAY", OCTOBER, 31).validFrom(Year.of(1990)).inSubdivision("mv").and()

      /* North Rhine-Westphalia */
      .hasFixedHoliday("ALL_SAINTS", NOVEMBER, 1).inSubdivision("nw").and()
      .hasRelativeToFixedHoliday("REPENTANCE_PRAYER", WEDNESDAY, BEFORE, MonthDay.of(NOVEMBER, 23)).validBetween(Year.of(1945), Year.of(1989)).inSubdivision("nw").and()
      .hasChristianHoliday("CORPUS_CHRISTI").inSubdivision("nw").and()

      /* Rhineland-Palatinate */
      .hasFixedHoliday("ALL_SAINTS", NOVEMBER, 1).inSubdivision("rp").and()
      .hasRelativeToFixedHoliday("REPENTANCE_PRAYER", WEDNESDAY, BEFORE, MonthDay.of(NOVEMBER, 23)).validBetween(Year.of(1945), Year.of(1989)).inSubdivision("rp").and()
      .hasChristianHoliday("CORPUS_CHRISTI").inSubdivision("rp").and()

      /* Saarland */
      .hasFixedHoliday("ALL_SAINTS", NOVEMBER, 1).inSubdivision("sl").and()
      .hasFixedHoliday("ASSUMPTION_DAY", AUGUST, 15).inSubdivision("sl").and()
      .hasRelativeToFixedHoliday("REPENTANCE_PRAYER", WEDNESDAY, BEFORE, MonthDay.of(NOVEMBER, 23)).validBetween(Year.of(1945), Year.of(1989)).inSubdivision("sl").and()
      .hasChristianHoliday("CORPUS_CHRISTI").inSubdivision("sl").and()

      /* Saxony */
      .hasFixedHoliday("REFORMATION_DAY", OCTOBER, 31).validFrom(Year.of(1990)).inSubdivision("sn").and()
      .hasRelativeToFixedHoliday("REPENTANCE_PRAYER", WEDNESDAY, BEFORE, MonthDay.of(NOVEMBER, 23)).validFrom(Year.of(1995)).inSubdivision("sn").and()
      .hasChristianHoliday("CORPUS_CHRISTI").inSubdivision("sn", "rk").and()

      /* Saxony-Anhalt */
      .hasFixedHoliday("EPIPHANY", JANUARY, 6).validFrom(Year.of(1993)).inSubdivision("st").and()
      .hasFixedHoliday("REFORMATION_DAY", OCTOBER, 31).validFrom(Year.of(1990)).inSubdivision("st").and()

      /* Thuringia */
      .hasFixedHoliday("REFORMATION_DAY", OCTOBER, 31).validFrom(Year.of(1990)).inSubdivision("th").and()
      .hasFixedHoliday("CHILDRENS_DAY", SEPTEMBER, 20).validFrom(Year.of(2019)).inSubdivision("th").and()
      .hasChristianHoliday("CORPUS_CHRISTI").inSubdivision("th", "rk").and()

      /* Schleswig-Holstein */
      .hasFixedHoliday("REFORMATION_DAY", OCTOBER, 31).validFrom(Year.of(2018)).inSubdivision("sh").and()
      .hasRelativeToFixedHoliday("REPENTANCE_PRAYER", WEDNESDAY, BEFORE, MonthDay.of(NOVEMBER, 23)).validBetween(Year.of(1945), Year.of(1989)).inSubdivision("sh").and()

      /* Hamburg */
      .hasFixedHoliday("REFORMATION_DAY", OCTOBER, 31).validFrom(Year.of(2018)).inSubdivision("hh").and()
      .hasRelativeToFixedHoliday("REPENTANCE_PRAYER", WEDNESDAY, BEFORE, MonthDay.of(NOVEMBER, 23)).validBetween(Year.of(1945), Year.of(1989)).inSubdivision("hh").and()

      /* Bremen */
      .hasFixedHoliday("REFORMATION_DAY", OCTOBER, 31).validFrom(Year.of(2018)).inSubdivision("hb").and()
      .hasRelativeToFixedHoliday("REPENTANCE_PRAYER", WEDNESDAY, BEFORE, MonthDay.of(NOVEMBER, 23)).validBetween(Year.of(1945), Year.of(1989)).inSubdivision("hb").and()

      /* Lower-Saxony */
      .hasFixedHoliday("REFORMATION_DAY", OCTOBER, 31).validFrom(Year.of(2018)).inSubdivision("ni").and()
      .hasRelativeToFixedHoliday("REPENTANCE_PRAYER", WEDNESDAY, BEFORE, MonthDay.of(NOVEMBER, 23)).validBetween(Year.of(1945), Year.of(1989)).inSubdivision("ni")

      .check();
  }
}
