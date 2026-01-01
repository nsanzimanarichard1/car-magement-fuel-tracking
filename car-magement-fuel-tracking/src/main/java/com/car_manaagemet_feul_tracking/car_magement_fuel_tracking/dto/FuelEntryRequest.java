package com.car_manaagemet_feul_tracking.car_magement_fuel_tracking.dto;

public class FuelEntryRequest {
    public double liters;
    public double price;
    public int odometer;

    public double getLiters() {
        return liters;
    }

    public double getPrice() {
        return price;
    }

    public int getOdometer() {
        return odometer;
    }

    public void setLiters(double liters) {
        this.liters = liters;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setOdometer(int odometer) {
        this.odometer = odometer;
    }
}
