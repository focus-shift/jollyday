package de.jollyday.util;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * @author sven
 */
public class ClassLoadingUtilTest {

  ClassLoadingUtil classLoadingUtil = new ClassLoadingUtil();

  @Test
  public void testGetClassloader() {
    assertSame(Thread.currentThread().getContextClassLoader(),
      classLoadingUtil.getClassloader(),
      "Expected the current threads classloader.");
  }

  @Test
  public void testClassNotFound() throws ClassNotFoundException {
    assertThrows(ClassNotFoundException.class, () -> classLoadingUtil.loadClass(""));
  }

  @Test
  public void testClassloadingCorrect() throws Exception {
    assertSame(ClassLoadingUtil.class, classLoadingUtil.loadClass(ClassLoadingUtil.class.getName()));
  }

}
