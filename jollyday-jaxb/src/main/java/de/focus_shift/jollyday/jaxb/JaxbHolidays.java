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
import org.jspecify.annotations.NonNull;

/** see {@link HolidayConfigurations} */
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
  public @NonNull List<FixedHolidayConfiguration> fixed() {
    return holidays.getFixed().stream()
        .map(JaxbFixed::new)
        .map(FixedHolidayConfiguration.class::cast)
        .toList();
  }

  /**
   * {@inheritDoc}
   *
   * @return {@inheritDoc}
   */
  @Override
  public @NonNull List<RelativeToFixedHolidayConfiguration> relativeToFixed() {
    return holidays.getRelativeToFixed().stream()
        .map(JaxbRelativeToFixed::new)
        .map(RelativeToFixedHolidayConfiguration.class::cast)
        .toList();
  }

  /**
   * {@inheritDoc}
   *
   * @return {@inheritDoc}
   */
  @Override
  public @NonNull List<RelativeToWeekdayInMonthHolidayConfiguration> relativeToWeekdayInMonth() {
    return holidays.getRelativeToWeekdayInMonth().stream()
        .map(JaxbRelativeToWeekdayInMonth::new)
        .map(RelativeToWeekdayInMonthHolidayConfiguration.class::cast)
        .toList();
  }

  /**
   * {@inheritDoc}
   *
   * @return {@inheritDoc}
   */
  @Override
  public @NonNull List<FixedWeekdayInMonthHolidayConfiguration> fixedWeekdays() {
    return holidays.getFixedWeekday().stream()
        .map(JaxbFixedWeekdayInMonth::new)
        .map(FixedWeekdayInMonthHolidayConfiguration.class::cast)
        .toList();
  }

  /**
   * {@inheritDoc}
   *
   * @return {@inheritDoc}
   */
  @Override
  public @NonNull List<ChristianHolidayConfiguration> christianHolidays() {
    return holidays.getChristianHoliday().stream()
        .map(JaxbChristianHoliday::new)
        .map(ChristianHolidayConfiguration.class::cast)
        .toList();
  }

  /**
   * {@inheritDoc}
   *
   * @return {@inheritDoc}
   */
  @Override
  public @NonNull List<IslamicHolidayConfiguration> islamicHolidays() {
    return holidays.getIslamicHoliday().stream()
        .map(JaxbIslamicHoliday::new)
        .map(IslamicHolidayConfiguration.class::cast)
        .toList();
  }

  /**
   * {@inheritDoc}
   *
   * @return {@inheritDoc}
   */
  @Override
  public @NonNull List<FixedWeekdayBetweenFixedHolidayConfiguration> fixedWeekdayBetweenFixed() {
    return holidays.getFixedWeekdayBetweenFixed().stream()
        .map(JaxbFixedWeekdayBetweenFixed::new)
        .map(FixedWeekdayBetweenFixedHolidayConfiguration.class::cast)
        .toList();
  }

  /**
   * {@inheritDoc}
   *
   * @return {@inheritDoc}
   */
  @Override
  public @NonNull List<FixedWeekdayRelativeToFixedHolidayConfiguration>
      fixedWeekdayRelativeToFixed() {
    return holidays.getFixedWeekdayRelativeToFixed().stream()
        .map(JaxbFixedWeekdayRelativeToFixed::new)
        .map(FixedWeekdayRelativeToFixedHolidayConfiguration.class::cast)
        .toList();
  }

  /**
   * {@inheritDoc}
   *
   * @return {@inheritDoc}
   */
  @Override
  public @NonNull List<EthiopianOrthodoxHolidayConfiguration> ethiopianOrthodoxHolidays() {
    return holidays.getEthiopianOrthodoxHoliday().stream()
        .map(JaxbEthiopianOrthodoxHoliday::new)
        .map(EthiopianOrthodoxHolidayConfiguration.class::cast)
        .toList();
  }

  @Override
  public @NonNull List<RelativeToEasterSundayHolidayConfiguration> relativeToEasterSunday() {
    return holidays.getRelativeToEasterSunday().stream()
        .map(JaxbRelativeToEasterSunday::new)
        .map(RelativeToEasterSundayHolidayConfiguration.class::cast)
        .toList();
  }
}
