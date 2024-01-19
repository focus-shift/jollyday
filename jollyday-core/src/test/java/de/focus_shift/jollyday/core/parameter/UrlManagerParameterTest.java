package de.focus_shift.jollyday.core.parameter;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Properties;

import static org.assertj.core.api.Assertions.assertThat;

class UrlManagerParameterTest {

  private UrlManagerParameter sut;
  private URL url;

  @BeforeEach
  void setup() throws MalformedURLException {
    url = new URL("https://www.example.org");

    sut = new UrlManagerParameter(url, new Properties());
  }

  @Test
  void ensureToGetUrlAsCacheKey() {
    assertThat(sut.createCacheKey()).isEqualTo("https://www.example.org");
  }

  @Test
  void ensureToGetUrlAsDisplayName() {
    assertThat(sut.getDisplayName()).isEqualTo("https://www.example.org");
  }

  @Test
  void ensureToCreateResourceUrl() {
    assertThat(sut.createResourceUrl()).isEqualTo(url);
  }

  @Test
  void ensureToGetCorrectToString() {
    assertThat(sut.toString())
      .isEqualTo("UrlManagerParameter - https://www.example.org");
  }
}
