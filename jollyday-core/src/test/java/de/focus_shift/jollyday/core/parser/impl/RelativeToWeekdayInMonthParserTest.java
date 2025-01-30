package de.focus_shift.jollyday.core.parser.impl;

import de.focus_shift.jollyday.core.Holiday;
import de.focus_shift.jollyday.core.HolidayType;
import de.focus_shift.jollyday.core.spi.FixedWeekdayInMonth;
import de.focus_shift.jollyday.core.spi.Holidays;
import de.focus_shift.jollyday.core.spi.Limited;
import de.focus_shift.jollyday.core.spi.Occurrence;
import de.focus_shift.jollyday.core.spi.Relation;
import de.focus_shift.jollyday.core.spi.RelativeToWeekdayInMonth;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.Month;
import java.time.Year;
import java.util.List;

import static de.focus_shift.jollyday.core.HolidayType.PUBLIC_HOLIDAY;
import static de.focus_shift.jollyday.core.spi.Limited.YearCycle.EVERY_YEAR;
import static de.focus_shift.jollyday.core.spi.Occurrence.LAST;
import static java.time.DayOfWeek.MONDAY;
import static java.time.DayOfWeek.TUESDAY;
import static java.time.DayOfWeek.WEDNESDAY;
import static java.time.Month.APRIL;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class RelativeToWeekdayInMonthParserTest {

  @Mock
  private Holidays holidays;

  @ParameterizedTest
  @CsvSource({"BEFORE,2025-04-23", "AFTER,2025-04-30"})
  void ensureThatRelativeToWeekdayInMonthWithRelationBeforeIsValid(final Relation relation, final LocalDate expectedLocalDate) {

    final Year year = Year.of(2025);
    final FixedWeekdayInMonth fixedWeekdayInMonth = getFixedWeekdayInMonth(APRIL, MONDAY, LAST);
    final RelativeToWeekdayInMonth relativeToWeekdayInMonth = getRelativeToWeekdayInMonth(WEDNESDAY, relation, fixedWeekdayInMonth, year, year);

    final RelativeToWeekdayInMonthParser sut = new RelativeToWeekdayInMonthParser();
    when(holidays.relativeToWeekdayInMonth()).thenReturn(List.of(relativeToWeekdayInMonth));

    final List<Holiday> calculatedHoliday = sut.parse(year, holidays);
    assertThat(calculatedHoliday.get(0).getDate()).isEqualTo(expectedLocalDate);
  }

  @Nested
  class LimitedTests {

    @Test
    void ensureThatRelativeToWeekdayInMonthAreLimitedAndIsValid() {

      final Year year = Year.of(2025);
      final FixedWeekdayInMonth fixedWeekdayInMonth = getFixedWeekdayInMonth(APRIL, MONDAY, LAST);
      final RelativeToWeekdayInMonth relativeToWeekdayInMonth = getRelativeToWeekdayInMonth(WEDNESDAY, Relation.BEFORE, fixedWeekdayInMonth, year, year);

      final RelativeToWeekdayInMonthParser sut = new RelativeToWeekdayInMonthParser();
      when(holidays.relativeToWeekdayInMonth()).thenReturn(List.of(relativeToWeekdayInMonth));

      final List<Holiday> calculatedHoliday = sut.parse(year, holidays);
      assertThat(calculatedHoliday.get(0).getDate()).isEqualTo(LocalDate.of(2025, APRIL, 23));
    }

    @Test
    void ensureThatRelativeToWeekdayInMonthAreLimitedAndIsInvalid() {

      final FixedWeekdayInMonth fixedWeekdayInMonth = getFixedWeekdayInMonth(APRIL, MONDAY, LAST);
      final RelativeToWeekdayInMonth relativeToWeekdayInMonth = getRelativeToWeekdayInMonth(TUESDAY, Relation.BEFORE, fixedWeekdayInMonth, Year.of(2023), Year.of(2023));

      final RelativeToWeekdayInMonthParser sut = new RelativeToWeekdayInMonthParser();
      when(holidays.relativeToWeekdayInMonth()).thenReturn(List.of(relativeToWeekdayInMonth));

      final List<Holiday> calculatedHoliday = sut.parse(Year.of(2022), holidays);
      assertThat(calculatedHoliday).isEmpty();
    }
  }

  private static RelativeToWeekdayInMonth getRelativeToWeekdayInMonth(
    final DayOfWeek dayOfWeek,
    final Relation relation,
    final FixedWeekdayInMonth fixedWeekdayInMonth,
    final Year validFrom,
    final Year validTo
  ) {
    return new RelativeToWeekdayInMonth() {

      @Override
      public DayOfWeek weekday() {
        return dayOfWeek;
      }

      @Override
      public Relation when() {
        return relation;
      }

      @Override
      public FixedWeekdayInMonth weekdayInMonth() {
        return fixedWeekdayInMonth;
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
        return EVERY_YEAR;
      }
    };
  }

  private static FixedWeekdayInMonth getFixedWeekdayInMonth(
    final Month month,
    final DayOfWeek dayOfWeek,
    final Occurrence occurrence
  ) {
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
        return null;
      }

      @Override
      public Year validFrom() {
        return null;
      }

      @Override
      public Year validTo() {
        return null;
      }

      @Override
      public Limited.YearCycle cycle() {
        return null;
      }
    };
  }
}
