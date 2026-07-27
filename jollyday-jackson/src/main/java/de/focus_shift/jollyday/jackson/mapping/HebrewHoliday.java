package de.focus_shift.jollyday.jackson.mapping;

import tools.jackson.dataformat.xml.annotation.JacksonXmlProperty;

public class HebrewHoliday extends Holiday {

  @JacksonXmlProperty(localName = "type", isAttribute = true)
  protected HebrewHolidayType type;

  /**
   * Gets the value of the type property.
   *
   * @return possible object is
   * {@link HebrewHolidayType }
   */
  public HebrewHolidayType getType() {
    return type;
  }

  /**
   * Sets the value of the type property.
   *
   * @param value allowed object is
   *              {@link HebrewHolidayType }
   */
  public void setType(HebrewHolidayType value) {
    this.type = value;
  }

}
