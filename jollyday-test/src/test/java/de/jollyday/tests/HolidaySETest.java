package de.jollyday.tests;

import de.jollyday.tests.base.AbstractCountryTestBase;
import org.junit.jupiter.api.Test;

class HolidaySETest extends AbstractCountryTestBase {

    private static final String ISO_CODE = "se";
    private static final int YEAR = 2016;

    @Test
    void testManagerSEStructure() throws Exception {
        validateCalendarData(ISO_CODE, YEAR);
    }

}
