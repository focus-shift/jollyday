package de.focus_shift.jollyday.core;

import java.util.Locale;
import java.util.Set;

import static java.util.Arrays.stream;
import static java.util.stream.Collectors.toUnmodifiableSet;

/**
 * This enum provides a list of all supported holiday calendars.
 */
public enum HolidayCalendar {

  ALBANIA("AL"), ARGENTINA("AR"), AUSTRALIA("AU"), AUSTRIA("AT"),
  BAHAMAS("BS"), BELARUS("BY"), BELGIUM("BE"), BERMUDA("BM"), BOLIVIA("BO"), BOSNIA_HERZIGOWINA("BA"), BRAZIL("BR"), BRITISH_VIRGIN_ISLANDS("VG"), BULGARIA("BG"),
  CANADA(Locale.CANADA.getCountry()), CAYMAN_ISLANDS("KY"), CHILE("CL"), COLOMBIA("CO"), COSTA_RICA("CR"), CROATIA("HR"), CZECH_REPUBLIC("CZ"),
  DENMARK("DK"), DOW_JONES_STOXX("DJ_STOXX"),
  ECUADOR("EC"), EGYPT("EG"), ESTONIA("EE"), ETHIOPIA("ET"),
  FINLAND("FI"), FRANCE(Locale.FRANCE.getCountry()),
  GERMANY(Locale.GERMANY.getCountry()), GUERNSEY("GG"), GREECE("GR"),
  HONG_KONG("HK"), HUNGARY("HU"),
  ICELAND("IS"), INDIA("IN"), IRELAND("IE"), ISLE_OF_MAN("IM"), ITALY(Locale.ITALY.getCountry()),
  JAPAN("JP"), JERSEY("JE"),
  KAZAKHSTAN("KZ"), KOSOVO("XK"),
  LATVIA("LV"), LIECHTENSTEIN("LI"), LITHUANIA("LT"), LONDON_METAL_EXCHANGE("LME"), LUXEMBOURG("LU"),
  MACEDONIA("MK"), MALTA("MT"), MOROCCO("MA"), MAURITIUS("MU"), MEXICO("MX"), MOLDOVA("MD"), MONTENEGRO("ME"),
  NETHERLANDS("NL"), NEW_ZEALAND("NZ"), NICARAGUA("NI"), NIGERIA("NG"), NORWAY("NO"), NYSE("NYSE"), NYSE_EURONEXT("NYSE_EURONEXT"),
  PANAMA("PA"), PARAGUAY("PY"), PERU("PE"), POLAND("PL"), PORTUGAL("PT"),
  ROMANIA("RO"), RUSSIA("RU"),
  SAUDI_ARABIA("SA"), SERBIA("RS"), SINGAPORE("SG"), SLOWAKIA("SK"), SLOWENIA("SI"), SOUTH_AFRICA("ZA"), SPAIN("ES"), SWEDEN("SE"), SWITZERLAND("CH"),
  TARGET("TARGET"), TURKEY("TR"),
  UKRAINE("UA"), UNITED_ARAB_EMIRATES("AE"), UNITED_KINGDOM(Locale.UK.getCountry()), UNITED_STATES(Locale.US.getCountry()), URUGUAY("UY"),
  VENEZUELA("VE");

  private final String id;

  HolidayCalendar(String id) {
    this.id = id;
  }

  /**
   * <p>
   * Getter for the field <code>id</code>.
   * </p>
   *
   * @return a {@link java.lang.String} object.
   */
  public String getId() {
    return id;
  }

  /**
   * Returns a set of all currently supported ISO 3166-1 alpha-2 codes.
   *
   * @return Set of supported calendar codes.
   */
  public static Set<String> getSupportedCalendarCodes() {
    return stream(HolidayCalendar.values())
      .map(HolidayCalendar::getId)
      .collect(toUnmodifiableSet());
  }
}
