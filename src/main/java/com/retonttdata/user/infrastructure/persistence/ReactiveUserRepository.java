package com.retonttdata.user.infrastructure.persistence;

import com.retonttdata.user.infrastructure.persistence.entity.UserEntity;
import org.springframework.data.r2dbc.repository.R2dbcRepository;
import reactor.core.publisher.Mono;

public interface ReactiveUserRepository extends R2dbcRepository<UserEntity, Long> {
    Mono<UserEntity> findByEmail(String email);
    Mono<Boolean> existsByEmail(String email);
}