package org.code16.controller;


import lombok.RequiredArgsConstructor;
import org.code16.dto.request.CommentRequest;
import org.code16.dto.response.CommentResponse;
import org.code16.services.entity.CommentService;
import org.code16.services.entity.PostService;
import org.code16.services.entity.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/comments")
@RequiredArgsConstructor
public class CommentController {

    private final CommentService commentService;
    private final UserService userService;
    private final PostService postService;

    @PostMapping("/save/{postId}")
    public ResponseEntity<CommentResponse> saveComments(@RequestBody CommentRequest commentRequest,@PathVariable Long postId){
        return null;
    }

    @GetMapping("/get/all/{postId}")
    public ResponseEntity<List<CommentResponse>> getAllComment(@PathVariable Long postId){
        return null;
    }

    @PostMapping("/delete/{commentId}")
    public ResponseEntity<CommentResponse> deleteComments(@PathVariable Long commentId){
        return null;
    }

    @PostMapping("/update/{commentId}")
    public ResponseEntity<CommentResponse> updateComments(@RequestBody CommentRequest commentRequest,@PathVariable Long commentId){
        return null;
    }

}

