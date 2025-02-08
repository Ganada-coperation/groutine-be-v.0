package com.example.groutine.domain.challenge.dto.response;

import com.example.groutine.domain.mission.entity.ChallengeMissionType;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
public class ChallengeDetailResponseDto {
    private Long challengeId;
    private Long writerId;
    private String title;
    private String thumbnail;
    private String startAt;
    private String endAt;
    private int daysRemaining;
    private int participantCount;
    private String challengeDescription;
    private List<ChallengeMissionResponseDto> challengeMissionList;

    @Builder
    public record ChallengeMissionResponseDto(
            Long missionId,
            String title,
            String verifyGuide,
            ChallengeMissionType challengeMissionType
    ) {
    }
}
