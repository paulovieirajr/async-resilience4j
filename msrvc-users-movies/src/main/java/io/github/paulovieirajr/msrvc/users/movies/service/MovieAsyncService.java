package io.github.paulovieirajr.msrvc.users.movies.service;

import io.github.paulovieirajr.msrvc.movies.rating.model.MovieResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;

@Service
public class MovieAsyncService {

    private static final Logger LOGGER = LoggerFactory.getLogger(MovieAsyncService.class);

    private final MovieFeignService movieFeignService;

    public MovieAsyncService(MovieFeignService movieFeignService) {
        this.movieFeignService = movieFeignService;
    }

    @Async
    public CompletableFuture<MovieResponse> getMoviesForUser(Long userId) {
        LOGGER.info("[MOVIES] Async search initiated for userId: {}", userId);
        return CompletableFuture.supplyAsync(() -> movieFeignService.getMoviesForUser(userId));
    }
}
