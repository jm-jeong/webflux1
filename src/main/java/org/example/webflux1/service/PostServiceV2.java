package org.example.webflux1.service;

import lombok.RequiredArgsConstructor;
import org.example.webflux1.repository.Post;
import org.example.webflux1.repository.PostR2dbcRepository;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class PostServiceV2 {
    private final PostR2dbcRepository postR2dbcRepository;

    public Mono<Post> create(Long userId, String title, String content) {
        return postR2dbcRepository.save(
                Post.builder()
                        .userId(userId)
                        .title(title)
                        .content(content)
                        .build());
    }

    public Mono<Post> findById(Long id) {
        return postR2dbcRepository.findById(id);
    }

    public Flux<Post> findAll() {
        return postR2dbcRepository.findAll();
    }

    public Flux<Post> findAllByUserId(Long id) {
        return postR2dbcRepository.findByUserId(id);
    }

    public Mono<Void> deleteById(Long id) {
        return postR2dbcRepository.deleteById(id);
    }
}
