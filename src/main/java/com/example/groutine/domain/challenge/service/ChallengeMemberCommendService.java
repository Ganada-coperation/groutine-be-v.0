package com.example.groutine.domain.challenge.service;

import com.example.groutine.domain.challenge.dto.response.ChallengeIdResponseDto;
import com.example.groutine.domain.challenge.entity.Challenge;
import com.example.groutine.domain.challenge.entity.ChallengeMember;
import com.example.groutine.domain.challenge.repository.ChallengeRepository;
import com.example.groutine.domain.member.entity.Member;
import com.example.groutine.domain.mission.repository.ChallengeMemberRepository;
import com.example.groutine.domain.mission.status.ChallengeParticipationErrorStatus;
import com.example.groutine.global.common.exception.RestApiException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class ChallengeMemberCommendService {

    private final ChallengeQueryService challengeQueryService;
    private final ChallengeMemberQueryService challengeMemberQueryService;

    private final ChallengeMemberRepository challengeMemberRepository;

    // 챌린지 참여
    public ChallengeIdResponseDto joinChallenge(Member member, Long challengeId) {

        // 챌린지 조회
        Challenge challenge = challengeQueryService.findChallengeById(challengeId);

        // 멤버가 참여하지 않은 챌린지가 맞는 지 확인
        if (challengeMemberRepository.existsByMemberAndChallenge(member, challenge)) {
            throw new RestApiException(ChallengeParticipationErrorStatus.ALREADY_PARTICIPATED_CHALLENGE);
        }

        challengeMemberRepository.save(
                ChallengeMember.builder()
                        .member(member)
                        .challenge(challenge)
                        .build()
        );

        return new ChallengeIdResponseDto(challenge.getId());
    }

    // 챌린지 참여 취소
    public ChallengeIdResponseDto joinCancelChallenge(Member member, Long challengeId) {

        // 챌린지 조회
        Challenge challenge = challengeQueryService.findChallengeById(challengeId);

        // 멤버가 참여하고 있는 챌린지가 맞는지 확인
        ChallengeMember challengeMember = challengeMemberQueryService.findChallengeMember(member, challenge);

        return new ChallengeIdResponseDto(challengeMember.getId());
    }

}
