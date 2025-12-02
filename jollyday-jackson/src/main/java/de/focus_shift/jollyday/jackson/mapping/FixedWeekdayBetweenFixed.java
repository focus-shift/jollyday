package de.focus_shift.jollyday.jackson.mapping;

import tools.jackson.dataformat.xml.annotation.JacksonXmlProperty;

public class FixedWeekdayBetweenFixed extends Holiday {

  @JacksonXmlProperty(localName = "from")
  protected Fixed from;
  @JacksonXmlProperty(localName = "to")
  protected Fixed to;
  @JacksonXmlProperty(localName = "weekday", isAttribute = true)
  protected Weekday weekday;

  /**
   * Gets the value of the from property.
   *
   * @return possible object is
   * {@link Fixed }
   */
  public Fixed getFrom() {
    return from;
  }

  /**
   * Sets the value of the from property.
   *
   * @param value allowed object is
   *              {@link Fixed }
   */
  public void setFrom(Fixed value) {
    this.from = value;
  }

  /**
   * Gets the value of the to property.
   *
   * @return possible object is
   * {@link Fixed }
   */
  public Fixed getTo() {
    return to;
  }

  /**
   * Sets the value of the to property.
   *
   * @param value allowed object is
   *              {@link Fixed }
   */
  public void setTo(Fixed value) {
    this.to = value;
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

}
