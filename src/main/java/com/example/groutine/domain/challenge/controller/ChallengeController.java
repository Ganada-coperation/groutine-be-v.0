package com.example.groutine.domain.challenge.controller;

import com.example.groutine.domain.challenge.dto.request.ChallengeRequestDto;
import com.example.groutine.domain.member.entity.Member;
import com.example.groutine.global.common.base.BaseResponse;
import com.example.groutine.global.config.security.auth.CurrentMember;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;


@Tag(name = "챌린지 관련 API", description = "챌린지 API - 챌린지 정보 관련")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/challenges")
public class ChallengeController {

    private final ChallengeService challengeService;

    @Operation(summary = "챌린지 리스트 조회 API", description = "메인 페이지의 챌린지 리스트를 조회")
    @GetMapping("")
    public BaseResponse<ChallengeListResponseDto> getChallengeList(
            @CurrentMember Member member,
            @PageableDefault(size = 10, sort = "createdAt", direction = Sort.Direction.DESC) Pageable pageable
    ) {
        return BaseResponse.onSuccess();
    }

    @Operation(summary = "챌린지 상세 조회 API", description = "챌린지 하나를 상세 조회")
    @GetMapping("/{challengeId}")
    public BaseResponse<ChallengeDetailResponseDto> getChallengeDetail(
            @PathVariable Long challengeId
    ) {
        return BaseResponse.onSuccess();
    }

    @Operation(summary = "챌린지 참여 API", description = "유저가 새로운 챌린지를 등록")
    @PostMapping("/{challengeId}")
    public BaseResponse<ChallengeIdResponseDto> joinChallenge(
            @CurrentMember Member member,
            @PathVariable Long challengeId
    ) {
        return BaseResponse.onSuccess();
    }

    @Operation(summary = "챌린지 만들기 API", description = "새로운 업장을 등록함")
    @PostMapping("")
    public BaseResponse<ChallengeIdResponseDto> createChallenge(
            @CurrentMember Member member,
            @RequestBody ChallengeRequestDto request
    ) {
        return BaseResponse.onSuccess();
    }

    @Operation(summary = "챌린지 수정하기 API", description = "새로운 업장을 등록함")
    @PatchMapping("/{challengeId}")
    public BaseResponse<ChallengeIdResponseDto> updateChallenge(
            @PathVariable Long challengeId,
            @RequestBody ChallengeRequestDto request
    ) {
        return BaseResponse.onSuccess();
    }

    @Operation(summary = "챌린지 삭제 API", description = "새로운 업장을 등록함")
    @DeleteMapping("/{challengeId}")
    public BaseResponse<ChallengeIdResponseDto> deleteChallenge(
            @PathVariable Long challengeId
    ) {
        return BaseResponse.onSuccess();
    }


}
