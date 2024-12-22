package io.github.paulovieirajr.msrvc.users.movies.dto;

import io.github.paulovieirajr.msrvc.movies.rating.model.Movie;
import io.github.paulovieirajr.msrvc.users.movies.domain.User;

import java.util.List;

public record UserResponse(
        String name,
        String username,
        String email,
        String phone,
        List<Movie> movies
) {

    public static UserResponse fromUser(User user, List<Movie> movies) {
        return new UserResponse(user.getName(), user.getUsername(), user.getEmail(), user.getPhone(), movies);
    }
}
