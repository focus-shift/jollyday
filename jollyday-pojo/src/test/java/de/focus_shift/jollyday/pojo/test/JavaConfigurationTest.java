package de.focus_shift.jollyday.pojo.test;

import java.time.LocalDate;
import java.time.MonthDay;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import de.focus_shift.jollyday.core.HolidayManager;
import de.focus_shift.jollyday.core.HolidayType;
import de.focus_shift.jollyday.core.ManagerParameter;
import de.focus_shift.jollyday.core.ManagerParameters;
import de.focus_shift.jollyday.core.spi.Configuration;
import de.focus_shift.jollyday.core.spi.YearCycle;
import de.focus_shift.jollyday.pojo.JavaConfigurationService;
import de.focus_shift.jollyday.pojo.JavaFixed;

class JavaConfigurationTest {

  @Test
  void enhanceJavaConfigurationService() {

    JavaConfigurationService javaConfigurationService = new JavaConfigurationService();
    ManagerParameter parameter = ManagerParameters.create("de");

    HolidayManager holidayManager = HolidayManager.getInstance(parameter);
    Assertions.assertFalse(holidayManager.isHoliday(LocalDate.of(2022, 03, 22)), "Precondition 22.3 should be no holiday");

    // add new holiday for 22.3 dynamically via code/api
    Configuration configuration = javaConfigurationService.getConfiguration(parameter);
    JavaFixed stgandulfholiday = new JavaFixed("st.gandulf's day", HolidayType.UNOFFICIAL_HOLIDAY, null, null, YearCycle.EVERY_YEAR, null, MonthDay.of(3, 22));
    configuration.holidays().fixed().add(stgandulfholiday);
    HolidayManager.clearManagerCache(); // we have to clear the manager cache because otherwise the holidaymanager from above with old configuration would be reused.

    holidayManager = HolidayManager.getInstance(parameter);
    Assertions.assertTrue(holidayManager.isHoliday(LocalDate.of(2022, 03, 22), HolidayType.UNOFFICIAL_HOLIDAY), "Ensure newly added holiday is recognized");

  }
}

