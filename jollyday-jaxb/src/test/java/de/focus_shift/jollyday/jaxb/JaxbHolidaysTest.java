package de.focus_shift.jollyday.jaxb;

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
import de.focus_shift.jollyday.jaxb.mapping.ChristianHoliday;
import de.focus_shift.jollyday.jaxb.mapping.EthiopianOrthodoxHoliday;
import de.focus_shift.jollyday.jaxb.mapping.Fixed;
import de.focus_shift.jollyday.jaxb.mapping.FixedWeekdayBetweenFixed;
import de.focus_shift.jollyday.jaxb.mapping.FixedWeekdayInMonth;
import de.focus_shift.jollyday.jaxb.mapping.FixedWeekdayRelativeToFixed;
import de.focus_shift.jollyday.jaxb.mapping.Holidays;
import de.focus_shift.jollyday.jaxb.mapping.IslamicHoliday;
import de.focus_shift.jollyday.jaxb.mapping.RelativeToEasterSunday;
import de.focus_shift.jollyday.jaxb.mapping.RelativeToFixed;
import de.focus_shift.jollyday.jaxb.mapping.RelativeToWeekdayInMonth;
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
class JaxbHolidaysTest {

  @Mock
  private Holidays mappingHolidays;
  private JaxbHolidays jaxbHolidays;

  @BeforeEach
  void setUp() {
    jaxbHolidays = new JaxbHolidays(mappingHolidays);
  }

  @Test
  void testFixed() {
    final Fixed fixed = mock(Fixed.class);
    when(mappingHolidays.getFixed()).thenReturn(List.of(fixed));
    final List<FixedHolidayConfiguration> result = jaxbHolidays.fixed();
    assertThat(result).hasSize(1);
    assertThat(result.get(0)).isInstanceOf(JaxbFixed.class);
  }

  @Test
  void testRelativeToFixed() {
    final RelativeToFixed rel = mock(RelativeToFixed.class);
    when(mappingHolidays.getRelativeToFixed()).thenReturn(List.of(rel));
    final List<RelativeToFixedHolidayConfiguration> result = jaxbHolidays.relativeToFixed();
    assertThat(result).hasSize(1);
    assertThat(result.get(0)).isInstanceOf(JaxbRelativeToFixed.class);
  }

  @Test
  void testRelativeToWeekdayInMonth() {
    final RelativeToWeekdayInMonth rel = mock(RelativeToWeekdayInMonth.class);
    when(mappingHolidays.getRelativeToWeekdayInMonth()).thenReturn(List.of(rel));
    final List<RelativeToWeekdayInMonthHolidayConfiguration> result = jaxbHolidays.relativeToWeekdayInMonth();
    assertThat(result).hasSize(1);
    assertThat(result.get(0)).isInstanceOf(JaxbRelativeToWeekdayInMonth.class);
  }

  @Test
  void testFixedWeekdays() {
    final FixedWeekdayInMonth fixedWeekday = mock(FixedWeekdayInMonth.class);
    when(mappingHolidays.getFixedWeekday()).thenReturn(List.of(fixedWeekday));
    final List<FixedWeekdayInMonthHolidayConfiguration> result = jaxbHolidays.fixedWeekdays();
    assertThat(result).hasSize(1);
    assertThat(result.get(0)).isInstanceOf(JaxbFixedWeekdayInMonth.class);
  }

  @Test
  void testChristianHolidays() {
    final ChristianHoliday holiday = mock(ChristianHoliday.class);
    when(mappingHolidays.getChristianHoliday()).thenReturn(List.of(holiday));
    final List<ChristianHolidayConfiguration> result = jaxbHolidays.christianHolidays();
    assertThat(result).hasSize(1);
    assertThat(result.get(0)).isInstanceOf(JaxbChristianHoliday.class);
  }

  @Test
  void testIslamicHolidays() {
    final IslamicHoliday holiday = mock(IslamicHoliday.class);
    when(mappingHolidays.getIslamicHoliday()).thenReturn(List.of(holiday));
    final List<IslamicHolidayConfiguration> result = jaxbHolidays.islamicHolidays();
    assertThat(result).hasSize(1);
    assertThat(result.get(0)).isInstanceOf(JaxbIslamicHoliday.class);
  }

  @Test
  void testFixedWeekdayBetweenFixed() {
    final FixedWeekdayBetweenFixed holiday = mock(FixedWeekdayBetweenFixed.class);
    when(mappingHolidays.getFixedWeekdayBetweenFixed()).thenReturn(List.of(holiday));
    final List<FixedWeekdayBetweenFixedHolidayConfiguration> result = jaxbHolidays.fixedWeekdayBetweenFixed();
    assertThat(result).hasSize(1);
    assertThat(result.get(0)).isInstanceOf(JaxbFixedWeekdayBetweenFixed.class);
  }

  @Test
  void testFixedWeekdayRelativeToFixed() {
    final FixedWeekdayRelativeToFixed holiday = mock(FixedWeekdayRelativeToFixed.class);
    when(mappingHolidays.getFixedWeekdayRelativeToFixed()).thenReturn(List.of(holiday));
    final List<FixedWeekdayRelativeToFixedHolidayConfiguration> result = jaxbHolidays.fixedWeekdayRelativeToFixed();
    assertThat(result).hasSize(1);
    assertThat(result.get(0)).isInstanceOf(JaxbFixedWeekdayRelativeToFixed.class);
  }

  @Test
  void testEthiopianOrthodoxHolidays() {
    final EthiopianOrthodoxHoliday holiday = mock(EthiopianOrthodoxHoliday.class);
    when(mappingHolidays.getEthiopianOrthodoxHoliday()).thenReturn(List.of(holiday));
    final List<EthiopianOrthodoxHolidayConfiguration> result = jaxbHolidays.ethiopianOrthodoxHolidays();
    assertThat(result).hasSize(1);
    assertThat(result.get(0)).isInstanceOf(JaxbEthiopianOrthodoxHoliday.class);
  }

  @Test
  void testRelativeToEasterSunday() {
    final RelativeToEasterSunday holiday = mock(RelativeToEasterSunday.class);
    when(mappingHolidays.getRelativeToEasterSunday()).thenReturn(List.of(holiday));
    final List<RelativeToEasterSundayHolidayConfiguration> result = jaxbHolidays.relativeToEasterSunday();
    assertThat(result).hasSize(1);
    assertThat(result.get(0)).isInstanceOf(JaxbRelativeToEasterSunday.class);
  }
}
