package com.example.groutine.domain.challenge.dto.response;

import lombok.Builder;

@Builder
public record ChallengeRankingInfoResponseDto(
        int myRank,
        int myScore,
        int myAchievementRate,
        int totalParticipantCount,
        int totalAchieverCount
) {
}
