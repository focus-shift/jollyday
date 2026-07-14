package de.focus_shift.jollyday.tests.country;

import org.junit.jupiter.api.Test;

import java.time.Year;

import static de.focus_shift.jollyday.core.HolidayCalendar.PORTUGAL;
import static de.focus_shift.jollyday.core.HolidayType.OBSERVANCE;
import static de.focus_shift.jollyday.core.spi.Occurrence.FIRST;
import static de.focus_shift.jollyday.core.spi.Occurrence.SECOND;
import static de.focus_shift.jollyday.core.spi.Occurrence.THIRD;
import static de.focus_shift.jollyday.core.spi.Occurrence.FOURTH;
import static de.focus_shift.jollyday.core.spi.Occurrence.LAST;
import static de.focus_shift.jollyday.core.spi.Relation.AFTER;
import static de.focus_shift.jollyday.tests.CalendarCheckerApi.assertFor;
import static java.time.DayOfWeek.MONDAY;
import static java.time.DayOfWeek.TUESDAY;
import static java.time.DayOfWeek.FRIDAY;
import static java.time.DayOfWeek.SATURDAY;
import static java.time.DayOfWeek.SUNDAY;
import static java.time.Month.APRIL;
import static java.time.Month.AUGUST;
import static java.time.Month.DECEMBER;
import static java.time.Month.FEBRUARY;
import static java.time.Month.JANUARY;
import static java.time.Month.JULY;
import static java.time.Month.JUNE;
import static java.time.Month.MARCH;
import static java.time.Month.MAY;
import static java.time.Month.NOVEMBER;
import static java.time.Month.OCTOBER;
import static java.time.Month.SEPTEMBER;

class HolidayPTTest {

  @Test
  void ensuresHolidays() {
    assertFor(PORTUGAL)
      .hasFixedHoliday("NEW_YEAR", JANUARY, 1).and()
      .hasFixedHoliday("FREEDOM_DEMOCRACY", APRIL, 25).and()
      .hasFixedHoliday("LABOUR_DAY", MAY, 1).and()
      .hasFixedHoliday("NATIONAL_DAY", JUNE, 10).and()
      .hasFixedHoliday("ASSUMPTION_DAY", AUGUST, 15).and()
      .hasFixedHoliday("REPUBLIC_DAY", OCTOBER, 5)
        .validTo(Year.of(2012))
        .notValidBetween(Year.of(2013), Year.of(2015))
        .validFrom(Year.of(2016))
        .and()
      .hasFixedHoliday("ALL_SAINTS", NOVEMBER, 1)
        .validTo(Year.of(2012))
        .notValidBetween(Year.of(2013), Year.of(2015))
        .validFrom(Year.of(2016))
      .and()
      .hasFixedHoliday("INDEPENDENCE_DAY", DECEMBER, 1)
        .validTo(Year.of(2012))
        .notValidBetween(Year.of(2013), Year.of(2015))
        .validFrom(Year.of(2016))
      .and()
      .hasFixedHoliday("IMMACULATE_CONCEPTION", DECEMBER, 8).and()
      .hasFixedHoliday("CHRISTMAS", DECEMBER, 25).and()
      .hasChristianHoliday("GOOD_FRIDAY").and()
      .hasChristianHoliday("EASTER").and()
      .hasChristianHoliday("CARNIVAL", OBSERVANCE).and()
      .hasChristianHoliday("CORPUS_CHRISTI")
        .validTo(Year.of(2012))
        .notValidBetween(Year.of(2013), Year.of(2015))
        .validFrom(Year.of(2016))
      .and()
      .hasChristianHoliday("AZORES_DAY", true)
        .inSubdivision("20")
      .and()
      .hasFixedHoliday("AUTONOMY_DAY", APRIL, 2)
        .inSubdivision("30")
        .notValidBetween(Year.of(1900), Year.of(2024))
        .validFrom(Year.of(2025))
      .and()
      .hasFixedHoliday("MADEIRA_DAY", JULY, 1)
        .inSubdivision("30")
      .and()
      .hasFixedHoliday("FIRST_OCTAVE", DECEMBER, 26)
        .inSubdivision("30")
      .check();
  }

  // Municipal holidays (city days) of all 308 municipalities.

  @Test
  void ensuresCityDaysInAveiro() {
    assertFor(PORTUGAL)
      .hasChristianHoliday("PENTECOST_MONDAY").inSubdivision("01", "AGD").and()
      .hasChristianHoliday("ASCENSION_DAY").inSubdivision("01", "AND").and()
      .hasFixedHoliday("CITY_DAY", MAY, 2).inSubdivision("01", "ARC").and()
      .hasFixedHoliday("CITY_DAY", MAY, 12).inSubdivision("01", "AVR").and()
      .hasFixedHoliday("CITY_DAY", JUNE, 24).inSubdivision("01", "CPV").and()
      .hasFixedHoliday("CITY_DAY", JUNE, 16).inSubdivision("01", "ESP").and()
      .hasFixedHoliday("CITY_DAY", JUNE, 13).inSubdivision("01", "ETR").and()
      .hasChristianHoliday("EASTER_MONDAY").inSubdivision("01", "ILH").and()
      .hasChristianHoliday("ASCENSION_DAY").inSubdivision("01", "MLD").and()
      .hasFixedHoliday("CITY_DAY", SEPTEMBER, 8).inSubdivision("01", "MRS").and()
      .hasFixedHoliday("CITY_DAY", JUNE, 13).inSubdivision("01", "VAC").and()
      .hasChristianHoliday("PENTECOST_MONDAY").inSubdivision("01", "VGS").and()
      .hasFixedHoliday("CITY_DAY", SEPTEMBER, 21).inSubdivision("01", "SVV").and()
      .hasFixedHoliday("CITY_DAY", OCTOBER, 11).inSubdivision("01", "SJM").and()
      .hasFixedHoliday("CITY_DAY", JANUARY, 20).inSubdivision("01", "VFR").and()
      .hasFixedHoliday("CITY_DAY", JULY, 25).inSubdivision("01", "OVR").and()
      .hasChristianHoliday("ASCENSION_DAY").inSubdivision("01", "OBR").and()
      // Albergaria-a-Velha: Monday after the third Sunday of August
      .hasRelativeToWeekdayInMonthHoliday("CITY_DAY", MONDAY, AFTER, THIRD, SUNDAY, AUGUST).inSubdivision("01", "ALB").and()
      // Oliveira de Azeméis: second Monday of August
      .hasFixedWeekdayHoliday("CITY_DAY", SECOND, MONDAY, AUGUST).inSubdivision("01", "OAZ")
      .check();
  }

  @Test
  void ensuresCityDaysInAcores() {
    assertFor(PORTUGAL)
      .hasFixedHoliday("CITY_DAY", JUNE, 24).inSubdivision("20", "VFC").and()
      .hasFixedHoliday("CITY_DAY", JUNE, 24).inSubdivision("20", "VPT").and()
      .hasFixedHoliday("CITY_DAY", APRIL, 23).inSubdivision("20", "VLS").and()
      .hasFixedHoliday("CITY_DAY", AUGUST, 16).inSubdivision("20", "SRQ").and()
      .hasFixedHoliday("CITY_DAY", JUNE, 24).inSubdivision("20", "SCF").and()
      .hasFixedHoliday("CITY_DAY", JUNE, 29).inSubdivision("20", "RGR").and()
      .hasFixedHoliday("CITY_DAY", JUNE, 20).inSubdivision("20", "VPV").and()
      .hasFixedHoliday("CITY_DAY", JULY, 18).inSubdivision("20", "NRD").and()
      .hasFixedHoliday("CITY_DAY", JULY, 22).inSubdivision("20", "MAD").and()
      .hasFixedHoliday("CITY_DAY", JUNE, 29).inSubdivision("20", "LGP").and()
      .hasFixedHoliday("CITY_DAY", APRIL, 11).inSubdivision("20", "LAG").and()
      .hasFixedHoliday("CITY_DAY", JUNE, 24).inSubdivision("20", "HRT").and()
      .hasFixedHoliday("CITY_DAY", JUNE, 20).inSubdivision("20", "CRV").and()
      .hasFixedHoliday("CITY_DAY", NOVEMBER, 25).inSubdivision("20", "CHT").and()
      .hasFixedHoliday("CITY_DAY", JUNE, 24).inSubdivision("20", "AGH").and()
      .hasRelativeToEasterSundayHoliday("CITY_DAY").inSubdivision("20", "PVC").and()
      .hasRelativeToEasterSundayHoliday("CITY_DAY").inSubdivision("20", "PDL").and()
      // Santa Cruz da Graciosa: Monday after the second Sunday of August
      .hasRelativeToWeekdayInMonthHoliday("CITY_DAY", MONDAY, AFTER, SECOND, SUNDAY, AUGUST).inSubdivision("20", "SCG").and()
      // Lajes das Flores: third Monday of July
      .hasFixedWeekdayHoliday("CITY_DAY", THIRD, MONDAY, JULY).inSubdivision("20", "LGF")
      .check();
  }

  @Test
  void ensuresCityDaysInBeja() {
    assertFor(PORTUGAL)
      .hasChristianHoliday("ASCENSION_DAY").inSubdivision("02", "VDG").and()
      .hasChristianHoliday("EASTER_TUESDAY").inSubdivision("02", "SRP").and()
      .hasFixedHoliday("CITY_DAY", SEPTEMBER, 8).inSubdivision("02", "ORQ").and()
      .hasFixedHoliday("CITY_DAY", SEPTEMBER, 8).inSubdivision("02", "ODM").and()
      .hasFixedHoliday("CITY_DAY", JUNE, 24).inSubdivision("02", "MRA").and()
      .hasFixedHoliday("CITY_DAY", JUNE, 24).inSubdivision("02", "MTL").and()
      .hasFixedHoliday("CITY_DAY", MARCH, 5).inSubdivision("02", "FAL").and()
      .hasChristianHoliday("EASTER_MONDAY").inSubdivision("02", "CBA").and()
      .hasFixedHoliday("CITY_DAY", JUNE, 29).inSubdivision("02", "CVR").and()
      .hasChristianHoliday("ASCENSION_DAY").inSubdivision("02", "BJA").and()
      .hasFixedHoliday("CITY_DAY", AUGUST, 28).inSubdivision("02", "BRC").and()
      .hasChristianHoliday("ASCENSION_DAY").inSubdivision("02", "AVT").and()
      .hasFixedHoliday("CITY_DAY", JUNE, 24).inSubdivision("02", "ADV").and()
      .hasFixedHoliday("CITY_DAY", JUNE, 13).inSubdivision("02", "AJT")
      .check();
  }

  @Test
  void ensuresCityDaysInBraga() {
    assertFor(PORTUGAL)
      .hasFixedHoliday("CITY_DAY", JUNE, 13).inSubdivision("03", "AMR").and()
      .hasFixedHoliday("CITY_DAY", MAY, 3).inSubdivision("03", "BCL").and()
      .hasFixedHoliday("CITY_DAY", JUNE, 24).inSubdivision("03", "BRG").and()
      .hasFixedHoliday("CITY_DAY", SEPTEMBER, 29).inSubdivision("03", "CBC").and()
      .hasFixedHoliday("CITY_DAY", JULY, 25).inSubdivision("03", "CBT").and()
      .hasFixedHoliday("CITY_DAY", AUGUST, 19).inSubdivision("03", "EPS").and()
      .hasFixedHoliday("CITY_DAY", MAY, 16).inSubdivision("03", "FAF").and()
      .hasFixedHoliday("CITY_DAY", JUNE, 24).inSubdivision("03", "GMR").and()
      .hasFixedHoliday("CITY_DAY", MARCH, 19).inSubdivision("03", "PVL").and()
      .hasFixedHoliday("CITY_DAY", OCTOBER, 20).inSubdivision("03", "TBR").and()
      .hasFixedHoliday("CITY_DAY", JUNE, 13).inSubdivision("03", "VNF").and()
      .hasFixedHoliday("CITY_DAY", JUNE, 13).inSubdivision("03", "VVD").and()
      .hasFixedHoliday("CITY_DAY", MARCH, 19).inSubdivision("03", "VIZ").and()
      // Vieira do Minho: Monday after the first Saturday of October
      .hasRelativeToWeekdayInMonthHoliday("CITY_DAY", MONDAY, AFTER, FIRST, SATURDAY, OCTOBER).inSubdivision("03", "VRM")
      .check();
  }

  @Test
  void ensuresCityDaysInBraganca() {
    assertFor(PORTUGAL)
      .hasFixedHoliday("CITY_DAY", JUNE, 29).inSubdivision("04", "AFE").and()
      .hasFixedHoliday("CITY_DAY", AUGUST, 22).inSubdivision("04", "BGC").and()
      .hasFixedHoliday("CITY_DAY", AUGUST, 31).inSubdivision("04", "CRZ").and()
      .hasChristianHoliday("EASTER_MONDAY").inSubdivision("04", "FEC").and()
      .hasFixedHoliday("CITY_DAY", JUNE, 29).inSubdivision("04", "MCD").and()
      .hasFixedHoliday("CITY_DAY", JULY, 10).inSubdivision("04", "MDR").and()
      .hasFixedHoliday("CITY_DAY", MAY, 25).inSubdivision("04", "MDL").and()
      .hasFixedHoliday("CITY_DAY", OCTOBER, 15).inSubdivision("04", "MGD").and()
      .hasFixedHoliday("CITY_DAY", MARCH, 19).inSubdivision("04", "TMC").and()
      .hasFixedHoliday("CITY_DAY", AUGUST, 24).inSubdivision("04", "VFL").and()
      .hasFixedHoliday("CITY_DAY", AUGUST, 10).inSubdivision("04", "VMS").and()
      .hasFixedHoliday("CITY_DAY", MAY, 20).inSubdivision("04", "VNH")
      .check();
  }

  @Test
  void ensuresCityDaysInCasteloBranco() {
    assertFor(PORTUGAL)
      .hasFixedHoliday("CITY_DAY", APRIL, 26).inSubdivision("05", "BMT").and()
      .hasFixedHoliday("CITY_DAY", OCTOBER, 20).inSubdivision("05", "CVL").and()
      .hasFixedHoliday("CITY_DAY", SEPTEMBER, 15).inSubdivision("05", "FND").and()
      .hasChristianHoliday("EASTER_MONDAY").inSubdivision("05", "PNC").and()
      .hasFixedHoliday("CITY_DAY", JUNE, 13).inSubdivision("05", "PNV").and()
      .hasFixedHoliday("CITY_DAY", JUNE, 24).inSubdivision("05", "SRT").and()
      .hasFixedHoliday("CITY_DAY", SEPTEMBER, 19).inSubdivision("05", "VLR").and()
      .hasRelativeToEasterSundayHoliday("CITY_DAY").inSubdivision("05", "CTB").and()
      .hasRelativeToEasterSundayHoliday("CITY_DAY").inSubdivision("05", "IDN").and()
      // Oleiros: second Monday of August
      .hasFixedWeekdayHoliday("CITY_DAY", SECOND, MONDAY, AUGUST).inSubdivision("05", "OLR").and()
      // Vila Velha de Ródão: Monday after the fourth Sunday of August
      .hasRelativeToWeekdayInMonthHoliday("CITY_DAY", MONDAY, AFTER, FOURTH, SUNDAY, AUGUST).inSubdivision("05", "VVR")
      .check();
  }

  @Test
  void ensuresCityDaysInCoimbra() {
    assertFor(PORTUGAL)
      .hasFixedHoliday("CITY_DAY", SEPTEMBER, 7).inSubdivision("06", "AGN").and()
      .hasFixedHoliday("CITY_DAY", JULY, 25).inSubdivision("06", "CNT").and()
      .hasFixedHoliday("CITY_DAY", JULY, 4).inSubdivision("06", "CBR").and()
      .hasFixedHoliday("CITY_DAY", JULY, 24).inSubdivision("06", "CDN").and()
      .hasFixedHoliday("CITY_DAY", JUNE, 24).inSubdivision("06", "FIG").and()
      .hasFixedHoliday("CITY_DAY", AUGUST, 13).inSubdivision("06", "GOI").and()
      .hasFixedHoliday("CITY_DAY", JUNE, 24).inSubdivision("06", "LSA").and()
      .hasFixedHoliday("CITY_DAY", JULY, 25).inSubdivision("06", "MIR").and()
      .hasFixedHoliday("CITY_DAY", JUNE, 1).inSubdivision("06", "MCV").and()
      .hasFixedHoliday("CITY_DAY", SEPTEMBER, 8).inSubdivision("06", "MMV").and()
      .hasFixedHoliday("CITY_DAY", OCTOBER, 7).inSubdivision("06", "OHP").and()
      .hasFixedHoliday("CITY_DAY", APRIL, 10).inSubdivision("06", "PPS").and()
      .hasFixedHoliday("CITY_DAY", JULY, 17).inSubdivision("06", "PCV").and()
      .hasFixedHoliday("CITY_DAY", SEPTEMBER, 29).inSubdivision("06", "PNL").and()
      .hasFixedHoliday("CITY_DAY", SEPTEMBER, 21).inSubdivision("06", "SRE").and()
      .hasFixedHoliday("CITY_DAY", APRIL, 10).inSubdivision("06", "TBU").and()
      .hasFixedHoliday("CITY_DAY", JANUARY, 13).inSubdivision("06", "PRS")
      .check();
  }

  @Test
  void ensuresCityDaysInEvora() {
    assertFor(PORTUGAL)
      .hasChristianHoliday("ASCENSION_DAY").inSubdivision("07", "ARL").and()
      .hasChristianHoliday("EASTER_MONDAY").inSubdivision("07", "BRB").and()
      .hasChristianHoliday("ASCENSION_DAY").inSubdivision("07", "ETZ").and()
      .hasFixedHoliday("CITY_DAY", JUNE, 29).inSubdivision("07", "EVR").and()
      .hasFixedHoliday("CITY_DAY", MARCH, 8).inSubdivision("07", "MMN").and()
      .hasChristianHoliday("EASTER_MONDAY").inSubdivision("07", "MOR").and()
      .hasFixedHoliday("CITY_DAY", FEBRUARY, 2).inSubdivision("07", "MOU").and()
      .hasChristianHoliday("EASTER_MONDAY").inSubdivision("07", "PRL").and()
      .hasChristianHoliday("EASTER_MONDAY").inSubdivision("07", "RDD").and()
      .hasFixedHoliday("CITY_DAY", JUNE, 13).inSubdivision("07", "RMZ").and()
      .hasFixedHoliday("CITY_DAY", SEPTEMBER, 7).inSubdivision("07", "VND").and()
      .hasFixedHoliday("CITY_DAY", JANUARY, 13).inSubdivision("07", "VNT").and()
      .hasFixedHoliday("CITY_DAY", AUGUST, 16).inSubdivision("07", "VVC").and()
      .hasRelativeToEasterSundayHoliday("CITY_DAY").inSubdivision("07", "ADL")
      .check();
  }

  @Test
  void ensuresCityDaysInFaro() {
    assertFor(PORTUGAL)
      .hasFixedHoliday("CITY_DAY", AUGUST, 20).inSubdivision("08", "ABF").and()
      .hasFixedHoliday("CITY_DAY", AUGUST, 29).inSubdivision("08", "AJZ").and()
      .hasFixedHoliday("CITY_DAY", JUNE, 24).inSubdivision("08", "CTM").and()
      .hasFixedHoliday("CITY_DAY", SEPTEMBER, 7).inSubdivision("08", "FAR").and()
      .hasFixedHoliday("CITY_DAY", SEPTEMBER, 8).inSubdivision("08", "LGA").and()
      .hasFixedHoliday("CITY_DAY", OCTOBER, 27).inSubdivision("08", "LGS").and()
      .hasChristianHoliday("ASCENSION_DAY").inSubdivision("08", "LLE").and()
      .hasChristianHoliday("ASCENSION_DAY").inSubdivision("08", "MCQ").and()
      .hasFixedHoliday("CITY_DAY", JUNE, 16).inSubdivision("08", "OLH").and()
      .hasFixedHoliday("CITY_DAY", DECEMBER, 11).inSubdivision("08", "PTM").and()
      .hasFixedHoliday("CITY_DAY", JUNE, 1).inSubdivision("08", "SBA").and()
      .hasFixedHoliday("CITY_DAY", SEPTEMBER, 3).inSubdivision("08", "SLV").and()
      .hasFixedHoliday("CITY_DAY", JUNE, 24).inSubdivision("08", "TVR").and()
      .hasFixedHoliday("CITY_DAY", JANUARY, 22).inSubdivision("08", "VBP").and()
      .hasFixedHoliday("CITY_DAY", MAY, 13).inSubdivision("08", "VRS").and()
      // Alcoutim: Sunday after the first Friday of September
      .hasRelativeToWeekdayInMonthHoliday("CITY_DAY", SUNDAY, AFTER, FIRST, FRIDAY, SEPTEMBER).inSubdivision("08", "ACT")
      .check();
  }

  @Test
  void ensuresCityDaysInGuarda() {
    assertFor(PORTUGAL)
      .hasFixedHoliday("CITY_DAY", FEBRUARY, 10).inSubdivision("09", "AGB").and()
      .hasFixedHoliday("CITY_DAY", JULY, 2).inSubdivision("09", "ALD").and()
      .hasFixedHoliday("CITY_DAY", MAY, 23).inSubdivision("09", "CLB").and()
      .hasFixedHoliday("CITY_DAY", JULY, 7).inSubdivision("09", "FCR").and()
      .hasFixedHoliday("CITY_DAY", SEPTEMBER, 29).inSubdivision("09", "FAG").and()
      .hasFixedHoliday("CITY_DAY", NOVEMBER, 27).inSubdivision("09", "GRD").and()
      .hasFixedHoliday("CITY_DAY", MARCH, 4).inSubdivision("09", "MTG").and()
      .hasFixedHoliday("CITY_DAY", NOVEMBER, 11).inSubdivision("09", "MED").and()
      .hasFixedHoliday("CITY_DAY", AUGUST, 25).inSubdivision("09", "PNH").and()
      .hasFixedHoliday("CITY_DAY", JULY, 3).inSubdivision("09", "SEI").and()
      .hasFixedHoliday("CITY_DAY", MAY, 29).inSubdivision("09", "TCR").and()
      .hasFixedHoliday("CITY_DAY", MAY, 21).inSubdivision("09", "VLF").and()
      .hasRelativeToEasterSundayHoliday("CITY_DAY").inSubdivision("09", "SBG").and()
      // Gouveia: second Monday of August
      .hasFixedWeekdayHoliday("CITY_DAY", SECOND, MONDAY, AUGUST).inSubdivision("09", "GVA")
      .check();
  }

  @Test
  void ensuresCityDaysInLeiria() {
    assertFor(PORTUGAL)
      .hasFixedHoliday("CITY_DAY", AUGUST, 20).inSubdivision("10", "ACB").and()
      .hasFixedHoliday("CITY_DAY", JUNE, 13).inSubdivision("10", "AVZ").and()
      .hasChristianHoliday("ASCENSION_DAY").inSubdivision("10", "ANS").and()
      .hasFixedHoliday("CITY_DAY", AUGUST, 14).inSubdivision("10", "BTL").and()
      .hasFixedHoliday("CITY_DAY", JUNE, 29).inSubdivision("10", "BBR").and()
      .hasFixedHoliday("CITY_DAY", MAY, 15).inSubdivision("10", "CLD").and()
      .hasFixedHoliday("CITY_DAY", JULY, 4).inSubdivision("10", "CPR").and()
      .hasFixedHoliday("CITY_DAY", JUNE, 24).inSubdivision("10", "FVN").and()
      .hasFixedHoliday("CITY_DAY", MAY, 22).inSubdivision("10", "LRA").and()
      .hasChristianHoliday("ASCENSION_DAY").inSubdivision("10", "MGR").and()
      .hasFixedHoliday("CITY_DAY", SEPTEMBER, 8).inSubdivision("10", "NZR").and()
      .hasFixedHoliday("CITY_DAY", JANUARY, 11).inSubdivision("10", "OBD").and()
      .hasFixedHoliday("CITY_DAY", JULY, 24).inSubdivision("10", "PGR").and()
      .hasFixedHoliday("CITY_DAY", NOVEMBER, 11).inSubdivision("10", "PBL").and()
      .hasFixedHoliday("CITY_DAY", JUNE, 29).inSubdivision("10", "PMS").and()
      // Peniche: Monday after the first Sunday of August
      .hasRelativeToWeekdayInMonthHoliday("CITY_DAY", MONDAY, AFTER, FIRST, SUNDAY, AUGUST).inSubdivision("10", "PNI")
      .check();
  }

  @Test
  void ensuresCityDaysInLisboa() {
    assertFor(PORTUGAL)
      .hasChristianHoliday("ASCENSION_DAY").inSubdivision("11", "ALQ").and()
      .hasFixedHoliday("CITY_DAY", SEPTEMBER, 11).inSubdivision("11", "AMD").and()
      .hasChristianHoliday("ASCENSION_DAY").inSubdivision("11", "ARV").and()
      .hasChristianHoliday("ASCENSION_DAY").inSubdivision("11", "AZB").and()
      .hasFixedHoliday("CITY_DAY", JANUARY, 13).inSubdivision("11", "CDV").and()
      .hasFixedHoliday("CITY_DAY", JUNE, 13).inSubdivision("11", "CSC").and()
      .hasFixedHoliday("CITY_DAY", JUNE, 13).inSubdivision("11", "LSB").and()
      .hasFixedHoliday("CITY_DAY", JULY, 26).inSubdivision("11", "LRS").and()
      .hasFixedHoliday("CITY_DAY", JUNE, 24).inSubdivision("11", "LNH").and()
      .hasChristianHoliday("ASCENSION_DAY").inSubdivision("11", "MFR").and()
      .hasFixedHoliday("CITY_DAY", NOVEMBER, 19).inSubdivision("11", "ODV").and()
      .hasFixedHoliday("CITY_DAY", JUNE, 7).inSubdivision("11", "OER").and()
      .hasFixedHoliday("CITY_DAY", JUNE, 29).inSubdivision("11", "SNT").and()
      .hasChristianHoliday("ASCENSION_DAY").inSubdivision("11", "SMA").and()
      .hasFixedHoliday("CITY_DAY", NOVEMBER, 11).inSubdivision("11", "TVD").and()
      .hasChristianHoliday("ASCENSION_DAY").inSubdivision("11", "VFX")
      .check();
  }

  @Test
  void ensuresCityDaysInMadeira() {
    assertFor(PORTUGAL)
      .hasFixedHoliday("CITY_DAY", JUNE, 24).inSubdivision("30", "CLT").and()
      .hasFixedHoliday("CITY_DAY", OCTOBER, 4).inSubdivision("30", "CML").and()
      .hasFixedHoliday("CITY_DAY", AUGUST, 21).inSubdivision("30", "FNC").and()
      .hasFixedHoliday("CITY_DAY", MAY, 8).inSubdivision("30", "MCH").and()
      .hasFixedHoliday("CITY_DAY", SEPTEMBER, 8).inSubdivision("30", "PTS").and()
      .hasFixedHoliday("CITY_DAY", JULY, 22).inSubdivision("30", "PMZ").and()
      .hasFixedHoliday("CITY_DAY", JUNE, 24).inSubdivision("30", "PST").and()
      .hasFixedHoliday("CITY_DAY", JUNE, 29).inSubdivision("30", "RBR").and()
      .hasFixedHoliday("CITY_DAY", JANUARY, 15).inSubdivision("30", "SCR").and()
      .hasFixedHoliday("CITY_DAY", MAY, 25).inSubdivision("30", "STN").and()
      .hasFixedHoliday("CITY_DAY", JANUARY, 22).inSubdivision("30", "SVC")
      .check();
  }

  @Test
  void ensuresCityDaysInPortalegre() {
    assertFor(PORTUGAL)
      .hasChristianHoliday("ASCENSION_DAY").inSubdivision("12", "ALT").and()
      .hasFixedHoliday("CITY_DAY", JUNE, 24).inSubdivision("12", "ARR").and()
      .hasChristianHoliday("EASTER_MONDAY").inSubdivision("12", "AVS").and()
      .hasChristianHoliday("EASTER_MONDAY").inSubdivision("12", "CMR").and()
      .hasChristianHoliday("EASTER_MONDAY").inSubdivision("12", "CVD").and()
      .hasChristianHoliday("EASTER_MONDAY").inSubdivision("12", "CRT").and()
      .hasFixedHoliday("CITY_DAY", JANUARY, 14).inSubdivision("12", "ELV").and()
      .hasFixedHoliday("CITY_DAY", APRIL, 6).inSubdivision("12", "FTR").and()
      .hasFixedHoliday("CITY_DAY", NOVEMBER, 23).inSubdivision("12", "GAV").and()
      .hasFixedHoliday("CITY_DAY", SEPTEMBER, 8).inSubdivision("12", "MRV").and()
      .hasChristianHoliday("EASTER_MONDAY").inSubdivision("12", "NIS").and()
      .hasChristianHoliday("EASTER_MONDAY").inSubdivision("12", "PSR").and()
      .hasFixedHoliday("CITY_DAY", MAY, 23).inSubdivision("12", "PTG").and()
      .hasChristianHoliday("EASTER_MONDAY").inSubdivision("12", "SSL").and()
      .hasRelativeToEasterSundayHoliday("CITY_DAY").inSubdivision("12", "MFT")
      .check();
  }

  @Test
  void ensuresCityDaysInPorto() {
    assertFor(PORTUGAL)
      .hasFixedHoliday("CITY_DAY", JULY, 8).inSubdivision("13", "AMT").and()
      .hasFixedHoliday("CITY_DAY", AUGUST, 24).inSubdivision("13", "BAO").and()
      .hasFixedHoliday("CITY_DAY", JUNE, 29).inSubdivision("13", "FLG").and()
      .hasFixedHoliday("CITY_DAY", SEPTEMBER, 8).inSubdivision("13", "MCN").and()
      .hasFixedHoliday("CITY_DAY", NOVEMBER, 6).inSubdivision("13", "PFR").and()
      .hasFixedHoliday("CITY_DAY", NOVEMBER, 11).inSubdivision("13", "PNF").and()
      .hasFixedHoliday("CITY_DAY", JUNE, 24).inSubdivision("13", "PRT").and()
      .hasFixedHoliday("CITY_DAY", JUNE, 29).inSubdivision("13", "PVZ").and()
      .hasFixedHoliday("CITY_DAY", JULY, 11).inSubdivision("13", "STS").and()
      .hasFixedHoliday("CITY_DAY", NOVEMBER, 19).inSubdivision("13", "TRF").and()
      .hasFixedHoliday("CITY_DAY", JUNE, 24).inSubdivision("13", "VLG").and()
      .hasFixedHoliday("CITY_DAY", JUNE, 24).inSubdivision("13", "VCD").and()
      .hasFixedHoliday("CITY_DAY", JUNE, 24).inSubdivision("13", "VNG").and()
      .hasRelativeToEasterSundayHoliday("CITY_DAY").inSubdivision("13", "MTS").and()
      // Gondomar: Monday after the first Sunday of October
      .hasRelativeToWeekdayInMonthHoliday("CITY_DAY", MONDAY, AFTER, FIRST, SUNDAY, OCTOBER).inSubdivision("13", "GDM").and()
      // Lousada: Monday after the last Sunday of July
      .hasRelativeToWeekdayInMonthHoliday("CITY_DAY", MONDAY, AFTER, LAST, SUNDAY, JULY).inSubdivision("13", "LOU").and()
      // Maia: Monday after the second Sunday of July
      .hasRelativeToWeekdayInMonthHoliday("CITY_DAY", MONDAY, AFTER, SECOND, SUNDAY, JULY).inSubdivision("13", "MAI").and()
      // Paredes: Monday after the third Sunday of July
      .hasRelativeToWeekdayInMonthHoliday("CITY_DAY", MONDAY, AFTER, THIRD, SUNDAY, JULY).inSubdivision("13", "PRD")
      .check();
  }

  @Test
  void ensuresCityDaysInSantarem() {
    assertFor(PORTUGAL)
      .hasFixedHoliday("CITY_DAY", JUNE, 14).inSubdivision("14", "ABT").and()
      .hasChristianHoliday("ASCENSION_DAY").inSubdivision("14", "ACN").and()
      .hasChristianHoliday("ASCENSION_DAY").inSubdivision("14", "ALR").and()
      .hasFixedHoliday("CITY_DAY", APRIL, 2).inSubdivision("14", "APC").and()
      .hasChristianHoliday("ASCENSION_DAY").inSubdivision("14", "BNV").and()
      .hasChristianHoliday("ASCENSION_DAY").inSubdivision("14", "CTX").and()
      .hasChristianHoliday("ASCENSION_DAY").inSubdivision("14", "CHM").and()
      .hasChristianHoliday("EASTER_MONDAY").inSubdivision("14", "CNS").and()
      .hasFixedHoliday("CITY_DAY", AUGUST, 17).inSubdivision("14", "CCH").and()
      .hasFixedHoliday("CITY_DAY", NOVEMBER, 24).inSubdivision("14", "ENT").and()
      .hasFixedHoliday("CITY_DAY", JUNE, 13).inSubdivision("14", "FZZ").and()
      .hasChristianHoliday("ASCENSION_DAY").inSubdivision("14", "GLG").and()
      .hasChristianHoliday("EASTER_MONDAY").inSubdivision("14", "MAC").and()
      .hasFixedHoliday("CITY_DAY", JUNE, 20).inSubdivision("14", "ORM").and()
      .hasFixedHoliday("CITY_DAY", NOVEMBER, 6).inSubdivision("14", "RMR").and()
      .hasChristianHoliday("ASCENSION_DAY").inSubdivision("14", "SMG").and()
      .hasFixedHoliday("CITY_DAY", MARCH, 19).inSubdivision("14", "STR").and()
      .hasFixedHoliday("CITY_DAY", SEPTEMBER, 22).inSubdivision("14", "SRD").and()
      .hasFixedHoliday("CITY_DAY", MARCH, 1).inSubdivision("14", "TMR").and()
      .hasChristianHoliday("ASCENSION_DAY").inSubdivision("14", "TNV").and()
      .hasFixedHoliday("CITY_DAY", JUNE, 13).inSubdivision("14", "VNB")
      .check();
  }

  @Test
  void ensuresCityDaysInSetubal() {
    assertFor(PORTUGAL)
      .hasFixedHoliday("CITY_DAY", JUNE, 24).inSubdivision("15", "ASL").and()
      .hasFixedHoliday("CITY_DAY", JUNE, 24).inSubdivision("15", "ACH").and()
      .hasFixedHoliday("CITY_DAY", JUNE, 24).inSubdivision("15", "ALM").and()
      .hasFixedHoliday("CITY_DAY", JUNE, 28).inSubdivision("15", "BRR").and()
      .hasFixedHoliday("CITY_DAY", OCTOBER, 22).inSubdivision("15", "GDL").and()
      .hasFixedHoliday("CITY_DAY", JUNE, 29).inSubdivision("15", "MTJ").and()
      .hasFixedHoliday("CITY_DAY", JUNE, 1).inSubdivision("15", "PLM").and()
      .hasFixedHoliday("CITY_DAY", JULY, 25).inSubdivision("15", "STC").and()
      .hasFixedHoliday("CITY_DAY", JUNE, 29).inSubdivision("15", "SXL").and()
      .hasFixedHoliday("CITY_DAY", MAY, 4).inSubdivision("15", "SSB").and()
      .hasFixedHoliday("CITY_DAY", SEPTEMBER, 15).inSubdivision("15", "STB").and()
      .hasFixedHoliday("CITY_DAY", NOVEMBER, 24).inSubdivision("15", "SNS").and()
      // Moita: Tuesday after the second Sunday of September
      .hasRelativeToWeekdayInMonthHoliday("CITY_DAY", TUESDAY, AFTER, SECOND, SUNDAY, SEPTEMBER).inSubdivision("15", "MTA")
      .check();
  }

  @Test
  void ensuresCityDaysInVianaDoCastelo() {
    assertFor(PORTUGAL)
      .hasFixedHoliday("CITY_DAY", JULY, 11).inSubdivision("16", "AVV").and()
      .hasChristianHoliday("EASTER_MONDAY").inSubdivision("16", "CMN").and()
      .hasChristianHoliday("ASCENSION_DAY").inSubdivision("16", "MLG").and()
      .hasFixedHoliday("CITY_DAY", MARCH, 12).inSubdivision("16", "MNC").and()
      .hasFixedHoliday("CITY_DAY", AUGUST, 10).inSubdivision("16", "PCR").and()
      .hasFixedHoliday("CITY_DAY", AUGUST, 24).inSubdivision("16", "PTB").and()
      .hasFixedHoliday("CITY_DAY", SEPTEMBER, 20).inSubdivision("16", "PTL").and()
      .hasFixedHoliday("CITY_DAY", FEBRUARY, 18).inSubdivision("16", "VLC").and()
      .hasFixedHoliday("CITY_DAY", AUGUST, 20).inSubdivision("16", "VCT").and()
      .hasFixedHoliday("CITY_DAY", OCTOBER, 1).inSubdivision("16", "VNC")
      .check();
  }

  @Test
  void ensuresCityDaysInVilaReal() {
    assertFor(PORTUGAL)
      .hasFixedHoliday("CITY_DAY", NOVEMBER, 11).inSubdivision("17", "ALJ").and()
      .hasFixedHoliday("CITY_DAY", NOVEMBER, 6).inSubdivision("17", "BTC").and()
      .hasFixedHoliday("CITY_DAY", JULY, 8).inSubdivision("17", "CHV").and()
      .hasFixedHoliday("CITY_DAY", NOVEMBER, 30).inSubdivision("17", "MSF").and()
      .hasFixedHoliday("CITY_DAY", JULY, 25).inSubdivision("17", "MDB").and()
      .hasFixedHoliday("CITY_DAY", JUNE, 9).inSubdivision("17", "MTR").and()
      .hasFixedHoliday("CITY_DAY", MAY, 8).inSubdivision("17", "MUR").and()
      .hasFixedHoliday("CITY_DAY", AUGUST, 16).inSubdivision("17", "PRG").and()
      .hasFixedHoliday("CITY_DAY", AUGUST, 16).inSubdivision("17", "RPN").and()
      .hasFixedHoliday("CITY_DAY", SEPTEMBER, 8).inSubdivision("17", "SBS").and()
      .hasFixedHoliday("CITY_DAY", JANUARY, 13).inSubdivision("17", "SMP").and()
      .hasFixedHoliday("CITY_DAY", NOVEMBER, 6).inSubdivision("17", "VPC").and()
      .hasFixedHoliday("CITY_DAY", JUNE, 22).inSubdivision("17", "VPA").and()
      .hasFixedHoliday("CITY_DAY", JUNE, 13).inSubdivision("17", "VRL")
      .check();
  }

  @Test
  void ensuresCityDaysInViseu() {
    assertFor(PORTUGAL)
      .hasFixedHoliday("CITY_DAY", JUNE, 24).inSubdivision("18", "AMM").and()
      .hasFixedHoliday("CITY_DAY", JUNE, 29).inSubdivision("18", "CDR").and()
      .hasFixedHoliday("CITY_DAY", JUNE, 24).inSubdivision("18", "CNF").and()
      .hasFixedHoliday("CITY_DAY", SEPTEMBER, 8).inSubdivision("18", "LMG").and()
      .hasFixedHoliday("CITY_DAY", SEPTEMBER, 8).inSubdivision("18", "MGL").and()
      .hasFixedHoliday("CITY_DAY", JUNE, 24).inSubdivision("18", "MBR").and()
      .hasChristianHoliday("ASCENSION_DAY").inSubdivision("18", "MRT").and()
      .hasFixedHoliday("CITY_DAY", JUNE, 24).inSubdivision("18", "NLS").and()
      .hasFixedHoliday("CITY_DAY", OCTOBER, 7).inSubdivision("18", "OFR").and()
      .hasFixedHoliday("CITY_DAY", AUGUST, 25).inSubdivision("18", "PCT").and()
      .hasFixedHoliday("CITY_DAY", JUNE, 29).inSubdivision("18", "PND").and()
      .hasFixedHoliday("CITY_DAY", SEPTEMBER, 29).inSubdivision("18", "RSD").and()
      .hasChristianHoliday("ASCENSION_DAY").inSubdivision("18", "SCD").and()
      .hasFixedHoliday("CITY_DAY", JUNE, 24).inSubdivision("18", "SJP").and()
      .hasFixedHoliday("CITY_DAY", JUNE, 29).inSubdivision("18", "SPS").and()
      .hasFixedHoliday("CITY_DAY", AUGUST, 20).inSubdivision("18", "SAT").and()
      .hasFixedHoliday("CITY_DAY", MAY, 3).inSubdivision("18", "SRN").and()
      .hasFixedHoliday("CITY_DAY", JUNE, 24).inSubdivision("18", "TBC").and()
      .hasFixedHoliday("CITY_DAY", SEPTEMBER, 29).inSubdivision("18", "TRC").and()
      .hasFixedHoliday("CITY_DAY", SEPTEMBER, 16).inSubdivision("18", "TND").and()
      .hasFixedHoliday("CITY_DAY", MARCH, 2).inSubdivision("18", "VNP").and()
      .hasFixedHoliday("CITY_DAY", SEPTEMBER, 21).inSubdivision("18", "VIS").and()
      .hasFixedHoliday("CITY_DAY", MAY, 14).inSubdivision("18", "VZL").and()
      // Carregal do Sal: third Monday of July
      .hasFixedWeekdayHoliday("CITY_DAY", THIRD, MONDAY, JULY).inSubdivision("18", "CRS")
      .check();
  }
}
