package de.focus_shift.jollyday.core.parser.impl;

import de.focus_shift.jollyday.core.Holiday;
import de.focus_shift.jollyday.core.HolidayType;
import de.focus_shift.jollyday.core.spi.HolidayConfigurations;
import de.focus_shift.jollyday.core.spi.IslamicHolidayConfiguration;
import de.focus_shift.jollyday.core.spi.Movable;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.Year;
import java.util.List;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static de.focus_shift.jollyday.core.spi.IslamicHolidayConfiguration.IslamicHolidayType.ID_UL_ADHA_2;
import static de.focus_shift.jollyday.core.spi.IslamicHolidayConfiguration.IslamicHolidayType.MAWLID_AN_NABI;
import static de.focus_shift.jollyday.core.spi.Limited.YearCycle.EVERY_YEAR;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class IslamicHolidayParserTest {

  @Mock
  private HolidayConfigurations holidays;

  @Nested
  @TestInstance(TestInstance.Lifecycle.PER_CLASS)
  class IslamicHolidayTypeTests {

    private Stream<Arguments> islamicHolidaysWithLocalDates() {
      final IslamicHolidayConfiguration.IslamicHolidayType[] islamicHolidayTypes = IslamicHolidayConfiguration.IslamicHolidayType.values();
      final String[] islamicHolidayDates = {
        "2022-07-30",
        "2022-08-08",
        "2022-10-08",
        "2022-02-28",
        "2022-03-18",
        "2022-04-02",
        "2022-04-28",
        "2022-05-01",
        "2022-05-02",
        "2022-05-03",
        "2022-05-04",
        "2022-07-08",
        "2022-07-09",
        "2022-07-10",
        "2022-07-11"
      };

      return IntStream.range(0, islamicHolidayTypes.length)
        .mapToObj(i -> Arguments.of(islamicHolidayTypes[i], islamicHolidayDates[i]));
    }

    @ParameterizedTest
    @MethodSource("islamicHolidaysWithLocalDates")
    void ensureThatAllIslamicHolidayTypesProvideAHoliday(final IslamicHolidayConfiguration.IslamicHolidayType type, final LocalDate expected) {

      final IslamicHolidayConfiguration islamicHoliday = getIslamicHoliday(type);

      final IslamicHolidayParser sut = new IslamicHolidayParser();
      when(holidays.islamicHolidays()).thenReturn(List.of(islamicHoliday));

      final List<Holiday> calculatedHoliday = sut.parse(Year.of(2022), holidays);
      assertThat(calculatedHoliday.get(0).getPropertiesKey()).isEqualTo(type.name());
      assertThat(calculatedHoliday.get(0).getDate()).isEqualTo(expected);
    }
  }

  @Nested
  class LimitedTests {

    @Test
    void ensureThatIslamicHolidaysAreLimitedAndIsValid() {

      final IslamicHolidayConfiguration islamicHoliday = getIslamicHoliday(MAWLID_AN_NABI, Year.of(2022), Year.of(2022));

      final IslamicHolidayParser sut = new IslamicHolidayParser();
      when(holidays.islamicHolidays()).thenReturn(List.of(islamicHoliday));

      final List<Holiday> calculatedHoliday = sut.parse(Year.of(2022), holidays);
      assertThat(calculatedHoliday.get(0).getPropertiesKey()).isEqualTo("MAWLID_AN_NABI");
    }

    @Test
    void ensureThatIslamicHolidaysAreLimitedAndIsInvalid() {

      final IslamicHolidayConfiguration islamicHoliday = getIslamicHoliday(MAWLID_AN_NABI, Year.of(2023), Year.of(2023));

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

      final IslamicHolidayConfiguration islamicHoliday = getIslamicHoliday(ID_UL_ADHA_2, movingCondition);

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

  private static IslamicHolidayConfiguration getIslamicHoliday(final IslamicHolidayConfiguration.IslamicHolidayType type) {
    return getIslamicHoliday(type, null, null, null);
  }

  private static IslamicHolidayConfiguration getIslamicHoliday(final IslamicHolidayConfiguration.IslamicHolidayType type, final Movable.MovingCondition movingCondition) {
    return getIslamicHoliday(type, movingCondition, null, null);
  }

  private static IslamicHolidayConfiguration getIslamicHoliday(final IslamicHolidayConfiguration.IslamicHolidayType type, final Year validFrom, final Year validTo) {
    return getIslamicHoliday(type, null, validFrom, validTo);
  }

  private static IslamicHolidayConfiguration getIslamicHoliday(
    final IslamicHolidayConfiguration.IslamicHolidayType type,
    final Movable.MovingCondition movingCondition,
    final Year validFrom,
    final Year validTo
  ) {
    return new IslamicHolidayConfiguration() {

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
        return EVERY_YEAR;
      }
    };
  }
}
