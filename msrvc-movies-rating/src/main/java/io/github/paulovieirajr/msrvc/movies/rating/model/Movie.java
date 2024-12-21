package io.github.paulovieirajr.msrvc.movies.rating.model;

public record Movie(
        Long id,
        String name,
        String genre,
        Integer rating
) {
}
