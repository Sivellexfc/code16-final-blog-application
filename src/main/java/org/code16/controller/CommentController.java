package org.code16.controller;


import lombok.RequiredArgsConstructor;
import org.code16.dto.request.CommentRequest;
import org.code16.dto.response.CommentResponse;
import org.code16.entity.Comment;
import org.code16.entity.Post;
import org.code16.entity.User;
import org.code16.services.entity.CommentService;
import org.code16.services.entity.PostService;
import org.code16.services.entity.UserService;
import org.code16.services.security.JwtService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/comments")
@RequiredArgsConstructor
public class CommentController {

    private final CommentService commentService;
    private final UserService userService;
    private final PostService postService;
    private final JwtService jwtService;

    @PostMapping("/add/{postId}")
    public ResponseEntity<CommentResponse> saveComments(@RequestBody CommentRequest commentRequest,
                                                        @PathVariable Long postId,
                                                        @RequestHeader (name="Authorization") String authHeader){
        Optional<Post> post =  postService.find(postId);
        if(post.isPresent()){
            Optional<User> user = jwtService.getUser(authHeader);
            if(user.get().getId() == post.get().getUser().getId()){
                Comment comment = new Comment();
                comment.setContent(commentRequest.content());
                comment.setPublishedDate(new Date());
                comment.setPost(post.get());
                comment.setUser(user.get());

                commentService.save(comment);
                CommentResponse commentResponse = new CommentResponse(
                        commentRequest.content(),
                        comment.getPublishedDate(),
                        comment.getPost(),
                        comment.getUser()
                );
                return ResponseEntity.ok(commentResponse);
            }
            else return ResponseEntity.status(HttpStatus.FORBIDDEN).body(null);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
    }

    @GetMapping("/get/all/{postId}")
    public ResponseEntity<List<CommentResponse>> getAllComment(@PathVariable Long postId){
        List<Comment> comments = commentService.getAll(postId);
        List<CommentResponse> commentResponses = new ArrayList<>();
        comments.forEach(
                comment -> {
                    commentResponses.add(new CommentResponse(
                            comment.getContent(),
                            comment.getPublishedDate(),
                            comment.getPost(),
                            comment.getUser()));
                }
        );
        return ResponseEntity.ok(commentResponses);
    }

    @DeleteMapping("/delete/{commentId}")
    public ResponseEntity deleteComments(@PathVariable Long commentId,
                                         @RequestHeader (name="Authorization") String authHeader){
        Optional<Comment> comment = commentService.find(commentId);
        if(comment.isPresent()){
            if(jwtService.getUser(authHeader).get().getId().equals(comment.get().getId())){
                commentService.delete(commentId);
                return ResponseEntity.ok().body(null);
            }
            else return ResponseEntity.status(HttpStatus.FORBIDDEN).body(null);
        }
        else return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
    }

    @PostMapping("/update/{commentId}")
    public ResponseEntity<CommentResponse> updateComments(@RequestBody CommentRequest commentRequest,
                                                          @PathVariable Long commentId,
                                                          @RequestHeader (name="Authorization") String authHeader){
        return null;
    }

}

