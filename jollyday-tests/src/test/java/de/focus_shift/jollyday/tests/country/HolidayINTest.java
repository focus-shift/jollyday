package de.focus_shift.jollyday.tests.country;

import de.focus_shift.jollyday.core.Holiday;
import de.focus_shift.jollyday.core.HolidayManager;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.time.LocalDate;
import java.time.Year;
import java.util.List;

import static de.focus_shift.jollyday.core.HolidayCalendar.INDIA;
import static de.focus_shift.jollyday.core.ManagerParameters.create;
import static de.focus_shift.jollyday.tests.CalendarCheckerApi.assertFor;
import static java.time.Month.APRIL;
import static java.time.Month.AUGUST;
import static java.time.Month.DECEMBER;
import static java.time.Month.FEBRUARY;
import static java.time.Month.JANUARY;
import static java.time.Month.MAY;
import static java.time.Month.NOVEMBER;
import static java.time.Month.OCTOBER;
import static org.assertj.core.api.Assertions.assertThat;

class HolidayINTest {

  private static final Year YEAR_FROM = Year.of(1900);
  private static final Year YEAR_TO = Year.of(2173);

  private static final List<String> MUHARRAM_SUBDIVISIONS = List.of(
    "an", "ap", "br", "ch", "cg", "dl", "hp", "jk", "ka", "kl", "ld", "mp", "mh", "mz", "od", "rj", "tn", "ts", "up", "uk", "wb"
  );
  private static final List<String> MILAD_UN_NABI_SUBDIVISIONS = List.of(
    "an", "ap", "cg", "dl", "dh", "jk", "jh", "ka", "kl", "ld", "mp", "mh", "mn", "mz", "nl", "od", "py", "rj", "tn", "ts", "up", "uk"
  );

  @Test
  void ensuresHolidays() {
    assertFor(INDIA)
      .hasFixedHoliday("REPUBLIC_DAY", JANUARY, 26).validBetween(YEAR_FROM, YEAR_TO).and()
      .hasFixedHoliday("INDEPENDENCE_DAY", AUGUST, 15).validBetween(YEAR_FROM, YEAR_TO).and()
      .hasFixedHoliday("GHANDIS_BIRTHDAY", OCTOBER, 2).validBetween(YEAR_FROM, YEAR_TO).and()
      .hasIslamicHoliday("ID_AL_FITR_2").validBetween(YEAR_FROM, YEAR_TO).and()
      .hasFixedHoliday("BUDDHA_PURNIMA", MAY, 7).validBetween(Year.of(2020), Year.of(2020)).and()
      .hasFixedHoliday("BUDDHA_PURNIMA", MAY, 26).validBetween(Year.of(2021), Year.of(2021)).and()
      .hasFixedHoliday("BUDDHA_PURNIMA", MAY, 16).validBetween(Year.of(2022), Year.of(2022)).and()
      .hasFixedHoliday("BUDDHA_PURNIMA", MAY, 5).validBetween(Year.of(2023), Year.of(2023)).and()
      .hasFixedHoliday("BUDDHA_PURNIMA", MAY, 23).validBetween(Year.of(2024), Year.of(2024)).and()
      .hasFixedHoliday("BUDDHA_PURNIMA", MAY, 12).validBetween(Year.of(2025), Year.of(2025)).and()
      .hasFixedHoliday("BUDDHA_PURNIMA", MAY, 1).validBetween(Year.of(2026), Year.of(2026)).and()
      .hasFixedHoliday("BUDDHA_PURNIMA", MAY, 20).validBetween(Year.of(2027), Year.of(2027)).and()

      // Andaman and Nicobar Islands
      .hasFixedHoliday("CHRISTMAS", DECEMBER, 25).inSubdivision("an").validBetween(YEAR_FROM, YEAR_TO).and()
      .hasChristianHoliday("GOOD_FRIDAY").inSubdivision("an").validBetween(YEAR_FROM, YEAR_TO).and()
      .hasIslamicHoliday("ASCHURA").inSubdivision("an").validBetween(YEAR_FROM, YEAR_TO).and()
      .hasIslamicHoliday("MAWLID_AN_NABI").inSubdivision("an").validBetween(YEAR_FROM, YEAR_TO).and()
      .hasIslamicHoliday("ID_UL_ADHA_2").inSubdivision("an").validBetween(YEAR_FROM, YEAR_TO).and()

      // Andhra Pradesh
      .hasFixedHoliday("AMBEDKAR_JAYANTI", APRIL, 14).inSubdivision("ap").validBetween(YEAR_FROM, YEAR_TO).and()
      .hasFixedHoliday("CHRISTMAS", DECEMBER, 25).inSubdivision("ap").validBetween(YEAR_FROM, YEAR_TO).and()
      .hasChristianHoliday("GOOD_FRIDAY").inSubdivision("ap").validBetween(YEAR_FROM, YEAR_TO).and()
      .hasIslamicHoliday("ASCHURA").inSubdivision("ap").validBetween(YEAR_FROM, YEAR_TO).and()
      .hasIslamicHoliday("MAWLID_AN_NABI").inSubdivision("ap").validBetween(YEAR_FROM, YEAR_TO).and()
      .hasIslamicHoliday("ID_UL_ADHA_2").inSubdivision("ap").validBetween(YEAR_FROM, YEAR_TO).and()

      // Arunāchal Pradesh
      .hasFixedHoliday("STATEHOOD", FEBRUARY, 20).inSubdivision("ar").validBetween(YEAR_FROM, YEAR_TO).and()
      .hasFixedHoliday("NEW_YEAR", JANUARY, 1).inSubdivision("ar").validBetween(YEAR_FROM, YEAR_TO).and()
      .hasFixedHoliday("CHRISTMAS", DECEMBER, 25).inSubdivision("ar").validBetween(YEAR_FROM, YEAR_TO).and()
      .hasChristianHoliday("GOOD_FRIDAY").inSubdivision("ar").validBetween(YEAR_FROM, YEAR_TO).and()

      // Assam
      .hasFixedHoliday("LABOUR_DAY", MAY, 1).inSubdivision("as").validBetween(YEAR_FROM, YEAR_TO).and()
      .hasFixedHoliday("CHRISTMAS", DECEMBER, 25).inSubdivision("as").validBetween(YEAR_FROM, YEAR_TO).and()
      .hasChristianHoliday("GOOD_FRIDAY").inSubdivision("as").validBetween(YEAR_FROM, YEAR_TO).and()
      .hasIslamicHoliday("ID_UL_ADHA_2").inSubdivision("as").validBetween(YEAR_FROM, YEAR_TO).and()

      // Bihār
      .hasFixedHoliday("LABOUR_DAY", MAY, 1).inSubdivision("br").validBetween(YEAR_FROM, YEAR_TO).and()
      .hasFixedHoliday("AMBEDKAR_JAYANTI", APRIL, 14).inSubdivision("br").validBetween(YEAR_FROM, YEAR_TO).and()
      .hasFixedHoliday("CHRISTMAS", DECEMBER, 25).inSubdivision("br").validBetween(YEAR_FROM, YEAR_TO).and()
      .hasChristianHoliday("GOOD_FRIDAY").inSubdivision("br").validBetween(YEAR_FROM, YEAR_TO).and()
      .hasIslamicHoliday("ASCHURA").inSubdivision("br").validBetween(YEAR_FROM, YEAR_TO).and()
      .hasIslamicHoliday("ID_UL_ADHA_2").inSubdivision("br").validBetween(YEAR_FROM, YEAR_TO).and()

      // Chandīgarh
      .hasFixedHoliday("AMBEDKAR_JAYANTI", APRIL, 14).inSubdivision("ch").validBetween(YEAR_FROM, YEAR_TO).and()
      .hasFixedHoliday("CHRISTMAS", DECEMBER, 25).inSubdivision("ch").validBetween(YEAR_FROM, YEAR_TO).and()
      .hasChristianHoliday("GOOD_FRIDAY").inSubdivision("ch").validBetween(YEAR_FROM, YEAR_TO).and()
      .hasIslamicHoliday("ASCHURA").inSubdivision("ch").validBetween(YEAR_FROM, YEAR_TO).and()
      .hasIslamicHoliday("ID_UL_ADHA_2").inSubdivision("ch").validBetween(YEAR_FROM, YEAR_TO).and()

      // Chhattīsgarh
      .hasFixedHoliday("AMBEDKAR_JAYANTI", APRIL, 14).inSubdivision("cg").validBetween(YEAR_FROM, YEAR_TO).and()
      .hasFixedHoliday("CHRISTMAS", DECEMBER, 25).inSubdivision("cg").validBetween(YEAR_FROM, YEAR_TO).and()
      .hasChristianHoliday("GOOD_FRIDAY").inSubdivision("cg").validBetween(YEAR_FROM, YEAR_TO).and()
      .hasIslamicHoliday("ASCHURA").inSubdivision("cg").validBetween(YEAR_FROM, YEAR_TO).and()
      .hasIslamicHoliday("MAWLID_AN_NABI").inSubdivision("cg").validBetween(YEAR_FROM, YEAR_TO).and()
      .hasIslamicHoliday("ID_UL_ADHA_2").inSubdivision("cg").validBetween(YEAR_FROM, YEAR_TO).and()

      // Delhi
      .hasFixedHoliday("AMBEDKAR_JAYANTI", APRIL, 14).inSubdivision("dl").validBetween(YEAR_FROM, YEAR_TO).and()
      .hasFixedHoliday("CHRISTMAS", DECEMBER, 25).inSubdivision("dl").validBetween(YEAR_FROM, YEAR_TO).and()
      .hasChristianHoliday("GOOD_FRIDAY").inSubdivision("dl").validBetween(YEAR_FROM, YEAR_TO).and()
      .hasIslamicHoliday("ASCHURA").inSubdivision("dl").validBetween(YEAR_FROM, YEAR_TO).and()
      .hasIslamicHoliday("MAWLID_AN_NABI").inSubdivision("dl").validBetween(YEAR_FROM, YEAR_TO).and()
      .hasIslamicHoliday("ID_UL_ADHA_2").inSubdivision("dl").validBetween(YEAR_FROM, YEAR_TO).and()

      // Dādra and Nagar Haveli and Damān and Diu
      .hasFixedHoliday("CHRISTMAS", DECEMBER, 25).inSubdivision("dh").validBetween(YEAR_FROM, YEAR_TO).and()
      .hasChristianHoliday("GOOD_FRIDAY").inSubdivision("dh").validBetween(YEAR_FROM, YEAR_TO).and()
      .hasIslamicHoliday("MAWLID_AN_NABI").inSubdivision("dh").validBetween(YEAR_FROM, YEAR_TO).and()

      // Goa
      .hasFixedHoliday("LABOUR_DAY", MAY, 1).inSubdivision("ga").validBetween(YEAR_FROM, YEAR_TO).and()
      .hasFixedHoliday("ST_FRANCIS_XAVIER", DECEMBER, 3).inSubdivision("ga").validBetween(YEAR_FROM, YEAR_TO).and()
      .hasFixedHoliday("CHRISTMAS", DECEMBER, 25).inSubdivision("ga").validBetween(YEAR_FROM, YEAR_TO).and()
      .hasChristianHoliday("GOOD_FRIDAY").inSubdivision("ga").validBetween(YEAR_FROM, YEAR_TO).and()
      .hasIslamicHoliday("ID_UL_ADHA_2").inSubdivision("ga").validBetween(YEAR_FROM, YEAR_TO).and()

      // Gujarāt
      .hasFixedHoliday("AMBEDKAR_JAYANTI", APRIL, 14).inSubdivision("gj").validBetween(YEAR_FROM, YEAR_TO).and()
      .hasFixedHoliday("CHRISTMAS", DECEMBER, 25).inSubdivision("gj").validBetween(YEAR_FROM, YEAR_TO).and()
      .hasChristianHoliday("GOOD_FRIDAY").inSubdivision("gj").validBetween(YEAR_FROM, YEAR_TO).and()
      .hasIslamicHoliday("ID_UL_ADHA_2").inSubdivision("gj").validBetween(YEAR_FROM, YEAR_TO).and()

      // Haryāna
      .hasFixedHoliday("AMBEDKAR_JAYANTI", APRIL, 14).inSubdivision("hr").validBetween(YEAR_FROM, YEAR_TO).and()
      .hasFixedHoliday("CHRISTMAS", DECEMBER, 25).inSubdivision("hr").validBetween(YEAR_FROM, YEAR_TO).and()

      // Himāchal Pradesh
      .hasFixedHoliday("AMBEDKAR_JAYANTI", APRIL, 14).inSubdivision("hp").validBetween(YEAR_FROM, YEAR_TO).and()
      .hasFixedHoliday("CHRISTMAS", DECEMBER, 25).inSubdivision("hp").validBetween(YEAR_FROM, YEAR_TO).and()
      .hasChristianHoliday("GOOD_FRIDAY").inSubdivision("hp").validBetween(YEAR_FROM, YEAR_TO).and()
      .hasIslamicHoliday("ASCHURA").inSubdivision("hp").validBetween(YEAR_FROM, YEAR_TO).and()
      .hasIslamicHoliday("ID_UL_ADHA_2").inSubdivision("hp").validBetween(YEAR_FROM, YEAR_TO).and()

      // Jammu and Kashmīr
      .hasFixedHoliday("AMBEDKAR_JAYANTI", APRIL, 14).inSubdivision("jk").validBetween(YEAR_FROM, YEAR_TO).and()
      .hasFixedHoliday("CHRISTMAS", DECEMBER, 25).inSubdivision("jk").validBetween(YEAR_FROM, YEAR_TO).and()
      .hasIslamicHoliday("ASCHURA").inSubdivision("jk").validBetween(YEAR_FROM, YEAR_TO).and()
      .hasIslamicHoliday("MAWLID_AN_NABI").inSubdivision("jk").validBetween(YEAR_FROM, YEAR_TO).and()
      .hasIslamicHoliday("ID_UL_ADHA_2").inSubdivision("jk").validBetween(YEAR_FROM, YEAR_TO).and()

      // Jhārkhand
      .hasFixedHoliday("AMBEDKAR_JAYANTI", APRIL, 14).inSubdivision("jh").validBetween(YEAR_FROM, YEAR_TO).and()
      .hasFixedHoliday("CHRISTMAS", DECEMBER, 25).inSubdivision("jh").validBetween(YEAR_FROM, YEAR_TO).and()
      .hasChristianHoliday("GOOD_FRIDAY").inSubdivision("jh").validBetween(YEAR_FROM, YEAR_TO).and()
      .hasIslamicHoliday("MAWLID_AN_NABI").inSubdivision("jh").validBetween(YEAR_FROM, YEAR_TO).and()
      .hasIslamicHoliday("ID_UL_ADHA_2").inSubdivision("jh").validBetween(YEAR_FROM, YEAR_TO).and()

      // Karnātaka
      .hasFixedHoliday("LABOUR_DAY", MAY, 1).inSubdivision("ka").validBetween(YEAR_FROM, YEAR_TO).and()
      .hasFixedHoliday("AMBEDKAR_JAYANTI", APRIL, 14).inSubdivision("ka").validBetween(YEAR_FROM, YEAR_TO).and()
      .hasFixedHoliday("KARNATAKA_RAJYOTSAVA", NOVEMBER, 1).inSubdivision("ka").validBetween(YEAR_FROM, YEAR_TO).and()
      .hasFixedHoliday("CHRISTMAS", DECEMBER, 25).inSubdivision("ka").validBetween(YEAR_FROM, YEAR_TO).and()
      .hasChristianHoliday("GOOD_FRIDAY").inSubdivision("ka").validBetween(YEAR_FROM, YEAR_TO).and()
      .hasIslamicHoliday("ASCHURA").inSubdivision("ka").validBetween(YEAR_FROM, YEAR_TO).and()
      .hasIslamicHoliday("MAWLID_AN_NABI").inSubdivision("ka").validBetween(YEAR_FROM, YEAR_TO).and()
      .hasIslamicHoliday("ID_UL_ADHA_2").inSubdivision("ka").validBetween(YEAR_FROM, YEAR_TO).and()

      // Kerala
      .hasFixedHoliday("LABOUR_DAY", MAY, 1).inSubdivision("kl").validBetween(YEAR_FROM, YEAR_TO).and()
      .hasFixedHoliday("AMBEDKAR_JAYANTI", APRIL, 14).inSubdivision("kl").validBetween(YEAR_FROM, YEAR_TO).and()
      .hasFixedHoliday("CHRISTMAS", DECEMBER, 25).inSubdivision("kl").validBetween(YEAR_FROM, YEAR_TO).and()
      .hasChristianHoliday("GOOD_FRIDAY").inSubdivision("kl").validBetween(YEAR_FROM, YEAR_TO).and()
      .hasIslamicHoliday("ASCHURA").inSubdivision("kl").validBetween(YEAR_FROM, YEAR_TO).and()
      .hasIslamicHoliday("MAWLID_AN_NABI").inSubdivision("kl").validBetween(YEAR_FROM, YEAR_TO).and()
      .hasIslamicHoliday("ID_UL_ADHA_2").inSubdivision("kl").validBetween(YEAR_FROM, YEAR_TO).and()

      // Lakshadweep
      .hasFixedHoliday("CHRISTMAS", DECEMBER, 25).inSubdivision("ld").validBetween(YEAR_FROM, YEAR_TO).and()
      .hasIslamicHoliday("ASCHURA").inSubdivision("ld").validBetween(YEAR_FROM, YEAR_TO).and()
      .hasIslamicHoliday("MAWLID_AN_NABI").inSubdivision("ld").validBetween(YEAR_FROM, YEAR_TO).and()
      .hasIslamicHoliday("ID_UL_ADHA_2").inSubdivision("ld").validBetween(YEAR_FROM, YEAR_TO).and()

      // Madhya Pradesh
      .hasFixedHoliday("CHRISTMAS", DECEMBER, 25).inSubdivision("mp").validBetween(YEAR_FROM, YEAR_TO).and()
      .hasIslamicHoliday("ASCHURA").inSubdivision("mp").validBetween(YEAR_FROM, YEAR_TO).and()
      .hasIslamicHoliday("MAWLID_AN_NABI").inSubdivision("mp").validBetween(YEAR_FROM, YEAR_TO).and()
      .hasIslamicHoliday("ID_UL_ADHA_2").inSubdivision("mp").validBetween(YEAR_FROM, YEAR_TO).and()

      // Mahārāshtra
      .hasFixedHoliday("LABOUR_DAY", MAY, 1).inSubdivision("mh").validBetween(YEAR_FROM, YEAR_TO).and()
      .hasFixedHoliday("AMBEDKAR_JAYANTI", APRIL, 14).inSubdivision("mh").validBetween(YEAR_FROM, YEAR_TO).and()
      .hasFixedHoliday("SHIVAJI_JAYANTI", FEBRUARY, 19).inSubdivision("mh").validBetween(YEAR_FROM, YEAR_TO).and()
      .hasFixedHoliday("CHRISTMAS", DECEMBER, 25).inSubdivision("mh").validBetween(YEAR_FROM, YEAR_TO).and()
      .hasChristianHoliday("GOOD_FRIDAY").inSubdivision("mh").validBetween(YEAR_FROM, YEAR_TO).and()
      .hasIslamicHoliday("ASCHURA").inSubdivision("mh").validBetween(YEAR_FROM, YEAR_TO).and()
      .hasIslamicHoliday("MAWLID_AN_NABI").inSubdivision("mh").validBetween(YEAR_FROM, YEAR_TO).and()
      .hasIslamicHoliday("ID_UL_ADHA_2").inSubdivision("mh").validBetween(YEAR_FROM, YEAR_TO).and()

      // Manipur
      .hasFixedHoliday("LABOUR_DAY", MAY, 1).inSubdivision("mn").validBetween(YEAR_FROM, YEAR_TO).and()
      .hasFixedHoliday("NEW_YEAR", JANUARY, 1).inSubdivision("mn").validBetween(YEAR_FROM, YEAR_TO).and()
      .hasFixedHoliday("CHRISTMAS", DECEMBER, 25).inSubdivision("mn").validBetween(YEAR_FROM, YEAR_TO).and()
      .hasChristianHoliday("GOOD_FRIDAY").inSubdivision("mn").validBetween(YEAR_FROM, YEAR_TO).and()
      .hasIslamicHoliday("MAWLID_AN_NABI").inSubdivision("mn").validBetween(YEAR_FROM, YEAR_TO).and()
      .hasIslamicHoliday("ID_UL_ADHA_2").inSubdivision("mn").validBetween(YEAR_FROM, YEAR_TO).and()

      // Meghālaya
      .hasFixedHoliday("NEW_YEAR", JANUARY, 1).inSubdivision("ml").validBetween(YEAR_FROM, YEAR_TO).and()
      .hasFixedHoliday("CHRISTMAS", DECEMBER, 25).inSubdivision("ml").validBetween(YEAR_FROM, YEAR_TO).and()
      .hasChristianHoliday("GOOD_FRIDAY").inSubdivision("ml").validBetween(YEAR_FROM, YEAR_TO).and()
      .hasIslamicHoliday("ID_UL_ADHA_2").inSubdivision("ml").validBetween(YEAR_FROM, YEAR_TO).and()

      // Mizoram
      .hasFixedHoliday("STATEHOOD", FEBRUARY, 20).inSubdivision("mz").validBetween(YEAR_FROM, YEAR_TO).and()
      .hasFixedHoliday("NEW_YEAR", JANUARY, 1).inSubdivision("mz").validBetween(YEAR_FROM, YEAR_TO).and()
      .hasFixedHoliday("CHRISTMAS", DECEMBER, 25).inSubdivision("mz").validBetween(YEAR_FROM, YEAR_TO).and()
      .hasChristianHoliday("GOOD_FRIDAY").inSubdivision("mz").validBetween(YEAR_FROM, YEAR_TO).and()
      .hasIslamicHoliday("ASCHURA").inSubdivision("mz").validBetween(YEAR_FROM, YEAR_TO).and()
      .hasIslamicHoliday("MAWLID_AN_NABI").inSubdivision("mz").validBetween(YEAR_FROM, YEAR_TO).and()
      .hasIslamicHoliday("ID_UL_ADHA_2").inSubdivision("mz").validBetween(YEAR_FROM, YEAR_TO).and()

      // Nāgāland
      .hasFixedHoliday("NEW_YEAR", JANUARY, 1).inSubdivision("nl").validBetween(YEAR_FROM, YEAR_TO).and()
      .hasFixedHoliday("CHRISTMAS", DECEMBER, 25).inSubdivision("nl").validBetween(YEAR_FROM, YEAR_TO).and()
      .hasChristianHoliday("GOOD_FRIDAY").inSubdivision("nl").validBetween(YEAR_FROM, YEAR_TO).and()
      .hasIslamicHoliday("MAWLID_AN_NABI").inSubdivision("nl").validBetween(YEAR_FROM, YEAR_TO).and()
      .hasIslamicHoliday("ID_UL_ADHA_2").inSubdivision("nl").validBetween(YEAR_FROM, YEAR_TO).and()

      // Odisha
      .hasFixedHoliday("AMBEDKAR_JAYANTI", APRIL, 14).inSubdivision("od").validBetween(YEAR_FROM, YEAR_TO).and()
      .hasFixedHoliday("CHRISTMAS", DECEMBER, 25).inSubdivision("od").validBetween(YEAR_FROM, YEAR_TO).and()
      .hasChristianHoliday("GOOD_FRIDAY").inSubdivision("od").validBetween(YEAR_FROM, YEAR_TO).and()
      .hasIslamicHoliday("ASCHURA").inSubdivision("od").validBetween(YEAR_FROM, YEAR_TO).and()
      .hasIslamicHoliday("MAWLID_AN_NABI").inSubdivision("od").validBetween(YEAR_FROM, YEAR_TO).and()
      .hasIslamicHoliday("ID_UL_ADHA_2").inSubdivision("od").validBetween(YEAR_FROM, YEAR_TO).and()

      // Puducherry
      .hasFixedHoliday("LABOUR_DAY", MAY, 1).inSubdivision("py").validBetween(YEAR_FROM, YEAR_TO).and()
      .hasFixedHoliday("AMBEDKAR_JAYANTI", APRIL, 14).inSubdivision("py").validBetween(YEAR_FROM, YEAR_TO).and()
      .hasFixedHoliday("NEW_YEAR", JANUARY, 1).inSubdivision("py").validBetween(YEAR_FROM, YEAR_TO).and()
      .hasFixedHoliday("CHRISTMAS", DECEMBER, 25).inSubdivision("py").validBetween(YEAR_FROM, YEAR_TO).and()
      .hasChristianHoliday("GOOD_FRIDAY").inSubdivision("py").validBetween(YEAR_FROM, YEAR_TO).and()
      .hasIslamicHoliday("MAWLID_AN_NABI").inSubdivision("py").validBetween(YEAR_FROM, YEAR_TO).and()
      .hasIslamicHoliday("ID_UL_ADHA_2").inSubdivision("py").validBetween(YEAR_FROM, YEAR_TO).and()

      // Punjab
      .hasFixedHoliday("AMBEDKAR_JAYANTI", APRIL, 14).inSubdivision("pb").validBetween(YEAR_FROM, YEAR_TO).and()
      .hasFixedHoliday("CHRISTMAS", DECEMBER, 25).inSubdivision("pb").validBetween(YEAR_FROM, YEAR_TO).and()
      .hasChristianHoliday("GOOD_FRIDAY").inSubdivision("pb").validBetween(YEAR_FROM, YEAR_TO).and()
      .hasIslamicHoliday("ID_UL_ADHA_2").inSubdivision("pb").validBetween(YEAR_FROM, YEAR_TO).and()

      // Rājasthān
      .hasFixedHoliday("AMBEDKAR_JAYANTI", APRIL, 14).inSubdivision("rj").validBetween(YEAR_FROM, YEAR_TO).and()
      .hasFixedHoliday("CHRISTMAS", DECEMBER, 25).inSubdivision("rj").validBetween(YEAR_FROM, YEAR_TO).and()
      .hasChristianHoliday("GOOD_FRIDAY").inSubdivision("rj").validBetween(YEAR_FROM, YEAR_TO).and()
      .hasIslamicHoliday("ASCHURA").inSubdivision("rj").validBetween(YEAR_FROM, YEAR_TO).and()
      .hasIslamicHoliday("MAWLID_AN_NABI").inSubdivision("rj").validBetween(YEAR_FROM, YEAR_TO).and()
      .hasIslamicHoliday("ID_UL_ADHA_2").inSubdivision("rj").validBetween(YEAR_FROM, YEAR_TO).and()

      // Sikkim
      .hasFixedHoliday("STATEHOOD", MAY, 16).inSubdivision("sk").validBetween(YEAR_FROM, YEAR_TO).and()
      .hasFixedHoliday("NEW_YEAR", JANUARY, 1).inSubdivision("sk").validBetween(YEAR_FROM, YEAR_TO).and()
      .hasFixedHoliday("CHRISTMAS", DECEMBER, 25).inSubdivision("sk").validBetween(YEAR_FROM, YEAR_TO).and()
      .hasChristianHoliday("GOOD_FRIDAY").inSubdivision("sk").validBetween(YEAR_FROM, YEAR_TO).and()

      // Tamil Nādu
      .hasFixedHoliday("LABOUR_DAY", MAY, 1).inSubdivision("tn").validBetween(YEAR_FROM, YEAR_TO).and()
      .hasFixedHoliday("AMBEDKAR_JAYANTI", APRIL, 14).inSubdivision("tn").validBetween(YEAR_FROM, YEAR_TO).and()
      .hasFixedHoliday("NEW_YEAR", JANUARY, 1).inSubdivision("tn").validBetween(YEAR_FROM, YEAR_TO).and()
      .hasFixedHoliday("CHRISTMAS", DECEMBER, 25).inSubdivision("tn").validBetween(YEAR_FROM, YEAR_TO).and()
      .hasChristianHoliday("GOOD_FRIDAY").inSubdivision("tn").validBetween(YEAR_FROM, YEAR_TO).and()
      .hasIslamicHoliday("ASCHURA").inSubdivision("tn").validBetween(YEAR_FROM, YEAR_TO).and()
      .hasIslamicHoliday("MAWLID_AN_NABI").inSubdivision("tn").validBetween(YEAR_FROM, YEAR_TO).and()
      .hasIslamicHoliday("ID_UL_ADHA_2").inSubdivision("tn").validBetween(YEAR_FROM, YEAR_TO).and()

      // Telangāna
      .hasFixedHoliday("AMBEDKAR_JAYANTI", APRIL, 14).inSubdivision("ts").validBetween(YEAR_FROM, YEAR_TO).and()
      .hasFixedHoliday("CHRISTMAS", DECEMBER, 25).inSubdivision("ts").validBetween(YEAR_FROM, YEAR_TO).and()
      .hasChristianHoliday("GOOD_FRIDAY").inSubdivision("ts").validBetween(YEAR_FROM, YEAR_TO).and()
      .hasIslamicHoliday("ASCHURA").inSubdivision("ts").validBetween(YEAR_FROM, YEAR_TO).and()
      .hasIslamicHoliday("MAWLID_AN_NABI").inSubdivision("ts").validBetween(YEAR_FROM, YEAR_TO).and()
      .hasIslamicHoliday("ID_UL_ADHA_2").inSubdivision("ts").validBetween(YEAR_FROM, YEAR_TO).and()

      // Tripura
      .hasFixedHoliday("LABOUR_DAY", MAY, 1).inSubdivision("tr").validBetween(YEAR_FROM, YEAR_TO).and()
      .hasFixedHoliday("SUBHAS_CHANDRA_BOSE_JAYANTI", JANUARY, 23).inSubdivision("tr").validBetween(YEAR_FROM, YEAR_TO).and()
      .hasFixedHoliday("CHRISTMAS", DECEMBER, 25).inSubdivision("tr").validBetween(YEAR_FROM, YEAR_TO).and()
      .hasIslamicHoliday("ID_UL_ADHA_2").inSubdivision("tr").validBetween(YEAR_FROM, YEAR_TO).and()

      // Uttar Pradesh
      .hasFixedHoliday("AMBEDKAR_JAYANTI", APRIL, 14).inSubdivision("up").validBetween(YEAR_FROM, YEAR_TO).and()
      .hasFixedHoliday("CHRISTMAS", DECEMBER, 25).inSubdivision("up").validBetween(YEAR_FROM, YEAR_TO).and()
      .hasChristianHoliday("GOOD_FRIDAY").inSubdivision("up").validBetween(YEAR_FROM, YEAR_TO).and()
      .hasIslamicHoliday("ASCHURA").inSubdivision("up").validBetween(YEAR_FROM, YEAR_TO).and()
      .hasIslamicHoliday("MAWLID_AN_NABI").inSubdivision("up").validBetween(YEAR_FROM, YEAR_TO).and()
      .hasIslamicHoliday("ID_UL_ADHA_2").inSubdivision("up").validBetween(YEAR_FROM, YEAR_TO).and()

      // Uttarākhand
      .hasFixedHoliday("AMBEDKAR_JAYANTI", APRIL, 14).inSubdivision("uk").validBetween(YEAR_FROM, YEAR_TO).and()
      .hasFixedHoliday("CHRISTMAS", DECEMBER, 25).inSubdivision("uk").validBetween(YEAR_FROM, YEAR_TO).and()
      .hasChristianHoliday("GOOD_FRIDAY").inSubdivision("uk").validBetween(YEAR_FROM, YEAR_TO).and()
      .hasIslamicHoliday("ASCHURA").inSubdivision("uk").validBetween(YEAR_FROM, YEAR_TO).and()
      .hasIslamicHoliday("MAWLID_AN_NABI").inSubdivision("uk").validBetween(YEAR_FROM, YEAR_TO).and()
      .hasIslamicHoliday("ID_UL_ADHA_2").inSubdivision("uk").validBetween(YEAR_FROM, YEAR_TO).and()

      // West Bengal
      .hasFixedHoliday("LABOUR_DAY", MAY, 1).inSubdivision("wb").validBetween(YEAR_FROM, YEAR_TO).and()
      .hasFixedHoliday("AMBEDKAR_JAYANTI", APRIL, 14).inSubdivision("wb").validBetween(YEAR_FROM, YEAR_TO).and()
      .hasFixedHoliday("SUBHAS_CHANDRA_BOSE_JAYANTI", JANUARY, 23).inSubdivision("wb").validBetween(YEAR_FROM, YEAR_TO).and()
      .hasFixedHoliday("CHRISTMAS", DECEMBER, 25).inSubdivision("wb").validBetween(YEAR_FROM, YEAR_TO).and()
      .hasChristianHoliday("GOOD_FRIDAY").inSubdivision("wb").validBetween(YEAR_FROM, YEAR_TO).and()
      .hasIslamicHoliday("ASCHURA").inSubdivision("wb").validBetween(YEAR_FROM, YEAR_TO).and()
      .hasIslamicHoliday("ID_UL_ADHA_2").inSubdivision("wb").validBetween(YEAR_FROM, YEAR_TO)
      .check();
  }

  @ParameterizedTest
  @CsvSource({
    "2020-08-30",
    "2021-08-19",
    "2022-08-09",
    "2023-07-29",
    "2024-07-17",
    "2025-07-06",
    "2026-06-26",
    "2027-06-16"
  })
  void ensuresMuharramOnTheDateOfTheDoPTHolidayList(final LocalDate muharram) {
    final HolidayManager holidayManager = HolidayManager.getInstance(create(INDIA));

    for (final String subdivision : MUHARRAM_SUBDIVISIONS) {
      assertThat(holidayManager.getHolidays(Year.of(muharram.getYear()), subdivision))
        .filteredOn(holiday -> holiday.getPropertiesKey().equals("islamic.ASCHURA"))
        .extracting(Holiday::getDate)
        .as("Muharram in subdivision '%s'", subdivision)
        .containsExactly(muharram);
    }
  }

  @ParameterizedTest
  @CsvSource({
    "2020-10-30",
    "2021-10-19",
    "2022-10-09",
    "2023-09-28",
    "2024-09-16",
    "2025-09-05",
    "2026-08-26",
    "2027-08-15"
  })
  void ensuresMiladUnNabiOnTheDateOfTheDoPTHolidayList(final LocalDate miladUnNabi) {
    final HolidayManager holidayManager = HolidayManager.getInstance(create(INDIA));

    for (final String subdivision : MILAD_UN_NABI_SUBDIVISIONS) {
      assertThat(holidayManager.getHolidays(Year.of(miladUnNabi.getYear()), subdivision))
        .filteredOn(holiday -> holiday.getPropertiesKey().equals("islamic.MAWLID_AN_NABI"))
        .extracting(Holiday::getDate)
        .as("Milad-un-Nabi in subdivision '%s'", subdivision)
        .containsExactly(miladUnNabi);
    }
  }

  @ParameterizedTest
  @CsvSource({
    "2025-04-18",
    "2026-04-03"
  })
  void ensuresGoodFridayInGujaratAndMaharashtra(final LocalDate goodFriday) {
    final HolidayManager holidayManager = HolidayManager.getInstance(create(INDIA));

    for (final String subdivision : List.of("gj", "mh")) {
      assertThat(holidayManager.getHolidays(Year.of(goodFriday.getYear()), subdivision))
        .filteredOn(holiday -> holiday.getPropertiesKey().equals("christian.GOOD_FRIDAY"))
        .extracting(Holiday::getDate)
        .as("Good Friday in subdivision '%s'", subdivision)
        .containsExactly(goodFriday);
    }
  }
}
