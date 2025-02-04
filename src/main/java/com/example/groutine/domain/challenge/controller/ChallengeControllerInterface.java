package com.example.groutine.domain.challenge.controller;

import com.example.groutine.domain.challenge.dto.request.ChallengeRequestDto;
import com.example.groutine.domain.challenge.dto.response.ChallengeBasicResponseDto;
import com.example.groutine.domain.challenge.dto.response.ChallengeDetailResponseDto;
import com.example.groutine.domain.challenge.dto.response.ChallengeIdResponseDto;
import com.example.groutine.domain.challenge.dto.response.ChallengeListResponseDto;
import com.example.groutine.domain.member.entity.Member;
import com.example.groutine.global.common.base.BaseResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

@Tag(name = "챌린지 관련 API", description = "챌린지 API - 챌린지 정보 관련")
public interface ChallengeControllerInterface {

    @Operation(summary = "챌린지 리스트 조회 API", description = "메인 페이지의 챌린지 리스트를 조회")
    BaseResponse<ChallengeListResponseDto> getChallengeList(Pageable pageable);

    @Operation(summary = "챌린지 상세 조회 API", description = "챌린지 하나를 상세 조회")
    public BaseResponse<ChallengeDetailResponseDto> getChallengeDetail(Long challengeId);

    @Operation(summary = "챌린지 기본 정보 API", description = "모든 챌린지에 공통되는 정보 조회")
    public BaseResponse<ChallengeBasicResponseDto> getChallengeBasicInfo();

    @Operation(summary = "챌린지 참여 API", description = "유저가 새로운 챌린지를 등록")
    public BaseResponse<ChallengeIdResponseDto> joinChallenge(Member member, Long challengeId);

    @Operation(summary = "챌린지 만들기 API", description = "새로운 업장을 등록함")
    public BaseResponse<ChallengeIdResponseDto> createChallenge(Member member, ChallengeRequestDto request);

    @Operation(summary = "챌린지 수정하기 API", description = "새로운 업장을 등록함")
    public BaseResponse<ChallengeIdResponseDto> updateChallenge(@PathVariable Long challengeId, ChallengeRequestDto request);

    @Operation(summary = "챌린지 삭제 API", description = "새로운 업장을 등록함")
    public BaseResponse<ChallengeIdResponseDto> deleteChallenge(Long challengeId);

}
