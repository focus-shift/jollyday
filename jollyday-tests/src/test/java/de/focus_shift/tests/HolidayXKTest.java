package de.focus_shift.tests;

import de.focus_shift.Holiday;
import de.focus_shift.HolidayManager;
import de.focus_shift.ManagerParameters;
import de.focus_shift.tests.base.AbstractCountryTestBase;
import de.focus_shift.util.CalendarUtil;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.Set;

import static de.focus_shift.HolidayCalendar.KOSOVO;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.fail;

class HolidayXKTest extends AbstractCountryTestBase {

  private static final String ISO_CODE = "xk";

  private final CalendarUtil calendarUtil = new CalendarUtil();

  @Test
  void testManagerXKStructure2021() {
    validateCalendarData(ISO_CODE, 2021);
  }

  @Test
  void testManagerXKStructure2022() {
    validateCalendarData(ISO_CODE, 2022);
  }

  @Test
  void testManagerXKStructure2023() {
    validateCalendarData(ISO_CODE, 2023);
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
  void testManagerDifferentInstance() {
    Locale defaultLocale = Locale.getDefault();
    Locale.setDefault(Locale.US);
    try {
      final HolidayManager defaultManager = HolidayManager.getInstance();
      final HolidayManager germanManager = HolidayManager.getInstance(ManagerParameters.create(KOSOVO));
      assertThat(defaultManager).isNotEqualTo(germanManager);
    } catch (Exception e) {
      fail("Unexpected error occurred: " + e.getClass().getName() + " - " + e.getMessage());
    } finally {
      Locale.setDefault(defaultLocale);
    }
  }
}
