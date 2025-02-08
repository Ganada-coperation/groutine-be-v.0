package com.example.groutine.domain.challenge.dto.response.ChallengeActivityResponse;

import java.util.List;

public record ChallengeActivityListResponseDto(
        List<ChallengeActivityResponseDto> challengeActivityResponseDtoList
) {
}
