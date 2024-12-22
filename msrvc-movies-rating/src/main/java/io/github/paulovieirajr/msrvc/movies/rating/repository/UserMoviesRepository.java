package io.github.paulovieirajr.msrvc.movies.rating.repository;

import io.github.paulovieirajr.msrvc.movies.rating.model.Movie;
import io.github.paulovieirajr.msrvc.movies.rating.model.MovieResponse;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class UserMoviesRepository {

    private final Map<Long, List<Movie>> userMovies = new HashMap<>();

    public UserMoviesRepository() {
        generateUserMovies();
    }

    public MovieResponse getUserMovies(Long userId) {
        return new MovieResponse(null, userMovies.get(userId));
    }


    private void generateUserMovies() {
        userMovies.put(1L, List.of(
                new Movie(1L, "Harry Potter and the Philosopher's Stone", "Adventure", 7),
                new Movie(3L, "The Ghost Rider", "Action", 5),
                new Movie(5L, "Chucky", "Horror", 4)
        ));
        userMovies.put(2L, List.of(
                new Movie(2L, "Wolf of Wall Street", "Biography", 8),
                new Movie(5L, "Chucky", "Horror", 3),
                new Movie(6L, "The Lord of the Rings: The Return of the King", "Adventure", 6)
        ));
        userMovies.put(3L, List.of(
                new Movie(1L, "Harry Potter and the Philosopher's Stone", "Adventure", 9),
                new Movie(4L, "The Matrix", "Action", 9),
                new Movie(7L, "The Shining", "Horror", 8)
        ));
    }
}
