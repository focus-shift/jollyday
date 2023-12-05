package de.focus_shift.jollyday.jackson.mapping;

public class FixedWeekdayRelativeToFixed extends Holiday {

  protected Fixed day;
  protected Which which;
  protected Weekday weekday;
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
