package org.example.webflux1.repository;

import org.example.webflux1.dto.UserResponse;
import org.junit.jupiter.api.Test;
import reactor.test.StepVerifier;

import static org.junit.jupiter.api.Assertions.*;

class UserRepositoryTest {
    private final UserRepository userRepository = new UserRepositoryImpl();

    @Test
    void save() {
        var user = User.builder().name("karina").email("karina@mail.net").build();
        StepVerifier.create(userRepository.save(user))
                .assertNext(u -> {
                    assertEquals(1L, u.getId());
                    assertEquals("karina", u.getName());
                })
                .verifyComplete();
    }

    @Test
    void findAll() {
        var user1 = User.builder().name("karina").email("karina@mail.net").build();
        var user2 = User.builder().name("winter").email("winter@mail.net").build();
        var user3 = User.builder().name("zizel").email("zizel@mail.net").build();
        userRepository.save(user1);
        userRepository.save(user2);
        userRepository.save(user3);

        StepVerifier.create(userRepository.findAll())
                .expectNextCount(3)
                .verifyComplete();
    }

    @Test
    void findById() {
        var user1 = User.builder().name("karina").email("karina@mail.net").build();
        userRepository.save(user1);
        StepVerifier.create(userRepository.findById(1L))
                .assertNext(u -> {
                    assertEquals(1L, u.getId());
                    assertEquals("karina", u.getName());
                })
                .verifyComplete();
    }

    @Test
    void deleteById() {
        var user1 = User.builder().name("karina").email("karina@mail.net").build();
        var user2 = User.builder().name("winter").email("winter@mail.net").build();

        userRepository.save(user1);
        userRepository.save(user2);
        StepVerifier.create(userRepository.deleteById(2L))
                .expectNextCount(1)
                .verifyComplete();
    }
}