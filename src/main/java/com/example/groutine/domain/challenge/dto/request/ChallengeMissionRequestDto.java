package com.example.groutine.domain.challenge.dto.request;

import com.example.groutine.domain.challenge.entity.MissionType;

public record ChallengeMissionRequestDto(
    String title,
    String verifyGuide,
    MissionType missionType
) {
}
