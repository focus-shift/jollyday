package de.focus_shift.jollyday.tests.country;

import de.focus_shift.jollyday.core.Holiday;
import de.focus_shift.jollyday.core.HolidayManager;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.time.LocalDate;
import java.time.Year;
import java.util.Set;

import static de.focus_shift.jollyday.core.HolidayCalendar.CHILE;
import static de.focus_shift.jollyday.core.HolidayType.BANK_HOLIDAY;
import static de.focus_shift.jollyday.core.ManagerParameters.create;
import static de.focus_shift.jollyday.tests.CalendarChecker.Adjuster.PREVIOUS;
import static de.focus_shift.jollyday.tests.CalendarCheckerApi.assertFor;
import static java.time.DayOfWeek.FRIDAY;
import static java.time.DayOfWeek.MONDAY;
import static java.time.DayOfWeek.THURSDAY;
import static java.time.DayOfWeek.TUESDAY;
import static java.time.DayOfWeek.WEDNESDAY;
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
import static org.assertj.core.api.Assertions.assertThat;

class HolidayCLTest {

  @Test
  void ensuresHolidays() {
    assertFor(CHILE)
      .hasFixedHoliday("NEW_YEAR", JANUARY, 1).and()
      .hasFixedHoliday("ELECTION_DAY", JANUARY, 17)
        .validBetween(Year.of(2010), Year.of(2010)).and()
      .hasFixedHoliday("LABOUR_DAY", MAY, 1).and()
      .hasFixedHoliday("NAVY_DAY", MAY, 21).and()
      .hasFixedHoliday("ST_PETER_PAUL", JUNE, 29)
        .validTo(Year.of(1999))
      .and()
      .hasFixedHoliday("ST_PETER_PAUL", JUNE, 29)
        .validFrom(Year.of(2000))
        .canBeMovedFrom(TUESDAY, PREVIOUS, MONDAY)
        .canBeMovedFrom(WEDNESDAY, PREVIOUS, MONDAY)
        .canBeMovedFrom(THURSDAY, PREVIOUS, MONDAY)
        .canBeMovedFrom(FRIDAY, MONDAY)
      .and()
      .hasFixedHoliday("VIRGIN_CARMEN", JULY, 16)
        .validFrom(Year.of(2007))
      .and()
      .hasFixedHoliday("ASSUMPTION_MARY", AUGUST, 15).and()
      .hasFixedHoliday("NATIONAL_DAY", SEPTEMBER, 18).and()
      .hasFixedHoliday("ARMY_DAY", SEPTEMBER, 19).and()
      .hasFixedHoliday("COLUMBUS_DAY", OCTOBER, 12)
        .validTo(Year.of(1999))
      .and()
      .hasFixedHoliday("COLUMBUS_DAY", OCTOBER, 12)
        .validFrom(Year.of(2000))
        .canBeMovedFrom(TUESDAY, PREVIOUS, MONDAY)
        .canBeMovedFrom(WEDNESDAY, PREVIOUS, MONDAY)
        .canBeMovedFrom(THURSDAY, PREVIOUS, MONDAY)
        .canBeMovedFrom(FRIDAY, MONDAY)
      .and()
      .hasFixedHoliday("REFORMATION_DAY", OCTOBER, 31)
        .validFrom(Year.of(2008))
        .canBeMovedFrom(TUESDAY, PREVIOUS, FRIDAY)
        .canBeMovedFrom(WEDNESDAY, FRIDAY)
      .and()
      .hasFixedHoliday("ALL_SAINTS", NOVEMBER, 1).and()
      .hasFixedHoliday("IMMACULATE_CONCEPTION", DECEMBER, 8).and()
      .hasFixedHoliday("CHRISTMAS", DECEMBER, 25).and()
      .hasFixedHoliday("NEW_YEARS_EVE", DECEMBER, 31, BANK_HOLIDAY).and()
      .hasFixedHoliday("FIESTAS_PATRIAS", SEPTEMBER, 17)
        .validBetween(Year.of(2010), Year.of(2010)).and()
      .hasFixedHoliday("FIESTAS_PATRIAS", SEPTEMBER, 20)
        .validBetween(Year.of(2010), Year.of(2010)).and()
      .hasFixedHoliday("NATIONAL_CENSUS_DAY", APRIL, 19)
        .validBetween(Year.of(2017), Year.of(2017)).and()
      .hasFixedHoliday("FIESTAS_PATRIAS", SEPTEMBER, 16)
        .validBetween(Year.of(2022), Year.of(2022)).and()
      .hasFixedHoliday("INDIGENOUS_PEOPLES_DAY", JUNE, 21)
        .validBetween(Year.of(2021), Year.of(2023)).and()
      .hasFixedHoliday("INDIGENOUS_PEOPLES_DAY", JUNE, 20)
        .validBetween(Year.of(2024), Year.of(2025)).and()
      .hasFixedHoliday("INDIGENOUS_PEOPLES_DAY", JUNE, 21)
        .validBetween(Year.of(2026), Year.of(2026)).and()
      .hasFixedHoliday("ASSAULT_AND_CAPTURE_OF_MORRO_DE_ARICA", JUNE, 7)
        .inSubdivision("ap")
        .validFrom(Year.of(2013))
      .and()
      .hasFixedHoliday("BIRTH_OF_BERNARDO_OHIGGINS", AUGUST, 20)
        .inSubdivision("nb", "ch")
        .validFrom(Year.of(2014))
      .and()
      .hasFixedHoliday("BIRTH_OF_BERNARDO_OHIGGINS", AUGUST, 20)
        .inSubdivision("nb", "cv")
        .validFrom(Year.of(2014))
      .and()
      .hasFixedHoliday("PAPAL_VISIT", JANUARY, 16)
        .inSubdivision("rm")
        .validBetween(Year.of(2018), Year.of(2018)).and()
      .hasFixedHoliday("PAPAL_VISIT", JANUARY, 17)
        .inSubdivision("ar")
        .validBetween(Year.of(2018), Year.of(2018)).and()
      .hasFixedHoliday("PAPAL_VISIT", JANUARY, 18)
        .inSubdivision("ta")
        .validBetween(Year.of(2018), Year.of(2018)).and()
      .hasChristianHoliday("EASTER").and()
      .hasChristianHoliday("GOOD_FRIDAY").and()
      .hasChristianHoliday("EASTER_SATURDAY").and()
      .hasChristianHoliday("CORPUS_CHRISTI")
        .validTo(Year.of(1999))
      .check();
  }

  @ParameterizedTest
  @CsvSource({
    "ST_PETER_PAUL, 2020-06-29",
    "ST_PETER_PAUL, 2022-06-27",
    "ST_PETER_PAUL, 2023-06-26",
    "ST_PETER_PAUL, 2024-06-29",
    "ST_PETER_PAUL, 2028-06-26",
    "ST_PETER_PAUL, 2029-07-02",
    "COLUMBUS_DAY, 2020-10-12",
    "COLUMBUS_DAY, 2022-10-10",
    "COLUMBUS_DAY, 2023-10-09",
    "COLUMBUS_DAY, 2024-10-12",
    "COLUMBUS_DAY, 2028-10-09",
    "COLUMBUS_DAY, 2029-10-15",
    "REFORMATION_DAY, 2023-10-27",
    "REFORMATION_DAY, 2028-10-27",
    "REFORMATION_DAY, 2029-11-02",
    "christian.CORPUS_CHRISTI, 2003-06-16",
    "christian.CORPUS_CHRISTI, 2006-06-12",
    "NEW_YEAR, 2017-01-02",
    "NEW_YEAR, 2023-01-02",
    "FIESTAS_PATRIAS, 2007-09-17",
    "FIESTAS_PATRIAS, 2018-09-17",
    "FIESTAS_PATRIAS, 2019-09-20",
    "FIESTAS_PATRIAS, 2021-09-17",
    "FIESTAS_PATRIAS, 2024-09-20",
    "FIESTAS_PATRIAS, 2027-09-17",
    "FIESTAS_PATRIAS, 2029-09-17",
    "FIESTAS_PATRIAS, 2030-09-20"
  })
  void ensuresMovedAndConditionalHolidays(final String propertiesKey, final LocalDate date) {
    assertThat(holidaysOf(date.getYear()))
      .filteredOn(holiday -> holiday.getPropertiesKey().equals(propertiesKey))
      .extracting(Holiday::getDate)
      .contains(date);
  }

  @ParameterizedTest
  @CsvSource({
    // 18 September on Sunday before Ley 20.983, Monday, Thursday or Friday: no additional day
    "2016", "2023", "2025", "2026"
  })
  void ensuresNoAdditionalFiestasPatriasDay(final int year) {
    assertThat(holidaysOf(year))
      .filteredOn(holiday -> holiday.getPropertiesKey().equals("FIESTAS_PATRIAS"))
      .isEmpty();
  }

  @Test
  void ensuresSecondJanuaryOnlyIfFirstJanuaryIsSunday() {
    assertThat(holidaysOf(2024))
      .extracting(Holiday::getDate)
      .doesNotContain(LocalDate.of(2024, JANUARY, 2));
    assertThat(holidaysOf(2012))
      .extracting(Holiday::getDate)
      .doesNotContain(LocalDate.of(2012, JANUARY, 2));
  }

  private static Set<Holiday> holidaysOf(final int year) {
    return HolidayManager.getInstance(create(CHILE)).getHolidays(Year.of(year));
  }
}
