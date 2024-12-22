package io.github.paulovieirajr.msrvc.users.movies.infra.repository;

import io.github.paulovieirajr.msrvc.users.movies.domain.User;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class UserRepository {

    private Map<Long, User> users = Map.of(
            1L, new User(1L, "John Doe", "johndoe", "jonhdoe@gmail.com", "444-232-1234"),
            2L, new User(2L, "Jane Smith", "janesmith", "janesmith@gmail.com", "442-321-9921"),
            3L, new User(3L, "Alice Johnson", "alicejohnson", "ajohnson@hotmail.com", "123-456-7890")
    );

    public User findById(Long id) {
        return users.get(id);
    }
}
