package com.example.groutine.domain.member.dto.request;


import com.example.groutine.domain.member.entity.Gender;

import java.time.LocalDateTime;

public record MemberInfoRequest(
        String name,
        String email,
        String profileImageUrl,
        LocalDateTime birth,
        Gender gender
) {
}
