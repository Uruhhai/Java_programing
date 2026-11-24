package lab3;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class LibraryModel {
    private Book[] books;

    public LibraryModel() {
        initializeDefaultData();
    }

    private void initializeDefaultData() {
        books = new Book[] {
                new Book("Кобзар", "Тарас Шевченко", "Дніпро", 1840, 250, 150.0),
                new Book("Тіні забутих предків", "Михайло Коцюбинський", "Фоліо", 1911, 160, 120.5),
                new Book("Захар Беркут", "Іван Франко", "А-ба-ба-га-ла-ма-га", 1883, 220, 180.0),
                new Book("Чорна рада", "Пантелеймон Куліш", "Знання", 1846, 320, 200.0),
                new Book("Кайдашева сім'я", "Іван Нечуй-Левицький", "Фоліо", 1879, 280, 140.0),
                new Book("Місто", "Валер'ян Підмогильний", "Книгоспілка", 1928, 260, 220.0),
                new Book("Тигролови", "Іван Багряний", "Українська просвіта", 1944, 240, 190.0),
                new Book("Лісова пісня", "Леся Українка", "Дніпро", 1911, 100, 110.0),
                new Book("Маруся Чурай", "Ліна Костенко", "А-ба-ба-га-ла-ма-га", 1979, 144, 250.0),
                new Book("Сто років самотності", "Габріель Гарсія Маркес", "Видавництво Старого Лева", 1967, 350, 350.0)
        };
    }

    public Book[] getBooks() {
        return books;
    }

    public void setBooks(Book[] books) {
        if (books == null) {
            throw new IllegalArgumentException("Масив книг не може бути null.");
        }
        this.books = books;
    }

    public List<Book> findByAuthor(String author) {
        List<Book> result = new ArrayList<>();
        for (Book book : books) {
            if (book != null && book.getAuthor().equalsIgnoreCase(author)) {
                result.add(book);
            }
        }
        return result;
    }

    public List<Book> findByPublisher(String publisher) {
        List<Book> result = new ArrayList<>();
        for (Book book : books) {
            if (book != null && book.getPublisher().equalsIgnoreCase(publisher)) {
                result.add(book);
            }
        }
        return result;
    }

    public List<Book> findByYearAfter(int year) {
        List<Book> result = new ArrayList<>();
        for (Book book : books) {
            if (book != null && book.getYear() > year) {
                result.add(book);
            }
        }
        return result;
    }

    public void sortByPublisher() {
        Arrays.sort(books, Comparator.comparing(
                Book::getPublisher,
                Comparator.nullsLast(String.CASE_INSENSITIVE_ORDER)
        ));
    }
}