package com.example.groutine.domain.participation.controller;

import com.example.groutine.domain.challenge.dto.response.ChallengeIdResponseDto;
import com.example.groutine.domain.member.entity.Member;
import com.example.groutine.domain.participation.service.ChallengeParticipationService;
import com.example.groutine.global.common.base.BaseResponse;
import com.example.groutine.global.config.security.auth.CurrentMember;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/challenges")
@Tag(name = "챌린지 참여 API", description = "챌린지 참여 API")
public class ChallengeParticipationController {

    private final ChallengeParticipationService challengeParticipationService;

    // 챌린지 참여 API
    @PostMapping("/{challengeId}/participations")
    @Operation(summary = "챌린지 참여 API", description = "챌린지 참여")
    public BaseResponse<ChallengeIdResponseDto> joinChallenge(
            @CurrentMember Member member,
            @PathVariable Long challengeId
    ) {
        return BaseResponse.onSuccess(
                challengeParticipationService.joinChallenge(member, challengeId)
        );
    }

    // 챌린지 참여 취소 API
    @DeleteMapping("/{challengeId}/participations")
    @Operation(summary = "챌린지 참여 취소 API", description = "챌린지 참여 취소")
    public BaseResponse<ChallengeIdResponseDto> joinCancelChallenge(
            @CurrentMember Member member,
            @PathVariable Long challengeId
    ) {
        return BaseResponse.onSuccess(
                challengeParticipationService.joinCancelChallenge(member, challengeId)
        );
    }
}
