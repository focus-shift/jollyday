package de.focus_shift.jollyday.jaxb;

import de.focus_shift.jollyday.core.spi.HolidayCalendarConfiguration;
import de.focus_shift.jollyday.core.spi.HolidayConfigurations;

import java.util.stream.Stream;

/**
 * see {@link HolidayCalendarConfiguration}
 */
class JaxbConfiguration implements HolidayCalendarConfiguration {

  private final de.focus_shift.jollyday.jaxb.mapping.Configuration xmlConfiguration;

  JaxbConfiguration(de.focus_shift.jollyday.jaxb.mapping.Configuration xmlConfiguration) {
    this.xmlConfiguration = xmlConfiguration;
  }

  @Override
  public HolidayConfigurations holidays() {
    return new JaxbHolidays(xmlConfiguration.getHolidays());
  }

  @Override
  public Stream<HolidayCalendarConfiguration> subConfigurations() {
    return xmlConfiguration.getSubConfigurations().stream().map(JaxbConfiguration::new);
  }

  @Override
  public String hierarchy() {
    return xmlConfiguration.getHierarchy();
  }

  @Override
  public String description() {
    return xmlConfiguration.getDescription();
  }

}
