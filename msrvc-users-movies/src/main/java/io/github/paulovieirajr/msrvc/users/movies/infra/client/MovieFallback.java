package io.github.paulovieirajr.msrvc.users.movies.infra.client;

import io.github.paulovieirajr.msrvc.movies.rating.model.Movie;

import java.util.List;

public class MovieFallback implements MovieClient {

    @Override
    public List<Movie> getMoviesForUser(Long userId) {
        return List.of();
    }
}
