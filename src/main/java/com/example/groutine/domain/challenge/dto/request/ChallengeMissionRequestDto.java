package com.example.groutine.domain.challenge.dto.request;

import com.example.groutine.domain.mission.entity.ChallengeMissionType;

public record ChallengeMissionRequestDto(
    String title,
    String verifyGuide,
    ChallengeMissionType challengeMissionType
) {
}
