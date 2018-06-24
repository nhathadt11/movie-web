package study.nhatha.web.util;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import java.io.ByteArrayOutputStream;
import java.io.OutputStream;

public final class XmlUtils {
  public static <T> void marshal(T jaxbObject, Class<T> clazz, OutputStream outputStream)
      throws JAXBException {

    JAXBContext jaxbContext = JAXBContext.newInstance(clazz);
    Marshaller marshaller = jaxbContext.createMarshaller();
    marshaller.setProperty(Marshaller.JAXB_FRAGMENT, true);

    marshaller.marshal(jaxbObject, outputStream);
  }

  public static <T> String marshal(T jaxbObject, Class<T> clazz) {
    OutputStream outputStream = new ByteArrayOutputStream();

    try {
      marshal(jaxbObject, clazz, outputStream);
    } catch (JAXBException e) {
      e.printStackTrace();
    }

    return outputStream.toString();
  }

  private XmlUtils() {
  }
}
