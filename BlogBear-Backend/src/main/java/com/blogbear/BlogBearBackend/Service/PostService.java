package com.blogbear.BlogBearBackend.Service;

import com.blogbear.BlogBearBackend.Dto.Pagination;
import com.blogbear.BlogBearBackend.Dto.PostDto;

public interface PostService {
    PostDto newPost(PostDto postDto);

    Pagination getAllPosts(int page, int pageSize, String sortBy, String sortDir);

    PostDto getPostById(long id);

    PostDto updatePostById(PostDto postDto, long id);

    void deletePostById(long id);
}
