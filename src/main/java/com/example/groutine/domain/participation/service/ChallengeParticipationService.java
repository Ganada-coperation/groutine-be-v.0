package com.example.groutine.domain.participation.service;

import com.example.groutine.domain.challenge.dto.response.ChallengeIdResponseDto;
import com.example.groutine.domain.challenge.entity.Challenge;
import com.example.groutine.domain.challenge.service.ChallengeQueryService;
import com.example.groutine.domain.member.entity.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class ChallengeParticipationService {

    private final ChallengeQueryService challengeQueryService;

    // 챌린지 참여
    public ChallengeIdResponseDto joinChallenge(Member member, Long challengeId) {

        // 챌린지 조회
        Challenge challenge = challengeQueryService.findChallengeById(challengeId);

        // 멤버가 참여하지 않은 챌린지가 맞는 지 확인

        return ;
    }

    // 챌린지 참여 취소
    public ChallengeIdResponseDto joinCancelChallenge(Member member, Long challengeId) {

        // 챌린지 조회
        Challenge challenge = challengeQueryService.findChallengeById(challengeId);

        // 멤버가 참여하고 있는 챌린지가 맞는지 확인

        return ;
    }

    // 멤버가 참여하고 있는 챌린지가 맞는지 확인 (나중에 챌린지 인증에서도 사용), 아니면 예외


}