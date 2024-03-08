package de.focus_shift.jollyday.tests;

import org.openjdk.jmh.results.RunResult;

import java.text.DecimalFormat;

import static org.assertj.core.api.Assertions.assertThat;

public abstract class Benchmarks {

  protected static void assertDeviationWithin(final RunResult result, final double referenceScore, final double maxDeviationInPercent) {
    final double score = result.getPrimaryResult().getScore();
    final double minimumValidScore = referenceScore - (referenceScore * maxDeviationInPercent);

    assertThat(score)
      .withFailMessage("The score (%s) must be greater than the minimum valid score (%s)", score, minimumValidScore)
      .isGreaterThan(minimumValidScore);
  }
}
