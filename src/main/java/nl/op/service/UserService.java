package nl.op.service;

import nl.op.domain.User;

import java.util.Optional;
import java.util.UUID;

public interface UserService {
    Optional<User> findById(UUID user);
    User save(UUID userId);
}
