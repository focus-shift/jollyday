package de.focus_shift.util;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;

class ClassLoadingUtilTest {

  private final ClassLoadingUtil classLoadingUtil = new ClassLoadingUtil();

  @Test
  void testGetClassloader() {
    assertSame(Thread.currentThread().getContextClassLoader(),
      classLoadingUtil.getClassloader(),
      "Expected the current threads classloader.");
  }

  @Test
  void testClassNotFound() {
    assertThrows(ClassNotFoundException.class, () -> classLoadingUtil.loadClass(""));
  }

  @Test
  void testClassloadingCorrect() throws ClassNotFoundException {
    assertSame(ClassLoadingUtil.class, classLoadingUtil.loadClass(ClassLoadingUtil.class.getName()));
  }
}
