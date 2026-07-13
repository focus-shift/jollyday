package de.focus_shift.jollyday.tests.country;

import de.focus_shift.jollyday.core.Holiday;
import de.focus_shift.jollyday.core.HolidayManager;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.Month;
import java.time.MonthDay;
import java.time.Year;
import java.util.Set;

import static de.focus_shift.jollyday.core.HolidayCalendar.AUSTRALIA;
import static de.focus_shift.jollyday.core.ManagerParameters.create;
import static de.focus_shift.jollyday.core.spi.Occurrence.FIRST;
import static de.focus_shift.jollyday.core.spi.Occurrence.LAST;
import static de.focus_shift.jollyday.core.spi.Occurrence.SECOND;
import static de.focus_shift.jollyday.core.spi.Relation.AFTER;
import static de.focus_shift.jollyday.tests.CalendarCheckerApi.assertFor;
import static java.time.DayOfWeek.FRIDAY;
import static java.time.DayOfWeek.MONDAY;
import static java.time.DayOfWeek.SATURDAY;
import static java.time.DayOfWeek.SUNDAY;
import static java.time.DayOfWeek.TUESDAY;
import static java.time.DayOfWeek.WEDNESDAY;
import static java.time.Month.AUGUST;
import static java.time.Month.FEBRUARY;
import static java.time.Month.JANUARY;
import static java.time.Month.JUNE;
import static java.time.Month.MARCH;
import static java.time.Month.MAY;
import static java.time.Month.NOVEMBER;
import static java.time.Month.OCTOBER;
import static java.time.Month.SEPTEMBER;
import static org.assertj.core.api.Assertions.assertThat;

class HolidayAUTest {

  @Test
  void ensuresNationalHolidays() {
    assertFor(AUSTRALIA)
      .hasFixedHoliday("NEW_YEAR", JANUARY, 1)
        .validTo(Year.of(2007))
        .canBeMovedFrom(SATURDAY, MONDAY)
        .canBeMovedFrom(SUNDAY, MONDAY)
      .and()
      .hasFixedHoliday("NEW_YEAR", JANUARY, 1)
        .validFrom(Year.of(2020))
        .canBeMovedFrom(SATURDAY, MONDAY)
        .canBeMovedFrom(SUNDAY, MONDAY)
      .and()
      .hasFixedHoliday("NATIONAL_DAY", JANUARY, 26)
        .validTo(Year.of(2007))
      .and()
      .hasFixedHoliday("NATIONAL_DAY", JANUARY, 26)
        .validFrom(Year.of(2008))
        .canBeMovedFrom(SATURDAY, MONDAY)
        .canBeMovedFrom(SUNDAY, MONDAY)
      .and()
      .hasFixedHoliday("ANZAC", Month.APRIL, 25)
        .validTo(Year.of(2007))
      .and()
      .hasFixedHoliday("ANZAC", Month.APRIL, 25)
        .validFrom(Year.of(2020))
      .and()
      .hasFixedHoliday("CHRISTMAS", Month.DECEMBER, 25)
        .validTo(Year.of(2007))
        .canBeMovedFrom(SUNDAY, MONDAY)
      .and()
      .hasFixedHoliday("BOXING_DAY", Month.DECEMBER, 26)
        .validTo(Year.of(2007))
        .canBeMovedFrom(SUNDAY, MONDAY)
      .and()
      .hasFixedHoliday("CHRISTMAS", Month.DECEMBER, 25)
        .validFrom(Year.of(2020))
        .canBeMovedFrom(SATURDAY, MONDAY)
        .canBeMovedFrom(SUNDAY, MONDAY)
      .and()
      .hasFixedHoliday("BOXING_DAY", Month.DECEMBER, 26)
        .validFrom(Year.of(2020))
        .canBeMovedFrom(SATURDAY, MONDAY)
        .canBeMovedFrom(SUNDAY, TUESDAY)
        .canBeMovedFrom(MONDAY, TUESDAY)
      .and()
      .hasChristianHoliday("EASTER").and()
      .hasChristianHoliday("GOOD_FRIDAY").and()
      .hasChristianHoliday("EASTER_SATURDAY").and()
      .hasChristianHoliday("EASTER_MONDAY")
      .check();
  }

  @Test
  void ensuresActHolidays() {
    assertFor(AUSTRALIA)
      .hasFixedHoliday("NEW_YEAR", JANUARY, 1)
        .validBetween(Year.of(2008), Year.of(2019))
        .canBeMovedFrom(SATURDAY, MONDAY)
        .canBeMovedFrom(SUNDAY, MONDAY)
        .inSubdivision("act")
      .and()
      .hasFixedHoliday("ANZAC", Month.APRIL, 25)
        .validTo(Year.of(2007))
        .canBeMovedFrom(SATURDAY, MONDAY)
        .canBeMovedFrom(SUNDAY, MONDAY)
        .inSubdivision("act")
      .and()
      .hasFixedHoliday("ANZAC", Month.APRIL, 25)
        .validFrom(Year.of(2020))
        .canBeMovedFrom(SATURDAY, MONDAY)
        .canBeMovedFrom(SUNDAY, MONDAY)
        .inSubdivision("act")
      .and()
      .hasFixedHoliday("CHRISTMAS", Month.DECEMBER, 25)
        .validBetween(Year.of(2008), Year.of(2019))
        .canBeMovedFrom(SATURDAY, MONDAY)
        .canBeMovedFrom(SUNDAY, MONDAY)
        .inSubdivision("act")
      .and()
      .hasFixedHoliday("BOXING_DAY", Month.DECEMBER, 26)
        .validBetween(Year.of(2008), Year.of(2019))
        .canBeMovedFrom(SATURDAY, MONDAY)
        .canBeMovedFrom(SUNDAY, MONDAY)
        .inSubdivision("act")
      .and()
      .hasFixedWeekdayHoliday("CANBERRA_DAY", SECOND, MONDAY, MARCH)
        .inSubdivision("act")
      .and()
      .hasFixedWeekdayHoliday("QUEENS_BIRTHDAY", SECOND, MONDAY, JUNE)
        .inSubdivision("act")
      .and()
      .hasFixedWeekdayHoliday("LABOUR_DAY", FIRST, MONDAY, OCTOBER)
        .inSubdivision("act")
      .and()
      .hasFixedWeekdayHoliday("FAMILY_COMMUNITY", FIRST, TUESDAY, NOVEMBER)
        .validTo(Year.of(2019))
        .inSubdivision("act")
      .and()
      .hasFixedWeekdayRelativeToFixedHoliday("RECONCILIATION", FIRST, MONDAY, AFTER, MonthDay.of(MAY, 26))
        .validFrom(Year.of(2020))
        .inSubdivision("act")
      .check();
  }

  @Test
  void ensuresNswHolidays() {
    assertFor(AUSTRALIA)
      .hasFixedHoliday("NEW_YEAR", JANUARY, 1)
        .validBetween(Year.of(2008), Year.of(2019))
        .canBeMovedFrom(SUNDAY, MONDAY)
        .inSubdivision("nsw")
      .and()
      .hasFixedHoliday("ANZAC", Month.APRIL, 25)
        .validTo(Year.of(2007))
        .canBeMovedFrom(SUNDAY, MONDAY)
        .inSubdivision("nsw")
      .and()
      .hasFixedHoliday("ANZAC", Month.APRIL, 25)
        .validFrom(Year.of(2026))
        .canBeMovedFrom(SATURDAY, MONDAY)
        .canBeMovedFrom(SUNDAY, MONDAY)
        .inSubdivision("nsw")
      .and()
      .hasFixedHoliday("CHRISTMAS", Month.DECEMBER, 25)
        .validBetween(Year.of(2008), Year.of(2019))
        .canBeMovedFrom(SATURDAY, MONDAY)
        .canBeMovedFrom(SUNDAY, MONDAY)
        .inSubdivision("nsw")
      .and()
      .hasFixedHoliday("BOXING_DAY", Month.DECEMBER, 26)
        .validBetween(Year.of(2008), Year.of(2019))
        .canBeMovedFrom(SATURDAY, MONDAY)
        .canBeMovedFrom(SUNDAY, MONDAY)
        .inSubdivision("nsw")
      .and()
      .hasFixedWeekdayHoliday("QUEENS_BIRTHDAY", SECOND, MONDAY, JUNE)
        .inSubdivision("nsw")
      .and()
      .hasFixedWeekdayHoliday("LABOUR_DAY", FIRST, MONDAY, OCTOBER)
        .inSubdivision("nsw")
      .and()
      .hasFixedWeekdayHoliday("BANK_HOLIDAY", FIRST, MONDAY, AUGUST)
        .validFrom(Year.of(2020))
        .inSubdivision("nsw")
      .check();
  }

  @Test
  void ensuresNtHolidays() {
    assertFor(AUSTRALIA)
      .hasFixedHoliday("NEW_YEAR", JANUARY, 1)
        .validBetween(Year.of(2008), Year.of(2019))
        .canBeMovedFrom(SATURDAY, MONDAY)
        .canBeMovedFrom(SUNDAY, MONDAY)
        .inSubdivision("nt")
      .and()
      .hasFixedHoliday("ANZAC", Month.APRIL, 25)
        .validTo(Year.of(2007))
        .canBeMovedFrom(SUNDAY, MONDAY)
        .inSubdivision("nt")
      .and()
      .hasFixedHoliday("ANZAC", Month.APRIL, 25)
        .validFrom(Year.of(2020))
        .canBeMovedFrom(SUNDAY, MONDAY)
        .inSubdivision("nt")
      .and()
      .hasFixedHoliday("CHRISTMAS", Month.DECEMBER, 25)
        .validBetween(Year.of(2008), Year.of(2019))
        .canBeMovedFrom(SATURDAY, MONDAY)
        .canBeMovedFrom(SUNDAY, MONDAY)
        .inSubdivision("nt")
      .and()
      .hasFixedHoliday("BOXING_DAY", Month.DECEMBER, 26)
        .validBetween(Year.of(2008), Year.of(2019))
        .canBeMovedFrom(SATURDAY, MONDAY)
        .canBeMovedFrom(SUNDAY, MONDAY)
        .inSubdivision("nt")
      .and()
      .hasFixedWeekdayHoliday("MAY_DAY", FIRST, MONDAY, MAY)
        .inSubdivision("nt")
      .and()
      .hasFixedWeekdayHoliday("QUEENS_BIRTHDAY", SECOND, MONDAY, JUNE)
        .inSubdivision("nt")
      .and()
      .hasFixedWeekdayHoliday("PICNIC", FIRST, MONDAY, AUGUST)
        .inSubdivision("nt")
      .check();
  }

  @Test
  void ensuresQldHolidays() {
    assertFor(AUSTRALIA)
      .hasFixedHoliday("NEW_YEAR", JANUARY, 1)
        .validBetween(Year.of(2008), Year.of(2019))
        .canBeMovedFrom(SUNDAY, MONDAY)
        .inSubdivision("qld")
      .and()
      .hasFixedHoliday("ANZAC", Month.APRIL, 25)
        .validTo(Year.of(2007))
        .canBeMovedFrom(SUNDAY, MONDAY)
        .inSubdivision("qld")
      .and()
      .hasFixedHoliday("ANZAC", Month.APRIL, 25)
        .validFrom(Year.of(2020))
        .canBeMovedFrom(SUNDAY, MONDAY)
        .inSubdivision("qld")
      .and()
      .hasFixedHoliday("CHRISTMAS", Month.DECEMBER, 25)
        .validBetween(Year.of(2008), Year.of(2019))
        .canBeMovedFrom(SUNDAY, MONDAY)
        .inSubdivision("qld")
      .and()
      .hasFixedHoliday("BOXING_DAY", Month.DECEMBER, 26)
        .validBetween(Year.of(2008), Year.of(2019))
        .canBeMovedFrom(SUNDAY, MONDAY)
        .inSubdivision("qld")
      .and()
      .hasFixedWeekdayHoliday("LABOUR_DAY", FIRST, MONDAY, MAY)
        .inSubdivision("qld")
      .and()
      .hasFixedWeekdayHoliday("QUEENS_BIRTHDAY", SECOND, MONDAY, JUNE)
        .validTo(Year.of(2019))
        .inSubdivision("qld")
      .and()
      .hasFixedWeekdayHoliday("QUEENS_BIRTHDAY", FIRST, MONDAY, OCTOBER)
        .validFrom(Year.of(2020))
        .inSubdivision("qld")
      .and()
      .hasFixedWeekdayBetweenFixedHoliday("EKKA", WEDNESDAY, MonthDay.of(AUGUST, 10), MonthDay.of(AUGUST, 16))
        .validFrom(Year.of(2020))
        .inSubdivision("qld", "br")
      .check();
  }

  @Test
  void ensuresSaHolidays() {
    assertFor(AUSTRALIA)
      .hasFixedHoliday("NEW_YEAR", JANUARY, 1)
        .validBetween(Year.of(2008), Year.of(2019))
        .canBeMovedFrom(SATURDAY, MONDAY)
        .canBeMovedFrom(SUNDAY, MONDAY)
        .inSubdivision("sa")
      .and()
      .hasFixedHoliday("NATIONAL_DAY", JANUARY, 26)
        .validFrom(Year.of(2020))
        .inSubdivision("sa")
      .and()
      .hasFixedHoliday("ANZAC", Month.APRIL, 25)
        .validTo(Year.of(2007))
        .canBeMovedFrom(SUNDAY, MONDAY)
        .inSubdivision("sa")
      .and()
      .hasFixedHoliday("ANZAC", Month.APRIL, 25)
        .validBetween(Year.of(2020), Year.of(2022))
        .canBeMovedFrom(SUNDAY, MONDAY)
        .inSubdivision("sa")
      .and()
      .hasFixedHoliday("CHRISTMAS", Month.DECEMBER, 25)
        .validBetween(Year.of(2008), Year.of(2019))
        .canBeMovedFrom(SATURDAY, MONDAY)
        .canBeMovedFrom(SUNDAY, MONDAY)
        .inSubdivision("sa")
      .and()
      .hasFixedHoliday("PROCLAMATION", Month.DECEMBER, 26)
        .validBetween(Year.of(2008), Year.of(2019))
        .canBeMovedFrom(SATURDAY, MONDAY)
        .canBeMovedFrom(SUNDAY, MONDAY)
        .inSubdivision("sa")
      .and()
      .hasFixedHoliday("PROCLAMATION", Month.DECEMBER, 26)
        .validFrom(Year.of(2020))
        .canBeMovedFrom(SATURDAY, MONDAY)
        .canBeMovedFrom(SUNDAY, TUESDAY)
        .canBeMovedFrom(MONDAY, TUESDAY)
        .inSubdivision("sa")
      .and()
      .hasFixedWeekdayHoliday("ADELAIDE_CUP", SECOND, MONDAY, MARCH)
        .inSubdivision("sa")
      .and()
      .hasFixedWeekdayHoliday("QUEENS_BIRTHDAY", SECOND, MONDAY, JUNE)
        .inSubdivision("sa")
      .and()
      .hasFixedWeekdayHoliday("LABOUR_DAY", FIRST, MONDAY, OCTOBER)
        .inSubdivision("sa")
      .check();
  }

  @Test
  void ensuresTasHolidays() {
    assertFor(AUSTRALIA)
      .hasFixedHoliday("ANZAC", Month.APRIL, 25)
        .validTo(Year.of(2019))
        .inSubdivision("tas")
      .and()
      .hasFixedHoliday("NEW_YEAR", JANUARY, 1)
        .validBetween(Year.of(2008), Year.of(2019))
        .canBeMovedFrom(SATURDAY, MONDAY)
        .canBeMovedFrom(SUNDAY, MONDAY)
        .inSubdivision("tas")
      .and()
      .hasFixedHoliday("CHRISTMAS", Month.DECEMBER, 25)
        .validBetween(Year.of(2008), Year.of(2019))
        .canBeMovedFrom(SATURDAY, MONDAY)
        .canBeMovedFrom(SUNDAY, MONDAY)
        .inSubdivision("tas")
      .and()
      .hasFixedHoliday("BOXING_DAY", Month.DECEMBER, 26)
        .validBetween(Year.of(2008), Year.of(2019))
        .canBeMovedFrom(SATURDAY, MONDAY)
        .canBeMovedFrom(SUNDAY, MONDAY)
        .inSubdivision("tas")
      .and()
      .hasFixedWeekdayHoliday("EIGHT", SECOND, MONDAY, MARCH)
        .inSubdivision("tas")
      .and()
      .hasFixedWeekdayHoliday("QUEENS_BIRTHDAY", SECOND, MONDAY, JUNE)
        .inSubdivision("tas")
      .and()
      .hasChristianHoliday("EASTER_TUESDAY")
        .inSubdivision("tas")
      .and()
      .hasFixedWeekdayHoliday("HOBART", SECOND, MONDAY, FEBRUARY)
        .inSubdivision("tas", "ho")
      .and()
      .hasFixedWeekdayHoliday("RECREATION", FIRST, MONDAY, NOVEMBER)
        .inSubdivision("tas", "nh")
      .check();
  }

  @Test
  void ensuresVicHolidays() {
    assertFor(AUSTRALIA)
      .hasFixedHoliday("ANZAC", Month.APRIL, 25)
        .validTo(Year.of(2019))
        .inSubdivision("vic")
      .and()
      .hasFixedHoliday("NEW_YEAR", JANUARY, 1)
        .validBetween(Year.of(2008), Year.of(2019))
        .canBeMovedFrom(SUNDAY, MONDAY)
        .inSubdivision("vic")
      .and()
      .hasFixedHoliday("CHRISTMAS", Month.DECEMBER, 25)
        .validBetween(Year.of(2008), Year.of(2019))
        .canBeMovedFrom(SATURDAY, MONDAY)
        .canBeMovedFrom(SUNDAY, MONDAY)
        .inSubdivision("vic")
      .and()
      .hasFixedHoliday("BOXING_DAY", Month.DECEMBER, 26)
        .validBetween(Year.of(2008), Year.of(2019))
        .canBeMovedFrom(SATURDAY, MONDAY)
        .canBeMovedFrom(SUNDAY, MONDAY)
        .inSubdivision("vic")
      .and()
      .hasFixedWeekdayHoliday("LABOUR_DAY", SECOND, MONDAY, MARCH)
        .inSubdivision("vic")
      .and()
      .hasFixedWeekdayHoliday("QUEENS_BIRTHDAY", SECOND, MONDAY, JUNE)
        .inSubdivision("vic")
      .and()
      .hasFixedWeekdayHoliday("MELBOURNE_CUP", FIRST, TUESDAY, NOVEMBER)
        .inSubdivision("vic")
      .and()
      .hasFixedWeekdayHoliday("AFL", LAST, FRIDAY, SEPTEMBER)
        .validFrom(Year.of(2020))
        .inSubdivision("vic")
      .check();
  }

  @Test
  void ensuresWaHolidays() {
    assertFor(AUSTRALIA)
      .hasFixedHoliday("NEW_YEAR", JANUARY, 1)
        .validBetween(Year.of(2008), Year.of(2019))
        .canBeMovedFrom(SATURDAY, MONDAY)
        .canBeMovedFrom(SUNDAY, MONDAY)
        .inSubdivision("wa")
      .and()
      .hasFixedHoliday("ANZAC", Month.APRIL, 25)
        .validTo(Year.of(2007))
        .canBeMovedFrom(SATURDAY, MONDAY)
        .canBeMovedFrom(SUNDAY, MONDAY)
        .inSubdivision("wa")
      .and()
      .hasFixedHoliday("ANZAC", Month.APRIL, 25)
        .validFrom(Year.of(2020))
        .canBeMovedFrom(SATURDAY, MONDAY)
        .canBeMovedFrom(SUNDAY, MONDAY)
        .inSubdivision("wa")
      .and()
      .hasFixedHoliday("CHRISTMAS", Month.DECEMBER, 25)
        .validBetween(Year.of(2008), Year.of(2019))
        .canBeMovedFrom(SATURDAY, MONDAY)
        .canBeMovedFrom(SUNDAY, MONDAY)
        .inSubdivision("wa")
      .and()
      .hasFixedHoliday("BOXING_DAY", Month.DECEMBER, 26)
        .validBetween(Year.of(2008), Year.of(2019))
        .canBeMovedFrom(SATURDAY, MONDAY)
        .canBeMovedFrom(SUNDAY, MONDAY)
        .inSubdivision("wa")
      .and()
      .hasFixedWeekdayHoliday("LABOUR_DAY", FIRST, MONDAY, MARCH)
        .inSubdivision("wa")
      .and()
      .hasFixedWeekdayHoliday("FOUNDATION", FIRST, MONDAY, JUNE)
        .validTo(Year.of(2019))
        .inSubdivision("wa")
      .and()
      .hasFixedWeekdayHoliday("WESTERN_AUSTRALIA", FIRST, MONDAY, JUNE)
        .validFrom(Year.of(2020))
        .inSubdivision("wa")
      .and()
      .hasFixedWeekdayHoliday("QUEENS_BIRTHDAY", LAST, MONDAY, SEPTEMBER)
        .inSubdivision("wa")
      .check();
  }

  @Test
  void ensuresNswAnzacDaySubstituteWhenSaturdayFrom2026() {
    // Anzac Day 2026 falls on Saturday — NSW should get Monday substitute from 2026
    final HolidayManager manager = HolidayManager.getInstance(create(AUSTRALIA));

    final Set<Holiday> nswHolidays2026 = manager.getHolidays(Year.of(2026), "nsw");
    assertThat(nswHolidays2026)
      .extracting(Holiday::getDate)
      .contains(LocalDate.of(2026, Month.APRIL, 25))  // Anzac Day itself
      .contains(LocalDate.of(2026, Month.APRIL, 27)); // Monday substitute
  }

  @Test
  void ensuresNswAnzacDayNoSubstituteBefore2026() {
    // Anzac Day 2025 falls on Friday — no substitute needed, but also confirm
    // that the validFrom=2026 boundary is respected by checking a year where
    // Anzac Day falls on Sunday (2021) — NSW should NOT get a Monday substitute before 2026
    final HolidayManager manager = HolidayManager.getInstance(create(AUSTRALIA));

    final Set<Holiday> nswHolidays2021 = manager.getHolidays(Year.of(2021), "nsw");
    assertThat(nswHolidays2021)
      .extracting(Holiday::getDate)
      .contains(LocalDate.of(2021, Month.APRIL, 25))     // Anzac Day itself (Sunday)
      .doesNotContain(LocalDate.of(2021, Month.APRIL, 26)); // No Monday substitute before 2026
  }

  @Test
  void ensuresSaAnzacDayNoSubstitute() {
    // SA does not provide a substitute holiday when Anzac Day falls on a weekend
    final HolidayManager manager = HolidayManager.getInstance(create(AUSTRALIA));

    // 2026: Anzac Day on Saturday
    final Set<Holiday> saHolidays2026 = manager.getHolidays(Year.of(2026), "sa");
    assertThat(saHolidays2026)
      .extracting(Holiday::getDate)
      .contains(LocalDate.of(2026, Month.APRIL, 25))     // Anzac Day itself
      .doesNotContain(LocalDate.of(2026, Month.APRIL, 27)); // No Monday substitute

    // 2027: Anzac Day on Sunday
    final Set<Holiday> saHolidays2027 = manager.getHolidays(Year.of(2027), "sa");
    assertThat(saHolidays2027)
      .extracting(Holiday::getDate)
      .contains(LocalDate.of(2027, Month.APRIL, 25))     // Anzac Day itself
      .doesNotContain(LocalDate.of(2027, Month.APRIL, 26)); // No Monday substitute
  }

  @Test
  void ensuresNswAnzacDaySubstituteWhenSundayFrom2026() {
    // Anzac Day 2027 falls on Sunday — NSW should get Monday substitute
    final HolidayManager manager = HolidayManager.getInstance(create(AUSTRALIA));

    final Set<Holiday> nswHolidays2027 = manager.getHolidays(Year.of(2027), "nsw");
    assertThat(nswHolidays2027)
      .extracting(Holiday::getDate)
      .contains(LocalDate.of(2027, Month.APRIL, 25))  // Anzac Day itself
      .contains(LocalDate.of(2027, Month.APRIL, 26)); // Monday substitute
  }

  @Test
  void ensuresActAnzacDaySubstituteStillWorks() {
    // ACT has long-standing Sat/Sun substitution — regression check
    final HolidayManager manager = HolidayManager.getInstance(create(AUSTRALIA));

    final Set<Holiday> actHolidays2026 = manager.getHolidays(Year.of(2026), "act");
    assertThat(actHolidays2026)
      .extracting(Holiday::getDate)
      .contains(LocalDate.of(2026, Month.APRIL, 25))  // Anzac Day itself
      .contains(LocalDate.of(2026, Month.APRIL, 27)); // Monday substitute
  }
}
