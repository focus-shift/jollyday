package de.focus_shift.jollyday.core.configuration;

import de.focus_shift.jollyday.core.util.ResourceUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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

  private static final Logger LOG = LoggerFactory.getLogger(ClasspathConfigurationProvider.class);

  private static final String CONFIG_FILE = "jollyday.properties";

  private final ResourceUtil resourceUtil = new ResourceUtil();

  @Override
  public Properties getProperties() {
    final URL config = resourceUtil.getResource(CONFIG_FILE);
    if (config == null) {
      throw new IllegalStateException("Properties file " + CONFIG_FILE + " not found on classpath.");
    }
    return mapConfigurationFromUrl(config);
  }

  private Properties mapConfigurationFromUrl(final URL url) {
    final Properties properties = new Properties();

    try (final InputStream inputStream = url.openStream()) {
      if (inputStream != null) {
        properties.load(inputStream);
      } else {
        LOG.warn("Could not load default properties file '{}' from classpath.", url);
      }
    } catch (IOException e) {
      throw new IllegalStateException("Could not load default properties from classpath.", e);
    }

    return properties;
  }
}
