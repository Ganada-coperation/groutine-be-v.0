package com.example.groutine.domain.challenge.service;

import com.example.groutine.domain.challenge.dto.request.ChallengeRequestDto;
import com.example.groutine.domain.challenge.dto.response.ChallengeIdResponseDto;
import com.example.groutine.domain.member.entity.Member;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;

@Service
@Transactional
public class ChallengeCommandService {

    public ChallengeIdResponseDto createChallenge(Member member, ChallengeRequestDto request) {
        return ;
    }

    public ChallengeIdResponseDto updateChallenge(Long challengeId, ChallengeRequestDto request) {

        return ;
    }

    public ChallengeIdResponseDto deleteChallenge(Long challengeId) {

        return ;
    }
}
