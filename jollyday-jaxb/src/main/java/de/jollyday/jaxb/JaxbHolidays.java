package de.jollyday.jaxb;

import de.jollyday.spi.ChristianHoliday;
import de.jollyday.spi.EthiopianOrthodoxHoliday;
import de.jollyday.spi.Fixed;
import de.jollyday.spi.FixedWeekdayBetweenFixed;
import de.jollyday.spi.FixedWeekdayInMonth;
import de.jollyday.spi.FixedWeekdayRelativeToFixed;
import de.jollyday.spi.HinduHoliday;
import de.jollyday.spi.IslamicHoliday;
import de.jollyday.spi.RelativeToEasterSunday;
import de.jollyday.spi.RelativeToFixed;
import de.jollyday.spi.RelativeToWeekdayInMonth;

import java.util.List;

import static java.util.stream.Collectors.toList;

/**
 * @author sdiedrichsen
 * @version $
 * @since 15.03.20
 */
public class JaxbHolidays implements de.jollyday.spi.Holidays {

  private final de.jollyday.jaxb.mapping.Holidays holidays;

  public JaxbHolidays(de.jollyday.jaxb.mapping.Holidays holidays) {
    this.holidays = holidays;
  }

  @Override
  public List<Fixed> fixed() {
    return holidays.getFixed().stream().map(JaxbFixed::new).collect(toList());
  }

  @Override
  public List<RelativeToFixed> relativeToFixed() {
    return holidays.getRelativeToFixed().stream().map(JaxbRelativeToFixed::new).collect(toList());
  }

  @Override
  public List<RelativeToWeekdayInMonth> relativeToWeekdayInMonth() {
    return holidays.getRelativeToWeekdayInMonth().stream().map(JaxbRelativeToWeekdayInMonth::new).collect(toList());
  }

  @Override
  public List<FixedWeekdayInMonth> fixedWeekdays() {
    return holidays.getFixedWeekday().stream().map(JaxbFixedWeekdayInMonth::new).collect(toList());
  }

  @Override
  public List<ChristianHoliday> christianHolidays() {
    return holidays.getChristianHoliday().stream().map(JaxbChristianHoliday::new).collect(toList());
  }

  @Override
  public List<IslamicHoliday> islamicHolidays() {
    return holidays.getIslamicHoliday().stream().map(JaxbIslamicHoliday::new).collect(toList());
  }

  @Override
  public List<FixedWeekdayBetweenFixed> fixedWeekdayBetweenFixed() {
    return holidays.getFixedWeekdayBetweenFixed().stream().map(JaxbFixedWeekdayBetweenFixed::new).collect(toList());
  }

  @Override
  public List<FixedWeekdayRelativeToFixed> fixedWeekdayRelativeToFixed() {
    return holidays.getFixedWeekdayRelativeToFixed().stream().map(JaxbFixedWeekdayRelativeToFixed::new).collect(toList());
  }

  @Override
  public List<HinduHoliday> hinduHolidays() {
    return holidays.getHinduHoliday().stream().map(JaxbHinduHoliday::new).collect(toList());
  }

  @Override
  public List<EthiopianOrthodoxHoliday> ethiopianOrthodoxHolidays() {
    return holidays.getEthiopianOrthodoxHoliday().stream().map(JaxbEthiopianOrthodoxHoliday::new).collect(toList());
  }

  @Override
  public List<RelativeToEasterSunday> relativeToEasterSunday() {
    return holidays.getRelativeToEasterSunday().stream().map(JaxbRelativeToEasterSunday::new).collect(toList());
  }
}
