package de.focus_shift.jollyday.jaxb;

import de.focus_shift.jollyday.core.spi.ChristianHoliday;
import de.focus_shift.jollyday.core.spi.EthiopianOrthodoxHoliday;
import de.focus_shift.jollyday.core.spi.Fixed;
import de.focus_shift.jollyday.core.spi.FixedWeekdayBetweenFixed;
import de.focus_shift.jollyday.core.spi.FixedWeekdayInMonth;
import de.focus_shift.jollyday.core.spi.FixedWeekdayRelativeToFixed;
import de.focus_shift.jollyday.core.spi.Holidays;
import de.focus_shift.jollyday.core.spi.IslamicHoliday;
import de.focus_shift.jollyday.core.spi.RelativeToEasterSunday;
import de.focus_shift.jollyday.core.spi.RelativeToFixed;
import de.focus_shift.jollyday.core.spi.RelativeToWeekdayInMonth;

import java.util.List;

import static java.util.stream.Collectors.toList;

/**
 * see {@link Holidays}
 */
public class JaxbHolidays implements Holidays {

  private final de.focus_shift.jollyday.jaxb.mapping.Holidays holidays;

  public JaxbHolidays(de.focus_shift.jollyday.jaxb.mapping.Holidays holidays) {
    this.holidays = holidays;
  }

  /**
   * {@inheritDoc}
   *
   * @return {@inheritDoc}
   */
  @Override
  public List<Fixed> fixed() {
    return holidays.getFixed().stream().map(JaxbFixed::new).collect(toList());
  }

  /**
   * {@inheritDoc}
   *
   * @return {@inheritDoc}
   */
  @Override
  public List<RelativeToFixed> relativeToFixed() {
    return holidays.getRelativeToFixed().stream().map(JaxbRelativeToFixed::new).collect(toList());
  }

  /**
   * {@inheritDoc}
   *
   * @return {@inheritDoc}
   */
  @Override
  public List<RelativeToWeekdayInMonth> relativeToWeekdayInMonth() {
    return holidays.getRelativeToWeekdayInMonth().stream().map(JaxbRelativeToWeekdayInMonth::new).collect(toList());
  }

  /**
   * {@inheritDoc}
   *
   * @return {@inheritDoc}
   */
  @Override
  public List<FixedWeekdayInMonth> fixedWeekdays() {
    return holidays.getFixedWeekday().stream().map(JaxbFixedWeekdayInMonth::new).collect(toList());
  }

  /**
   * {@inheritDoc}
   *
   * @return {@inheritDoc}
   */
  @Override
  public List<ChristianHoliday> christianHolidays() {
    return holidays.getChristianHoliday().stream().map(JaxbChristianHoliday::new).collect(toList());
  }

  /**
   * {@inheritDoc}
   *
   * @return {@inheritDoc}
   */
  @Override
  public List<IslamicHoliday> islamicHolidays() {
    return holidays.getIslamicHoliday().stream().map(JaxbIslamicHoliday::new).collect(toList());
  }

  /**
   * {@inheritDoc}
   *
   * @return {@inheritDoc}
   */
  @Override
  public List<FixedWeekdayBetweenFixed> fixedWeekdayBetweenFixed() {
    return holidays.getFixedWeekdayBetweenFixed().stream().map(JaxbFixedWeekdayBetweenFixed::new).collect(toList());
  }

  /**
   * {@inheritDoc}
   *
   * @return {@inheritDoc}
   */
  @Override
  public List<FixedWeekdayRelativeToFixed> fixedWeekdayRelativeToFixed() {
    return holidays.getFixedWeekdayRelativeToFixed().stream().map(JaxbFixedWeekdayRelativeToFixed::new).collect(toList());
  }

  /**
   * {@inheritDoc}
   *
   * @return {@inheritDoc}
   */
  @Override
  public List<EthiopianOrthodoxHoliday> ethiopianOrthodoxHolidays() {
    return holidays.getEthiopianOrthodoxHoliday().stream().map(JaxbEthiopianOrthodoxHoliday::new).collect(toList());
  }

  @Override
  public List<RelativeToEasterSunday> relativeToEasterSunday() {
    return holidays.getRelativeToEasterSunday().stream().map(JaxbRelativeToEasterSunday::new).collect(toList());
  }
}
