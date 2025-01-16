package de.focus_shift.jollyday.core.parser.impl;

import de.focus_shift.jollyday.core.Holiday;
import de.focus_shift.jollyday.core.HolidayType;
import de.focus_shift.jollyday.core.spi.FixedWeekdayInMonth;
import de.focus_shift.jollyday.core.spi.Holidays;
import de.focus_shift.jollyday.core.spi.Limited;
import de.focus_shift.jollyday.core.spi.Occurrence;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.Month;
import java.time.Year;
import java.util.List;

import static de.focus_shift.jollyday.core.HolidayType.PUBLIC_HOLIDAY;
import static de.focus_shift.jollyday.core.spi.Limited.YearCycle.EVERY_YEAR;
import static java.time.DayOfWeek.WEDNESDAY;
import static java.time.Month.JANUARY;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class FixedWeekdayInMonthParserTest {

  @Mock
  private Holidays holidays;

  @Nested
  class LimitedTests {

    @Test
    void ensureThatFixedWeekdayInMonthAreLimitedAndIsValid() {

      final Year year = Year.of(2025);
      final FixedWeekdayInMonth fixedWeekdayInMonth = getFixedWeekdayInMonth(WEDNESDAY, JANUARY, Occurrence.LAST, year, year, EVERY_YEAR);

      final FixedWeekdayInMonthParser sut = new FixedWeekdayInMonthParser();
      when(holidays.fixedWeekdays()).thenReturn(List.of(fixedWeekdayInMonth));

      final List<Holiday> calculatedHoliday = sut.parse(year, holidays);
      assertThat(calculatedHoliday.get(0).getDate()).isEqualTo(LocalDate.of(2025, JANUARY, 29));
    }

    @Test
    void ensureThatFixedWeekdayInMonthAreLimitedAndIsInvalid() {

      final FixedWeekdayInMonth fixedWeekdayInMonth = getFixedWeekdayInMonth(WEDNESDAY, JANUARY, Occurrence.LAST, Year.of(2023), Year.of(2023), EVERY_YEAR);

      final FixedWeekdayInMonthParser sut = new FixedWeekdayInMonthParser();
      when(holidays.fixedWeekdays()).thenReturn(List.of(fixedWeekdayInMonth));

      final List<Holiday> calculatedHoliday = sut.parse(Year.of(2022), holidays);
      assertThat(calculatedHoliday).isEmpty();
    }
  }

  private static FixedWeekdayInMonth getFixedWeekdayInMonth(final DayOfWeek dayOfWeek, final Month month, final Occurrence occurrence,
                                                            final Year validFrom, final Year validTo, final Limited.YearCycle yearCycle) {
    return new FixedWeekdayInMonth() {

      @Override
      public DayOfWeek weekday() {
        return dayOfWeek;
      }

      @Override
      public Month month() {
        return month;
      }

      @Override
      public Occurrence which() {
        return occurrence;
      }

      @Override
      public String descriptionPropertiesKey() {
        return "";
      }

      @Override
      public HolidayType holidayType() {
        return PUBLIC_HOLIDAY;
      }

      @Override
      public Year validFrom() {
        return validFrom;
      }

      @Override
      public Year validTo() {
        return validTo;
      }

      @Override
      public YearCycle cycle() {
        return yearCycle;
      }
    };
  }
}
