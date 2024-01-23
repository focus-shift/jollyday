package de.focus_shift.jollyday.tests;

import de.focus_shift.jollyday.core.HolidayManager;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDate;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicLong;

import static de.focus_shift.jollyday.core.ManagerParameters.create;
import static java.util.concurrent.TimeUnit.SECONDS;

class HolidayManagerPerformanceTest {

  private static final Logger LOG = LoggerFactory.getLogger(HolidayManagerPerformanceTest.class);

  @Test
  void testIsHolidayPerformanceMultiThread() throws InterruptedException {

    LocalDate date = LocalDate.of(2010, 1, 1);
    final AtomicLong count = new AtomicLong(0);
    final AtomicLong sumDuration = new AtomicLong(0);

    final ExecutorService executorService = Executors.newCachedThreadPool();
    while (date.getYear() < 2013) {
      final LocalDate localDate = date;
      executorService.submit(() -> {
        final long start = System.currentTimeMillis();

        final HolidayManager holidayManager = HolidayManager.getInstance(create("test"));
        holidayManager.isHoliday(localDate);

        final long duration = System.currentTimeMillis() - start;
        count.incrementAndGet();
        sumDuration.addAndGet(duration);
      });
      date = date.plusDays(1);
    }
    executorService.shutdown();
    executorService.awaitTermination(5, SECONDS);

    LOG.info("isHoliday took {} millis average tested with {} calls.", sumDuration.doubleValue() / count.doubleValue(), count.longValue());
  }
}
