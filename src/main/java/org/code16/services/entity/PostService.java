package org.code16.services.entity;

import lombok.RequiredArgsConstructor;
import org.code16.entity.Post;
import org.code16.repository.PostRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;

    public Post save(Post post){
        return postRepository.save(post);
    }

    public List<Post> getAll(){
        return postRepository.findAll();
    }


}
