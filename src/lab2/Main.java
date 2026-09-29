package lab2;

import com.google.gson.Gson;

public class Main {
    public static void main(String[] args) {
        // a. Створюємо екземпляр Person
        Person originalPerson = new Person("Євген", "Закліковський", 19);
        System.out.println("Початковий об'єкт: " + originalPerson);

        // Ініціалізуємо об'єкт Gson для роботи з JSON
        Gson gson = new Gson();

        // b. Конвертуємо об'єкт Person у JSON-рядок (Серіалізація)
        String jsonString = gson.toJson(originalPerson);
        System.out.println("JSON представлення: " + jsonString);

        // c. Конвертуємо JSON-рядок назад у новий об'єкт Person (Десеріалізація)
        Person deserializedPerson = gson.fromJson(jsonString, Person.class);
        System.out.println("Відновлений об'єкт: " + deserializedPerson);

        // d. Перевіряємо equals-ом початковий і одержаний об'єкти
        boolean isEqual = originalPerson.equals(deserializedPerson);

        System.out.println("\nЧи рівні об'єкти (originalPerson.equals(deserializedPerson))? -> " + isEqual);

        // Додаткова перевірка посилань (чи це один і той самий об'єкт у пам'яті?)
        boolean isSameReference = (originalPerson == deserializedPerson);
        System.out.println("Чи це один і той самий об'єкт у пам'яті (==)? -> " + isSameReference);
    }
}