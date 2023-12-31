package com.blogbear.BlogBearBackend.Service;

import com.blogbear.BlogBearBackend.Exception.BlogAppException;
import com.blogbear.BlogBearBackend.Exception.ResourceNotFoundException;
import com.blogbear.BlogBearBackend.Model.Comment;
import com.blogbear.BlogBearBackend.Model.Post;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;


import com.blogbear.BlogBearBackend.Dto.CommentDto;
import com.blogbear.BlogBearBackend.Repository.CommentRepository;
import com.blogbear.BlogBearBackend.Repository.PostRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CommentService {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private PostRepository postRepository;

    private CommentDto mapEntityToDTO(Comment comment) {
        CommentDto dto = modelMapper.map(comment, CommentDto.class);
        return dto;
    }

    private Comment mapDtoToEntity(CommentDto commentDto) {
        Comment comment = modelMapper.map(commentDto, Comment.class);
        return comment;
    }


    public CommentDto createComment(long postId, CommentDto commentDto) {
        Comment comment = mapDtoToEntity(commentDto);
        Post post = postRepository.
                findById(postId).orElseThrow(() -> new ResourceNotFoundException("Post", "id", postId));
        comment.setPost(post);
        Comment newComment = commentRepository.save(comment);

        return mapEntityToDTO(newComment);
    }


    public List<CommentDto> getAllCommentsByPostId(long postID) {
        List<Comment> comments = commentRepository.findByPostId(postID);
        return comments.stream().map(comment -> mapEntityToDTO(comment)).collect(Collectors.toList());
    }


    public CommentDto getCommentById(long postId, long commentId) {
        Post post = postRepository.findById(postId).
                orElseThrow(() -> new ResourceNotFoundException("Post", "id", postId));

        Comment comment = commentRepository.findById(commentId).
                orElseThrow(() -> new ResourceNotFoundException("Comment", "id", commentId));

        if (!comment.getPost().getId().equals(post.getId())) {
            throw new BlogAppException(HttpStatus.BAD_REQUEST, "NOT FOUND THAT COMMENT IN THIS POST");
        }
        return mapEntityToDTO(comment);
    }


    public CommentDto updateCommentById(Long postId, Long commentId, CommentDto updateCommentDto) {
        Post post = postRepository.findById(postId).
                orElseThrow(() -> new ResourceNotFoundException("Post", "id", postId));

        Comment comment = commentRepository.findById(commentId).
                orElseThrow(() -> new ResourceNotFoundException("Comment", "id", commentId));

        if (!comment.getPost().getId().equals(post.getId())) {
            throw new BlogAppException(HttpStatus.BAD_REQUEST, "NOT FOUND THAT COMMENT IN THIS POST");
        }

        comment.setCommentary(updateCommentDto.getCommentary());

        Comment updatedComment = commentRepository.save(comment);

        return mapEntityToDTO(updatedComment);
    }


    public void deleteCommentById(Long postId, Long commentId) {
        Post post = postRepository.findById(postId).
                orElseThrow(() -> new ResourceNotFoundException("Post", "id", postId));

        Comment comment = commentRepository.findById(commentId).
                orElseThrow(() -> new ResourceNotFoundException("Comment", "id", commentId));

        if (!comment.getPost().getId().equals(post.getId())) {
            throw new BlogAppException(HttpStatus.BAD_REQUEST, "NOT FOUND THAT COMMENT IN THIS POST");
        }

        commentRepository.delete(comment);
    }
}
