package io.github.paulovieirajr.msrvc.users.movies.service;

import io.github.paulovieirajr.msrvc.movies.rating.model.MovieResponse;
import io.github.paulovieirajr.msrvc.users.movies.dto.UserResponse;
import io.github.paulovieirajr.msrvc.users.movies.infra.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class UserMovieService {

    private final static Logger LOGGER = LoggerFactory.getLogger(UserMovieService.class.getName());

    private final MovieAsyncService movieAsyncService;
    private final UserRepository userRepository;

    public UserMovieService(MovieAsyncService movieAsyncService, UserRepository userRepository) {
        this.movieAsyncService = movieAsyncService;
        this.userRepository = userRepository;
    }

    public UserResponse getUserMovies(Long userId) {
        var user = userRepository.findById(userId);
        if (user == null) {
            LOGGER.error("[USERS] User not found on the database with id: {}", userId);
            return null;
        }

        MovieResponse movies = movieAsyncService.getMoviesForUser(userId).join();
        return UserResponse.fromUser(user, movies);
    }
}
