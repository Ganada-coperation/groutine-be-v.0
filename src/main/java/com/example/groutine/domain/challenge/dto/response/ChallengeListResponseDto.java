package com.example.groutine.domain.challenge.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
@AllArgsConstructor
public class ChallengeListResponseDto {

    private List<ChallengeResponseDto> challengeList;

    @Builder
    public record ChallengeResponseDto(
            Long challengeId,
            String title,
            String thumbnail,
            String startDate,
            String endDate,
            int daysRemaining,
            int participantCount
    ) {
    }
}
