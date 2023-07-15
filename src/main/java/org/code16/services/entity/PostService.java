package org.code16.services.entity;

import lombok.RequiredArgsConstructor;
import org.code16.entity.Post;
import org.code16.repository.PostRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;

    public Post save(Post post){
        return postRepository.save(post);
    }

    public Optional<Post> find(Long id){
        return postRepository.findById(id);
    }

    public List<Post> getAll(){
        return postRepository.findAll();
    }

    public List<Post> getPostByUser(Long id) {
        return postRepository.findByUserId(id);
    }

    public Post updatePost(Post post) {
        return postRepository.save(post);
    }

    public void deletePost(Long id) {
        postRepository.deleteById(id);
    }


}
