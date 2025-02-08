package com.example.groutine.domain.challenge.mapper;

import com.example.groutine.domain.challenge.dto.response.ChallengeProgressListResponseDto;
import com.example.groutine.domain.challenge.dto.response.ChallengeRankingListResponseDto;
import com.example.groutine.domain.challenge.entity.ChallengeMember;
import com.example.groutine.domain.mission.entity.ChallengeMissionVerification;

import java.time.LocalDate;
import java.util.List;

public class ChallengeMemberMapper {

    // 랭킹 리스트 변환
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

    // 랭킹 응답값 변환
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

    // 챌린지 진행 상태 응답 변환
    public static ChallengeProgressListResponseDto toChallengeProgressListResponseDto(
            List<ChallengeMissionVerification> challengeMissionVerificationList
    ) {
        return ChallengeProgressListResponseDto.builder()
                .challengeProgressList(
                        challengeMissionVerificationList.stream()
                                .map(challengeMissionVerification -> ChallengeProgressListResponseDto.ChallengeProgressResponseDto.builder()
                                        .date(LocalDate.from(challengeMissionVerification.getVerifyDate()))
                                        .verifiyStatus(challengeMissionVerification.getStatus())
                                        .build()
                                )
                                .toList()
                )
                .build();
    }

}
