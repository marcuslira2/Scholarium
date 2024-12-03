package com.scholarium.gateway.config;

import com.scholarium.gateway.filter.AuthenticationFilter;
import com.scholarium.gateway.filter.RouteValidator;
import com.scholarium.gateway.util.JwtUtil;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;


@Configuration
public class AuthConfig {

    private final RouteValidator validator;

    private final JwtUtil jwtUtil;

    public AuthConfig(RouteValidator validator, JwtUtil jwtUtil) {
        this.validator = validator;
        this.jwtUtil = jwtUtil;
    }

    @Bean
    public RestTemplate template(){
        return new RestTemplate();
    }


    @Bean
    public RouteLocator customRouteLocator(RouteLocatorBuilder builder) {
        return builder.routes()
                .route("profile-service", r -> r.path("/profile/**")
                        .filters(f -> f.filter(new AuthenticationFilter(validator,jwtUtil).apply(new AuthenticationFilter.Config())))
                        .uri("lb://PROFILE-SERVICE"))
                .route("authenticate-service", r -> r.path("/auth/**")
                        .uri("lb://AUTHENTICATE-SERVICE"))
                .build();
    }

}
