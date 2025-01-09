package de.focus_shift.jollyday.core.configuration;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Objects;
import java.util.Properties;

import static java.util.Arrays.stream;
import static java.util.function.Predicate.not;

/**
 * An {@link ConfigurationProvider} implementation which reads a list of URLs
 * provided by the system property 'config.urls' in order they are
 * provided into a {@link Properties} instance.
 */
class URLConfigurationProvider implements ConfigurationProvider {

  /**
   * System property to define URLs to overriding jollyday configuration files.
   */
  static final String CONFIG_URLS_PROPERTY = "de.focus_shift.jollyday.config.urls";

  private static final Logger LOG = LoggerFactory.getLogger(URLConfigurationProvider.class);

  URLConfigurationProvider() {
    // ok
  }

  /**
   * Returns the properties by reading from the URLs provided by the system
   * property 'config.urls'.
   */
  @Override
  public Properties getProperties() {
    final Properties properties = new Properties();
    final String configURLs = System.getProperty(CONFIG_URLS_PROPERTY);

    if (configURLs != null) {
      stream(configURLs.split(","))
        .filter(not(String::isEmpty))
        .map(String::trim)
        .map(this::createUrl)
        .filter(Objects::nonNull)
        .forEach(url -> readPropertiesFromURL(properties, url));
    }

    return properties;
  }

  private void readPropertiesFromURL(final Properties properties, final URL url) {
    try (final InputStream inputStream = url.openStream()) {
      properties.load(inputStream);
    } catch (IOException e) {
      throw new IllegalStateException("Could not load default properties from classpath.", e);
    }
  }

  private URL createUrl(final String stringUrl) {
    URL url = null;
    try {
      url = new URL(stringUrl);
    } catch (MalformedURLException e) {
      LOG.warn("Cannot read configuration from '{}' with message '{}'.", stringUrl, e.getMessage());
    }
    return url;
  }
}
