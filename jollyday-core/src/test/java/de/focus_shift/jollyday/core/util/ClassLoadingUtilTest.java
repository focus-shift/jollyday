package de.focus_shift.jollyday.core.util;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.Test;

class ClassLoadingUtilTest {

  @Test
  void ensureThatClassLoaderIsEqualToThreadContextClassLoader() {
    assertThat(ClassLoadingUtil.getClassloader())
        .isEqualTo(Thread.currentThread().getContextClassLoader());
  }

  @Test
  void ensureToThrowClassNotFoundException() {
    assertThatThrownBy(() -> ClassLoadingUtil.loadClass(""))
        .isInstanceOf(ClassNotFoundException.class);
  }

  @Test
  void testClassloadingCorrect() throws ClassNotFoundException {
    assertThat(ClassLoadingUtil.loadClass(ClassLoadingUtil.class.getName()))
        .isEqualTo(ClassLoadingUtil.class);
  }
}
