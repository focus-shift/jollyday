package de.focus_shift.jollyday.jackson.mapping;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

import java.util.ArrayList;
import java.util.List;

public class IslamicHoliday extends Holiday {

  @JacksonXmlProperty(localName = "type", isAttribute = true)
  protected IslamicHolidayType type;
  protected List<MovingCondition> movingCondition;

  /**
   * Gets the value of the type property.
   *
   * @return possible object is
   * {@link IslamicHolidayType }
   */
  public IslamicHolidayType getType() {
    return type;
  }

  /**
   * Sets the value of the type property.
   *
   * @param value allowed object is
   *              {@link IslamicHolidayType }
   */
  public void setType(IslamicHolidayType value) {
    this.type = value;
  }

  public List<MovingCondition> getMovingCondition() {
    if (movingCondition == null) {
      movingCondition = new ArrayList<>();
    }
    return this.movingCondition;
  }

}
