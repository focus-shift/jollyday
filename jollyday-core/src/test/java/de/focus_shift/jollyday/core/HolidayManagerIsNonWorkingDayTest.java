package de.focus_shift.jollyday.core;

import org.jspecify.annotations.NonNull;
import org.junit.jupiter.api.Test;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.Year;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

class HolidayManagerIsNonWorkingDayTest {

  @Test
  void isNonWorkingDayIsTrueOnWeekendDay() {
    final HolidayManager sut = managerWith(Set.of(DayOfWeek.FRIDAY, DayOfWeek.SATURDAY), Set.of());

    assertThat(sut.isNonWorkingDay(LocalDate.of(2024, 1, 5))).isTrue(); // a Friday
  }

  @Test
  void isNonWorkingDayIsTrueOnHoliday() {
    final LocalDate newYear = LocalDate.of(2024, 1, 1); // a Monday
    final HolidayManager sut = managerWith(
      Set.of(DayOfWeek.SATURDAY, DayOfWeek.SUNDAY),
      Set.of(new Holiday(newYear, "NEW_YEAR", HolidayType.PUBLIC_HOLIDAY))
    );

    assertThat(sut.isNonWorkingDay(newYear)).isTrue();
  }

  @Test
  void isNonWorkingDayIsFalseOnRegularWorkday() {
    final HolidayManager sut = managerWith(Set.of(DayOfWeek.SATURDAY, DayOfWeek.SUNDAY), Set.of());

    assertThat(sut.isNonWorkingDay(LocalDate.of(2024, 1, 2))).isFalse(); // a Tuesday, no holiday
  }

  private static @NonNull HolidayManager managerWith(@NonNull final Set<DayOfWeek> weekendDays, @NonNull final Set<Holiday> holidays) {
    return new HolidayManager() {

      @Override
      public void doInit() {
        // not needed for this test
      }

      @Override
      public @NonNull Set<Holiday> getHolidays(@NonNull final Year year, @NonNull final String... args) {
        return holidays;
      }

      @Override
      public @NonNull Set<Holiday> getHolidays(@NonNull final Year year, final HolidayType holidayType, @NonNull final String... args) {
        throw new UnsupportedOperationException("not needed for this test");
      }

      @Override
      public @NonNull Set<Holiday> getHolidays(@NonNull final LocalDate startDateInclusive, @NonNull final LocalDate endDateInclusive, @NonNull final String... args) {
        throw new UnsupportedOperationException("not needed for this test");
      }

      @Override
      public @NonNull Set<Holiday> getHolidays(@NonNull final LocalDate startDateInclusive, @NonNull final LocalDate endDateInclusive, final HolidayType holidayType, @NonNull final String... args) {
        throw new UnsupportedOperationException("not needed for this test");
      }

      @Override
      public @NonNull CalendarHierarchy getCalendarHierarchy() {
        throw new UnsupportedOperationException("not needed for this test");
      }

      @Override
      public @NonNull Set<DayOfWeek> getWeekendDays(@NonNull final Year year, @NonNull final String... args) {
        return weekendDays;
      }
    };
  }
}
