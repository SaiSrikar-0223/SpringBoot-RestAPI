package com.springboot.blog.payload;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class CommentDto {
    private long id;
    //name shouldn't be empty or null
    @NotEmpty(message = "Name shouldn't be null or empty")
    private String name;
    @NotEmpty(message = "Email shouldn't be null or empty")
    @Email
    private  String email;
    @NotEmpty
    @Size(min=10,message = "Body should have atleast 10 characters")
    private  String body;
}
