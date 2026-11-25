package lab6;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Translator translator = new Translator();
        seedDefaultDictionary(translator);

        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        System.out.println("=== LAB 6: ПЕРЕКЛАДАЧ (HASHMAP) ===");
        System.out.println("Словник заповнено базовими словами (" + translator.getDictionarySize() + " пар).");

        while (running) {
            printMenu();
            String choice = scanner.nextLine().trim();

            switch (choice) {
                case "1" -> translateFromKeyboard(translator, scanner);
                case "2" -> addPairFromKeyboard(translator, scanner);
                case "3" -> printDictionary(translator);
                case "0" -> {
                    running = false;
                    System.out.println("Роботу завершено.");
                }
                default -> System.out.println("Невірний пункт меню. Оберіть 0, 1, 2 або 3.");
            }
        }
    }

    private static void printMenu() {
        System.out.println("\n--- Меню ---");
        System.out.println("1. Ввести фразу англійською та перекласти");
        System.out.println("2. Додати нову пару слів (англійське -> українське) з клавіатури");
        System.out.println("3. Показати поточний словник");
        System.out.println("0. Вихід");
        System.out.print("Ваш вибір: ");
    }

    private static void translateFromKeyboard(Translator translator, Scanner scanner) {
        System.out.print("Введіть фразу англійською мовою: ");
        String phrase = scanner.nextLine();

        if (phrase.isBlank()) {
            System.out.println("Помилка: фраза не може бути порожньою.");
            return;
        }

        String translated = translator.translatePhrase(phrase);
        System.out.println("Переклад українською: " + translated);
    }

    private static void addPairFromKeyboard(Translator translator, Scanner scanner) {
        System.out.print("Введіть англійське слово: ");
        String en = scanner.nextLine();

        System.out.print("Введіть український переклад: ");
        String uk = scanner.nextLine();

        try {
            translator.addWordPair(en, uk);
            System.out.println("Пару додано. Поточний розмір словника: " + translator.getDictionarySize());
        } catch (IllegalArgumentException e) {
            System.out.println("Помилка додавання: " + e.getMessage());
        }
    }

    private static void printDictionary(Translator translator) {
        System.out.println("\n--- Поточний словник (" + translator.getDictionarySize() + " слів) ---");
        translator.getDictionaryView().forEach((en, uk) ->
                System.out.printf("  %-15s -> %s%n", en, uk)
        );
    }

    private static void seedDefaultDictionary(Translator translator) {
        translator.addWordPair("hello", "привіт");
        translator.addWordPair("world", "світ");
        translator.addWordPair("java", "джава");
        translator.addWordPair("code", "код");
        translator.addWordPair("stream", "потік");
        translator.addWordPair("file", "файл");
        translator.addWordPair("book", "книга");
        translator.addWordPair("read", "читати");
        translator.addWordPair("write", "писати");
        translator.addWordPair("good", "добрий");
        translator.addWordPair("morning", "ранок");
        translator.addWordPair("evening", "вечір");
        translator.addWordPair("friend", "друг");
        translator.addWordPair("system", "система");
        translator.addWordPair("test", "тест");
    }
}