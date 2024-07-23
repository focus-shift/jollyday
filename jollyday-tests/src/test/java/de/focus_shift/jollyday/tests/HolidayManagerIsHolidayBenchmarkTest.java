package de.focus_shift.jollyday.tests;

import de.focus_shift.jollyday.core.HolidayManager;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.BenchmarkMode;
import org.openjdk.jmh.annotations.Fork;
import org.openjdk.jmh.annotations.Measurement;
import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.State;
import org.openjdk.jmh.annotations.Warmup;
import org.openjdk.jmh.results.RunResult;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

import java.time.LocalDate;
import java.util.Collection;
import java.util.concurrent.TimeUnit;

import static de.focus_shift.jollyday.core.ManagerParameters.create;
import static org.assertj.core.api.Assertions.assertThat;

@BenchmarkMode(Mode.Throughput)
@Fork(3)
@Warmup(iterations = 5, time = 2000, timeUnit = TimeUnit.MILLISECONDS)
@Measurement(iterations = 5, time = 2000, timeUnit = TimeUnit.MILLISECONDS)
public class HolidayManagerIsHolidayBenchmarkTest extends Benchmarks {

  private static final double REFERENCE_SCORE = 8_300_000.000;

  @State(Scope.Thread)
  public static class HolidayManagerState {
    public final HolidayManager holidayManager = HolidayManager.getInstance(create("test"));
  }

  @State(Scope.Thread)
  public static class LocalDateState {
    public final LocalDate localDate = LocalDate.of(2010, 1, 1);
  }

  @Benchmark
  public static boolean benchmarkIsHoliday(final HolidayManagerState holidayManagerState, final LocalDateState localDateState) {
    return holidayManagerState.holidayManager.isHoliday(localDateState.localDate);
  }

  @Test
  @Tag("BenchmarkTest")
  void runJmhBenchmark() throws RunnerException {
    final Options opt = new OptionsBuilder()
      .include(HolidayManagerIsHolidayBenchmarkTest.class.getSimpleName())
      .build();

    final Collection<RunResult> runResults = new Runner(opt).run();
    assertThat(runResults).isNotEmpty();

    for (RunResult runResult : runResults) {
      assertDeviationWithin(runResult, REFERENCE_SCORE, 0.05);
    }
  }
}
