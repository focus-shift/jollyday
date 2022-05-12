package de.focus_shift.util;

import de.focus_shift.config.Configuration;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.IOException;
import java.io.InputStream;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class XMLUtilTest {

  @Mock
  XMLUtil.JAXBContextCreator contextCreator;
  @Mock
  InputStream inputStream;

  @InjectMocks
  XMLUtil xmlUtil = new XMLUtil();

  @Test
  public void testUnmarshallConfigurationNullCheck() {
    assertThrows(IllegalArgumentException.class, () -> xmlUtil.unmarshallConfiguration(null));
  }

  @Test
  public void testUnmarshallConfigurationException() throws IOException, JAXBException {
    when(contextCreator.create(eq(XMLUtil.PACKAGE), any(ClassLoader.class))).thenThrow(new JAXBException(""))
      .thenThrow(new JAXBException(""));
    assertThrows(IllegalStateException.class, () -> xmlUtil.unmarshallConfiguration(inputStream));
    verify(inputStream, never()).close();
  }

  @Test
  public void testUnmarshallConfiguration() throws IOException, JAXBException {
    JAXBContext ctx = mock(JAXBContext.class);
    Unmarshaller unmarshaller = mock(Unmarshaller.class);
    @SuppressWarnings("unchecked")
    JAXBElement<Configuration> element = mock(JAXBElement.class);
    when(contextCreator.create(eq(XMLUtil.PACKAGE), any(ClassLoader.class))).thenReturn(null).thenReturn(ctx);
    when(ctx.createUnmarshaller()).thenReturn(unmarshaller);
    when(unmarshaller.unmarshal(inputStream)).thenReturn(element);
    xmlUtil.unmarshallConfiguration(inputStream);
    verify(element).getValue();
  }
}
