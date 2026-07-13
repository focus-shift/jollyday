package de.focus_shift.jollyday.tests.country;

import org.junit.jupiter.api.Test;

import java.time.Year;

import static de.focus_shift.jollyday.core.HolidayCalendar.UNITED_STATES;
import static de.focus_shift.jollyday.core.HolidayType.OBSERVANCE;
import static de.focus_shift.jollyday.core.spi.Occurrence.FIRST;
import static de.focus_shift.jollyday.core.spi.Occurrence.FOURTH;
import static de.focus_shift.jollyday.core.spi.Occurrence.LAST;
import static de.focus_shift.jollyday.core.spi.Occurrence.SECOND;
import static de.focus_shift.jollyday.core.spi.Occurrence.THIRD;
import static de.focus_shift.jollyday.core.spi.Relation.AFTER;
import static de.focus_shift.jollyday.core.spi.Relation.BEFORE;
import static de.focus_shift.jollyday.tests.CalendarCheckerApi.assertFor;
import static de.focus_shift.jollyday.tests.CalendarChecker.Adjuster.PREVIOUS;
import static java.time.DayOfWeek.FRIDAY;
import static java.time.DayOfWeek.MONDAY;
import static java.time.DayOfWeek.SATURDAY;
import static java.time.DayOfWeek.SUNDAY;
import static java.time.DayOfWeek.THURSDAY;
import static java.time.DayOfWeek.TUESDAY;
import static java.time.Month.APRIL;
import static java.time.Month.AUGUST;
import static java.time.Month.DECEMBER;
import static java.time.Month.FEBRUARY;
import static java.time.Month.JANUARY;
import static java.time.Month.JULY;
import static java.time.Month.JUNE;
import static java.time.Month.MARCH;
import static java.time.Month.MAY;
import static java.time.Month.NOVEMBER;
import static java.time.Month.OCTOBER;
import static java.time.Month.SEPTEMBER;

class HolidayUSTest {

  @Test
  void ensuresFederalFixedHolidays() {

    assertFor(UNITED_STATES)
      .hasFixedHoliday("NEW_YEAR", JANUARY, 1)
        .canBeMovedFrom(SATURDAY, PREVIOUS, FRIDAY)
        .canBeMovedFrom(SUNDAY, MONDAY)
      .and()
      .hasFixedHoliday("MEMORIAL_DAY", MAY, 30)
        .validBetween(Year.of(1869), Year.of(1967))
      .and()
      .hasFixedHoliday("JUNETEENTH", JUNE, 19)
        .validFrom(Year.of(2021))
        .canBeMovedFrom(SATURDAY, PREVIOUS, FRIDAY)
        .canBeMovedFrom(SUNDAY, MONDAY)
      .and()
      .hasFixedHoliday("INDEPENDENCE_DAY", JULY, 4)
        .validFrom(Year.of(1776))
        .canBeMovedFrom(SATURDAY, PREVIOUS, FRIDAY)
        .canBeMovedFrom(SUNDAY, MONDAY)
      .and()
      .hasFixedHoliday("VETERANS_DAY", NOVEMBER, 11)
        .validFrom(Year.of(1938))
        .canBeMovedFrom(SATURDAY, PREVIOUS, FRIDAY)
        .canBeMovedFrom(SUNDAY, MONDAY)
      .and()
      .hasFixedHoliday("CHRISTMAS", DECEMBER, 25)
        .canBeMovedFrom(SATURDAY, PREVIOUS, FRIDAY)
        .canBeMovedFrom(SUNDAY, MONDAY)
      .check();
  }

  @Test
  void ensuresFixedWeekdayHolidays() {

    assertFor(UNITED_STATES)
      .hasFixedWeekdayHoliday("MARTIN_LUTHER_KING", THIRD, MONDAY, JANUARY)
        .validFrom(Year.of(1986))
      .and()
      .hasFixedWeekdayHoliday("PRESIDENTS_DAY", THIRD, MONDAY, FEBRUARY)
        .validFrom(Year.of(1971))
      .and()
      .hasFixedWeekdayHoliday("MEMORIAL_DAY", LAST, MONDAY, MAY)
        .validFrom(Year.of(1968))
      .and()
      .hasFixedWeekdayHoliday("LABOUR_DAY", FIRST, MONDAY, SEPTEMBER)
        .validFrom(Year.of(1895))
      .and()
      .hasFixedWeekdayHoliday("THANKSGIVING", FOURTH, THURSDAY, NOVEMBER)
        .validFrom(Year.of(1863))
      .check();
  }

  @Test
  void ensuresAlabamaHolidays() {

    assertFor(UNITED_STATES)
      .hasFixedWeekdayHoliday("WASHINGTONS_BIRTHDAY", THIRD, MONDAY, FEBRUARY)
        .validFrom(Year.of(1968))
        .inSubdivision("al")
      .and()
      .hasFixedWeekdayHoliday("CONFEDERATE", FOURTH, MONDAY, APRIL, OBSERVANCE)
        .inSubdivision("al")
      .and()
      .hasFixedWeekdayHoliday("JEFFERSON_DAVIS", FIRST, MONDAY, JUNE, OBSERVANCE)
        .inSubdivision("al")
      .and()
      .hasFixedWeekdayHoliday("COLUMBUS_DAY", SECOND, MONDAY, OCTOBER)
        .validFrom(Year.of(1934))
        .inSubdivision("al")
      .and()
      .hasFixedWeekdayHoliday("INDIGENOUS_PEOPLES_DAY", SECOND, MONDAY, OCTOBER)
        .validFrom(Year.of(2019))
        .inSubdivision("al")
      .check();
  }

  @Test
  void ensuresAlaskaHolidays() {

    assertFor(UNITED_STATES)
      .hasFixedHoliday("ALASKA", OCTOBER, 18, OBSERVANCE)
        .inSubdivision("ak")
      .and()
      .hasFixedWeekdayHoliday("SEWARD", LAST, MONDAY, MARCH, OBSERVANCE)
        .inSubdivision("ak")
      .and()
      .hasFixedWeekdayHoliday("INDIGENOUS_PEOPLES_DAY", SECOND, MONDAY, OCTOBER)
        .validFrom(Year.of(2015))
        .inSubdivision("ak")
      .check();
  }

  @Test
  void ensuresArizonaHolidays() {

    assertFor(UNITED_STATES)
      .hasFixedHoliday("WASHINGTONS_BIRTHDAY", FEBRUARY, 22)
        .validBetween(Year.of(1879), Year.of(1967))
        .inSubdivision("az")
      .and()
      .hasFixedWeekdayHoliday("WASHINGTONS_BIRTHDAY", THIRD, MONDAY, FEBRUARY)
        .validFrom(Year.of(1968))
        .inSubdivision("az")
      .and()
      .hasFixedWeekdayHoliday("COLUMBUS_DAY", SECOND, MONDAY, OCTOBER)
        .validFrom(Year.of(1934))
        .inSubdivision("az")
      .check();
  }

  @Test
  void ensuresArkansasHolidays() {

    assertFor(UNITED_STATES)
      .hasFixedHoliday("CHRISTMAS_EVE", DECEMBER, 24, OBSERVANCE)
        .inSubdivision("ar")
      .and()
      .hasFixedHoliday("WASHINGTONS_BIRTHDAY", FEBRUARY, 22)
        .validBetween(Year.of(1879), Year.of(1967))
        .inSubdivision("ar")
      .and()
      .hasFixedWeekdayHoliday("WASHINGTONS_BIRTHDAY", THIRD, MONDAY, FEBRUARY)
        .validFrom(Year.of(1968))
        .inSubdivision("ar")
      .check();
  }

  @Test
  void ensuresCaliforniaHolidays() {

    assertFor(UNITED_STATES)
      .hasFixedHoliday("LINCOLN", FEBRUARY, 12, OBSERVANCE)
        .canBeMovedFrom(SUNDAY, MONDAY)
        .inSubdivision("ca")
      .and()
      .hasFixedHoliday("CESAR_CHAVEZ", MARCH, 31)
        .canBeMovedFrom(SUNDAY, MONDAY)
        .inSubdivision("ca")
      .and()
      .hasFixedHoliday("WASHINGTONS_BIRTHDAY", FEBRUARY, 22)
        .validBetween(Year.of(1879), Year.of(1967))
        .inSubdivision("ca")
      .and()
      .hasFixedWeekdayHoliday("WASHINGTONS_BIRTHDAY", THIRD, MONDAY, FEBRUARY)
        .validFrom(Year.of(1968))
        .inSubdivision("ca")
      .and()
      .hasFixedWeekdayHoliday("INDIGENOUS_PEOPLES_DAY", SECOND, MONDAY, OCTOBER)
        .validFrom(Year.of(2019))
        .inSubdivision("ca")
      .and()
      .hasFixedWeekdayHoliday("DAY_AFTER_THANKSGIVING", FOURTH, FRIDAY, NOVEMBER)
        .inSubdivision("ca")
      .check();
  }

  @Test
  void ensuresColoradoHolidays() {

    assertFor(UNITED_STATES)
      .hasFixedHoliday("WASHINGTONS_BIRTHDAY", FEBRUARY, 22)
        .validBetween(Year.of(1879), Year.of(1967))
        .inSubdivision("co")
      .and()
      .hasFixedWeekdayHoliday("WASHINGTONS_BIRTHDAY", THIRD, MONDAY, FEBRUARY)
        .validFrom(Year.of(1968))
        .inSubdivision("co")
      .and()
      .hasFixedWeekdayHoliday("CABRINI_DAY", FIRST, MONDAY, OCTOBER)
        .validFrom(Year.of(2020))
        .inSubdivision("co")
      .and()
      .hasFixedWeekdayHoliday("COLUMBUS_DAY", SECOND, MONDAY, OCTOBER)
        .validBetween(Year.of(1906), Year.of(2019))
        .inSubdivision("co")
      .check();
  }

  @Test
  void ensuresConnecticutHolidays() {

    assertFor(UNITED_STATES)
      .hasFixedHoliday("LINCOLN", FEBRUARY, 12)
        .canBeMovedFrom(SATURDAY, PREVIOUS, FRIDAY)
        .canBeMovedFrom(SUNDAY, MONDAY)
        .inSubdivision("ct")
      .and()
      .hasFixedHoliday("WASHINGTONS_BIRTHDAY", FEBRUARY, 22)
        .validBetween(Year.of(1879), Year.of(1967))
        .inSubdivision("ct")
      .and()
      .hasFixedWeekdayHoliday("WASHINGTONS_BIRTHDAY", THIRD, MONDAY, FEBRUARY)
        .validFrom(Year.of(1968))
        .inSubdivision("ct")
      .and()
      .hasFixedWeekdayHoliday("COLUMBUS_DAY", SECOND, MONDAY, OCTOBER)
        .validFrom(Year.of(1934))
        .inSubdivision("ct")
      .and()
      .hasChristianHoliday("GOOD_FRIDAY")
        .inSubdivision("ct")
      .check();
  }

  @Test
  void ensuresDelawareHolidays() {

    assertFor(UNITED_STATES)
      .hasFixedHoliday("WASHINGTONS_BIRTHDAY", FEBRUARY, 22)
        .validBetween(Year.of(1879), Year.of(1967))
        .inSubdivision("de")
      .and()
      .hasFixedWeekdayHoliday("WASHINGTONS_BIRTHDAY", THIRD, MONDAY, FEBRUARY)
        .validFrom(Year.of(1968))
        .inSubdivision("de")
      .and()
      .hasFixedWeekdayHoliday("DAY_AFTER_THANKSGIVING", FOURTH, FRIDAY, NOVEMBER)
        .inSubdivision("de")
      .and()
      .hasChristianHoliday("GOOD_FRIDAY")
        .inSubdivision("de")
      .check();
  }

  @Test
  void ensuresFloridaHolidays() {

    assertFor(UNITED_STATES)
      .hasFixedHoliday("WASHINGTONS_BIRTHDAY", FEBRUARY, 22)
        .validBetween(Year.of(1879), Year.of(1967))
        .inSubdivision("fl")
      .and()
      .hasFixedWeekdayHoliday("WASHINGTONS_BIRTHDAY", THIRD, MONDAY, FEBRUARY)
        .validFrom(Year.of(1968))
        .inSubdivision("fl")
      .and()
      .hasFixedWeekdayHoliday("CONFEDERATE", FOURTH, MONDAY, APRIL, OBSERVANCE)
        .inSubdivision("fl")
      .and()
      .hasFixedWeekdayHoliday("DAY_AFTER_THANKSGIVING", FOURTH, FRIDAY, NOVEMBER)
        .inSubdivision("fl")
      .check();
  }

  @Test
  void ensuresGeorgiaHolidays() {

    assertFor(UNITED_STATES)
      .hasFixedHoliday("WASHINGTONS_BIRTHDAY", FEBRUARY, 22)
        .validBetween(Year.of(1879), Year.of(1967))
        .inSubdivision("ga")
      .and()
      .hasFixedWeekdayHoliday("WASHINGTONS_BIRTHDAY", THIRD, MONDAY, FEBRUARY)
        .validFrom(Year.of(1968))
        .inSubdivision("ga")
      .and()
      .hasFixedWeekdayHoliday("COLUMBUS_DAY", SECOND, MONDAY, OCTOBER)
        .validFrom(Year.of(1934))
        .inSubdivision("ga")
      .and()
      .hasFixedWeekdayHoliday("CONFEDERATE", FOURTH, MONDAY, APRIL)
        .validBetween(Year.of(1874), Year.of(2016))
        .inSubdivision("ga")
      .and()
      .hasFixedWeekdayHoliday("DAY_AFTER_THANKSGIVING", FOURTH, FRIDAY, NOVEMBER)
        .inSubdivision("ga")
      .check();
  }

  @Test
  void ensuresHawaiiHolidays() {

    assertFor(UNITED_STATES)
      .hasFixedHoliday("KALANIANAOLE", MARCH, 26, OBSERVANCE)
        .inSubdivision("hi")
      .and()
      .hasFixedHoliday("KAMEHAMEHA", JUNE, 11, OBSERVANCE)
        .inSubdivision("hi")
      .and()
      .hasFixedHoliday("WASHINGTONS_BIRTHDAY", FEBRUARY, 22)
        .validBetween(Year.of(1879), Year.of(1967))
        .inSubdivision("hi")
      .and()
      .hasFixedWeekdayHoliday("WASHINGTONS_BIRTHDAY", THIRD, MONDAY, FEBRUARY)
        .validFrom(Year.of(1968))
        .inSubdivision("hi")
      .and()
      .hasFixedWeekdayHoliday("STATEHOOD", THIRD, FRIDAY, AUGUST, OBSERVANCE)
        .inSubdivision("hi")
      .and()
      .hasFixedWeekdayHoliday("INDIGENOUS_PEOPLES_DAY", SECOND, MONDAY, OCTOBER)
        .validFrom(Year.of(1988))
        .inSubdivision("hi")
      .and()
      .hasChristianHoliday("GOOD_FRIDAY", OBSERVANCE)
        .inSubdivision("hi")
      .check();
  }

  @Test
  void ensuresIdahoHolidays() {

    assertFor(UNITED_STATES)
      .hasFixedHoliday("WASHINGTONS_BIRTHDAY", FEBRUARY, 22)
        .validBetween(Year.of(1879), Year.of(1967))
        .inSubdivision("id")
      .and()
      .hasFixedWeekdayHoliday("WASHINGTONS_BIRTHDAY", THIRD, MONDAY, FEBRUARY)
        .validFrom(Year.of(1968))
        .inSubdivision("id")
      .and()
      .hasFixedWeekdayHoliday("COLUMBUS_DAY", SECOND, MONDAY, OCTOBER)
        .validFrom(Year.of(1934))
        .inSubdivision("id")
      .check();
  }

  @Test
  void ensuresIllinoisHolidays() {

    assertFor(UNITED_STATES)
      .hasFixedHoliday("LINCOLN", FEBRUARY, 12, OBSERVANCE)
        .inSubdivision("il")
      .and()
      .hasFixedHoliday("WASHINGTONS_BIRTHDAY", FEBRUARY, 22)
        .validBetween(Year.of(1879), Year.of(1967))
        .inSubdivision("il")
      .and()
      .hasFixedWeekdayHoliday("WASHINGTONS_BIRTHDAY", THIRD, MONDAY, FEBRUARY)
        .validFrom(Year.of(1968))
        .inSubdivision("il")
      .and()
      .hasFixedWeekdayHoliday("COLUMBUS_DAY", SECOND, MONDAY, OCTOBER)
        .validFrom(Year.of(1934))
        .inSubdivision("il")
      .and()
      .hasFixedWeekdayHoliday("PULASKI", FIRST, MONDAY, MARCH, OBSERVANCE)
        .inSubdivision("il")
      .check();
  }

  @Test
  void ensuresIndianaHolidays() {

    assertFor(UNITED_STATES)
      .hasFixedHoliday("CHRISTMAS_EVE", DECEMBER, 24, OBSERVANCE)
        .inSubdivision("in")
      .and()
      .hasFixedWeekdayHoliday("COLUMBUS_DAY", SECOND, MONDAY, OCTOBER)
        .validFrom(Year.of(1934))
        .inSubdivision("in")
      .and()
      .hasChristianHoliday("GOOD_FRIDAY", OBSERVANCE)
        .inSubdivision("in")
      .and()
      .hasChristianHoliday("EASTER_MONDAY", OBSERVANCE)
        .inSubdivision("in")
      .check();
  }

  @Test
  void ensuresIowaHolidays() {

    assertFor(UNITED_STATES)
      .hasFixedHoliday("CHRISTMAS_EVE", DECEMBER, 24, OBSERVANCE)
        .inSubdivision("ia")
      .and()
      .hasFixedHoliday("NEW_YEARS_EVE", DECEMBER, 31, OBSERVANCE)
        .inSubdivision("ia")
      .and()
      .hasFixedWeekdayHoliday("COLUMBUS_DAY", SECOND, MONDAY, OCTOBER)
        .validFrom(Year.of(1934))
        .inSubdivision("ia")
      .and()
      .hasFixedWeekdayHoliday("INDIGENOUS_PEOPLES_DAY", SECOND, MONDAY, OCTOBER)
        .validFrom(Year.of(2019))
        .inSubdivision("ia")
      .and()
      .hasFixedWeekdayHoliday("DAY_AFTER_THANKSGIVING", FOURTH, FRIDAY, NOVEMBER)
        .inSubdivision("ia")
      .check();
  }

  @Test
  void ensuresKansasHolidays() {

    assertFor(UNITED_STATES)
      .hasFixedWeekdayHoliday("COLUMBUS_DAY", SECOND, MONDAY, OCTOBER)
        .validFrom(Year.of(1934))
        .inSubdivision("ks")
      .and()
      .hasFixedWeekdayHoliday("DAY_AFTER_THANKSGIVING", FOURTH, FRIDAY, NOVEMBER)
        .inSubdivision("ks")
      .check();
  }

  @Test
  void ensuresKentuckyHolidays() {

    assertFor(UNITED_STATES)
      .hasFixedHoliday("CHRISTMAS_EVE", DECEMBER, 24, OBSERVANCE)
        .inSubdivision("ky")
      .and()
      .hasFixedHoliday("NEW_YEARS_EVE", DECEMBER, 31, OBSERVANCE)
        .inSubdivision("ky")
      .and()
      .hasFixedWeekdayHoliday("COLUMBUS_DAY", SECOND, MONDAY, OCTOBER)
        .validFrom(Year.of(1934))
        .inSubdivision("ky")
      .and()
      .hasFixedWeekdayHoliday("DAY_AFTER_THANKSGIVING", FOURTH, FRIDAY, NOVEMBER)
        .inSubdivision("ky")
      .and()
      .hasChristianHoliday("GOOD_FRIDAY", OBSERVANCE)
        .inSubdivision("ky")
      .check();
  }

  @Test
  void ensuresLouisianaHolidays() {

    assertFor(UNITED_STATES)
      .hasFixedHoliday("CONFEDERATE", APRIL, 26)
        .validBetween(Year.of(1925), Year.of(2022))
        .inSubdivision("la")
      .and()
      .hasFixedWeekdayHoliday("COLUMBUS_DAY", SECOND, MONDAY, OCTOBER)
        .validFrom(Year.of(1934))
        .inSubdivision("la")
      .and()
      .hasFixedWeekdayHoliday("INDIGENOUS_PEOPLES_DAY", SECOND, MONDAY, OCTOBER)
        .validFrom(Year.of(2019))
        .inSubdivision("la")
      .and()
      .hasChristianHoliday("GOOD_FRIDAY", OBSERVANCE)
        .inSubdivision("la")
      .and()
      .hasChristianHoliday("MARDI_GRAS", OBSERVANCE)
        .inSubdivision("la")
      .check();
  }

  @Test
  void ensuresMaineHolidays() {

    assertFor(UNITED_STATES)
      .hasFixedHoliday("WASHINGTONS_BIRTHDAY", FEBRUARY, 22)
        .validBetween(Year.of(1879), Year.of(1967))
        .inSubdivision("me")
      .and()
      .hasFixedWeekdayHoliday("WASHINGTONS_BIRTHDAY", THIRD, MONDAY, FEBRUARY)
        .validFrom(Year.of(1968))
        .inSubdivision("me")
      .and()
      .hasFixedWeekdayHoliday("COLUMBUS_DAY", SECOND, MONDAY, OCTOBER)
        .validFrom(Year.of(1934))
        .inSubdivision("me")
      .and()
      .hasFixedWeekdayHoliday("INDIGENOUS_PEOPLES_DAY", SECOND, MONDAY, OCTOBER)
        .validFrom(Year.of(2019))
        .inSubdivision("me")
      .and()
      .hasFixedWeekdayHoliday("DAY_AFTER_THANKSGIVING", FOURTH, FRIDAY, NOVEMBER)
        .inSubdivision("me")
      .check();
  }

  @Test
  void ensuresMarylandHolidays() {

    assertFor(UNITED_STATES)
      .hasFixedHoliday("WASHINGTONS_BIRTHDAY", FEBRUARY, 22)
        .validBetween(Year.of(1879), Year.of(1967))
        .inSubdivision("md")
      .and()
      .hasFixedWeekdayHoliday("WASHINGTONS_BIRTHDAY", THIRD, MONDAY, FEBRUARY)
        .validFrom(Year.of(1968))
        .inSubdivision("md")
      .and()
      .hasFixedWeekdayHoliday("COLUMBUS_DAY", SECOND, MONDAY, OCTOBER)
        .validFrom(Year.of(1934))
        .inSubdivision("md")
      .and()
      .hasFixedWeekdayHoliday("AMERICAN_INDIAN_HERITAGE_DAY", FOURTH, FRIDAY, NOVEMBER)
        .inSubdivision("md")
      .and()
      .hasRelativeToWeekdayInMonthHoliday("SERVICE_REDUCTION", FRIDAY, BEFORE, LAST, MONDAY, MAY, OBSERVANCE)
        .inSubdivision("md")
      .check();
  }

  @Test
  void ensuresMassachusettsHolidays() {

    assertFor(UNITED_STATES)
      .hasFixedHoliday("WASHINGTONS_BIRTHDAY", FEBRUARY, 22)
        .validBetween(Year.of(1879), Year.of(1967))
        .inSubdivision("ma")
      .and()
      .hasFixedWeekdayHoliday("WASHINGTONS_BIRTHDAY", THIRD, MONDAY, FEBRUARY)
        .validFrom(Year.of(1968))
        .inSubdivision("ma")
      .and()
      .hasFixedWeekdayHoliday("PATRIOT", THIRD, MONDAY, APRIL, OBSERVANCE)
        .inSubdivision("ma")
      .and()
      .hasFixedWeekdayHoliday("COLUMBUS_DAY", SECOND, MONDAY, OCTOBER)
        .validFrom(Year.of(1934))
        .inSubdivision("ma")
      .and()
      .hasFixedHoliday("EVACUATION", MARCH, 17)
        .inSubdivision("ma", "sc")
      .and()
      .hasFixedHoliday("BUNKER_HILL", JUNE, 17)
        .inSubdivision("ma", "sc")
      .and()
      .hasFixedHoliday("EVACUATION", MARCH, 17)
        .inSubdivision("ma", "ca")
      .check();
  }

  @Test
  void ensuresMichiganHolidays() {

    assertFor(UNITED_STATES)
      .hasFixedHoliday("CHRISTMAS_EVE", DECEMBER, 24)
        .inSubdivision("mi")
      .and()
      .hasFixedHoliday("NEW_YEARS_EVE", DECEMBER, 31)
        .inSubdivision("mi")
      .and()
      .hasFixedHoliday("WASHINGTONS_BIRTHDAY", FEBRUARY, 22)
        .validBetween(Year.of(1879), Year.of(1967))
        .inSubdivision("mi")
      .and()
      .hasFixedWeekdayHoliday("WASHINGTONS_BIRTHDAY", THIRD, MONDAY, FEBRUARY)
        .validFrom(Year.of(1968))
        .inSubdivision("mi")
      .and()
      .hasFixedWeekdayHoliday("INDIGENOUS_PEOPLES_DAY", SECOND, MONDAY, OCTOBER)
        .validFrom(Year.of(2019))
        .inSubdivision("mi")
      .and()
      .hasFixedWeekdayHoliday("DAY_AFTER_THANKSGIVING", FOURTH, FRIDAY, NOVEMBER)
        .inSubdivision("mi")
      .and()
      .hasChristianHoliday("EASTER_MONDAY", OBSERVANCE)
        .inSubdivision("mi")
      .check();
  }

  @Test
  void ensuresMinnesotaHolidays() {

    assertFor(UNITED_STATES)
      .hasFixedHoliday("WASHINGTONS_BIRTHDAY", FEBRUARY, 22)
        .validBetween(Year.of(1879), Year.of(1967))
        .inSubdivision("mn")
      .and()
      .hasFixedWeekdayHoliday("WASHINGTONS_BIRTHDAY", THIRD, MONDAY, FEBRUARY)
        .validFrom(Year.of(1968))
        .inSubdivision("mn")
      .and()
      .hasFixedWeekdayHoliday("INDIGENOUS_PEOPLES_DAY", SECOND, MONDAY, OCTOBER)
        .validFrom(Year.of(2016))
        .inSubdivision("mn")
      .and()
      .hasFixedWeekdayHoliday("DAY_AFTER_THANKSGIVING", FOURTH, FRIDAY, NOVEMBER)
        .inSubdivision("mn")
      .check();
  }

  @Test
  void ensuresMississippiHolidays() {

    assertFor(UNITED_STATES)
      .hasFixedHoliday("WASHINGTONS_BIRTHDAY", FEBRUARY, 22)
        .validBetween(Year.of(1879), Year.of(1967))
        .inSubdivision("ms")
      .and()
      .hasFixedWeekdayHoliday("WASHINGTONS_BIRTHDAY", THIRD, MONDAY, FEBRUARY)
        .validFrom(Year.of(1968))
        .inSubdivision("ms")
      .and()
      .hasFixedWeekdayHoliday("CONFEDERATE", FOURTH, MONDAY, APRIL)
        .inSubdivision("ms")
      .and()
      .hasFixedWeekdayHoliday("COLUMBUS_DAY", SECOND, MONDAY, OCTOBER)
        .validFrom(Year.of(1934))
        .inSubdivision("ms")
      .check();
  }

  @Test
  void ensuresMissouriHolidays() {

    assertFor(UNITED_STATES)
      .hasFixedHoliday("LINCOLN", FEBRUARY, 12, OBSERVANCE)
        .inSubdivision("mo")
      .and()
      .hasFixedHoliday("TRUMAN", MAY, 8, OBSERVANCE)
        .inSubdivision("mo")
      .and()
      .hasFixedHoliday("WASHINGTONS_BIRTHDAY", FEBRUARY, 22)
        .validBetween(Year.of(1879), Year.of(1967))
        .inSubdivision("mo")
      .and()
      .hasFixedWeekdayHoliday("WASHINGTONS_BIRTHDAY", THIRD, MONDAY, FEBRUARY)
        .validFrom(Year.of(1968))
        .inSubdivision("mo")
      .and()
      .hasFixedWeekdayHoliday("COLUMBUS_DAY", SECOND, MONDAY, OCTOBER)
        .validFrom(Year.of(1934))
        .inSubdivision("mo")
      .check();
  }

  @Test
  void ensuresMontanaHolidays() {

    assertFor(UNITED_STATES)
      .hasFixedHoliday("WASHINGTONS_BIRTHDAY", FEBRUARY, 22)
        .validBetween(Year.of(1879), Year.of(1967))
        .inSubdivision("mt")
      .and()
      .hasFixedWeekdayHoliday("WASHINGTONS_BIRTHDAY", THIRD, MONDAY, FEBRUARY)
        .validFrom(Year.of(1968))
        .inSubdivision("mt")
      .and()
      .hasFixedWeekdayHoliday("COLUMBUS_DAY", SECOND, MONDAY, OCTOBER)
        .validFrom(Year.of(1934))
        .inSubdivision("mt")
      .and()
      .hasRelativeToWeekdayInMonthHoliday("THANKSGIVING", TUESDAY, AFTER, FIRST, MONDAY, NOVEMBER, OBSERVANCE)
        .inSubdivision("mt")
      .check();
  }

  @Test
  void ensuresNebraskaHolidays() {

    assertFor(UNITED_STATES)
      .hasFixedHoliday("WASHINGTONS_BIRTHDAY", FEBRUARY, 22)
        .validBetween(Year.of(1879), Year.of(1967))
        .inSubdivision("ne")
      .and()
      .hasFixedWeekdayHoliday("WASHINGTONS_BIRTHDAY", THIRD, MONDAY, FEBRUARY)
        .validFrom(Year.of(1968))
        .inSubdivision("ne")
      .and()
      .hasFixedWeekdayHoliday("ARBOR", LAST, FRIDAY, APRIL)
        .inSubdivision("ne")
      .and()
      .hasFixedWeekdayHoliday("COLUMBUS_DAY", SECOND, MONDAY, OCTOBER)
        .validFrom(Year.of(1934))
        .inSubdivision("ne")
      .and()
      .hasFixedWeekdayHoliday("INDIGENOUS_PEOPLES_DAY", SECOND, MONDAY, OCTOBER)
        .validFrom(Year.of(2020))
        .inSubdivision("ne")
      .and()
      .hasFixedWeekdayHoliday("DAY_AFTER_THANKSGIVING", FOURTH, FRIDAY, NOVEMBER)
        .inSubdivision("ne")
      .check();
  }

  @Test
  void ensuresNevadaHolidays() {

    assertFor(UNITED_STATES)
      .hasFixedHoliday("WASHINGTONS_BIRTHDAY", FEBRUARY, 22)
        .validBetween(Year.of(1879), Year.of(1967))
        .inSubdivision("nv")
      .and()
      .hasFixedWeekdayHoliday("WASHINGTONS_BIRTHDAY", THIRD, MONDAY, FEBRUARY)
        .validFrom(Year.of(1968))
        .inSubdivision("nv")
      .and()
      .hasFixedWeekdayHoliday("NEVADA", LAST, FRIDAY, OCTOBER, OBSERVANCE)
        .inSubdivision("nv")
      .and()
      .hasFixedWeekdayHoliday("DAY_AFTER_THANKSGIVING", FOURTH, FRIDAY, NOVEMBER)
        .inSubdivision("nv")
      .check();
  }

  @Test
  void ensuresNewHampshireHolidays() {

    assertFor(UNITED_STATES)
      .hasFixedHoliday("WASHINGTONS_BIRTHDAY", FEBRUARY, 22)
        .validBetween(Year.of(1879), Year.of(1967))
        .inSubdivision("nh")
      .and()
      .hasFixedWeekdayHoliday("WASHINGTONS_BIRTHDAY", THIRD, MONDAY, FEBRUARY)
        .validFrom(Year.of(1968))
        .inSubdivision("nh")
      .and()
      .hasFixedWeekdayHoliday("COLUMBUS_DAY", SECOND, MONDAY, OCTOBER)
        .validFrom(Year.of(1934))
        .inSubdivision("nh")
      .and()
      .hasFixedWeekdayHoliday("DAY_AFTER_THANKSGIVING", FOURTH, FRIDAY, NOVEMBER)
        .inSubdivision("nh")
      .check();
  }

  @Test
  void ensuresNewJerseyHolidays() {

    assertFor(UNITED_STATES)
      .hasFixedHoliday("LINCOLN", FEBRUARY, 12, OBSERVANCE)
        .inSubdivision("nj")
      .and()
      .hasFixedHoliday("WASHINGTONS_BIRTHDAY", FEBRUARY, 22)
        .validBetween(Year.of(1879), Year.of(1967))
        .inSubdivision("nj")
      .and()
      .hasFixedWeekdayHoliday("WASHINGTONS_BIRTHDAY", THIRD, MONDAY, FEBRUARY)
        .validFrom(Year.of(1968))
        .inSubdivision("nj")
      .and()
      .hasFixedWeekdayHoliday("COLUMBUS_DAY", SECOND, MONDAY, OCTOBER)
        .validFrom(Year.of(1934))
        .inSubdivision("nj")
      .and()
      .hasChristianHoliday("GOOD_FRIDAY", OBSERVANCE)
        .inSubdivision("nj")
      .check();
  }

  @Test
  void ensuresNewMexicoHolidays() {

    assertFor(UNITED_STATES)
      .hasFixedWeekdayHoliday("COLUMBUS_DAY", SECOND, MONDAY, OCTOBER)
        .validFrom(Year.of(1934))
        .inSubdivision("nm")
      .and()
      .hasFixedWeekdayHoliday("INDIGENOUS_PEOPLES_DAY", SECOND, MONDAY, OCTOBER)
        .validFrom(Year.of(2019))
        .inSubdivision("nm")
      .and()
      .hasFixedWeekdayHoliday("DAY_AFTER_THANKSGIVING", FOURTH, FRIDAY, NOVEMBER)
        .inSubdivision("nm")
      .check();
  }

  @Test
  void ensuresNewYorkHolidays() {

    assertFor(UNITED_STATES)
      .hasFixedHoliday("WASHINGTONS_BIRTHDAY", FEBRUARY, 22)
        .validBetween(Year.of(1879), Year.of(1967))
        .inSubdivision("ny")
      .and()
      .hasFixedHoliday("LINCOLN", FEBRUARY, 12, OBSERVANCE)
        .inSubdivision("ny")
      .and()
      .hasFixedWeekdayHoliday("WASHINGTONS_BIRTHDAY", THIRD, MONDAY, FEBRUARY)
        .validFrom(Year.of(1968))
        .inSubdivision("ny")
      .and()
      .hasFixedWeekdayHoliday("COLUMBUS_DAY", SECOND, MONDAY, OCTOBER)
        .validFrom(Year.of(1934))
        .inSubdivision("ny")
      .and()
      .hasChristianHoliday("EASTER_MONDAY", OBSERVANCE)
        .inSubdivision("ny")
      .and()
      .hasFixedWeekdayHoliday("BROOKLY_QUEENS", FIRST, THURSDAY, JUNE)
        .inSubdivision("ny", "nyc")
      .check();
  }

  @Test
  void ensuresNorthCarolinaHolidays() {

    assertFor(UNITED_STATES)
      .hasFixedHoliday("CHRISTMAS_EVE", DECEMBER, 24)
        .inSubdivision("nc")
      .and()
      .hasFixedWeekdayHoliday("CONFEDERATE", FOURTH, MONDAY, APRIL, OBSERVANCE)
        .inSubdivision("nc")
      .and()
      .hasFixedWeekdayHoliday("INDIGENOUS_PEOPLES_DAY", SECOND, MONDAY, OCTOBER)
        .validFrom(Year.of(2018))
        .inSubdivision("nc")
      .and()
      .hasFixedWeekdayHoliday("DAY_AFTER_THANKSGIVING", FOURTH, FRIDAY, NOVEMBER)
        .inSubdivision("nc")
      .and()
      .hasChristianHoliday("GOOD_FRIDAY", OBSERVANCE)
        .inSubdivision("nc")
      .and()
      .hasFixedHoliday("INDEPENDENCE_DAY", MAY, 20)
        .inSubdivision("nc", "ce")
      .and()
      .hasFixedHoliday("INDEPENDENCE_DAY", MAY, 20)
        .inSubdivision("nc", "me")
      .check();
  }

  @Test
  void ensuresNorthDakotaHolidays() {

    assertFor(UNITED_STATES)
      .hasFixedHoliday("WASHINGTONS_BIRTHDAY", FEBRUARY, 22)
        .validBetween(Year.of(1879), Year.of(1967))
        .inSubdivision("nd")
      .and()
      .hasFixedWeekdayHoliday("WASHINGTONS_BIRTHDAY", THIRD, MONDAY, FEBRUARY)
        .validFrom(Year.of(1968))
        .inSubdivision("nd")
      .and()
      .hasChristianHoliday("GOOD_FRIDAY", OBSERVANCE)
        .inSubdivision("nd")
      .and()
      .hasChristianHoliday("EASTER_MONDAY", OBSERVANCE)
        .inSubdivision("nd")
      .check();
  }

  @Test
  void ensuresOhioHolidays() {

    assertFor(UNITED_STATES)
      .hasFixedHoliday("WASHINGTONS_BIRTHDAY", FEBRUARY, 22)
        .validBetween(Year.of(1879), Year.of(1967))
        .inSubdivision("oh")
      .and()
      .hasFixedHoliday("ULYSSES_S_GRANT_DAY", APRIL, 27)
        .validFrom(Year.of(2024))
        .inSubdivision("oh")
      .and()
      .hasFixedWeekdayHoliday("WASHINGTONS_BIRTHDAY", THIRD, MONDAY, FEBRUARY)
        .validFrom(Year.of(1968))
        .inSubdivision("oh")
      .and()
      .hasFixedWeekdayHoliday("COLUMBUS_DAY", SECOND, MONDAY, OCTOBER)
        .validFrom(Year.of(1934))
        .inSubdivision("oh")
      .check();
  }

  @Test
  void ensuresOklahomaHolidays() {

    assertFor(UNITED_STATES)
      .hasFixedHoliday("WASHINGTONS_BIRTHDAY", FEBRUARY, 22)
        .validBetween(Year.of(1879), Year.of(1967))
        .inSubdivision("ok")
      .and()
      .hasFixedHoliday("CHRISTMAS_EVE", DECEMBER, 24, OBSERVANCE)
        .inSubdivision("ok")
      .and()
      .hasFixedWeekdayHoliday("WASHINGTONS_BIRTHDAY", THIRD, MONDAY, FEBRUARY)
        .validFrom(Year.of(1968))
        .inSubdivision("ok")
      .and()
      .hasFixedWeekdayHoliday("COLUMBUS_DAY", SECOND, MONDAY, OCTOBER)
        .validFrom(Year.of(1934))
        .inSubdivision("ok")
      .and()
      .hasFixedWeekdayHoliday("INDIGENOUS_PEOPLES_DAY", SECOND, MONDAY, OCTOBER)
        .validFrom(Year.of(2019))
        .inSubdivision("ok")
      .and()
      .hasFixedWeekdayHoliday("DAY_AFTER_THANKSGIVING", FOURTH, FRIDAY, NOVEMBER)
        .inSubdivision("ok")
      .check();
  }

  @Test
  void ensuresOregonHolidays() {

    assertFor(UNITED_STATES)
      .hasFixedHoliday("WASHINGTONS_BIRTHDAY", FEBRUARY, 22)
        .validBetween(Year.of(1879), Year.of(1967))
        .inSubdivision("or")
      .and()
      .hasFixedWeekdayHoliday("WASHINGTONS_BIRTHDAY", THIRD, MONDAY, FEBRUARY)
        .validFrom(Year.of(1968))
        .inSubdivision("or")
      .and()
      .hasFixedWeekdayHoliday("INDIGENOUS_PEOPLES_DAY", SECOND, MONDAY, OCTOBER)
        .validFrom(Year.of(2021))
        .inSubdivision("or")
      .and()
      .hasFixedWeekdayHoliday("DAY_AFTER_THANKSGIVING", FOURTH, FRIDAY, NOVEMBER)
        .inSubdivision("or")
      .check();
  }

  @Test
  void ensuresPennsylvaniaHolidays() {

    assertFor(UNITED_STATES)
      .hasFixedHoliday("WASHINGTONS_BIRTHDAY", FEBRUARY, 22)
        .validBetween(Year.of(1879), Year.of(1967))
        .inSubdivision("pa")
      .and()
      .hasFixedWeekdayHoliday("WASHINGTONS_BIRTHDAY", THIRD, MONDAY, FEBRUARY)
        .validFrom(Year.of(1968))
        .inSubdivision("pa")
      .and()
      .hasFixedWeekdayHoliday("COLUMBUS_DAY", SECOND, MONDAY, OCTOBER)
        .validFrom(Year.of(1934))
        .inSubdivision("pa")
      .and()
      .hasFixedWeekdayHoliday("DAY_AFTER_THANKSGIVING", FOURTH, FRIDAY, NOVEMBER)
        .inSubdivision("pa")
      .check();
  }

  @Test
  void ensuresRhodeIslandHolidays() {

    assertFor(UNITED_STATES)
      .hasFixedWeekdayHoliday("VICTORY", SECOND, MONDAY, AUGUST, OBSERVANCE)
        .inSubdivision("ri")
      .and()
      .hasFixedWeekdayHoliday("COLUMBUS_DAY", SECOND, MONDAY, OCTOBER)
        .validFrom(Year.of(1934))
        .inSubdivision("ri")
      .check();
  }

  @Test
  void ensuresSouthCarolinaHolidays() {

    assertFor(UNITED_STATES)
      .hasFixedHoliday("CONFEDERATE", MAY, 10, OBSERVANCE)
        .validFrom(Year.of(2000))
        .inSubdivision("sc")
      .and()
      .hasFixedHoliday("WASHINGTONS_BIRTHDAY", FEBRUARY, 22)
        .validBetween(Year.of(1879), Year.of(1967))
        .inSubdivision("sc")
      .and()
      .hasFixedWeekdayHoliday("WASHINGTONS_BIRTHDAY", THIRD, MONDAY, FEBRUARY)
        .validFrom(Year.of(1968))
        .inSubdivision("sc")
      .and()
      .hasFixedWeekdayHoliday("COLUMBUS_DAY", SECOND, MONDAY, OCTOBER)
        .validFrom(Year.of(1934))
        .inSubdivision("sc")
      .and()
      .hasFixedWeekdayHoliday("DAY_AFTER_THANKSGIVING", FOURTH, FRIDAY, NOVEMBER)
        .inSubdivision("sc")
      .check();
  }

  @Test
  void ensuresSouthDakotaHolidays() {

    assertFor(UNITED_STATES)
      .hasFixedHoliday("WASHINGTONS_BIRTHDAY", FEBRUARY, 22)
        .validBetween(Year.of(1879), Year.of(1967))
        .inSubdivision("sd")
      .and()
      .hasFixedWeekdayHoliday("WASHINGTONS_BIRTHDAY", THIRD, MONDAY, FEBRUARY)
        .validFrom(Year.of(1968))
        .inSubdivision("sd")
      .and()
      .hasFixedWeekdayHoliday("INDIGENOUS_PEOPLES_DAY", SECOND, MONDAY, OCTOBER)
        .validFrom(Year.of(1989))
        .inSubdivision("sd")
      .check();
  }

  @Test
  void ensuresTennesseeHolidays() {

    assertFor(UNITED_STATES)
      .hasFixedHoliday("CHRISTMAS_EVE", DECEMBER, 24)
        .inSubdivision("tn")
      .and()
      .hasFixedHoliday("WASHINGTONS_BIRTHDAY", FEBRUARY, 22)
        .validBetween(Year.of(1879), Year.of(1967))
        .inSubdivision("tn")
      .and()
      .hasFixedWeekdayHoliday("WASHINGTONS_BIRTHDAY", THIRD, MONDAY, FEBRUARY)
        .validFrom(Year.of(1968))
        .inSubdivision("tn")
      .and()
      .hasFixedWeekdayHoliday("CONFEDERATE", FOURTH, MONDAY, APRIL, OBSERVANCE)
        .inSubdivision("tn")
      .and()
      .hasFixedWeekdayHoliday("COLUMBUS_DAY", SECOND, MONDAY, OCTOBER)
        .validFrom(Year.of(1934))
        .inSubdivision("tn")
      .and()
      .hasFixedWeekdayHoliday("DAY_AFTER_THANKSGIVING", FOURTH, FRIDAY, NOVEMBER)
        .inSubdivision("tn")
      .and()
      .hasChristianHoliday("GOOD_FRIDAY", OBSERVANCE)
        .inSubdivision("tn")
      .check();
  }

  @Test
  void ensuresTexasHolidays() {

    assertFor(UNITED_STATES)
      .hasFixedHoliday("CHRISTMAS_EVE", DECEMBER, 24, OBSERVANCE)
        .inSubdivision("tx")
      .and()
      .hasFixedHoliday("WASHINGTONS_BIRTHDAY", FEBRUARY, 22)
        .validBetween(Year.of(1879), Year.of(1967))
        .inSubdivision("tx")
      .and()
      .hasFixedWeekdayHoliday("WASHINGTONS_BIRTHDAY", THIRD, MONDAY, FEBRUARY)
        .validFrom(Year.of(1968))
        .inSubdivision("tx")
      .and()
      .hasFixedWeekdayHoliday("CONFEDERATE", FOURTH, MONDAY, APRIL)
        .validFrom(Year.of(1973))
        .inSubdivision("tx")
      .and()
      .hasFixedWeekdayHoliday("INDIGENOUS_PEOPLES_DAY", SECOND, MONDAY, OCTOBER)
        .validFrom(Year.of(2021))
        .inSubdivision("tx")
      .and()
      .hasFixedWeekdayHoliday("DAY_AFTER_THANKSGIVING", FOURTH, FRIDAY, NOVEMBER)
        .inSubdivision("tx")
      .and()
      .hasChristianHoliday("GOOD_FRIDAY", OBSERVANCE)
        .inSubdivision("tx")
      .check();
  }

  @Test
  void ensuresUtahHolidays() {

    assertFor(UNITED_STATES)
      .hasFixedHoliday("PIONEER", JULY, 24, OBSERVANCE)
        .inSubdivision("ut")
      .and()
      .hasFixedHoliday("WASHINGTONS_BIRTHDAY", FEBRUARY, 22)
        .validBetween(Year.of(1879), Year.of(1967))
        .inSubdivision("ut")
      .and()
      .hasFixedWeekdayHoliday("WASHINGTONS_BIRTHDAY", THIRD, MONDAY, FEBRUARY)
        .validFrom(Year.of(1968))
        .inSubdivision("ut")
      .and()
      .hasFixedWeekdayHoliday("COLUMBUS_DAY", SECOND, MONDAY, OCTOBER)
        .validFrom(Year.of(1934))
        .inSubdivision("ut")
      .check();
  }

  @Test
  void ensuresVermontHolidays() {

    assertFor(UNITED_STATES)
      .hasFixedHoliday("WASHINGTONS_BIRTHDAY", FEBRUARY, 22)
        .validBetween(Year.of(1879), Year.of(1967))
        .inSubdivision("vt")
      .and()
      .hasFixedWeekdayHoliday("WASHINGTONS_BIRTHDAY", THIRD, MONDAY, FEBRUARY)
        .validFrom(Year.of(1968))
        .inSubdivision("vt")
      .and()
      .hasFixedWeekdayHoliday("BENNINGTON", THIRD, MONDAY, AUGUST, OBSERVANCE)
        .inSubdivision("vt")
      .and()
      .hasFixedWeekdayHoliday("INDIGENOUS_PEOPLES_DAY", SECOND, MONDAY, OCTOBER)
        .validFrom(Year.of(2016))
        .inSubdivision("vt")
      .check();
  }

  @Test
  void ensuresVirginiaHolidays() {

    assertFor(UNITED_STATES)
      .hasFixedHoliday("WASHINGTONS_BIRTHDAY", FEBRUARY, 22)
        .validBetween(Year.of(1879), Year.of(1967))
        .inSubdivision("va")
      .and()
      .hasFixedWeekdayHoliday("WASHINGTONS_BIRTHDAY", THIRD, MONDAY, FEBRUARY)
        .validFrom(Year.of(1968))
        .inSubdivision("va")
      .and()
      .hasFixedWeekdayHoliday("COLUMBUS_DAY", SECOND, MONDAY, OCTOBER)
        .validFrom(Year.of(1934))
        .inSubdivision("va")
      .and()
      .hasFixedWeekdayHoliday("INDIGENOUS_PEOPLES_DAY", SECOND, MONDAY, OCTOBER)
        .validFrom(Year.of(2020))
        .inSubdivision("va")
      .and()
      .hasRelativeToWeekdayInMonthHoliday("LEE_JACKSON", FRIDAY, BEFORE, THIRD, MONDAY, JANUARY, OBSERVANCE)
        .validTo(Year.of(2019))
        .inSubdivision("va")
      .check();
  }

  @Test
  void ensuresWashingtonStateHolidays() {

    assertFor(UNITED_STATES)
      .hasFixedHoliday("WASHINGTONS_BIRTHDAY", FEBRUARY, 22)
        .validBetween(Year.of(1879), Year.of(1967))
        .inSubdivision("wa")
      .and()
      .hasFixedWeekdayHoliday("WASHINGTONS_BIRTHDAY", THIRD, MONDAY, FEBRUARY)
        .validFrom(Year.of(1968))
        .inSubdivision("wa")
      .and()
      .hasFixedWeekdayHoliday("NATIVE_AMERICAN_HERITAGE_DAY", FOURTH, FRIDAY, NOVEMBER)
        .inSubdivision("wa")
      .check();
  }

  @Test
  void ensuresWestVirginiaHolidays() {

    assertFor(UNITED_STATES)
      .hasFixedHoliday("WEST_VIRGINIA", JUNE, 20, OBSERVANCE)
        .inSubdivision("wv")
      .and()
      .hasFixedHoliday("WASHINGTONS_BIRTHDAY", FEBRUARY, 22)
        .validBetween(Year.of(1879), Year.of(1967))
        .inSubdivision("wv")
      .and()
      .hasFixedWeekdayHoliday("WASHINGTONS_BIRTHDAY", THIRD, MONDAY, FEBRUARY)
        .validFrom(Year.of(1968))
        .inSubdivision("wv")
      .and()
      .hasFixedWeekdayHoliday("COLUMBUS_DAY", SECOND, MONDAY, OCTOBER)
        .validFrom(Year.of(1934))
        .inSubdivision("wv")
      .and()
      .hasFixedWeekdayHoliday("DAY_AFTER_THANKSGIVING", FOURTH, FRIDAY, NOVEMBER)
        .inSubdivision("wv")
      .check();
  }

  @Test
  void ensuresWisconsinHolidays() {

    assertFor(UNITED_STATES)
      .hasFixedHoliday("WASHINGTONS_BIRTHDAY", FEBRUARY, 22)
        .validBetween(Year.of(1879), Year.of(1967))
        .inSubdivision("wi")
      .and()
      .hasFixedWeekdayHoliday("WASHINGTONS_BIRTHDAY", THIRD, MONDAY, FEBRUARY)
        .validFrom(Year.of(1968))
        .inSubdivision("wi")
      .and()
      .hasFixedWeekdayHoliday("INDIGENOUS_PEOPLES_DAY", SECOND, MONDAY, OCTOBER)
        .validFrom(Year.of(2019))
        .inSubdivision("wi")
      .check();
  }

  @Test
  void ensuresWyomingHolidays() {

    assertFor(UNITED_STATES)
      .hasFixedHoliday("WASHINGTONS_BIRTHDAY", FEBRUARY, 22)
        .validBetween(Year.of(1879), Year.of(1967))
        .inSubdivision("wy")
      .and()
      .hasFixedWeekdayHoliday("WASHINGTONS_BIRTHDAY", THIRD, MONDAY, FEBRUARY)
        .validFrom(Year.of(1968))
        .inSubdivision("wy")
      .check();
  }

  @Test
  void ensuresDistrictOfColumbiaHolidays() {

    assertFor(UNITED_STATES)
      .hasFixedHoliday("WASHINGTONS_BIRTHDAY", FEBRUARY, 22)
        .validBetween(Year.of(1879), Year.of(1967))
        .inSubdivision("dc")
      .and()
      .hasFixedWeekdayHoliday("WASHINGTONS_BIRTHDAY", THIRD, MONDAY, FEBRUARY)
        .validFrom(Year.of(1968))
        .inSubdivision("dc")
      .and()
      .hasFixedWeekdayHoliday("INDIGENOUS_PEOPLES_DAY", SECOND, MONDAY, OCTOBER)
        .validFrom(Year.of(2019))
        .inSubdivision("dc")
      .check();
  }
}
