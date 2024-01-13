package de.focus_shift.jollyday.jackson.mapping;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;

public class RelativeToFixed extends Holiday {

  @JacksonXmlElementWrapper(localName = "Days", useWrapping = false)
  protected Integer days;
  @JacksonXmlElementWrapper(localName = "Weekday", useWrapping = false)
  protected Weekday weekday;
  @JacksonXmlElementWrapper(localName = "When", useWrapping = false)
  protected When when;
  @JacksonXmlElementWrapper(localName = "Date", useWrapping = false)
  protected Fixed date;

  /**
   * Gets the value of the days property.
   *
   * @return possible object is
   * {@link Integer }
   */
  public Integer getDays() {
    return days;
  }

  /**
   * Sets the value of the days property.
   *
   * @param value allowed object is
   *              {@link Integer }
   */
  public void setDays(Integer value) {
    this.days = value;
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

  /**
   * Gets the value of the date property.
   *
   * @return possible object is
   * {@link Fixed }
   */
  public Fixed getDate() {
    return date;
  }

  /**
   * Sets the value of the date property.
   *
   * @param value allowed object is
   *              {@link Fixed }
   */
  public void setDate(Fixed value) {
    this.date = value;
  }

}
