package de.focus_shift.jollyday.core.parser.predicates;

import de.focus_shift.jollyday.core.spi.Limited;
import de.focus_shift.jollyday.core.spi.YearCycle;
import net.jqwik.api.ForAll;
import net.jqwik.api.Property;
import net.jqwik.time.api.constraints.YearRange;
import org.junit.jupiter.api.Test;

import java.time.Year;

import static org.assertj.core.api.Assertions.assertThat;

class ValidFromToTest {

  @Test
  void ensureFromAndToCanBothBeNullAndWillBeValidForever() {
    final Limited limited = getLimited(null, null);

    final boolean isValid = new ValidFromTo(Year.of(2020)).test(limited);
    assertThat(isValid).isTrue();
  }

  @Property
  void ensureFromCanBeNullAndToIsAYearAndWillBeValidUntilTo(@ForAll @YearRange(max = 2020) final Year year) {
    final Limited limited = getLimited(null, Year.of(2020));

    final boolean isValid = new ValidFromTo(year).test(limited);
    assertThat(isValid).isTrue();
  }

  @Property
  void ensureFromBothBeNullAndToIsAYearAfter2020AndWillNotBeValid(@ForAll @YearRange(min = 2021) final Year year) {
    final Limited limited = getLimited(null, Year.of(2020));

    final boolean isValid = new ValidFromTo(year).test(limited);
    assertThat(isValid).isFalse();
  }

  @Property
  void ensureToCanBeNullAndFromIsSetAndLimitedWillBeValidUntilThisYear(@ForAll @YearRange(min = 1900) final Year year) {
    final Limited limited = getLimited(Year.of(1900), null);

    final boolean isValid = new ValidFromTo(year).test(limited);
    assertThat(isValid).isTrue();
  }

  @Property
  void ensureToCanBeNullAndFromIsSetAndLimitedWillBeNotValidAfterThisYear(@ForAll @YearRange(min = 1800, max = 1899) final Year year) {
    final Limited limited = getLimited(Year.of(1900), null);

    final boolean isValid = new ValidFromTo(year).test(limited);
    assertThat(isValid).isFalse();
  }

  @Property
  void ensureIsOnlyValidInTheIntervalFromAndTo(@ForAll @YearRange(min = 1920, max = 2080) final Year year) {
    final Limited limited = getLimited(Year.of(1920), Year.of(2080));

    final boolean isValid = new ValidFromTo(year).test(limited);
    assertThat(isValid).isTrue();
  }

  @Property
  void ensureIsNotValidBeforeTheIntervalFromAndTo(@ForAll @YearRange(min = 1700, max = 1919) final Year year) {
    final Limited limited = getLimited(Year.of(1920), Year.of(2080));

    final boolean isValid = new ValidFromTo(year).test(limited);
    assertThat(isValid).isFalse();
  }

  @Property
  void ensureIsNotValidAfterTheIntervalFromAndTo(@ForAll @YearRange(min = 2081, max = 2150) final Year year) {
    final Limited limited = getLimited(Year.of(1920), Year.of(2080));

    final boolean isValid = new ValidFromTo(year).test(limited);
    assertThat(isValid).isFalse();
  }

  private static Limited getLimited(Year fromYear, Year toYear) {
    return new Limited() {
      @Override
      public Year validFrom() {
        return fromYear;
      }

      @Override
      public Year validTo() {
        return toYear;
      }

      @Override
      public YearCycle cycle() {
        return YearCycle.EVERY_YEAR;
      }
    };
  }
}
