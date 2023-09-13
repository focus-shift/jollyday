package de.focus_shift.tests.util;

import de.focus_shift.util.ClassLoadingUtil;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class ClassLoadingUtilTest {

  private final ClassLoadingUtil classLoadingUtil = new ClassLoadingUtil();

  @Test
  void testGetClassloader() {
    assertThat(classLoadingUtil.getClassloader()).isEqualTo(Thread.currentThread().getContextClassLoader());
  }

  @Test
  void testClassNotFound() {
    assertThatThrownBy(() -> classLoadingUtil.loadClass("")).isInstanceOf(ClassNotFoundException.class);
  }

  @Test
  void testClassloadingCorrect() throws ClassNotFoundException {
    assertThat(classLoadingUtil.loadClass(ClassLoadingUtil.class.getName())).isEqualTo(ClassLoadingUtil.class);
  }
}
