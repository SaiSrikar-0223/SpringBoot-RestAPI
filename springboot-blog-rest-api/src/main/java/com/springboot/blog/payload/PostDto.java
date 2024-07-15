package com.springboot.blog.payload;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.util.Set;

@Data
public class PostDto {
    private long id;

    //post title shouldn't be empty or null and should have atleast 2 characters
    @NotEmpty
    @Size(min = 2,message = "Post title should have atleast 2 characters")
    private String title;

    //post description shouldn't be empty or null and should have atleast 10 characters
    @NotEmpty
    @Size(min = 10,message = "Post description should have atleast 2 characters")
    private String description;

    //post content shouldn't be empty or null
    @NotEmpty
    private String content;

    private Set<CommentDto> comments;

    private Long categoryId;
}
