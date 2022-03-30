package de.jollyday.parameter;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Properties;

import static org.junit.jupiter.api.Assertions.assertEquals;

class UrlManagerParameterTest {

    private UrlManagerParameter urlManagerParameter;
    private URL url;

    @BeforeEach
    void setup() throws MalformedURLException {
        url = new URL("http://www.google.de");
        Properties properties = new Properties();
        urlManagerParameter = new UrlManagerParameter(url, properties);
    }

    @Test
    void testCreateCacheKey() {
        assertEquals("http://www.google.de", urlManagerParameter.createCacheKey(), "Unexpected cache key.");
    }

    @Test
    void testGetDisplayName() {
        assertEquals("http://www.google.de", urlManagerParameter.getDisplayName(), "Unexpected display name.");
    }

    @Test
    void testCreateResourceUrl() {
        assertEquals(url, urlManagerParameter.createResourceUrl(), "Unexpected url.");
    }

}
