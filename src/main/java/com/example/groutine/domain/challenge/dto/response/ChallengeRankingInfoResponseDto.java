package com.example.groutine.domain.challenge.dto.response;

import lombok.Builder;

@Builder
public record ChallengeRankingInfoResponseDto(
        Integer myRank,
        Integer myScore,
        Integer myAchievementRate,
        Integer totalParticipantCount,
        Integer totalAchieverCount
) {
}
