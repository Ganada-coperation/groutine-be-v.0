package com.example.groutine.domain.challenge.controller;

import com.example.groutine.domain.challenge.dto.request.*;
import com.example.groutine.domain.challenge.dto.response.*;
import com.example.groutine.domain.challenge.service.ChallengeCommandService;
import com.example.groutine.domain.challenge.service.ChallengeQueryService;
import com.example.groutine.domain.member.entity.Member;
import com.example.groutine.domain.participation.service.ChallengeParticipationService;
import com.example.groutine.global.common.base.BaseResponse;
import com.example.groutine.global.config.security.auth.CurrentMember;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

//챌린지 관련 컨트롤러
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/challenges")
public class ChallengeController implements ChallengeControllerInterface{

    private final ChallengeQueryService challengeQueryService;
    private final ChallengeCommandService challengeCommandService;

    // 챌린지 리스트 조회 API
    @Override
    @GetMapping("")
    public BaseResponse<ChallengeListResponseDto> getChallengeList(
            @PageableDefault(size = 10, sort = "createdAt", direction = Sort.Direction.DESC) Pageable pageable
    ) {
        return BaseResponse.onSuccess(
            challengeQueryService.getChallengeList(pageable)
        );
    }

    // 챌린지 상세 조회 API
    @Override
    @GetMapping("/{challengeId}")
    public BaseResponse<ChallengeDetailResponseDto> getChallengeDetail(
            @PathVariable Long challengeId
    ) {
        return BaseResponse.onSuccess(
            challengeQueryService.getChallengeDetail(challengeId)
        );
    }

    //챌린지 기본 정보 조회 (모든 챌린지가 동일)
    @Override
    @GetMapping("/basic-info")
    public BaseResponse<ChallengeBasicResponseDto> getChallengeBasicInfo(
    ) {
        return BaseResponse.onSuccess(
            challengeQueryService.getChallengeBasic()
        );
    }

    // 챌린지 만들기 API
    @Override
    @PostMapping("")
    public BaseResponse<ChallengeIdResponseDto> createChallenge(
            @CurrentMember Member member,
            @RequestBody ChallengeRequestDto request
    ) {
        return BaseResponse.onSuccess(
            challengeCommandService.createChallenge(member, request)
        );
    }

    // 챌린지 수정하기 API
    // todo: 내가 만든 챌린지가 맞는 지, 어노테이션으로 검증
    @Override
    @PatchMapping("/{challengeId}")
    public BaseResponse<ChallengeIdResponseDto> updateChallenge(
            @PathVariable Long challengeId,
            @RequestBody ChallengeRequestDto request
    ) {
        return BaseResponse.onSuccess(
            challengeCommandService.updateChallenge(challengeId, request)
        );
    }

    // 챌린지 삭제 API
    // todo: 내가 만든 챌린지가 맞는 지, 어노테이션으로 검증
    @Override
    @Operation(summary = "챌린지 삭제 API", description = "새로운 업장을 등록함")
    @DeleteMapping("/{challengeId}")
    public BaseResponse<ChallengeIdResponseDto> deleteChallenge(
            @PathVariable Long challengeId
    ) {
        return BaseResponse.onSuccess(
            challengeCommandService.deleteChallenge(challengeId)
        );
    }


}
