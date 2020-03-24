package nl.op.service.impl;

import nl.op.domain.User;
import nl.op.repository.UserRepository;
import nl.op.service.UserService;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.UUID;

@Component
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    //small hack to emulate user registration. id generates on FE
    @Override
    public Optional<User> findById(UUID userId) {
        Optional<User> user = userRepository.findById(userId);
        if (user.isPresent()){
            return user;
        } else {
            return Optional.of(save(userId));
        }
    }

    @Override
    public User save(UUID userId) {
        return userRepository.save(new User(userId));
    }
}
