package com.retonttdata.user.application;

import com.retonttdata.user.domain.model.User;
import com.retonttdata.user.domain.model.exception.UserAlreadyExistsException;
import com.retonttdata.user.domain.port.input.UserRegistrationUseCase;
import com.retonttdata.user.domain.port.output.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class UserRegistrationService implements UserRegistrationUseCase {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserRegistrationService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public Mono<User> registerUser(User user) {
        return userRepository.existsByEmail(user.getEmail())
                .flatMap(exists -> {
                    if (exists) {
                        return Mono.error(new UserAlreadyExistsException("Email already registered"));
                    }
                    return Mono.just(User.builder()
                                    .fullName(user.getFullName())
                                    .email(user.getEmail())
                                    .password(passwordEncoder.encode(user.getPassword()))
                                    .phone(user.getPhone())
                                    .build())
                            .flatMap(userRepository::save);
                });
    }

    @Override
    public Flux<User> getAllUsers() {
        return userRepository.findAll();
    }
}