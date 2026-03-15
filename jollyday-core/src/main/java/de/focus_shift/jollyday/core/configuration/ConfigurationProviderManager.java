package de.focus_shift.jollyday.core.configuration;

import de.focus_shift.jollyday.core.ManagerParameter;
import org.jspecify.annotations.NonNull;

/**
 * Manages the configuration provider implementations and thus delivering the jollyday
 * configuration.
 */
public class ConfigurationProviderManager {

  private final ProviderClassesConfigurationProvider providerClassesConfigurationProvider =
      new ProviderClassesConfigurationProvider();
  private final URLConfigurationProvider urlConfigurationProvider = new URLConfigurationProvider();
  private final ApplicationClasspathConfigurationProvider
      applicationClasspathConfigurationProvider = new ApplicationClasspathConfigurationProvider();
  private final BaseClasspathConfigurationProvider baseClasspathConfigurationProvider =
      new BaseClasspathConfigurationProvider();

  /**
   * Reads the configuration and provides them in the following hierarchy
   *
   * <ol>
   *   <li>provided from {@link ManagerParameter}
   *   <li>{@link ProviderClassesConfigurationProvider}
   *   <li>{@link URLConfigurationProvider}
   *   <li>{@link ApplicationClasspathConfigurationProvider}
   *   <li>{@link BaseClasspathConfigurationProvider}
   * </ol>
   *
   * <p>the providers with the highest hierarchy overrides.
   *
   * <p>For example, the {@link ManagerParameter} has the highest hierarchy and overrides all other
   * configuration providers.
   *
   * @param parameter the configuration {@link ManagerParameter} to use
   */
  public void mergeConfigurationProperties(@NonNull final ManagerParameter parameter) {
    parameter.mergeProperties(providerClassesConfigurationProvider.getProperties());
    parameter.mergeProperties(urlConfigurationProvider.getProperties());
    parameter.mergeProperties(applicationClasspathConfigurationProvider.getProperties());
    parameter.mergeProperties(baseClasspathConfigurationProvider.getProperties());
  }
}
