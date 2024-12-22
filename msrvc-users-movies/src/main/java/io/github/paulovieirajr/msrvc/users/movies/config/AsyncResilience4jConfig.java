package io.github.paulovieirajr.msrvc.users.movies.config;

import feign.Retryer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AsyncResilience4jConfig {

    @Bean
    public Retryer retryer() {
        return new Retryer.Default(1000, 5000, 3);
    }
}
