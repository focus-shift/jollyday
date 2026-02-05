package de.focus_shift.jollyday.jaxb;

import de.focus_shift.jollyday.core.spi.ChristianHolidayConfiguration;
import de.focus_shift.jollyday.core.spi.EthiopianOrthodoxHolidayConfiguration;
import de.focus_shift.jollyday.core.spi.FixedHolidayConfiguration;
import de.focus_shift.jollyday.core.spi.FixedWeekdayBetweenFixedHolidayConfiguration;
import de.focus_shift.jollyday.core.spi.FixedWeekdayInMonthHolidayConfiguration;
import de.focus_shift.jollyday.core.spi.FixedWeekdayRelativeToFixedHolidayConfiguration;
import de.focus_shift.jollyday.core.spi.HolidayConfigurations;
import de.focus_shift.jollyday.core.spi.IslamicHolidayConfiguration;
import de.focus_shift.jollyday.core.spi.RelativeToEasterSundayHolidayConfiguration;
import de.focus_shift.jollyday.core.spi.RelativeToFixedHolidayConfiguration;
import de.focus_shift.jollyday.core.spi.RelativeToWeekdayInMonthHolidayConfiguration;
import de.focus_shift.jollyday.jaxb.mapping.Holidays;

import java.util.List;

import static java.util.stream.Collectors.toList;

/**
 * see {@link HolidayConfigurations}
 */
public class JaxbHolidays implements HolidayConfigurations {

  private final Holidays holidays;

  public JaxbHolidays(Holidays holidays) {
    this.holidays = holidays;
  }

  /**
   * {@inheritDoc}
   *
   * @return {@inheritDoc}
   */
  @Override
  public List<FixedHolidayConfiguration> fixed() {
    return holidays.getFixed().stream().map(JaxbFixed::new).collect(toList());
  }

  /**
   * {@inheritDoc}
   *
   * @return {@inheritDoc}
   */
  @Override
  public List<RelativeToFixedHolidayConfiguration> relativeToFixed() {
    return holidays.getRelativeToFixed().stream().map(JaxbRelativeToFixed::new).collect(toList());
  }

  /**
   * {@inheritDoc}
   *
   * @return {@inheritDoc}
   */
  @Override
  public List<RelativeToWeekdayInMonthHolidayConfiguration> relativeToWeekdayInMonth() {
    return holidays.getRelativeToWeekdayInMonth().stream().map(JaxbRelativeToWeekdayInMonth::new).collect(toList());
  }

  /**
   * {@inheritDoc}
   *
   * @return {@inheritDoc}
   */
  @Override
  public List<FixedWeekdayInMonthHolidayConfiguration> fixedWeekdays() {
    return holidays.getFixedWeekday().stream().map(JaxbFixedWeekdayInMonth::new).collect(toList());
  }

  /**
   * {@inheritDoc}
   *
   * @return {@inheritDoc}
   */
  @Override
  public List<ChristianHolidayConfiguration> christianHolidays() {
    return holidays.getChristianHoliday().stream().map(JaxbChristianHoliday::new).collect(toList());
  }

  /**
   * {@inheritDoc}
   *
   * @return {@inheritDoc}
   */
  @Override
  public List<IslamicHolidayConfiguration> islamicHolidays() {
    return holidays.getIslamicHoliday().stream().map(JaxbIslamicHoliday::new).collect(toList());
  }

  /**
   * {@inheritDoc}
   *
   * @return {@inheritDoc}
   */
  @Override
  public List<FixedWeekdayBetweenFixedHolidayConfiguration> fixedWeekdayBetweenFixed() {
    return holidays.getFixedWeekdayBetweenFixed().stream().map(JaxbFixedWeekdayBetweenFixed::new).collect(toList());
  }

  /**
   * {@inheritDoc}
   *
   * @return {@inheritDoc}
   */
  @Override
  public List<FixedWeekdayRelativeToFixedHolidayConfiguration> fixedWeekdayRelativeToFixed() {
    return holidays.getFixedWeekdayRelativeToFixed().stream().map(JaxbFixedWeekdayRelativeToFixed::new).collect(toList());
  }

  /**
   * {@inheritDoc}
   *
   * @return {@inheritDoc}
   */
  @Override
  public List<EthiopianOrthodoxHolidayConfiguration> ethiopianOrthodoxHolidays() {
    return holidays.getEthiopianOrthodoxHoliday().stream().map(JaxbEthiopianOrthodoxHoliday::new).collect(toList());
  }

  @Override
  public List<RelativeToEasterSundayHolidayConfiguration> relativeToEasterSunday() {
    return holidays.getRelativeToEasterSunday().stream().map(JaxbRelativeToEasterSunday::new).collect(toList());
  }
}
