package com.cclabs.cclabPost.repository;

import com.cclabs.cclabPost.models.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Long> {
}
