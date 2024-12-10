package de.focus_shift.jollyday.core.datasource;

import de.focus_shift.jollyday.core.ManagerParameter;
import de.focus_shift.jollyday.core.spi.Configuration;
import de.focus_shift.jollyday.core.spi.ConfigurationService;
import de.focus_shift.jollyday.core.spi.Holidays;
import de.focus_shift.jollyday.core.support.LazyServiceLoaderCache;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ConfigurationServiceManagerTest {

  @Mock
  private LazyServiceLoaderCache<ConfigurationService> configurationServiceCache;

  private ConfigurationServiceManager sut;

  @BeforeEach
  void setUp() {
    sut = new ConfigurationServiceManager(configurationServiceCache);
  }

  @Test
  void ensuresToThrowExceptionIfNoImplementationIsAvailable() {

    when(configurationServiceCache.getServices()).thenReturn(List.of());

    final IllegalStateException exception = assertThrows(IllegalStateException.class, () -> sut.getConfigurationService("configurationServiceImplClassName"));
    assertThat(exception.getMessage()).contains("Cannot instantiate datasource instance because there is no implementations");
  }

  @Test
  void ensuresToProvideConfigurationServiceIfExactlyOneIsAvailable() {
    when(configurationServiceCache.getServices()).thenReturn(List.of(new MockConfigurationService()));

    final ConfigurationService configurationService = sut.getConfigurationService("configurationServiceImplClassName");
    assertThat(configurationService).isInstanceOf(MockConfigurationService.class);
  }

  @Test
  void ensuresToThrowExceptionIfMultipleImplementationsAreAvailableButNoneOfThatAreConfigured() {

    when(configurationServiceCache.getServices()).thenReturn(List.of(new MockConfigurationService(), new MockConfigurationService()));

    final IllegalStateException exception = assertThrows(IllegalStateException.class, () -> sut.getConfigurationService("configurationServiceImplClassName"));
    assertThat(exception.getMessage()).contains("Cannot instantiate datasource instance because there are two or more implementations available");
  }

  @Test
  void ensuresToUseTheConfiguredConfigurationServiceIfMultipleAreOnTheClasspath() {

    final MockConfigurationServiceOther mockConfigurationServiceOther = new MockConfigurationServiceOther();
    when(configurationServiceCache.getServices()).thenReturn(List.of(new MockConfigurationService(), mockConfigurationServiceOther));

    final ConfigurationService configurationService = sut.getConfigurationService(mockConfigurationServiceOther.getClass().getName());
    assertThat(configurationService).isInstanceOf(MockConfigurationServiceOther.class);
  }

  private static class MockConfigurationService implements ConfigurationService {

    @Override
    public Configuration getConfiguration(ManagerParameter parameter) {
      return new Configuration() {
        @Override
        public Holidays holidays() {
          return null;
        }

        @Override
        public Stream<Configuration> subConfigurations() {
          return null;
        }

        @Override
        public String hierarchy() {
          return null;
        }

        @Override
        public String description() {
          return null;
        }
      };
    }
  }

  private static class MockConfigurationServiceOther implements ConfigurationService {

    @Override
    public Configuration getConfiguration(ManagerParameter parameter) {
      return new Configuration() {
        @Override
        public Holidays holidays() {
          return null;
        }

        @Override
        public Stream<Configuration> subConfigurations() {
          return null;
        }

        @Override
        public String hierarchy() {
          return null;
        }

        @Override
        public String description() {
          return null;
        }
      };
    }
  }
}
