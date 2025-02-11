package com.example.groutine.domain.mission.repository;

import com.example.groutine.domain.challenge.entity.ChallengeMember;
import com.example.groutine.domain.mission.entity.ChallengeMissionType;
import com.example.groutine.domain.mission.entity.ChallengeMissionVerification;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;
import java.util.List;

public interface ChallengeMissionVerificationRepository extends JpaRepository<ChallengeMissionVerification, Long> {
    // todo : page로 변경
    // ChallengeMember와 ChallengeMissionType에 맞는 미션 인증 조회
    List<ChallengeMissionVerification> findByChallengeMemberAndChallengeMission_ChallengeMissionType(
            ChallengeMember challengeMember,
            ChallengeMissionType challengeMissionType,
            Pageable pageable
    );

    // todo : 최적화 필요
    // 챌린지 아이디와 생성일자로 미션 인증 조회
    @Query("SELECT cmv FROM ChallengeMissionVerification cmv WHERE cmv.challengeMember.challenge.id = :challengeId AND cmv.createdAt BETWEEN :atStartOfDay AND :atTime")
    List<ChallengeMissionVerification> findByChallengeMember_Challenge_ChallengeIdAndCreatedAtBetween(Long challengeId, Object atStartOfDay, Object atTime, Pageable pageable);

    // 해당 날짜와 특정 미션 대한 내 미션 인증이 있는지 확인
    @Query("SELECT CASE WHEN COUNT(cmv) > 0 THEN TRUE ELSE FALSE END FROM ChallengeMissionVerification cmv WHERE cmv.challengeMember = :challengeMember AND cmv.createdAt BETWEEN :startOfDay AND :endOfDay AND cmv.challengeMission.challenge.id = :challengeMissionId")
    boolean existsByChallengeMemberAndCreatedAtBetweenAndChallengeMission_ChallengeMissionId(
            ChallengeMember challengeMember,
            LocalDateTime startOfDay,
            LocalDateTime endOfDay,
            Long challengeMissionId
    );
}