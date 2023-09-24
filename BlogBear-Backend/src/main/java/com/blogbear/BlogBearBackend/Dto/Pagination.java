package com.blogbear.BlogBearBackend.Dto;

import java.util.List;

public class Pagination {
    private List<PostDto> post;
    private int page;
    private int pageSize;
    private long totalPost;
    private int totalPage;
    private boolean lastPage;

    public List<PostDto> getPost() {
        return post;
    }

    public void setPost(List<PostDto> post) {
        this.post = post;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public long getTotalPost() {
        return totalPost;
    }

    public void setTotalPost(long totalPost) {
        this.totalPost = totalPost;
    }

    public int getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }

    public boolean isLastPage() {
        return lastPage;
    }

    public void setLastPage(boolean lastPage) {
        this.lastPage = lastPage;
    }
}
