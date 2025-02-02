package com.example.groutine.domain.challenge.dto.request;

import java.time.LocalDateTime;
import java.util.List;

public record ChallengeRequestDto(
        String title,
        String description, // 챌린지 설명
        String thumbnail, // 썸네일, 배너 이미지
        LocalDateTime startAt, // 챌린지 시작일
        LocalDateTime endAt, // 챌린지 종료일
        List<ChallengeMissionRequestDto> missions // 미션 리스트
) {
}
