package de.focus_shift.jollyday.core.util;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * <p>
 * ClassLoadingUtil class.
 * </p>
 */
public final class ClassLoadingUtil {

  private ClassLoadingUtil() {
    // ok
  }

  private static final Logger LOG = LoggerFactory.getLogger(ClassLoadingUtil.class);

  /**
   * Loads the class by class name with the current threads context
   * classloader. If there occurs an exception the class will be loaded by
   * default classloader.
   *
   * @param className a {@link java.lang.String} object.
   * @return a {@link java.lang.Class} object.
   * @throws java.lang.ClassNotFoundException if any.
   */
  public static Class<?> loadClass(final String className) throws ClassNotFoundException {
    try {
      return Class.forName(className, true, getClassloader());
    } catch (Exception e) {
      LOG.warn("Could not load class with current threads context classloader. Using default. Reason: {}: {}", e.getClass().getSimpleName(), e.getMessage());
      return Class.forName(className);
    }
  }

  /**
   * Returns the classloader.
   *
   * @return the current classloader
   * @see Class#getClassLoader()
   */
  public static ClassLoader getClassloader() {
    return ClassLoadingUtil.class.getClassLoader();
  }
}
