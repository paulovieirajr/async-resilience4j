package io.github.paulovieirajr.msrvc.users.movies.service;

import io.github.paulovieirajr.msrvc.movies.rating.model.Movie;
import io.github.paulovieirajr.msrvc.users.movies.dto.UserResponse;
import io.github.paulovieirajr.msrvc.users.movies.infra.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserMovieService {

    private final static Logger LOGGER = LoggerFactory.getLogger(UserMovieService.class.getName());

    private final MovieFeignService movieFeignService;
    private final UserRepository userRepository;

    public UserMovieService(MovieFeignService movieFeignService, UserRepository userRepository) {
        this.movieFeignService = movieFeignService;
        this.userRepository = userRepository;
    }

    public UserResponse getUserMovies(Long userId) {
        var user = userRepository.findById(userId);
        if (user == null) {
            LOGGER.error("[USERS] User not found: {}", userId);
            return null;
        }

        // Chama o serviço de filmes e aguarda o resultado
        List<Movie> movies = movieFeignService.getMoviesForUser(userId);

        // Retorna a resposta do usuário com os filmes
        return UserResponse.fromUser(user, movies);
    }
}
