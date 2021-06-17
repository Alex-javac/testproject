package info.library.model.books;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.xml.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;

@ToString
@Setter
@Getter
@NoArgsConstructor
@XmlRootElement(name = "books")
@XmlAccessorType(XmlAccessType.NONE)
public class Books {

    @XmlElementWrapper(name="list-books", nillable = true)
    @XmlElement(name = "book", type = Book.class)
    public List book = new ArrayList();
}
