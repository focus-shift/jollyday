package de.focus_shift.jollyday.core.spi;

import java.util.List;

/**
 * Represents the configurations for all holidays
 * for a specific country or special calendar.
 */
public interface Holidays {

  /**
   * see {@link Fixed}
   *
   * @return list of all fixed holidays configurations
   */
  List<Fixed> fixed();

    /**
   * see {@link RelativeToFixed}
   *
   * @return list of relativeToFixed configurations
   */
  List<RelativeToFixed> relativeToFixed();

    /**
   * see {@link RelativeToWeekdayInMonth}
   *
   * @return list of all relativeToWeekdayInMonth configurations
   */
  List<RelativeToWeekdayInMonth> relativeToWeekdayInMonth();

    /**
   * see {@link FixedWeekdayInMonth}
   *
   * @return list of all fixedWeekdays configurations
   */
  List<FixedWeekdayInMonth> fixedWeekdays();

    /**
   * see {@link ChristianHoliday}
   *
   * @return list of all christianHolidays configurations
   */
  List<ChristianHoliday> christianHolidays();

    /**
   * see {@link IslamicHoliday}
   *
   * @return list of islamicHolidays fixed configurations
   */
  List<IslamicHoliday> islamicHolidays();

    /**
   * see {@link FixedWeekdayBetweenFixed}
   *
   * @return list of all fixedWeekdayBetweenFixed configurations
   */
  List<FixedWeekdayBetweenFixed> fixedWeekdayBetweenFixed();

    /**
   * see {@link FixedWeekdayRelativeToFixed}
   *
   * @return list of all fixedWeekdayRelativeToFixed configurations
   */
  List<FixedWeekdayRelativeToFixed> fixedWeekdayRelativeToFixed();

    /**
   * see {@link EthiopianOrthodoxHoliday}
   *
   * @return list of all ethiopianOrthodoxHolidays configurations
   */
  List<EthiopianOrthodoxHoliday> ethiopianOrthodoxHolidays();

    /**
   * see {@link RelativeToEasterSunday}
   *
   * @return list of all relativeToEasterSunday configurations
   */
  List<RelativeToEasterSunday> relativeToEasterSunday();

}
