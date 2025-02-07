package com.example.groutine.domain.participation.service;

import com.example.groutine.domain.challenge.dto.response.ChallengeIdResponseDto;
import com.example.groutine.domain.challenge.entity.Challenge;
import com.example.groutine.domain.challenge.service.ChallengeQueryService;
import com.example.groutine.domain.member.entity.Member;
import com.example.groutine.domain.participation.entity.ChallengeMember;
import com.example.groutine.domain.participation.repository.ChallengeMemberRepository;
import com.example.groutine.domain.participation.status.ChallengeParticipationErrorStatus;
import com.example.groutine.global.common.exception.RestApiException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class ChallengeParticipationService {

    private final ChallengeMemberRepository challengeMemberRepository;

    private final ChallengeQueryService challengeQueryService;

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

        return new ChallengeIdResponseDto(challenge.getId()); // todo: 어떤 응답 값을 줄지 고민 ㄱㄱ
    }

    // 챌린지 참여 취소
    public ChallengeIdResponseDto joinCancelChallenge(Member member, Long challengeId) {

        // 챌린지 조회
        Challenge challenge = challengeQueryService.findChallengeById(challengeId);

        // 멤버가 참여하고 있는 챌린지가 맞는지 확인
        ChallengeMember challengeMember = challengeMemberRepository
                .findByMemberAndChallenge(member, challenge)
                .orElseThrow(() -> new RestApiException(ChallengeParticipationErrorStatus.NOT_PARTICIPATED_CHALLENGE));

        return new ChallengeIdResponseDto(challengeMember.getId()); // todo: 어떤 응답 값을 줄지 고민 ㄱㄱ
    }

}