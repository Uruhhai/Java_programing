package lab3;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Random;

public class LibraryModel {
    private Book[] books;

    public LibraryModel() {
        initializeDefaultData();
    }

    // Ініціалізація заздалегідь підготовленими даними (масив >= 10 елементів)
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

    // 1. Отримати список книг зазначеного автора
    public List<Book> findByAuthor(String author) {
        List<Book> result = new ArrayList<>();
        for (Book book : books) {
            if (book.getAuthor().equalsIgnoreCase(author)) {
                result.add(book);
            }
        }
        return result;
    }

    // 2. Отримати список книг, які видані зазначеним видавництвом
    public List<Book> findByPublisher(String publisher) {
        List<Book> result = new ArrayList<>();
        for (Book book : books) {
            if (book.getPublisher().equalsIgnoreCase(publisher)) {
                result.add(book);
            }
        }
        return result;
    }

    // 3. Отримати список книг, виданих пізніше вказаного року
    public List<Book> findByYearAfter(int year) {
        List<Book> result = new ArrayList<>();
        for (Book book : books) {
            if (book.getYear() > year) {
                result.add(book);
            }
        }
        return result;
    }

    // 4. Відсортувати книги за видавництвами (використовуючи Comparator)
    public void sortByPublisher() {
        Arrays.sort(books, Comparator.comparing(Book::getPublisher, String.CASE_INSENSITIVE_ORDER));
    }

    // Методи для вибору випадкових даних для пошуку
    public String getRandomAuthor() {
        Random rand = new Random();
        return books[rand.nextInt(books.length)].getAuthor();
    }

    public String getRandomPublisher() {
        Random rand = new Random();
        return books[rand.nextInt(books.length)].getPublisher();
    }

    public int getRandomYear() {
        Random rand = new Random();
        return books[rand.nextInt(books.length)].getYear();
    }
}