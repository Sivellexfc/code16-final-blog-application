package org.code16.controller;

import lombok.RequiredArgsConstructor;
import org.code16.dto.request.CommentRequest;
import org.code16.dto.request.PostRequest;
import org.code16.dto.response.CommentResponse;
import org.code16.dto.response.PostResponse;
import org.code16.entity.Post;
import org.code16.services.entity.CommentService;
import org.code16.services.entity.PostService;
import org.code16.services.entity.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/posts")
@RequiredArgsConstructor
public class PostController {

    private final CommentService commentService;
    private final UserService userService;
    private final PostService postService;

    @PostMapping("/save/{postId}")
    public ResponseEntity<PostResponse> savePost(@RequestBody PostRequest postRequest, @PathVariable Long postId){
        return null;
    }

    @GetMapping("/get/all")
    public ResponseEntity<PostResponse> getAllPost(){
        return null;
    }

    @GetMapping("/get/{userId}")
    public ResponseEntity<PostResponse> getPostByUserId(@PathVariable Long userId){
        return null;
    }

    @GetMapping("/get/myPosts")
    public ResponseEntity<List<PostResponse>> getPostByUserId(){
        return null;
    }

    @PostMapping("/delete/{postId}")
    public ResponseEntity<PostResponse> deletePost(@RequestBody PostRequest postRequest,@PathVariable Long postId){
        return null;
    }

    @PostMapping("/update/{postId}")
    public ResponseEntity<PostResponse> updatePost(@RequestBody PostRequest postRequest,@PathVariable Long postId){
        return null;
    }
}
