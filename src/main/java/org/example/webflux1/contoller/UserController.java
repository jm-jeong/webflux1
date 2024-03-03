package org.example.webflux1.contoller;

import lombok.RequiredArgsConstructor;
import org.example.webflux1.dto.UserCreateRequest;
import org.example.webflux1.dto.UserResponse;
import org.example.webflux1.dto.UserUpdateRequest;
import org.example.webflux1.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserController {
    private final UserService userService;

    @PostMapping("")
    public Mono<UserResponse> createUser(@RequestBody UserCreateRequest request) {
        return userService.create(request.getName(), request.getEmail())
                .map(UserResponse::of);
    }

    @GetMapping("")
    public Flux<UserResponse> findAllUsers() {
        return userService.findAll()
                .map(UserResponse::of);
    }

    @GetMapping("/{id}")
    public Mono<ResponseEntity<UserResponse>> findUser(@PathVariable Long id) {
        return userService.findById(id)
                .map(user -> ResponseEntity.ok(UserResponse.of(user)))
                .switchIfEmpty(Mono.just(ResponseEntity.notFound().build()));
    }

    @DeleteMapping("/{id}")
    public Mono<ResponseEntity<?>> deleteUser(@PathVariable Long id) {
        return userService.deleteById(id).then(Mono.just(ResponseEntity.noContent().build()));
    }

    @PutMapping("/{id}")
    public Mono<ResponseEntity<UserResponse>> updatedUser(@PathVariable Long id, @RequestBody UserUpdateRequest request) {
        // user(x) : 404 not found
        // user(o) : 200 ok
        return userService.update(id, request.getName(), request.getEmail())
                .map(user -> ResponseEntity.ok(UserResponse.of(user)))
                .switchIfEmpty(Mono.just(ResponseEntity.notFound().build()));
    }
}
