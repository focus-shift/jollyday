package de.focus_shift.jollyday.core.parser.impl;

import de.focus_shift.jollyday.core.Holiday;
import de.focus_shift.jollyday.core.spi.Holidays;
import de.focus_shift.jollyday.core.spi.IslamicHoliday;
import de.focus_shift.jollyday.core.spi.Movable;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.DayOfWeek;
import java.time.Year;
import java.util.List;

import static de.focus_shift.jollyday.core.spi.Limited.YearCycle.EVERY_YEAR;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class IslamicHolidayParserTest {

  @Mock
  private Holidays holidays;
  @Mock
  private IslamicHoliday islamicHoliday;

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

    final IslamicHolidayParser sut = new IslamicHolidayParser();
    when(holidays.islamicHolidays()).thenReturn(List.of(islamicHoliday));
    when(islamicHoliday.type()).thenReturn(IslamicHoliday.IslamicHolidayType.ID_UL_ADHA_2);
    when(islamicHoliday.conditions()).thenReturn(List.of(movingCondition));
    when(islamicHoliday.cycle()).thenReturn(EVERY_YEAR);

    // ID_UL_ADHA_2 will be on a sunday in 2022
    final List<Holiday> calculatedHoliday = sut.parse(Year.of(2022), holidays);
    assertThat(calculatedHoliday.get(0).getDate().getDayOfWeek()).isEqualTo(DayOfWeek.MONDAY);
  }
}
