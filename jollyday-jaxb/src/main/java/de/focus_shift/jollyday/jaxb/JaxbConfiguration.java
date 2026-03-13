package de.focus_shift.jollyday.jaxb;

import de.focus_shift.jollyday.core.spi.HolidayCalendarConfiguration;
import de.focus_shift.jollyday.core.spi.HolidayConfigurations;
import de.focus_shift.jollyday.jaxb.mapping.Configuration;
import org.jspecify.annotations.NonNull;

import java.util.stream.Stream;

/**
 * see {@link HolidayCalendarConfiguration}
 */
class JaxbConfiguration implements HolidayCalendarConfiguration {

  private final Configuration xmlConfiguration;

  JaxbConfiguration(Configuration xmlConfiguration) {
    this.xmlConfiguration = xmlConfiguration;
  }

  @Override
  public @NonNull HolidayConfigurations holidays() {
    return new JaxbHolidays(xmlConfiguration.getHolidays());
  }

  @Override
  public @NonNull Stream<HolidayCalendarConfiguration> subConfigurations() {
    return xmlConfiguration.getSubConfigurations().stream().map(JaxbConfiguration::new);
  }

  @Override
  public @NonNull String hierarchy() {
    return xmlConfiguration.getHierarchy();
  }

  @Override
  public @NonNull String description() {
    return xmlConfiguration.getDescription();
  }

}
