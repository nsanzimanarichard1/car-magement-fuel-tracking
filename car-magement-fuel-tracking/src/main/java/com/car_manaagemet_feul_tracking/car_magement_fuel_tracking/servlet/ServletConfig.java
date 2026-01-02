package com.car_manaagemet_feul_tracking.car_magement_fuel_tracking.servlet;


import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ServletConfig {

    @Bean
    public ServletRegistrationBean<FuelStatsServlet> fuelStatsServlet() {
        return new ServletRegistrationBean<>(
                new FuelStatsServlet(),
                "/servlet/fuel-stats"
        );
    }
}
