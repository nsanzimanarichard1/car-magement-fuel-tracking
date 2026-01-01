package com.car_manaagemet_feul_tracking.car_magement_fuel_tracking.model;
import java.util.ArrayList;
import java.util.List;

/**
 * Domain entity representing a Car.
 */
public class Car {

    private final Long id;
    private final String brand;
    private final String model;
    private final int year;
    private  List<FuelEntry> fuelEntries = new ArrayList<>();

    public Long getId() {
        return id;
    }

    public String getBrand() {
        return brand;
    }

    public String getModel() {
        return model;
    }

    public int getYear() {
        return year;
    }

    public List<FuelEntry> getFuelEntries() {
        return fuelEntries;
    }

    public Car(Long id, String brand, String model, int year) {
        this.id = id;
        this.brand = brand;
        this.model = model;
        this.year = year;
    }

    // Second constructor: initialize with existing fuel entries
    public Car(Long id, String brand, String model, int year, List<FuelEntry> fuelEntries) {
        this.id = id;
        this.brand = brand;
        this.model = model;
        this.year = year;
        this.fuelEntries = new ArrayList<>(fuelEntries); // defensive copy
    }


}