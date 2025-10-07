package de.focus_shift.jollyday.jackson.mapping;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

import java.util.Objects;

public abstract class Holiday {

  @JacksonXmlProperty(localName = "validFrom", isAttribute = true)
  protected Integer validFrom;
  @JacksonXmlProperty(localName = "validTo", isAttribute = true)
  protected Integer validTo;
  @JacksonXmlProperty(localName = "every", isAttribute = true)
  protected HolidayCycleType every;
  @JacksonXmlProperty(localName = "descriptionPropertiesKey", isAttribute = true)
  protected String descriptionPropertiesKey;
  @JacksonXmlProperty(localName = "localizedType", isAttribute = true)
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
    return Objects.requireNonNullElse(localizedType, HolidayType.PUBLIC_HOLIDAY);
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
