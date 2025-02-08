package com.example.groutine.domain.challenge.dto.response;

import java.time.LocalDate;
import java.util.List;

public record ChallengeProgressListResponseDto(
    List<ChallengeProgressResponseDto> challengeProgressList
) {
    public record ChallengeProgressResponseDto(
            LocalDate date,
            Boolean completedStatus
    ) {
    }
}
