package com.cclabs.cclabPost.service;

import com.cclabs.cclabPost.dto.PostDTO;
import com.cclabs.cclabPost.exception.ResourceNotFoundException;
import com.cclabs.cclabPost.exception.ResourceNotFoundException; // 새로 만든 예외 클래스
import com.cclabs.cclabPost.models.Post;
import com.cclabs.cclabPost.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PostService {

    @Autowired
    private PostRepository postRepository;

    // Create Post
    public PostDTO createPost(PostDTO postDTO) {
        validatePostDTO(postDTO); // 유효성 검증
        Post post = convertToEntity(postDTO);
        Post savedPost = postRepository.save(post);
        postDTO.setId(savedPost.getId());
        return postDTO;
    }

    // Get All Posts
    public List<PostDTO> getAllPosts() {
        return postRepository.findAll()
                .stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    // Get Post by ID
    public PostDTO getPostById(Long id) {
        Post post = postRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Post not found with id: " + id));
        return convertToDto(post);
    }

    // Update Post
    public PostDTO updatePost(Long id, PostDTO postDTO) {
        validatePostDTO(postDTO); // 유효성 검증
        Post post = postRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Post not found with id: " + id));
        post.setTitle(postDTO.getTitle());
        post.setContent(postDTO.getContent());
        post.setAuthor(postDTO.getAuthor());
        postRepository.save(post);
        postDTO.setId(post.getId());
        return postDTO;
    }

    // Delete Post
    public void deletePost(Long id) {
        if (!postRepository.existsById(id)) {
            throw new ResourceNotFoundException("Post not found with id: " + id);
        }
        postRepository.deleteById(id);
    }

    // Convert PostDTO to Post
    private Post convertToEntity(PostDTO postDTO) {
        Post post = new Post();
        post.setTitle(postDTO.getTitle());
        post.setContent(postDTO.getContent());
        post.setAuthor(postDTO.getAuthor());
        return post;
    }

    // Convert Post to PostDTO
    private PostDTO convertToDto(Post post) {
        PostDTO postDTO = new PostDTO();
        postDTO.setId(post.getId());
        postDTO.setTitle(post.getTitle());
        postDTO.setContent(post.getContent());
        postDTO.setAuthor(post.getAuthor());
        return postDTO;
    }

    // Validate PostDTO
    private void validatePostDTO(PostDTO postDTO) {
        if (postDTO.getTitle() == null || postDTO.getTitle().isBlank()) {
            throw new ResourceNotFoundException("Title cannot be null or empty");
        }
        if (postDTO.getContent() == null || postDTO.getContent().isBlank()) {
            throw new ResourceNotFoundException("Content cannot be null or empty");
        }
        if (postDTO.getAuthor() == null || postDTO.getAuthor().isBlank()) {
            throw new ResourceNotFoundException("Author cannot be null or empty");
        }
    }
}
