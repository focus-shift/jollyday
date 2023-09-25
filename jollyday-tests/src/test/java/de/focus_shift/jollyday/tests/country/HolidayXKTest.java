package de.focus_shift.jollyday.tests.country;

import de.focus_shift.jollyday.core.Holiday;
import de.focus_shift.jollyday.core.HolidayManager;
import de.focus_shift.jollyday.core.ManagerParameters;
import de.focus_shift.jollyday.core.util.CalendarUtil;
import de.focus_shift.jollyday.tests.country.base.AbstractCountryTestBase;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

import static de.focus_shift.jollyday.core.HolidayCalendar.KOSOVO;
import static org.assertj.core.api.Assertions.assertThat;

class HolidayXKTest extends AbstractCountryTestBase {

  private static final String ISO_CODE = "xk";

  private final CalendarUtil calendarUtil = new CalendarUtil();

  @ParameterizedTest
  @ValueSource(ints = {2021, 2022, 2023})
  void testManagerXKStructure(final int year) {
    validateCalendarData(ISO_CODE, year, true);
  }

  @Test
  void testManagerXKInterval() {
    HolidayManager instance = HolidayManager.getInstance(ManagerParameters.create(KOSOVO));
    Set<Holiday> holidays = instance.getHolidays(calendarUtil.create(2010, 6, 1),
      calendarUtil.create(2011, 5, 31));

    List<LocalDate> expected = Arrays.asList(calendarUtil.create(2010, 6, 12),
      calendarUtil.create(2010, 6, 15),
      calendarUtil.create(2010, 9, 28),
      calendarUtil.create(2010, 11, 28),
      calendarUtil.create(2010, 12, 25),
      calendarUtil.create(2011, 1, 1),
      calendarUtil.create(2011, 2, 15),
      calendarUtil.create(2011, 2, 17),
      calendarUtil.create(2011, 3, 6),
      calendarUtil.create(2011, 3, 8),
      calendarUtil.create(2011, 4, 23),
      calendarUtil.create(2011, 5, 1),
      calendarUtil.create(2011, 5, 6),
      calendarUtil.create(2011, 5, 9)
    );

    assertThat(holidays).hasSameSizeAs(expected);
    holidays.forEach(holiday -> assertThat(expected).contains(holiday.getDate()));
    expected.forEach(d -> assertThat(calendarUtil.contains(holidays, d)).isTrue());
  }

  @Test
  void testManagerDifferentInstanceXK() {
    validateManagerDifferentInstance(KOSOVO);
  }
}
