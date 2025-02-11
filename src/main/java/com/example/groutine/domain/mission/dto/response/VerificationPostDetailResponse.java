package com.example.groutine.domain.mission.dto.response;

import lombok.Builder;

@Builder
public record VerificationPostDetailResponse(
        Long memberId,
        Long verificationPostId,
        String content,
        String imageUrl
) {
}
