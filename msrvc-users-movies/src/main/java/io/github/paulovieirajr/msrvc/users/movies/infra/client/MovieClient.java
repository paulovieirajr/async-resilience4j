package io.github.paulovieirajr.msrvc.users.movies.infra.client;

import io.github.paulovieirajr.msrvc.movies.rating.model.Movie;
import io.github.resilience4j.retry.annotation.Retry;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(name = "msrvc-movies", url = "${msrvc.movies.url}", fallback = MovieFallback.class)
@Retry(name = "movieService")
public interface MovieClient {

    @GetMapping("/movies")
    List<Movie> getMoviesForUser(
            @RequestParam Long userId
    );
}
