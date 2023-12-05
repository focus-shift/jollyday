package de.focus_shift.jollyday.jackson.mapping;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

public class MovingCondition {

  @JacksonXmlProperty(localName = "substitute", isAttribute = true)
  protected Weekday substitute;
  @JacksonXmlProperty(localName = "with", isAttribute = true)
  protected With with;
  @JacksonXmlProperty(localName = "weekday", isAttribute = true)
  protected Weekday weekday;

  /**
   * Gets the value of the substitute property.
   *
   * @return possible object is
   * {@link Weekday }
   */
  public Weekday getSubstitute() {
    return substitute;
  }

  /**
   * Sets the value of the substitute property.
   *
   * @param value allowed object is
   *              {@link Weekday }
   */
  public void setSubstitute(Weekday value) {
    this.substitute = value;
  }

  /**
   * Gets the value of the with property.
   *
   * @return possible object is
   * {@link With }
   */
  public With getWith() {
    return with;
  }

  /**
   * Sets the value of the with property.
   *
   * @param value allowed object is
   *              {@link With }
   */
  public void setWith(With value) {
    this.with = value;
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
