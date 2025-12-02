package de.focus_shift.jollyday.jackson.mapping;

import tools.jackson.dataformat.xml.annotation.JacksonXmlProperty;

public class FixedWeekdayInMonth extends Holiday {

  @JacksonXmlProperty(localName = "which", isAttribute = true)
  protected Which which;
  @JacksonXmlProperty(localName = "weekday", isAttribute = true)
  protected Weekday weekday;
  @JacksonXmlProperty(localName = "month", isAttribute = true)
  protected Month month;

  /**
   * Gets the value of the which property.
   *
   * @return possible object is
   * {@link Which }
   */
  public Which getWhich() {
    return which;
  }

  /**
   * Sets the value of the which property.
   *
   * @param value allowed object is
   *              {@link Which }
   */
  public void setWhich(Which value) {
    this.which = value;
  }

  /**
   * Gets the value of the weekday property.
   *
   * @return possible object is
   * {@link Weekday }
   */
  public Weekday getWeekday() {
    return weekday;
  }

  /**
   * Sets the value of the weekday property.
   *
   * @param value allowed object is
   *              {@link Weekday }
   */
  public void setWeekday(Weekday value) {
    this.weekday = value;
  }

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

}
