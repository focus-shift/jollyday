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

import static de.focus_shift.jollyday.core.HolidayCalendar.SWITZERLAND;
import static de.focus_shift.jollyday.core.HolidayType.OFFICIAL_HOLIDAY;
import static de.focus_shift.jollyday.core.ManagerParameters.create;
import static java.time.Month.DECEMBER;
import static java.time.Month.JUNE;
import static java.time.Month.SEPTEMBER;
import static org.assertj.core.api.Assertions.assertThat;

class HolidayCHTest extends AbstractCountryTestBase {

  private static final String ISO_CODE = "ch";

  @Test
  void testManagerCHStructure() {
    validateCalendarData(ISO_CODE, 2022, true);
  }


  @Property
  void ensuresThatStNicholasIsNotConfiguredInObwaldenUntil1946(@ForAll @YearRange(max = 1946) Year year) {
    final HolidayManager holidayManager = HolidayManager.getInstance(create(SWITZERLAND));
    final Set<Holiday> holidays = holidayManager.getHolidays(year.getValue(), "ow");
    assertThat(holidays)
      .isNotEmpty()
      .doesNotContain(new Holiday(LocalDate.of(year.getValue(), SEPTEMBER, 25), "ST_NICHOLAS", OFFICIAL_HOLIDAY));
  }

  @Property
  void ensuresThatStNicholasIsConfiguredInObwalden(@ForAll @YearRange(min = 1947) Year year) {
    final HolidayManager holidayManager = HolidayManager.getInstance(create(SWITZERLAND));
    final Set<Holiday> holidays = holidayManager.getHolidays(year.getValue(), "ow");
    assertThat(holidays)
      .isNotEmpty()
      .contains(new Holiday(LocalDate.of(year.getValue(), SEPTEMBER, 25), "ST_NICHOLAS", OFFICIAL_HOLIDAY));
  }

  @Property
  void ensuresThatStPeterAndPaulIsConfiguredInTicino(@ForAll @YearRange Year year) {
    final HolidayManager holidayManager = HolidayManager.getInstance(create(SWITZERLAND));
    final Set<Holiday> holidays = holidayManager.getHolidays(year.getValue(), "ti");
    assertThat(holidays)
      .isNotEmpty()
      .contains(new Holiday(LocalDate.of(year.getValue(), JUNE, 29), "ST_PETER_PAUL", OFFICIAL_HOLIDAY));
  }

  @Property
  void ensuresThatDayOfIndependenceIsConfiguredInJura(@ForAll @YearRange Year year) {
    final HolidayManager holidayManager = HolidayManager.getInstance(create(SWITZERLAND));
    final Set<Holiday> holidays = holidayManager.getHolidays(year.getValue(), "ju");
    assertThat(holidays)
      .isNotEmpty()
      .contains(new Holiday(LocalDate.of(year.getValue(), JUNE, 23), "INDEPENDENCE_DAY", OFFICIAL_HOLIDAY));
  }

  @Property
  void ensuresThatRestorationOfTheRepublicIsConfiguredInGeneve(@ForAll @YearRange Year year) {
    final HolidayManager holidayManager = HolidayManager.getInstance(create(SWITZERLAND));
    final Set<Holiday> holidays = holidayManager.getHolidays(year.getValue(), "ge");
    assertThat(holidays)
      .isNotEmpty()
      .contains(new Holiday(LocalDate.of(year.getValue(), DECEMBER, 31), "RESTORATION_OF_THE_REPUBLIC", OFFICIAL_HOLIDAY));
  }
}
