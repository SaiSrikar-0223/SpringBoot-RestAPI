package com.springboot.blog.service.impl;

import com.springboot.blog.entity.Category;
import com.springboot.blog.entity.Post;
import com.springboot.blog.exception.ResourceNotFoundException;
import com.springboot.blog.payload.PostDto;
import com.springboot.blog.payload.PostResponse;
import com.springboot.blog.repository.CategoryRepository;
import com.springboot.blog.repository.PostRepository;
import com.springboot.blog.service.PostService;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PostServiceImpl implements PostService {

    private PostRepository postRepository;

    private CategoryRepository categoryRepository;

    private ModelMapper mapper;

    public PostServiceImpl(PostRepository postRepository,ModelMapper mapper,
                           CategoryRepository categoryRepository) {
        this.postRepository = postRepository;
        this.mapper=mapper;
        this.categoryRepository=categoryRepository;
    }

    @Override
    public PostDto createPost(PostDto postDto) {

        Category category=categoryRepository.findById(postDto.getCategoryId()).
                orElseThrow(()->new ResourceNotFoundException("Category","id", postDto.getCategoryId()));

       //Convert DTO to Entity
        Post post=mapToEntity(postDto);
        post.setCategory(category);
        Post newPost= postRepository.save(post);

       //convert Post Entity to PostDto and we need to send it to the controller
       PostDto postResponse=mapToDto(newPost);
        return postResponse;
    }

    @Override
    public PostResponse getAllPosts(int pageNo,int pageSize,String sortBy,String sortDir) {

        Sort sort=sortDir.equalsIgnoreCase(Sort.Direction.ASC.name())? Sort.by(sortBy).ascending()
                : Sort.by(sortBy).descending();

        //create Pageable Instance
        Pageable pageable= PageRequest.of(pageNo,pageSize,sort);

        Page<Post> posts=postRepository.findAll(pageable);

        //get content from page object
        List<Post> listOfPosts=posts.getContent();

        List<PostDto> content=listOfPosts.stream().map(post -> mapToDto(post)).collect(Collectors.toList());

        PostResponse postResponse=new PostResponse();
        postResponse.setContent(content);
        postResponse.setPageNo(posts.getNumber());
        postResponse.setPageSize(posts.getSize());
        postResponse.setTotalElements(posts.getTotalElements());
        postResponse.setTotalPages(posts.getTotalPages());
        postResponse.setLast(posts.isLast());

        return postResponse;
    }

    @Override
    public PostDto getPostById(long id) {
        Post post=postRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("POST","id",id));
        return mapToDto(post);
    }

    @Override
    public PostDto updatePost(PostDto postDto, long id) {
        //get post by id from the database
        Post post=postRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("POST","id",id));

        Category category=categoryRepository.findById(postDto.getCategoryId()).
                orElseThrow(()->new ResourceNotFoundException("Category","id",postDto.getCategoryId()));

        post.setTitle(postDto.getTitle());
        post.setContent(postDto.getContent());
        post.setDescription(postDto.getDescription());
        post.setCategory(category);
        Post updatepost=postRepository.save(post);
        return mapToDto(updatepost);
    }

    @Override
    public void deleteById(long id) {
        //get post by id from the database
        Post post=postRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("POST","id",id));
        postRepository.delete(post);
        //postRepository.deleteById(post.getId());
    }

    @Override
    public List<PostDto> getPostByCategoryId(Long categoryId) {
       Category category=categoryRepository.findById(categoryId).
               orElseThrow(()->new ResourceNotFoundException("Category","id",categoryId));

       List<Post> posts=postRepository.findByCategoryId(categoryId);

       return posts.stream().map((post)->mapToDto(post)).collect(Collectors.toList());
    }

    //convert Post Entity to PostDto
    private PostDto mapToDto(Post post){
        PostDto postDto=mapper.map(post,PostDto.class);

//        PostDto postDto=new PostDto();
//        postDto.setId(post.getId());
//        postDto.setTitle(post.getTitle());
//        postDto.setDescription(post.getDescription());
//        postDto.setContent(post.getContent());
        return postDto;
    }

    //convert the DTO to Entity
    private Post mapToEntity(PostDto postDto) {
        Post post=mapper.map(postDto,Post.class);
//        Post post=new Post();
//        post.setTitle(postDto.getTitle());
//        post.setDescription((postDto.getDescription()));
//        post.setContent(postDto.getContent());
        return post;
    }
}
