package de.focus_shift.jollyday.core.parameter;

import org.junit.jupiter.api.Test;

import java.net.URL;
import java.util.Properties;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;

class BaseManagerParameterTest {

  @Test
  void ensureToAddAllPropertiesOnCreating() {
    final Properties properties = new Properties();
    properties.setProperty("somebody", "just told me");
    properties.setProperty("manager.impl", "managerImplClassName");

    final TestBaseManagerParameter sut = new TestBaseManagerParameter(properties);
    assertThat(sut.getProperty("somebody")).isEqualTo("just told me");
    assertThat(sut.getProperty("manager.impl")).isEqualTo("managerImplClassName");
  }

  @Test
  void ensureToSetProperty() {

    final TestBaseManagerParameter sut = new TestBaseManagerParameter(new Properties());
    sut.setProperty("somebody", "just told me");

    assertThat(sut.getProperty("somebody")).isEqualTo("just told me");
  }

  @Test
  void ensureToMergePropertiesAndCannotOverridesTheDefaults() {
    final Properties properties = new Properties();
    properties.setProperty("somebody", "just told me");
    properties.setProperty("manager.impl", "managerImplClassName");

    final TestBaseManagerParameter sut = new TestBaseManagerParameter(properties);
    assertThat(sut.getProperty("somebody")).isEqualTo("just told me");
    assertThat(sut.getProperty("manager.impl")).isEqualTo("managerImplClassName");

    final Properties mergeProperties = new Properties();
    mergeProperties.setProperty("somebody", "just told me that this is a new one");
    mergeProperties.setProperty("somebody new", "just told me that this is a new one");

    sut.mergeProperties(mergeProperties);
    assertThat(sut.getProperty("somebody")).isEqualTo("just told me");
    assertThat(sut.getProperty("somebody new")).isEqualTo("just told me that this is a new one");
    assertThat(sut.getProperty("manager.impl")).isEqualTo("managerImplClassName");
  }

  @Test
  void ensureTheGetManagerImplClassName() {
    final Properties properties = new Properties();
    properties.setProperty("manager.impl", "managerImplClassName");

    final TestBaseManagerParameter sut = new TestBaseManagerParameter(properties);
    assertThat(sut.getManagerImplClassName()).isEqualTo("managerImplClassName");
  }

  private static class TestBaseManagerParameter extends BaseManagerParameter {

    TestBaseManagerParameter(Properties properties) {
      super(properties);
    }

    @Override
    public String createCacheKey() {
      return "cacheKey";
    }

    @Override
    public String getDisplayName() {
      return "displayName";
    }

    @Override
    public URL createResourceUrl() {
      return mock(URL.class);
    }
  }
}
