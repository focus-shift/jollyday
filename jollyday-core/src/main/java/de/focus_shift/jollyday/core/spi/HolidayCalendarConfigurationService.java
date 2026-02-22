package de.focus_shift.jollyday.core.spi;

import de.focus_shift.jollyday.core.ManagerParameter;

/**
 * This interface provides the way to receive the serialized holiday configuration
 * on the base of the xml files for a specific country or special calendar.
 */
public interface HolidayCalendarConfigurationService {

  /**
   * Returns the holiday calendar configuration based on the {@link ManagerParameter}.
   * <p>
   * In most cases the configuration is based on the countries ISO code 3166.
   *
   * @param parameter to identify the country or special calendar configuration
   * @return the holiday configuration of a country
   */
  HolidayCalendarConfiguration getHolidayCalendarConfiguration(ManagerParameter parameter);

}
