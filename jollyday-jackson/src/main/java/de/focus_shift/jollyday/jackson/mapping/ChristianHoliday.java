package de.focus_shift.jollyday.jackson.mapping;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

public class ChristianHoliday extends MoveableHoliday {

  @JacksonXmlProperty(localName = "type", isAttribute = true)
  protected ChristianHolidayType type;

  @JacksonXmlProperty(localName = "chronology", isAttribute = true)
  protected ChronologyType chronology;

  /**
   * Gets the value of the type property.
   *
   * @return possible object is
   * {@link ChristianHolidayType }
   */
  public ChristianHolidayType getType() {
    return type;
  }

  /**
   * Sets the value of the type property.
   *
   * @param value allowed object is
   *              {@link ChristianHolidayType }
   */
  public void setType(ChristianHolidayType value) {
    this.type = value;
  }

  /**
   * Gets the value of the chronology property.
   *
   * @return possible object is
   * {@link ChronologyType }
   */
  public ChronologyType getChronology() {
    return chronology;
  }

  /**
   * Sets the value of the chronology property.
   *
   * @param value allowed object is
   *              {@link ChronologyType }
   */
  public void setChronology(ChronologyType value) {
    this.chronology = value;
  }

}
