package com.example.groutine.domain.challenge.repository;

import com.example.groutine.domain.challenge.entity.Challenge;
import com.example.groutine.domain.member.entity.Member;
import com.example.groutine.domain.challenge.entity.ChallengeMember;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface ChallengeMemberRepository extends JpaRepository<ChallengeMember, Long> {

    Boolean existsByMemberAndChallenge(Member member, Challenge challenge);
    Optional<ChallengeMember> findByMemberAndChallenge(Member member, Challenge challenge);
    Optional<ChallengeMember> findByMemberAndChallengeId(Member member, Long challengeId);

    // 챌린지 실시간 랭킹 조회
    List<ChallengeMember> findByChallengeOrderByScoreDesc(Challenge challenge);

    @Query("SELECT cm FROM ChallengeMember cm WHERE cm.member = :member AND cm.challenge.endDate < CURRENT_TIMESTAMP")
    List<ChallengeMember> findCompletedChallenges(Member member, Pageable pageable);

    @Query("SELECT cm FROM ChallengeMember cm WHERE cm.member = :member AND cm.challenge.endDate >= CURRENT_TIMESTAMP")
    List<ChallengeMember> findInProgressChallenges(Member member, Pageable pageable);
}
