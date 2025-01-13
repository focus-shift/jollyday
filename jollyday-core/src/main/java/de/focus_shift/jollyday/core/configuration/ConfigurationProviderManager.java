package de.focus_shift.jollyday.core.configuration;

import de.focus_shift.jollyday.core.ManagerParameter;

/**
 * Manages the configuration provider implementations and thus delivering the jollyday configuration.
 */
public class ConfigurationProviderManager {

  private final CustomConfigurationProvider customConfigurationProvider = new CustomConfigurationProvider();
  private final URLConfigurationProvider urlConfigurationProvider = new URLConfigurationProvider();
  private final ApplicationClasspathConfigurationProvider applicationClasspathConfigurationProvider = new ApplicationClasspathConfigurationProvider();
  private final BaseClasspathConfigurationProvider baseClasspathConfigurationProvider = new BaseClasspathConfigurationProvider();

  /**
   * Reads the configuration and provides them in the following hierarchy
   * <ol>
   * <li>provided from {@link ManagerParameter}</li>
   * <li>{@link CustomConfigurationProvider}</li>
   * <li>{@link URLConfigurationProvider}</li>
   * <li>{@link ApplicationClasspathConfigurationProvider}</li>
   * <li>{@link BaseClasspathConfigurationProvider}</li>
   * </ol>
   * <p>
   * the providers with the highest hierarchy overrides.
   * <p>
   * For example the {@link ManagerParameter} has the highest hierarchy and overrides all other configuration providers.
   *
   * @param parameter the configuration {@link ManagerParameter} to use
   */
  public void mergeConfigurationProperties(final ManagerParameter parameter) {
    parameter.mergeProperties(customConfigurationProvider.getProperties());
    parameter.mergeProperties(urlConfigurationProvider.getProperties());
    parameter.mergeProperties(applicationClasspathConfigurationProvider.getProperties());
    parameter.mergeProperties(baseClasspathConfigurationProvider.getProperties());
  }
}
