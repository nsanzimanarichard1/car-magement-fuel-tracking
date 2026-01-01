package com.car_manaagemet_feul_tracking.car_magement_fuel_tracking.controller;


import com.car_manaagemet_feul_tracking.car_magement_fuel_tracking.dto.CreateCarRequest;
import com.car_manaagemet_feul_tracking.car_magement_fuel_tracking.dto.FuelEntryRequest;
import com.car_manaagemet_feul_tracking.car_magement_fuel_tracking.dto.FuelStatsResponse;
import com.car_manaagemet_feul_tracking.car_magement_fuel_tracking.model.Car;
import com.car_manaagemet_feul_tracking.car_magement_fuel_tracking.model.FuelEntry;
import com.car_manaagemet_feul_tracking.car_magement_fuel_tracking.services.CarService;
import com.car_manaagemet_feul_tracking.car_magement_fuel_tracking.services.FuelStatisticsService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/cars")
public class CarController {

    private final CarService carService;
    private final FuelStatisticsService statsService;

    public CarController(CarService carService, FuelStatisticsService statsService) {
        this.carService = carService;
        this.statsService = statsService;
    }

    // Create car
    @PostMapping
    public ResponseEntity<Car> createCar(@RequestBody CreateCarRequest request) {
        Car car = carService.createCar(request.brand, request.model, request.year, request.getFuelEntries());
        return ResponseEntity.status(HttpStatus.CREATED).body(car);
    }

    // List cars
    @GetMapping
    public List<Car> getAllCars() {
        return carService.getAllCars();
    }

    // Add fuel
    @PostMapping("/{id}/fuel")
    public ResponseEntity<Car> addFuel(
            @PathVariable Long id,
            @RequestBody FuelEntryRequest request
    ) {
        return ResponseEntity.status(HttpStatus.CREATED).body(
        carService.addFuel(
                id,
                new FuelEntry(request.liters, request.price, request.odometer)
        ));

    }

    // Get fuel stats
    @GetMapping("/{id}/fuel/stats")
    public FuelStatsResponse getStats(@PathVariable Long id) {
        Car car = carService.getCarById(id);
        return statsService.calculate(car);
    }
}
