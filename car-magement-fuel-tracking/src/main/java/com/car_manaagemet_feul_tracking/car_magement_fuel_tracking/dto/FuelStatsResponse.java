package com.car_manaagemet_feul_tracking.car_magement_fuel_tracking.dto;

public class FuelStatsResponse {

    public double totalFuel;
    public double totalCost;
    public double averageConsumption;

    public FuelStatsResponse(double totalFuel, double totalCost, double averageConsumption) {
        this.totalFuel = totalFuel;
        this.totalCost = totalCost;
        this.averageConsumption = averageConsumption;
    }
}
