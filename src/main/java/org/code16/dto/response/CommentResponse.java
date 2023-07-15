package org.code16.dto.response;

import org.code16.entity.Post;
import org.code16.entity.User;

import java.util.Date;

public record CommentResponse(
        String content,
        Date publishedDate,
        Post post,
        User user
) {
}
