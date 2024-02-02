module de.focus_shift.jollyday.core {

  uses de.focus_shift.jollyday.core.spi.ConfigurationService;

  opens de.focus_shift.jollyday.core.util;
  opens descriptions;
  opens holidays;

  requires java.xml;
  requires org.slf4j;
  requires org.threeten.extra;

  exports de.focus_shift.jollyday.core;
  exports de.focus_shift.jollyday.core.spi;
  exports de.focus_shift.jollyday.core.util to
    de.focus_shift.jollyday.jaxb,
    de.focus_shift.jollyday.jackson,
    de.focus_shift.jollyday.jackson.test,
    de.focus_shift.jollyday.jaxb.test,
    de.focus_shift.jollyday.java.test;
  exports de.focus_shift.jollyday.core.parser.impl to
    de.focus_shift.jollyday.jackson.test,
    de.focus_shift.jollyday.jaxb.test,
    de.focus_shift.jollyday.java.test;
}
