package de.focus_shift.jollyday.core.configuration;

import java.util.Properties;

/**
 * Interface for the configuration providers
 */
public interface ConfigurationProvider {

  /**
   * System property to define URLs to overriding jollyday configuration
   * files.
   */
  String CONFIG_URLS_PROPERTY = "de.focus_shift.jollyday.config.urls";

  /**
   * @return the configuration properties for jollyday.
   */
  Properties getProperties();
}
