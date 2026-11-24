package lab5;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

public class StreamCipherTask {

    // Метод шифрування потоку
    public static void encryptStream(String inputFilePath, String outputFilePath, char keyChar) throws IOException {
        try (BufferedReader reader = new BufferedReader(
                new InputStreamReader(new FileInputStream(inputFilePath), StandardCharsets.UTF_8));
             EncryptingWriter writer = new EncryptingWriter(
                     new BufferedWriter(new OutputStreamWriter(new FileOutputStream(outputFilePath), StandardCharsets.UTF_8)),
                     keyChar)) {

            int ch;
            while ((ch = reader.read()) != -1) {
                writer.write(ch);
            }
            writer.flush();
        }
    }

    // Метод дешифрування потоку
    public static void decryptStream(String inputFilePath, String outputFilePath, char keyChar) throws IOException {
        try (DecryptingReader reader = new DecryptingReader(
                new BufferedReader(new InputStreamReader(new FileInputStream(inputFilePath), StandardCharsets.UTF_8)),
                keyChar);
             BufferedWriter writer = new BufferedWriter(
                     new OutputStreamWriter(new FileOutputStream(outputFilePath), StandardCharsets.UTF_8))) {

            int ch;
            while ((ch = reader.read()) != -1) {
                writer.write(ch);
            }
            writer.flush();
        }
    }

    public static void runInteractive(Scanner scanner) {
        System.out.println("\n--- Завдання 3: Шифрування / Дешифрування символьного потоку ---");
        System.out.println("1. Зашифрувати файл");
        System.out.println("2. Розшифрувати файл");
        System.out.print("Оберіть дію: ");
        String mode = scanner.nextLine().trim();

        System.out.print("Введіть шлях до вхідного файлу: ");
        String inputPath = scanner.nextLine().trim();

        System.out.print("Введіть шлях до вихідного файлу: ");
        String outputPath = scanner.nextLine().trim();

        System.out.print("Введіть ключовий символ: ");
        String keyInput = scanner.nextLine();

        if (inputPath.isEmpty() || outputPath.isEmpty() || keyInput.isEmpty()) {
            System.out.println("Помилка: шляхи до файлів та ключовий символ не повинні бути порожніми.");
            return;
        }

        char keyChar = keyInput.charAt(0);

        try {
            if ("1".equals(mode)) {
                encryptStream(inputPath, outputPath, keyChar);
                System.out.println("Файл успішно зашифровано у: " + outputPath);
            } else if ("2".equals(mode)) {
                decryptStream(inputPath, outputPath, keyChar);
                System.out.println("Файл успішно розшифровано у: " + outputPath);
            } else {
                System.out.println("Невідома дія. Оберіть 1 або 2.");
            }
        } catch (IOException e) {
            System.out.println("Помилка роботи з потоком даних: " + e.getMessage());
        }
    }
}