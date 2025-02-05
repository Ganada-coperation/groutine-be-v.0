package com.example.groutine.domain.challenge.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ChallengeBasicInfo {
    CHALLENGE_INFO_V1("순위권 도전 미션을 통해 챌린지의 순위가 부여됩니다.\n" +
            "매일 매일 순위를 확인하면 자신의 순위를 올려보세요!\n" +
            "\n" +
            "필수 미션을 95% 달성 시 포인트가 지급되며 순위권 도전 미션을 통해 순위를 올릴 수 있습니다! 포인트는 마지막 순위에 따라 차등 지급되며, 포인트로 랜덤 선물을 교환 가능합니다!"),
    ;

    private final String description;
}
