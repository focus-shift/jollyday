package de.focus_shift.jollyday.jackson.mapping;

public class RelativeToEasterSunday extends Holiday {

  protected ChronologyType chronology;
  protected int days;

  /**
   * Gets the value of the chronology property.
   *
   * @return possible object is
   * {@link ChronologyType }
   */
  public ChronologyType getChronology() {
    return chronology;
  }

  /**
   * Sets the value of the chronology property.
   *
   * @param value allowed object is
   *              {@link ChronologyType }
   */
  public void setChronology(ChronologyType value) {
    this.chronology = value;
  }

  /**
   * Gets the value of the days property.
   */
  public int getDays() {
    return days;
  }

  /**
   * Sets the value of the days property.
   */
  public void setDays(int value) {
    this.days = value;
  }

}
