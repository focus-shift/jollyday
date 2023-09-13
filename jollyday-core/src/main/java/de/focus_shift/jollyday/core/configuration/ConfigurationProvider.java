package de.focus_shift.jollyday.core.configuration;

import java.util.Properties;

/**
 * The interface for jollyday configuration provider.
 *
 * @author sven
 */
public interface ConfigurationProvider {

  /**
   * System property to define a comma separated list of custom
   * {@link ConfigurationProvider} implementations to use for jollyday
   * configuration.
   */
  String CONFIG_PROVIDERS_PROPERTY = "config.providers";
  /**
   * System property to define URLs to overriding jollyday configuration
   * files.
   */
  String CONFIG_URLS_PROPERTY = "config.urls";

  /**
   * @return the configuration properties for jollyday.
   */
  Properties getProperties();

}
