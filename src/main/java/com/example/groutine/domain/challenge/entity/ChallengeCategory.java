package com.example.groutine.domain.challenge.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ChallengeCategory {
    COMMON("공통"),
    EXERCISE("운동"),
    DAILY("일상"),
    CULTURE("교양")
    ;

    private final String title;
}

