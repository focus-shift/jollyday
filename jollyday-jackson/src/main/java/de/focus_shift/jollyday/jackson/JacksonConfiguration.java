package de.focus_shift.jollyday.jackson;

import de.focus_shift.jollyday.core.spi.Configuration;
import de.focus_shift.jollyday.core.spi.Holidays;

import java.util.stream.Stream;

/**
 * see {@link Configuration}
 */
public class JacksonConfiguration implements Configuration {

  private final de.focus_shift.jollyday.jackson.mapping.Configuration xmlConfiguration;

  public JacksonConfiguration(de.focus_shift.jollyday.jackson.mapping.Configuration xmlConfiguration) {
    this.xmlConfiguration = xmlConfiguration;
  }

  /**
   * {@inheritDoc}
   *
   * @return {@inheritDoc}
   */
  @Override
  public Holidays holidays() {
    return new JacksonHolidays(xmlConfiguration.getHolidays());
  }

  /**
   * {@inheritDoc}
   *
   * @return {@inheritDoc}
   */
  @Override
  public Stream<Configuration> subConfigurations() {
    return xmlConfiguration.getSubConfigurations().stream().map(JacksonConfiguration::new);
  }
  /**
   * {@inheritDoc}
   *
   * @return {@inheritDoc}
   */

  @Override
  public String hierarchy() {
    return xmlConfiguration.getHierarchy();
  }

  /**
   * {@inheritDoc}
   *
   * @return {@inheritDoc}
   */
  @Override
  public String description() {
    return xmlConfiguration.getDescription();
  }

}
