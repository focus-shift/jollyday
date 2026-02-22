package de.focus_shift.jollyday.core.spi;

import java.util.List;

/**
 * Represents the configurations for all holidays
 * for a specific country or special calendar.
 */
public interface HolidayConfigurations {

  /**
   * see {@link FixedHolidayConfiguration}
   *
   * @return list of all fixed holidays configurations
   */
  List<FixedHolidayConfiguration> fixed();

    /**
   * see {@link RelativeToFixedHolidayConfiguration}
   *
   * @return list of relativeToFixed configurations
   */
  List<RelativeToFixedHolidayConfiguration> relativeToFixed();

    /**
   * see {@link RelativeToWeekdayInMonthHolidayConfiguration}
   *
   * @return list of all relativeToWeekdayInMonth configurations
   */
  List<RelativeToWeekdayInMonthHolidayConfiguration> relativeToWeekdayInMonth();

    /**
   * see {@link FixedWeekdayInMonthHolidayConfiguration}
   *
   * @return list of all fixedWeekdays configurations
   */
  List<FixedWeekdayInMonthHolidayConfiguration> fixedWeekdays();

    /**
   * see {@link ChristianHolidayConfiguration}
   *
   * @return list of all christianHolidays configurations
   */
  List<ChristianHolidayConfiguration> christianHolidays();

    /**
   * see {@link IslamicHolidayConfiguration}
   *
   * @return list of islamicHolidays fixed configurations
   */
  List<IslamicHolidayConfiguration> islamicHolidays();

    /**
   * see {@link FixedWeekdayBetweenFixedHolidayConfiguration}
   *
   * @return list of all fixedWeekdayBetweenFixed configurations
   */
  List<FixedWeekdayBetweenFixedHolidayConfiguration> fixedWeekdayBetweenFixed();

    /**
   * see {@link FixedWeekdayRelativeToFixedHolidayConfiguration}
   *
   * @return list of all fixedWeekdayRelativeToFixed configurations
   */
  List<FixedWeekdayRelativeToFixedHolidayConfiguration> fixedWeekdayRelativeToFixed();

    /**
   * see {@link EthiopianOrthodoxHolidayConfiguration}
   *
   * @return list of all ethiopianOrthodoxHolidays configurations
   */
  List<EthiopianOrthodoxHolidayConfiguration> ethiopianOrthodoxHolidays();

    /**
   * see {@link RelativeToEasterSundayHolidayConfiguration}
   *
   * @return list of all relativeToEasterSunday configurations
   */
  List<RelativeToEasterSundayHolidayConfiguration> relativeToEasterSunday();

}
