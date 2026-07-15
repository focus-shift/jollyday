package de.focus_shift.jollyday.core.parser.functions;

import de.focus_shift.jollyday.core.HolidayType;
import de.focus_shift.jollyday.core.spi.FixedHolidayConfiguration;
import de.focus_shift.jollyday.core.spi.FixedWeekdayRelativeToFixedHolidayConfiguration;
import de.focus_shift.jollyday.core.spi.Occurrence;
import de.focus_shift.jollyday.core.spi.Relation;
import org.jspecify.annotations.NonNull;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.Year;
import java.util.Optional;

import static java.time.DayOfWeek.WEDNESDAY;
import static org.assertj.core.api.Assertions.assertThat;

class FindWeekDayRelativeToDateTest {

  @ParameterizedTest
  @CsvSource({
    "AFTER,  FIRST,  2024-01-03",
    "AFTER,  SECOND, 2024-01-10",
    "AFTER,  THIRD,  2024-01-17",
    "AFTER,  FOURTH, 2024-01-24",
    "AFTER,  LAST,   2024-01-03",
    "BEFORE, FIRST,  2023-12-27",
    "BEFORE, SECOND, 2023-12-20",
    "BEFORE, THIRD,  2023-12-13",
    "BEFORE, FOURTH, 2023-12-06",
    "CLOSEST, FIRST, 2024-01-03",
  })
  void ensureAppliesRelationAndOccurrenceToWeekday(final Relation relation, final Occurrence occurrence, final LocalDate expected) {
    final LocalDate anchor = LocalDate.of(2024, 1, 1);
    final FindWeekDayRelativeToDate sut = new FindWeekDayRelativeToDate(anchor);

    final LocalDate result = sut.apply(config(WEDNESDAY, occurrence, relation));

    assertThat(result).isEqualTo(expected);
  }

  private static @NonNull FixedWeekdayRelativeToFixedHolidayConfiguration config(
    final DayOfWeek weekday,
    final Occurrence which,
    final Relation when
  ) {
    return new FixedWeekdayRelativeToFixedHolidayConfiguration() {

      @Override
      public @NonNull FixedHolidayConfiguration day() {
        throw new UnsupportedOperationException("not used by FindWeekDayRelativeToDate");
      }

      @Override
      public @NonNull Occurrence which() {
        return which;
      }

      @Override
      public @NonNull DayOfWeek weekday() {
        return weekday;
      }

      @Override
      public @NonNull Relation when() {
        return when;
      }

      @Override
      public @NonNull String descriptionPropertiesKey() {
        return "";
      }

      @Override
      public @NonNull HolidayType holidayType() {
        return HolidayType.PUBLIC_HOLIDAY;
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
        return YearCycle.EVERY_YEAR;
      }
    };
  }
}
