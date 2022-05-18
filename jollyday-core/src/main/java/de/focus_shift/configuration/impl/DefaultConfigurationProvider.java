package de.focus_shift.configuration.impl;

import de.focus_shift.configuration.ConfigurationProvider;
import de.focus_shift.util.ResourceUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Properties;

/**
 * Provider which adds jollydays default configuration file
 * 'jollyday.properties' by reading it from the classpath by using the currents
 * threads classloader.
 *
 * @author Sven Diedrichsen
 */
public class DefaultConfigurationProvider implements ConfigurationProvider {

  private static final Logger LOG = LoggerFactory.getLogger(DefaultConfigurationProvider.class);

  /**
   * The name of the configuration file.
   */
  private static final String CONFIG_FILE = "jollyday.properties";
  /**
   * The utility to load resources.
   */
  private final ResourceUtil resourceUtil = new ResourceUtil();

  /*
   * (non-Javadoc)
   *
   * @see
   * de.focus_shift.configuration.ConfigurationProvider#addConfiguration(java
   * .util.Properties)
   */
  @Override
  public Properties getProperties() {
    final Properties properties = new Properties();
    try {
      final URL config = resourceUtil.getResource(CONFIG_FILE);
      if (config == null) {
        throw new IllegalStateException("Properties file " + CONFIG_FILE + " not found on classpath.");
      }
      try (InputStream stream = config.openStream()) {
        if (stream != null) {
          properties.load(stream);
        } else {
          LOG.warn("Could not load default properties file '{}' from classpath.", CONFIG_FILE);
        }
      }
      return properties;
    } catch (IOException e) {
      throw new IllegalStateException("Could not load default properties from classpath.", e);
    }
  }
}
