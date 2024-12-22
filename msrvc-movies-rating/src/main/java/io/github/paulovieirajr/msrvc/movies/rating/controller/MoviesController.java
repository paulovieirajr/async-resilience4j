package io.github.paulovieirajr.msrvc.movies.rating.controller;

import io.github.paulovieirajr.msrvc.movies.rating.model.MovieResponse;
import io.github.paulovieirajr.msrvc.movies.rating.repository.UserMoviesRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MoviesController {

    private final UserMoviesRepository userMoviesRepository;

    public MoviesController(UserMoviesRepository userMoviesRepository) {
        this.userMoviesRepository = userMoviesRepository;
    }

    @GetMapping("/movies")
    public ResponseEntity<MovieResponse> getMoviesForUser(@RequestParam Long userId) {
        var userMovies = userMoviesRepository.getUserMovies(userId);
        if (userMovies == null || userMovies.collection().isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(userMovies);
    }
}
