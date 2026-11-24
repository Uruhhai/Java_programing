package lab3;

import java.util.List;
import java.util.Scanner;

public class LibraryView {
    private final Scanner scanner;

    public LibraryView(Scanner scanner) {
        this.scanner = scanner;
    }

    public void printMessage(String message) {
        System.out.println(message);
    }

    public void printBooks(Book[] books) {
        System.out.println("\n--- Список книг ---");
        if (books == null || books.length == 0) {
            System.out.println("Список порожній.");
            return;
        }
        for (int i = 0; i < books.length; i++) {
            System.out.println((i + 1) + ". " + books[i]);
        }
        System.out.println("-------------------\n");
    }

    public void printBookList(List<Book> books, String criteria) {
        System.out.println("\n--- Результати пошуку (" + criteria + ") ---");
        if (books == null || books.isEmpty()) {
            System.out.println("За заданим критерієм книг не знайдено.");
        } else {
            for (int i = 0; i < books.size(); i++) {
                System.out.println((i + 1) + ". " + books.get(i));
            }
        }
        System.out.println("---------------------------------------------\n");
    }

    public String promptString(String prompt) {
        System.out.print(prompt);
        return scanner.nextLine().trim();
    }

    public int promptInt(String prompt) {
        while (true) {
            System.out.print(prompt);
            String input = scanner.nextLine().trim();
            try {
                return Integer.parseInt(input);
            } catch (NumberFormatException e) {
                System.out.println("Помилка вводу: очікується ціле число. Спробуйте ще раз.");
            }
        }
    }

    public int showMenu() {
        System.out.println("=== МЕНЮ УПРАВЛІННЯ БІБЛІОТЕКОЮ ===");
        System.out.println("1. Вивести поточний масив книг");
        System.out.println("2. Пошук книг за автором (ввід з клавіатури)");
        System.out.println("3. Пошук книг за видавництвом (ввід з клавіатури)");
        System.out.println("4. Пошук книг, виданих пізніше вказаного року (ввід з клавіатури)");
        System.out.println("5. Відсортувати книги за видавництвами (Comparator)");
        System.out.println("6. Зберегти набір книг у файл (серіалізація)");
        System.out.println("7. Прочитати набір книг із файлу (десеріалізація)");
        System.out.println("0. Вихід");
        return promptInt("Оберіть пункт меню: ");
    }
}