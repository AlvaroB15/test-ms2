package com.retonttdata.user.domain.port.input;

import com.retonttdata.user.domain.model.User;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface UserRegistrationUseCase {
    Mono<User> registerUser(User user);
    Flux<User> getAllUsers();
}