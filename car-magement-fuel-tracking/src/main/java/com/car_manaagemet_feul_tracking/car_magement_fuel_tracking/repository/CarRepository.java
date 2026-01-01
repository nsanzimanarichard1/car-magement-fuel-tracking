package com.car_manaagemet_feul_tracking.car_magement_fuel_tracking.repository;
import com.car_manaagemet_feul_tracking.car_magement_fuel_tracking.model.Car;
import com.car_manaagemet_feul_tracking.car_magement_fuel_tracking.model.FuelEntry;
import org.springframework.stereotype.Repository;

import java.util.*;

/**
 * In-memory repository for Cars.
 * Simulates database behavior using Map.
 */
@Repository
public class CarRepository {

    private final Map<Long, Car> storage = new HashMap<>();
    private long idSequence = 1;

    public Car save(String brand, String model, int year, List<FuelEntry> fuelEntries) {

        // BEFORE saving
        System.out.println("========== BEFORE SAVE ==========");
        if (storage.isEmpty()) {
            System.out.println("Storage is EMPTY");
        } else {
            storage.forEach((id, car) ->
                    System.out.println("Car ID=" + id));
        }

        // Log incoming data
        System.out.println("Saving new car:");
        System.out.println("  brand=" + brand);
        System.out.println("  model=" + model);
        System.out.println("  year=" + year);

        // Create and store car
        Car car = new Car(idSequence++, brand, model, year);
        // Add initial fuel entries if any
        if (fuelEntries != null) {
            car.getFuelEntries().addAll(fuelEntries);
        }
        storage.put(car.getId(), car);

        // AFTER saving
        System.out.println("========== AFTER SAVE ==========");
        storage.forEach((id, storedCar) ->
                System.out.println("Car ID=" + id));

        System.out.println("Total cars in storage = " + storage.size());
        System.out.println("================================");

        return car;
    }



    public Optional<Car> findById(Long id) {
        return Optional.ofNullable(storage.get(id));
    }

    public List<Car> findAll() {
        return new ArrayList<>(storage.values());
    }
}