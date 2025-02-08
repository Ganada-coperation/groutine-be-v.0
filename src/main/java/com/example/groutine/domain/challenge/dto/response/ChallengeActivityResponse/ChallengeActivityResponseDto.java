package com.example.groutine.domain.challenge.dto.response.ChallengeActivityResponse;

// 공통된 부분 todo: ChallengeListReponse랑도 합치기
// 해당 레코드 상속 받아서 사용
public record ChallengeActivityResponseDto(
        Long challengeId,
        String challengeTitle,
        String startDate,
        String endDate,
        String thumbnail
) {
}
