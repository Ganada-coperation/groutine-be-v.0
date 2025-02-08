package com.example.groutine.domain.challenge.mapper;

import com.example.groutine.domain.challenge.dto.request.ChallengeRequestDto;
import com.example.groutine.domain.challenge.dto.response.ChallengeDetailResponseDto;
import com.example.groutine.domain.challenge.dto.response.ChallengeListResponseDto;
import com.example.groutine.domain.challenge.entity.Challenge;
import com.example.groutine.domain.member.entity.Member;
import com.example.groutine.global.util.DateUtil;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ChallengeMapper {

    // 챌린지 엔티티로 변환
    public static Challenge toChallenge(Member member, ChallengeRequestDto request) {
        return Challenge.builder()
                .title(request.title())
                .description(request.description())
                .startDate(request.startDate())
                .endDate(request.endDate())
                .writer(member)
                .build();
    }

    // 챌린지 엔티티에서 ChallengeListResponseDto로 변환
    public static ChallengeListResponseDto toChallengeListResponseDto(
            List<Challenge> challengeList
    ) {
        return ChallengeListResponseDto.builder()
                .challengeList(
                        challengeList.stream()
                                .map(challenge ->
                                        ChallengeListResponseDto.ChallengeResponseDto.builder()
                                                .challengeId(challenge.getId())
                                                .title(challenge.getTitle())
                                                .thumbnail(challenge.getThumbnail())
                                                .startDate(DateUtil.formatDate(challenge.getStartDate())) //todo: 날짜 형식 맞춰서 주기 (유틸 사용하기
                                                .endDate(DateUtil.formatDate(challenge.getEndDate()))
                                                .daysRemaining(DateUtil.calculateDaysRemaining(challenge.getStartDate())) // todo: 남은 날짜 계산 (유틸 사용하기
                                                .participantCount(challenge.getChallengeMemberList().size())
                                                .build()
                                )
                                .toList()
                ).build();
    }

    // 챌린지 엔티티에서 ChallengeDetailResponseDto로 변환
    public static ChallengeDetailResponseDto toChallengeDetailResponseDto(Challenge challenge) {
        return ChallengeDetailResponseDto.builder()
                .challengeId(challenge.getId())
                .writerId(challenge.getWriter().getId())
                .title(challenge.getTitle())
                .thumbnail(challenge.getThumbnail())
                .startDate(DateUtil.formatDate(challenge.getStartDate())) //todo: 날짜 형식 맞춰서 주기 (유틸 사용하기
                .endDate(DateUtil.formatDate(challenge.getEndDate()))
                .daysRemaining(DateUtil.calculateDaysRemaining(challenge.getStartDate())) // todo: 남은 날짜 계산 (유틸 사용하기
                .participantCount(challenge.getChallengeMemberList().size())
                .challengeDescription(challenge.getDescription())
                .challengeMissionList(
                        challenge.getChallengeMissionList().stream()
                                .map(mission ->
                                        new ChallengeDetailResponseDto.ChallengeMissionResponseDto(
                                                mission.getId(),
                                                mission.getTitle(),
                                                mission.getVerifyGuide(),
                                                mission.getChallengeMissionType()
                                        )
                                )
                                .toList()
                )
                .build();
    }

}
