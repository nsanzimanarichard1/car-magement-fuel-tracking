package com.car_manaagemet_feul_tracking.car_magement_fuel_tracking.cli;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.HashMap;
import java.util.Map;

public class CarCli {

    private static final String BASE_URL = "http://localhost:8081"; // backend URL
    private static final HttpClient client = HttpClient.newHttpClient();
    private static final ObjectMapper mapper = new ObjectMapper();

    public static void main(String[] args) throws IOException, InterruptedException {
        if (args.length == 0) {
            System.out.println("No command provided. Available commands: create-car, add-fuel, fuel-stats");
            return;
        }

        String command = args[0];

        switch (command) {
            case "create-car" -> handleCreateCar(args);
            case "add-fuel" -> handleAddFuel(args);
            case "fuel-stats" -> handleFuelStats(args);
            default -> System.out.println("Unknown command: " + command);
        }
    }

    // ===================== CREATE CAR =====================
    private static void handleCreateCar(String[] args) throws IOException, InterruptedException {
        Map<String, String> params = parseArgs(args);

        String brand = params.get("brand");
        String model = params.get("model");
        String year = params.get("year");

        if (brand == null || model == null || year == null) {
            System.out.println("Usage: create-car --brand Toyota --model Corolla --year 2018");
            return;
        }

        String json = String.format("{\"brand\":\"%s\",\"model\":\"%s\",\"year\":%s}", brand, model, year);

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(BASE_URL + "/api/cars"))
                .header("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(json))
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        if (response.statusCode() == 200 || response.statusCode() == 201) {
            System.out.println("Car created successfully: " + response.body());
        } else {
            System.out.println("Failed to create car: " + response.body());
        }
    }

    // ===================== ADD FUEL =====================
    private static void handleAddFuel(String[] args) throws IOException, InterruptedException {
        Map<String, String> params = parseArgs(args);

        String carId = params.get("carId");
        String liters = params.get("liters");
        String price = params.get("price");
        String odometer = params.get("odometer");

        if (carId == null || liters == null || price == null || odometer == null) {
            System.out.println("Usage: add-fuel --carId 1 --liters 40 --price 52.5 --odometer 45000");
            return;
        }

        String json = String.format("{\"liters\":%s,\"price\":%s,\"odometer\":%s}", liters, price, odometer);

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(BASE_URL + "/api/cars/" + carId + "/fuel"))
                .header("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(json))
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        if (response.statusCode() == 200 || response.statusCode() == 201) {
            System.out.println("Fuel entry added successfully: " + response.body());
        } else {
            System.out.println("Failed to add fuel: " + response.body());
        }
    }

    // ===================== FUEL STATS =====================
    private static void handleFuelStats(String[] args) throws IOException, InterruptedException {
        Map<String, String> params = parseArgs(args);
        String carId = params.get("carId");

        if (carId == null) {
            System.out.println("Usage: fuel-stats --carId 1");
            return;
        }

        // FIXED endpoint URL
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(BASE_URL + "/api/cars/" + carId + "/stats"))
                .GET()
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        if (response.statusCode() == 200) {
            JsonNode node = mapper.readTree(response.body());
            System.out.println("Total fuel: " + node.get("totalFuel").asDouble() + " L");
            System.out.println("Total cost: " + node.get("totalCost").asDouble());
            System.out.println("Average consumption: " + node.get("averageConsumption").asDouble() + " L/100km");
        } else {
            System.out.println("Failed to fetch stats: " + response.body());
        }
    }

    // ===================== UTILITY: parse CLI args =====================
    private static Map<String, String> parseArgs(String[] args) {
        Map<String, String> map = new HashMap<>();
        for (int i = 1; i < args.length - 1; i += 2) {
            String key = args[i].replace("--", "");
            String value = args[i + 1];
            map.put(key, value);
        }
        return map;
    }
}
