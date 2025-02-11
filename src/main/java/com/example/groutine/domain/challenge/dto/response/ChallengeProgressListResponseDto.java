package com.example.groutine.domain.challenge.dto.response;

import com.example.groutine.domain.mission.entity.VerifiyStatus;
import lombok.Builder;

import java.time.LocalDate;
import java.util.List;

@Builder
public record ChallengeProgressListResponseDto(
    List<ChallengeProgressResponseDto> challengeProgressList
) {
    @Builder
    public record ChallengeProgressResponseDto(
            LocalDate date,
            VerifiyStatus verifiyStatus
    ) {
    }
}
