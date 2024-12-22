package io.github.paulovieirajr.msrvc.users.movies.infra.client;

import io.github.paulovieirajr.msrvc.movies.rating.model.MovieResponse;

import java.util.List;

public class MovieFallback implements MovieClient {

    @Override
    public MovieResponse getMoviesForUser(Long userId) {
        return new MovieResponse(true, List.of());
    }
}
