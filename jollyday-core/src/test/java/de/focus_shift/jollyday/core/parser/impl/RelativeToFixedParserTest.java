package de.focus_shift.jollyday.core.parser.impl;

import de.focus_shift.jollyday.core.Holiday;
import de.focus_shift.jollyday.core.HolidayType;
import de.focus_shift.jollyday.core.spi.Fixed;
import de.focus_shift.jollyday.core.spi.Holidays;
import de.focus_shift.jollyday.core.spi.Limited;
import de.focus_shift.jollyday.core.spi.Relation;
import de.focus_shift.jollyday.core.spi.RelativeToFixed;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.threeten.extra.Days;

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
class RelativeToFixedParserTest {

  @Mock
  private Holidays holidays;

  @Nested
  class LimitedTests {

    @Test
    void ensureThatRelativeToFixedAreLimitedAndIsValidWithWeekday() {

      final Year year = Year.of(2025);
      final RelativeToFixed relativeToFixed = getRelativeToFixed(MonthDay.of(JANUARY, 6), WEDNESDAY, null, Relation.BEFORE, year, year, EVERY_YEAR);

      final RelativeToFixedParser sut = new RelativeToFixedParser();
      when(holidays.relativeToFixed()).thenReturn(List.of(relativeToFixed));

      final List<Holiday> calculatedHoliday = sut.parse(year, holidays);
      assertThat(calculatedHoliday.get(0).getDate()).isEqualTo(LocalDate.of(2025, JANUARY, 1));
    }

    @Test
    void ensureThatRelativeToFixedAreLimitedAndIsValidWithDays() {

      final Year year = Year.of(2025);
      final RelativeToFixed relativeToFixed = getRelativeToFixed(MonthDay.of(JANUARY, 6), null, Days.of(2), Relation.BEFORE, year, year, EVERY_YEAR);

      final RelativeToFixedParser sut = new RelativeToFixedParser();
      when(holidays.relativeToFixed()).thenReturn(List.of(relativeToFixed));

      final List<Holiday> calculatedHoliday = sut.parse(year, holidays);
      assertThat(calculatedHoliday.get(0).getDate()).isEqualTo(LocalDate.of(2025, JANUARY, 4));
    }

    @Test
    void ensureThatRelativeToFixedAreLimitedAndIsInvalid() {

      final RelativeToFixed relativeToFixed = getRelativeToFixed(MonthDay.of(JANUARY, 6), WEDNESDAY, Days.of(2), Relation.BEFORE, Year.of(2023), Year.of(2023), EVERY_YEAR);

      final RelativeToFixedParser sut = new RelativeToFixedParser();
      when(holidays.relativeToFixed()).thenReturn(List.of(relativeToFixed));

      final List<Holiday> calculatedHoliday = sut.parse(Year.of(2022), holidays);
      assertThat(calculatedHoliday).isEmpty();
    }
  }

  private static RelativeToFixed getRelativeToFixed(
    final MonthDay monthDay,
    final DayOfWeek dayOfWeek,
    final Days days,
    final Relation relation,
    final Year validFrom,
    final Year validTo,
    final Limited.YearCycle yearCycle
  ) {
    return new RelativeToFixed() {

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
      public DayOfWeek weekday() {
        return dayOfWeek;
      }

      @Override
      public Days days() {
        return days;
      }

      @Override
      public Relation when() {
        return relation;
      }

      @Override
      public Fixed date() {
        return getFixed(monthDay);
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
