package com.example.groutine.domain.mission.service;

import com.example.groutine.domain.challenge.entity.ChallengeMember;
import com.example.groutine.domain.member.entity.Member;
import com.example.groutine.domain.mission.Mapper.ChallengeMissionVerificationMapper;
import com.example.groutine.domain.mission.dto.response.VerificationPostDetailResponse;
import com.example.groutine.domain.mission.entity.ChallengeMissionType;
import com.example.groutine.domain.mission.entity.ChallengeMissionVerification;
import com.example.groutine.domain.mission.repository.ChallengeMissionVerificationRepository;
import com.example.groutine.domain.mission.status.ChallengeMissionErrorStatus;
import com.example.groutine.global.common.exception.RestApiException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
    public List<ChallengeMissionVerification> getVerificationPostsByDate(
            ChallengeMember challengeMember, Pageable pageable
    ) {
        // 미션 인증 조회 (미션 타입에 맞는 미션과 연관된 인증만 조회)
        return challengeMissionVerificationRepository.findByChallengeMember(challengeMember, pageable);
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

}
