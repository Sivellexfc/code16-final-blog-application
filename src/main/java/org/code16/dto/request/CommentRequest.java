package org.code16.dto.request;

import org.code16.entity.Post;

public record CommentRequest(
        Post post,
        String content
) {
}
