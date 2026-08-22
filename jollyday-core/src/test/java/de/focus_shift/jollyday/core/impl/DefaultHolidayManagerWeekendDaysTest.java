package de.focus_shift.jollyday.core.impl;

import de.focus_shift.jollyday.core.spi.HolidayCalendarConfiguration;
import de.focus_shift.jollyday.core.spi.WeekendConfiguration;
import org.jspecify.annotations.NonNull;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.DayOfWeek;
import java.time.Year;
import java.util.Optional;
import java.util.stream.Stream;

import static java.time.DayOfWeek.FRIDAY;
import static java.time.DayOfWeek.SATURDAY;
import static java.time.DayOfWeek.SUNDAY;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class DefaultHolidayManagerWeekendDaysTest {

  @Mock
  private HolidayCalendarConfiguration configuration;

  @Test
  void fallsBackToSaturdayAndSundayWhenNoWeekendIsDefinedAnywhere() {
    when(configuration.weekends()).thenReturn(Stream.empty());

    final DefaultHolidayManager sut = managerFor(configuration);

    assertThat(sut.getWeekendDays(Year.of(2024))).containsExactlyInAnyOrder(SATURDAY, SUNDAY);
  }

  @Test
  void usesOwnWeekendConfigurationWhenPresent() {
    final WeekendConfiguration friday = weekend(FRIDAY, null, null);
    final WeekendConfiguration saturday = weekend(SATURDAY, null, null);
    when(configuration.weekends()).thenAnswer(invocation -> Stream.of(friday, saturday));

    final DefaultHolidayManager sut = managerFor(configuration);

    assertThat(sut.getWeekendDays(Year.of(2024))).containsExactlyInAnyOrder(FRIDAY, SATURDAY);
  }

  @Test
  void resolvesTheWeekendValidForTheRequestedYearWhenItChangedOverTime() {
    final WeekendConfiguration fridayUntil2021 = weekend(FRIDAY, null, 2021);
    final WeekendConfiguration saturdayUntil2021 = weekend(SATURDAY, null, 2021);
    final WeekendConfiguration saturdayFrom2022 = weekend(SATURDAY, 2022, null);
    final WeekendConfiguration sundayFrom2022 = weekend(SUNDAY, 2022, null);
    when(configuration.weekends()).thenAnswer(invocation -> Stream.of(fridayUntil2021, saturdayUntil2021, saturdayFrom2022, sundayFrom2022));

    final DefaultHolidayManager sut = managerFor(configuration);

    assertThat(sut.getWeekendDays(Year.of(2021))).containsExactlyInAnyOrder(FRIDAY, SATURDAY);
    assertThat(sut.getWeekendDays(Year.of(2022))).containsExactlyInAnyOrder(SATURDAY, SUNDAY);
  }

  @Test
  void subdivisionOverridesParentWeekend() {
    final WeekendConfiguration friday = weekend(FRIDAY, null, null);
    final HolidayCalendarConfiguration subConfiguration = mock(HolidayCalendarConfiguration.class);
    when(subConfiguration.hierarchy()).thenReturn("dxb");
    when(subConfiguration.weekends()).thenReturn(Stream.of(friday));

    when(configuration.subConfigurations()).thenReturn(Stream.of(subConfiguration));

    final DefaultHolidayManager sut = managerFor(configuration);

    assertThat(sut.getWeekendDays(Year.of(2024), "dxb")).containsExactlyInAnyOrder(FRIDAY);
  }

  @Test
  void subdivisionInheritsParentWeekendWhenItDefinesNoneOfItsOwn() {
    final HolidayCalendarConfiguration subConfiguration = mock(HolidayCalendarConfiguration.class);
    when(subConfiguration.hierarchy()).thenReturn("bw");
    when(subConfiguration.weekends()).thenReturn(Stream.empty());

    final WeekendConfiguration saturday = weekend(SATURDAY, null, null);
    final WeekendConfiguration sunday = weekend(SUNDAY, null, null);
    when(configuration.weekends()).thenReturn(Stream.of(saturday, sunday));
    when(configuration.subConfigurations()).thenReturn(Stream.of(subConfiguration));

    final DefaultHolidayManager sut = managerFor(configuration);

    assertThat(sut.getWeekendDays(Year.of(2024), "bw")).containsExactlyInAnyOrder(SATURDAY, SUNDAY);
  }

  private static @NonNull DefaultHolidayManager managerFor(@NonNull final HolidayCalendarConfiguration configuration) {
    final DefaultHolidayManager manager = new DefaultHolidayManager();
    manager.holidayCalendarConfiguration = configuration;
    return manager;
  }

  private static @NonNull WeekendConfiguration weekend(@NonNull final DayOfWeek weekday, final Integer validFrom, final Integer validTo) {
    final WeekendConfiguration weekend = mock(WeekendConfiguration.class);
    when(weekend.weekday()).thenReturn(weekday);
    when(weekend.validFrom()).thenReturn(validFrom == null ? Optional.empty() : Optional.of(Year.of(validFrom)));
    when(weekend.validTo()).thenReturn(validTo == null ? Optional.empty() : Optional.of(Year.of(validTo)));
    return weekend;
  }
}
