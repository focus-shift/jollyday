package de.focus_shift.jollyday.tests.country;

import de.focus_shift.jollyday.core.util.ResourceUtil;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.Enumeration;
import java.util.HashSet;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.fail;

/**
 * The Class ISOCodesTest.
 *
 * @author Sven
 */
class ISOCodesTest {

  private static final int NUMBER_OF_ISO_COUNTRIES = 249;

  private Locale defaultLocale;

  private ResourceUtil resourceUtil;

  /**
   * Inits
   */
  @BeforeEach
  void init() {
    defaultLocale = Locale.getDefault();
    Locale.setDefault(Locale.ENGLISH);
    resourceUtil = new ResourceUtil();
  }

  /**
   * Cleanup.
   */
  @AfterEach
  void cleanup() {
    Locale.setDefault(defaultLocale);
  }

  /**
   * Test iso codes.
   */
  @Test
  void testISOCodes() {
    Locale.setDefault(defaultLocale);
    final Set<String> isoCodes = resourceUtil.getISOCodes();
    assertThat(isoCodes)
      .isNotNull()
      .hasSize(NUMBER_OF_ISO_COUNTRIES);
  }

  /**
   * Test iso codes.
   */
  @Test
  void testISOCodesEN() {
    final Set<String> isoCodes = resourceUtil.getISOCodes();
    assertThat(isoCodes)
      .isNotNull()
      .hasSize(NUMBER_OF_ISO_COUNTRIES);
  }

  /**
   * Test iso codes.
   */
  @Test
  void testISOCodesDE() {
    Locale.setDefault(Locale.GERMANY);
    final Set<String> isoCodes = resourceUtil.getISOCodes();
    assertThat(isoCodes)
      .isNotNull()
      .hasSize(NUMBER_OF_ISO_COUNTRIES);
  }

  /**
   * Test iso codes compare en with de.
   */
  @Test
  void testISOCodesCompareENWithDE() {
    final ResourceBundle en = load(Locale.ENGLISH);
    final ResourceBundle de = load(Locale.GERMANY);
    compareL1WithL2(en, de);
    compareL1WithL2(de, en);
  }

  /**
   * Compare l1 with l2.
   *
   * @param l1 the first language
   * @param l2 the second language
   */
  private void compareL1WithL2(ResourceBundle l1, ResourceBundle l2) {
    final Locale locale = "".equals(l2.getLocale().getCountry()) ? Locale.ENGLISH : l2.getLocale();
    final Enumeration<String> keys = l1.getKeys();
    final Set<String> l2KeySet = new HashSet<>(Collections.list(l2.getKeys()));
    final StringBuilder misses = new StringBuilder();
    while (keys.hasMoreElements()) {
      final String propertyName = keys.nextElement();
      if (!l2KeySet.contains(propertyName)) {
        misses.append(locale).append(" misses ").append(propertyName).append('\n');
      }
    }
    if (misses.length() > 0) {
      fail(misses.toString());
    }
  }

  /**
   * Load.
   *
   * @param locale the locale
   * @return the properties
   */
  private ResourceBundle load(Locale locale) {
    return ResourceBundle.getBundle("descriptions.country_descriptions", locale);
  }
}
