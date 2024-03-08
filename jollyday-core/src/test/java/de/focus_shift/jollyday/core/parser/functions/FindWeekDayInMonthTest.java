package de.focus_shift.jollyday.core.parser.functions;

import de.focus_shift.jollyday.core.HolidayType;
import de.focus_shift.jollyday.core.spi.FixedWeekdayInMonth;
import de.focus_shift.jollyday.core.spi.Occurrance;
import de.focus_shift.jollyday.core.spi.YearCycle;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.Month;
import java.time.Year;

import static java.time.Month.MARCH;
import static org.assertj.core.api.Assertions.assertThat;

class FindWeekDayInMonthTest {

  @ParameterizedTest
  @CsvSource({
    "FIRST,2024-03-04",
    "SECOND,2024-03-11",
    "THIRD,2024-03-18",
    "FOURTH,2024-03-25",
    "LAST,2024-03-25",
  })
  void ensureToFindFixedWeekdayInMonth(final Occurrance occurrance, final LocalDate expectedLocalDate) {

    final FixedWeekdayInMonth fixedWeekdayInMonth = new FixedWeekdayInMonth() {
      @Override
      public Year validFrom() {
        return null;
      }

      @Override
      public Year validTo() {
        return null;
      }

      @Override
      public YearCycle cycle() {
        return null;
      }

      @Override
      public DayOfWeek weekday() {
        return DayOfWeek.MONDAY;
      }

      @Override
      public Month month() {
        return MARCH;
      }

      @Override
      public Occurrance which() {
        return occurrance;
      }

      @Override
      public String descriptionPropertiesKey() {
        return null;
      }

      @Override
      public HolidayType officiality() {
        return null;
      }
    };

    final LocalDate actualLocalDate = new FindWeekDayInMonth(2024).apply(fixedWeekdayInMonth);
    assertThat(actualLocalDate).isEqualTo(expectedLocalDate);
  }
}
