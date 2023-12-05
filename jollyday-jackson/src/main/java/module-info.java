import de.focus_shift.jollyday.core.spi.ConfigurationService;
import de.focus_shift.jollyday.jackson.JacksonConfigurationService;

module de.focus_shift.jollday.jackson {

  opens de.focus_shift.jollyday.jackson.mapping to com.fasterxml.jackson.annotation, com.fasterxml.jackson.databind;

  requires java.xml;
  requires org.slf4j;
  requires org.threeten.extra;
  requires de.focus_shift.jollyday.core;
  requires com.fasterxml.jackson.databind;
  requires com.fasterxml.jackson.annotation;
  requires com.fasterxml.jackson.dataformat.xml;

  exports de.focus_shift.jollyday.jackson to de.focus_shift.jollyday.core;
  exports de.focus_shift.jollyday.jackson.mapping to com.fasterxml.jackson.databind;
  provides ConfigurationService
    with JacksonConfigurationService;
}
