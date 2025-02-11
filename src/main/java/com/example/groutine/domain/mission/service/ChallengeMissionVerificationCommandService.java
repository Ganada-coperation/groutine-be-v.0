package com.example.groutine.domain.mission.service;

import com.example.groutine.domain.challenge.entity.ChallengeMember;
import com.example.groutine.domain.challenge.service.ChallengeMemberQueryService;
import com.example.groutine.domain.member.entity.Member;
import com.example.groutine.domain.mission.dto.request.MissionVerificationRequestDto;
import com.example.groutine.domain.mission.dto.response.MissionVerificationIdRespinseDto;
import com.example.groutine.domain.mission.entity.ChallengeMission;
import com.example.groutine.domain.mission.entity.ChallengeMissionVerification;
import com.example.groutine.domain.mission.entity.VerifiyStatus;
import com.example.groutine.domain.mission.repository.ChallengeMissionVerificationRepository;
import com.example.groutine.domain.mission.status.ChallengeMissionErrorStatus;
import com.example.groutine.global.common.exception.RestApiException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Service
@Transactional
@RequiredArgsConstructor
public class ChallengeMissionVerificationCommandService {

    private final ChallengeMissionVerificationRepository challengeMissionVerificationRepository;

    private final ChallengeMissionQueryService challengeMissionQueryService;
    private final ChallengeMemberQueryService challengeMemberQueryService;
    private final ChallengeMissionVerificationQueryService challengeMissionVerificationQueryService;

    // 챌린지 미션 인증 저장 (미션 인증하기)
    public MissionVerificationIdRespinseDto postVerificationPost(
            Member member,
            Long challengeId,
            Long missionId,
            MissionVerificationRequestDto request
    ){

        // 미션이 존재 하는 지
        ChallengeMission challengeMission = challengeMissionQueryService.findChallengeMission(missionId);

        // 챌린지 멤버
        ChallengeMember challengeMember = challengeMemberQueryService.findChallengeMember(member, challengeId);

        // 내가 이미 인증(오늘 인증) 했는 지 확인
        if(challengeMissionVerificationQueryService.isExistVerificationPostByDateAndMission(challengeMember, missionId, LocalDate.now())){
            throw new RestApiException(ChallengeMissionErrorStatus.ALREADY_VERIFIED); // 이미 인증했음
        }

        // 미션 인증 저장
        return new MissionVerificationIdRespinseDto(
                challengeMissionVerificationRepository.save(
                        ChallengeMissionVerification.builder()
                                .challengeMember(challengeMember)
                                .challengeMission(challengeMission)
                                .imageUrl(request.imageUrl())
                                .content(request.content())
                                .status(VerifiyStatus.APPROVE)
                                .verifyDate(LocalDateTime.now())
                                .build()
                ).getId()
        );
    }

}
