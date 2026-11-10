package lab4;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class VehicleRoadTest {

    @Test
    public void testBusCapacityAndBoardingException() {
        Bus bus = new Bus(2);
        RegularPassenger p1 = new RegularPassenger("Олександр");
        FireFighter p2 = new FireFighter("Андрій");

        bus.board(p1);
        bus.board(p2);
        assertEquals(2, bus.getOccupiedCapacity());

        // Перевірка перевищення ліміту місць (має кинути IllegalStateException)
        RegularPassenger p3 = new RegularPassenger("Олена");
        assertThrows(IllegalStateException.class, () -> {
            bus.board(p3);
        });
    }

    @Test
    public void testFireEngineTypeSafety() {
        FireEngine fireEngine = new FireEngine(2);
        FireFighter firefighter = new FireFighter("Іван");

        fireEngine.board(firefighter);
        assertEquals(1, fireEngine.getOccupiedCapacity());

        // Компілятор Java на етапі компіляції заблокує спробу додати сюди звичайну людину чи поліцейського:
        // fireEngine.board(new RegularPassenger("Петро")); // -> Помилка компіляції, завдяки Generics.
    }

    @Test
    public void testUnboardException() {
        Taxi taxi = new Taxi(3);
        RegularPassenger passenger = new RegularPassenger("Марія");

        // Спроба висадити пасажира, якого не було в таксі (має кинути IllegalArgumentException)
        assertThrows(IllegalArgumentException.class, () -> {
            taxi.unboard(passenger);
        });
    }

    @Test
    public void testRoadHumanCounting() {
        Road road = new Road();

        Bus bus = new Bus(10);
        bus.board(new RegularPassenger("Олег"));
        bus.board(new FireFighter("Михайло"));

        PoliceCar policeCar = new PoliceCar(2);
        policeCar.board(new PoliceOfficer("Копчік Сергій"));

        road.addCarToRoad(bus);
        road.addCarToRoad(policeCar);

        // В автобусі 2 пасажири, в поліцейській машині 1. Разом на дорозі 3 людини.
        assertEquals(3, road.getCountOfHumans());
    }
}