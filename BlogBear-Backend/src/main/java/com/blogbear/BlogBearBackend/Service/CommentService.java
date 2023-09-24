package com.blogbear.BlogBearBackend.Service;

import com.blogbear.BlogBearBackend.Dto.CommentDto;

import java.util.List;

public interface CommentService {
    CommentDto createComment(long postId, CommentDto commentDto);
    List<CommentDto> getAllCommentsByPostId(long postID);
    CommentDto getCommentById(long postId, long commentId);
    CommentDto updateCommentById(Long postId, Long commentId, CommentDto updateCommentDto);
    void deleteCommentById(Long postId, Long commentId);
}
