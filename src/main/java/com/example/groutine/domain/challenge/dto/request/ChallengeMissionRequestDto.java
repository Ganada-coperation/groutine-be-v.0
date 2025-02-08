package com.example.groutine.domain.challenge.dto.request;

import com.example.groutine.domain.participation.entity.ChallengeMissionType;

public record ChallengeMissionRequestDto(
    String title,
    String verifyGuide,
    ChallengeMissionType challengeMissionType
) {
}
