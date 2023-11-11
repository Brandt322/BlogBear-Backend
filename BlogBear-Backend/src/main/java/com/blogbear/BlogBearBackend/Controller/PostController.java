package com.blogbear.BlogBearBackend.Controller;

import com.blogbear.BlogBearBackend.Dto.Pagination;
import com.blogbear.BlogBearBackend.Dto.PostDto;
import com.blogbear.BlogBearBackend.Service.PostService;
import com.blogbear.BlogBearBackend.Utility.Constant;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/post")
public class PostController {
    @Autowired
    private PostService postService;

    @PostMapping("/newPost")
    public ResponseEntity<PostDto> savePost(@Valid @RequestBody PostDto postDto) {
        return new ResponseEntity<>(postService.newPost(postDto), HttpStatus.CREATED);
    }

    @GetMapping("/getPosts")
    public Pagination getAllPosts(
            @RequestParam(value = "page", defaultValue = Constant.PAGE_NUMBER, required = false) int page,
            @RequestParam(value = "pageSize", defaultValue = Constant.DEFAULT_PAGE, required = false) int pageSize,
            @RequestParam(value = "sortBy", defaultValue = Constant.DEFAULT_SORT, required = false) String sortBy,
            @RequestParam(value = "sortDir", defaultValue = Constant.DEFAULT_SORT_DIRECTION, required = false) String sortDir) {

            return postService.getAllPosts(page, pageSize, sortBy, sortDir);
    }

    @GetMapping("getPost/{id}")
    public ResponseEntity<PostDto> getPostById(@PathVariable(name = "id") long id) {
        return ResponseEntity.ok(postService.getPostById(id));
    }

    @PutMapping("updatePost/{id}")
    public ResponseEntity<PostDto> updatePostById(@PathVariable(name = "id") long id,@Valid  @RequestBody PostDto postDto) {
        PostDto post = postService.updatePostById(postDto, id);
        return new ResponseEntity<>(post, HttpStatus.OK);
    }

    @DeleteMapping("deletePost/{id}")
    public ResponseEntity<String> deletePostById(@PathVariable(name = "id") long id) {
        postService.deletePostById(id);
        return new ResponseEntity<>("Post deleted it works!, dont way back", HttpStatus.OK);
    }
}
