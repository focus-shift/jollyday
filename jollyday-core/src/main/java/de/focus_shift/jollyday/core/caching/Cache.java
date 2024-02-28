package de.focus_shift.jollyday.core.caching;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Cache implementation which handles concurrent access to cached values.
 *
 * @param <V> the type of cached values
 */
public class Cache<V> {

  private final Map<String, V> cachingMap = new ConcurrentHashMap<>();

  /**
   * Returns the value defined by the {@link ValueHandler}
   *
   * @param valueHandler which creates the key and the value if necessary
   * @return the eventually cached value, otherwise the newly created via {@link ValueHandler#createValue}
   */
  public V get(final ValueHandler<V> valueHandler) {
    return cachingMap.computeIfAbsent(valueHandler.getKey(), key -> valueHandler.createValue());
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
