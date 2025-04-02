package de.focus_shift.jollyday.core;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Objects;
import java.util.StringJoiner;

import static de.focus_shift.jollyday.core.util.ResourceUtil.UNDEFINED;
import static de.focus_shift.jollyday.core.util.ResourceUtil.getCountryDescription;

/**
 * Bean class for describing the configuration hierarchy.
 */
public final class CalendarHierarchy {

  private final String id;
  private final CalendarHierarchy parent;
  private final String fallbackDescription;
  private final Map<String, CalendarHierarchy> children = new HashMap<>();

  /**
   * Constructor which takes a eventually existing parent hierarchy node and
   * the ID of this hierarchy.
   *
   * @param id     a {@link String} object.
   * @param parent a {@link CalendarHierarchy} object.
   */
  public CalendarHierarchy(final String id, final String fallbackDescription, final CalendarHierarchy parent) {
    this.id = id;
    this.parent = parent;
    this.fallbackDescription = fallbackDescription;
  }

  /**
   * <p>
   * Getter for the field <code>id</code>.
   * </p>
   *
   * @return the id
   */
  public String getId() {
    return id;
  }

  /**
   * Returns the country description for the default locale.
   *
   * @return the description
   */
  public String getDescription() {
    return receiveFallbackDescription(getCountryDescription(getPropertiesKey()));
  }

  /**
   * Returns the hierarchies description text from the resource bundle.
   *
   * @param locale Locale to return the description text for.
   * @return Description text
   */
  public String getDescription(Locale locale) {
    return receiveFallbackDescription(getCountryDescription(locale, getPropertiesKey()));
  }

  /**
   * <p>
   * Getter for the field <code>children</code>.
   * </p>
   *
   * @return the children
   */
  public Map<String, CalendarHierarchy> getChildren() {
    return children;
  }

  @Override
  public boolean equals(Object obj) {
    if (!(obj instanceof CalendarHierarchy)) {
      return false;
    }

    final CalendarHierarchy that = (CalendarHierarchy) obj;
    return Objects.equals(id, that.id);
  }

  @Override
  public int hashCode() {
    return Objects.hashCode(id);
  }

  @Override
  public String toString() {
    return new StringJoiner(", ", CalendarHierarchy.class.getSimpleName() + "[", "]")
      .add("id='" + id + "'")
      .add("parentId='" + (parent != null ? parent.getId() : "") + "'")
      .add("description='" + getDescription() + "'")
      .add("children=" + children)
      .toString();
  }

  /**
   * Recursively returns the properties key to retrieve the description from
   * the localized resource bundle.
   */
  private String getPropertiesKey() {
    if (parent != null) {
      return parent.getPropertiesKey() + "." + getId();
    }
    return getId();
  }

  private String receiveFallbackDescription(final String description) {
    if (UNDEFINED.equals(description) && fallbackDescription != null) {
      return fallbackDescription;
    }
    return description;
  }
}
