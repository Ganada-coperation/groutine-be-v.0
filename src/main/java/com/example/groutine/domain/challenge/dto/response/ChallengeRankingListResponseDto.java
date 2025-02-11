package com.example.groutine.domain.challenge.dto.response;

import com.example.groutine.domain.mission.entity.ChallengeMissionType;
import lombok.Builder;

import java.util.List;

@Builder
public record ChallengeRankingListResponseDto(
        int myRank,
        int myScore,
        int myAchievementRate,
        int totalParticipantCount,
        int totalAchieverCount,
        List<ChallengeRankingResponseDto> challengeRankingList
) {

    @Builder
    public record ChallengeRankingResponseDto(
            Long memberId,
            String name,
            Integer rank,
            Integer score
    ) {
    }
}
