package com.example.groutine.domain.challenge.dto.response.ChallengeActivityResponse;

import lombok.AllArgsConstructor;
import lombok.Builder;

@Builder
@AllArgsConstructor
public class CompletedChallengeActivityResponseDto extends ChallengeActivityResponseDto{
    private int participantCount;
    private int myAchievementRate;
}
