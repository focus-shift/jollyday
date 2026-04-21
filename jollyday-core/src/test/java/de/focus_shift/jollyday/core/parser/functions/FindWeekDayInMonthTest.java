package de.focus_shift.jollyday.core.parser.functions;

import de.focus_shift.jollyday.core.HolidayType;
import de.focus_shift.jollyday.core.spi.FixedWeekdayInMonthHolidayConfiguration;
import de.focus_shift.jollyday.core.spi.Occurrence;
import org.jspecify.annotations.NonNull;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.Month;
import java.time.Year;
import java.util.List;
import java.util.Optional;

import static java.time.Month.MARCH;
import static org.assertj.core.api.Assertions.assertThat;

class FindWeekDayInMonthTest {

  @ParameterizedTest
  @CsvSource({
    "FIRST,2024-03-04",
    "SECOND,2024-03-11",
    "THIRD,2024-03-18",
    "FOURTH,2024-03-25",
    "LAST,2024-03-25",
  })
  void ensureToFindFixedWeekdayInMonth(final Occurrence occurrence, final LocalDate expectedLocalDate) {

    final FixedWeekdayInMonthHolidayConfiguration fixedWeekdayInMonth = new FixedWeekdayInMonthHolidayConfiguration() {
      @Override
      public @NonNull List<MovingCondition> conditions() {
        return List.of();
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
        return null;
      }

      @Override
      public @NonNull DayOfWeek weekday() {
        return DayOfWeek.MONDAY;
      }

      @Override
      public @NonNull Month month() {
        return MARCH;
      }

      @Override
      public @NonNull Occurrence which() {
        return occurrence;
      }

      @Override
      public @NonNull String descriptionPropertiesKey() {
        return null;
      }

      @Override
      public @NonNull HolidayType holidayType() {
        return null;
      }
    };

    final LocalDate actualLocalDate = new FindWeekDayInMonth(Year.of(2024)).apply(fixedWeekdayInMonth);
    assertThat(actualLocalDate).isEqualTo(expectedLocalDate);
  }
}
