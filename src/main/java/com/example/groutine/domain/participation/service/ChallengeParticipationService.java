package com.example.groutine.domain.participation.service;

import com.example.groutine.domain.challenge.dto.response.ChallengeIdResponseDto;
import com.example.groutine.domain.challenge.entity.Challenge;
import com.example.groutine.domain.challenge.service.ChallengeQueryService;
import com.example.groutine.domain.member.entity.Member;
import com.example.groutine.domain.challenge.entity.ChallengeMember;
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

}