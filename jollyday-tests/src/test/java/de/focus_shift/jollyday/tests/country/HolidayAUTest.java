package de.focus_shift.jollyday.tests.country;

import de.focus_shift.jollyday.core.Holiday;
import de.focus_shift.jollyday.core.HolidayManager;
import net.jqwik.api.ForAll;
import net.jqwik.api.Property;
import net.jqwik.time.api.constraints.YearRange;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.Year;
import java.util.Set;

import static de.focus_shift.jollyday.core.HolidayCalendar.AUSTRALIA;
import static de.focus_shift.jollyday.core.ManagerParameters.create;
import static org.assertj.core.api.Assertions.assertThat;

class HolidayAUTest extends AbstractCountryTestBase {

  private static final String ISO_CODE = "au";

  @Property
  void testManagerAUStructure(@ForAll @YearRange(min = 2019, max = 2022) Year year) {
    validateCalendarData(ISO_CODE, year, true);
  }

  @Test
  void testManagerAULoadFromUrl() {
    final HolidayManager calendarPartLoaded = HolidayManager.getInstance(create("test_au_2020"));
    final HolidayManager urlLoaded = HolidayManager.getInstance(
      create(AbstractCountryTestBase.class.getClassLoader().getResource("holidays/Holidays_test_au_2020.xml"))
    );

    final var dataHierarchy = calendarPartLoaded.getCalendarHierarchy();
    final var testHierarchy = urlLoaded.getCalendarHierarchy();

    compareHierarchies(testHierarchy, dataHierarchy);
    compareData(urlLoaded, calendarPartLoaded, Year.of(2020), true);
  }

  @Test
  void ensuresNswAnzacDaySubstituteWhenSaturdayFrom2026() {
    // Anzac Day 2026 falls on Saturday — NSW should get Monday substitute from 2026
    final HolidayManager manager = HolidayManager.getInstance(create(AUSTRALIA));

    final Set<Holiday> nswHolidays2026 = manager.getHolidays(Year.of(2026), "nsw");
    assertThat(nswHolidays2026)
      .extracting(Holiday::getDate)
      .contains(LocalDate.of(2026, 4, 25))  // Anzac Day itself
      .contains(LocalDate.of(2026, 4, 27)); // Monday substitute
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
      .contains(LocalDate.of(2021, 4, 25))     // Anzac Day itself (Sunday)
      .doesNotContain(LocalDate.of(2021, 4, 26)); // No Monday substitute before 2026
  }

  @Test
  void ensuresSaAnzacDayNoSubstitute() {
    // SA does not provide a substitute holiday when Anzac Day falls on a weekend
    final HolidayManager manager = HolidayManager.getInstance(create(AUSTRALIA));

    // 2026: Anzac Day on Saturday
    final Set<Holiday> saHolidays2026 = manager.getHolidays(Year.of(2026), "sa");
    assertThat(saHolidays2026)
      .extracting(Holiday::getDate)
      .contains(LocalDate.of(2026, 4, 25))     // Anzac Day itself
      .doesNotContain(LocalDate.of(2026, 4, 27)); // No Monday substitute

    // 2027: Anzac Day on Sunday
    final Set<Holiday> saHolidays2027 = manager.getHolidays(Year.of(2027), "sa");
    assertThat(saHolidays2027)
      .extracting(Holiday::getDate)
      .contains(LocalDate.of(2027, 4, 25))     // Anzac Day itself
      .doesNotContain(LocalDate.of(2027, 4, 26)); // No Monday substitute
  }

  @Test
  void ensuresNswAnzacDaySubstituteWhenSundayFrom2026() {
    // Anzac Day 2027 falls on Sunday — NSW should get Monday substitute
    final HolidayManager manager = HolidayManager.getInstance(create(AUSTRALIA));

    final Set<Holiday> nswHolidays2027 = manager.getHolidays(Year.of(2027), "nsw");
    assertThat(nswHolidays2027)
      .extracting(Holiday::getDate)
      .contains(LocalDate.of(2027, 4, 25))  // Anzac Day itself
      .contains(LocalDate.of(2027, 4, 26)); // Monday substitute
  }

  @Test
  void ensuresActAnzacDaySubstituteStillWorks() {
    // ACT has long-standing Sat/Sun substitution — regression check
    final HolidayManager manager = HolidayManager.getInstance(create(AUSTRALIA));

    final Set<Holiday> actHolidays2026 = manager.getHolidays(Year.of(2026), "act");
    assertThat(actHolidays2026)
      .extracting(Holiday::getDate)
      .contains(LocalDate.of(2026, 4, 25))  // Anzac Day itself
      .contains(LocalDate.of(2026, 4, 27)); // Monday substitute
  }
}
