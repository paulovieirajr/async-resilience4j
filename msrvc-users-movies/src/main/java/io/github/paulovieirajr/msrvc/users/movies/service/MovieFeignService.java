package io.github.paulovieirajr.msrvc.users.movies.service;

import io.github.paulovieirajr.msrvc.movies.rating.model.Movie;
import io.github.paulovieirajr.msrvc.users.movies.infra.client.MovieClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MovieFeignService {

    private static final Logger LOGGER = LoggerFactory.getLogger(MovieFeignService.class);

    private final MovieClient movieClient;

    public MovieFeignService(MovieClient movieClient) {
        this.movieClient = movieClient;
    }

    public List<Movie> getMoviesForUser(Long userId) {
        LOGGER.info("[MOVIES] Searching movies for user: {}", userId);
        try {
            return movieClient.getMoviesForUser(userId);
        } catch (Exception e) {
            LOGGER.error("[MOVIES] Error searching movies for user: {}", userId, e);
            return List.of();
        }
    }
}
