package de.focus_shift.jollyday.jackson.mapping;

import tools.jackson.dataformat.xml.annotation.JacksonXmlProperty;

public class Weekend {

  @JacksonXmlProperty(localName = "weekday", isAttribute = true)
  protected Weekday weekday;
  @JacksonXmlProperty(localName = "validFrom", isAttribute = true)
  protected Integer validFrom;
  @JacksonXmlProperty(localName = "validTo", isAttribute = true)
  protected Integer validTo;

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
   * Gets the value of the validFrom property.
   *
   * @return possible object is
   * {@link Integer }
   */
  public Integer getValidFrom() {
    return validFrom;
  }

  /**
   * Sets the value of the validFrom property.
   *
   * @param value allowed object is
   *              {@link Integer }
   */
  public void setValidFrom(Integer value) {
    this.validFrom = value;
  }

  /**
   * Gets the value of the validTo property.
   *
   * @return possible object is
   * {@link Integer }
   */
  public Integer getValidTo() {
    return validTo;
  }

  /**
   * Sets the value of the validTo property.
   *
   * @param value allowed object is
   *              {@link Integer }
   */
  public void setValidTo(Integer value) {
    this.validTo = value;
  }

}
