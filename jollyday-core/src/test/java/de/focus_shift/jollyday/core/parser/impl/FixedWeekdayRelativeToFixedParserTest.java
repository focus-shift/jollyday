package de.focus_shift.jollyday.core.parser.impl;

import de.focus_shift.jollyday.core.Holiday;
import de.focus_shift.jollyday.core.HolidayType;
import de.focus_shift.jollyday.core.spi.Fixed;
import de.focus_shift.jollyday.core.spi.FixedWeekdayRelativeToFixed;
import de.focus_shift.jollyday.core.spi.Holidays;
import de.focus_shift.jollyday.core.spi.Limited;
import de.focus_shift.jollyday.core.spi.Occurrence;
import de.focus_shift.jollyday.core.spi.Relation;
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
class FixedWeekdayRelativeToFixedParserTest {

  @Mock
  private Holidays holidays;

  @Nested
  class LimitedTests {

    @Test
    void ensureThatFixedWeekdayRelativeToFixedAreLimitedAndIsValid() {

      final Year year = Year.of(2025);
      final FixedWeekdayRelativeToFixed fixedWeekdayRelativeToFixed = getFixedWeekdayRelativeToFixed(MonthDay.of(JANUARY, 6), WEDNESDAY, Occurrence.LAST, Relation.BEFORE, year, year, EVERY_YEAR);

      final FixedWeekdayRelativeToFixedParser sut = new FixedWeekdayRelativeToFixedParser();
      when(holidays.fixedWeekdayRelativeToFixed()).thenReturn(List.of(fixedWeekdayRelativeToFixed));

      final List<Holiday> calculatedHoliday = sut.parse(year, holidays);
      assertThat(calculatedHoliday.get(0).getDate()).isEqualTo(LocalDate.of(2025, JANUARY, 1));
    }

    @Test
    void ensureThatFixedWeekdayRelativeToFixedAreLimitedAndIsInvalid() {

      final FixedWeekdayRelativeToFixed fixedWeekdayRelativeToFixed = getFixedWeekdayRelativeToFixed(MonthDay.of(JANUARY, 6), WEDNESDAY, Occurrence.LAST, Relation.BEFORE, Year.of(2023), Year.of(2023), EVERY_YEAR);

      final FixedWeekdayRelativeToFixedParser sut = new FixedWeekdayRelativeToFixedParser();
      when(holidays.fixedWeekdayRelativeToFixed()).thenReturn(List.of(fixedWeekdayRelativeToFixed));

      final List<Holiday> calculatedHoliday = sut.parse(Year.of(2022), holidays);
      assertThat(calculatedHoliday).isEmpty();
    }
  }

  private static FixedWeekdayRelativeToFixed getFixedWeekdayRelativeToFixed(final MonthDay fixed, final DayOfWeek dayOfWeek, final Occurrence occurrence, final Relation relation,
                                                                      final Year validFrom, final Year validTo, final Limited.YearCycle yearCycle) {
    return new FixedWeekdayRelativeToFixed() {

      private Fixed getFixed(final MonthDay monthDay) {
        return new Fixed() {
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
      public Fixed day() {
        return getFixed(fixed);
      }

      @Override
      public Occurrence which() {
        return occurrence;
      }

      @Override
      public DayOfWeek weekday() {
        return dayOfWeek;
      }

      @Override
      public Relation when() {
        return relation;
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
