open module jollyday.core {
  requires org.slf4j;
  requires org.threeten.extra;
  requires java.xml;

  exports de.focus_shift;
  exports de.focus_shift.spi;
  exports de.focus_shift.util to jollyday.jaxb;
}
