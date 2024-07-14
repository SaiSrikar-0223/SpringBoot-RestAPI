package com.springboot.blog.service.impl;

import com.springboot.blog.entity.Comment;
import com.springboot.blog.entity.Post;
import com.springboot.blog.exception.BlogAPIException;
import com.springboot.blog.exception.ResourceNotFoundException;
import com.springboot.blog.payload.CommentDto;
import com.springboot.blog.repository.CommentRepository;
import com.springboot.blog.repository.PostRepository;
import com.springboot.blog.service.CommentService;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CommentServiceImpl implements CommentService {

    private CommentRepository commentRepository;
    private PostRepository postRepository;
    private ModelMapper modelMapper;

    public CommentServiceImpl(CommentRepository commentRepository,PostRepository postRepository,
                              ModelMapper modelMapper) {
        this.commentRepository = commentRepository;
        this.postRepository=postRepository;
        this.modelMapper=modelMapper;
    }

    @Override
    public CommentDto createComment(Long postId, CommentDto commentDto) {
        Comment comment=mapToEntity(commentDto);

        //retrieve post Entity by Id
        Post post=postRepository.findById(postId).orElseThrow(()->
                new ResourceNotFoundException("POST","id",postId));

        //Set Post To Comment Entity
        comment.setPost(post);

        //save comment entity to DB
        Comment newComment= commentRepository.save(comment);

        return mapToDTO(newComment);
    }

    @Override
    public List<CommentDto> getCommentsByPostId(long postId) {
        //retrieve comments based on postId
        List<Comment> comments=commentRepository.findByPostId(postId);
        //convert List Of comment Entities to list of comment dto's
        return comments.stream().map(comment -> mapToDTO(comment)).collect(Collectors.toList());
    }

    @Override
    public CommentDto getCommentById(Long postId, Long commentId) {
        //retrieve post Entity by Id
        Post post=postRepository.findById(postId).orElseThrow(()->
                new ResourceNotFoundException("POST","id",postId));

        //retrieve comment  by id
        Comment comment=commentRepository.findById(commentId).orElseThrow(()->
                new ResourceNotFoundException("Comment","id",postId));

        if(!comment.getPost().getId().equals(post.getId())){
            throw new BlogAPIException(HttpStatus.BAD_REQUEST,"comment doesn't belong to post");
        }
        return mapToDTO(comment);
    }

    @Override
    public CommentDto updateComment(Long postId, Long commentId, CommentDto commentRequest) {

        //retrieve post Entity by Id
        Post post=postRepository.findById(postId).orElseThrow(()->
                new ResourceNotFoundException("POST","id",postId));

        //retrieve comment  by id
        Comment comment=commentRepository.findById(commentId).orElseThrow(()->
                new ResourceNotFoundException("Comment","id",postId));

        if(!comment.getPost().getId().equals(post.getId())){
            throw new BlogAPIException(HttpStatus.BAD_REQUEST,"Comment doesn't belong to Post");
        }

        comment.setName(commentRequest.getName());
        comment.setBody(commentRequest.getBody());
        comment.setEmail(commentRequest.getEmail());

        Comment updatedComment=commentRepository.save(comment);

        return mapToDTO(updatedComment);
    }

    @Override
    public void deleteComment(Long postId, Long commentId) {
        //retrieve post Entity by Id
        Post post=postRepository.findById(postId).orElseThrow(()->
                new ResourceNotFoundException("POST","id",postId));

        //retrieve comment  by id
        Comment comment=commentRepository.findById(commentId).orElseThrow(()->
                new ResourceNotFoundException("Comment","id",postId));

        if(!comment.getPost().getId().equals(post.getId())){
            throw new BlogAPIException(HttpStatus.BAD_REQUEST,"Comment doesn't belong to Post");
        }

       commentRepository.delete(comment);
    }

    private CommentDto mapToDTO(Comment comment){
        CommentDto commentDto=modelMapper.map(comment,CommentDto.class);
//        CommentDto commentDto=new CommentDto();
//        commentDto.setId(comment.getId());
//        commentDto.setBody(comment.getBody());
//        commentDto.setName(comment.getName());
//        commentDto.setEmail(comment.getEmail());
        return commentDto;
    }

    private Comment mapToEntity(CommentDto commentDto){
        Comment comment=modelMapper.map(commentDto,Comment.class);
//        Comment comment=new Comment();
//        comment.setId(commentDto.getId());
//        comment.setBody(commentDto.getBody());
//        comment.setName(commentDto.getName());
//        comment.setEmail(commentDto.getEmail());
        return comment;
    }
}
