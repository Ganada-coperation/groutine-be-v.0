package com.example.groutine.domain.mission.repository;

import com.example.groutine.domain.challenge.entity.ChallengeMember;
import com.example.groutine.domain.mission.entity.ChallengeMissionType;
import com.example.groutine.domain.mission.entity.ChallengeMissionVerification;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.domain.Pageable;
import java.util.List;

public interface ChallengeMissionVerificationRepository extends JpaRepository<ChallengeMissionVerification, Long> {
    // todo : page로 변경
    // ChallengeMember와 ChallengeMissionType에 맞는 미션 인증 조회
    List<ChallengeMissionVerification> findByChallengeMemberAndChallengeMission_ChallengeMissionType(
            ChallengeMember challengeMember,
            ChallengeMissionType challengeMissionType,
            Pageable pageable
    );

    // 챌린지와 날짜(해당 날(ex 2월 1일) 안에 생성된 것들)에 맞는 미션 모든 사람들의 미션 인증 조회
    List<ChallengeMissionVerification>

}
