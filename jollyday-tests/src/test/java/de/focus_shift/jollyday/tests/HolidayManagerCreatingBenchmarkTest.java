package de.focus_shift.jollyday.tests;

import de.focus_shift.jollyday.core.HolidayManager;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.BenchmarkMode;
import org.openjdk.jmh.annotations.Fork;
import org.openjdk.jmh.annotations.Level;
import org.openjdk.jmh.annotations.Measurement;
import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.Setup;
import org.openjdk.jmh.annotations.State;
import org.openjdk.jmh.annotations.TearDown;
import org.openjdk.jmh.annotations.Warmup;
import org.openjdk.jmh.results.RunResult;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

import java.util.Collection;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import static de.focus_shift.jollyday.core.ManagerParameters.create;
import static org.assertj.core.api.Assertions.assertThat;

@BenchmarkMode(Mode.Throughput)
@Fork(3)
@Warmup(iterations = 5, time = 2000, timeUnit = TimeUnit.MILLISECONDS)
@Measurement(iterations = 5, time = 2000, timeUnit = TimeUnit.MILLISECONDS)
public class HolidayManagerCreatingBenchmarkTest extends Benchmarks {

  static final Map<String, Double> REFERENCE_SCORES = Map.of(
    "benchmarkCreateHolidayManagerWithCachingEnabled", 54_000.00,
    "benchmarkCreateHolidayManagerWithCachingDisabled", 1_200.00
  );

  @Benchmark
  public static HolidayManager benchmarkCreateHolidayManagerWithCachingEnabled() {
    return HolidayManager.getInstance(create("test"));
  }

  @State(Scope.Thread)
  public static class HolidayManagerCacheDisabledState {
    @Setup(Level.Trial)
    public void doSetup() {
      HolidayManager.setManagerCachingEnabled(false);
    }

    @TearDown(Level.Trial)
    public void doTearDown() {
      HolidayManager.setManagerCachingEnabled(true);
    }
  }

  @Benchmark
  public static HolidayManager benchmarkCreateHolidayManagerWithCachingDisabled(final HolidayManagerCacheDisabledState state) {
    return HolidayManager.getInstance(create("test"));
  }

  @Test
  @Tag("BenchmarkTest")
  void runJmhBenchmark() throws RunnerException {
    final Options opt = new OptionsBuilder()
      .include(HolidayManagerCreatingBenchmarkTest.class.getSimpleName())
      .build();

    final Collection<RunResult> runResults = new Runner(opt).run();
    assertThat(runResults).isNotEmpty();

    for (RunResult runResult : runResults) {
      assertDeviationWithin(runResult, REFERENCE_SCORES.get(runResult.getPrimaryResult().getLabel()), 0.05);
    }
  }
}
