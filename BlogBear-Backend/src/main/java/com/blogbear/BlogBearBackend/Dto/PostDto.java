package com.blogbear.BlogBearBackend.Dto;

import com.blogbear.BlogBearBackend.Model.Comment;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

import java.util.List;

public class PostDto {
    private Long id;
    @NotEmpty(message = "Title is required")
    @Size(min = 2, max = 50, message = "Title must be between 2 and 50 characters")
    private String title;
    @NotEmpty(message = "Description is required")
    @Size(min = 10, max = 50, message = "Description must be between 10 and 50 characters")
    private String description;
    @NotEmpty(message = "Content is required")
    @Size(min = 20, max = 700, message = "Content must be between 20 and 700 characters")
    private String content;
    private List<Comment> comments;

    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
