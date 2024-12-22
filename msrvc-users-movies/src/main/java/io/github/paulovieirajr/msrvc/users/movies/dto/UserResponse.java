package io.github.paulovieirajr.msrvc.users.movies.dto;

import io.github.paulovieirajr.msrvc.movies.rating.model.MovieResponse;
import io.github.paulovieirajr.msrvc.users.movies.domain.User;

public record UserResponse(
        String name,
        String username,
        String email,
        String phone,
        MovieResponse movies
) {

    public static UserResponse fromUser(User user, MovieResponse movies) {
        return new UserResponse(user.getName(), user.getUsername(), user.getEmail(), user.getPhone(), movies);
    }
}
