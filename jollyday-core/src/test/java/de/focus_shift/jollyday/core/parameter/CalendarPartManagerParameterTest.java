package de.focus_shift.jollyday.core.parameter;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Properties;

import static org.assertj.core.api.Assertions.assertThat;

class CalendarPartManagerParameterTest {

  private CalendarPartManagerParameter sut;

  @BeforeEach
  void setUp() {

    final Properties properties = new Properties();
    properties.setProperty("somebody", "just told me");
    properties.setProperty("manager.impl", "managerImplClassName");

    sut = new CalendarPartManagerParameter("de", properties);
  }

  @Test
  void ensureToGetCalendarPartAsCacheKey() {
    assertThat(sut.createCacheKey()).isEqualTo("de");
  }

  @Test
  void ensureToGetCalendarPartAsDisplayName() {
    assertThat(sut.getDisplayName()).isEqualTo("de");
  }

  @Test
  void ensureToCreateResourceUrl() {
    assertThat(sut.createResourceUrl().getFile())
      .endsWith("jollyday-core/target/classes/holidays/Holidays_de.xml");
  }

  @Test
  void ensureToConfigurationFileName() {
    assertThat(CalendarPartManagerParameter.getConfigurationFileName("de"))
      .isEqualTo("holidays/Holidays_de.xml");
  }

  @Test
  void ensureToGetCorrectToString() {
    assertThat(sut.toString())
      .isEqualTo("CalendarPartManagerParameter - de");
  }
}
