package org.example.webflux1.contoller;

import lombok.RequiredArgsConstructor;
import org.example.webflux1.dto.PostCreateRequest;
import org.example.webflux1.dto.PostResponseV2;
import org.example.webflux1.service.PostServiceV2;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v2/posts")
public class PostControllerV2 {
    private final PostServiceV2 postServiceV2;

    @GetMapping("")
    public Flux<PostResponseV2> findAllPost() {
        return postServiceV2.findAll()
                .map(PostResponseV2::of);
    }

    @PostMapping("")
    public Mono<PostResponseV2> createPost(@RequestBody PostCreateRequest postCreateRequest) {
        return postServiceV2.create(postCreateRequest.getUserId(), postCreateRequest.getTitle(), postCreateRequest.getContent())
                .map(PostResponseV2::of);
    }

    @GetMapping("/{id}")
    public Mono<ResponseEntity<PostResponseV2>> findPost(@PathVariable Long id) {
        return postServiceV2.findById(id)
                .map(p -> ResponseEntity.ok().body(PostResponseV2.of(p)))
                .switchIfEmpty(Mono.just(ResponseEntity.notFound().build()));
    }

    @DeleteMapping("/{id}")
    public Mono<ResponseEntity<?>> deletePost(@PathVariable Long id) {
        return postServiceV2.deleteById(id).then(Mono.just(ResponseEntity.noContent().build()));
    }
}