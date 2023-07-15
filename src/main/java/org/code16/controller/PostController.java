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
        postService.getAll().forEach(post -> {
            PostResponse postResponse = new PostResponse(
                    post.getTitle(),
                    post.getBody(),
                    post.getPublishedDate(),
                    post.getUser()
            );
            postResponseList.add(postResponse);
        });

        return ResponseEntity.status(HttpStatus.OK).body(postResponseList);
    }

    @GetMapping("/get/user/id/{userId}")
    public ResponseEntity<List<PostResponse>> getPostByUserId(@PathVariable Long userId){
        Optional<User> user =  userService.findUserByUserId(userId);
        if(user.isPresent()){
            List<PostResponse> postResponseList = new ArrayList<>();
            List<Post> posts = postService.getPostByUser(userId);
            posts.forEach(post -> {
                PostResponse postResponse = new PostResponse(
                        post.getTitle(),
                        post.getBody(),
                        post.getPublishedDate(),
                        post.getUser()
                );
                postResponseList.add(postResponse);
            });
            return ResponseEntity.status(HttpStatus.OK).body(postResponseList);
        }
        return null;
    }

    @GetMapping("/get/user/my-post")
    public ResponseEntity<List<PostResponse>> getMyPost(@RequestHeader (name="Authorization") String authHeader){
        String token = authHeader.substring(7);
        String username = jwtService.extractUsername(token);
        Optional<User> user = userService.findUserByUsername(username);

        if(user.isPresent()){
            List<Post> posts = postService.getPostByUser(user.get().getId());
            List<PostResponse> postResponseList = new ArrayList<>();
            posts.forEach(post -> {
                PostResponse postResponse = new PostResponse(
                        post.getTitle(),
                        post.getBody(),
                        post.getPublishedDate(),
                        post.getUser()
                );
                postResponseList.add(postResponse);
            });
            return ResponseEntity.status(HttpStatus.OK).body(postResponseList);
        }
        return ResponseEntity.status(HttpStatus.OK).body(null);
    }

    @DeleteMapping("/delete/{postId}")
    public ResponseEntity deletePost(@PathVariable Long postId,
                                     @RequestHeader (name="Authorization") String authHeader){
        Optional<Post> post = postService.find(postId);
        if(post.isPresent()){
            String token = authHeader.substring(7);
            String username = jwtService.extractUsername(token);
            Optional<User> user = userService.findUserByUsername(username);
            if(post.get().getUser().getId() == user.get().getId()){
                postService.deletePost(post.get().getId());
                return ResponseEntity.ok("Post silindi");
            }
            else return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Bunu silmeye izniniz yok!!!! ");
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Post bulunamadı ");
    }

    @PatchMapping("/update/{postId}")
    public ResponseEntity<PostResponse> updatePost(@RequestBody PostRequest postRequest,
                                                   @PathVariable Long postId,
                                                   @RequestHeader (name="Authorization") String authHeader){
        Optional<Post> returnedPost = postService.find(postId);
        if(returnedPost.isPresent()){
            String token = authHeader.substring(7);
            String username = jwtService.extractUsername(token);
            Optional<User> user = userService.findUserByUsername(username);
            if(returnedPost.get().getUser().getId() == user.get().getId()){
                Post post = new Post(
                        returnedPost.get().getId(),
                        postRequest.title(),
                        postRequest.body(),
                        returnedPost.get().getPublishedDate(),
                        new Date(),
                        returnedPost.get().getUser(),
                        returnedPost.get().getComments()
                );
                PostResponse postResponse = new PostResponse(
                        post.getTitle(),
                        post.getBody(),
                        post.getPublishedDate(),
                        post.getUser()
                );
                postService.updatePost(post);
                return ResponseEntity.ok(postResponse);
            }
            else return ResponseEntity.badRequest().body(null);
        }
        else return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
    }
}
