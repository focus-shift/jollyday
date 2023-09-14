import de.focus_shift.jollyday.core.spi.ConfigurationService;
import de.focus_shift.jollyday.jaxb.JaxbConfigurationService;

module de.focus_shift.jollyday.jaxb {

  opens de.focus_shift.jollyday.jaxb.mapping to jakarta.xml.bind;

  requires java.xml;
  requires jakarta.xml.bind;
  requires org.slf4j;
  requires org.threeten.extra;
  requires de.focus_shift.jollyday.core;

  exports de.focus_shift.jollyday.jaxb to de.focus_shift.jollyday.core;
  provides ConfigurationService
    with JaxbConfigurationService;
}
