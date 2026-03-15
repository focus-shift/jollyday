package de.focus_shift.jollyday.core.spi;

import java.util.List;
import org.jspecify.annotations.NonNull;

/** Represents the configurations for all holidays for a specific country or special calendar. */
public interface HolidayConfigurations {

  /**
   * see {@link FixedHolidayConfiguration}
   *
   * @return list of all fixed holidays configurations
   */
  @NonNull List<FixedHolidayConfiguration> fixed();

  /**
   * see {@link RelativeToFixedHolidayConfiguration}
   *
   * @return list of relativeToFixed configurations
   */
  @NonNull List<RelativeToFixedHolidayConfiguration> relativeToFixed();

  /**
   * see {@link RelativeToWeekdayInMonthHolidayConfiguration}
   *
   * @return list of all relativeToWeekdayInMonth configurations
   */
  @NonNull List<RelativeToWeekdayInMonthHolidayConfiguration> relativeToWeekdayInMonth();

  /**
   * see {@link FixedWeekdayInMonthHolidayConfiguration}
   *
   * @return list of all fixedWeekdays configurations
   */
  @NonNull List<FixedWeekdayInMonthHolidayConfiguration> fixedWeekdays();

  /**
   * see {@link ChristianHolidayConfiguration}
   *
   * @return list of all christianHolidays configurations
   */
  @NonNull List<ChristianHolidayConfiguration> christianHolidays();

  /**
   * see {@link IslamicHolidayConfiguration}
   *
   * @return list of islamicHolidays fixed configurations
   */
  @NonNull List<IslamicHolidayConfiguration> islamicHolidays();

  /**
   * see {@link FixedWeekdayBetweenFixedHolidayConfiguration}
   *
   * @return list of all fixedWeekdayBetweenFixed configurations
   */
  @NonNull List<FixedWeekdayBetweenFixedHolidayConfiguration> fixedWeekdayBetweenFixed();

  /**
   * see {@link FixedWeekdayRelativeToFixedHolidayConfiguration}
   *
   * @return list of all fixedWeekdayRelativeToFixed configurations
   */
  @NonNull List<FixedWeekdayRelativeToFixedHolidayConfiguration> fixedWeekdayRelativeToFixed();

  /**
   * see {@link EthiopianOrthodoxHolidayConfiguration}
   *
   * @return list of all ethiopianOrthodoxHolidays configurations
   */
  @NonNull List<EthiopianOrthodoxHolidayConfiguration> ethiopianOrthodoxHolidays();

  /**
   * see {@link RelativeToEasterSundayHolidayConfiguration}
   *
   * @return list of all relativeToEasterSunday configurations
   */
  @NonNull List<RelativeToEasterSundayHolidayConfiguration> relativeToEasterSunday();
}
