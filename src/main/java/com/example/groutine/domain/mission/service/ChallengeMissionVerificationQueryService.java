package com.example.groutine.domain.mission.service;

import com.example.groutine.domain.challenge.entity.ChallengeMember;
import com.example.groutine.domain.member.entity.Member;
import com.example.groutine.domain.mission.Mapper.ChallengeMissionVerificationMapper;
import com.example.groutine.domain.mission.dto.response.VerificationPostDetailResponse;
import com.example.groutine.domain.mission.dto.response.VerificationPostListResponseDto;
import com.example.groutine.domain.mission.entity.ChallengeMissionType;
import com.example.groutine.domain.mission.entity.ChallengeMissionVerification;
import com.example.groutine.domain.mission.repository.ChallengeMissionVerificationRepository;
import com.example.groutine.domain.mission.status.ChallengeMissionErrorStatus;
import com.example.groutine.global.common.exception.RestApiException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ChallengeMissionVerificationQueryService {

    private final ChallengeMissionVerificationRepository challengeMissionVerificationRepository;

    // 내 미션 인증 조회 (미션 타입에 맞는)
    public List<ChallengeMissionVerification> getMyMissionVerificationList(
            ChallengeMember challengeMember, Pageable pageable, ChallengeMissionType challengeMissionType
    ) {
        // 미션 인증 조회 (미션 타입에 맞는 미션과 연관된 인증만 조회)
        return challengeMissionVerificationRepository.findByChallengeMemberAndChallengeMission_ChallengeMissionType(
                challengeMember,
                challengeMissionType,
                pageable
        );
    }

    // 참여한 챌린지 날짜별 인증 사진 리스트 조회
    public VerificationPostListResponseDto getVerificationPostsByDate(
            Long challengeId, LocalDate date, Pageable pageable
    ) {
        // 미션 인증 조회 (미션 타입에 맞는 미션과 연관된 인증만 조회)
        List<ChallengeMissionVerification> challengeMissionVerificationList
                = challengeMissionVerificationRepository.findByChallengeMember_Challenge_ChallengeIdAndCreatedAtBetween(
                challengeId,
                date.atStartOfDay(),
                date.atTime(23, 59, 59),
                pageable
        );

        return ChallengeMissionVerificationMapper.toVerificationPostListResponseDto(challengeMissionVerificationList);

    }

    // 참여한 챌린지 날짜별 인증 사진 상세 조회
    public VerificationPostDetailResponse getVerificationPostDetail(
            Long verifyPostId
    ) {
        // 미션 인증 조회
        ChallengeMissionVerification challengeMissionVerification
                = challengeMissionVerificationRepository.findById(verifyPostId)
                .orElseThrow(() -> new RestApiException(ChallengeMissionErrorStatus.VERIFICATION_POST_NOT_FOUND) );

        return ChallengeMissionVerificationMapper.toVerificationPostDetailResponse(challengeMissionVerification);
    }

    // 해당 날짜와 특정 미션에 대한 미션 인증이 있는 지 확인
    public boolean isExistVerificationPostByDateAndMission(
            ChallengeMember challengeMember, Long missionId, LocalDate date
            ) {
        return challengeMissionVerificationRepository.existsByChallengeMemberAndCreatedAtBetweenAndChallengeMission_ChallengeMissionId(
                challengeMember,
                date.atStartOfDay(),
                date.atTime(23, 59, 59),
                missionId
        );
    }
}
