package de.focus_shift.jollyday.core.configuration;

import java.util.Properties;
import org.jspecify.annotations.NonNull;

/** Interface for the configuration providers */
public interface ConfigurationProvider {

  /**
   * @return the configuration properties for jollyday.
   */
  @NonNull Properties getProperties();
}
