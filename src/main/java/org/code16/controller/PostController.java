package org.code16.controller;

import lombok.RequiredArgsConstructor;
import org.code16.dto.request.PostRequest;
import org.code16.dto.response.PostResponse;
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
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/posts")
@RequiredArgsConstructor
public class PostController {

    private final CommentService commentService;
    private final UserService userService;
    private final PostService postService;
    private final JwtService jwtService;

    @PostMapping("/save")
    public ResponseEntity<PostResponse> savePost(@RequestBody PostRequest postRequest,
                                                 @RequestHeader (name="Authorization") String authHeader){
        String token = authHeader.substring(7);
        String username = jwtService.extractUsername(token);
        Optional<User> user = userService.findUserByUsername(username);
        if(user.isPresent()){
            Post post = new Post();
            post.setTitle(postRequest.title());
            post.setBody(postRequest.body());
            post.setUser(user.get());
            post.setPublishedDate(new Date());

            Post returnedPost = postService.save(post);

            PostResponse postResponse = new PostResponse(
                    returnedPost.getTitle(),
                    returnedPost.getBody(),
                    returnedPost.getPublishedDate(),
                    returnedPost.getUser()
            );
            return ResponseEntity.status(HttpStatus.CREATED).body(postResponse);
        }
        return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(null);
    }

    @GetMapping("/get/all")
    public ResponseEntity<List<PostResponse>> getAllPost(){

        List<PostResponse> postResponseList = new ArrayList<>();

        var list = postService.getAll();

//        postService.getAll().forEach(post -> {
//            PostResponse postResponse = new PostResponse(
//                    post.getTitle(),
//                    post.getBody(),
//                    post.getPublishedDate(),
//                    post.getUser()
//            );
//            postResponseList.add(postResponse);
//        });

        return ResponseEntity.status(HttpStatus.OK).body(postResponseList);
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
