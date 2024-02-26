package de.focus_shift.jollyday.core.descriptions;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Enumeration;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.MissingResourceException;
import java.util.ResourceBundle;
import java.util.Set;

import static java.util.Collections.list;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.fail;

class CountryDescriptionsTest {

  private static final int NUMBER_OF_ISO_COUNTRIES = 248;

  @Test
  void ensureThatAllISOCodesAreAvailableInResourceBundleGenerally() {
    getResourceBundles("descriptions.country_descriptions").forEach(resourceBundle -> {
        final Set<String> isoCodes = getISOCodes(resourceBundle);
        assertThat(isoCodes)
          .isNotNull()
          .hasSize(NUMBER_OF_ISO_COUNTRIES);
      }
    );
  }

  @Test
  void ensuresThatAllCountryDescriptionKeysAreAvailableInTheBaseResource() {
    final List<ResourceBundle> countryDescriptions = new ArrayList<>(getResourceBundles("descriptions.country_descriptions"));
    for (int i = 0; i < countryDescriptions.size(); i++) {
      for (int j = i + 1; j < countryDescriptions.size(); j++) {
        compareL1WithL2(countryDescriptions.get(i), countryDescriptions.get(j));
        compareL1WithL2(countryDescriptions.get(j), countryDescriptions.get(i));
      }
    }
  }

  private void compareL1WithL2(ResourceBundle l1, ResourceBundle l2) {
    final Locale locale = "".equals(l2.getLocale().getCountry()) ? Locale.ENGLISH : l2.getLocale();
    final Enumeration<String> keys = l1.getKeys();
    final Set<String> l2KeySet = new HashSet<>(list(l2.getKeys()));
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

  public static Set<ResourceBundle> getResourceBundles(final String baseName) {
    final Set<ResourceBundle> resourceBundles = new HashSet<>();

    for (Locale locale : Locale.getAvailableLocales()) {
      try {
        resourceBundles.add(ResourceBundle.getBundle(baseName, locale));
      } catch (MissingResourceException ex) {
        // do nothing
      }
    }

    return Collections.unmodifiableSet(resourceBundles);
  }

  public Set<String> getISOCodes(final ResourceBundle resourceBundle) {
    final Set<String> codes = new HashSet<>();
    for (final String property : resourceBundle.keySet()) {
      final String[] split = property.split("\\.");
      if (split.length > 2) {
        codes.add(split[2].toLowerCase());
      }
    }
    return codes;
  }
}
