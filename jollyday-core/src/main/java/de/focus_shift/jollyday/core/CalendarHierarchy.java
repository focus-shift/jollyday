package de.focus_shift.jollyday.core;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import static de.focus_shift.jollyday.core.util.ResourceUtil.UNDEFINED;
import static de.focus_shift.jollyday.core.util.ResourceUtil.getCountryDescription;

/**
 * Bean class for describing the configuration hierarchy.
 */
public class CalendarHierarchy {

  private final String id;
  private Map<String, CalendarHierarchy> children = new HashMap<>();
  private final CalendarHierarchy parent;
  private String fallbackDescription;

  /**
   * Constructor which takes a eventually existing parent hierarchy node and
   * the ID of this hierarchy.
   *
   * @param parent a {@link CalendarHierarchy} object.
   * @param id     a {@link java.lang.String} object.
   */
  public CalendarHierarchy(final CalendarHierarchy parent, final String id) {
    this.parent = parent;
    this.id = id;
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
   * Recursively returns the properties key to retrieve the description from
   * the localized resource bundle.
   *
   * @return
   */
  private String getPropertiesKey() {
    if (parent != null) {
      return parent.getPropertiesKey() + "." + getId();
    }
    return getId();
  }

  /**
   * {@inheritDoc}
   * <p>
   * Compares Hierarchies by id.
   */
  @Override
  public boolean equals(final Object obj) {
    if (obj instanceof CalendarHierarchy) {
      return ((CalendarHierarchy) obj).getId().equals(this.getId());
    }
    return super.equals(obj);
  }

  @Override
  public int hashCode() {
    return getId().hashCode();
  }

  /**
   * <p>
   * Setter for the field <code>children</code>.
   * </p>
   *
   * @param children the children to set
   */
  public void setChildren(final Map<String, CalendarHierarchy> children) {
    this.children = children;
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

  /**
   * @param description the fallback description
   */
  public void setFallbackDescription(final String description) {
    this.fallbackDescription = description;
  }

  private String receiveFallbackDescription(final String description) {
    if (UNDEFINED.equals(description) && fallbackDescription != null) {
      return fallbackDescription;
    }
    return description;
  }
}
