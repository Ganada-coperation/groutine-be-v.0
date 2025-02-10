package com.example.groutine.domain.mission.service;

import com.example.groutine.domain.member.entity.Member;
import com.example.groutine.domain.mission.dto.request.MissionVerificationRequestDto;
import com.example.groutine.domain.mission.dto.response.MissionVerificationIdRespinseDto;
import com.example.groutine.domain.mission.repository.ChallengeMissionVerificationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class ChallengeMissionVerificationCommandService {

    private final ChallengeMissionVerificationRepository challengeMissionVerificationRepository;

    private final ChallengeMissionQueryService challengeMissionQueryService;

    // 챌린지 미션 인증 저장 (미션 인증하기)
    public MissionVerificationIdRespinseDto postVerificationPost(
            Member member,
            Long challengeId,
            Long missionId,
            MissionVerificationRequestDto request
    ){

        // 미션이 존재 하는 지
        challengeMissionQueryService.findChallengeMission(missionId);

        // 내가 이미 인증(오늘 인증) 했는 지 확인

    }

}
