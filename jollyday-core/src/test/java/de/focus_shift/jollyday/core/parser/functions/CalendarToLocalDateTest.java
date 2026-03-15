package de.focus_shift.jollyday.core.parser.functions;

import static java.time.Month.APRIL;
import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDate;
import java.util.Calendar;
import java.util.GregorianCalendar;
import org.junit.jupiter.api.Test;

class CalendarToLocalDateTest {

  @Test
  void ensuresToConvertCalendarToLocalDate() {
    final Calendar calendar = new GregorianCalendar(2022, Calendar.APRIL, 2);
    final LocalDate localDate = new CalendarToLocalDate().apply(calendar);
    assertThat(localDate).hasYear(2022).hasMonth(APRIL).hasDayOfMonth(2);
  }
}
