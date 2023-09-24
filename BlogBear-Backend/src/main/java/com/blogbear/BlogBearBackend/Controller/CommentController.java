package com.blogbear.BlogBearBackend.Controller;

import com.blogbear.BlogBearBackend.Dto.CommentDto;
import com.blogbear.BlogBearBackend.Service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/comments")
public class CommentController {
    @Autowired
    CommentService commentService;

    @PostMapping("post/{postId}/comment")
    public ResponseEntity<CommentDto> createComment(
            @PathVariable(value="postId") long postId,
            @RequestBody CommentDto commentDto) {
        return new ResponseEntity<>(commentService.createComment(postId, commentDto), HttpStatus.CREATED);
    }

    @GetMapping("/post/{postId}/comments")
    public List<CommentDto> getAllCommentsByPostId(@PathVariable(value="postId") long postId) {
        return commentService.getAllCommentsByPostId(postId);
    }

    @GetMapping("/post/{postId}/comment/{commentId}")
    public ResponseEntity<CommentDto> getCommentByPostIdByCommentId(
            @PathVariable(value = "postId") long postId,
            @PathVariable(value = "commentId") long commentId) {
        CommentDto commentDto = commentService.getCommentById(postId, commentId);
        return new ResponseEntity<>(commentDto, HttpStatus.OK);
    }

    @PutMapping("/post/{postId}/comment/{commentId}")
    public ResponseEntity<CommentDto> updateCommentByPostIdByCommentId(
            @PathVariable(value = "postId") long postId,
            @PathVariable(value = "commentId") long commentId,
            @RequestBody CommentDto updateCommentDto
            ) {
        CommentDto updatedCommentDto = commentService.updateCommentById(postId, commentId, updateCommentDto);
        return new ResponseEntity<>(updatedCommentDto, HttpStatus.OK);
    }

    @DeleteMapping("/post/{postId}/comment/{commentId}")
    public ResponseEntity<String> deleteCommentById(
            @PathVariable(value = "postId") long postId,
            @PathVariable(value = "commentId") long commentId) {
        commentService.deleteCommentById(postId, commentId);
        return new ResponseEntity<>("The comment was successfully deleted", HttpStatus.OK);
    }
}
