package org.code16.services.entity;

import lombok.RequiredArgsConstructor;
import org.code16.entity.Comment;
import org.code16.repository.CommentRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CommentService {

    private final CommentRepository commentRepository;

    public Comment save(Comment comment) {
        return commentRepository.save(comment);
    }

    public List<Comment> getAll(Long postId) {
        return commentRepository.findCommentByPostId(postId);
    }

    public void delete(Long id){
        commentRepository.deleteById(id);
    }

    public Optional<Comment> find(Long id) {
        return commentRepository.findById(id);
    }
}
