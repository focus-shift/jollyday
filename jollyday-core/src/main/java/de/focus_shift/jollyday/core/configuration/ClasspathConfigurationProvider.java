package de.focus_shift.jollyday.core.configuration;

import de.focus_shift.jollyday.core.util.ResourceUtil;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Properties;

/**
 * An {@link ConfigurationProvider} implementation which reads the
 * 'jollyday.properties' default configuration file from the classpath
 * and provides the configuration.
 */
class ClasspathConfigurationProvider implements ConfigurationProvider {

  private static final String DEFAULT_CONFIGURATION_FILE_NAME = "jollyday.properties";
  private URL configurationFile;

  public ClasspathConfigurationProvider() {
    this.configurationFile = getConfigurationFile(DEFAULT_CONFIGURATION_FILE_NAME);
  }

  @Override
  public Properties getProperties() {
    return mapConfigurationFromUrl(configurationFile);
  }

  public void overrideConfigurationFileName(final String configurationFile) {
    this.configurationFile = getConfigurationFile(configurationFile);
  }

  private Properties mapConfigurationFromUrl(final URL url) {
    final Properties properties = new Properties();

    try (final InputStream inputStream = url.openStream()) {
      properties.load(inputStream);
    } catch (IOException e) {
      throw new IllegalStateException("Could not load default configuration from classpath.", e);
    }

    return properties;
  }

  private URL getConfigurationFile(final String configurationFileName) {
    final URL configuration = ResourceUtil.getResource(configurationFileName);
    if (configuration == null) {
      throw new IllegalStateException("Configuration file '" + configurationFileName + "' not found on classpath.");
    }
    return configuration;
  }
}
