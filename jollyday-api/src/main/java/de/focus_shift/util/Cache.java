package de.focus_shift.util;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Cache implementation which handles concurrent access to cached values.
 *
 * @param <VALUE> the type of cached values
 */
public class Cache<VALUE> {
  /**
   * Map for caching
   */
  private final Map<String, VALUE> cachingMap = new ConcurrentHashMap<>();

  /**
   * Returns the value defined by the {@link ValueHandler}
   *
   * @param valueHandler which creates the key and the value if necessary
   * @return the eventually cached value
   */
  public VALUE get(ValueHandler<VALUE> valueHandler) {
    final String key = valueHandler.getKey();
    // Try to first get the value which is most likely cached to avoid creating a lambda.
    final VALUE value = cachingMap.get(key);
    return value != null ? value : cachingMap.computeIfAbsent(key, k -> valueHandler.createValue());
  }

  /**
   * Clears the cache.
   */
  public void clear() {
    cachingMap.clear();
  }

  public interface ValueHandler<VALUE> {
    String getKey();

    VALUE createValue();
  }

}
