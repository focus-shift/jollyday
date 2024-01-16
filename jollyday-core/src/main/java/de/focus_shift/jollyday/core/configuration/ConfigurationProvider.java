package de.focus_shift.jollyday.core.configuration;

import java.util.Properties;

/**
 * Interface for the configuration providers
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
