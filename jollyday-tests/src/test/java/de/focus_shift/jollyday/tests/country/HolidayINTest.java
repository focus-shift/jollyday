package de.focus_shift.jollyday.tests.country;

import org.junit.jupiter.api.Test;

import java.time.Year;

import static de.focus_shift.jollyday.core.HolidayCalendar.INDIA;
import static de.focus_shift.jollyday.tests.CalendarCheckerApi.assertFor;
import static java.time.Month.APRIL;
import static java.time.Month.AUGUST;
import static java.time.Month.DECEMBER;
import static java.time.Month.FEBRUARY;
import static java.time.Month.JANUARY;
import static java.time.Month.MAY;
import static java.time.Month.NOVEMBER;
import static java.time.Month.OCTOBER;

class HolidayINTest {

  private static final Year YEAR_FROM = Year.of(1900);
  private static final Year YEAR_TO = Year.of(2173);

  @Test
  void ensuresHolidays() {
    assertFor(INDIA)
      .hasFixedHoliday("REPUBLIC_DAY", JANUARY, 26).validBetween(YEAR_FROM, YEAR_TO).and()
      .hasFixedHoliday("INDEPENDENCE_DAY", AUGUST, 15).validBetween(YEAR_FROM, YEAR_TO).and()
      .hasFixedHoliday("GHANDIS_BIRTHDAY", OCTOBER, 2).validBetween(YEAR_FROM, YEAR_TO).and()
      .hasIslamicHoliday("ID_AL_FITR_2").validBetween(YEAR_FROM, YEAR_TO).and()

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
}
