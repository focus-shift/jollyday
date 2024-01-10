package de.focus_shift.jollyday.jackson;

import de.focus_shift.jollyday.core.spi.ChristianHoliday;
import de.focus_shift.jollyday.core.spi.EthiopianOrthodoxHoliday;
import de.focus_shift.jollyday.core.spi.Fixed;
import de.focus_shift.jollyday.core.spi.FixedWeekdayBetweenFixed;
import de.focus_shift.jollyday.core.spi.FixedWeekdayInMonth;
import de.focus_shift.jollyday.core.spi.FixedWeekdayRelativeToFixed;
import de.focus_shift.jollyday.core.spi.IslamicHoliday;
import de.focus_shift.jollyday.core.spi.RelativeToEasterSunday;
import de.focus_shift.jollyday.core.spi.RelativeToFixed;
import de.focus_shift.jollyday.core.spi.RelativeToWeekdayInMonth;
import de.focus_shift.jollyday.jackson.mapping.Holidays;

import java.util.List;

import static java.util.stream.Collectors.toList;

public class JacksonHolidays implements de.focus_shift.jollyday.core.spi.Holidays {

  private final Holidays holidays;

  public JacksonHolidays(Holidays holidays) {
    this.holidays = holidays;
  }

  @Override
  public List<Fixed> fixed() {
    return holidays.getFixed().stream().map(JacksonFixed::new).collect(toList());
  }

  @Override
  public List<RelativeToFixed> relativeToFixed() {
    return holidays.getRelativeToFixed().stream().map(JacksonRelativeToFixed::new).collect(toList());
  }

  @Override
  public List<RelativeToWeekdayInMonth> relativeToWeekdayInMonth() {
    return holidays.getRelativeToWeekdayInMonth().stream().map(JacksonRelativeToWeekdayInMonth::new).collect(toList());
  }

  @Override
  public List<FixedWeekdayInMonth> fixedWeekdays() {
    return holidays.getFixedWeekday().stream().map(JacksonFixedWeekdayInMonth::new).collect(toList());
  }

  @Override
  public List<ChristianHoliday> christianHolidays() {
    return holidays.getChristianHoliday().stream().map(JacksonChristianHoliday::new).collect(toList());
  }

  @Override
  public List<IslamicHoliday> islamicHolidays() {
    return holidays.getIslamicHoliday().stream().map(JacksonIslamicHoliday::new).collect(toList());
  }

  @Override
  public List<FixedWeekdayBetweenFixed> fixedWeekdayBetweenFixed() {
    return holidays.getFixedWeekdayBetweenFixed().stream().map(JacksonFixedWeekdayBetweenFixed::new).collect(toList());
  }

  @Override
  public List<FixedWeekdayRelativeToFixed> fixedWeekdayRelativeToFixed() {
    return holidays.getFixedWeekdayRelativeToFixed().stream().map(JacksonFixedWeekdayRelativeToFixed::new).collect(toList());
  }

  @Override
  public List<EthiopianOrthodoxHoliday> ethiopianOrthodoxHolidays() {
    return holidays.getEthiopianOrthodoxHoliday().stream().map(JacksonEthiopianOrthodoxHoliday::new).collect(toList());
  }

  @Override
  public List<RelativeToEasterSunday> relativeToEasterSunday() {
    return holidays.getRelativeToEasterSunday().stream().map(JacksonRelativeToEasterSunday::new).collect(toList());
  }
}
