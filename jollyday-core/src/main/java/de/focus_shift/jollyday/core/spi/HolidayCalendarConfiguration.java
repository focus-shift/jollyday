package de.focus_shift.jollyday.core.spi;

import java.util.stream.Stream;

/**
 * Represents the holiday calendar configuration, meta information like the hierarchy and description
 * and the sub calendar configuration for a specific {@link de.focus_shift.jollyday.core.HolidayCalendar}.
 * <ul>
 *   <li>The `holidays` contains the holiday configuration for every holiday.</li>
 *   <li>The `hierarchy` contains the ISO 3166-1 alpha-2 for countries or ISO 3166-2 code for subdivisions.</li>
 *   <li>The `description` contains further information.</li>
 *   <li>The `subConfiguration` contains the holiday configuration for subdivisions, cities, ... of the main configuration.</li>
 * </ul>
 */
public interface HolidayCalendarConfiguration {

  /**
   * Contains the ISO 3166-1 alpha-2 for countries or ISO 3166-2 code for subdivisions
   *
   * @return the code of this configuration
   */
  String hierarchy();

  /**
   * Contains further information like the name.
   *
   * @return the description of this configuration
   */
  String description();

  /**
   * Contains all holiday configuration of different types like {@link FixedHolidayConfiguration} or {@link ChristianHolidayConfiguration} e.g.
   *
   * @return all holiday configuration for this {@link de.focus_shift.jollyday.core.HolidayCalendar}
   */
  HolidayConfigurations holidays();

  /**
   * Contains the holiday configuration for subdivisions, cities, ... of the main configuration.
   * The hierarchy is built on top of the iso standard. For germany e.g. there is a main configuration for Germany
   * and 16 sub configurations for the subdivisions.
   *
   * @return all configurations for the next hierarchy level like subdivisions or cities.
   */
  Stream<HolidayCalendarConfiguration> subConfigurations();

}
