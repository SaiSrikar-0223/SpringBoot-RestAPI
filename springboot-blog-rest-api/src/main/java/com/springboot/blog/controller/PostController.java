package com.springboot.blog.controller;

import com.springboot.blog.payload.PostDto;
import com.springboot.blog.payload.PostResponse;
import com.springboot.blog.service.PostService;
import com.springboot.blog.utils.AppConstants;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/posts")
public class PostController {

    private PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    //Create a blog Post Rest API
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public ResponseEntity<PostDto> createpost(@Valid @RequestBody PostDto postDto){
        return new ResponseEntity<>(postService.createPost(postDto), HttpStatus.CREATED);
    }

    //get AllPosts Rest API
    @GetMapping
    public PostResponse getAllPosts(
            @RequestParam(value="pageNo",defaultValue = AppConstants.DEFAULT_PAGE_NUMBER,required = false)int pageNo,
            @RequestParam(value ="pageSize",defaultValue = AppConstants.DEFAULT_PAGE_SIZE,required = false)int pageSize,
            @RequestParam(value="sortBy",defaultValue = AppConstants.DEFAULT_SORT_BY,required = false)String sortBy,
            @RequestParam(value="sortdir",defaultValue =AppConstants.DEFAULT_SORT_DIRECTION,required = false)String sortDir){
      return postService.getAllPosts(pageNo,pageSize,sortBy,sortDir);
    }

    //get Post By Id Rest API
    @GetMapping("/{id}")
     public ResponseEntity<PostDto> getPostById(@PathVariable(name="id") long id){
        return ResponseEntity.ok(postService.getPostById(id));
    }

    //update Post By id Rest API
    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("{id}")
    public ResponseEntity<PostDto> updatePostById(@Valid @RequestBody  PostDto postDto,
                                                  @PathVariable(name="id") long id){
        PostDto postUpdatedResponse=postService.updatePost(postDto,id);
        return new ResponseEntity<>(postUpdatedResponse,HttpStatus.OK);
    }
    //delete Post By Id API
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("{id}")
    public ResponseEntity<String> deletePost(@PathVariable(name="id")long id){
        postService.deleteById(id);
        return new ResponseEntity<>("PostEntity Deleted Successfully",HttpStatus.OK);
    }

    //Build Get Posts by Category Rest API
    //http://localhost:8080/api/posts/category/2
    @GetMapping("/category/{id}")
    public ResponseEntity<List<PostDto>> getPostByCategory(@PathVariable("id") Long categoryId){
        List<PostDto> postDtoList=postService.getPostByCategoryId(categoryId);
        return ResponseEntity.ok(postDtoList);
    }
}
