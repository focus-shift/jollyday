package de.jollyday.jaxb;

import de.jollyday.spi.ChristianHoliday;
import de.jollyday.spi.EthiopianOrthodoxHoliday;
import de.jollyday.spi.FixedWeekdayBetweenFixed;
import de.jollyday.spi.FixedWeekdayInMonth;
import de.jollyday.spi.FixedWeekdayRelativeToFixed;
import de.jollyday.spi.HinduHoliday;
import de.jollyday.spi.IslamicHoliday;
import de.jollyday.spi.RelativeToEasterSunday;
import de.jollyday.spi.RelativeToFixed;
import de.jollyday.spi.RelativeToWeekdayInMonth;

import java.util.stream.Stream;

/**
 * @author sdiedrichsen
 * @version $
 * @since 15.03.20
 */
public class Holidays implements de.jollyday.spi.Holidays {

  private de.jollyday.config.Holidays jaxbHolidays;

  public Holidays(de.jollyday.config.Holidays jaxbHolidays) {
    this.jaxbHolidays = jaxbHolidays;
  }

  @Override
  public Stream<de.jollyday.spi.Fixed> fixed() {
    return jaxbHolidays.getFixed().stream().map(Fixed::new);
  }

  @Override
  public Stream<RelativeToFixed> relativeToFixed() {
    return null;
  }

  @Override
  public Stream<RelativeToWeekdayInMonth> relativeToWeekdayInMonth() {
    return null;
  }

  @Override
  public Stream<FixedWeekdayInMonth> fixedWeekdays() {
    return null;
  }

  @Override
  public Stream<ChristianHoliday> christianHolidays() {
    return null;
  }

  @Override
  public Stream<IslamicHoliday> islamicHolidays() {
    return null;
  }

  @Override
  public Stream<FixedWeekdayBetweenFixed> fixedWeekdayBetweenFixed() {
    return null;
  }

  @Override
  public Stream<FixedWeekdayRelativeToFixed> fixedWeekdayRelativeToFixed() {
    return null;
  }

  @Override
  public Stream<HinduHoliday> hinduHolidays() {
    return null;
  }

  @Override
  public Stream<EthiopianOrthodoxHoliday> ethiopianOrthodoxHolidays() {
    return null;
  }

  @Override
  public Stream<RelativeToEasterSunday> relativeToEasterSunday() {
    return null;
  }
}
