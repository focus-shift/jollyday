import de.focus_shift.jollyday.core.spi.HolidayCalendarConfigurationService;
import de.focus_shift.jollyday.jaxb.JaxbConfigurationService;

module de.focus_shift.jollyday.jaxb {

  provides HolidayCalendarConfigurationService with
    JaxbConfigurationService;

  opens de.focus_shift.jollyday.jaxb.mapping to
    jakarta.xml.bind;

  requires de.focus_shift.jollyday.core;
  requires jakarta.xml.bind;
  requires java.xml;
  requires org.slf4j;
  requires org.threeten.extra;

  exports de.focus_shift.jollyday.jaxb to
    de.focus_shift.jollyday.core;
}
