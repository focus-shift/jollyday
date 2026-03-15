package de.focus_shift.jollyday.core.parser.functions;

import static org.assertj.core.api.Assertions.assertThat;

import de.focus_shift.jollyday.core.HolidayType;
import de.focus_shift.jollyday.core.spi.FixedHolidayConfiguration;
import de.focus_shift.jollyday.core.spi.FixedWeekdayBetweenFixedHolidayConfiguration;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.Year;
import org.jspecify.annotations.NonNull;
import org.jspecify.annotations.Nullable;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class FindWeekDayBetweenTest {

  @ParameterizedTest
  @CsvSource({
    "MONDAY,2025-01-06",
    "WEDNESDAY,2025-01-08",
    "FRIDAY,2025-01-10",
  })
  void ensureToFindFirstWeekdayBetweenTwoFixedDates(
      final DayOfWeek dayOfWeek, final LocalDate expectedDate) {
    final LocalDate firstMonday = LocalDate.of(2025, 1, 6);
    final LocalDate secondFriday = LocalDate.of(2025, 1, 17);
    final LocalDate actualDate =
        new FindWeekDayBetween(firstMonday, secondFriday)
            .apply(getFixedWeekdayBetweenFixed(dayOfWeek));
    assertThat(actualDate).isEqualTo(expectedDate);
  }

  @Test
  void ensureToReturnNullIfNoWeekdayWasFound() {
    final LocalDate monday = LocalDate.of(2025, 1, 6);
    final LocalDate tuesday = LocalDate.of(2025, 1, 7);
    final LocalDate actualDate =
        new FindWeekDayBetween(monday, tuesday)
            .apply(getFixedWeekdayBetweenFixed(DayOfWeek.WEDNESDAY));
    assertThat(actualDate).isNull();
  }

  private static FixedWeekdayBetweenFixedHolidayConfiguration getFixedWeekdayBetweenFixed(
      final DayOfWeek dayOfWeek) {

    return new FixedWeekdayBetweenFixedHolidayConfiguration() {

      @Override
      public @NonNull FixedHolidayConfiguration from() {
        return null;
      }

      @Override
      public @NonNull FixedHolidayConfiguration to() {
        return null;
      }

      @Override
      public @NonNull DayOfWeek weekday() {
        return dayOfWeek;
      }

      @Override
      public @NonNull String descriptionPropertiesKey() {
        return "";
      }

      @Override
      public @NonNull HolidayType holidayType() {
        return null;
      }

      @Override
      public @Nullable Year validFrom() {
        return null;
      }

      @Override
      public @Nullable Year validTo() {
        return null;
      }

      @Override
      public @NonNull YearCycle cycle() {
        return null;
      }
    };
  }
}
