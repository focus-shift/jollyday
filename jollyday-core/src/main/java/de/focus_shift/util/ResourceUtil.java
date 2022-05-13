package de.focus_shift.util;

import java.net.URL;
import java.util.Collections;
import java.util.HashSet;
import java.util.Locale;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

/**
 * <p>
 * ResourceUtil class.
 * </p>
 *
 * @author Sven Diedrichsen
 * @version $Id: $
 */
public class ResourceUtil {
  /**
   * Property prefix for country descriptions.
   */
  private static final String COUNTRY_PROPERTY_PREFIX = "country.description";
  /**
   * Property prefix for holiday descriptions.
   */
  private static final String HOLIDAY_PROPERTY_PREFIX = "holiday.description";
  /**
   * The prefix of the country description file.
   */
  private static final String COUNTRY_DESCRIPTIONS_FILE_PREFIX = "descriptions.country_descriptions";
  /**
   * The prefix of the holiday descriptions file.
   */
  private static final String HOLIDAY_DESCRIPTIONS_FILE_PREFIX = "descriptions.holiday_descriptions";
  /**
   * Unknown constant will be returned when there is no description
   * configured.
   */
  public static final String UNDEFINED = "undefined";
  /**
   * Cache for the holiday descriptions resource bundles.
   */
  private static final Map<Locale, ResourceBundle> HOLIDAY_DESCRIPTION_CACHE = new ConcurrentHashMap<>();
  /**
   * Cache for the country descriptions resource bundles.
   */
  private static final Map<Locale, ResourceBundle> COUNTRY_DESCRIPTIONS_CACHE = new ConcurrentHashMap<>();
  /**
   * Util class to provide the correct classloader.
   */
  private final ClassLoadingUtil classLoadingUtil = new ClassLoadingUtil();

  /**
   * The description read with the default locale.
   *
   * @param key a {@link java.lang.String} object.
   * @return holiday description using default locale.
   */
  public String getHolidayDescription(String key) {
    return getHolidayDescription(Locale.getDefault(), key);
  }

  /**
   * The description read with the provided locale.
   *
   * @param locale a {@link java.util.Locale} object.
   * @param key    a {@link java.lang.String} object.
   * @return holiday description using the provided locale.
   */
  public String getHolidayDescription(Locale locale, String key) {
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
  public String getCountryDescription(String key) {
    return getCountryDescription(Locale.getDefault(), key);
  }

  /**
   * Returns the hierarchies description text from the resource bundle.
   *
   * @param l   Locale to return the description text for.
   * @param key a {@link java.lang.String} object.
   * @return Description text
   */
  public String getCountryDescription(Locale l, String key) {
    if (key != null) {
      return getDescription(COUNTRY_PROPERTY_PREFIX + "." + key.toLowerCase(), getCountryDescriptions(l));
    }
    return ResourceUtil.UNDEFINED;
  }

  /**
   * Returns a list of ISO codes.
   *
   * @return 2-digit ISO codes.
   */
  public Set<String> getISOCodes() {
    Set<String> codes = new HashSet<>();
    ResourceBundle countryDescriptions = getCountryDescriptions(Locale.getDefault());
    for (String property : Collections.list(countryDescriptions.getKeys())) {
      String[] split = property.split("\\.");
      if (split.length > 2) {
        codes.add(split[2].toLowerCase());
      }
    }
    return codes;
  }

  /**
   * Returns the description from the resource bundle if the key is contained.
   * It will return 'undefined' otherwise.
   *
   * @param key    the key to get the description from
   * @param bundle the bundle to get the description
   * @return description the description behind the key
   */
  private String getDescription(String key, final ResourceBundle bundle) {
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
  private ResourceBundle getHolidayDescriptions(Locale locale) {
    return getResourceBundle(locale, HOLIDAY_DESCRIPTION_CACHE, HOLIDAY_DESCRIPTIONS_FILE_PREFIX);
  }

  /**
   * Returns the eventually cached ResourceBundle for the holiday
   * descriptions.
   *
   * @param l Locale to retrieve the descriptions for.
   * @return ResourceBundle containing the descriptions for the locale.
   */
  private ResourceBundle getCountryDescriptions(Locale l) {
    return getResourceBundle(l, COUNTRY_DESCRIPTIONS_CACHE, COUNTRY_DESCRIPTIONS_FILE_PREFIX);
  }

  /**
   * Returns the eventually cached ResourceBundle for the descriptions.
   *
   * @param locale Locale to retrieve the descriptions for.
   * @return ResourceBundle containing the descriptions for the locale.
   */
  private ResourceBundle getResourceBundle(Locale locale, Map<Locale, ResourceBundle> resourceCache, String filePrefix) {
    if (!resourceCache.containsKey(locale)) {
      ResourceBundle bundle = ResourceBundle.getBundle(filePrefix, locale, classLoadingUtil.getClassloader());
      resourceCache.put(locale, bundle);
    }
    return resourceCache.get(locale);
  }

  /**
   * Returns the resource by URL.
   *
   * @param resourceName the name/path of the resource to load
   * @return the URL to the resource
   */
  public URL getResource(String resourceName) {
    try {
      URL resource = classLoadingUtil.getClassloader().getResource(resourceName);
      return resource == null ? this.getClass().getClassLoader().getResource(resourceName) : resource;
    } catch (Exception e) {
      throw new IllegalStateException("Cannot load resource: " + resourceName, e);
    }
  }

}
