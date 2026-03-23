package de.focus_shift.jollyday.core.parser.impl;

import de.focus_shift.jollyday.core.Holiday;
import de.focus_shift.jollyday.core.HolidayType;
import de.focus_shift.jollyday.core.spi.FixedHolidayConfiguration;
import de.focus_shift.jollyday.core.spi.FixedWeekdayRelativeToFixedHolidayConfiguration;
import de.focus_shift.jollyday.core.spi.HolidayConfigurations;
import de.focus_shift.jollyday.core.spi.Occurrence;
import de.focus_shift.jollyday.core.spi.Relation;
import org.jspecify.annotations.NonNull;
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
import java.util.Optional;

import static de.focus_shift.jollyday.core.HolidayType.PUBLIC_HOLIDAY;
import static de.focus_shift.jollyday.core.spi.Limited.YearCycle.EVERY_YEAR;
import static java.time.DayOfWeek.WEDNESDAY;
import static java.time.Month.JANUARY;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class FixedWeekdayRelativeToFixedParserTest {

  @Mock
  private HolidayConfigurations holidays;

  @Nested
  class LimitedTests {

    @Test
    void ensureThatFixedWeekdayRelativeToFixedAreLimitedAndIsValid() {

      final Year year = Year.of(2025);
      final FixedWeekdayRelativeToFixedHolidayConfiguration fixedWeekdayRelativeToFixed = getFixedWeekdayRelativeToFixed(MonthDay.of(JANUARY, 6), WEDNESDAY, Occurrence.LAST, Relation.BEFORE, year, year);

      final FixedWeekdayRelativeToFixedParser sut = new FixedWeekdayRelativeToFixedParser();
      when(holidays.fixedWeekdayRelativeToFixed()).thenReturn(List.of(fixedWeekdayRelativeToFixed));

      final List<Holiday> calculatedHoliday = sut.parse(year, holidays);
      assertThat(calculatedHoliday.get(0).getDate()).isEqualTo(LocalDate.of(2025, JANUARY, 1));
    }

    @Test
    void ensureThatFixedWeekdayRelativeToFixedAreLimitedAndIsInvalid() {

      final FixedWeekdayRelativeToFixedHolidayConfiguration fixedWeekdayRelativeToFixed = getFixedWeekdayRelativeToFixed(MonthDay.of(JANUARY, 6), WEDNESDAY, Occurrence.LAST, Relation.BEFORE, Year.of(2023), Year.of(2023));

      final FixedWeekdayRelativeToFixedParser sut = new FixedWeekdayRelativeToFixedParser();
      when(holidays.fixedWeekdayRelativeToFixed()).thenReturn(List.of(fixedWeekdayRelativeToFixed));

      final List<Holiday> calculatedHoliday = sut.parse(Year.of(2022), holidays);
      assertThat(calculatedHoliday).isEmpty();
    }
  }

  private static FixedWeekdayRelativeToFixedHolidayConfiguration getFixedWeekdayRelativeToFixed(
    final MonthDay fixed,
    final DayOfWeek dayOfWeek,
    final Occurrence occurrence,
    final Relation relation,
    final Year validFrom,
    final Year validTo
  ) {
    return new FixedWeekdayRelativeToFixedHolidayConfiguration() {

      private FixedHolidayConfiguration getFixed(final MonthDay monthDay) {
        return new FixedHolidayConfiguration() {
          @Override
          public @NonNull MonthDay day() {
            return monthDay;
          }

          @Override
          public @NonNull String descriptionPropertiesKey() {
            return "";
          }

          @Override
          public @NonNull HolidayType holidayType() {
            return null;
          }

          @Override
          public @NonNull Optional<Year> validFrom() {
            return Optional.empty();
          }

          @Override
          public @NonNull Optional<Year> validTo() {
            return Optional.empty();
          }

          @Override
          public @NonNull YearCycle cycle() {
            return EVERY_YEAR;
          }

          @Override
          public @NonNull List<MovingCondition> conditions() {
            return List.of();
          }
        };
      }

      @Override
      public @NonNull FixedHolidayConfiguration day() {
        return getFixed(fixed);
      }

      @Override
      public @NonNull Occurrence which() {
        return occurrence;
      }

      @Override
      public @NonNull DayOfWeek weekday() {
        return dayOfWeek;
      }

      @Override
      public @NonNull Relation when() {
        return relation;
      }

      @Override
      public @NonNull String descriptionPropertiesKey() {
        return "";
      }

      @Override
      public @NonNull HolidayType holidayType() {
        return PUBLIC_HOLIDAY;
      }

      @Override
      public @NonNull Optional<Year> validFrom() {
        return Optional.of(validFrom);
      }

      @Override
      public @NonNull Optional<Year> validTo() {
        return Optional.of(validTo);
      }

      @Override
      public @NonNull YearCycle cycle() {
        return EVERY_YEAR;
      }
    };
  }
}
