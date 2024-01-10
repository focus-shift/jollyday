package de.focus_shift.jollyday.jackson.mapping;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

public class IslamicHoliday extends Holiday {

  @JacksonXmlProperty(localName = "type", isAttribute = true)
  protected IslamicHolidayType type;

  /**
   * Gets the value of the type property.
   *
   * @return possible object is
   * {@link IslamicHolidayType }
   */
  public IslamicHolidayType getType() {
    return type;
  }

  /**
   * Sets the value of the type property.
   *
   * @param value allowed object is
   *              {@link IslamicHolidayType }
   */
  public void setType(IslamicHolidayType value) {
    this.type = value;
  }

}
