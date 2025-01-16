package de.focus_shift.jollyday.core.parser.impl;

import de.focus_shift.jollyday.core.Holiday;
import de.focus_shift.jollyday.core.HolidayType;
import de.focus_shift.jollyday.core.spi.ChristianHoliday;
import de.focus_shift.jollyday.core.spi.Holidays;
import de.focus_shift.jollyday.core.spi.Limited;
import de.focus_shift.jollyday.core.spi.Movable;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.Year;
import java.time.chrono.Chronology;
import java.util.List;

import static de.focus_shift.jollyday.core.HolidayType.PUBLIC_HOLIDAY;
import static de.focus_shift.jollyday.core.spi.ChristianHoliday.ChristianHolidayType.EASTER;
import static de.focus_shift.jollyday.core.spi.ChristianHoliday.ChristianHolidayType.GOOD_FRIDAY;
import static de.focus_shift.jollyday.core.spi.Limited.YearCycle.EVERY_YEAR;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ChristianHolidayParserTest {

  @Mock
  private Holidays holidays;

  @Nested
  class ChristianHolidayTypeTests {

    @ParameterizedTest
    @EnumSource(ChristianHoliday.ChristianHolidayType.class)
    void ensureThatAllChristianHolidayTypesProvideAHoliday(final ChristianHoliday.ChristianHolidayType type) {

      final ChristianHoliday christianHoliday = getChristianHoliday(type);

      final ChristianHolidayParser sut = new ChristianHolidayParser();
      when(holidays.christianHolidays()).thenReturn(List.of(christianHoliday));

      final List<Holiday> calculatedHoliday = sut.parse(Year.of(2022), holidays);
      assertThat(calculatedHoliday.get(0).getPropertiesKey()).isEqualTo(type.name());
    }
  }

  @Nested
  class LimitedTests {

    @Test
    void ensureThatChristianHolidaysAreLimitedAndIsValid() {

      final ChristianHoliday christianHoliday = getChristianHoliday(GOOD_FRIDAY, Year.of(2022), Year.of(2022), EVERY_YEAR);

      final ChristianHolidayParser sut = new ChristianHolidayParser();
      when(holidays.christianHolidays()).thenReturn(List.of(christianHoliday));

      final List<Holiday> calculatedHoliday = sut.parse(Year.of(2022), holidays);
      assertThat(calculatedHoliday.get(0).getPropertiesKey()).isEqualTo("GOOD_FRIDAY");
    }

    @Test
    void ensureThatChristianHolidaysAreLimitedAndIsInvalid() {

      final ChristianHoliday christianHoliday = getChristianHoliday(GOOD_FRIDAY, Year.of(2023), Year.of(2023), EVERY_YEAR);

      final ChristianHolidayParser sut = new ChristianHolidayParser();
      when(holidays.christianHolidays()).thenReturn(List.of(christianHoliday));

      final List<Holiday> calculatedHoliday = sut.parse(Year.of(2022), holidays);
      assertThat(calculatedHoliday).isEmpty();
    }
  }

  @Nested
  class MovableTests {

    @Test
    void ensureThatChristianHolidaysAreMovable() {

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

      final ChristianHoliday christianHoliday = getChristianHoliday(EASTER, movingCondition);

      final ChristianHolidayParser sut = new ChristianHolidayParser();
      when(holidays.christianHolidays()).thenReturn(List.of(christianHoliday));

      final List<Holiday> calculatedHoliday = sut.parse(Year.of(2022), holidays);
      assertThat(calculatedHoliday.get(0).getPropertiesKey()).isEqualTo("EASTER");
      assertThat(calculatedHoliday.get(0).getDate().getDayOfWeek()).isEqualTo(DayOfWeek.MONDAY);
      assertThat(calculatedHoliday.get(0).getActualDate().getDayOfWeek()).isEqualTo(DayOfWeek.SUNDAY);
      assertThat(calculatedHoliday.get(0).getObservedDate()).map(LocalDate::getDayOfWeek).hasValue(DayOfWeek.MONDAY);
    }
  }

  private static ChristianHoliday getChristianHoliday(final ChristianHoliday.ChristianHolidayType type) {
    return getChristianHoliday(type, null, null, null, EVERY_YEAR);
  }

  private static ChristianHoliday getChristianHoliday(final ChristianHoliday.ChristianHolidayType type, final Movable.MovingCondition movingCondition) {
    return getChristianHoliday(type, movingCondition, null, null, EVERY_YEAR);
  }

  private static ChristianHoliday getChristianHoliday(final ChristianHoliday.ChristianHolidayType type, final Year validFrom, final Year validTo, final Limited.YearCycle yearCycle) {
    return getChristianHoliday(type, null, validFrom, validTo, yearCycle);
  }

  private static ChristianHoliday getChristianHoliday(final ChristianHoliday.ChristianHolidayType type,
                                                      final Movable.MovingCondition movingCondition,
                                                      final Year validFrom, final Year validTo, final Limited.YearCycle yearCycle) {
    return new ChristianHoliday() {

      @Override
      public List<MovingCondition> conditions() {
        return movingCondition == null ? List.of() : List.of(movingCondition);
      }

      @Override
      public String descriptionPropertiesKey() {
        return type.name();
      }

      @Override
      public HolidayType holidayType() {
        return PUBLIC_HOLIDAY;
      }

      @Override
      public ChristianHolidayType type() {
        return type;
      }

      @Override
      public Chronology chronology() {
        return null;
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
