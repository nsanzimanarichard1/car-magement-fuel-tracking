package com.car_manaagemet_feul_tracking.car_magement_fuel_tracking.services;

import com.car_manaagemet_feul_tracking.car_magement_fuel_tracking.exception.NotFoundException;
import com.car_manaagemet_feul_tracking.car_magement_fuel_tracking.model.Car;
import com.car_manaagemet_feul_tracking.car_magement_fuel_tracking.model.FuelEntry;
import org.springframework.stereotype.Service;
import com.car_manaagemet_feul_tracking.car_magement_fuel_tracking.repository.CarRepository;

import java.util.List;

/**
 * Core business service for Car operations.
 */
@Service
public class CarService {

    private final CarRepository repository;

    public CarService(CarRepository repository) {
        this.repository = repository;
    }

    public Car createCar(String brand, String model, int year, List<FuelEntry> fuelEntries) {
        return repository.save(brand, model, year, fuelEntries);
    }

    public List<Car> getAllCars() {
        return repository.findAll();
    }

    public Car getCarById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new NotFoundException("Car not found: " + id));
    }

    public Car addFuel(Long carId, FuelEntry entry) {
        Car car = getCarById(carId);
        car.getFuelEntries().add(entry);
        return car;
    }

}