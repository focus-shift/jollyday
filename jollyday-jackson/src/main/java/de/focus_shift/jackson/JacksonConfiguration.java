package de.focus_shift.jackson;

import java.util.stream.Stream;

import de.focus_shift.jollyday.core.spi.Configuration;
import de.focus_shift.jollyday.core.spi.Holidays;


public class JacksonConfiguration implements Configuration {

  private final de.focus_shift.jackson.mapping.Configuration xmlConfiguration;

  public JacksonConfiguration(de.focus_shift.jackson.mapping.Configuration xmlConfiguration) {
    this.xmlConfiguration = xmlConfiguration;
  }

  @Override
  public Holidays holidays() {
    return new JacksonHolidays(xmlConfiguration.getHolidays());
  }

  @Override
  public Stream<Configuration> subConfigurations() {
    return xmlConfiguration.getSubConfigurations().stream().map(JacksonConfiguration::new);
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
