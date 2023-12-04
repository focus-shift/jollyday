module de.focus_shift.jollyday.core {

  uses de.focus_shift.jollyday.core.spi.ConfigurationService;

  opens de.focus_shift.jollyday.core.util;
  opens holidays;
  opens descriptions;
  opens focus_shift.de.jollyday.schema.holiday;

  requires org.slf4j;
  requires org.threeten.extra;
  requires java.xml;

  exports de.focus_shift.jollyday.core;
  exports de.focus_shift.jollyday.core.spi;
  exports de.focus_shift.jollyday.core.util to de.focus_shift.jollyday.jaxb, de.focus_shift.jackson;

}
