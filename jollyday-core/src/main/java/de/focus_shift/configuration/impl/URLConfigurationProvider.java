package de.focus_shift.configuration.impl;

import de.focus_shift.configuration.ConfigurationProvider;

import java.io.InputStream;
import java.net.URL;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * An {@link ConfigurationProvider} implementation which reads a list of URLs
 * provided by the system property 'de.focus_shift.config.urls' in order they are
 * provided into a {@link Properties} instance.
 *
 * @author Sven Diedrichsen
 */
public class URLConfigurationProvider implements ConfigurationProvider {

  private static final Logger LOG = Logger.getLogger(URLConfigurationProvider.class.getName());

  /**
   * Returns the properties by reading from the URLs provided by the system
   * property 'de.focus_shift.config.urls'.
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
      LOG.log(Level.WARNING, "Cannot read configuration from '" + strURL + "'.", e);
    } finally {
      closeStreamFromURL(strURL, inputStream);
    }
  }

  private void closeStreamFromURL(String strURL, InputStream inputStream) {
    if (inputStream != null) {
      try {
        inputStream.close();
      } catch (Exception e) {
        LOG.warning("Cannot close stream for configuration URL " + strURL + ".");
      }
    }
  }
}
