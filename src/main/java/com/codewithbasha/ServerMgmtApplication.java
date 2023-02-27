package com.codewithbasha;

import com.codewithbasha.entities.Server;
import com.codewithbasha.repos.ServerRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import java.util.Arrays;
import java.util.List;

import static com.codewithbasha.enumeration.Status.SERVER_DOWN;
import static com.codewithbasha.enumeration.Status.SERVER_UP;

@SpringBootApplication
public class ServerMgmtApplication {

    public static void main(String[] args) {
        SpringApplication.run(ServerMgmtApplication.class, args);
    }

    @Bean
    CommandLineRunner run(ServerRepository serverRepository) {
        return args -> {
            serverRepository.save(new Server(null, "192.168.1.11", "Ubantu Linux", "16 GB", "personal PC", "http://localhost:8080/server/image/Server1.png", SERVER_UP));
            serverRepository.save(new Server(null, "192.168.1.12", "Fedore Linux", "16 GB", "Dell Tower", "http://localhost:8080/server/image/Server2.png", SERVER_DOWN));
            serverRepository.save(new Server(null, "192.168.1.13", "MS 2008", "32 GB", "Web Server", "http://localhost:8080/server/image/Server3.png", SERVER_UP));
            serverRepository.save(new Server(null, "192.168.1.10", "Red Hat Enterprise Linux", "64 GB", "Mail Server", "http://localhost:8080/server/image/Server4.png", SERVER_DOWN));
        };
    }

    @Bean
    public CorsFilter corsFilter() {
        UrlBasedCorsConfigurationSource urlBasedCorsConfigurationSource = new UrlBasedCorsConfigurationSource();
        CorsConfiguration corsConfiguration = new CorsConfiguration();
        corsConfiguration.setAllowCredentials(true);
        corsConfiguration.setAllowedOrigins(List.of("http://localhost:3000\",\"http://localhost:4200"));
        corsConfiguration.setAllowedHeaders(Arrays.asList("Origin", "Access-Control-Allow-Origin", "Content-Type", "Accept", "Jwt-Token", "Authorization", "Origin", "Accept", "X-Requested-With", "Access-Control-Request-Method", "Access-Control-Request-Header"));
        corsConfiguration.setExposedHeaders(Arrays.asList("Origin", "Content-Type", "Accept", "Jwt-Token", "Authorization", "Access-Control-Allow-Origin", "Access-Control-Allow-Credentials", "Filename"));
        corsConfiguration.setAllowedMethods(Arrays.asList("GET", "DELETE", "POST", "PUT", "PATCH", "OPTIONS"));
        urlBasedCorsConfigurationSource.registerCorsConfiguration("/**", corsConfiguration);
        return new CorsFilter(urlBasedCorsConfigurationSource);
    }
}





