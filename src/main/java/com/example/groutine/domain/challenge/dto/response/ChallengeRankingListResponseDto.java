package com.example.groutine.domain.challenge.dto.response;

import com.example.groutine.domain.mission.entity.ChallengeMissionType;
import lombok.Builder;

import java.util.List;

public record ChallengeRankingListResponseDto(
    List<ChallengeRankingResponseDto> challengeRankingList
) {

    @Builder
    public record ChallengeRankingResponseDto(
            Long userId,
            String nickname,
            Integer rank,
            Integer score
    ) {
    }
}
