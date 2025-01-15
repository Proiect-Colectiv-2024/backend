package com.rippleeffect.backend.controllers;

import com.rippleeffect.backend.models.Post;
import com.rippleeffect.backend.service.PostService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/posts")
@CrossOrigin(origins = "http://localhost:8081")
public class PostController {
    private final PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    @GetMapping
    public List<Post> getPosts() {
        return postService.getPosts();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Post> getPostById(@PathVariable Integer id) {
        Post player = postService.getPostById(id);
        return ResponseEntity.ok(player);
    }

    @PostMapping
    public ResponseEntity<Post> createPost(@RequestBody Post newPost) {
        try {
            Post createdPost = postService.createPost(newPost);
            return ResponseEntity.status(HttpStatus.CREATED).body(createdPost);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

}
