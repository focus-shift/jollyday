package de.focus_shift.jollyday.core.parser.impl;

import de.focus_shift.jollyday.core.Holiday;
import de.focus_shift.jollyday.core.HolidayType;
import de.focus_shift.jollyday.core.spi.Holidays;
import de.focus_shift.jollyday.core.spi.IslamicHoliday;
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
import java.util.List;

import static de.focus_shift.jollyday.core.spi.IslamicHoliday.IslamicHolidayType.ID_UL_ADHA_2;
import static de.focus_shift.jollyday.core.spi.IslamicHoliday.IslamicHolidayType.MAWLID_AN_NABI;
import static de.focus_shift.jollyday.core.spi.Limited.YearCycle.EVERY_YEAR;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class IslamicHolidayParserTest {

  @Mock
  private Holidays holidays;

  @Nested
  class DescribedTests {

    @ParameterizedTest
    @EnumSource(IslamicHoliday.IslamicHolidayType.class)
    void ensureThatAllIslamicHolidayTypesProvideAHoliday(final IslamicHoliday.IslamicHolidayType type) {

      final IslamicHoliday islamicHoliday = getIslamicHoliday(type);

      final IslamicHolidayParser sut = new IslamicHolidayParser();
      when(holidays.islamicHolidays()).thenReturn(List.of(islamicHoliday));

      final List<Holiday> calculatedHoliday = sut.parse(Year.of(2022), holidays);
      assertThat(calculatedHoliday.get(0).getPropertiesKey()).isEqualTo(type.name());
    }
  }

  @Nested
  class LimitedTests {

    @Test
    void ensureThatIslamicHolidaysAreLimitedAndIsValid() {

      final IslamicHoliday islamicHoliday = getIslamicHoliday(MAWLID_AN_NABI, Year.of(2022), Year.of(2022), EVERY_YEAR);

      final IslamicHolidayParser sut = new IslamicHolidayParser();
      when(holidays.islamicHolidays()).thenReturn(List.of(islamicHoliday));

      final List<Holiday> calculatedHoliday = sut.parse(Year.of(2022), holidays);
      assertThat(calculatedHoliday.get(0).getPropertiesKey()).isEqualTo("MAWLID_AN_NABI");
    }

    @Test
    void ensureThatIslamicHolidaysAreLimitedAndIsInvalid() {

      final IslamicHoliday islamicHoliday = getIslamicHoliday(MAWLID_AN_NABI, Year.of(2023), Year.of(2023), EVERY_YEAR);

      final IslamicHolidayParser sut = new IslamicHolidayParser();
      when(holidays.islamicHolidays()).thenReturn(List.of(islamicHoliday));

      final List<Holiday> calculatedHoliday = sut.parse(Year.of(2022), holidays);
      assertThat(calculatedHoliday).isEmpty();
    }
  }

  @Nested
  class MovableTests {
    @Test
    void ensureThatIslamicHolidaysAreMovable() {

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

      final IslamicHoliday islamicHoliday = getIslamicHoliday(ID_UL_ADHA_2, movingCondition);

      final IslamicHolidayParser sut = new IslamicHolidayParser();
      when(holidays.islamicHolidays()).thenReturn(List.of(islamicHoliday));

      // ID_UL_ADHA_2 will be on a sunday in 2022
      final List<Holiday> calculatedHoliday = sut.parse(Year.of(2022), holidays);
      assertThat(calculatedHoliday.get(0).getPropertiesKey()).isEqualTo("ID_UL_ADHA_2");
      assertThat(calculatedHoliday.get(0).getDate().getDayOfWeek()).isEqualTo(DayOfWeek.MONDAY);
      assertThat(calculatedHoliday.get(0).getActualDate().getDayOfWeek()).isEqualTo(DayOfWeek.SUNDAY);
      assertThat(calculatedHoliday.get(0).getObservedDate()).map(LocalDate::getDayOfWeek).hasValue(DayOfWeek.MONDAY);
    }
  }

  private static IslamicHoliday getIslamicHoliday(final IslamicHoliday.IslamicHolidayType type) {
    return getIslamicHoliday(type, null, null, null, EVERY_YEAR);
  }

  private static IslamicHoliday getIslamicHoliday(final IslamicHoliday.IslamicHolidayType type, final Movable.MovingCondition movingCondition) {
    return getIslamicHoliday(type, movingCondition, null, null, EVERY_YEAR);
  }

  private static IslamicHoliday getIslamicHoliday(final IslamicHoliday.IslamicHolidayType type, final Year validFrom, final Year validTo, final Limited.YearCycle yearCycle) {
    return getIslamicHoliday(type, null, validFrom, validTo, yearCycle);
  }

  private static IslamicHoliday getIslamicHoliday(final IslamicHoliday.IslamicHolidayType type,
                                                  final Movable.MovingCondition movingCondition,
                                                  final Year validFrom, final Year validTo, final Limited.YearCycle yearCycle) {
    return new IslamicHoliday() {

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
        return null;
      }

      @Override
      public IslamicHolidayType type() {
        return type;
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
