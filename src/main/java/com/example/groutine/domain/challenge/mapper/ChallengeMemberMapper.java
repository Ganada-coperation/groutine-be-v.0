package com.example.groutine.domain.challenge.mapper;

import com.example.groutine.domain.challenge.dto.response.ChallengeRankingListResponseDto;
import com.example.groutine.domain.challenge.entity.ChallengeMember;

import java.util.List;

public class ChallengeMemberMapper {

    public static List<ChallengeRankingListResponseDto.ChallengeRankingResponseDto> toChallengeRankingList(List<ChallengeMember> challengeMembers) {
        return challengeMembers.stream()
                        .map(challengeMember -> ChallengeRankingListResponseDto.ChallengeRankingResponseDto.builder()
                                .memberId(challengeMember.getMember().getId())
                                .name(challengeMember.getMember().getName())
                                .score(challengeMember.getScore())
                                .build()
                        )
                        .toList();
    }

    public static ChallengeRankingListResponseDto toChallengeRankingListResponseDto(
            List<ChallengeRankingListResponseDto.ChallengeRankingResponseDto> challengeRankingList,
            ChallengeRankingListResponseDto.ChallengeRankingResponseDto myRanking,
            int myAchievementRate,
            int totalParticipantCount,
            int totalAchieverCount
    ) {
        return ChallengeRankingListResponseDto.builder()
                .myRank(myRanking != null ? myRanking.rank() : 0)
                .myScore(myRanking != null ? myRanking.score() : 0)
                .myAchievementRate(myAchievementRate)
                .totalParticipantCount(totalParticipantCount)
                .totalAchieverCount(totalAchieverCount)
                .challengeRankingList(challengeRankingList)
                .build();
    }
}
