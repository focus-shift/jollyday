package de.focus_shift.jollyday.core.caching;

import org.jspecify.annotations.NonNull;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Cache implementation which handles concurrent access to cached values.
 *
 * @param <V> the type of cached values
 */
public class Cache<V> {

  private final Map<String, V> cachingMap;

  /**
   * Initializes the cache with a default unbounded size.
   */
  public Cache() {
    this(Integer.MAX_VALUE);
  }

  /**
   * Initializes the cache with a maximum size.
   *
   * @param maxSize the maximum number of entries to keep in the cache
   */
  public Cache(int maxSize) {
    this.cachingMap = Collections.synchronizedMap(new LinkedHashMap<>(16, 0.75f, true) {
      @Override
      protected boolean removeEldestEntry(Map.Entry<String, V> eldest) {
        return size() > maxSize;
      }
    });
  }

  /**
   * Returns the value defined by the {@link ValueHandler}
   *
   * @param valueHandler which creates the key and the value if necessary
   * @return the eventually cached value, otherwise the newly created via {@link ValueHandler#createValue}
   */
  public @NonNull V get(@NonNull final ValueHandler<V> valueHandler) {
    return cachingMap.computeIfAbsent(valueHandler.getKey(), key -> valueHandler.createValue());
  }

  /**
   * Clears the cache.
   */
  public void clear() {
    cachingMap.clear();
  }

  public interface ValueHandler<V> {

    @NonNull String getKey();

    @NonNull V createValue();
  }
}
