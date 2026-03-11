package de.focus_shift.jollyday.core.configuration;

import org.jspecify.annotations.NonNull;

import java.util.Properties;

/**
 * Interface for the configuration providers
 */
public interface ConfigurationProvider {

  /**
   * @return the configuration properties for jollyday.
   */
  @NonNull Properties getProperties();
}
