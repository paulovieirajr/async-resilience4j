package io.github.paulovieirajr.msrvc.users.movies.infra.client;

import io.github.paulovieirajr.msrvc.movies.rating.model.MovieResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "msrvc-movies", url = "${msrvc.movies.url}")
public interface MovieClient {

    @GetMapping("/movies")
    MovieResponse getMoviesForUser(
            @RequestParam Long userId
    );
}
