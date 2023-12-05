package de.focus_shift.jollyday.jackson.mapping;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

public class EthiopianOrthodoxHoliday extends Holiday {

  @JacksonXmlProperty(localName = "type", isAttribute = true)
  protected EthiopianOrthodoxHolidayType type;

  /**
   * Gets the value of the type property.
   *
   * @return possible object is
   * {@link EthiopianOrthodoxHolidayType }
   */
  public EthiopianOrthodoxHolidayType getType() {
    return type;
  }

  /**
   * Sets the value of the type property.
   *
   * @param value allowed object is
   *              {@link EthiopianOrthodoxHolidayType }
   */
  public void setType(EthiopianOrthodoxHolidayType value) {
    this.type = value;
  }

}
