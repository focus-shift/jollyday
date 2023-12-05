import de.focus_shift.jollyday.jackson.JacksonConfigurationService;
import de.focus_shift.jollyday.core.spi.ConfigurationService;

module de.focus_shift.jackson {

  opens de.focus_shift.jollyday.jackson.mapping to com.fasterxml.jackson.annotation;

  requires java.xml;
  requires org.slf4j;
  requires org.threeten.extra;
  requires de.focus_shift.jollyday.core;
  requires com.fasterxml.jackson.annotation;
  requires com.fasterxml.jackson.dataformat.xml;

  exports de.focus_shift.jollyday.jackson to de.focus_shift.jollyday.core;
  provides ConfigurationService
    with JacksonConfigurationService;
}
