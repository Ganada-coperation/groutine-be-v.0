package com.example.groutine.domain.mission.controller;

import com.example.groutine.domain.member.entity.Member;
import com.example.groutine.domain.mission.dto.request.MissionVerificationRequestDto;
import com.example.groutine.domain.mission.dto.response.MissionVerificationIdRespinseDto;
import com.example.groutine.domain.mission.dto.response.VerificationPostDetailResponse;
import com.example.groutine.domain.mission.dto.response.VerificationPostListResponseDto;
import com.example.groutine.domain.mission.service.ChallengeMissionVerificationCommandService;
import com.example.groutine.domain.mission.service.ChallengeMissionVerificationQueryService;
import com.example.groutine.global.common.base.BaseResponse;
import com.example.groutine.global.config.security.auth.CurrentMember;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/challenge/{challengeId}/verify-posts")
@Tag(name = "챌린지 미션 인증 사진 관련 API", description = "참여한 챌린지의 미션 인증 사진을 다룸")
public class ChallengeMissionPostController {

    private final ChallengeMissionVerificationQueryService challengeMissionVerificationQueryService;
    private final ChallengeMissionVerificationCommandService challengeMissionVerificationCommandService;


    // 참여한 챌린지 날짜별 인증 사진 리스트 조회
    // todo: 내가 참여하는 챌린지가 맞는 지 어노테이션으로 앞 단에서 검즘
    @GetMapping("/{date}")
    @Operation(summary = "날짜 별 미션 인증 사진 리스트 조회 API", description = "날짜 별 미션 인증 사진 리스트 조회")
    public BaseResponse<VerificationPostListResponseDto> getVerificationPostsByDate(
            @CurrentMember Member member,
            @PathVariable Long challengeId, @PathVariable LocalDate date,
            @PageableDefault(size = 10, sort = "createdAt", direction = Sort.Direction.DESC) Pageable pageable
    ) {
        return BaseResponse.onSuccess(challengeMissionVerificationQueryService.getVerificationPostsByDate(challengeId, date, pageable));
    }

    // 참여한 챌린지 날짜별 인증 사진 상세 조회
    // todo: 내가 참여하는 챌린지가 맞는 지 어노테이션으로 앞 단에서 검즘
    @GetMapping("/{verifyPostId}")
    @Operation(summary = "날짜 별 미션 인증 사진 상세 조회 API", description = "날짜 별 미션 미션 인증 사진 상세 조회")
    public BaseResponse<VerificationPostDetailResponse> getPhotoDetail(
            @CurrentMember Member member, @PathVariable Long challengeId, @PathVariable Long verifyPostId
    ) {
        return BaseResponse.onSuccess(challengeMissionVerificationQueryService.getVerificationPostDetail(verifyPostId));
    }

    // 미션 인증하기
    // todo: 내가 참여하는 챌린지가 맞는 지 어노테이션으로 앞 단에서 검즘
    @PostMapping("/{missionId}")
    @Operation(summary = "미션 인증 API", description = "챌린지에 대한 인증 사진을 업로드, 날짜는 서버에서 자동으로 처리")
    public BaseResponse<MissionVerificationIdRespinseDto> verifyMission(
            @PathVariable Long challengeId,
            @PathVariable Long missionId,
            @CurrentMember Member member,
            @RequestBody MissionVerificationRequestDto request
    ) {
        return BaseResponse.onSuccess(challengeMissionVerificationCommandService.postVerificationPost(member, challengeId, missionId, request));
    }

}
