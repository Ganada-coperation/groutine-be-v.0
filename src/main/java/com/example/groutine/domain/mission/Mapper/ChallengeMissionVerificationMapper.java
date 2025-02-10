package com.example.groutine.domain.mission.Mapper;

import com.example.groutine.domain.mission.dto.response.VerificationPostDetailResponse;
import com.example.groutine.domain.mission.entity.ChallengeMissionVerification;

public class ChallengeMissionVerificationMapper {
    public static VerificationPostDetailResponse toVerificationPostDetailResponse(ChallengeMissionVerification verificationPost) {
        return VerificationPostDetailResponse.builder()
                // todo : 이 부분 최적화 시키기
                .memberId(verificationPost.getChallengeMember().getMember().getId())
                .verificationPostId(verificationPost.getId())
                .content(verificationPost.getContent())
                .imageUrl(verificationPost.getImageUrl())
                .build();
    }
}
