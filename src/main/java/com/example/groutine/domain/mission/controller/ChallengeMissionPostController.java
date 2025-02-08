package com.example.groutine.domain.mission.controller;

import com.example.groutine.domain.member.entity.Member;
import com.example.groutine.global.common.base.BaseResponse;
import com.example.groutine.global.config.security.auth.CurrentMember;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/challenge/{challengeId}/verify-posts")
@Tag(name = "챌린지 미션 인증 사진 관련 API", description = "참여한 챌린지의 미션 인증 사진을 다룸")
public class ChallengeMissionPostController {

    // 참여한 챌린지 날짜별 인증 사진 리스트 조회
    // todo: 내가 참여하는 챌린지가 맞는 지 어노테이션으로 앞 단에서 검즘
    @GetMapping("/{date}")
    @Operation(summary = "날짜 별 미션 인증 사진 리스트 조회 API", description = "날짜 별 미션 인증 사진 리스트 조회")
    public ResponseEntity<List<PhotoResponseDto>> getPhotosByDate(
            @PathVariable Long challengeId, @PathVariable LocalDate date, @CurrentMember Member member
    ) {
        return BaseResponse.onSuccess(challengeParticipationService.getPhotosByDate(member, challengeId, date));
    }

    // 참여한 챌린지 날짜별 인증 사진 상세 조회
    // todo: 내가 참여하는 챌린지가 맞는 지 어노테이션으로 앞 단에서 검즘
    @GetMapping("/{verifyPostId}")
    @Operation(summary = "날짜 별 미션 인증 사진 상세 조회 API", description = "날짜 별 미션 미션 인증 사진 상세 조회")
    public ResponseEntity<PhotoDetailResponseDto> getPhotoDetail(
            @PathVariable Long verifyPostId, @CurrentMember Member member
    ) {
        return BaseResponse.onSuccess(challengeParticipationService.getPhotoDetail(member, verifyPostId));
    }

}
