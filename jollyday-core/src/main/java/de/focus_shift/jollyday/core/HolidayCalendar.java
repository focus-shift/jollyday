package de.focus_shift.jollyday.core;

import org.jspecify.annotations.NonNull;

import java.util.Locale;
import java.util.Set;

import static java.util.Arrays.stream;
import static java.util.stream.Collectors.toUnmodifiableSet;

/**
 * This enum provides a list of all supported holiday calendars.
 */
public enum HolidayCalendar {

  ANTARCTICA("AQ"), ALBANIA("AL"), ALGERIA("DZ"), ANDORRA("AD"), ANGOLA("AO"), ARGENTINA("AR"), ARMENIA("AM"), AUSTRALIA("AU"), AUSTRIA("AT"),
  BAHAMAS("BS"), BANGLADESH("BD"), BARBADOS("BB"), BELARUS("BY"), BELGIUM("BE"), BENIN("BJ"), BERMUDA("BM"), BOLIVIA("BO"), BOSNIA_HERZIGOWINA("BA"), BOTSWANA("BW"), BRAZIL("BR"), BRITISH_VIRGIN_ISLANDS("VG"), BULGARIA("BG"), BURKINA_FASO("BF"),
  CAMEROON("CM"), CANADA(Locale.CANADA.getCountry()), CAYMAN_ISLANDS("KY"), CENTRAL_AFRICAN_REPUBLIC("CF"), CHAD("TD"), CHILE("CL"), COLOMBIA("CO"), CONGO("CD"), COSTA_RICA("CR"), CROATIA("HR"), CUBA("CU"), CZECH_REPUBLIC("CZ"), CYPRUS("CY"),
  DENMARK("DK"), DOW_JONES_STOXX("DJ_STOXX"),
  ECUADOR("EC"), EGYPT("EG"), EL_SALVADOR("SV"), ESTONIA("EE"), ETHIOPIA("ET"),
  FINLAND("FI"), FAROE_ISLANDS("FO"), FRANCE(Locale.FRANCE.getCountry()),
  GEORGIA("GE"), GERMANY(Locale.GERMANY.getCountry()), GAMBIA("GM"), GHANA("GH"), GIBRALTAR("GI"), GUYANA("GY"), GUATEMALA("GT"), GUERNSEY("GG"), GREECE("GR"), GREENLAND("GL"), GUINEA("GN"),
  HONDURAS("HN"), HONG_KONG("HK"), HUNGARY("HU"),
  ICELAND("IS"), INDIA("IN"), IRELAND("IE"), ISLE_OF_MAN("IM"), ITALY(Locale.ITALY.getCountry()),
  JAMAICA("JM"), JAPAN("JP"), JERSEY("JE"),
  KAZAKHSTAN("KZ"), KOSOVO("XK"),
  LIBERIA("LR"), LIBYA("LY"),
  LATVIA("LV"), LIECHTENSTEIN("LI"), LITHUANIA("LT"), LONDON_METAL_EXCHANGE("LME"), LUXEMBOURG("LU"),
  MACEDONIA("MK"), MADAGASCAR("MG"), MALI("ML"), MALTA("MT"), MAURITANIA("MR"), MONACO("MC"), MOROCCO("MA"), MAURITIUS("MU"), MEXICO("MX"), MOLDOVA("MD"), MONTENEGRO("ME"),
  NAMIBIA("NA"), NETHERLANDS("NL"), NEW_ZEALAND("NZ"), NICARAGUA("NI"), NIGER("NE"), NIGERIA("NG"), NORWAY("NO"), NYSE("NYSE"), NYSE_EURONEXT("NYSE_EURONEXT"),
  PANAMA("PA"), PARAGUAY("PY"), PERU("PE"), POLAND("PL"), PORTUGAL("PT"),
  ROMANIA("RO"), RUSSIA("RU"),
  SAN_MARINO("SM"), SAUDI_ARABIA("SA"), SERBIA("RS"), SIERRA_LEONE("SL"), SEYCHELLES("SC"), SINGAPORE("SG"), SLOWAKIA("SK"), SLOWENIA("SI"), SUDAN("SD"), SOUTH_AFRICA("ZA"), SOUTH_SUDAN("SS"), SPAIN("ES"), SURINAME("SR"), SWEDEN("SE"), SWITZERLAND("CH"),
  TARGET("TARGET"), TARGET2_SECURITIES("TARGET2_SECURITIES"), TOGO("TG"), TUNISIA("TN"), TURKEY("TR"),
  UGANDA("UG"), UKRAINE("UA"), UNITED_ARAB_EMIRATES("AE"), UNITED_KINGDOM(Locale.UK.getCountry()), UNITED_STATES(Locale.US.getCountry()), URUGUAY("UY"),
  VATICAN_CITY("VA"), VENEZUELA("VE"), VIETNAM("VN"),
  ZAMBIA("ZM"), ZIMBABWE("ZW");

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
  public @NonNull String getId() {
    return id;
  }

  /**
   * Returns a set of all currently supported ISO 3166-1 alpha-2 codes.
   *
   * @return Set of supported calendar codes.
   */
  public static @NonNull Set<String> getSupportedCalendarCodes() {
    return stream(HolidayCalendar.values())
      .map(HolidayCalendar::getId)
      .collect(toUnmodifiableSet());
  }
}
