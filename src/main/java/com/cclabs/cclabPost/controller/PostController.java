package com.cclabs.cclabPost.controller;

import com.cclabs.cclabPost.dto.ApiResponse;
import com.cclabs.cclabPost.dto.PostDTO;
import com.cclabs.cclabPost.exception.ResourceNotFoundException;
import com.cclabs.cclabPost.service.PostService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/posts")
public class PostController {

    @Autowired
    private PostService postService;

    @PostMapping
    public ResponseEntity<ApiResponse> createPost(@Valid @RequestBody PostDTO postDTO) {
        PostDTO createdPost = postService.createPost(postDTO);
        ApiResponse response = new ApiResponse(true, HttpStatus.CREATED.value(), "Post created successfully with ID: " + createdPost.getId());
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<PostDTO>> getAllPosts() {
        List<PostDTO> posts = postService.getAllPosts();
        return new ResponseEntity<>(posts, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse> getPostById(@PathVariable Long id) {
        PostDTO post = postService.getPostById(id);
        ApiResponse response = new ApiResponse(true, HttpStatus.OK.value(), "Post retrieved successfully");
        response.setMessage("Post details: " + post);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse> updatePost(@PathVariable Long id, @Valid @RequestBody PostDTO postDTO) {
        PostDTO updatedPost = postService.updatePost(id, postDTO);
        ApiResponse response = new ApiResponse(true, HttpStatus.OK.value(), "Post updated successfully with ID: " + updatedPost.getId());
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse> deletePost(@PathVariable Long id) {
        postService.deletePost(id);
        ApiResponse response = new ApiResponse(true, HttpStatus.NO_CONTENT.value(), "Post deleted successfully");
        return new ResponseEntity<>(response, HttpStatus.NO_CONTENT);
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ApiResponse> handleResourceNotFound(ResourceNotFoundException ex) {
        ApiResponse response = new ApiResponse(false, HttpStatus.NOT_FOUND.value(), ex.getMessage());
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }
}
