package de.focus_shift.jollyday.core;

import de.focus_shift.jollyday.core.datasource.ConfigurationServiceManager;
import de.focus_shift.jollyday.core.impl.DefaultHolidayManager;
import de.focus_shift.jollyday.core.impl.JapaneseBridgingHolidayManager;
import de.focus_shift.jollyday.core.spi.HolidayCalendarConfiguration;
import de.focus_shift.jollyday.core.spi.HolidayCalendarConfigurationService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.stream.Stream;

import static de.focus_shift.jollyday.core.ManagerParameters.create;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class HolidayManagerValueHandlerTest {

  @Test
  void ensureToGetCorrectKey() {
    final String managerImplClassName = "de.focus_shift.jollyday.core.impl.DefaultHolidayManager";
    final String configurationServiceImplClassName = "de.focus_shift.jollyday.jackson.JacksonConfigurationService";
    final ConfigurationServiceManager configurationServiceManager = mock(ConfigurationServiceManager.class);
    final HolidayManagerValueHandler sut = new HolidayManagerValueHandler(create("de"), managerImplClassName, configurationServiceImplClassName, configurationServiceManager);

    final String key = sut.getKey();
    assertThat(key).isEqualTo("de");
  }

  private static Stream<Arguments> provideHolidayManagers() {
    return Stream.of(
      Arguments.of("de.focus_shift.jollyday.core.impl.DefaultHolidayManager", DefaultHolidayManager.class),
      Arguments.of("de.focus_shift.jollyday.core.impl.JapaneseBridgingHolidayManager", JapaneseBridgingHolidayManager.class)
    );
  }

  @ParameterizedTest
  @MethodSource("provideHolidayManagers")
  void ensureToCreateCorrectHolidayManagerAndReturnIt(final String managerImplClass, final Class<HolidayManager> clazz) {

    final ConfigurationServiceManager configurationServiceManager = mock(ConfigurationServiceManager.class);
    final HolidayCalendarConfigurationService configurationService = mock(HolidayCalendarConfigurationService.class);
    final HolidayCalendarConfiguration configuration = mock(HolidayCalendarConfiguration.class);

    when(configurationServiceManager.getConfigurationService(any(String.class))).thenReturn(configurationService);
    when(configurationService.getHolidayCalendarConfiguration(any(ManagerParameter.class))).thenReturn(configuration);

    final HolidayManagerValueHandler sut = new HolidayManagerValueHandler(create("de"), managerImplClass, "configurationServiceImplClassName", configurationServiceManager);

    final HolidayManager holidayManager = sut.createValue();
    assertThat(holidayManager).isInstanceOf(clazz);
  }

  @Test
  void ensureToThrowIllegalStateExceptionIfHolidayManagerIsWrong() {

    final String wrongManagerImplClassName = "de.focus_shift.jollyday.core.impl.WrongHolidayManager";
    final HolidayManagerValueHandler sut = new HolidayManagerValueHandler(create("de"), wrongManagerImplClassName, "configurationServiceImplClassName", mock(ConfigurationServiceManager.class));

    assertThatThrownBy(sut::createValue)
      .isInstanceOf(IllegalStateException.class)
      .hasMessageContaining("Cannot create manager class de.focus_shift.jollyday.core.impl.WrongHolidayManager");
  }
}
