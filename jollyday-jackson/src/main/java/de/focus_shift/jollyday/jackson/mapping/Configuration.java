package de.focus_shift.jollyday.jackson.mapping;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

import java.util.ArrayList;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Configuration {

  @JacksonXmlElementWrapper(localName = "Holidays", useWrapping = false)
  protected Holidays holidays;
  @JacksonXmlElementWrapper(localName = "Sources", useWrapping = false)
  protected Sources sources;
  @JacksonXmlElementWrapper(localName = "SubConfigurations", useWrapping = false)
  protected List<Configuration> subConfigurations;

  @JacksonXmlProperty(localName = "hierarchy", isAttribute = true)
  protected String hierarchy;
  @JacksonXmlProperty(localName = "description", isAttribute = true)
  protected String description;

  /**
   * Gets the value of the holidays property.
   *
   * @return possible object is
   * {@link Holidays }
   */
  public Holidays getHolidays() {
    return holidays;
  }

  /**
   * Sets the value of the holidays property.
   *
   * @param value allowed object is
   *              {@link Holidays }
   */
  public void setHolidays(Holidays value) {
    this.holidays = value;
  }

  /**
   * Gets the value of the sources' property.
   *
   * @return possible object is
   * {@link Sources }
   */
  public Sources getSources() {
    return this.sources;
  }

  /**
   * Sets the value of the sources property.
   *
   * @param sources allowed object is
   *              {@link Sources }
   */
  public void setSources(Sources sources) {
    this.sources = sources;
  }

  /**
   * Gets the value of the subConfigurations property.
   *
   * <p>
   * This accessor method returns a reference to the live list,
   * not a snapshot. Therefore any modification you make to the
   * returned list will be present inside the Jakarta XML Binding object.
   * This is why there is not a <CODE>set</CODE> method for the subConfigurations property.
   *
   * <p>
   * For example, to add a new item, do as follows:
   * <pre>
   *    getSubConfigurations().add(newItem);
   * </pre>
   *
   *
   * <p>
   * Objects of the following type(s) are allowed in the list
   * {@link Configuration }
   */
  public List<Configuration> getSubConfigurations() {
    if (subConfigurations == null) {
      subConfigurations = new ArrayList<>();
    }
    return this.subConfigurations;
  }

  /**
   * Gets the value of the hierarchy property.
   *
   * @return possible object is
   * {@link String }
   */
  public String getHierarchy() {
    return hierarchy;
  }

  /**
   * Sets the value of the hierarchy property.
   *
   * @param value allowed object is
   *              {@link String }
   */
  public void setHierarchy(String value) {
    this.hierarchy = value;
  }

  /**
   * Gets the value of the description property.
   *
   * @return possible object is
   * {@link String }
   */
  public String getDescription() {
    return description;
  }

  /**
   * Sets the value of the description property.
   *
   * @param value allowed object is
   *              {@link String }
   */
  public void setDescription(String value) {
    this.description = value;
  }

}
