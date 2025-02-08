package com.example.groutine.domain.challenge.repository;

import com.example.groutine.domain.challenge.entity.Challenge;
import com.example.groutine.domain.member.entity.Member;
import com.example.groutine.domain.challenge.entity.ChallengeMember;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ChallengeMemberRepository extends JpaRepository<ChallengeMember, Long> {

    Boolean existsByMemberAndChallenge(Member member, Challenge challenge);
    Optional<ChallengeMember> findByMemberAndChallenge(Member member, Challenge challenge);

    // 챌린지 실시간 랭킹 조회
    List<ChallengeMember> findByChallengeOrderByScoreDesc(Challenge challenge);
}
