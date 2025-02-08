package com.example.groutine.domain.challenge.controller;

import com.example.groutine.domain.challenge.dto.request.ChallengeStatus;
import com.example.groutine.domain.challenge.dto.response.ChallengeActivityResponse.ChallengeActivityResponseDto;
import com.example.groutine.domain.challenge.dto.response.ChallengeProgressResponseDto;
import com.example.groutine.domain.challenge.dto.response.ChallengeRankingInfoResponseDto;
import com.example.groutine.domain.challenge.dto.response.ChallengeRankingListResponseDto;
import com.example.groutine.domain.challenge.service.ChallengeMemberQueryService;
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

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/challenge/activities")
@Tag(name = "챌린지 활동 관련 API", description = "챌린지 참여 현황 및 활동을 다룸")
public class ChallengeActivityController {

    private final ChallengeMemberQueryService challengeMemberQueryService;

    // 내 챌린지 활동 조회 (이전 참여한 챌린지, 현재 참여 중인 챌린지)
    @GetMapping("")
    @Operation(summary = "내 챌린지 활동 내역 조회 API", description = "진행중, 종료, 앞으로 시작할 챌린지 조회")
    public BaseResponse<ChallengeActivityResponseDto> getMyChallengeActivities(
            @CurrentMember Member member,
            @PageableDefault(size = 10, sort = "createdAt", direction = Sort.Direction.DESC) Pageable pageable,
            @RequestParam(required = false) ChallengeStatus status // todo: 진행중, 챌린지 종료, 앞으로 시작할 챌린지
    ) {
        return BaseResponse.onSuccess(challengeMemberQueryService.getMyChallengeActivitieList(member, pageable, status));
    }

    // 참여한 챌린지 실시간 랭킹 조회
    // todo : 캐싱해야 함
    // todo : 내가 참여하는 챌린지가 맞는 지 어노테이션으로 앞 단에서 검즘
    @GetMapping("/{challengeId}/ranking")
    @Operation(summary = "챌린지 랭킹 조회 API", description = "하나에 챌린지에 대한 랭킹 정보 조회, 하루 마다 갱신 되는 값")
    public BaseResponse<ChallengeRankingListResponseDto> getChallengeRankingList(
            @CurrentMember Member member,
            @PathVariable Long challengeId
    ) {
        return BaseResponse.onSuccess(challengeMemberQueryService.getChallengeRankingList(member, challengeId));
    }

    // 참여한 챌린지 실시간 랭킹 조회
    // todo : 캐싱해야 함
    // todo : 내가 참여하는 챌린지가 맞는 지 어노테이션으로 앞 단에서 검즘
    @GetMapping("/{challengeId}/ranking-info")
    @Operation(summary = "챌린지 랭킹 기타 정보 조회 API", description = "챌린지 랭킹 기타 정보 조회 (내 랭킹, 달성한 사람 수 등등)")
    public BaseResponse<ChallengeRankingInfoResponseDto> getChallengeRankingInfo(
            @CurrentMember Member member,
            @PathVariable Long challengeId
    ) {
        return BaseResponse.onSuccess(challengeMemberQueryService.getChallengeRankingInfo(member, challengeId));
    }

    // 챌린지 진행 상황 조회
    // todo: 캐싱 고려 (많이 변경되지 않는 데이터 같은데, 나만 조회에서 성능 이점이 있을지는 모르겠음)
    // todo: 내가 참여하는 챌린지가 맞는 지 어노테이션으로 앞 단에서 검즘
    @GetMapping("/{challengeId}/progress")
    @Operation(summary = "챌린지 진행 상황 API", description = "챌린지에 대한 진행 상황 조회, 페이지 값만 쿼리로 넘겨줘도(?page=0) 알아서 잘 동작함 ")
    public BaseResponse<ChallengeProgressResponseDto> getChallengeProgress(
            @CurrentMember Member member,
            @PathVariable Long challengeId,
            @PageableDefault(size = 15, sort = "createdAt", direction = Sort.Direction.DESC) Pageable pageable
    ) {
        return BaseResponse.onSuccess(challengeMemberQueryService.getChallengeProgress(member, challengeId));
    }
}
