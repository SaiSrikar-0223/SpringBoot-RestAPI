package com.springboot.blog.payload;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.util.Set;

@Data
@Schema(
      description = "PostDTO Model Information"
)
public class PostDto {
    private long id;

    @Schema(
            description = "Blog Post Title"
    )

    //post title shouldn't be empty or null and should have atleast 2 characters
    @NotEmpty
    @Size(min = 2,message = "Post title should have atleast 2 characters")
    private String title;

    @Schema(
            description = "Blog Post Description"
    )
    //post description shouldn't be empty or null and should have atleast 10 characters
    @NotEmpty
    @Size(min = 10,message = "Post description should have atleast 2 characters")
    private String description;

    //post content shouldn't be empty or null
    @Schema(
            description = "Blog Post Content"
    )
    @NotEmpty
    private String content;

    private Set<CommentDto> comments;
    @Schema(
            description = "Blog Post Category"
    )
    private Long categoryId;
}
