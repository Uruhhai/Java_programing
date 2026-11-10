package lab3;

import java.util.List;

public class LibraryController {
    private LibraryModel model;
    private LibraryView view;

    public LibraryController(LibraryModel model, LibraryView view) {
        this.model = model;
        this.view = view;
    }

    public void run() {
        view.printMessage("Програма успішно ініціалізована за шаблоном MVC.");
        // Вимога п.4: Виведення вихідного масиву після створення
        view.printBooks(model.getBooks());

        boolean running = true;
        while (running) {
            int choice = view.showMenu();
            switch (choice) {
                case 1:
                    view.printBooks(model.getBooks());
                    break;
                case 2:
                    String randomAuthor = model.getRandomAuthor();
                    List<Book> authorBooks = model.findByAuthor(randomAuthor);
                    view.printBookList(authorBooks, "Автор: " + randomAuthor);
                    break;
                case 3:
                    String randomPublisher = model.getRandomPublisher();
                    List<Book> publisherBooks = model.findByPublisher(randomPublisher);
                    view.printBookList(publisherBooks, "Видавництво: " + randomPublisher);
                    break;
                case 4:
                    int randomYear = model.getRandomYear();
                    List<Book> yearBooks = model.findByYearAfter(randomYear);
                    view.printBookList(yearBooks, "Видані пізніше року: " + randomYear);
                    break;
                case 5:
                    model.sortByPublisher();
                    view.printMessage("\nМасив успішно впорядковано за назвою видавництва!");
                    view.printBooks(model.getBooks());
                    break;
                case 0:
                    running = false;
                    view.printMessage("Вихід з програми. До побачення!");
                    break;
                default:
                    view.printMessage("Невірний пункт меню. Спробуйте ще раз.");
            }
        }
    }
}