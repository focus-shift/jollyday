import de.focus_shift.jollyday.core.spi.ConfigurationService;
import de.focus_shift.jollyday.pojo.JavaConfigurationService;

module de.focus_shift.jollyday.pojo {

  provides ConfigurationService with
    JavaConfigurationService;

  requires org.slf4j;
  requires org.threeten.extra;
  requires de.focus_shift.jollyday.core;

  exports de.focus_shift.jollyday.pojo to
    de.focus_shift.jollyday.core,
    de.focus_shift.jollyday.pojo.test;


}
