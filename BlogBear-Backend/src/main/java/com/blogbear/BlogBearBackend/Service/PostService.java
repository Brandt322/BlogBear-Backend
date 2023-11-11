package com.blogbear.BlogBearBackend.Service;

import com.blogbear.BlogBearBackend.Dto.Pagination;
import com.blogbear.BlogBearBackend.Dto.PostDto;
import com.blogbear.BlogBearBackend.Exception.ResourceNotFoundException;
import com.blogbear.BlogBearBackend.Model.Post;
import com.blogbear.BlogBearBackend.Repository.PostRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PostService{

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private PostRepository postRepository;

    private PostDto convertEntityToDto(Post post) {
        PostDto postDto = modelMapper.map(post, PostDto.class);
        return postDto;
    }

    private Post convertDtoToEntity(PostDto postDto) {
        Post post = modelMapper.map(postDto, Post.class);
        return post;
    }

    public PostDto newPost(PostDto postDto) {
        Post post = convertDtoToEntity(postDto);
        Post newPst = postRepository.save(post);

        return convertEntityToDto(newPst);
    }

    public Pagination getAllPosts(int page, int pageSize, String sortBy, String sortDir) {

        //Si sortDir es igual a ascendente que ordene de forma ascendente, sino lo hara descendente
        Sort sort = sortDir
                .equalsIgnoreCase(Sort.Direction.ASC.name())
                ?Sort.by(sortBy).ascending()
                :Sort.by(sortBy).descending();

        Pageable pageable = PageRequest.of(page, pageSize, sort);
        Page<Post> forPosts = postRepository.findAll(pageable);
        List<Post> listPosts = forPosts.getContent();

        //El contenido es la lista de de dtos
        List<PostDto> content = new ArrayList<>();
        for (Post post : listPosts) {
            PostDto postDto = convertEntityToDto(post);
            content.add(postDto);
        }

        Pagination pagination = new Pagination();
        pagination.setPost(content); //le pasamos a la paginacion la lista de dtos previa
        pagination.setPage(forPosts.getNumber());
        pagination.setPageSize(forPosts.getSize());
        pagination.setTotalPost(forPosts.getTotalElements());
        pagination.setTotalPage(forPosts.getTotalPages());
        pagination.setLastPage(forPosts.isLast());
        return pagination;
    }


    public PostDto getPostById(long id) {
        Post post = postRepository.
                findById(id).orElseThrow(() -> new ResourceNotFoundException("Post", "id", id));
        return convertEntityToDto(post);
    }


    public PostDto updatePostById(PostDto postDto, long id) {
        Post post = postRepository.
                findById(id).orElseThrow(() -> new ResourceNotFoundException("Post", "id", id));

        post.setTitle(postDto.getTitle());
        post.setDescription(postDto.getDescription());
        post.setContent(postDto.getContent());

        Post postUpdated = postRepository.save(post);
        return convertEntityToDto(postUpdated);
    }


    public void deletePostById(long id) {
        Post post = postRepository.
                findById(id).orElseThrow(() -> new ResourceNotFoundException("Post", "id", id));

        postRepository.deleteById(id);
    }


}
