package de.focus_shift.jollyday.tests.country;

import de.focus_shift.jollyday.core.Holiday;
import de.focus_shift.jollyday.core.HolidayManager;
import de.focus_shift.jollyday.core.util.CalendarUtil;
import net.jqwik.api.ForAll;
import net.jqwik.api.Property;
import net.jqwik.time.api.constraints.YearRange;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.Year;
import java.util.Set;

import static de.focus_shift.jollyday.core.HolidayCalendar.NEW_ZEALAND;
import static de.focus_shift.jollyday.core.HolidayType.OFFICIAL_HOLIDAY;
import static de.focus_shift.jollyday.core.ManagerParameters.create;
import static java.time.Month.SEPTEMBER;
import static org.assertj.core.api.Assertions.assertThat;

class HolidayNZTest extends AbstractCountryTestBase {

  private static final String ISO_CODE = "nz";
  private static final int YEAR = 2018;

  private final CalendarUtil calendarUtil = new CalendarUtil();
  private final HolidayManager holidayManager = HolidayManager.getInstance(create(NEW_ZEALAND));

  @Test
  void testManagerNZStructure() {
    validateCalendarData(ISO_CODE, YEAR);
  }

  @Test
  void testSouthlandAnniversary2011() {
    // Monday closest to 17 January
    final LocalDate expected = calendarUtil.create(2011, 1, 17);
    final Set<Holiday> holidays = holidayManager.getHolidays(2011, "stl");

    boolean found = holidays.stream()
      .anyMatch(holiday -> holiday.getPropertiesKey().equals("SOUTHLAND_ANNIVERSARY") && holiday.getDate().equals(expected));
    assertThat(found).isTrue();
  }

  @Test
  void testSouthlandAnniversary2012() {
    // Easter Tuesday
    final LocalDate expected = calendarUtil.create(2012, 4, 10);
    final Set<Holiday> holidays = holidayManager.getHolidays(2012, "stl");

    boolean found = holidays.stream()
      .anyMatch(holiday -> holiday.getPropertiesKey().equals("SOUTHLAND_ANNIVERSARY") && holiday.getDate().equals(expected));
    assertThat(found).isTrue();
  }

  @Property
  void ensuresThatQueenElisabethIIMemorialDayIsNotConfiguredUntil2021(@ForAll @YearRange(max = 2021) Year year) {
    final HolidayManager holidayManager = HolidayManager.getInstance(create(ISO_CODE));
    final Set<Holiday> holidays = holidayManager.getHolidays(year.getValue());
    assertThat(holidays)
      .isNotEmpty()
      .doesNotContain(new Holiday(LocalDate.of(year.getValue(), SEPTEMBER, 26), "QUEEN_ELIZABETH_II_MEMORIAL_DAY", OFFICIAL_HOLIDAY));
  }

  @Property
  void ensuresThatQueenElisabethIIMemorialDayIsConfiguredIn2022(@ForAll @YearRange(min = 2022, max = 2022) Year year) {
    final HolidayManager holidayManager = HolidayManager.getInstance(create(ISO_CODE));
    final Set<Holiday> holidays = holidayManager.getHolidays(year.getValue());
    assertThat(holidays)
      .isNotEmpty()
      .contains(new Holiday(LocalDate.of(year.getValue(), SEPTEMBER, 26), "QUEEN_ELIZABETH_II_MEMORIAL_DAY", OFFICIAL_HOLIDAY));
  }

  @Property
  void ensuresThatQueenElisabethIIMemorialDayIsConfiguredSince2023(@ForAll @YearRange(min = 2023) Year year) {
    final HolidayManager holidayManager = HolidayManager.getInstance(create(ISO_CODE));
    final Set<Holiday> holidays = holidayManager.getHolidays(year.getValue());
    assertThat(holidays)
      .isNotEmpty()
      .doesNotContain(new Holiday(LocalDate.of(year.getValue(), SEPTEMBER, 26), "QUEEN_ELIZABETH_II_MEMORIAL_DAY", OFFICIAL_HOLIDAY));
  }
}
