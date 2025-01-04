package com.retonttdata.user.infrastructure.rest;

import com.retonttdata.user.application.dto.UserRegistrationRequest;
import com.retonttdata.user.application.dto.UserResponse;
import com.retonttdata.user.application.mapper.UserMapper;
import com.retonttdata.user.domain.port.input.UserRegistrationUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class UserController {
    private final UserRegistrationUseCase userRegistrationUseCase;
    private final UserMapper userMapper;

    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<UserResponse> registerUser(@Valid @RequestBody UserRegistrationRequest request) {
        return userRegistrationUseCase
                .registerUser(userMapper.toUser(request))
                .map(userMapper::toResponse);
    }

    @GetMapping("/users")
    public Flux<UserResponse> getAllUsers() {
        return userRegistrationUseCase
                .getAllUsers()
                .map(userMapper::toResponse);
    }
}