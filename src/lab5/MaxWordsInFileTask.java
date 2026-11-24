package lab5;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

public class MaxWordsInFileTask {

    public static String findLineWithMaxWords(String filePath) throws IOException {
        String bestLine = null;
        int maxWords = -1;

        try (BufferedReader reader = new BufferedReader(
                new InputStreamReader(new FileInputStream(filePath), StandardCharsets.UTF_8))) {

            String line;
            while ((line = reader.readLine()) != null) {
                int wordCount = countWords(line);
                if (wordCount > maxWords) {
                    maxWords = wordCount;
                    bestLine = line;
                }
            }
        }
        return bestLine;
    }

    public static int countWords(String line) {
        if (line == null) {
            return 0;
        }
        String trimmed = line.trim();
        if (trimmed.isEmpty()) {
            return 0;
        }
        return trimmed.split("\\s+").length;
    }

    public static void runInteractive(Scanner scanner) {
        System.out.println("\n--- Завдання 1: Рядок з максимальною кількістю слів ---");
        System.out.print("Введіть шлях до файлу: ");
        String path = scanner.nextLine().trim();

        if (path.isEmpty()) {
            System.out.println("Помилка: шлях до файлу не може бути порожнім.");
            return;
        }

        try {
            String result = findLineWithMaxWords(path);
            if (result == null) {
                System.out.println("Файл порожній.");
            } else {
                System.out.println("Кількість слів: " + countWords(result));
                System.out.println("Рядок: " + result);
            }
        } catch (IOException e) {
            System.out.println("Помилка читання файлу: " + e.getMessage());
        }
    }
}