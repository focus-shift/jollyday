package de.focus_shift.jollyday.core.caching;

import org.jspecify.annotations.NonNull;

import com.github.benmanes.caffeine.cache.Caffeine;

/**
 * Cache implementation which handles concurrent access to cached values.
 *
 * @param <V> the type of cached values
 */
public class Cache<V> {

   private final com.github.benmanes.caffeine.cache.Cache<String, V> caffeineCache;

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
     this.caffeineCache = Caffeine.newBuilder()
       .maximumSize(maxSize)
       .build();
   }

   /**
    * Returns the value defined by the {@link ValueHandler}
    *
    * @param valueHandler which creates the key and the value if necessary
    * @return the eventually cached value, otherwise the newly created via {@link ValueHandler#createValue}
    */
   public @NonNull V get(@NonNull final ValueHandler<V> valueHandler) {
     return caffeineCache.get(valueHandler.getKey(), key -> valueHandler.createValue());
   }

   /**
    * Clears the cache.
    */
   public void clear() {
     caffeineCache.invalidateAll();
   }

   public interface ValueHandler<V> {

     @NonNull String getKey();

     @NonNull V createValue();
   }
}
