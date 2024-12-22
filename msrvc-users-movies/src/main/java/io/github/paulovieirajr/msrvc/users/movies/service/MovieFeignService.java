package io.github.paulovieirajr.msrvc.users.movies.service;

import io.github.paulovieirajr.msrvc.movies.rating.model.MovieResponse;
import io.github.paulovieirajr.msrvc.users.movies.infra.client.MovieClient;
import io.github.resilience4j.circuitbreaker.CallNotPermittedException;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MovieFeignService {

    private static final Logger LOGGER = LoggerFactory.getLogger(MovieFeignService.class);
    private static int ATTEMPTS = 1;

    private final MovieClient movieClient;

    public MovieFeignService(MovieClient movieClient) {
        this.movieClient = movieClient;
    }

    @Retry(name = "movieServiceRetry")
    @CircuitBreaker(name = "movieServiceCb", fallbackMethod = "getMoviesForUserFallback")
    public MovieResponse getMoviesForUser(Long userId) {
        LOGGER.info("[MOVIES] Searching movies for userId: {}. Attempt: {}", userId, ATTEMPTS++);
        return movieClient.getMoviesForUser(userId);
    }

    public MovieResponse getMoviesForUserFallback(Long userId, Throwable e) {
        ATTEMPTS = 1;
        if (e instanceof CallNotPermittedException) {
            LOGGER.warn("[MOVIES] Circuit Breaker is OPEN. Returning fallback response for userId: {}", userId);
        } else {
            LOGGER.error("[MOVIES] Service has failed. Fallback method called for userId: {}", userId, e);
        }

        LOGGER.error("[MOVIES] Movie service is unavailable. Returning an empty list of movies.");
        return new MovieResponse(true, List.of());
    }

}
