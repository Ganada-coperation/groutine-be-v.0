package com.example.groutine.domain.challenge.dto.response.ChallengeActivityResponse;

import lombok.AllArgsConstructor;
import lombok.Builder;

@Builder
@AllArgsConstructor
public class BeforeCompletedChallengeActivityResponseDto extends ChallengeActivityResponseDto {
    private int daysRemaining;
}
