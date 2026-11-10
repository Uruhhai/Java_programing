package lab4;

import java.util.ArrayList;
import java.util.List;

public abstract class Vehicle<T extends Human> {
    private int maxCapacity;
    private List<T> passengers;

    public Vehicle(int maxCapacity) {
        this.maxCapacity = maxCapacity;
        this.passengers = new ArrayList<>();
    }

    public int getMaxCapacity() {
        return maxCapacity;
    }

    public int getOccupiedCapacity() {
        return passengers.size();
    }

    public List<T> getPassengers() {
        return passengers;
    }

    // Посадка пасажира (генерує виняток, якщо місць немає)
    public void board(T passenger) {
        if (passengers.size() >= maxCapacity) {
            throw new IllegalStateException("Помилка: транспортний засіб заповнений! Неможливо додати пасажира.");
        }
        passengers.add(passenger);
    }

    // Висадка пасажира (генерує виняток, якщо пасажира немає в транспорті)
    public void unboard(T passenger) {
        if (!passengers.remove(passenger)) {
            throw new IllegalArgumentException("Помилка: вказаний пасажир «не сидить» у цьому транспортному засобі.");
        }
    }
}