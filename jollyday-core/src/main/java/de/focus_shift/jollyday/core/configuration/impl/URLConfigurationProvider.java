package de.focus_shift.jollyday.core.configuration.impl;

import de.focus_shift.jollyday.core.configuration.ConfigurationProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.InputStream;
import java.net.URL;
import java.util.Properties;

/**
 * An {@link ConfigurationProvider} implementation which reads a list of URLs
 * provided by the system property 'config.urls' in order they are
 * provided into a {@link Properties} instance.
 *
 * @author Sven Diedrichsen
 */
public class URLConfigurationProvider implements ConfigurationProvider {

  private static final Logger LOG = LoggerFactory.getLogger(URLConfigurationProvider.class);

  /**
   * Returns the properties by reading from the URLs provided by the system
   * property 'config.urls'.
   */
  @Override
  public Properties getProperties() {
    final Properties properties = new Properties();
    final String configURLs = System.getProperty(CONFIG_URLS_PROPERTY);
    if (configURLs != null) {
      final String[] strConfigURLs = configURLs.split(",");
      for (String strURL : strConfigURLs) {
        readPropertiesFromURL(properties, strURL);
      }
    }
    return properties;
  }

  private void readPropertiesFromURL(Properties properties, String strURL) {
    if (strURL == null || "".equals(strURL)) {
      return;
    }

    InputStream inputStream = null;
    try {
      final URL configURL = new URL(strURL.trim());
      inputStream = configURL.openStream();
      properties.load(inputStream);
    } catch (Exception e) {
      LOG.warn("Cannot read configuration from '{}' with message '{}'.", strURL, e.getMessage());
    } finally {
      closeStreamFromURL(strURL, inputStream);
    }
  }

  private void closeStreamFromURL(String strURL, InputStream inputStream) {
    if (inputStream != null) {
      try {
        inputStream.close();
      } catch (Exception e) {
        LOG.warn("Cannot close stream for configuration URL {}.", strURL);
      }
    }
  }
}
