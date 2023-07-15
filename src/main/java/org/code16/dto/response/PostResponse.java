package org.code16.dto.response;

import org.code16.entity.User;

import java.util.Date;

public record PostResponse(
        String title,
        String body,
        Date createdDate,
        User user
) {
}
