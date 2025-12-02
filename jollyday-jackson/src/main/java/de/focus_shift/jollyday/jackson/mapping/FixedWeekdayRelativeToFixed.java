package de.focus_shift.jollyday.jackson.mapping;

import tools.jackson.dataformat.xml.annotation.JacksonXmlProperty;

public class FixedWeekdayRelativeToFixed extends Holiday {

  @JacksonXmlProperty(localName = "day")
  protected Fixed day;
  @JacksonXmlProperty(localName = "which", isAttribute = true)
  protected Which which;
  @JacksonXmlProperty(localName = "weekday", isAttribute = true)
  protected Weekday weekday;
  @JacksonXmlProperty(localName = "when", isAttribute = true)
  protected When when;

  /**
   * Gets the value of the day property.
   *
   * @return possible object is
   * {@link Fixed }
   */
  public Fixed getDay() {
    return day;
  }

  /**
   * Sets the value of the day property.
   *
   * @param value allowed object is
   *              {@link Fixed }
   */
  public void setDay(Fixed value) {
    this.day = value;
  }

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
   * Gets the value of the when property.
   *
   * @return possible object is
   * {@link When }
   */
  public When getWhen() {
    return when;
  }

  /**
   * Sets the value of the when property.
   *
   * @param value allowed object is
   *              {@link When }
   */
  public void setWhen(When value) {
    this.when = value;
  }

}
