package com.example.groutine.domain.member.dto.response;

import com.example.groutine.domain.member.entity.Gender;
import lombok.Builder;

import java.time.LocalDateTime;

@Builder
public record MemberInfoResponse(
        String name,
        String profileImageUrl,
        LocalDateTime birth,
        Gender gender
) {
}
