package de.focus_shift.jollyday.core;

import org.jspecify.annotations.NonNull;
import org.jspecify.annotations.Nullable;

import java.net.URL;
import java.util.Optional;
import java.util.Properties;

/**
 * Defines a contract for managing and accessing configuration parameters
 * used by a manager component.
 *
 * <p>This interface provides methods to read, write, and merge properties,
 * as well as to retrieve specific configuration values such as implementation
 * class names and resource locations.</p>
 *
 * <p>Implementations are expected to handle property storage and ensure
 * consistency when merging or retrieving values.</p>
 */
public interface ManagerParameter {

  /**
   * Prefix used to identify manager implementation class properties.
   */
  String MANAGER_IMPL_CLASS_PREFIX = "manager.impl";

  /**
   * The configuration prefix for parser implementations.
   */
  String PARSER_IMPL_PREFIX = "parser.impl.";

  /**
   * Key used to identify the configuration service implementation class.
   */
  String CONFIGURATION_SERVICE_IMPL = "configuration.service.impl";

  /**
   * Merges the given properties into the current set of properties.
   * Existing keys may be overwritten depending on the implementation.
   *
   * @param properties the properties to merge; may be {@code null}
   */
  void mergeProperties(@Nullable final Properties properties);

  /**
   * Retrieves the value of a property by its key.
   *
   * @param key the property key; must not be {@code null}
   * @return an {@link Optional} containing the property value if present,
   *         otherwise an empty {@link Optional}; never {@code null}
   */
  @NonNull Optional<String> getProperty(@NonNull final String key);

  /**
   * Sets or updates the value of a property.
   *
   * @param key the property key; must not be {@code null}
   * @param value the property value; must not be {@code null}
   */
  void setProperty(@NonNull final String key, @NonNull final String value);

  /**
   * Returns the fully qualified class name of the manager implementation.
   *
   * @return the manager implementation class name; never {@code null}
   */
  @NonNull String getManagerImplClassName();

  /**
   * Returns the fully qualified class name of the parser implementation.
   *
   * @return the parser implementation class name; never {@code null}
   */
  @NonNull String getParserImplClassName(@NonNull final String className);

  /**
   * Returns the fully qualified class name of the configuration service implementation.
   *
   * @return the configuration service implementation class name; never {@code null}
   */
  @NonNull String getConfigurationServiceImplClassName();

  /**
   * Creates a cache key representing the current state of the parameters.
   *
   * <p>The cache key should uniquely identify the configuration so that
   * it can be used for caching purposes.</p>
   *
   * @return a non-null cache key string
   */
  @NonNull String createCacheKey();

  /**
   * Returns a human-readable display name for this parameter set.
   *
   * @return a non-null display name
   */
  @NonNull String getDisplayName();

  /**
   * Creates a {@link URL} pointing to a resource associated with this configuration.
   *
   * @return a non-null URL to the resource
   * @throws RuntimeException if the URL cannot be created
   */
  @NonNull URL createResourceUrl();

}
