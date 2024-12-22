package io.github.paulovieirajr.msrvc.movies.rating.model;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
public record MovieResponse(
        Boolean hasFailed,
        List<Movie> collection
) {
}
