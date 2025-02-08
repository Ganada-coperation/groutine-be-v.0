package com.example.groutine.domain.challenge.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ChallengeStatus {
    // 챌린지 상태, 진행중, 완료, 시작 전
    IN_PROGRESS("진행중인 챌린지"), COMPLETED("완료한 챌린지"), NOT_STARTED("시작 전인 챌린지");

    private final String description;
}
