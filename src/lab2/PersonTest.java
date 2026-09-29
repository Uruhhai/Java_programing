package lab2;

import nl.jqno.equalsverifier.EqualsVerifier;
import org.junit.jupiter.api.Test;

public class PersonTest {

    @Test
    public void testEqualsAndHashCodeContract() {
        // EqualsVerifier автоматично тестує всі строгі правила методу equals:
        // рефлексивність, симетричність, транзитивність, узгодженість і перевірку на null.
        EqualsVerifier.simple()
                .forClass(Person.class)
                .verify();

        System.out.println("Тест EqualsVerifier пройдено успішно!");
    }
}