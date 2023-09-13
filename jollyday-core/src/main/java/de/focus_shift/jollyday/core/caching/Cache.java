package de.focus_shift.jollyday.core.caching;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Cache implementation which handles concurrent access to cached values.
 *
 * @param <V> the type of cached values
 */
public class Cache<V> {
  /**
   * Map for caching
   */
  private final Map<String, V> cachingMap = new ConcurrentHashMap<>();

  /**
   * Returns the value defined by the {@link ValueHandler}
   *
   * @param valueHandler which creates the key and the value if necessary
   * @return the eventually cached value
   */
  public V get(ValueHandler<V> valueHandler) {
    final String key = valueHandler.getKey();
    // Try to first get the value which is most likely cached to avoid creating a lambda.
    final V value = cachingMap.get(key);
    return value != null ? value : cachingMap.computeIfAbsent(key, k -> valueHandler.createValue());
  }

  /**
   * Clears the cache.
   */
  public void clear() {
    cachingMap.clear();
  }

  public interface ValueHandler<V> {
    String getKey();

    V createValue();
  }
}
