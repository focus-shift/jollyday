package de.focus_shift.jollyday.core.configuration;

import de.focus_shift.jollyday.core.util.ResourceUtil;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Optional;
import java.util.Properties;

abstract class AbstractClasspathConfigurationProvider implements ConfigurationProvider {

  static final String DEFAULT_CONFIGURATION_FILE_NAME = "jollyday.properties";

  private final Properties properties;

  AbstractClasspathConfigurationProvider() {
    this(false);
  }

  AbstractClasspathConfigurationProvider(final boolean searchOnlyInJar) {
    this.properties = loadProperties(searchOnlyInJar);
  }

  @Override
  public Properties getProperties() {
    return properties;
  }

  private Properties loadProperties(final boolean searchOnlyInJar) {
    return getConfigurationFile(searchOnlyInJar)
      .map(this::toProperties)
      .orElseGet(Properties::new);
  }

  private Properties toProperties(final URL propertiesFile) {
    final Properties props = new Properties();
    try (final InputStream inputStream = propertiesFile.openStream()) {
      props.load(inputStream);
    } catch (IOException e) {
      throw new IllegalStateException("Could not load default configuration from classpath.", e);
    }
    return props;
  }

  private Optional<URL> getConfigurationFile(final boolean searchOnlyInJar) {
    return ResourceUtil.getResource(DEFAULT_CONFIGURATION_FILE_NAME, searchOnlyInJar);
  }
}
