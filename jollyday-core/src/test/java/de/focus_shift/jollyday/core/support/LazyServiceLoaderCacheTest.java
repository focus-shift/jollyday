package de.focus_shift.jollyday.core.support;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class LazyServiceLoaderCacheTest {

  @Test
  void ensureServiceConfigurationErrorIsCaughtAndLoggedAsWarning() {
    // Runnable is not declared with a `uses` clause in module-info.java, so
    // ServiceLoader.load(Runnable.class) throws a ServiceConfigurationError.
    final LazyServiceLoaderCache<Runnable> sut = new LazyServiceLoaderCache<>(Runnable.class);
    assertThat(sut.getServices()).isEmpty();
    assertThat(sut.getServices()).isEmpty();
  }
}
