package com.example.groutine.domain.challenge.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
public class ChallengeListResponseDto {

    private List<ChallengeResponseDto> challengeList;

    public record ChallengeResponseDto(
            Long challengeId,
            String title,
            String thumbnail,
            String startAt,
            String endAt,
            int daysRemaining,
            int participantCount
    ) {
    }
}
