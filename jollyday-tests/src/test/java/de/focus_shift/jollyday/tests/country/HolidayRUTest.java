package de.focus_shift.jollyday.tests.country;

import de.focus_shift.jollyday.core.Holiday;
import de.focus_shift.jollyday.core.HolidayManager;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.MonthDay;
import java.time.Year;
import java.util.Set;

import static de.focus_shift.jollyday.core.HolidayCalendar.RUSSIA;
import static de.focus_shift.jollyday.core.ManagerParameters.create;
import static de.focus_shift.jollyday.core.spi.Occurrence.FIRST;
import static de.focus_shift.jollyday.core.spi.Relation.BEFORE;
import static de.focus_shift.jollyday.tests.CalendarCheckerApi.assertFor;
import static java.time.DayOfWeek.MONDAY;
import static java.time.DayOfWeek.SATURDAY;
import static java.time.DayOfWeek.SUNDAY;
import static java.time.Month.APRIL;
import static java.time.Month.AUGUST;
import static java.time.Month.DECEMBER;
import static java.time.Month.FEBRUARY;
import static java.time.Month.JANUARY;
import static java.time.Month.JUNE;
import static java.time.Month.JULY;
import static java.time.Month.MARCH;
import static java.time.Month.MAY;
import static java.time.Month.NOVEMBER;
import static java.time.Month.OCTOBER;
import static org.assertj.core.api.Assertions.assertThat;

class HolidayRUTest {

  // Tatarstan and Bashkortostan carry Islamic holidays, and the Hijrah calendar does
  // not span the checker's full default range, so the regional checks are bounded
  private static final Year CHECK_FROM = Year.of(1900);
  private static final Year CHECK_TO = Year.of(2173);

  @Test
  void ensuresHolidays() {
    assertFor(RUSSIA)
      .hasFixedHoliday("NEW_YEAR", JANUARY, 1)
        .canBeMovedFrom(SATURDAY, MONDAY)
        .canBeMovedFrom(SUNDAY, MONDAY).and()
      .hasFixedHoliday("NEW_YEAR", JANUARY, 2)
        .canBeMovedFrom(SATURDAY, MONDAY)
        .canBeMovedFrom(SUNDAY, MONDAY).and()
      .hasFixedHoliday("NEW_YEAR", JANUARY, 3)
        .canBeMovedFrom(SATURDAY, MONDAY)
        .canBeMovedFrom(SUNDAY, MONDAY).and()
      .hasFixedHoliday("NEW_YEAR", JANUARY, 4)
        .canBeMovedFrom(SATURDAY, MONDAY)
        .canBeMovedFrom(SUNDAY, MONDAY).and()
      .hasFixedHoliday("NEW_YEAR", JANUARY, 5)
        .canBeMovedFrom(SATURDAY, MONDAY)
        .canBeMovedFrom(SUNDAY, MONDAY).and()
      .hasFixedHoliday("NEW_YEAR", JANUARY, 6)
        .canBeMovedFrom(SATURDAY, MONDAY)
        .canBeMovedFrom(SUNDAY, MONDAY).and()
      .hasFixedHoliday("CHRISTMAS", JANUARY, 7)
        .canBeMovedFrom(SATURDAY, MONDAY)
        .canBeMovedFrom(SUNDAY, MONDAY).and()
      .hasFixedHoliday("NEW_YEAR", JANUARY, 8)
        .canBeMovedFrom(SATURDAY, MONDAY)
        .canBeMovedFrom(SUNDAY, MONDAY).and()
      .hasFixedHoliday("NEW_YEAR", JANUARY, 9)
        .canBeMovedFrom(SATURDAY, MONDAY)
        .canBeMovedFrom(SUNDAY, MONDAY).and()
      .hasFixedHoliday("DEFENDER_FATHERLAND", FEBRUARY, 23)
        .canBeMovedFrom(SATURDAY, MONDAY)
        .canBeMovedFrom(SUNDAY, MONDAY).and()
      .hasFixedHoliday("INTERNATIONAL_WOMAN", MARCH, 8)
        .canBeMovedFrom(SATURDAY, MONDAY)
        .canBeMovedFrom(SUNDAY, MONDAY).and()
      .hasFixedHoliday("LABOUR_DAY", MAY, 1)
        .canBeMovedFrom(SATURDAY, MONDAY)
        .canBeMovedFrom(SUNDAY, MONDAY).and()
      .hasFixedHoliday("VICTORY", MAY, 9)
        .canBeMovedFrom(SATURDAY, MONDAY)
        .canBeMovedFrom(SUNDAY, MONDAY).and()
      .hasFixedHoliday("INDEPENDENCE_DAY", JUNE, 12)
        .notValidBetween(Year.of(1900), Year.of(1991))
        .validFrom(Year.of(1992))
        .canBeMovedFrom(SATURDAY, MONDAY)
        .canBeMovedFrom(SUNDAY, MONDAY).and()
      .hasFixedHoliday("CONSOLIDATION", NOVEMBER, 4)
        .notValidBetween(Year.of(1900), Year.of(2004))
        .validFrom(Year.of(2005))
        .canBeMovedFrom(SATURDAY, MONDAY)
        .canBeMovedFrom(SUNDAY, MONDAY)
      .check();
  }

  /**
   * Regional non-working days established by the law of the subject itself, on top of
   * the all-Russia list in Article 112 of the Labour Code.
   *
   * <p>Tatarstan is the exception on weekend handling: the 2016 redaction of law
   * 1448-XII dropped the substitute day off that the original text granted, so its four
   * days carry no moving condition while every other region here shifts to the Monday.
   */
  @Test
  void ensuresRegionalHolidays() {
    assertFor(RUSSIA)
      // Tatarstan - law 1448-XII, no substitute day off
      .hasFixedHoliday("REPUBLIC_DAY", AUGUST, 30).inSubdivision("ta").validBetween(CHECK_FROM, CHECK_TO).and()
      .hasFixedHoliday("CONSTITUTION_DAY", NOVEMBER, 6).inSubdivision("ta").validBetween(CHECK_FROM, CHECK_TO).and()
      // Bashkortostan - law VS-10/21
      .hasFixedHoliday("REPUBLIC_DAY", OCTOBER, 11).inSubdivision("ba").validBetween(CHECK_FROM, CHECK_TO)
        .canBeMovedFrom(SATURDAY, MONDAY)
        .canBeMovedFrom(SUNDAY, MONDAY).and()
      // Chuvashia - law N 4
      .hasFixedHoliday("REPUBLIC_DAY", JUNE, 24).inSubdivision("cu").validBetween(CHECK_FROM, CHECK_TO)
        .canBeMovedFrom(SATURDAY, MONDAY)
        .canBeMovedFrom(SUNDAY, MONDAY).and()
      // Kalmykia - law 156-III-Z
      .hasFixedHoliday("REPUBLIC_DAY", JULY, 5).inSubdivision("kl").validBetween(CHECK_FROM, CHECK_TO)
        .canBeMovedFrom(SATURDAY, MONDAY)
        .canBeMovedFrom(SUNDAY, MONDAY).and()
      .hasFixedHoliday("DEPORTATION_VICTIMS_MEMORIAL_DAY", DECEMBER, 28).inSubdivision("kl").validBetween(CHECK_FROM, CHECK_TO)
        .canBeMovedFrom(SATURDAY, MONDAY)
        .canBeMovedFrom(SUNDAY, MONDAY).and()
      // Belgorod Oblast - law N 462
      .hasFixedHoliday("PROKHOROVKA_FIELD_DAY", JULY, 12).inSubdivision("bel").validBetween(CHECK_FROM, CHECK_TO)
        .canBeMovedFrom(SATURDAY, MONDAY)
        .canBeMovedFrom(SUNDAY, MONDAY).and()
      // North Ossetia-Alania - law 61-RZ, Monday of the last full week within November
      .hasFixedWeekdayRelativeToFixedHoliday("DJEORGUYBA", FIRST, MONDAY, BEFORE, MonthDay.of(NOVEMBER, 25))
        .inSubdivision("se").validBetween(CHECK_FROM, CHECK_TO).and()
      // Radonitsa, Orthodox Easter + 9 days, fixed by standing law in five regions
      .hasRelativeToEasterSundayHoliday("RADONITSA").inSubdivision("kda").validBetween(CHECK_FROM, CHECK_TO).and()
      .hasRelativeToEasterSundayHoliday("RADONITSA").inSubdivision("sta").validBetween(CHECK_FROM, CHECK_TO).and()
      .hasRelativeToEasterSundayHoliday("RADONITSA").inSubdivision("sar").validBetween(CHECK_FROM, CHECK_TO).and()
      .hasRelativeToEasterSundayHoliday("RADONITSA").inSubdivision("pnz").validBetween(CHECK_FROM, CHECK_TO)
      .check();
  }

  /**
   * Eid is non-working by standing law in Tatarstan (1448-XII) and Bashkortostan
   * (VS-10/21 Article 3.1); the annual government resolution only announces the date.
   * Checked over a bounded range because the Hijrah calendar does not span the
   * checker's full default range.
   */
  @Test
  void ensuresRegionalIslamicHolidays() {
    assertFor(RUSSIA)
      .hasIslamicHoliday("ID_AL_FITR").inSubdivision("ta").validBetween(CHECK_FROM, CHECK_TO).and()
      .hasIslamicHoliday("ID_UL_ADHA").inSubdivision("ta").validBetween(CHECK_FROM, CHECK_TO).and()
      .hasIslamicHoliday("ID_AL_FITR").inSubdivision("ba").validBetween(CHECK_FROM, CHECK_TO).and()
      .hasIslamicHoliday("ID_UL_ADHA").inSubdivision("ba").validBetween(CHECK_FROM, CHECK_TO)
      .check();
  }

  /**
   * The two computed regional rules produce dates that cannot be read off the
   * declarative assertions above, so they are pinned to concrete days here.
   *
   * <p>Djeorguyba is the Monday of the last Monday-to-Sunday week lying wholly within
   * November, which is the last Monday strictly before 25 November. Radonitsa is the
   * ninth day after Orthodox Easter and therefore always a Tuesday; the appendices to
   * the Krasnodar and Stavropol laws enumerate the dates and agree with these.
   */
  @Test
  void ensuresComputedRegionalDatesMatch() {
    final HolidayManager holidayManager = HolidayManager.getInstance(create(RUSSIA));

    assertThat(holidayManager.getHolidays(Year.of(2024), "se")).extracting(Holiday::getDate)
      .contains(LocalDate.of(2024, NOVEMBER, 18));
    assertThat(holidayManager.getHolidays(Year.of(2025), "se")).extracting(Holiday::getDate)
      .contains(LocalDate.of(2025, NOVEMBER, 24));
    assertThat(holidayManager.getHolidays(Year.of(2026), "se")).extracting(Holiday::getDate)
      .contains(LocalDate.of(2026, NOVEMBER, 23));

    assertThat(holidayManager.getHolidays(Year.of(2024), "kda")).extracting(Holiday::getDate)
      .contains(LocalDate.of(2024, MAY, 14));
    assertThat(holidayManager.getHolidays(Year.of(2025), "kda")).extracting(Holiday::getDate)
      .contains(LocalDate.of(2025, APRIL, 29));
    assertThat(holidayManager.getHolidays(Year.of(2026), "kda")).extracting(Holiday::getDate)
      .contains(LocalDate.of(2026, APRIL, 21));
  }

  /**
   * Tatarstan grants no substitute day off, Bashkortostan does. Pinned to two years
   * where the day actually falls on a weekend, since this is the easiest thing to get
   * wrong and the two regions disagree.
   */
  @Test
  void ensuresWeekendHandlingDiffersBetweenTatarstanAndBashkortostan() {
    final HolidayManager holidayManager = HolidayManager.getInstance(create(RUSSIA));

    // 30 August 2026 is a Sunday and stays put
    final Set<Holiday> tatarstan = holidayManager.getHolidays(Year.of(2026), "ta");
    assertThat(tatarstan).extracting(Holiday::getDate)
      .contains(LocalDate.of(2026, AUGUST, 30))
      .doesNotContain(LocalDate.of(2026, AUGUST, 31));

    // 11 October 2025 is a Saturday and moves to the Monday
    final Set<Holiday> bashkortostan = holidayManager.getHolidays(Year.of(2025), "ba");
    assertThat(bashkortostan).extracting(Holiday::getDate)
      .contains(LocalDate.of(2025, OCTOBER, 13))
      .doesNotContain(LocalDate.of(2025, OCTOBER, 11));
  }

  /**
   * A subdivision without its own regional law inherits the national calendar unchanged.
   */
  @Test
  void ensuresSubdivisionWithoutRegionalLawHasOnlyNationalHolidays() {
    final HolidayManager holidayManager = HolidayManager.getInstance(create(RUSSIA));

    assertThat(holidayManager.getHolidays(Year.of(2026), "ud"))
      .isEqualTo(holidayManager.getHolidays(Year.of(2026)));
  }
}
