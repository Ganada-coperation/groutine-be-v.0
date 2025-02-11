package com.example.groutine.domain.mission.dto.response;

import lombok.Builder;

import java.util.List;

@Builder
public class VerificationPostListResponseDto {
    private List<VerificationPostResponseDto> verificationPostList;

    @Builder
    public record VerificationPostResponseDto(
            Long verificationPostId,
            String imageUrl
    ) {
    }
}
