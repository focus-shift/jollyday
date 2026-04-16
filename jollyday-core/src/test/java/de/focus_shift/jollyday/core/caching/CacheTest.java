package de.focus_shift.jollyday.core.caching;

import org.jspecify.annotations.NonNull;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CacheTest {

  @Test
  void testUnboundedCache() {
    Cache<String> cache = new Cache<>();

    String value1 = cache.get(new Cache.ValueHandler<>() {
      @Override @NonNull public String getKey() { return "key1"; }
      @Override @NonNull public String createValue() { return "value1"; }
    });
    String value2 = cache.get(new Cache.ValueHandler<>() {
      @Override @NonNull public String getKey() { return "key2"; }
      @Override @NonNull public String createValue() { return "value2"; }
    });

    assertEquals("value1", value1);
    assertEquals("value2", value2);
  }

  @Test
  void testBoundedCacheEviction() {
    final int[] callCount = {0};
    Cache<String> cache = new Cache<>(2);

    cache.get(new Cache.ValueHandler<>() {
      @Override @NonNull public String getKey() { return "key1"; }
      @Override @NonNull public String createValue() { callCount[0]++; return "value1"; }
    });
    cache.get(new Cache.ValueHandler<>() {
      @Override @NonNull public String getKey() { return "key2"; }
      @Override @NonNull public String createValue() { callCount[0]++; return "value2"; }
    });
    cache.get(new Cache.ValueHandler<>() {
      @Override @NonNull public String getKey() { return "key3"; }
      @Override @NonNull public String createValue() { callCount[0]++; return "value3"; }
    });

    // Re-access "key1" - it should trigger a new creation because it was evicted
    cache.get(new Cache.ValueHandler<>() {
      @Override @NonNull public String getKey() { return "key1"; }
      @Override @NonNull public String createValue() { callCount[0]++; return "value1_reloaded"; }
    });

    assertEquals(4, callCount[0]);
  }

  @Test
  void testLRUBehavior() {
    final int[] callCount = {0};
    Cache<String> cache = new Cache<>(2);

    cache.get(new Cache.ValueHandler<>() {
      @Override @NonNull public String getKey() { return "key1"; }
      @Override @NonNull public String createValue() { callCount[0]++; return "value1"; }
    });

    cache.get(new Cache.ValueHandler<>() {
      @Override @NonNull public String getKey() { return "key2"; }
      @Override @NonNull public String createValue() { callCount[0]++; return "value2"; }
    });

    // Access 1 again to make it "Recently Used"
    cache.get(new Cache.ValueHandler<>() {
      @Override @NonNull public String getKey() { return "key1"; }
      @Override @NonNull public String createValue() { callCount[0]++; return "value1"; }
    });

    // Add 3 - This should evict "key2" because "key1" was recently accessed
    cache.get(new Cache.ValueHandler<>() {
      @Override @NonNull public String getKey() { return "key3"; }
      @Override @NonNull public String createValue() { callCount[0]++; return "value3"; }
    });

    // Re-access 1 (should be cached, no new call)
    cache.get(new Cache.ValueHandler<>() {
      @Override @NonNull public String getKey() { return "key1"; }
      @Override @NonNull public String createValue() { callCount[0]++; return "relly_not_me"; }
    });
    assertEquals(3, callCount[0]);

    // Re-access 2 (should NOT be cached, should trigger a new call)
    cache.get(new Cache.ValueHandler<>() {
      @Override @NonNull public String getKey() { return "key2"; }
      @Override @NonNull public String createValue() { callCount[0]++; return "value2_reloaded"; }
    });
    assertEquals(4, callCount[0]);
  }

  @Test
  void testClear() {
    Cache<String> cache = new Cache<>();
    cache.get(new Cache.ValueHandler<>() {
      @Override @NonNull public String getKey() { return "key1"; }
      @Override @NonNull public String createValue() { return "value1"; }
    });
    cache.clear();

    final int[] callCount = {0};
    String value = cache.get(new Cache.ValueHandler<>() {
      @Override @NonNull public String getKey() { return "key1"; }
      @Override @NonNull public String createValue() {
        callCount[0]++;
        return "value1_new";
      }
    });

    assertEquals("value1_new", value);
    assertEquals(1, callCount[0]);
  }
}
