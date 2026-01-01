//package com.car_manaagemet_feul_tracking.car_magement_fuel_tracking.servlet;
//
//import com.car_manaagemet_feul_tracking.car_magement_fuel_tracking.dto.FuelStatsResponse;
//import com.car_manaagemet_feul_tracking.car_magement_fuel_tracking.model.Car;
//import com.car_manaagemet_feul_tracking.car_magement_fuel_tracking.services.CarService;
//import com.car_manaagemet_feul_tracking.car_magement_fuel_tracking.services.FuelStatisticsService;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import jakarta.servlet.http.HttpServlet;
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpServletResponse;
//import org.springframework.web.context.support.WebApplicationContextUtils;
//
//import java.io.IOException;
//
//public class FuelStatsServlet extends HttpServlet {
//
//    private CarService carService;
//    private FuelStatisticsService statsService;
//
//    @Override
//    public void init() {
//        var ctx = WebApplicationContextUtils
//                .getWebApplicationContext(getServletContext());
//        carService = ctx.getBean(CarService.class);
//        statsService = ctx.getBean(FuelStatisticsService.class);
//    }
//
//    @Override
//    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
//            throws IOException {
//
//        resp.setContentType("application/json");
//
//        String carIdParam = req.getParameter("carId");
//        if (carIdParam == null) {
//            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
//            resp.getWriter().write("{\"error\":\"Missing carId\"}");
//            return;
//        }
//
//        try {
//            Long carId = Long.parseLong(carIdParam);
//            Car car = carService.getCarById(carId);
//            FuelStatsResponse stats = statsService.calculate(car);
//
//            resp.setStatus(HttpServletResponse.SC_OK);
//            new ObjectMapper().writeValue(resp.getWriter(), stats);
//
//        } catch (Exception e) {
//            resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
//            resp.getWriter().write("{\"error\":\"Car not found\"}");
//        }
//    }
//}
