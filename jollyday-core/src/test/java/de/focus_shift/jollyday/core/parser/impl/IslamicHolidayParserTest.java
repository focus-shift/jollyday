package de.focus_shift.jollyday.core.parser.impl;

import de.focus_shift.jollyday.core.Holiday;
import de.focus_shift.jollyday.core.HolidayType;
import de.focus_shift.jollyday.core.spi.HolidayConfigurations;
import de.focus_shift.jollyday.core.spi.IslamicHolidayConfiguration;
import de.focus_shift.jollyday.core.spi.IslamicHolidayConfiguration.IslamicHolidayType;
import de.focus_shift.jollyday.core.spi.Movable;
import org.jspecify.annotations.NonNull;
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
import java.util.Optional;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static de.focus_shift.jollyday.core.spi.IslamicHolidayConfiguration.IslamicHolidayType.ID_UL_ADHA_2;
import static de.focus_shift.jollyday.core.spi.IslamicHolidayConfiguration.IslamicHolidayType.JUMUATUL_WIDA;
import static de.focus_shift.jollyday.core.spi.IslamicHolidayConfiguration.IslamicHolidayType.MAWLID_AN_NABI;
import static de.focus_shift.jollyday.core.spi.IslamicHolidayConfiguration.IslamicHolidayType.RAMADAN_END;
import static de.focus_shift.jollyday.core.spi.Limited.YearCycle.EVERY_YEAR;
import static java.time.DayOfWeek.FRIDAY;
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
         "2022-07-30",  // NEWYEAR
         "2022-08-08",  // ASCHURA
         "2022-10-08",  // MAWLID_AN_NABI
         "2022-02-28",  // LAILAT_AL_MIRAJ
         "2022-03-18",  // LAILAT_AL_BARAT
         "2022-04-02",  // RAMADAN
         "2022-04-29",  // JUMUATUL_WIDA (Friday before Ramadan ends)
         "2022-04-28",  // LAILAT_AL_QADR
         "2022-05-01",  // RAMADAN_END
         "2022-05-02",  // ID_AL_FITR
         "2022-05-03",  // ID_AL_FITR_2
         "2022-05-04",  // ID_AL_FITR_3
         "2022-07-08",  // ARAFAAT
         "2022-07-09",  // ID_UL_ADHA
         "2022-07-10",  // ID_UL_ADHA_2
         "2022-07-11"   // ID_UL_ADHA_3
       };

       return IntStream.range(0, islamicHolidayTypes.length)
         .mapToObj(i -> Arguments.of(islamicHolidayTypes[i], islamicHolidayDates[i]));
     }

    @ParameterizedTest
    @MethodSource("islamicHolidaysWithLocalDates")
    void ensureThatAllIslamicHolidayTypesProvideAHoliday(final IslamicHolidayType type, final LocalDate expected) {

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
        public @NonNull DayOfWeek substitute() {
          return DayOfWeek.SUNDAY;
        }

        @Override
        public @NonNull With with() {
          return With.NEXT;
        }

        @Override
        public @NonNull DayOfWeek weekday() {
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

  @Nested
  class JuumatulWidaTests {

    @Test
    void ensureThatJumatulWidaIsAdjustedToPreviousFriday() {
      // JUMUATUL_WIDA is calculated from Ramadan end (10/1 Hijrah) minus 1 day
      // then adjusted to the previous or same Friday
      // In 2022, Ramadan ends on May 1st (Sunday), so JUMUATUL_WIDA should be April 29th (Friday)

      final IslamicHolidayConfiguration islamicHoliday = getIslamicHoliday(JUMUATUL_WIDA);

      final IslamicHolidayParser sut = new IslamicHolidayParser();
      when(holidays.islamicHolidays()).thenReturn(List.of(islamicHoliday));

      final List<Holiday> calculatedHoliday = sut.parse(Year.of(2022), holidays);
      assertThat(calculatedHoliday).isNotEmpty();
      assertThat(calculatedHoliday.get(0).getPropertiesKey()).isEqualTo("JUMUATUL_WIDA");
      assertThat(calculatedHoliday.get(0).getDate()).isEqualTo(LocalDate.of(2022, 4, 29));
      assertThat(calculatedHoliday.get(0).getDate().getDayOfWeek()).isEqualTo(FRIDAY);
    }

    @Test
    void ensureThatJumuatulWidaStaysOnFridayIfAlreadyFriday() {
      // In 2028, Ramadan ends is on a Friday and therefore jumuatul wida stays on that day

      final IslamicHolidayConfiguration ramadanEnd = getIslamicHoliday(RAMADAN_END);
      final IslamicHolidayConfiguration jumuatulWida = getIslamicHoliday(JUMUATUL_WIDA);

      final IslamicHolidayParser sut = new IslamicHolidayParser();
      when(holidays.islamicHolidays()).thenReturn(List.of(jumuatulWida, ramadanEnd));

      final List<Holiday> calculatedHoliday = sut.parse(Year.of(2028), holidays);
      assertThat(calculatedHoliday).isNotEmpty();
      assertThat(calculatedHoliday.get(0).getPropertiesKey()).isEqualTo("JUMUATUL_WIDA");
      assertThat(calculatedHoliday.get(0).getDate().getDayOfWeek()).isEqualTo(FRIDAY);
      assertThat(calculatedHoliday.get(1).getPropertiesKey()).isEqualTo("RAMADAN_END");
      assertThat(calculatedHoliday.get(1).getDate().getDayOfWeek()).isEqualTo(FRIDAY);
      assertThat(calculatedHoliday.get(0).getDate()).isEqualTo(calculatedHoliday.get(1).getDate());

    }
  }

  private static IslamicHolidayConfiguration getIslamicHoliday(final IslamicHolidayType type) {
    return getIslamicHoliday(type, null, null, null);
  }

  private static IslamicHolidayConfiguration getIslamicHoliday(final IslamicHolidayType type, final Movable.MovingCondition movingCondition) {
    return getIslamicHoliday(type, movingCondition, null, null);
  }

  private static IslamicHolidayConfiguration getIslamicHoliday(final IslamicHolidayType type, final Year validFrom, final Year validTo) {
    return getIslamicHoliday(type, null, validFrom, validTo);
  }

  private static IslamicHolidayConfiguration getIslamicHoliday(
    final IslamicHolidayType type,
    final Movable.MovingCondition movingCondition,
    final Year validFrom,
    final Year validTo
  ) {
    return new IslamicHolidayConfiguration() {

      @Override
      public @NonNull List<MovingCondition> conditions() {
        return movingCondition == null ? List.of() : List.of(movingCondition);
      }

      @Override
      public @NonNull String descriptionPropertiesKey() {
        return type.name();
      }

      @Override
      public @NonNull HolidayType holidayType() {
        return null;
      }

      @Override
      public @NonNull IslamicHolidayType type() {
        return type;
      }

      @Override
      public @NonNull Optional<Year> validFrom() {
        return Optional.ofNullable(validFrom);
      }

      @Override
      public @NonNull Optional<Year> validTo() {
        return Optional.ofNullable(validTo);
      }

      @Override
      public @NonNull YearCycle cycle() {
        return EVERY_YEAR;
      }
    };
  }
}
