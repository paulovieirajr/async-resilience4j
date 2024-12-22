package io.github.paulovieirajr.msrvc.users.movies.infra.controller;

import io.github.paulovieirajr.msrvc.users.movies.dto.UserResponse;
import io.github.paulovieirajr.msrvc.users.movies.service.UserMovieService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserMoviesController {

    private final static Logger LOGGER = LoggerFactory.getLogger(UserMoviesController.class.getName());

    private final UserMovieService userMovieService;

    public UserMoviesController(UserMovieService userMovieService) {
        this.userMovieService = userMovieService;
    }

    @GetMapping("/search")
    public ResponseEntity<UserResponse> getUserMovies(@RequestParam Long userId) {
        LOGGER.info("[USERS] Endpoint /users/movies called for user: {}", userId);
        var userMovies = userMovieService.getUserMovies(userId);
        if (userMovies == null) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(userMovies);
    }
}
