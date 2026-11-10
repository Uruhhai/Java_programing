package lab4;

import java.util.ArrayList;
import java.util.List;

public class Road {
    // Використання універсального списку транспортних засобів за допомогою Wildcard
    private List<Vehicle<?>> carsInRoad = new ArrayList<>();

    public void addCarToRoad(Vehicle<?> vehicle) {
        carsInRoad.add(vehicle);
    }

    public int getCountOfHumans() {
        int totalHumans = 0;
        for (Vehicle<?> vehicle : carsInRoad) {
            totalHumans += vehicle.getOccupiedCapacity();
        }
        return totalHumans;
    }

    public List<Vehicle<?>> getCarsInRoad() {
        return carsInRoad;
    }
}