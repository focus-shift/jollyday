module jollyday.core {

  opens de.focus_shift.jollyday.core.datasource;
  opens de.focus_shift.jollyday.core.util;
  opens holidays;
  opens descriptions;

  requires org.slf4j;
  requires org.threeten.extra;
  requires java.xml;

  exports de.focus_shift.jollyday.core;
  exports de.focus_shift.jollyday.core.spi;
  exports de.focus_shift.jollyday.core.util to jollyday.jaxb;
}
