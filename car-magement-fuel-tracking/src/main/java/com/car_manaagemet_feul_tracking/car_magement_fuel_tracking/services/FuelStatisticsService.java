package com.car_manaagemet_feul_tracking.car_magement_fuel_tracking.services;

import com.car_manaagemet_feul_tracking.car_magement_fuel_tracking.dto.FuelStatsResponse;
import com.car_manaagemet_feul_tracking.car_magement_fuel_tracking.model.Car;
import com.car_manaagemet_feul_tracking.car_magement_fuel_tracking.model.FuelEntry;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Service responsible for calculating fuel statistics.
 */
@Service
public class FuelStatisticsService {

    public FuelStatsResponse calculate(Car car) {

        List<FuelEntry> entries = car.getFuelEntries();

        // Not enough data to calculate consumption
        // Business rule: need at least 2 entries
        if (entries.size() < 2) {
            throw new IllegalStateException(
                    "At least two fuel entries are required to calculate statistics"
            );
        }

        double totalFuel = 0;
        double totalCost = 0;

        for (FuelEntry entry : entries) {
            totalFuel += entry.getLiters();
            totalCost += entry.getPrice();
        }

        int distance =
                entries.get(entries.size() - 1).getOdometer()
                        - entries.get(0).getOdometer();

        double averageConsumption = (totalFuel / distance) * 100;

        return new FuelStatsResponse(
                totalFuel,
                totalCost,
                averageConsumption
        );
    }
}
