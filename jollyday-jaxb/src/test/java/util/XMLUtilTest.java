package util;

import de.focus_shift.jaxb.XMLUtil;
import de.focus_shift.spi.Configuration;
import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBElement;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Unmarshaller;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.IOException;
import java.io.InputStream;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class XMLUtilTest {

  @Mock
  private XMLUtil.JAXBContextCreator contextCreator;
  @Mock
  private InputStream inputStream;

  @InjectMocks
  private final XMLUtil xmlUtil = new XMLUtil();

  @Test
  void testUnmarshallConfigurationNullCheck() {
    assertThrows(IllegalArgumentException.class, () -> xmlUtil.unmarshallConfiguration(null));
  }

  @Test
  void testUnmarshallConfigurationException() throws IOException, JAXBException {
    when(contextCreator.create(eq(XMLUtil.PACKAGE), any(ClassLoader.class))).thenThrow(new JAXBException("")).thenThrow(new JAXBException(""));
    assertThrows(IllegalStateException.class, () -> xmlUtil.unmarshallConfiguration(inputStream));
    verify(inputStream, never()).close();
  }

  @Test
  void testUnmarshallConfiguration() throws JAXBException {
    final JAXBContext ctx = mock(JAXBContext.class);
    final Unmarshaller unmarshaller = mock(Unmarshaller.class);
    @SuppressWarnings("unchecked") final JAXBElement<Configuration> element = mock(JAXBElement.class);
    when(contextCreator.create(eq(XMLUtil.PACKAGE), any(ClassLoader.class))).thenReturn(null).thenReturn(ctx);
    when(ctx.createUnmarshaller()).thenReturn(unmarshaller);
    when(unmarshaller.unmarshal(inputStream)).thenReturn(element);
    xmlUtil.unmarshallConfiguration(inputStream);
    verify(element).getValue();
  }
}
