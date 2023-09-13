package de.focus_shift.jollyday.jaxb;

import de.focus_shift.jollyday.core.Configuration;
import de.focus_shift.jollyday.core.spi.Holidays;

import java.util.stream.Stream;


public class JaxbConfiguration implements Configuration {

  private final de.focus_shift.jollyday.jaxb.mapping.Configuration xmlConfiguration;

  public JaxbConfiguration(de.focus_shift.jollyday.jaxb.mapping.Configuration xmlConfiguration) {
    this.xmlConfiguration = xmlConfiguration;
  }

  @Override
  public Holidays holidays() {
    return new JaxbHolidays(xmlConfiguration.getHolidays());
  }

  @Override
  public Stream<Configuration> subConfigurations() {
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
