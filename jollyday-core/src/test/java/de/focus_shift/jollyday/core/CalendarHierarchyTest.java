package de.focus_shift.jollyday.core;

import de.focus_shift.jollyday.core.util.ResourceUtil;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

class CalendarHierarchyTest {

  private Locale previousDefault;

  @BeforeEach
  void setUp() {
    previousDefault = Locale.getDefault();
    clearCountryDescriptionsCache();
  }

  @AfterEach
  void tearDown() {
    Locale.setDefault(previousDefault);
    clearCountryDescriptionsCache();
  }

  @Test
  void ensureGetId() {
    final CalendarHierarchy sut = new CalendarHierarchy(null, "de");
    assertThat(sut.getId()).isEqualTo("de");
  }

  @Test
  void ensureGetDescriptionWithLocale() {
    final CalendarHierarchy sut = new CalendarHierarchy(null, "de");
    assertThat(sut.getDescription(Locale.ENGLISH)).isEqualTo("Germany");
  }

  @Test
  void ensureGetDescriptionFallsBackToFallbackDescriptionWhenUndefined() {
    final CalendarHierarchy sut = new CalendarHierarchy(null, "unknown-country-code");
    sut.setFallbackDescription("my fallback");
    assertThat(sut.getDescription(Locale.ENGLISH)).isEqualTo("my fallback");
  }

  @Test
  void ensureChildrenGetterAndSetter() {
    final CalendarHierarchy sut = new CalendarHierarchy(null, "de");
    final Map<String, CalendarHierarchy> children = new HashMap<>();
    children.put("by", new CalendarHierarchy(sut, "by"));

    sut.setChildren(children);

    assertThat(sut.getChildren()).isEqualTo(children);
  }

  @Test
  void ensureEqualsAndHashCodeAreBasedOnId() {
    final CalendarHierarchy sut = new CalendarHierarchy(null, "de");
    final CalendarHierarchy same = new CalendarHierarchy(null, "de");
    final CalendarHierarchy other = new CalendarHierarchy(null, "fr");

    assertThat(sut)
      .isEqualTo(sut)
      .isEqualTo(same)
      .isNotEqualTo(other)
      .isNotEqualTo(new Object())
      .isNotEqualTo(null);

    assertThat(sut).hasSameHashCodeAs(same);
  }

  @Test
  void ensureEnglishDescriptionIsNotOverriddenByGermanJvmDefaultLocale() {
    // CalendarHierarchy.getDescription(Locale) delegates to
    // ResourceUtil.getCountryDescription(Locale, String), which is affected by the same
    // default-locale fallback as Holiday.getDescription(Locale). See #1294.
    Locale.setDefault(Locale.GERMANY);

    final CalendarHierarchy hierarchy = new CalendarHierarchy(null, "de");

    assertThat(hierarchy.getDescription(Locale.ENGLISH)).isEqualTo("Germany");
  }

  @Test
  void ensureGermanDescriptionIsStillReturnedWithGermanJvmDefaultLocale() {
    Locale.setDefault(Locale.GERMANY);

    final CalendarHierarchy hierarchy = new CalendarHierarchy(null, "de");

    assertThat(hierarchy.getDescription(Locale.GERMAN)).isEqualTo("Deutschland");
  }

  private static void clearCountryDescriptionsCache() {
    try {
      final Field field = ResourceUtil.class.getDeclaredField("COUNTRY_DESCRIPTIONS_CACHE");
      field.setAccessible(true);
      ((Map<?, ?>) field.get(null)).clear();
    } catch (NoSuchFieldException | IllegalAccessException e) {
      throw new RuntimeException("Failed to clear ResourceUtil cache: COUNTRY_DESCRIPTIONS_CACHE", e);
    }
  }
}
