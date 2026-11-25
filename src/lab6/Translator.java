package lab6;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Translator {
    private final Map<String, String> dictionary = new HashMap<>();
    private static final Pattern TOKEN_PATTERN = Pattern.compile("[A-Za-z']+");

    public void addWordPair(String englishWord, String ukrainianWord) {
        if (englishWord == null || ukrainianWord == null) {
            throw new IllegalArgumentException("Слово та переклад не можуть бути null.");
        }
        String cleanEn = englishWord.trim().toLowerCase(Locale.ROOT);
        String cleanUk = ukrainianWord.trim().toLowerCase(Locale.ROOT);

        if (cleanEn.isEmpty() || cleanUk.isEmpty()) {
            throw new IllegalArgumentException("Англійське слово та український переклад не можуть бути порожніми.");
        }
        dictionary.put(cleanEn, cleanUk);
    }

    public String translatePhrase(String phrase) {
        if (phrase == null || phrase.isBlank()) {
            return "";
        }

        Matcher matcher = TOKEN_PATTERN.matcher(phrase);
        StringBuilder result = new StringBuilder();

        while (matcher.find()) {
            String word = matcher.group();
            String lookupKey = word.toLowerCase(Locale.ROOT);
            String translation = dictionary.get(lookupKey);

            String replacement = (translation != null) ? translation : "[" + word + "]";
            matcher.appendReplacement(result, Matcher.quoteReplacement(replacement));
        }
        matcher.appendTail(result);

        return result.toString();
    }

    public int getDictionarySize() {
        return dictionary.size();
    }

    public Map<String, String> getDictionaryView() {
        return Map.copyOf(dictionary);
    }
}