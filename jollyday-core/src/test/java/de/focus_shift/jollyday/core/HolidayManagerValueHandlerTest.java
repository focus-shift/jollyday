package de.focus_shift.jollyday.core;

import de.focus_shift.jollyday.core.HolidayManager;
import de.focus_shift.jollyday.core.HolidayManagerValueHandler;
import de.focus_shift.jollyday.core.ManagerParameter;
import de.focus_shift.jollyday.core.datasource.ConfigurationServiceManager;
import de.focus_shift.jollyday.core.impl.DefaultHolidayManager;
import de.focus_shift.jollyday.core.impl.JapaneseHolidayManager;
import de.focus_shift.jollyday.core.spi.Configuration;
import de.focus_shift.jollyday.core.spi.ConfigurationService;
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
    final ConfigurationServiceManager configurationServiceManager = mock(ConfigurationServiceManager.class);
    final HolidayManagerValueHandler sut = new HolidayManagerValueHandler(create("de"), managerImplClassName, configurationServiceManager);

    final String key = sut.getKey();
    assertThat(key).isEqualTo("de");
  }

  private static Stream<Arguments> provideHolidayManagers() {
    return Stream.of(
      Arguments.of("de.focus_shift.jollyday.core.impl.DefaultHolidayManager", DefaultHolidayManager.class),
      Arguments.of("de.focus_shift.jollyday.core.impl.JapaneseHolidayManager", JapaneseHolidayManager.class)
    );
  }

  @ParameterizedTest
  @MethodSource("provideHolidayManagers")
  void ensureToCreateCorrectHolidayManagerAndReturnIt(final String managerImplClass, final Class<HolidayManager> clazz) {

    final ConfigurationServiceManager configurationServiceManager = mock(ConfigurationServiceManager.class);
    final ConfigurationService configurationService = mock(ConfigurationService.class);
    final Configuration configuration = mock(Configuration.class);

    when(configurationServiceManager.getConfigurationService()).thenReturn(configurationService);
    when(configurationService.getConfiguration(any(ManagerParameter.class))).thenReturn(configuration);

    final HolidayManagerValueHandler sut = new HolidayManagerValueHandler(create("de"), managerImplClass, configurationServiceManager);

    final HolidayManager holidayManager = sut.createValue();
    assertThat(holidayManager).isInstanceOf(clazz);
  }

  @Test
  void ensureToThrowIllegalStateExceptionIfHolidayManagerIsWrong() {

    final String wrongManagerImplClassName = "de.focus_shift.jollyday.core.impl.WrongHolidayManager";
    final HolidayManagerValueHandler sut = new HolidayManagerValueHandler(create("de"), wrongManagerImplClassName, mock(ConfigurationServiceManager.class));

    assertThatThrownBy(sut::createValue)
      .isInstanceOf(IllegalStateException.class)
      .hasMessageContaining("Cannot create manager class de.focus_shift.jollyday.core.impl.WrongHolidayManager");
  }
}
