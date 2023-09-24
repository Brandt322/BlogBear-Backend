package com.blogbear.BlogBearBackend.Repository;

import com.blogbear.BlogBearBackend.Model.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Long> {
}
