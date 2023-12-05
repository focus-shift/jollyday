package de.focus_shift.jollyday.jackson.mapping;

public class MovingCondition {

  protected Weekday substitute;
  protected With with;
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
