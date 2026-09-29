package lab1;

import java.util.Scanner;

public class NumberConverter {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Введіть ціле позитивне число в десятковій системі: ");

        // Перевірка, чи ввів користувач саме ціле число
        if (scanner.hasNextInt()) {
            int number = scanner.nextInt();

            if (number > 0) {
                // Виклик функції для різних систем числення
                String binary = convertToBase(number, 2);
                String octal = convertToBase(number, 8);
                String hex = convertToBase(number, 16);

                // Виведення результатів
                System.out.println("Десяткове число: " + number);
                System.out.println("Двійкова система: " + binary);
                System.out.println("Вісімкова система: " + octal);
                System.out.println("Шістнадцяткова система: " + hex);
            } else {
                System.out.println("Помилка! Число має бути позитивним (> 0).");
            }
        } else {
            System.out.println("Помилка! Ви ввели не ціле число.");
        }

        scanner.close();
    }

    /**
     * Універсальна функція для переведення десяткового числа в іншу систему числення.
     * @param number Десяткове число.
     * @param base Основа цільової системи числення (2, 8 або 16).
     * @return Рядок (String), що представляє число в новій системі.
     */
    public static String convertToBase(int number, int base) {
        // Якщо число дорівнює 0, повертаємо "0" (хоча за умовою число позитивне, але це вважаю доречною підстраховкою)
        if (number == 0) {
            return "0";
        }

        // Використовуємо StringBuilder для ефективного створення рядка в циклі
        StringBuilder result = new StringBuilder();

        // Масив символів для відображення залишків
        // Для двійкової/вісімкової використовуються лише цифри, для 16-ї додаються літери
        char[] digits = {'0', '1', '2', '3', '4', '5', '6', '7',
                '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};

        // Цикл працює, поки число більше нуля
        while (number > 0) {
            // Знаходимо залишок від ділення
            int remainder = number % base;

            // Додаємо відповідний символ з масиву до результату
            result.append(digits[remainder]);

            // Ділимо число на основу, щоб перейти до наступного розряду
            number = number / base;
        }

        // Оскільки залишки ми отримували з кінця, рядок потрібно перевернути
        return result.reverse().toString();
    }
}