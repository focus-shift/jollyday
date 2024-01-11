package de.focus_shift.jollyday.core;

import de.focus_shift.jollyday.core.parameter.CalendarPartManagerParameter;
import de.focus_shift.jollyday.core.parameter.UrlManagerParameter;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.net.URL;
import java.util.Locale;
import java.util.Properties;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ManagerParametersTest {

  @Test
  void ensureToCreateCalendarPartManagerParameterWithCalendarPart() {
    final ManagerParameter managerParameter = ManagerParameters.create("de");
    assertThat(managerParameter).isInstanceOf(CalendarPartManagerParameter.class);
    assertThat(managerParameter.getDisplayName()).isEqualTo("de");
  }

  @Test
  void ensureToCreateCalendarPartManagerParameterWithCalendarPartAndProperties() {

    final Properties properties = new Properties();
    properties.setProperty("property", "property-value");

    final ManagerParameter managerParameter = ManagerParameters.create("de", properties);
    assertThat(managerParameter).isInstanceOf(CalendarPartManagerParameter.class);
    assertThat(managerParameter.getDisplayName()).isEqualTo("de");
    assertThat(managerParameter.getProperty("property")).isEqualTo("property-value");
  }

  @Test
  void ensureToCreateCalendarPartManagerParameterDefaultLocaleWithCalendarPart() {

    Locale.setDefault(Locale.GERMANY);

    final ManagerParameter managerParameter = ManagerParameters.create(" ");
    assertThat(managerParameter).isInstanceOf(CalendarPartManagerParameter.class);
    assertThat(managerParameter.getDisplayName()).isEqualTo("de");
  }

  @Test
  void ensureToCreateCalendarPartManagerParameterWithLocale() {
    final ManagerParameter managerParameter = ManagerParameters.create(Locale.GERMANY);
    assertThat(managerParameter).isInstanceOf(CalendarPartManagerParameter.class);
    assertThat(managerParameter.getDisplayName()).isEqualTo("de");
  }

  @Test
  void ensureToCreateCalendarPartManagerParameterWithLocaleAndProperties() {

    final Properties properties = new Properties();
    properties.setProperty("property", "property-value");

    final ManagerParameter managerParameter = ManagerParameters.create(Locale.GERMANY, properties);
    assertThat(managerParameter).isInstanceOf(CalendarPartManagerParameter.class);
    assertThat(managerParameter.getDisplayName()).isEqualTo("de");
    assertThat(managerParameter.getProperty("property")).isEqualTo("property-value");
  }

  @Test
  void ensureToCreateCalendarPartManagerParameterWithHolidayCalendar() {
    final ManagerParameter managerParameter = ManagerParameters.create(HolidayCalendar.GERMANY);
    assertThat(managerParameter).isInstanceOf(CalendarPartManagerParameter.class);
    assertThat(managerParameter.getDisplayName()).isEqualTo("de");
  }

  @Test
  void ensureToCreateCalendarPartManagerParameterWithHolidayCalendarAndProperties() {

    final Properties properties = new Properties();
    properties.setProperty("property", "property-value");

    final ManagerParameter managerParameter = ManagerParameters.create(HolidayCalendar.GERMANY, properties);
    assertThat(managerParameter).isInstanceOf(CalendarPartManagerParameter.class);
    assertThat(managerParameter.getDisplayName()).isEqualTo("de");
    assertThat(managerParameter.getProperty("property")).isEqualTo("property-value");
  }

  @Test
  void ensureToCreateUrlManagerParameterWithHolidayCalendar() {

    final URL url = mock(URL.class);
    when(url.toString()).thenReturn("Holidays_test.xml");

    final ManagerParameter managerParameter = ManagerParameters.create(url);
    assertThat(managerParameter).isInstanceOf(UrlManagerParameter.class);
    assertThat(managerParameter.getDisplayName()).isEqualTo("Holidays_test.xml");
  }

  @Test
  void ensureToCreateUrlManagerParameterWithHolidayCalendarAndProperties() {

    final URL url = mock(URL.class);
    when(url.toString()).thenReturn("Holidays_test.xml");

    final Properties properties = new Properties();
    properties.setProperty("property", "property-value");

    final ManagerParameter managerParameter = ManagerParameters.create(url, properties);
    assertThat(managerParameter).isInstanceOf(UrlManagerParameter.class);
    assertThat(managerParameter.getDisplayName()).isEqualTo("Holidays_test.xml");
    assertThat(managerParameter.getProperty("property")).isEqualTo("property-value");
  }
}
