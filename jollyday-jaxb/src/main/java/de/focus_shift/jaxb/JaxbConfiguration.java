package de.focus_shift.jaxb;

import de.focus_shift.spi.Configuration;
import de.focus_shift.spi.Holidays;

import java.util.stream.Stream;

/**
 * @author sdiedrichsen
 * @version $
 * @since 15.03.20
 */
public class JaxbConfiguration implements Configuration {

  private final de.focus_shift.jaxb.mapping.Configuration xmlConfiguration;

  public JaxbConfiguration(de.focus_shift.jaxb.mapping.Configuration xmlConfiguration) {
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
