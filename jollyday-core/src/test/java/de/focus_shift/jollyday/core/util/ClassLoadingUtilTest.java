package de.focus_shift.jollyday.core.util;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class ClassLoadingUtilTest {

  @Test
  void testGetClassloader() {
    assertThat(ClassLoadingUtil.getClassloader()).isEqualTo(Thread.currentThread().getContextClassLoader());
  }

  @Test
  void testClassNotFound() {
    assertThatThrownBy(() -> ClassLoadingUtil.loadClass("")).isInstanceOf(ClassNotFoundException.class);
  }

  @Test
  void testClassloadingCorrect() throws ClassNotFoundException {
    assertThat(ClassLoadingUtil.loadClass(ClassLoadingUtil.class.getName())).isEqualTo(ClassLoadingUtil.class);
  }
}
