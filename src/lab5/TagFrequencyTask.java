package lab5;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TagFrequencyTask {

    private static final Pattern TAG_PATTERN = Pattern.compile("<\\s*([a-zA-Z][a-zA-Z0-9-]*)\\b[^>]*/?>");

    public static String fetchHtml(String urlString) throws IOException {
        URL url;
        try {
            URI uri = new URI(urlString);
            if (!uri.isAbsolute() || uri.getScheme() == null) {
                throw new MalformedURLException("URL повинен містити схему (наприклад, https://).");
            }
            url = uri.toURL();
        } catch (URISyntaxException e) {
            throw new MalformedURLException("Некоректний синтаксис URL: " + e.getMessage());
        }

        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");
        connection.setConnectTimeout(10_000);
        connection.setReadTimeout(10_000);
        connection.setRequestProperty("User-Agent", "Mozilla/5.0 (Java Lab5 TagCounter)");

        try {
            int responseCode = connection.getResponseCode();
            if (responseCode < 200 || responseCode >= 300) {
                throw new IOException("HTTP статус відповіді: " + responseCode);
            }

            StringBuilder html = new StringBuilder();
            try (BufferedReader reader = new BufferedReader(
                    new InputStreamReader(connection.getInputStream(), StandardCharsets.UTF_8))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    html.append(line).append('\n');
                }
            }
            return html.toString();
        } finally {
            connection.disconnect();
        }
    }

    public static Map<String, Integer> countTags(String html) {
        Map<String, Integer> counts = new HashMap<>();
        if (html == null || html.isEmpty()) {
            return counts;
        }

        String cleanedHtml = html.replaceAll("<!--[\\s\\S]*?-->", "");
        Matcher matcher = TAG_PATTERN.matcher(cleanedHtml);

        while (matcher.find()) {
            String tagName = matcher.group(1).toLowerCase();
            counts.merge(tagName, 1, Integer::sum);
        }
        return counts;
    }

    // а. У порядку зростання в лексикографічному порядку тегів
    public static Map<String, Integer> sortByTagLexicographically(Map<String, Integer> counts) {
        return new TreeMap<>(counts);
    }

    // b. У порядку зростання частоти появи тегів
    public static Map<String, Integer> sortByFrequencyAscending(Map<String, Integer> counts) {
        Map<String, Integer> sorted = new LinkedHashMap<>();
        counts.entrySet().stream()
                .sorted(Map.Entry.<String, Integer>comparingByValue()
                        .thenComparing(Map.Entry.comparingByKey()))
                .forEach(entry -> sorted.put(entry.getKey(), entry.getValue()));
        return sorted;
    }

    private static void printTagMap(String title, Map<String, Integer> map) {
        System.out.println("\n" + title);
        if (map.isEmpty()) {
            System.out.println("Тегів не знайдено.");
            return;
        }
        for (Map.Entry<String, Integer> entry : map.entrySet()) {
            System.out.printf("  <%s> : %d%n", entry.getKey(), entry.getValue());
        }
    }

    public static void runInteractive(Scanner scanner) {
        System.out.println("\n--- Завдання 4: Підрахунок частоти тегів за URL ---");
        System.out.print("Введіть URL (наприклад, https://example.com): ");
        String urlInput = scanner.nextLine().trim();

        if (urlInput.isEmpty()) {
            System.out.println("Помилка: URL не може бути порожнім.");
            return;
        }

        try {
            String html = fetchHtml(urlInput);
            Map<String, Integer> counts = countTags(html);

            Map<String, Integer> byNameAsc = sortByTagLexicographically(counts);
            Map<String, Integer> byFreqAsc = sortByFrequencyAscending(counts);

            printTagMap("а) За зростанням у лексикографічному порядку тегів:", byNameAsc);
            printTagMap("b) За зростанням частоти появи тегів:", byFreqAsc);
        } catch (IOException | IllegalArgumentException e) {
            System.out.println("Помилка під час отримання або обробки сторінки: " + e.getMessage());
        }
    }
}