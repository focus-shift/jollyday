package de.focus_shift.jollyday.core.parameter;

import org.jspecify.annotations.NonNull;
import org.junit.jupiter.api.Test;

import java.net.URL;
import java.util.Properties;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalStateException;
import static org.mockito.Mockito.mock;

class BaseManagerParameterTest {

  @Test
  void ensureToAddAllPropertiesOnCreating() {
    final Properties properties = new Properties();
    properties.setProperty("somebody", "just told me");
    properties.setProperty("manager.impl", "managerImplClassName");

    final TestBaseManagerParameter sut = new TestBaseManagerParameter(properties);
    assertThat(sut.getProperty("somebody")).hasValue("just told me");
    assertThat(sut.getProperty("manager.impl")).hasValue("managerImplClassName");
  }

  @Test
  void ensureToSetProperty() {

    final TestBaseManagerParameter sut = new TestBaseManagerParameter(new Properties());
    sut.setProperty("somebody", "just told me");

    assertThat(sut.getProperty("somebody")).hasValue("just told me");
  }

  @Test
  void ensureToMergePropertiesAndCannotOverridesTheDefaults() {
    final Properties properties = new Properties();
    properties.setProperty("somebody", "just told me");
    properties.setProperty("manager.impl", "managerImplClassName");

    final TestBaseManagerParameter sut = new TestBaseManagerParameter(properties);
    assertThat(sut.getProperty("somebody")).hasValue("just told me");
    assertThat(sut.getProperty("manager.impl")).hasValue("managerImplClassName");

    final Properties mergeProperties = new Properties();
    mergeProperties.setProperty("somebody", "just told me that this is a new one");
    mergeProperties.setProperty("somebody new", "just told me that this is a new one");

    sut.mergeProperties(mergeProperties);
    assertThat(sut.getProperty("somebody")).hasValue("just told me");
    assertThat(sut.getProperty("somebody new")).hasValue("just told me that this is a new one");
    assertThat(sut.getProperty("manager.impl")).hasValue("managerImplClassName");
  }

  @Test
  void ensureTheGetManagerImplClassName() {
    final Properties properties = new Properties();
    properties.setProperty("manager.impl", "managerImplClassName");

    final TestBaseManagerParameter sut = new TestBaseManagerParameter(properties);
    assertThat(sut.getManagerImplClassName()).isEqualTo("managerImplClassName");
  }

  @Test
  void ensureGetManagerImplClassNameThrowsWhenMissing() {
    final TestBaseManagerParameter sut = new TestBaseManagerParameter(new Properties());

    assertThatIllegalStateException()
      .isThrownBy(sut::getManagerImplClassName)
      .withMessageContaining("manager.impl");
  }

  @Test
  void ensureGetParserImplClassName() {
    final Properties properties = new Properties();
    properties.setProperty("parser.impl.holidayClass", "parserImplClassName");

    final TestBaseManagerParameter sut = new TestBaseManagerParameter(properties);
    assertThat(sut.getParserImplClassName("holidayClass")).isEqualTo("parserImplClassName");
  }

  @Test
  void ensureGetParserImplClassNameThrowsWhenMissing() {
    final TestBaseManagerParameter sut = new TestBaseManagerParameter(new Properties());

    assertThatIllegalStateException()
      .isThrownBy(() -> sut.getParserImplClassName("holidayClass"))
      .withMessageContaining("holidayClass");
  }

  @Test
  void ensureGetConfigurationServiceImplClassName() {
    final Properties properties = new Properties();
    properties.setProperty("configuration.service.impl", "configurationServiceImplClassName");

    final TestBaseManagerParameter sut = new TestBaseManagerParameter(properties);
    assertThat(sut.getConfigurationServiceImplClassName()).isEqualTo("configurationServiceImplClassName");
  }

  @Test
  void ensureGetConfigurationServiceImplClassNameThrowsWhenMissing() {
    final TestBaseManagerParameter sut = new TestBaseManagerParameter(new Properties());

    assertThatIllegalStateException()
      .isThrownBy(sut::getConfigurationServiceImplClassName)
      .withMessageContaining("configuration.service.impl");
  }

  private static class TestBaseManagerParameter extends BaseManagerParameter {

    TestBaseManagerParameter(Properties properties) {
      super(properties);
    }

    @Override
    public @NonNull String createCacheKey() {
      return "cacheKey";
    }

    @Override
    public @NonNull String getDisplayName() {
      return "displayName";
    }

    @Override
    public @NonNull URL createResourceUrl() {
      return mock(URL.class);
    }
  }
}
