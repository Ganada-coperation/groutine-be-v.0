package com.example.groutine.domain.mission.controller;

import com.example.groutine.domain.member.entity.Member;
import com.example.groutine.global.common.base.BaseResponse;
import com.example.groutine.global.config.security.auth.CurrentMember;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/challenge/missions")
@Tag(name = "챌린지 미션 관련 API", description = "참여한 챌린지의 미션을 다룸")
public class ChallengeMissionController {

    // 오늘의 미션 조회
    @GetMapping("")
    public BaseResponse<List<MissionResponseDto>> getTodayMissions(@CurrentMember Member member) {
        return BaseResponse.onSuccess(challengeMissionService.getTodayMissions(member));
    }

    // 미션 인증하기
    // todo: 내가 참여하는 챌린지가 맞는 지 어노테이션으로 앞 단에서 검즘
    @PostMapping("{challengeId}/{missionId}")
    public BaseResponse<MissionVerificationIdRespinseDto> verifyMission(
            @PathVariable Long challengeId,
            @PathVariable Long missionId,
            @CurrentMember Member member,
            @RequestBody MissionVerificationRequest request
    ) {
        return BaseResponse.onSuccess(challengeMissionService.verifyMission(member, challengeId, missionId, request));
    }

    // 참여한 챌린지 날짜별 미션 조회
    // todo: 내가 참여하는 챌린지가 맞는 지 어노테이션으로 앞 단에서 검즘
    @GetMapping("{challengeId}/{date}")
    public ResponseEntity<List<MissionResponseDto>> getMissionsByDate(
            @PathVariable Long challengeId, @PathVariable LocalDate date, @CurrentMember Member member
    ) {
        return BaseResponse.onSuccess(challengeParticipationService.getMissionsByDate(member, challengeId, date));
    }
}
