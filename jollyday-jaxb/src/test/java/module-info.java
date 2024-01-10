module de.focus_shift.jollyday.jaxb.test {

  opens de.focus_shift.jollyday.jaxb.test to org.junit.platform.commons;

  requires de.focus_shift.jollyday.core;
  requires de.focus_shift.jollyday.jaxb;
  requires jakarta.xml.bind;
  requires org.assertj.core;
  requires org.junit.jupiter.api;
  requires org.junit.jupiter.params;
  requires org.mockito.junit.jupiter;
  requires org.mockito;
}
