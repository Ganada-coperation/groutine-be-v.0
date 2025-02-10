package com.example.groutine.domain.challenge.controller;

import com.example.groutine.domain.challenge.dto.request.ChallengeRequestDto;
import com.example.groutine.domain.challenge.dto.response.*;
import com.example.groutine.domain.challenge.service.ChallengeCommandService;
import com.example.groutine.domain.challenge.service.ChallengeMemberCommendService;
import com.example.groutine.domain.challenge.service.ChallengeQueryService;
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

//챌린지 관련 컨트롤러
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/challenges")
@Tag(name = "챌린지 관련 API", description = "챌린지 API - 챌린지 정보 관련")
public class ChallengeController{

    private final ChallengeQueryService challengeQueryService;
    private final ChallengeCommandService challengeCommandService;
    private final ChallengeMemberCommendService challengeMemberCommendService;

    // 챌린지 리스트 조회 API
    @GetMapping("")
    @Operation(summary = "챌린지 리스트 조회 API", description = "메인 페이지의 챌린지 리스트를 조회")
    public BaseResponse<ChallengeListResponseDto> getChallengeList(
            @PageableDefault(size = 10, sort = "createdAt", direction = Sort.Direction.DESC) Pageable pageable
    ) {
        return BaseResponse.onSuccess(
            challengeQueryService.getChallengeList(pageable)
        );
    }

    // 챌린지 상세 조회 API
    @GetMapping("/{challengeId}")
    @Operation(summary = "챌린지 상세 조회 API", description = "챌린지 하나를 상세 조회")
    public BaseResponse<ChallengeDetailResponseDto> getChallengeDetail(
            @PathVariable Long challengeId
    ) {
        return BaseResponse.onSuccess(
            challengeQueryService.getChallengeDetail(challengeId)
        );
    }

    //챌린지 기본 정보 조회 (모든 챌린지가 동일)
    @GetMapping("/basic-info")
    @Operation(summary = "챌린지 기본 정보 API", description = "모든 챌린지에 공통되는 정보 조회")
    public BaseResponse<ChallengeBasicResponseDto> getChallengeBasicInfo(
    ) {
        return BaseResponse.onSuccess(
            challengeQueryService.getChallengeBasic()
        );
    }

    // 챌린지 만들기 API
    // todo: 미션까지 한방에 받을 껀지, 미션 저장 나눌지 생각 -> 나누는 게 좋을 듯 ㅇㅅㅇ
    @PostMapping("")
    @Operation(summary = "챌린지 만들기 API", description = "챌린지 만들기")
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
    @PatchMapping("/{challengeId}")
    @Operation(summary = "챌린지 수정하기 API", description = "챌린지를 수정함 내가 만든 챌린지만 수정 가능")
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
    @Operation(summary = "챌린지 삭제 API", description = "챌린지를 삭제함, 내가 ㅁ나든 챌린지만 삭제 가능")
    @DeleteMapping("/{challengeId}")
    public BaseResponse<ChallengeIdResponseDto> deleteChallenge(
            @PathVariable Long challengeId
    ) {
        return BaseResponse.onSuccess(
            challengeCommandService.deleteChallenge(challengeId)
        );
    }

    // 챌린지 참여 API
    @PostMapping("/{challengeId}")
    @Operation(summary = "챌린지 참여 API", description = "챌린지 참여")
    public BaseResponse<ChallengeIdResponseDto> joinChallenge(
            @CurrentMember Member member,
            @PathVariable Long challengeId
    ) {
        return BaseResponse.onSuccess( // todo: 어떤 응답 값을 줄지 고민 ㄱㄱ
                challengeMemberCommendService.joinChallenge(member, challengeId)
        );
    }

    // 챌린지 참여 취소 API
    @DeleteMapping("/{challengeId}")
    @Operation(summary = "챌린지 참여 취소 API", description = "챌린지 참여 취소")
    public BaseResponse<ChallengeIdResponseDto> joinCancelChallenge(
            @CurrentMember Member member,
            @PathVariable Long challengeId
    ) {
        return BaseResponse.onSuccess( // todo: 어떤 응답 값을 줄지 고민 ㄱㄱ
                challengeMemberCommendService.joinCancelChallenge(member, challengeId)
        );
    }

}
