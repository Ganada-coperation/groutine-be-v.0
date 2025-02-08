package com.example.groutine.domain.challenge.mapper;

import com.example.groutine.domain.challenge.dto.response.ChallengeActivityResponse.BeforeCompletedChallengeActivityResponseDto;
import com.example.groutine.domain.challenge.dto.response.ChallengeActivityResponse.CompletedChallengeActivityResponseDto;
import com.example.groutine.domain.challenge.dto.response.ChallengeProgressListResponseDto;
import com.example.groutine.domain.challenge.dto.response.ChallengeRankingListResponseDto;
import com.example.groutine.domain.challenge.entity.Challenge;
import com.example.groutine.domain.challenge.entity.ChallengeMember;
import com.example.groutine.domain.mission.entity.ChallengeMissionVerification;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
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

    // 완료된 챌린지 DTO 생성
    public static CompletedChallengeActivityResponseDto toCompletedChallengeActivityResponseDto(Challenge challenge, int participantCount, int myAchievementRate) {
        return CompletedChallengeActivityResponseDto.builder()
                .challengeId(challenge.getId())
                .challengeTitle(challenge.getTitle())
                .startDate(challenge.getStartDate().toString())
                .endDate(challenge.getEndDate().toString())
                .thumbnail(challenge.getThumbnail())
                .participantCount(participantCount)
                .myAchievementRate(myAchievementRate)
                .build();
    }

    // 진행 중인 챌린지 DTO 생성
    public static BeforeCompletedChallengeActivityResponseDto toBeforeCompletedChallengeActivityResponseDto(Challenge challenge) {
        return BeforeCompletedChallengeActivityResponseDto.builder()
                .challengeId(challenge.getId())
                .challengeTitle(challenge.getTitle())
                .startDate(challenge.getStartDate().toString())
                .endDate(challenge.getEndDate().toString())
                .thumbnail(challenge.getThumbnail())
                .daysRemaining(calculateDaysRemaining(challenge.getEndDate()))
                .build();
    }

    // 남은 일수 계산 todo: 서비스로 옮기기
    private static int calculateDaysRemaining(LocalDateTime endDate) {
        return (int) Duration.between(LocalDateTime.now(), endDate).toDays();
    }

}
