package de.focus_shift.jollyday.jackson.mapping;

import tools.jackson.dataformat.xml.annotation.JacksonXmlProperty;

public class Fixed extends MoveableHoliday {

  @JacksonXmlProperty(localName = "month", isAttribute = true)
  protected Month month;
  @JacksonXmlProperty(localName = "day", isAttribute = true)
  protected Integer day;

  /**
   * Gets the value of the month property.
   *
   * @return possible object is
   * {@link Month }
   */
  public Month getMonth() {
    return month;
  }

  /**
   * Sets the value of the month property.
   *
   * @param value allowed object is
   *              {@link Month }
   */
  public void setMonth(Month value) {
    this.month = value;
  }

  /**
   * Gets the value of the day property.
   *
   * @return possible object is
   * {@link Integer }
   */
  public Integer getDay() {
    return day;
  }

  /**
   * Sets the value of the day property.
   *
   * @param value allowed object is
   *              {@link Integer }
   */
  public void setDay(Integer value) {
    this.day = value;
  }

}
