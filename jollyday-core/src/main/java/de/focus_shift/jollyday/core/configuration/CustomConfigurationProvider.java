package de.focus_shift.jollyday.core.configuration;

import de.focus_shift.jollyday.core.util.ClassLoadingUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Properties;

import static java.util.Arrays.stream;
import static java.util.function.Predicate.not;

class CustomConfigurationProvider implements ConfigurationProvider {

  /**
   * System property to define a comma separated list of custom
   * {@link ConfigurationProvider} implementations to use for jollyday
   * configuration.
   */
  static final String CONFIG_PROVIDERS_PROPERTY = "de.focus_shift.jollyday.config.providers";

  private static final Logger LOG = LoggerFactory.getLogger(CustomConfigurationProvider.class);

  CustomConfigurationProvider() {
    // ok
  }

  /**
   * Returns the properties by reading from the URLs provided by the system
   * property 'config.urls'.
   */
  @Override
  public Properties getProperties() {

    final Properties properties = new Properties();

    final String providerClassNames = System.getProperty(CONFIG_PROVIDERS_PROPERTY);
    if(providerClassNames != null) {
      stream(providerClassNames.split(","))
        .filter(not(String::isEmpty))
        .map(String::trim)
        .forEach(providerClassName -> {
          try {
            final Class<?> providerClass = Class.forName(providerClassName, true, ClassLoadingUtil.getClassloader());
            final ConfigurationProvider configurationProvider = (ConfigurationProvider) providerClass.getDeclaredConstructor().newInstance();
            properties.putAll(configurationProvider.getProperties());
          } catch (Exception e) {
            LOG.warn("Cannot load configuration from provider class '{}'. {} ({}).", providerClassName, e.getClass().getSimpleName(), e.getMessage());
          }
        });
    }

    return properties;
  }
}
