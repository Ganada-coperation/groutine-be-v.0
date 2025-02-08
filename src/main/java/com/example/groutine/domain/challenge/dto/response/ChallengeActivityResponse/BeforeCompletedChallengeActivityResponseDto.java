package com.example.groutine.domain.challenge.dto.response.ChallengeActivityResponse;

import lombok.AllArgsConstructor;
import lombok.Builder;

@Builder
@AllArgsConstructor
public class BeforeCompletedChallengeActivityResponseDto extends ChallengeActivityResponseDto {
    private Long challengeId;
    private String challengeTitle;
    private String startDate;
    private String endDate;
    private String thumbnail;
    private int daysRemaining;
}
