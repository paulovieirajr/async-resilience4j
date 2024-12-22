package io.github.paulovieirajr.msrvc.users.movies.config;

import feign.FeignException;
import feign.RetryableException;
import io.github.resilience4j.retry.Retry;
import io.github.resilience4j.retry.RetryConfig;
import io.github.resilience4j.retry.RetryRegistry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;

import java.net.ConnectException;
import java.time.Duration;

@Configuration
public class Resilience4jRetryConfig {

    @Autowired
    private RetryRegistry retryRegistry;

    @Bean
    public Retry retryWithCustomConfig() {
        RetryConfig customConfig = RetryConfig.custom()
                .maxAttempts(3)
                .waitDuration(Duration.ofSeconds(2))
                .retryExceptions(FeignException.FeignServerException.class, ConnectException.class, RetryableException.class)
                .ignoreExceptions(FeignException.FeignClientException.class)
                .build();

        return retryRegistry.retry("customRetryConfig", customConfig);
    }

}
