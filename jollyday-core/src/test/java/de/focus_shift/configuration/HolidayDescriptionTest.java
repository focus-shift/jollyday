package de.focus_shift.configuration;

import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashSet;
import java.util.Properties;
import java.util.ResourceBundle;
import java.util.Set;

import static java.util.Locale.ROOT;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

class HolidayDescriptionTest {

  @Test
  void testHolidayDescriptionsCompleteness() throws IOException {

    final File folder = new File("src/main/resources/descriptions/");
    assertTrue(folder.isDirectory());

    final String baseName = "descriptions.holiday_descriptions";
    final Set<String> props = getLocalisedResourceBundleKeys();
    final ResourceBundle root = getRootResourceBundle(baseName);

    // Test that the ROOT bundle contains the superset of keys
    final Set<String> missingProps = new HashSet<>();

    for (String prop : props) {
      if (!root.containsKey(prop)) {
        missingProps.add(prop);
      }
    }

    assertTrue(missingProps.isEmpty(), "Root bundle is lacking properties: " + missingProps);
  }

  protected Set<String> getLocalisedResourceBundleKeys() throws IOException {
    final File folder = new File("src/main/resources/descriptions");
    assertTrue(folder.isDirectory());
    // Collect all localised descriptions
    final File[] descriptions = folder.listFiles((dir, name) -> name.startsWith("holiday_descriptions_") && name.endsWith(".properties"));
    assertNotNull(descriptions);
    assertTrue(descriptions.length > 0);

    final Set<String> propertiesNames = new HashSet<>();
    for (final File descriptionFile : descriptions) {
      final Properties props = new Properties();
      props.load(new FileInputStream(descriptionFile));
      propertiesNames.addAll(props.stringPropertyNames());
    }
    return propertiesNames;
  }

  protected ResourceBundle getRootResourceBundle(String baseName) {
    return ResourceBundle.getBundle(baseName, ROOT);
  }
}
