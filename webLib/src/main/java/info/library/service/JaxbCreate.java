package info.library.service;

import info.library.model.books.Book;
import info.library.model.books.Books;
import info.library.model.books.Genre;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.*;
import java.nio.file.Path;

public class JaxbCreate {
   static JAXBContext context;
    static Unmarshaller unmarshaller;
    static {
        try {
            context = JAXBContext.newInstance( Books.class,Book.class);
            unmarshaller = context.createUnmarshaller();
        } catch (JAXBException e) {
            e.printStackTrace();
        }
    }
    public static Books bookUnmarshalling(Path file) {
        try(BufferedReader bufferedReader = new BufferedReader(new FileReader(String.valueOf(file)));) {
            return (Books) unmarshaller.unmarshal(bufferedReader);
        } catch (JAXBException | IOException e) {
            e.printStackTrace();
        }
        return null;
    }

//    public static void bookMarshalling(Books books) {
//        try ( BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter("/home/alexander/IdeaProjects/LibraryProject/src/main/java/files/books.xml")))
//        {
//            context = JAXBContext.newInstance(Book.class, Books.class);
//            Marshaller marshaller = context.createMarshaller();
//            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
//
//            marshaller.marshal(books, bufferedWriter);
//
//
//        } catch (JAXBException | IOException e) {
//            e.printStackTrace();
//        }
//    }

}
