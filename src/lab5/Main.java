package lab5;

import lab3.LibraryController;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        while (running) {
            System.out.println("\n================ LAB 5: I/O STREAMS ================");
            System.out.println("1. Рядок з максимальною кількістю слів у файлі");
            System.out.println("2. Бібліотека (Lab 3 + серіалізація/десеріалізація та пошук)");
            System.out.println("3. Шифрування / дешифрування символьного потоку");
            System.out.println("4. Частота появи HTML-тегів за URL");
            System.out.println("0. Вихід");
            System.out.print("Оберіть завдання: ");

            String choice = scanner.nextLine().trim();
            switch (choice) {
                case "1":
                    MaxWordsInFileTask.runInteractive(scanner);
                    break;
                case "2":
                    LibraryController.runInteractive(scanner);
                    break;
                case "3":
                    StreamCipherTask.runInteractive(scanner);
                    break;
                case "4":
                    TagFrequencyTask.runInteractive(scanner);
                    break;
                case "0":
                    running = false;
                    System.out.println("Програму завершено.");
                    break;
                default:
                    System.out.println("Невірний вибір. Введіть число від 0 до 4.");
            }
        }
    }
}