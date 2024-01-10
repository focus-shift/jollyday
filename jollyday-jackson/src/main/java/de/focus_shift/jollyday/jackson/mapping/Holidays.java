package de.focus_shift.jollyday.jackson.mapping;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;

import java.util.ArrayList;
import java.util.List;

public class Holidays {

  @JacksonXmlElementWrapper(useWrapping = false)
  protected List<Fixed> fixed;
  @JacksonXmlElementWrapper(useWrapping = false)
  protected List<RelativeToFixed> relativeToFixed;
  @JacksonXmlElementWrapper(useWrapping = false)
  protected List<RelativeToWeekdayInMonth> relativeToWeekdayInMonth;
  @JacksonXmlElementWrapper(useWrapping = false)
  protected List<FixedWeekdayInMonth> fixedWeekday;
  @JacksonXmlElementWrapper(useWrapping = false)
  protected List<ChristianHoliday> christianHoliday;
  @JacksonXmlElementWrapper(useWrapping = false)
  protected List<IslamicHoliday> islamicHoliday;
  @JacksonXmlElementWrapper(useWrapping = false)
  protected List<FixedWeekdayBetweenFixed> fixedWeekdayBetweenFixed;
  @JacksonXmlElementWrapper(useWrapping = false)
  protected List<FixedWeekdayRelativeToFixed> fixedWeekdayRelativeToFixed;
  @JacksonXmlElementWrapper(useWrapping = false)
  protected List<EthiopianOrthodoxHoliday> ethiopianOrthodoxHoliday;
  @JacksonXmlElementWrapper(useWrapping = false)
  protected List<RelativeToEasterSunday> relativeToEasterSunday;

  /**
   * Gets the value of the fixed property.
   *
   * <p>
   * This accessor method returns a reference to the live list,
   * not a snapshot. Therefore any modification you make to the
   * returned list will be present inside the Jakarta XML Binding object.
   * This is why there is not a <CODE>set</CODE> method for the fixed property.
   *
   * <p>
   * For example, to add a new item, do as follows:
   * <pre>
   *    getFixed().add(newItem);
   * </pre>
   *
   *
   * <p>
   * Objects of the following type(s) are allowed in the list
   * {@link Fixed }
   */
  public List<Fixed> getFixed() {
    if (fixed == null) {
      fixed = new ArrayList<>();
    }
    return this.fixed;
  }

  /**
   * Gets the value of the relativeToFixed property.
   *
   * <p>
   * This accessor method returns a reference to the live list,
   * not a snapshot. Therefore any modification you make to the
   * returned list will be present inside the Jakarta XML Binding object.
   * This is why there is not a <CODE>set</CODE> method for the relativeToFixed property.
   *
   * <p>
   * For example, to add a new item, do as follows:
   * <pre>
   *    getRelativeToFixed().add(newItem);
   * </pre>
   *
   *
   * <p>
   * Objects of the following type(s) are allowed in the list
   * {@link RelativeToFixed }
   */
  public List<RelativeToFixed> getRelativeToFixed() {
    if (relativeToFixed == null) {
      relativeToFixed = new ArrayList<>();
    }
    return this.relativeToFixed;
  }

  /**
   * Gets the value of the relativeToWeekdayInMonth property.
   *
   * <p>
   * This accessor method returns a reference to the live list,
   * not a snapshot. Therefore any modification you make to the
   * returned list will be present inside the Jakarta XML Binding object.
   * This is why there is not a <CODE>set</CODE> method for the relativeToWeekdayInMonth property.
   *
   * <p>
   * For example, to add a new item, do as follows:
   * <pre>
   *    getRelativeToWeekdayInMonth().add(newItem);
   * </pre>
   *
   *
   * <p>
   * Objects of the following type(s) are allowed in the list
   * {@link RelativeToWeekdayInMonth }
   */
  public List<RelativeToWeekdayInMonth> getRelativeToWeekdayInMonth() {
    if (relativeToWeekdayInMonth == null) {
      relativeToWeekdayInMonth = new ArrayList<>();
    }
    return this.relativeToWeekdayInMonth;
  }

  /**
   * Gets the value of the fixedWeekday property.
   *
   * <p>
   * This accessor method returns a reference to the live list,
   * not a snapshot. Therefore any modification you make to the
   * returned list will be present inside the Jakarta XML Binding object.
   * This is why there is not a <CODE>set</CODE> method for the fixedWeekday property.
   *
   * <p>
   * For example, to add a new item, do as follows:
   * <pre>
   *    getFixedWeekday().add(newItem);
   * </pre>
   *
   *
   * <p>
   * Objects of the following type(s) are allowed in the list
   * {@link FixedWeekdayInMonth }
   */
  public List<FixedWeekdayInMonth> getFixedWeekday() {
    if (fixedWeekday == null) {
      fixedWeekday = new ArrayList<>();
    }
    return this.fixedWeekday;
  }

  /**
   * Gets the value of the christianHoliday property.
   *
   * <p>
   * This accessor method returns a reference to the live list,
   * not a snapshot. Therefore any modification you make to the
   * returned list will be present inside the Jakarta XML Binding object.
   * This is why there is not a <CODE>set</CODE> method for the christianHoliday property.
   *
   * <p>
   * For example, to add a new item, do as follows:
   * <pre>
   *    getChristianHoliday().add(newItem);
   * </pre>
   *
   *
   * <p>
   * Objects of the following type(s) are allowed in the list
   * {@link ChristianHoliday }
   */
  public List<ChristianHoliday> getChristianHoliday() {
    if (christianHoliday == null) {
      christianHoliday = new ArrayList<>();
    }
    return this.christianHoliday;
  }

  /**
   * Gets the value of the islamicHoliday property.
   *
   * <p>
   * This accessor method returns a reference to the live list,
   * not a snapshot. Therefore any modification you make to the
   * returned list will be present inside the Jakarta XML Binding object.
   * This is why there is not a <CODE>set</CODE> method for the islamicHoliday property.
   *
   * <p>
   * For example, to add a new item, do as follows:
   * <pre>
   *    getIslamicHoliday().add(newItem);
   * </pre>
   *
   *
   * <p>
   * Objects of the following type(s) are allowed in the list
   * {@link IslamicHoliday }
   */
  public List<IslamicHoliday> getIslamicHoliday() {
    if (islamicHoliday == null) {
      islamicHoliday = new ArrayList<>();
    }
    return this.islamicHoliday;
  }

  /**
   * Gets the value of the fixedWeekdayBetweenFixed property.
   *
   * <p>
   * This accessor method returns a reference to the live list,
   * not a snapshot. Therefore any modification you make to the
   * returned list will be present inside the Jakarta XML Binding object.
   * This is why there is not a <CODE>set</CODE> method for the fixedWeekdayBetweenFixed property.
   *
   * <p>
   * For example, to add a new item, do as follows:
   * <pre>
   *    getFixedWeekdayBetweenFixed().add(newItem);
   * </pre>
   *
   *
   * <p>
   * Objects of the following type(s) are allowed in the list
   * {@link FixedWeekdayBetweenFixed }
   */
  public List<FixedWeekdayBetweenFixed> getFixedWeekdayBetweenFixed() {
    if (fixedWeekdayBetweenFixed == null) {
      fixedWeekdayBetweenFixed = new ArrayList<>();
    }
    return this.fixedWeekdayBetweenFixed;
  }

  /**
   * Gets the value of the fixedWeekdayRelativeToFixed property.
   *
   * <p>
   * This accessor method returns a reference to the live list,
   * not a snapshot. Therefore any modification you make to the
   * returned list will be present inside the Jakarta XML Binding object.
   * This is why there is not a <CODE>set</CODE> method for the fixedWeekdayRelativeToFixed property.
   *
   * <p>
   * For example, to add a new item, do as follows:
   * <pre>
   *    getFixedWeekdayRelativeToFixed().add(newItem);
   * </pre>
   *
   *
   * <p>
   * Objects of the following type(s) are allowed in the list
   * {@link FixedWeekdayRelativeToFixed }
   */
  public List<FixedWeekdayRelativeToFixed> getFixedWeekdayRelativeToFixed() {
    if (fixedWeekdayRelativeToFixed == null) {
      fixedWeekdayRelativeToFixed = new ArrayList<>();
    }
    return this.fixedWeekdayRelativeToFixed;
  }

  /**
   * Gets the value of the ethiopianOrthodoxHoliday property.
   *
   * <p>
   * This accessor method returns a reference to the live list,
   * not a snapshot. Therefore any modification you make to the
   * returned list will be present inside the Jakarta XML Binding object.
   * This is why there is not a <CODE>set</CODE> method for the ethiopianOrthodoxHoliday property.
   *
   * <p>
   * For example, to add a new item, do as follows:
   * <pre>
   *    getEthiopianOrthodoxHoliday().add(newItem);
   * </pre>
   *
   *
   * <p>
   * Objects of the following type(s) are allowed in the list
   * {@link EthiopianOrthodoxHoliday }
   */
  public List<EthiopianOrthodoxHoliday> getEthiopianOrthodoxHoliday() {
    if (ethiopianOrthodoxHoliday == null) {
      ethiopianOrthodoxHoliday = new ArrayList<>();
    }
    return this.ethiopianOrthodoxHoliday;
  }

  /**
   * Gets the value of the relativeToEasterSunday property.
   *
   * <p>
   * This accessor method returns a reference to the live list,
   * not a snapshot. Therefore any modification you make to the
   * returned list will be present inside the Jakarta XML Binding object.
   * This is why there is not a <CODE>set</CODE> method for the relativeToEasterSunday property.
   *
   * <p>
   * For example, to add a new item, do as follows:
   * <pre>
   *    getRelativeToEasterSunday().add(newItem);
   * </pre>
   *
   *
   * <p>
   * Objects of the following type(s) are allowed in the list
   * {@link RelativeToEasterSunday }
   */
  public List<RelativeToEasterSunday> getRelativeToEasterSunday() {
    if (relativeToEasterSunday == null) {
      relativeToEasterSunday = new ArrayList<>();
    }
    return this.relativeToEasterSunday;
  }

}
