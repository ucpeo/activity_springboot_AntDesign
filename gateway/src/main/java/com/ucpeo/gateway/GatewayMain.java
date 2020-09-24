package com.ucpeo.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableDiscoveryClient
public class GatewayMain {
    @Bean
    public RouteLocator customRouteLocator(RouteLocatorBuilder builder) {
        return builder.routes()
                .route("path_route", r -> r.header("content-type","application/json")
                        .uri("lb://activity-service"))
                .route("default",r->r.order(999).alwaysTrue().uri("lb://activity-web"))
                .build();
    }

    public static void main(String[] args) {
        SpringApplication.run(GatewayMain.class,args);
    }
}
