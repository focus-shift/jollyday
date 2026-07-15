package de.focus_shift.jollyday.core.util;

import org.jspecify.annotations.NonNull;
import org.jspecify.annotations.Nullable;

import java.net.URL;
import java.util.Collections;
import java.util.Enumeration;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Stream;

public final class ResourceUtil {

  private ResourceUtil() {
    // ok
  }

  /**
   * Property prefix for country descriptions.
   */
  private static final String COUNTRY_PROPERTY_PREFIX = "country.description";
  /**
   * The prefix of the country description file.
   */
  private static final String COUNTRY_DESCRIPTIONS_FILE_PREFIX = "descriptions.country_descriptions";
  /**
   * Cache for the country descriptions resource bundles.
   */
  private static final Map<Locale, ResourceBundle> COUNTRY_DESCRIPTIONS_CACHE = new ConcurrentHashMap<>();

  /**
   * Property prefix for holiday descriptions.
   */
  private static final String HOLIDAY_PROPERTY_PREFIX = "holiday.description";
  /**
   * The prefix of the holiday description file.
   */
  private static final String HOLIDAY_DESCRIPTIONS_FILE_PREFIX = "descriptions.holiday_descriptions";
  /**
   * Cache for the holiday descriptions resource bundles.
   */
  private static final Map<Locale, ResourceBundle> HOLIDAY_DESCRIPTION_CACHE = new ConcurrentHashMap<>();

  /**
   * Unknown constant will be returned when there is no description
   * configured.
   */
  public static final String UNDEFINED = "undefined";

  /**
   * The description read with the default locale.
   *
   * @param key a {@link java.lang.String} object.
   * @return holiday description using default locale.
   */
  public static @NonNull String getHolidayDescription(@NonNull final String key) {
    return getHolidayDescription(Locale.getDefault(), key);
  }

  /**
   * The description read with the provided locale.
   *
   * @param locale a {@link java.util.Locale} object.
   * @param key    a {@link java.lang.String} object.
   * @return holiday description using the provided locale.
   */
  public static @NonNull String getHolidayDescription(@NonNull final Locale locale, @NonNull final String key) {
    return getDescription(HOLIDAY_PROPERTY_PREFIX + "." + key, getHolidayDescriptions(locale));
  }

  /**
   * <p>
   * getCountryDescription.
   * </p>
   *
   * @param key a {@link java.lang.String} object.
   * @return the description
   */
  public static @NonNull String getCountryDescription(@NonNull final String key) {
    return getCountryDescription(Locale.getDefault(), key);
  }

  /**
   * Returns the hierarchies description text from the resource bundle.
   *
   * @param locale Locale to return the description text for.
   * @param key    a {@link java.lang.String} object.
   * @return Description text
   */
  public static @NonNull String getCountryDescription(@NonNull final Locale locale, @Nullable final String key) {
    if (key != null) {
      return getDescription(COUNTRY_PROPERTY_PREFIX + "." + key.toLowerCase(), getCountryDescriptions(locale));
    }
    return UNDEFINED;
  }

  /**
   * Returns the description from the resource bundle if the key is contained.
   * It will return 'undefined' otherwise.
   *
   * @param key    the key to get the description from
   * @param bundle the bundle to get the description
   * @return description the description behind the key
   */
  private static @NonNull String getDescription(@NonNull final String key, @NonNull final ResourceBundle bundle) {
    if (!bundle.containsKey(key)) {
      return UNDEFINED;
    }
    return bundle.getString(key);
  }

  /**
   * Returns the eventually cached ResourceBundle for the holiday
   * descriptions.
   *
   * @param locale Locale to retrieve the descriptions for.
   * @return ResourceBundle containing the descriptions for the locale.
   */
  private static @NonNull ResourceBundle getHolidayDescriptions(@NonNull final Locale locale) {
    return getResourceBundle(locale, HOLIDAY_DESCRIPTION_CACHE, HOLIDAY_DESCRIPTIONS_FILE_PREFIX);
  }

  /**
   * Returns the eventually cached ResourceBundle for the holiday
   * descriptions.
   *
   * @param locale Locale to retrieve the descriptions for.
   * @return ResourceBundle containing the descriptions for the locale.
   */
  private static @NonNull ResourceBundle getCountryDescriptions(@NonNull final Locale locale) {
    return getResourceBundle(locale, COUNTRY_DESCRIPTIONS_CACHE, COUNTRY_DESCRIPTIONS_FILE_PREFIX);
  }

  /**
   * Returns the eventually cached ResourceBundle for the descriptions.
   *
   * @param locale        Locale to retrieve the descriptions for.
   * @param resourceCache the cache to use
   * @param filePrefix    the resource bundle name prefix (without locale suffix)
   * @return ResourceBundle containing the descriptions for the locale.
   */
  private static @NonNull ResourceBundle getResourceBundle(
    @NonNull final Locale locale,
    @NonNull final Map<Locale, ResourceBundle> resourceCache,
    @NonNull final String filePrefix
  ) {
    return resourceCache.computeIfAbsent(locale, givenLocale -> {
      final ResourceBundle bundle = ResourceBundle.getBundle(filePrefix, givenLocale, ClassLoadingUtil.getClassloader());

      // ResourceBundle.getBundle(baseName, locale) consults Locale.getDefault() whenever the
      // requested locale itself has no dedicated resource file - even though a root/base bundle
      // further down the candidate chain would otherwise satisfy the request. In other words,
      // requesting e.g. Locale.ENGLISH or Locale.ITALIAN (neither has a dedicated properties file
      // here) on a JVM whose default locale happens to be one we DO ship a translation for
      // (de, el, fr, nl, pt, sv) silently returns that translation instead of the base (English)
      // bundle. A resolved bundle whose locale doesn't match the requested locale's language - and
      // isn't the root locale itself - is exactly this misfire. Requesting Locale.ROOT directly is
      // never subject to this default-locale fallback, so re-querying with it recovers the
      // intended base bundle without mutating any shared/global state.
      if (!Locale.ROOT.equals(bundle.getLocale()) && !bundle.getLocale().getLanguage().equals(givenLocale.getLanguage())) {
        return ResourceBundle.getBundle(filePrefix, Locale.ROOT, ClassLoadingUtil.getClassloader());
      }
      return bundle;
    });
  }

  /**
   * Returns the resource by URL.
   *
   * @param resourceName the name/path of the resource to load
   * @return the URL to the resource
   */
  public static @NonNull Optional<URL> getResource(@NonNull final String resourceName) {
    return getResource(resourceName, false);
  }

  /**
   * Returns the resource by URL.
   *
   * @param resourceName    the name/path of the resource to load
   * @param searchOnlyInJar if true searches for the given resourceName only in resource with the protocol 'jar'
   *                        otherwise the protocol is irrelevant
   * @return the optional URL to the resource
   */
  public static @NonNull Optional<URL> getResource(@NonNull final String resourceName, final boolean searchOnlyInJar) {
    final Stream<URL> stream = getResources(resourceName).stream();
    return searchOnlyInJar ? stream.filter(resource -> "jar".equals(resource.getProtocol())).findFirst() : stream.findFirst();
  }

  private static @NonNull List<URL> getResources(@NonNull final String resourceName) {
    final Enumeration<URL> resourcesEnum;

    try {
      resourcesEnum = ClassLoadingUtil.getClassloader().getResources(resourceName);
    } catch (Exception e) {
      throw new IllegalStateException("Cannot load resource: " + resourceName, e);
    }

    return Collections.list(resourcesEnum);
  }
}
