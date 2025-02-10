package com.example.groutine.domain.mission.dto.response;

import lombok.Builder;

import java.util.List;

@Builder
public class MissionListResponseDto {

    private List<MissionResponseDto> RequiredMissionList;
    private List<MissionResponseDto> OptionalMissionList;


    @Builder
    public record MissionResponseDto(
            Long missionId,
            String title,
            boolean completedStatus
    ) {
    }
}
