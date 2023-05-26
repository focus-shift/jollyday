package de.focus_shift.jackson.mapping;

public abstract class Holiday {

  protected Integer validFrom;
  protected Integer validTo;
  protected HolidayCycleType every;
  protected String descriptionPropertiesKey;
  protected HolidayType localizedType;

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

  /**
   * Gets the value of the every property.
   *
   * @return possible object is
   * {@link HolidayCycleType }
   */
  public HolidayCycleType getEvery() {
    if (every == null) {
      return HolidayCycleType.EVERY_YEAR;
    } else {
      return every;
    }
  }

  /**
   * Sets the value of the every property.
   *
   * @param value allowed object is
   *              {@link HolidayCycleType }
   */
  public void setEvery(HolidayCycleType value) {
    this.every = value;
  }

  /**
   * Gets the value of the descriptionPropertiesKey property.
   *
   * @return possible object is
   * {@link String }
   */
  public String getDescriptionPropertiesKey() {
    return descriptionPropertiesKey;
  }

  /**
   * Sets the value of the descriptionPropertiesKey property.
   *
   * @param value allowed object is
   *              {@link String }
   */
  public void setDescriptionPropertiesKey(String value) {
    this.descriptionPropertiesKey = value;
  }

  /**
   * Gets the value of the localizedType property.
   *
   * @return possible object is
   * {@link HolidayType }
   */
  public HolidayType getLocalizedType() {
    if (localizedType == null) {
      return HolidayType.OFFICIAL_HOLIDAY;
    } else {
      return localizedType;
    }
  }

  /**
   * Sets the value of the localizedType property.
   *
   * @param value allowed object is
   *              {@link HolidayType }
   */
  public void setLocalizedType(HolidayType value) {
    this.localizedType = value;
  }

}
