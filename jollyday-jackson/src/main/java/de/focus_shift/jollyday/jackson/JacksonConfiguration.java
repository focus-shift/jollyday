package de.focus_shift.jollyday.jackson;

import de.focus_shift.jollyday.core.spi.HolidayCalendarConfiguration;
import de.focus_shift.jollyday.core.spi.HolidayConfigurations;
import de.focus_shift.jollyday.jackson.mapping.Configuration;
import org.jspecify.annotations.NonNull;

import java.util.stream.Stream;

/**
 * see {@link HolidayCalendarConfiguration}
 */
public class JacksonConfiguration implements HolidayCalendarConfiguration {

  private final Configuration xmlConfiguration;

  public JacksonConfiguration(Configuration xmlConfiguration) {
    this.xmlConfiguration = xmlConfiguration;
  }

  /**
   * {@inheritDoc}
   *
   * @return {@inheritDoc}
   */
  @Override
  public @NonNull HolidayConfigurations holidays() {
    return new JacksonHolidays(xmlConfiguration.getHolidays());
  }

  /**
   * {@inheritDoc}
   *
   * @return {@inheritDoc}
   */
  @Override
  public @NonNull Stream<HolidayCalendarConfiguration> subConfigurations() {
    return xmlConfiguration.getSubConfigurations().stream().map(JacksonConfiguration::new);
  }
  /**
   * {@inheritDoc}
   *
   * @return {@inheritDoc}
   */

  @Override
  public @NonNull String hierarchy() {
    return xmlConfiguration.getHierarchy();
  }

  /**
   * {@inheritDoc}
   *
   * @return {@inheritDoc}
   */
  @Override
  public @NonNull String description() {
    return xmlConfiguration.getDescription();
  }

}
