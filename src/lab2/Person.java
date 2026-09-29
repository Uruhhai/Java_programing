package lab2;

import java.util.Objects;

public class Person {
    private String firstName;
    private String lastName;
    private int age;

    // Конструктор за замовчуванням (бажано залишати для бібліотек серіалізації)
    public Person() {
    }

    // Конструктор з параметрами
    public Person(String firstName, String lastName, int age) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
    }

    // Гетери та сетери
    public String getFirstName() { return firstName; }
    public void setFirstName(String firstName) { this.firstName = firstName; }

    public String getLastName() { return lastName; }
    public void setLastName(String lastName) { this.lastName = lastName; }

    public int getAge() { return age; }
    public void setAge(int age) { this.age = age; }

    /**
     * Крок 1 завдання: Реалізація методу equals.
     */
    @Override
    public boolean equals(Object o) {
        // 1. Перевірка на те, чи не порівнюємо ми об'єкт сам із собою (посилання на ту ж область пам'яті)
        if (this == o) return true;

        // 2. Перевірка на null та порівняння класів
        if (o == null || getClass() != o.getClass()) return false;

        // 3. Приведення типу (cast) Object до Person
        Person person = (Person) o;

        // 4. Порівняння значень полів. Для примітивів (age) використовуємо ==,
        // для об'єктів (String) використовуємо клас Objects (він безпечний до null-значень)
        return age == person.age &&
                Objects.equals(firstName, person.firstName) &&
                Objects.equals(lastName, person.lastName);
    }

    /**
     * Обов'язково перевизначаємо hashCode, якщо перевизначили equals.
     */
    @Override
    public int hashCode() {
        return Objects.hash(firstName, lastName, age);
    }

    // Перевизначаємо toString для зручного виводу об'єкта в консоль
    @Override
    public String toString() {
        return "Person{firstName='" + firstName + "', lastName='" + lastName + "', age=" + age + '}';
    }
}
