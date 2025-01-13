package de.focus_shift.jollyday.core.configuration;

import de.focus_shift.jollyday.core.ManagerParameter;

/**
 * Manages the configuration provider implementations and thus delivering the jollyday configuration.
 */
public class ConfigurationProviderManager {

  private final URLConfigurationProvider urlConfigurationProvider = new URLConfigurationProvider();
  private final ClasspathConfigurationProvider classpathConfigurationProvider = new ClasspathConfigurationProvider();
  private final CustomConfigurationProvider customConfigurationProvider = new CustomConfigurationProvider();

  /**
   * Reads the configuration and provides them in the following hierarchy
   * <ol>
   * <li>provided from {@link ManagerParameter}</li>
   * <li>{@link CustomConfigurationProvider}</li>
   * <li>{@link URLConfigurationProvider}</li>
   * <li>{@link ClasspathConfigurationProvider}</li>
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
    parameter.mergeProperties(classpathConfigurationProvider.getProperties());
  }
}
