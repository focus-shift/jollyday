package de.focus_shift.jollyday.core.parser.impl;

import de.focus_shift.jollyday.core.Holiday;
import de.focus_shift.jollyday.core.HolidayType;
import de.focus_shift.jollyday.core.spi.FixedWeekdayInMonthHolidayConfiguration;
import de.focus_shift.jollyday.core.spi.HolidayConfigurations;
import de.focus_shift.jollyday.core.spi.Movable;
import de.focus_shift.jollyday.core.spi.Occurrence;
import org.jspecify.annotations.NonNull;
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
import java.util.Optional;

import static de.focus_shift.jollyday.core.HolidayType.PUBLIC_HOLIDAY;
import static de.focus_shift.jollyday.core.spi.Limited.YearCycle.EVERY_YEAR;
import static java.time.DayOfWeek.MONDAY;
import static java.time.DayOfWeek.WEDNESDAY;
import static java.time.Month.FEBRUARY;
import static java.time.Month.JANUARY;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class FixedWeekdayInMonthParserTest {

  @Mock
  private HolidayConfigurations holidays;

  @Nested
  class LimitedTests {

    @Test
    void ensureThatFixedWeekdayInMonthAreLimitedAndIsValid() {

      final Year year = Year.of(2025);
      final FixedWeekdayInMonthHolidayConfiguration fixedWeekdayInMonth = getFixedWeekdayInMonth(WEDNESDAY, JANUARY, Occurrence.LAST, year, year);

      final FixedWeekdayInMonthParser sut = new FixedWeekdayInMonthParser();
      when(holidays.fixedWeekdays()).thenReturn(List.of(fixedWeekdayInMonth));

      final List<Holiday> calculatedHoliday = sut.parse(year, holidays);
      assertThat(calculatedHoliday.get(0).getDate()).isEqualTo(LocalDate.of(2025, JANUARY, 29));
    }

    @Test
    void ensureThatFixedWeekdayInMonthAreLimitedAndIsInvalid() {

      final FixedWeekdayInMonthHolidayConfiguration fixedWeekdayInMonth = getFixedWeekdayInMonth(WEDNESDAY, JANUARY, Occurrence.LAST, Year.of(2023), Year.of(2023));

      final FixedWeekdayInMonthParser sut = new FixedWeekdayInMonthParser();
      when(holidays.fixedWeekdays()).thenReturn(List.of(fixedWeekdayInMonth));

      final List<Holiday> calculatedHoliday = sut.parse(Year.of(2022), holidays);
      assertThat(calculatedHoliday).isEmpty();
    }
  }

  @Nested
  class MovableTests {

    @Test
    void ensureThatFixedWeekdayInMonthAreMovable() {

      final Movable.MovingCondition movingCondition = new Movable.MovingCondition() {
        @Override
        public @NonNull DayOfWeek substitute() {
          return WEDNESDAY;
        }

        @Override
        public @NonNull With with() {
          return With.NEXT;
        }

        @Override
        public @NonNull DayOfWeek weekday() {
          return MONDAY;
        }
      };

      final Year year = Year.of(2025);
      final FixedWeekdayInMonthHolidayConfiguration fixedWeekdayInMonth = getFixedWeekdayInMonth(WEDNESDAY, JANUARY, Occurrence.LAST, year, year, movingCondition);

      final FixedWeekdayInMonthParser sut = new FixedWeekdayInMonthParser();
      when(holidays.fixedWeekdays()).thenReturn(List.of(fixedWeekdayInMonth));

      final List<Holiday> calculatedHoliday = sut.parse(Year.of(2025), holidays);
      assertThat(calculatedHoliday.get(0).getDate()).isEqualTo(LocalDate.of(2025, FEBRUARY, 3));
      assertThat(calculatedHoliday.get(0).getDate().getDayOfWeek()).isEqualTo(MONDAY);
      assertThat(calculatedHoliday.get(0).getActualDate()).isEqualTo(LocalDate.of(2025, JANUARY, 29));
      assertThat(calculatedHoliday.get(0).getActualDate().getDayOfWeek()).isEqualTo(WEDNESDAY);
      assertThat(calculatedHoliday.get(0).getObservedDate()).hasValue(LocalDate.of(2025, FEBRUARY, 3));
      assertThat(calculatedHoliday.get(0).getObservedDate()).map(LocalDate::getDayOfWeek).hasValue(MONDAY);
    }
  }

  @Nested
  class AvoidHolyWeekTests {

    @Test
    void ensureThatFirstThursdayOfAprilMovesToSecondThursdayWhenWithinHolyWeek() {

      // Easter Sunday 2026 is April 5, 2026; the first Thursday of April 2026 (April 2) falls
      // within Holy Week (March 29 - April 5), so the holiday must move to April 9, 2026.
      final Year year = Year.of(2026);
      final FixedWeekdayInMonthHolidayConfiguration fixedWeekdayInMonth =
        getFixedWeekdayInMonthAvoidingHolyWeek(DayOfWeek.THURSDAY, Month.APRIL, Occurrence.FIRST, year, year);

      final FixedWeekdayInMonthParser sut = new FixedWeekdayInMonthParser();
      when(holidays.fixedWeekdays()).thenReturn(List.of(fixedWeekdayInMonth));

      final List<Holiday> calculatedHoliday = sut.parse(year, holidays);
      assertThat(calculatedHoliday.get(0).getDate()).isEqualTo(LocalDate.of(2026, Month.APRIL, 9));
    }

    @Test
    void ensureThatFirstThursdayOfAprilStaysUnchangedWhenNotWithinHolyWeek() {

      // Easter Sunday 2027 is March 28, 2027; the first Thursday of April 2027 (April 1) is not
      // within Holy Week (March 21 - March 28), so the holiday stays on April 1, 2027.
      final Year year = Year.of(2027);
      final FixedWeekdayInMonthHolidayConfiguration fixedWeekdayInMonth =
        getFixedWeekdayInMonthAvoidingHolyWeek(DayOfWeek.THURSDAY, Month.APRIL, Occurrence.FIRST, year, year);

      final FixedWeekdayInMonthParser sut = new FixedWeekdayInMonthParser();
      when(holidays.fixedWeekdays()).thenReturn(List.of(fixedWeekdayInMonth));

      final List<Holiday> calculatedHoliday = sut.parse(year, holidays);
      assertThat(calculatedHoliday.get(0).getDate()).isEqualTo(LocalDate.of(2027, Month.APRIL, 1));
    }
  }

  private static FixedWeekdayInMonthHolidayConfiguration getFixedWeekdayInMonth(
    final DayOfWeek dayOfWeek,
    final Month month,
    final Occurrence occurrence,
    final Year validFrom,
    final Year validTo
  ) {
    return getFixedWeekdayInMonth(dayOfWeek, month, occurrence, validFrom, validTo, null);
  }

  private static FixedWeekdayInMonthHolidayConfiguration getFixedWeekdayInMonth(
    final DayOfWeek dayOfWeek,
    final Month month,
    final Occurrence occurrence,
    final Year validFrom,
    final Year validTo,
    final Movable.MovingCondition movingCondition
  ) {
    return new FixedWeekdayInMonthHolidayConfiguration() {

      @Override
      public @NonNull List<MovingCondition> conditions() {
        return movingCondition == null ? List.of() : List.of(movingCondition);
      }

      @Override
      public @NonNull DayOfWeek weekday() {
        return dayOfWeek;
      }

      @Override
      public @NonNull Month month() {
        return month;
      }

      @Override
      public @NonNull Occurrence which() {
        return occurrence;
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

  private static FixedWeekdayInMonthHolidayConfiguration getFixedWeekdayInMonthAvoidingHolyWeek(
    final DayOfWeek dayOfWeek,
    final Month month,
    final Occurrence occurrence,
    final Year validFrom,
    final Year validTo
  ) {
    final FixedWeekdayInMonthHolidayConfiguration delegate =
      getFixedWeekdayInMonth(dayOfWeek, month, occurrence, validFrom, validTo, null);

    return new FixedWeekdayInMonthHolidayConfiguration() {

      @Override
      public @NonNull List<MovingCondition> conditions() {
        return delegate.conditions();
      }

      @Override
      public @NonNull DayOfWeek weekday() {
        return delegate.weekday();
      }

      @Override
      public @NonNull Month month() {
        return delegate.month();
      }

      @Override
      public @NonNull Occurrence which() {
        return delegate.which();
      }

      @Override
      public @NonNull String descriptionPropertiesKey() {
        return delegate.descriptionPropertiesKey();
      }

      @Override
      public @NonNull HolidayType holidayType() {
        return delegate.holidayType();
      }

      @Override
      public @NonNull Optional<Year> validFrom() {
        return delegate.validFrom();
      }

      @Override
      public @NonNull Optional<Year> validTo() {
        return delegate.validTo();
      }

      @Override
      public @NonNull YearCycle cycle() {
        return delegate.cycle();
      }

      @Override
      public boolean avoidHolyWeek() {
        return true;
      }
    };
  }
}
