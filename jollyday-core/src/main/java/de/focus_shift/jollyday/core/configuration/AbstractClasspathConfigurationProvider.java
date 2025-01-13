package de.focus_shift.jollyday.core.configuration;

import de.focus_shift.jollyday.core.util.ResourceUtil;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Optional;
import java.util.Properties;

abstract class AbstractClasspathConfigurationProvider implements ConfigurationProvider {

    static final String DEFAULT_CONFIGURATION_FILE_NAME = "jollyday.properties";

    private final URL configurationFile;

    AbstractClasspathConfigurationProvider() {
        this(false);
    }

    AbstractClasspathConfigurationProvider(final boolean searchOnlyInJar) {
        this.configurationFile = getConfigurationFile(searchOnlyInJar).orElse(null);
    }

    @Override
    public Properties getProperties() {
        final Properties properties = new Properties();

        if (configurationFile != null) {
            try (final InputStream inputStream = configurationFile.openStream()) {
                properties.load(inputStream);
            } catch (IOException e) {
                throw new IllegalStateException("Could not load default configuration from classpath.", e);
            }
        }

        return properties;
    }

    private Optional<URL> getConfigurationFile(final boolean searchOnlyInJar) {
        return ResourceUtil.getResource(DEFAULT_CONFIGURATION_FILE_NAME, searchOnlyInJar);
    }
}
