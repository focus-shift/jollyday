package de.focus_shift.jollyday.core.configuration;

import de.focus_shift.jollyday.core.ManagerParameter;

/**
 * Manages the configuration provider implementations and thus delivering the jollyday configuration.
 */
public class ConfigurationProviderManager {

  private ConfigurationProvider urlConfigurationProvider = new URLConfigurationProvider();
  private ConfigurationProvider classpathConfigurationProvider = new ClasspathConfigurationProvider();
  private ConfigurationProvider customConfigurationProvider = new CustomConfigurationProvider();

  /**
   * Reads the jollyday configuration from the
   * {@link URLConfigurationProvider}, the
   * {@link ClasspathConfigurationProvider} and
   * {@link CustomConfigurationProvider}'.
   *
   * @param parameter the configuration {@link ManagerParameter} to use
   */
  public void mergeConfigurationProperties(final ManagerParameter parameter) {
    parameter.mergeProperties(classpathConfigurationProvider.getProperties());
    parameter.mergeProperties(urlConfigurationProvider.getProperties());
    parameter.mergeProperties(customConfigurationProvider.getProperties());
  }
}
