package de.focus_shift.jollyday.core.parser.impl;

import de.focus_shift.jollyday.core.Holiday;
import de.focus_shift.jollyday.core.HolidayType;
import de.focus_shift.jollyday.core.spi.FixedHolidayConfiguration;
import de.focus_shift.jollyday.core.spi.FixedWeekdayBetweenFixedHolidayConfiguration;
import de.focus_shift.jollyday.core.spi.HolidayConfigurations;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.MonthDay;
import java.time.Year;
import java.util.List;

import static de.focus_shift.jollyday.core.HolidayType.PUBLIC_HOLIDAY;
import static de.focus_shift.jollyday.core.spi.Limited.YearCycle.EVERY_YEAR;
import static java.time.DayOfWeek.WEDNESDAY;
import static java.time.Month.JANUARY;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class FixedWeekdayBetweenFixedParserTest {

  @Mock
  private HolidayConfigurations holidays;

  @Nested
  class LimitedTests {

    @Test
    void ensureThatFixedWeekdayBetweenFixedAreLimitedAndIsValid() {

      final Year year = Year.of(2025);
      final FixedWeekdayBetweenFixedHolidayConfiguration fixedWeekdayBetweenFixed = getFixedWeekdayBetweenFixed(MonthDay.of(JANUARY, 6), MonthDay.of(JANUARY, 12), WEDNESDAY, year, year);

      final FixedWeekdayBetweenFixedParser sut = new FixedWeekdayBetweenFixedParser();
      when(holidays.fixedWeekdayBetweenFixed()).thenReturn(List.of(fixedWeekdayBetweenFixed));

      final List<Holiday> calculatedHoliday = sut.parse(year, holidays);
      assertThat(calculatedHoliday.get(0).getDate()).isEqualTo(LocalDate.of(2025, JANUARY, 8));
    }

    @Test
    void ensureThatFixedWeekdayBetweenFixedAreLimitedAndIsInvalid() {

      final FixedWeekdayBetweenFixedHolidayConfiguration fixedWeekdayBetweenFixed = getFixedWeekdayBetweenFixed(MonthDay.of(JANUARY, 6), MonthDay.of(JANUARY, 12), WEDNESDAY, Year.of(2023), Year.of(2023));

      final FixedWeekdayBetweenFixedParser sut = new FixedWeekdayBetweenFixedParser();
      when(holidays.fixedWeekdayBetweenFixed()).thenReturn(List.of(fixedWeekdayBetweenFixed));

      final List<Holiday> calculatedHoliday = sut.parse(Year.of(2022), holidays);
      assertThat(calculatedHoliday).isEmpty();
    }
  }

  private static FixedWeekdayBetweenFixedHolidayConfiguration getFixedWeekdayBetweenFixed(
    final MonthDay from,
    final MonthDay to,
    final DayOfWeek dayOfWeek,
    final Year validFrom,
    final Year validTo
  ) {
    return new FixedWeekdayBetweenFixedHolidayConfiguration() {

      private FixedHolidayConfiguration getFixed(final MonthDay monthDay) {
        return new FixedHolidayConfiguration() {
          @Override
          public MonthDay day() {
            return monthDay;
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
          public YearCycle cycle() {
            return EVERY_YEAR;
          }

          @Override
          public List<MovingCondition> conditions() {
            return List.of();
          }
        };
      }

      @Override
      public FixedHolidayConfiguration from() {
        return getFixed(from);
      }

      @Override
      public FixedHolidayConfiguration to() {
        return getFixed(to);
      }

      @Override
      public DayOfWeek weekday() {
        return dayOfWeek;
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
}
