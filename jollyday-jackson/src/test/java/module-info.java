module de.focus_shift.jollyday.jackson.test {

  opens de.focus_shift.jollyday.jackson.test to org.junit.platform.commons;

  requires com.fasterxml.jackson.databind;
  requires com.fasterxml.jackson.dataformat.xml;
  requires de.focus_shift.jollyday.core;
  requires de.focus_shift.jollyday.jackson;
  requires org.assertj.core;
  requires org.junit.jupiter.api;
  requires org.junit.jupiter.params;
}
