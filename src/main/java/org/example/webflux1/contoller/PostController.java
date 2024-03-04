package org.example.webflux1.contoller;

import lombok.RequiredArgsConstructor;
import org.example.webflux1.dto.PostResponse;
import org.example.webflux1.service.PostService;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/posts")
public class PostController {
    private final PostService postService;

    @GetMapping("/{id}")
    public Mono<PostResponse> getPostContent(@PathVariable Long id) {
        return postService.getPostContent(id);
    }

    @GetMapping("/search")
    public Flux<PostResponse> getMultiplePostContent(@RequestParam(name = "ids") List<Long> idList) {
//        return postService.getMultiplePostContent(idList);
        return postService.getParellelMultiplePostContent(idList);
    }
}
