package com.car_manaagemet_feul_tracking.car_magement_fuel_tracking.dto;

import com.car_manaagemet_feul_tracking.car_magement_fuel_tracking.model.FuelEntry;

import java.util.List;

public class CreateCarRequest {
    public String brand;
    public String model;
    public int year;
    private List<FuelEntry> fuelEntries;

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

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public void setFuelEntries(List<FuelEntry> fuelEntries) {
        this.fuelEntries = fuelEntries;
    }
}
