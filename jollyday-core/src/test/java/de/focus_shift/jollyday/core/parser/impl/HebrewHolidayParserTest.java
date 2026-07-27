package de.focus_shift.jollyday.core.parser.impl;

import de.focus_shift.jollyday.core.Holiday;
import de.focus_shift.jollyday.core.HolidayType;
import de.focus_shift.jollyday.core.spi.HebrewHolidayConfiguration;
import de.focus_shift.jollyday.core.spi.HolidayConfigurations;
import org.jspecify.annotations.NonNull;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.time.Year;
import java.util.List;
import java.util.Optional;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static de.focus_shift.jollyday.core.HolidayType.PUBLIC_HOLIDAY;
import static de.focus_shift.jollyday.core.spi.HebrewHolidayConfiguration.HebrewHolidayType.ROSH_HASHANA;
import static de.focus_shift.jollyday.core.spi.Limited.YearCycle.EVERY_YEAR;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class HebrewHolidayParserTest {

  @Mock
  private HolidayConfigurations holidays;

  @Nested
  @TestInstance(TestInstance.Lifecycle.PER_CLASS)
  class HebrewHolidayTypeTests {

    private Stream<Arguments> hebrewHolidaysWithLocalDates() {
      final HebrewHolidayConfiguration.HebrewHolidayType[] hebrewHolidayTypes = HebrewHolidayConfiguration.HebrewHolidayType.values();
      final String[] hebrewHolidayDates = {
        "2022-09-26", // ROSH_HASHANA
        "2022-09-27", // ROSH_HASHANA_II
        "2022-10-05", // YOM_KIPPUR
        "2022-10-10", // SUKKOT
        "2022-10-17", // SHEMINI_ATZERET
        "2022-04-16", // PESACH
        "2022-04-22", // PESACH_VII
        "2022-06-05", // SHAVUOT
        "2022-05-05"  // YOM_HAATZMAUT (raw 5 Iyar is Friday 2022-05-06, postponed 1 day earlier)
      };

      return IntStream.range(0, hebrewHolidayTypes.length)
        .mapToObj(i -> Arguments.of(hebrewHolidayTypes[i], hebrewHolidayDates[i]));
    }

    @ParameterizedTest
    @MethodSource("hebrewHolidaysWithLocalDates")
    void ensureThatAllHebrewHolidayTypesProvideAHoliday(final HebrewHolidayConfiguration.HebrewHolidayType type, final LocalDate expected) {

      final HebrewHolidayConfiguration hebrewHoliday = getHebrewHoliday(type);

      final HebrewHolidayParser sut = new HebrewHolidayParser();
      when(holidays.hebrewHolidays()).thenReturn(List.of(hebrewHoliday));

      final List<Holiday> calculatedHoliday = sut.parse(Year.of(2022), holidays);
      assertThat(calculatedHoliday.get(0).getPropertiesKey()).isEqualTo(type.name());
      assertThat(calculatedHoliday.get(0).getDate()).isEqualTo(expected);
    }
  }

  @Nested
  class YomHaatzmautPostponementTests {

    // Verified against published Israeli Independence Day observance dates:
    // Friday/Saturday raw dates always move to the preceding Thursday; a Monday raw
    // date is only postponed to Tuesday from 2004 onwards; Wednesday is never moved.
    @ParameterizedTest
    @CsvSource({
      "2022, 2022-05-05", // raw 5 Iyar is Friday 2022-05-06 -> Thursday
      "2008, 2008-05-08", // raw 5 Iyar is Saturday 2008-05-10 -> Thursday
      "1997, 1997-05-12", // raw 5 Iyar is Monday 1997-05-12 -> stays (pre-2004)
      "2004, 2004-04-27", // raw 5 Iyar is Monday 2004-04-26 -> Tuesday (rule starts 2004)
      "2026, 2026-04-22"  // raw 5 Iyar is Wednesday 2026-04-22 -> stays
    })
    void ensureYomHaatzmautIsPostponedCorrectly(final int year, final LocalDate expected) {
      final HebrewHolidayConfiguration hebrewHoliday = getHebrewHoliday(HebrewHolidayConfiguration.HebrewHolidayType.YOM_HAATZMAUT);

      final HebrewHolidayParser sut = new HebrewHolidayParser();
      when(holidays.hebrewHolidays()).thenReturn(List.of(hebrewHoliday));

      final List<Holiday> calculatedHoliday = sut.parse(Year.of(year), holidays);
      assertThat(calculatedHoliday.get(0).getDate()).isEqualTo(expected);
    }
  }

  @Nested
  class LimitedTests {

    @Test
    void ensureThatHebrewHolidaysAreLimitedAndIsValid() {

      final HebrewHolidayConfiguration hebrewHoliday = getHebrewHoliday(ROSH_HASHANA, Year.of(2022), Year.of(2022));

      final HebrewHolidayParser sut = new HebrewHolidayParser();
      when(holidays.hebrewHolidays()).thenReturn(List.of(hebrewHoliday));

      final List<Holiday> calculatedHoliday = sut.parse(Year.of(2022), holidays);
      assertThat(calculatedHoliday.get(0).getPropertiesKey()).isEqualTo("ROSH_HASHANA");
    }

    @Test
    void ensureThatHebrewHolidaysAreLimitedAndIsInvalid() {

      final HebrewHolidayConfiguration hebrewHoliday = getHebrewHoliday(ROSH_HASHANA, Year.of(2023), Year.of(2023));

      final HebrewHolidayParser sut = new HebrewHolidayParser();
      when(holidays.hebrewHolidays()).thenReturn(List.of(hebrewHoliday));

      final List<Holiday> calculatedHoliday = sut.parse(Year.of(2022), holidays);
      assertThat(calculatedHoliday).isEmpty();
    }
  }

  private static HebrewHolidayConfiguration getHebrewHoliday(final HebrewHolidayConfiguration.HebrewHolidayType type) {
    return getHebrewHoliday(type, null, null);
  }

  private static HebrewHolidayConfiguration getHebrewHoliday(
    final HebrewHolidayConfiguration.HebrewHolidayType type,
    final Year validFrom,
    final Year validTo
  ) {
    return new HebrewHolidayConfiguration() {

      @Override
      public @NonNull String descriptionPropertiesKey() {
        return type.name();
      }

      @Override
      public @NonNull HolidayType holidayType() {
        return PUBLIC_HOLIDAY;
      }

      @Override
      public @NonNull HebrewHolidayType type() {
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
