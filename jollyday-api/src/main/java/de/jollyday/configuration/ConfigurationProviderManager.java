package de.jollyday.configuration;

import de.jollyday.ManagerParameter;
import de.jollyday.configuration.impl.DefaultConfigurationProvider;
import de.jollyday.configuration.impl.URLConfigurationProvider;
import de.jollyday.util.ClassLoadingUtil;

import java.util.logging.Logger;

/**
 * Manages the configuration provider implementations and thus delivering the
 * jollyday configuration.
 *
 * @author Sven Diedrichsen
 */
public class ConfigurationProviderManager {

  private static final Logger LOG = Logger.getLogger(ConfigurationProviderManager.class.getName());

  private final ConfigurationProvider defaultConfigurationProvider = new DefaultConfigurationProvider();
  private final ConfigurationProvider urlConfigurationProvider = new URLConfigurationProvider();
  private final ClassLoadingUtil classLoadingUtil = new ClassLoadingUtil();

  /**
   * Reads the jollyday configuration from the
   * {@link DefaultConfigurationProvider}, the
   * {@link URLConfigurationProvider} and any configuration provider specified
   * by the system property 'de.jollyday.config.provider'.
   *
   * @param parameter the configuration {@link ManagerParameter} to use
   */
  public void mergeConfigurationProperties(ManagerParameter parameter) {
    addInternalConfigurationProviderProperties(parameter);
    addCustomConfigurationProviderProperties(parameter);
  }

  private void addInternalConfigurationProviderProperties(ManagerParameter parameter) {
    parameter.mergeProperties(urlConfigurationProvider.getProperties());
    parameter.mergeProperties(defaultConfigurationProvider.getProperties());
  }

  private void addCustomConfigurationProviderProperties(ManagerParameter parameter) {
    final String providersStrList = System.getProperty(ConfigurationProvider.CONFIG_PROVIDERS_PROPERTY);
    if (providersStrList != null) {
      final String[] providersClassNames = providersStrList.split(",");
      for (String providerClassName : providersClassNames) {
        if (providerClassName == null || "".equals(providerClassName))
          continue;
        try {
          final Class<?> providerClass = Class.forName(providerClassName.trim(), true, classLoadingUtil.getClassloader());
          final ConfigurationProvider configurationProvider = ConfigurationProvider.class.cast(providerClass.getDeclaredConstructor().newInstance());
          parameter.mergeProperties(configurationProvider.getProperties());
        } catch (Exception e) {
          LOG.warning("Cannot load configuration from provider class '" + providerClassName + "'. " + e.getClass().getSimpleName() + " (" + e.getMessage() + ").");
        }
      }
    }
  }
}
