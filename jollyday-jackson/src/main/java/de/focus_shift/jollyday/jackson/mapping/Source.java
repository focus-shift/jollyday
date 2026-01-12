package de.focus_shift.jollyday.jackson.mapping;

import tools.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import tools.jackson.dataformat.xml.annotation.JacksonXmlText;


/**
 * <p>Java class for Source complex type.
 *
 * <p>The following schema fragment specifies the expected content contained within this class.
 *
 * <pre>
 * &lt;complexType name="Source"&gt;
 *   &lt;simpleContent&gt;
 *     &lt;extension base="&lt;http://www.w3.org/2001/XMLSchema&gt;string"&gt;
 *       &lt;attribute name="of" type="{http://www.w3.org/2001/XMLSchema}string" /&gt;
 *     &lt;/extension&gt;
 *   &lt;/simpleContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 */
public class Source {

  @JacksonXmlText
  protected String value;
  @JacksonXmlProperty(localName = "of", isAttribute = true)
  protected String of;

  /**
   * Gets the value of the value property.
   *
   * @return possible object is
   * {@link String }
   */
  public String getValue() {
    return value;
  }

  /**
   * Sets the value of the value property.
   *
   * @param value allowed object is
   *              {@link String }
   */
  public void setValue(String value) {
    this.value = value;
  }

  /**
   * Gets the value of the of property.
   *
   * @return possible object is
   * {@link String }
   */
  public String getOf() {
    return of;
  }

  /**
   * Sets the value of the of property.
   *
   * @param value allowed object is
   *              {@link String }
   */
  public void setOf(String value) {
    this.of = value;
  }

}
