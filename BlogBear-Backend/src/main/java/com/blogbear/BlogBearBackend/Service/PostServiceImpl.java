package com.blogbear.BlogBearBackend.Service;

import com.blogbear.BlogBearBackend.Dto.Pagination;
import com.blogbear.BlogBearBackend.Dto.PostDto;
import com.blogbear.BlogBearBackend.Exception.ResourceNotFoundException;
import com.blogbear.BlogBearBackend.Model.Post;
import com.blogbear.BlogBearBackend.Repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PostServiceImpl implements PostService{

    @Autowired
    private PostRepository postRepository;

    private PostDto convertEntityToDto(Post post) {
        PostDto postDto = new PostDto();
        postDto.setId(post.getId());
        postDto.setTitle(post.getTitle());
        postDto.setDescription(post.getDescription());
        postDto.setContent(post.getContent());
        return postDto;
    }

    private Post convertDtoToEntity(PostDto postDto) {
        Post post = new Post();
        post.setTitle(postDto.getTitle());
        post.setDescription(postDto.getDescription());
        post.setContent(postDto.getContent());
        return post;
    }

    @Override
    public PostDto newPost(PostDto postDto) {
        Post post = convertDtoToEntity(postDto);
        Post newPst = postRepository.save(post);

        return convertEntityToDto(newPst);
    }

    @Override
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
        List<PostDto> content = listPosts.stream().map(post -> convertEntityToDto(post))
                .collect(Collectors.toList());

        Pagination pagination = new Pagination();
        pagination.setPost(content); //le pasamos a la paginacion la lista de dtos previa
        pagination.setPage(forPosts.getNumber());
        pagination.setPageSize(forPosts.getSize());
        pagination.setTotalPost(forPosts.getTotalElements());
        pagination.setTotalPage(forPosts.getTotalPages());
        pagination.setLastPage(forPosts.isLast());
        return pagination;
    }

    @Override
    public PostDto getPostById(long id) {
        Post post = postRepository.
                findById(id).orElseThrow(() -> new ResourceNotFoundException("Post", "id", id));
        return convertEntityToDto(post);
    }

    @Override
    public PostDto updatePostById(PostDto postDto, long id) {
        Post post = postRepository.
                findById(id).orElseThrow(() -> new ResourceNotFoundException("Post", "id", id));

        post.setTitle(postDto.getTitle());
        post.setDescription(postDto.getDescription());
        post.setContent(postDto.getContent());

        Post postUpdated = postRepository.save(post);
        return convertEntityToDto(postUpdated);
    }

    @Override
    public void deletePostById(long id) {
        Post post = postRepository.
                findById(id).orElseThrow(() -> new ResourceNotFoundException("Post", "id", id));

        postRepository.deleteById(id);
    }


}
