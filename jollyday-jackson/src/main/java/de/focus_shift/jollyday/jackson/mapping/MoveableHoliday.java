package de.focus_shift.jollyday.jackson.mapping;

import tools.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;

import java.util.ArrayList;
import java.util.List;

public abstract class MoveableHoliday extends Holiday {

  @JacksonXmlElementWrapper(localName = "MovingCondition", useWrapping = false)
  protected List<MovingCondition> movingCondition;

  /**
   * Gets the value of the movingCondition property.
   *
   * <p>
   * This accessor method returns a reference to the live list,
   * not a snapshot. Therefore any modification you make to the
   * returned list will be present inside the Jakarta XML Binding object.
   * This is why there is not a <CODE>set</CODE> method for the movingCondition property.
   *
   * <p>
   * For example, to add a new item, do as follows:
   * <pre>
   *    getMovingCondition().add(newItem);
   * </pre>
   *
   *
   * <p>
   * Objects of the following type(s) are allowed in the list
   * {@link MovingCondition }
   */
  public List<MovingCondition> getMovingCondition() {
    if (movingCondition == null) {
      movingCondition = new ArrayList<>();
    }
    return this.movingCondition;
  }

  public void setMovingCondition(List<MovingCondition> movingCondition) {
    this.movingCondition = movingCondition;
  }
}
