package info.library.model.books;

import javax.xml.bind.annotation.XmlEnum;


@XmlEnum(String.class)
public enum Genre {
    CRIME, //Криминальная проза, детектив
    DETECTIVE, //Детектив
    SCIENCE, //Научная фантастика
    POST_APOCALYPTIC, //Постапокалипсис
    FANTASY, // Фэнтези
    ROMANCE, //Любовный роман
    WESTERN, // Вестерн
    HORROR, // Ужасы
    CLASSIC, //Классическая литература
    FAIRY_TALE, //Сказка
    HISTORICAL, // Историческая проза
    HUMOR, // Юмористическая проза
    THRILLER, // Триллер
    BIOGRAPHY, //Биография
    REFERENCE_BOOK, //Справочник
    ENCYCLOPEDIA, //Энциклопедия
    DICTIONARY, // Словарь
    TECHNICAL_WRITING, //Техническая литература
    COOKBOOK //Кулинарная книга
}
