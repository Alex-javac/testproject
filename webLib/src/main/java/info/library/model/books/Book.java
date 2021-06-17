package info.library.model.books;
import lombok.*;

import javax.xml.bind.annotation.*;
import java.util.List;

@ToString
@Setter
@Getter
@EqualsAndHashCode
@NoArgsConstructor
@XmlAccessorType(XmlAccessType.NONE)
@XmlType(name = "book")
public class Book {
    @XmlAttribute
    private int id;
    @XmlElement
    private Genre genre;
    @XmlElement
    private String title;
    @XmlElement
    private String author;
//    @XmlElementWrapper(name="list-authors", nillable = true)
//    @XmlElement(name = "author", type = Author.class)
//    private List<Author> author;
}
