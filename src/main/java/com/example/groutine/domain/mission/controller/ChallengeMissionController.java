package com.example.groutine.domain.mission.controller;

import com.example.groutine.domain.member.entity.Member;
import com.example.groutine.domain.mission.dto.response.MissionListResponseDto;
import com.example.groutine.domain.mission.service.ChallengeMissionQueryService;
import com.example.groutine.global.common.base.BaseResponse;
import com.example.groutine.global.config.security.auth.CurrentMember;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/challenge/missions")
@Tag(name = "챌린지 미션 관련 API", description = "참여한 챌린지의 미션을 다룸")
public class ChallengeMissionController {

    private final ChallengeMissionQueryService challengeMissionQueryService;

    // 오늘의 미션 조회
    @GetMapping("")
    @Operation(summary = "오늘의 미션 조회 API", description = "내가 해야할 미션을 전부 다 조회, 미션 완료 미완료 상태값이 존재")
    public BaseResponse<MissionListResponseDto> getTodayMissions(@CurrentMember Member member) {
        return BaseResponse.onSuccess(challengeMissionQueryService.getMissionsByDate(member));
    }

    // 참여한 챌린지 날짜별 미션 조회
    // todo: 내가 참여하는 챌린지가 맞는 지 어노테이션으로 앞 단에서 검즘
    @GetMapping("{challengeId}/{date}")
    @Operation(summary = "날짜 별 미션 조회 API", description = "날짜 별 미션 조회, 미션 완료 미완료 상태값이 존재")
    public BaseResponse<MissionListResponseDto> getMissionsByDate(
            @PathVariable Long challengeId, @PathVariable LocalDateTime date, @CurrentMember Member member
    ) {
        return BaseResponse.onSuccess(challengeMissionQueryService.getMissionsByDate(member, challengeId, date));
    }
}
