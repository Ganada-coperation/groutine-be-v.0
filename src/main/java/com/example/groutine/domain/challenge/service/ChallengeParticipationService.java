package com.example.groutine.domain.challenge.service;

import com.example.groutine.domain.challenge.dto.response.ChallengeIdResponseDto;
import com.example.groutine.domain.member.entity.Member;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class ChallengeParticipationService {

    public ChallengeIdResponseDto joinChallenge(Member member, Long challengeId) {
        return ;
    }

    public ChallengeIdResponseDto joinCancelChallenge(Member member, Long challengeId) {

        return ;
    }
}
