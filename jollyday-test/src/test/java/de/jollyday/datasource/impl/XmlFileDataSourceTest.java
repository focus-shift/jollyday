package de.jollyday.datasource.impl;

import de.jollyday.ManagerParameter;
import de.jollyday.jaxb.XMLUtil;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.InputStream;
import java.net.URL;

import static org.mockito.Mockito.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class XmlFileDataSourceTest {

    @Mock
    XMLUtil xmlUtil;

    @InjectMocks
    private XmlFileDataSource xmlFileDataSource;

    @Test
    void testGetConfiguration() throws Exception {
        final ManagerParameter managerParameter = mock(ManagerParameter.class);
        URL resourceUrl = new URL("http://www.google.de");
        when(managerParameter.createResourceUrl()).thenReturn(resourceUrl);
        xmlFileDataSource.getConfiguration(managerParameter);
        verify(xmlUtil, times(1)).unmarshallConfiguration(any(InputStream.class));
    }
}