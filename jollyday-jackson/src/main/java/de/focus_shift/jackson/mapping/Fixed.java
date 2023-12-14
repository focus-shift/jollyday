package de.focus_shift.jackson.mapping;

public class Fixed extends MoveableHoliday {

  protected Month month;
  protected Integer day;

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

  /**
   * Gets the value of the day property.
   *
   * @return possible object is
   * {@link Integer }
   */
  public Integer getDay() {
    return day;
  }

  /**
   * Sets the value of the day property.
   *
   * @param value allowed object is
   *              {@link Integer }
   */
  public void setDay(Integer value) {
    this.day = value;
  }

}
