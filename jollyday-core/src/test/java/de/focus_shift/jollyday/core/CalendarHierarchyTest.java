package de.focus_shift.jollyday.core;

import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

class CalendarHierarchyTest {

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

    assertThat(sut.hashCode()).isEqualTo(same.hashCode());
  }
}
