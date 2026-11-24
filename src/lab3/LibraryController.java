package lab3;

import lab5.BookFileStorage;

import java.io.IOException;
import java.util.List;
import java.util.Scanner;

public class LibraryController {
    private final LibraryModel model;
    private final LibraryView view;
    private final BookFileStorage fileStorage;

    public LibraryController(LibraryModel model, LibraryView view, BookFileStorage fileStorage) {
        this.model = model;
        this.view = view;
        this.fileStorage = fileStorage;
    }

    public void run() {
        view.printMessage("Програму запущено за шаблоном MVC.");
        view.printBooks(model.getBooks());

        boolean running = true;
        while (running) {
            int choice = view.showMenu();
            switch (choice) {
                case 1:
                    view.printBooks(model.getBooks());
                    break;
                case 2: {
                    String author = view.promptString("Введіть ПІБ автора: ");
                    if (author.isEmpty()) {
                        view.printMessage("Помилка: ім'я автора не може бути порожнім.");
                        break;
                    }
                    List<Book> found = model.findByAuthor(author);
                    view.printBookList(found, "Автор: " + author);
                    break;
                }
                case 3: {
                    String publisher = view.promptString("Введіть назву видавництва: ");
                    if (publisher.isEmpty()) {
                        view.printMessage("Помилка: назва видавництва не може бути порожньою.");
                        break;
                    }
                    List<Book> found = model.findByPublisher(publisher);
                    view.printBookList(found, "Видавництво: " + publisher);
                    break;
                }
                case 4: {
                    int year = view.promptInt("Введіть рік: ");
                    List<Book> found = model.findByYearAfter(year);
                    view.printBookList(found, "Видані пізніше " + year + " року");
                    break;
                }
                case 5:
                    model.sortByPublisher();
                    view.printMessage("\nМасив відсортовано за видавництвами.");
                    view.printBooks(model.getBooks());
                    break;
                case 6: {
                    String path = view.promptString("Введіть шлях та ім'я файлу для збереження (наприклад, books.dat): ");
                    if (path.isEmpty()) {
                        view.printMessage("Помилка: шлях до файлу не може бути порожнім.");
                        break;
                    }
                    try {
                        fileStorage.saveBooks(path, model.getBooks());
                        view.printMessage("Набір книг успішно збережено у файл: " + path);
                    } catch (IOException e) {
                        view.printMessage("Помилка під час збереження у файл: " + e.getMessage());
                    }
                    break;
                }
                case 7: {
                    String path = view.promptString("Введіть шлях та ім'я файлу для читання (наприклад, books.dat): ");
                    if (path.isEmpty()) {
                        view.printMessage("Помилка: шлях до файлу не може бути порожнім.");
                        break;
                    }
                    try {
                        Book[] loadedBooks = fileStorage.loadBooks(path);
                        model.setBooks(loadedBooks);
                        view.printMessage("Набір книг успішно завантажено з файлу: " + path);
                        view.printBooks(model.getBooks());
                    } catch (IOException | ClassNotFoundException | IllegalArgumentException e) {
                        view.printMessage("Помилка під час читання файлу: " + e.getMessage());
                    }
                    break;
                }
                case 0:
                    running = false;
                    view.printMessage("Роботу завершено.");
                    break;
                default:
                    view.printMessage("Невірний пункт меню. Оберіть значення зі списку.");
            }
        }
    }

    public static void runInteractive(Scanner scanner) {
        LibraryModel model = new LibraryModel();
        LibraryView view = new LibraryView(scanner);
        BookFileStorage fileStorage = new BookFileStorage();
        LibraryController controller = new LibraryController(model, view, fileStorage);
        controller.run();
    }
}