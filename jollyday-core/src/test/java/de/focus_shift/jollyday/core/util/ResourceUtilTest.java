package de.focus_shift.jollyday.core.util;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;
import java.util.Locale;
import java.util.ResourceBundle;

import static org.assertj.core.api.Assertions.assertThat;

class ResourceUtilTest {

  private Locale originalDefaultLocale;

  @BeforeEach
  void setUp() {
    originalDefaultLocale = Locale.getDefault();
  }

  @AfterEach
  void tearDown() {
    Locale.setDefault(originalDefaultLocale);
    clearCaches();
  }

  @Test
  void ensureEnglishCountryDescriptionIsReturnedWithGermanDefaultLocale() {
    // Clear caches to ensure fresh bundle loading
    clearCaches();

    // Set default locale to German - this is the scenario that caused the bug
    Locale.setDefault(Locale.GERMANY);

    // Request English description for Germany
    final String description = ResourceUtil.getCountryDescription(Locale.ENGLISH, "de");

    // Should return English "Germany", not German "Deutschland"
    assertThat(description)
      .as("Country description for 'de' with Locale.ENGLISH should be in English even when default locale is German")
      .isEqualTo("Germany");
  }

  @Test
  void ensureEnglishHolidayDescriptionIsReturnedWithGermanDefaultLocale() {
    // Clear caches to ensure fresh bundle loading
    clearCaches();

    // Set default locale to German
    Locale.setDefault(Locale.GERMANY);

    // Request English holiday description for New Year
    final String description = ResourceUtil.getHolidayDescription(Locale.ENGLISH, "NEW_YEAR");

    // Should return English "New Year's Day", not German "Neujahr"
    assertThat(description)
      .as("Holiday description for 'NEW_YEAR' with Locale.ENGLISH should be in English even when default locale is German")
      .isEqualTo("New Year's Day");
  }

  @Test
  void ensureGermanCountryDescriptionIsReturnedWithGermanDefaultLocale() {
    // Clear caches to ensure fresh bundle loading
    clearCaches();

    // Set default locale to German
    Locale.setDefault(Locale.GERMANY);

    // Request German description for Germany
    final String description = ResourceUtil.getCountryDescription(Locale.GERMANY, "de");

    // Should return German "Deutschland"
    assertThat(description)
      .as("Country description for 'de' with Locale.GERMANY should be in German")
      .isEqualTo("Deutschland");
  }

  @Test
  void ensureGermanHolidayDescriptionIsReturnedWithGermanDefaultLocale() {
    // Clear caches to ensure fresh bundle loading
    clearCaches();

    // Set default locale to German
    Locale.setDefault(Locale.GERMANY);

    // Request German holiday description for New Year
    final String description = ResourceUtil.getHolidayDescription(Locale.GERMANY, "NEW_YEAR");

    // Should return German "Neujahr"
    assertThat(description)
      .as("Holiday description for 'NEW_YEAR' with Locale.GERMANY should be in German")
      .isEqualTo("Neujahr");
  }

  @Test
  void ensureEnglishCountryDescriptionIsReturnedWithFrenchDefaultLocale() {
    // Clear caches to ensure fresh bundle loading
    clearCaches();

    // Set default locale to French
    Locale.setDefault(Locale.FRANCE);

    // Request English description for France
    final String description = ResourceUtil.getCountryDescription(Locale.ENGLISH, "fr");

    // Should return English "France", not French "France" (same in this case, but let's use a different one)
    // Let's use "de" to get "Germany" vs "Allemagne"
    final String germanyDescription = ResourceUtil.getCountryDescription(Locale.ENGLISH, "de");
    assertThat(germanyDescription)
      .as("Country description for 'de' with Locale.ENGLISH should be in English even when default locale is French")
      .isEqualTo("Germany");
  }

  @Test
  void ensureUndefinedIsReturnedForNonexistentKey() {
    // Clear caches to ensure fresh bundle loading
    clearCaches();

    Locale.setDefault(Locale.GERMANY);

    final String countryDescription = ResourceUtil.getCountryDescription(Locale.ENGLISH, "zz");
    assertThat(countryDescription)
      .isEqualTo(ResourceUtil.UNDEFINED);

    final String holidayDescription = ResourceUtil.getHolidayDescription(Locale.ENGLISH, "NONEXISTENT_HOLIDAY");
    assertThat(holidayDescription)
      .isEqualTo(ResourceUtil.UNDEFINED);
  }

  @Test
  void ensureUndefinedIsReturnedForNullCountryKey() {
    final String description = ResourceUtil.getCountryDescription(Locale.ENGLISH, null);
    assertThat(description)
      .isEqualTo(ResourceUtil.UNDEFINED);
  }

  @Test
  void ensureEnglishDescriptionWithUsDefaultLocale() {
    // Clear caches to ensure fresh bundle loading
    clearCaches();

    // Set default locale to US
    Locale.setDefault(Locale.US);

    // Request English description - should still work
    final String description = ResourceUtil.getCountryDescription(Locale.ENGLISH, "de");
    assertThat(description)
      .isEqualTo("Germany");
  }

  @Test
  void getCountryDescriptionWithDefaultLocaleReturnsDescriptionForDefaultLocale() {
    clearCaches();

    Locale.setDefault(Locale.GERMANY);
    final String description = ResourceUtil.getCountryDescription("de");
    assertThat(description)
      .as("getCountryDescription(String) with German default locale should return German description")
      .isEqualTo("Deutschland");

    clearCaches();
    Locale.setDefault(Locale.ENGLISH);
    final String descriptionAfterReset = ResourceUtil.getCountryDescription("de");
    assertThat(descriptionAfterReset)
      .as("getCountryDescription(String) with English default locale should return English description")
      .isEqualTo("Germany");
  }

  @Test
  void getHolidayDescriptionWithDefaultLocaleReturnsDescriptionForDefaultLocale() {
    clearCaches();

    Locale.setDefault(Locale.GERMANY);
    final String description = ResourceUtil.getHolidayDescription("NEW_YEAR");
    assertThat(description)
      .as("getHolidayDescription(String) with German default locale should return German description")
      .isEqualTo("Neujahr");

    clearCaches();
    Locale.setDefault(Locale.ENGLISH);
    final String descriptionAfterReset = ResourceUtil.getHolidayDescription("NEW_YEAR");
    assertThat(descriptionAfterReset)
      .as("getHolidayDescription(String) with English default locale should return English description")
      .isEqualTo("New Year's Day");
  }

  @Test
  void getResourceReturnsOptionalPresentForExistingResource() {
    final var resource = ResourceUtil.getResource("jollyday.properties");
    assertThat(resource)
      .as("getResource should return a present Optional for existing resource")
      .isPresent();
    assertThat(resource.get().getFile())
      .endsWith("jollyday.properties");
  }

  @Test
  void getResourceReturnsOptionalEmptyForNonexistentResource() {
    final var resource = ResourceUtil.getResource("this_file_does_not_exist_12345.properties");
    assertThat(resource)
      .as("getResource should return an empty Optional for nonexistent resource")
      .isEmpty();
  }

  @Test
  void getResourceWithSearchOnlyInJarReturnsEmptyForClasspathResource() {
    // jollyday.properties is on the classpath, not in a JAR
    final var resource = ResourceUtil.getResource("jollyday.properties", true);
    assertThat(resource)
      .as("getResource with searchOnlyInJar=true should return empty for classpath resource")
      .isEmpty();
  }

  @Test
  void getResourceWithSearchOnlyInJarReturnsEmptyForNonexistentResource() {
    final var resource = ResourceUtil.getResource("nonexistent.properties", true);
    assertThat(resource)
      .as("getResource with searchOnlyInJar=true should return empty for nonexistent resource")
      .isEmpty();
  }

  @Test
  void undefinedConstantHasExpectedValue() {
    assertThat(ResourceUtil.UNDEFINED)
      .isEqualTo("undefined");
  }

  @Test
  void getCountryDescriptionUsesDefaultLocaleWhenNoLocaleProvided() {
    clearCaches();

    // With French default locale, calling the single-arg overload should return French text
    Locale.setDefault(Locale.FRANCE);
    final String description = ResourceUtil.getCountryDescription("de");
    assertThat(description)
      .as("getCountryDescription(String) with French default locale should return French description")
      .isEqualTo("Allemagne");
  }

  @Test
  void getHolidayDescriptionUsesDefaultLocaleWhenNoLocaleProvided() {
    clearCaches();

    // With French default locale, calling the single-arg overload should return French text
    Locale.setDefault(Locale.FRANCE);
    final String description = ResourceUtil.getHolidayDescription("NEW_YEAR");
    assertThat(description)
      .as("getHolidayDescription(String) with French default locale should return French description")
      .isEqualTo("Jour de l'An");
  }

  /**
   * Clears the static caches in ResourceUtil using reflection.
   * This is necessary to ensure tests run with a clean slate,
   * as the ResourceBundle caching is static and persistent.
   */
  private void clearCaches() {
    try {
      final Field countryCacheField = ResourceUtil.class.getDeclaredField("COUNTRY_DESCRIPTIONS_CACHE");
      countryCacheField.setAccessible(true);
      @SuppressWarnings("unchecked")
      final java.util.Map<Locale, ResourceBundle> countryCache = (java.util.Map<Locale, ResourceBundle>) countryCacheField.get(null);
      countryCache.clear();

      final Field holidayCacheField = ResourceUtil.class.getDeclaredField("HOLIDAY_DESCRIPTION_CACHE");
      holidayCacheField.setAccessible(true);
      @SuppressWarnings("unchecked")
      final java.util.Map<Locale, ResourceBundle> holidayCache = (java.util.Map<Locale, ResourceBundle>) holidayCacheField.get(null);
      holidayCache.clear();
    } catch (NoSuchFieldException | IllegalAccessException e) {
      throw new RuntimeException("Failed to clear ResourceUtil caches", e);
    }
  }
}
