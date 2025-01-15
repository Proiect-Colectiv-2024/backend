package com.rippleeffect.backend.service;

import com.rippleeffect.backend.models.Challenge;
import com.rippleeffect.backend.models.Post;
import com.rippleeffect.backend.utils.JsonFileReader;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostService {
    private final JsonFileReader jsonFileReader;
    private final List<Post> posts;

    private static final String FILE_PATH = "data/posts.json";

    public PostService(JsonFileReader jsonFileReader) {
        this.jsonFileReader = jsonFileReader;
        this.posts = jsonFileReader.readPostsFromFile();
    }

    public List<Post> getPosts() {
        return posts;
    }

    public Post getPostById(Integer id) {
        return posts.stream()
                .filter(post -> post.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Post not found with id: " + id));
    }

    public Post createPost(Post newPost) {
        // Set a new ID for the post
        Integer newId = posts.stream().mapToInt(Post::getId).max().orElse(0) + 1;
        newPost.setId(newId);
        posts.add(newPost);

        // Write to JSON file
        jsonFileReader.writePostsToFile(FILE_PATH, posts);

        return newPost;
    }
}
