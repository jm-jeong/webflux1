package org.example.webflux1.repository;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Flux;

public interface PostR2dbcRepository extends ReactiveCrudRepository<Post, Long> {
    Flux<Post> findByUserId(Long userId);
}
