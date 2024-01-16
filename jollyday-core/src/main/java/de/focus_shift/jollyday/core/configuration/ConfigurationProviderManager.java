package de.focus_shift.jollyday.core.configuration;

import de.focus_shift.jollyday.core.ManagerParameter;
import de.focus_shift.jollyday.core.util.ClassLoadingUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Manages the configuration provider implementations and thus delivering the jollyday configuration.
 */
public class ConfigurationProviderManager {

  private static final Logger LOG = LoggerFactory.getLogger(ConfigurationProviderManager.class);

  private ConfigurationProvider classpathConfigurationProvider = new ClasspathConfigurationProvider();
  private ConfigurationProvider urlConfigurationProvider = new URLConfigurationProvider();
  private final ClassLoadingUtil classLoadingUtil = new ClassLoadingUtil();

  /**
   * Reads the jollyday configuration from the
   * {@link ClasspathConfigurationProvider}, the
   * {@link URLConfigurationProvider} and any configuration provider specified
   * by the system property 'config.providers'.
   *
   * @param parameter the configuration {@link ManagerParameter} to use
   */
  public void mergeConfigurationProperties(ManagerParameter parameter) {
    addInternalConfigurationProviderProperties(parameter);
    addCustomConfigurationProviderProperties(parameter);
  }

  private void addInternalConfigurationProviderProperties(ManagerParameter parameter) {
    parameter.mergeProperties(urlConfigurationProvider.getProperties());
    parameter.mergeProperties(classpathConfigurationProvider.getProperties());
  }

  private void addCustomConfigurationProviderProperties(ManagerParameter parameter) {
    final String providersStrList = System.getProperty(ConfigurationProvider.CONFIG_PROVIDERS_PROPERTY);
    if (providersStrList != null) {
      final String[] providersClassNames = providersStrList.split(",");
      for (String providerClassName : providersClassNames) {
        if (providerClassName == null || providerClassName.isEmpty())
          continue;
        try {
          final Class<?> providerClass = Class.forName(providerClassName.trim(), true, classLoadingUtil.getClassloader());
          final ConfigurationProvider configurationProvider = (ConfigurationProvider) providerClass.getDeclaredConstructor().newInstance();
          parameter.mergeProperties(configurationProvider.getProperties());
        } catch (Exception e) {
          LOG.warn("Cannot load configuration from provider class '{}'. {} ({}).", providerClassName, e.getClass().getSimpleName(), e.getMessage());
        }
      }
    }
  }
}
