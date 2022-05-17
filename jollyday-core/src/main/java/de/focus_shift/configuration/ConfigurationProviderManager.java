package de.focus_shift.configuration;

import de.focus_shift.ManagerParameter;
import de.focus_shift.configuration.impl.DefaultConfigurationProvider;
import de.focus_shift.configuration.impl.URLConfigurationProvider;
import de.focus_shift.util.ClassLoadingUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * Manages the configuration provider implementations and thus delivering the
 * jollyday configuration.
 *
 * @author Sven Diedrichsen
 */
public class ConfigurationProviderManager {

  private static final Logger LOG = LoggerFactory.getLogger(ConfigurationProviderManager.class);

  private ConfigurationProvider defaultConfigurationProvider = new DefaultConfigurationProvider();
  private ConfigurationProvider urlConfigurationProvider = new URLConfigurationProvider();
  private final ClassLoadingUtil classLoadingUtil = new ClassLoadingUtil();

  /**
   * Reads the jollyday configuration from the
   * {@link DefaultConfigurationProvider}, the
   * {@link URLConfigurationProvider} and any configuration provider specified
   * by the system property 'de.focus_shift.config.provider'.
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
          LOG.warn("Cannot load configuration from provider class '{}'. {} ({}).", providerClassName, e.getClass().getSimpleName(), e.getMessage());
        }
      }
    }
  }
}
