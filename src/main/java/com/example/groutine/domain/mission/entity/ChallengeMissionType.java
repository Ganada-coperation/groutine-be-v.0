package com.example.groutine.domain.mission.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ChallengeMissionType {
    REQUIRED("필수"),
    OPTIONAL("선택");

    private final String description;
}
