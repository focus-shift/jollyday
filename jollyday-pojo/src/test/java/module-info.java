module de.focus_shift.jollyday.pojo.test {

  opens de.focus_shift.jollyday.pojo.test to
    org.junit.platform.commons;

  requires de.focus_shift.jollyday.core;
  requires de.focus_shift.jollyday.pojo;
  requires org.assertj.core;
  requires org.junit.jupiter.api;
  requires org.junit.jupiter.params;
  requires org.threeten.extra;
}
