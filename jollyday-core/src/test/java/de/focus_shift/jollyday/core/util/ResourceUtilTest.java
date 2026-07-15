package de.focus_shift.jollyday.core.util;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

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

    // "fr" would read as "France" in both English and French, so use "de" instead to get a
    // distinguishable "Germany" vs "Allemagne"
    final String description = ResourceUtil.getCountryDescription(Locale.ENGLISH, "de");
    assertThat(description)
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

  @Test
  void ensureBaseDescriptionIsReturnedForLocaleWithoutDedicatedFileRegardlessOfDefaultLocale() {
    // The underlying JDK behavior isn't specific to Locale.ENGLISH: ANY locale without its own
    // dedicated resource file (here: Italian, which jollyday never shipped a translation for)
    // is affected the same way whenever the JVM default locale happens to be one that DOES have
    // a dedicated file (here: German).
    clearCaches();
    Locale.setDefault(Locale.GERMANY);

    final String countryDescription = ResourceUtil.getCountryDescription(Locale.ITALIAN, "de");
    assertThat(countryDescription)
      .as("Country description for 'de' with Locale.ITALIAN should fall back to the base (English) bundle, not German")
      .isEqualTo("Germany");

    final String holidayDescription = ResourceUtil.getHolidayDescription(Locale.ITALIAN, "NEW_YEAR");
    assertThat(holidayDescription)
      .as("Holiday description for 'NEW_YEAR' with Locale.ITALIAN should fall back to the base (English) bundle, not German")
      .isEqualTo("New Year's Day");
  }

  @Test
  void ensureBaseDescriptionIsReturnedForRegionalVariantOfLocaleWithoutDedicatedFile() {
    // A realistic real-world case: an application configured with a plain US English locale
    // running on a JVM whose default happens to be a fully-translated language.
    clearCaches();
    Locale.setDefault(Locale.GERMANY);

    final String description = ResourceUtil.getCountryDescription(Locale.US, "de");
    assertThat(description)
      .as("Country description for 'de' with Locale.US should be in English, not German")
      .isEqualTo("Germany");
  }

  @Test
  void ensureGetResourceBundleNeverMutatesTheJvmDefaultLocale() {
    clearCaches();
    Locale.setDefault(Locale.GERMANY);

    ResourceUtil.getCountryDescription(Locale.ENGLISH, "de");
    ResourceUtil.getHolidayDescription(Locale.ITALIAN, "NEW_YEAR");

    assertThat(Locale.getDefault())
      .as("Resolving descriptions must never change the JVM-wide default locale as a side effect")
      .isEqualTo(Locale.GERMANY);
  }

  @Test
  void ensureConcurrentResourceBundleResolutionDoesNotCorruptTheSharedDefaultLocale() throws InterruptedException {
    clearCaches();
    Locale.setDefault(Locale.GERMANY);

    final int threadCount = 8;
    final int iterationsPerThread = 200;
    final ExecutorService executor = Executors.newFixedThreadPool(threadCount + 1);
    final CountDownLatch start = new CountDownLatch(1);
    final AtomicBoolean stop = new AtomicBoolean(false);
    final AtomicInteger corruptedDefaultObservations = new AtomicInteger(0);

    try {
      // "host application" thread: unrelated code that depends on Locale.getDefault() for
      // something else entirely (e.g. date/number formatting), running concurrently with
      // jollyday's resource bundle resolution below.
      final var watcher = executor.submit(() -> {
        try {
          start.await();
        } catch (InterruptedException ignored) {
          Thread.currentThread().interrupt();
        }
        while (!stop.get()) {
          if (!Locale.GERMANY.equals(Locale.getDefault())) {
            corruptedDefaultObservations.incrementAndGet();
          }
        }
      });

      final var workers = new java.util.ArrayList<java.util.concurrent.Future<?>>();
      for (int t = 0; t < threadCount; t++) {
        final int threadIndex = t;
        workers.add(executor.submit(() -> {
          try {
            start.await();
          } catch (InterruptedException ignored) {
            Thread.currentThread().interrupt();
          }
          for (int i = 0; i < iterationsPerThread; i++) {
            // force a cache miss on (nearly) every call with distinct locales
            final Locale unique = new Locale("en", "X" + threadIndex + "_" + i);
            ResourceUtil.getCountryDescription(unique, "de");
          }
        }));
      }

      start.countDown();
      for (final var worker : workers) {
        worker.get(30, TimeUnit.SECONDS);
      }
      stop.set(true);
      watcher.get(5, TimeUnit.SECONDS);
    } catch (final Exception e) {
      throw new RuntimeException(e);
    } finally {
      executor.shutdownNow();
    }

    assertThat(corruptedDefaultObservations.get())
      .as("Locale.getDefault() must remain GERMANY for unrelated concurrent code throughout resolution")
      .isZero();
    assertThat(Locale.getDefault())
      .as("Locale.getDefault() must still be GERMANY after all concurrent resolution has finished")
      .isEqualTo(Locale.GERMANY);
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
