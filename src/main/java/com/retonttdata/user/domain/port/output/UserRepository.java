package com.retonttdata.user.domain.port.output;

import com.retonttdata.user.domain.model.User;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface UserRepository {
    Mono<User> save(User user);
    Mono<User> findByEmail(String email);
    Flux<User> findAll();
    Mono<Boolean> existsByEmail(String email);
}