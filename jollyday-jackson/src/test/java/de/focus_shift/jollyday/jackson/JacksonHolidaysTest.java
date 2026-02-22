package de.focus_shift.jollyday.jackson;

import de.focus_shift.jollyday.core.spi.ChristianHolidayConfiguration;
import de.focus_shift.jollyday.core.spi.EthiopianOrthodoxHolidayConfiguration;
import de.focus_shift.jollyday.core.spi.FixedHolidayConfiguration;
import de.focus_shift.jollyday.core.spi.FixedWeekdayBetweenFixedHolidayConfiguration;
import de.focus_shift.jollyday.core.spi.FixedWeekdayInMonthHolidayConfiguration;
import de.focus_shift.jollyday.core.spi.FixedWeekdayRelativeToFixedHolidayConfiguration;
import de.focus_shift.jollyday.core.spi.IslamicHolidayConfiguration;
import de.focus_shift.jollyday.core.spi.RelativeToEasterSundayHolidayConfiguration;
import de.focus_shift.jollyday.core.spi.RelativeToFixedHolidayConfiguration;
import de.focus_shift.jollyday.core.spi.RelativeToWeekdayInMonthHolidayConfiguration;
import de.focus_shift.jollyday.jackson.mapping.ChristianHoliday;
import de.focus_shift.jollyday.jackson.mapping.EthiopianOrthodoxHoliday;
import de.focus_shift.jollyday.jackson.mapping.Fixed;
import de.focus_shift.jollyday.jackson.mapping.FixedWeekdayBetweenFixed;
import de.focus_shift.jollyday.jackson.mapping.FixedWeekdayInMonth;
import de.focus_shift.jollyday.jackson.mapping.FixedWeekdayRelativeToFixed;
import de.focus_shift.jollyday.jackson.mapping.Holidays;
import de.focus_shift.jollyday.jackson.mapping.IslamicHoliday;
import de.focus_shift.jollyday.jackson.mapping.RelativeToEasterSunday;
import de.focus_shift.jollyday.jackson.mapping.RelativeToFixed;
import de.focus_shift.jollyday.jackson.mapping.RelativeToWeekdayInMonth;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class JacksonHolidaysTest {

  @Mock
  private Holidays mappingHolidays;
  private JacksonHolidays jacksonHolidays;

  @BeforeEach
  void setUp() {
    jacksonHolidays = new JacksonHolidays(mappingHolidays);
  }

  @Test
  void testFixed() {
    final Fixed fixed = mock(Fixed.class);
    when(mappingHolidays.getFixed()).thenReturn(List.of(fixed));
    final List<FixedHolidayConfiguration> result = jacksonHolidays.fixed();
    assertThat(result).hasSize(1);
    assertThat(result.get(0)).isInstanceOf(JacksonFixed.class);
  }

  @Test
  void testRelativeToFixed() {
    final RelativeToFixed rel = mock(RelativeToFixed.class);
    when(mappingHolidays.getRelativeToFixed()).thenReturn(List.of(rel));
    final List<RelativeToFixedHolidayConfiguration> result = jacksonHolidays.relativeToFixed();
    assertThat(result).hasSize(1);
    assertThat(result.get(0)).isInstanceOf(JacksonRelativeToFixed.class);
  }

  @Test
  void testRelativeToWeekdayInMonth() {
    final RelativeToWeekdayInMonth rel = mock(RelativeToWeekdayInMonth.class);
    when(mappingHolidays.getRelativeToWeekdayInMonth()).thenReturn(List.of(rel));
    final List<RelativeToWeekdayInMonthHolidayConfiguration> result = jacksonHolidays.relativeToWeekdayInMonth();
    assertThat(result).hasSize(1);
    assertThat(result.get(0)).isInstanceOf(JacksonRelativeToWeekdayInMonth.class);
  }

  @Test
  void testFixedWeekdays() {
    final FixedWeekdayInMonth fixedWeekday = mock(FixedWeekdayInMonth.class);
    when(mappingHolidays.getFixedWeekday()).thenReturn(List.of(fixedWeekday));
    final List<FixedWeekdayInMonthHolidayConfiguration> result = jacksonHolidays.fixedWeekdays();
    assertThat(result).hasSize(1);
    assertThat(result.get(0)).isInstanceOf(JacksonFixedWeekdayInMonth.class);
  }

  @Test
  void testChristianHolidays() {
    final ChristianHoliday holiday = mock(ChristianHoliday.class);
    when(mappingHolidays.getChristianHoliday()).thenReturn(List.of(holiday));
    final List<ChristianHolidayConfiguration> result = jacksonHolidays.christianHolidays();
    assertThat(result).hasSize(1);
    assertThat(result.get(0)).isInstanceOf(JacksonChristianHoliday.class);
  }

  @Test
  void testIslamicHolidays() {
    final IslamicHoliday holiday = mock(IslamicHoliday.class);
    when(mappingHolidays.getIslamicHoliday()).thenReturn(List.of(holiday));
    final List<IslamicHolidayConfiguration> result = jacksonHolidays.islamicHolidays();
    assertThat(result).hasSize(1);
    assertThat(result.get(0)).isInstanceOf(JacksonIslamicHoliday.class);
  }

  @Test
  void testFixedWeekdayBetweenFixed() {
    final FixedWeekdayBetweenFixed holiday = mock(FixedWeekdayBetweenFixed.class);
    when(mappingHolidays.getFixedWeekdayBetweenFixed()).thenReturn(List.of(holiday));
    final List<FixedWeekdayBetweenFixedHolidayConfiguration> result = jacksonHolidays.fixedWeekdayBetweenFixed();
    assertThat(result).hasSize(1);
    assertThat(result.get(0)).isInstanceOf(JacksonFixedWeekdayBetweenFixed.class);
  }

  @Test
  void testFixedWeekdayRelativeToFixed() {
    final FixedWeekdayRelativeToFixed holiday = mock(FixedWeekdayRelativeToFixed.class);
    when(mappingHolidays.getFixedWeekdayRelativeToFixed()).thenReturn(List.of(holiday));
    final List<FixedWeekdayRelativeToFixedHolidayConfiguration> result = jacksonHolidays.fixedWeekdayRelativeToFixed();
    assertThat(result).hasSize(1);
    assertThat(result.get(0)).isInstanceOf(JacksonFixedWeekdayRelativeToFixed.class);
  }

  @Test
  void testEthiopianOrthodoxHolidays() {
    final EthiopianOrthodoxHoliday holiday = mock(EthiopianOrthodoxHoliday.class);
    when(mappingHolidays.getEthiopianOrthodoxHoliday()).thenReturn(List.of(holiday));
    final List<EthiopianOrthodoxHolidayConfiguration> result = jacksonHolidays.ethiopianOrthodoxHolidays();
    assertThat(result).hasSize(1);
    assertThat(result.get(0)).isInstanceOf(JacksonEthiopianOrthodoxHoliday.class);
  }

  @Test
  void testRelativeToEasterSunday() {
    final RelativeToEasterSunday holiday = mock(RelativeToEasterSunday.class);
    when(mappingHolidays.getRelativeToEasterSunday()).thenReturn(List.of(holiday));
    final List<RelativeToEasterSundayHolidayConfiguration> result = jacksonHolidays.relativeToEasterSunday();
    assertThat(result).hasSize(1);
    assertThat(result.get(0)).isInstanceOf(JacksonRelativeToEasterSunday.class);
  }
}
