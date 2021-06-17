package info.library.service;

import org.xml.sax.SAXException;

import javax.xml.XMLConstants;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;
import java.io.File;
import java.io.IOException;
import java.nio.file.Path;


public class ValidationChecker {
   static SchemaFactory factory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
   static Schema schema;
    static {
        try {
            schema = factory.newSchema(new File("/home/alexander/IdeaProjects/LibraryProject/src/main/java/files/validator.xsd"));
        } catch (SAXException e) {
            e.printStackTrace();
        }
    }
  static Validator validator = schema.newValidator();

    public static boolean validateXMLSchema( Path xmlPath) {
        try {
            validator.validate(new StreamSource(String.valueOf(xmlPath)));
        } catch (IOException | SAXException e) {
            System.out.println("Exception: " + e.getMessage());
            return false;
        }
        return true;
    }
}
