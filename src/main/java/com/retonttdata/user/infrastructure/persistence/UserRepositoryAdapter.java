package com.retonttdata.user.infrastructure.persistence;

import com.retonttdata.user.domain.model.User;
import com.retonttdata.user.domain.port.output.UserRepository;
import com.retonttdata.user.infrastructure.persistence.entity.UserEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
public class UserRepositoryAdapter implements UserRepository {
    private final ReactiveUserRepository reactiveUserRepository;

    @Override
    public Mono<User> save(User user) {
        UserEntity entity = toEntity(user);
        return reactiveUserRepository.save(entity)
                .map(this::toDomain);
    }

    @Override
    public Mono<User> findByEmail(String email) {
        return reactiveUserRepository.findByEmail(email)
                .map(this::toDomain);
    }

    @Override
    public Flux<User> findAll() {
        return reactiveUserRepository.findAll()
                .map(this::toDomain);
    }

    @Override
    public Mono<Boolean> existsByEmail(String email) {
        return reactiveUserRepository.existsByEmail(email);
    }

    private UserEntity toEntity(User user) {
        UserEntity entity = new UserEntity();
        entity.setId(user.getId());
        entity.setFullName(user.getFullName());
        entity.setEmail(user.getEmail());
        entity.setPassword(user.getPassword());
        entity.setPhone(user.getPhone());
        return entity;
    }

    private User toDomain(UserEntity entity) {
        return User.builder()
                .id(entity.getId())
                .fullName(entity.getFullName())
                .email(entity.getEmail())
                .password(entity.getPassword())
                .phone(entity.getPhone())
                .build();
    }
}