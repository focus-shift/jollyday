package de.focus_shift.jackson.mapping;

public class RelativeToWeekdayInMonth extends Holiday {

  protected FixedWeekdayInMonth fixedWeekday;
  protected Weekday weekday;
  protected When when;

  /**
   * Gets the value of the fixedWeekday property.
   *
   * @return possible object is
   * {@link FixedWeekdayInMonth }
   */
  public FixedWeekdayInMonth getFixedWeekday() {
    return fixedWeekday;
  }

  /**
   * Sets the value of the fixedWeekday property.
   *
   * @param value allowed object is
   *              {@link FixedWeekdayInMonth }
   */
  public void setFixedWeekday(FixedWeekdayInMonth value) {
    this.fixedWeekday = value;
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
