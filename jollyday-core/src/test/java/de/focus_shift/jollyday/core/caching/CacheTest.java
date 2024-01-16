package de.focus_shift.jollyday.core.caching;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class CacheTest {

  @Test
  void ensureToWriteIntoCacheAndGetSameValueWithSameKey() {
    final int[] callCounter = new int[]{0};

    final Cache.ValueHandler<String> valueHandler = new Cache.ValueHandler<>() {
      @Override
      public String getKey() {
        return "key";
      }

      @Override
      public String createValue() {
        callCounter[0]++;
        return "value";
      }
    };

    final Cache<String> stringCache = new Cache<>();
    assertThat(stringCache.get(valueHandler)).isEqualTo("value");
    assertThat(stringCache.get(valueHandler)).isEqualTo("value");
    assertThat(callCounter[0]).isEqualTo(1);
  }

  @Test
  void ensureToWriteIntoCacheWithDifferentKeys() {
    final int[] callCounter = new int[]{0, 0};

    final Cache.ValueHandler<String> valueHandler = new Cache.ValueHandler<>() {
      @Override
      public String getKey() {
        return String.valueOf(callCounter[1]++);
      }

      @Override
      public String createValue() {
        return "value" + callCounter[0]++;
      }
    };

    final Cache<String> stringCache = new Cache<>();
    assertThat(stringCache.get(valueHandler)).isEqualTo("value0");
    assertThat(stringCache.get(valueHandler)).isEqualTo("value1");
    assertThat(callCounter[0]).isEqualTo(2);
  }

  @Test
  void ensureToClearTheCacheSoThatTheSecondCallWasNotCachedAndMustBeCreatedAgain() {
    final int[] callCounter = new int[]{0};

    final Cache.ValueHandler<String> valueHandler = new Cache.ValueHandler<>() {
      @Override
      public String getKey() {
        return "key";
      }

      @Override
      public String createValue() {
        callCounter[0]++;
        return "value";
      }
    };

    final Cache<String> stringCache = new Cache<>();
    assertThat(stringCache.get(valueHandler)).isEqualTo("value");
    stringCache.clear();
    assertThat(stringCache.get(valueHandler)).isEqualTo("value");
    assertThat(callCounter[0]).isEqualTo(2);
  }
}
