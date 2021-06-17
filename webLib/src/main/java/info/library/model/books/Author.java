package info.library.model.books;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@ToString
@Setter
@Getter
@NoArgsConstructor
@XmlAccessorType(XmlAccessType.NONE)
@XmlType(name = "author")
public class Author {
    @XmlElement
    String name;
}
