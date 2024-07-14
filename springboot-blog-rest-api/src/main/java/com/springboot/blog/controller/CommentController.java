package com.springboot.blog.controller;

import com.springboot.blog.payload.CommentDto;
import com.springboot.blog.service.CommentService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/")
public class CommentController {

    private CommentService commentService;

    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @PostMapping("/posts/{postid}/comments")
    public ResponseEntity<CommentDto> createComment(@PathVariable(name="postid") long postId,
                                                    @Valid @RequestBody CommentDto commentDto){

      return new ResponseEntity<>(commentService.createComment(postId,commentDto), HttpStatus.CREATED);
    }

    @GetMapping("/posts/{postId}/comments")
    public List<CommentDto> getCommentsByPostId(@PathVariable(name="postId") Long postId){

           return commentService.getCommentsByPostId(postId);
    }
    @GetMapping("/posts/{postId}/comments/{commentId}")
    public ResponseEntity<CommentDto> getCommentById(@PathVariable(name="postId") Long postId,
                                                     @PathVariable(name="commentId") Long commentId){

        CommentDto commentDto=commentService.getCommentById(postId,commentId);
        return new ResponseEntity<>(commentDto,HttpStatus.OK);

    }
    @PutMapping("/posts/{postId}/comments/{commentId}")
    public ResponseEntity<CommentDto> updateComment(@PathVariable(name="postId") long postId,
                                                    @PathVariable(name="commentId") long commentId,
                                                    @Valid @RequestBody CommentDto commentRequest){

        CommentDto updatedComment=commentService.updateComment(postId,commentId,commentRequest);
        return new ResponseEntity<>(updatedComment,HttpStatus.OK);
    }
    @DeleteMapping("/posts/{postId}/comments/{commentId}")
    public ResponseEntity<String> deleteComment(@PathVariable(name="postId") long postId,
                                                @PathVariable(name="commentId") long commentId){
        commentService.deleteComment(postId,commentId);
        return new ResponseEntity<>("Comment is Deled successfully",HttpStatus.OK);
    }
}
