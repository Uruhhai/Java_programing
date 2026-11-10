package lab3;

import java.util.List;
import java.util.Scanner;

public class LibraryView {
    private Scanner scanner = new Scanner(System.in);

    public void printMessage(String message) {
        System.out.println(message);
    }

    public void printBooks(Book[] books) {
        System.out.println("\n--- Список книг у базі ---");
        if (books == null || books.length == 0) {
            System.out.println("Список порожній.");
            return;
        }
        for (int i = 0; i < books.length; i++) {
            System.out.println((i + 1) + ". " + books[i]);
        }
        System.out.println("--------------------------\n");
    }

    public void printBookList(List<Book> books, String criteria) {
        System.out.println("\n--- Результати пошуку (" + criteria + ") ---");
        if (books == null || books.isEmpty()) {
            System.out.println("За заданим критерієм книг не знайдено."); // Вимога п.3
        } else {
            for (int i = 0; i < books.size(); i++) {
                System.out.println((i + 1) + ". " + books.get(i));
            }
        }
        System.out.println("---------------------------------------------\n");
    }

    public int showMenu() {
        System.out.println("=== МЕНЮ УПРАВЛІННЯ БІБЛІОТЕКОЮ ===");
        System.out.println("1. Вивести вихідний масив книг");
        System.out.println("2. Отримати список книг зазначеного автора (випадковий вибір)");
        System.out.println("3. Отримати список книг зазначеного видавництва (випадковий вибір)");
        System.out.println("4. Отримати список книг, виданих пізніше вказаного року (випадковий вибір)");
        System.out.println("5. Відсортувати книги за видавництвами (Comparator)");
        System.out.println("0. Вихід");
        System.out.print("Оберіть пункт меню: ");

        while (!scanner.hasNextInt()) {
            System.out.print("Помилка! Введіть число від 0 до 5: ");
            scanner.next();
        }
        return scanner.nextInt();
    }
}