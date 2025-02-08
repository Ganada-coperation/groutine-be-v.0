package com.example.groutine.domain.mission.service;

import com.example.groutine.domain.challenge.entity.ChallengeMember;
import com.example.groutine.domain.mission.entity.ChallengeMissionType;
import com.example.groutine.domain.mission.entity.ChallengeMissionVerification;
import com.example.groutine.domain.mission.repository.ChallengeMissionVerificationRepository;
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

}
