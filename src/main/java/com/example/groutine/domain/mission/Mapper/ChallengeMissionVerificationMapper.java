package com.example.groutine.domain.mission.Mapper;

import com.example.groutine.domain.mission.dto.response.VerificationPostDetailResponse;
import com.example.groutine.domain.mission.dto.response.VerificationPostListResponseDto;
import com.example.groutine.domain.mission.entity.ChallengeMissionVerification;

import java.util.List;

public class ChallengeMissionVerificationMapper {

    // 인증 사진 상세 조회 응답값으로 변환
    public static VerificationPostDetailResponse toVerificationPostDetailResponse(ChallengeMissionVerification verificationPost) {
        return VerificationPostDetailResponse.builder()
                // todo : 이 부분 최적화 시키기
                .memberId(verificationPost.getChallengeMember().getMember().getId())
                .verificationPostId(verificationPost.getId())
                .content(verificationPost.getContent())
                .imageUrl(verificationPost.getImageUrl())
                .build();
    }

    // 인증 사진 리스트 조회 응답값으로 변환
    public static VerificationPostListResponseDto toVerificationPostListResponseDto(List<ChallengeMissionVerification> verificationPosts) {
        return VerificationPostListResponseDto.builder()
                .verificationPostList(
                        verificationPosts.stream()
                                .map( ChallengeMissionVerification -> VerificationPostListResponseDto.VerificationPostResponseDto.builder()
                                        .verificationPostId(ChallengeMissionVerification.getId())
                                        .imageUrl(ChallengeMissionVerification.getImageUrl())
                                        .build()
                                ).toList()
                )
                .build();
    }
}
