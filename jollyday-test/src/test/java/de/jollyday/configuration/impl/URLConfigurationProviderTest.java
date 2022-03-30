/**
 * Copyright 2012 Sven Diedrichsen
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either
 * express or implied. See the License for the specific language
 * governing permissions and limitations under the License.
 */
package de.jollyday.configuration.impl;

import de.jollyday.configuration.ConfigurationProvider;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.Properties;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class URLConfigurationProviderTest {

    private ConfigurationProvider urlConfigurationProvider = new URLConfigurationProvider();

    @Test
    void testPutConfigurationWithPropertyNotSet() {
        Properties props = urlConfigurationProvider.getProperties();
        assertTrue(props.isEmpty());
    }

    @AfterEach
    void teardown() {
        System.clearProperty(ConfigurationProvider.CONFIG_URLS_PROPERTY);
    }

    @Test
    void testPutConfigurationWithPropertySetEmpty() {
        System.setProperty(ConfigurationProvider.CONFIG_URLS_PROPERTY, "");
        Properties props = urlConfigurationProvider.getProperties();
        assertTrue(props.isEmpty());
    }

    @Test
    void testPutConfigurationWithPropertyWithIllegalURL() {
        System.setProperty(ConfigurationProvider.CONFIG_URLS_PROPERTY, "TestIllegalData");
        Properties props = urlConfigurationProvider.getProperties();
        assertTrue(props.isEmpty());
    }

    @Test
    void testPutConfigurationWithPropertyWithCorrectURL() {
        System.setProperty(ConfigurationProvider.CONFIG_URLS_PROPERTY, "file:./src/test/resources/url.load.properties");
        Properties props = urlConfigurationProvider.getProperties();
        assertFalse(props.isEmpty());
        assertEquals("de.jollyday.impl.DefaultHolidayManager", props.getProperty("manager.impl.test"), "Wrong new property.");
        assertEquals("ManagerOverloaded", props.getProperty("manager.impl"), "Wrong overloaded property.");
    }

}
