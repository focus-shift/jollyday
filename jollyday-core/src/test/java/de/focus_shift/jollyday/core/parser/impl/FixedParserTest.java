package de.focus_shift.jollyday.core.parser.impl;

import de.focus_shift.jollyday.core.Holiday;
import de.focus_shift.jollyday.core.HolidayType;
import de.focus_shift.jollyday.core.spi.Fixed;
import de.focus_shift.jollyday.core.spi.Holidays;
import de.focus_shift.jollyday.core.spi.Limited;
import de.focus_shift.jollyday.core.spi.Movable;
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
import static java.time.Month.JANUARY;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class FixedParserTest {

  @Mock
  private Holidays holidays;

  @Nested
  class LimitedTests {

    @Test
    void ensureThatFixedAreLimitedAndIsValid() {

      final Fixed fixed = getFixed(MonthDay.of(JANUARY, 5), Year.of(2022), Year.of(2022), EVERY_YEAR);

      final FixedParser sut = new FixedParser();
      when(holidays.fixed()).thenReturn(List.of(fixed));

      final List<Holiday> calculatedHoliday = sut.parse(Year.of(2022), holidays);
      assertThat(calculatedHoliday.get(0).getDate()).isEqualTo(LocalDate.of(2022, JANUARY, 5));
    }

    @Test
    void ensureThatFixedAreLimitedAndIsInvalid() {

      final Fixed fixed = getFixed(MonthDay.of(JANUARY, 5), Year.of(2023), Year.of(2023), EVERY_YEAR);

      final FixedParser sut = new FixedParser();
      when(holidays.fixed()).thenReturn(List.of(fixed));

      final List<Holiday> calculatedHoliday = sut.parse(Year.of(2022), holidays);
      assertThat(calculatedHoliday).isEmpty();
    }
  }

  @Nested
  class MovableTests {

    @Test
    void ensureThatFixedAreMovable() {

      final Movable.MovingCondition movingCondition = new Movable.MovingCondition() {
        @Override
        public DayOfWeek substitute() {
          return DayOfWeek.SUNDAY;
        }

        @Override
        public With with() {
          return With.NEXT;
        }

        @Override
        public DayOfWeek weekday() {
          return DayOfWeek.MONDAY;
        }
      };

      final Fixed fixed = getFixed(MonthDay.of(JANUARY, 5), movingCondition);

      final FixedParser sut = new FixedParser();
      when(holidays.fixed()).thenReturn(List.of(fixed));

      final List<Holiday> calculatedHoliday = sut.parse(Year.of(2025), holidays);
      assertThat(calculatedHoliday.get(0).getDate()).isEqualTo(LocalDate.of(2025, JANUARY, 6));
      assertThat(calculatedHoliday.get(0).getDate().getDayOfWeek()).isEqualTo(DayOfWeek.MONDAY);
      assertThat(calculatedHoliday.get(0).getActualDate()).isEqualTo(LocalDate.of(2025, JANUARY, 5));
      assertThat(calculatedHoliday.get(0).getActualDate().getDayOfWeek()).isEqualTo(DayOfWeek.SUNDAY);
      assertThat(calculatedHoliday.get(0).getObservedDate()).hasValue(LocalDate.of(2025, JANUARY, 6));
      assertThat(calculatedHoliday.get(0).getObservedDate()).map(LocalDate::getDayOfWeek).hasValue(DayOfWeek.MONDAY);
    }
  }

  private static Fixed getFixed(final MonthDay day, final Movable.MovingCondition movingCondition) {
    return getFixed(day, movingCondition, null, null, EVERY_YEAR);
  }

  private static Fixed getFixed(final MonthDay day, final Year validFrom, final Year validTo, final Limited.YearCycle yearCycle) {
    return getFixed(day, null, validFrom, validTo, yearCycle);
  }

  private static Fixed getFixed(
    final MonthDay day,
    final Movable.MovingCondition movingCondition,
    final Year validFrom,
    final Year validTo,
    final Limited.YearCycle yearCycle
  ) {
    return new Fixed() {

      @Override
      public MonthDay day() {
        return day;
      }

      @Override
      public List<MovingCondition> conditions() {
        return movingCondition == null ? List.of() : List.of(movingCondition);
      }

      @Override
      public String descriptionPropertiesKey() {
        return day.toString();
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
