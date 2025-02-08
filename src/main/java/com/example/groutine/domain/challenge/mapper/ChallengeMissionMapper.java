package com.example.groutine.domain.challenge.mapper;

import com.example.groutine.domain.challenge.dto.request.ChallengeMissionRequestDto;
import com.example.groutine.domain.challenge.entity.Challenge;
import com.example.groutine.domain.participation.entity.ChallengeMission;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ChallengeMissionMapper {

    public static List<ChallengeMission> toChallengeMissionList(
            Challenge challenge, List<ChallengeMissionRequestDto> missionList
    ) {
        return missionList.stream()
                .map(mission -> ChallengeMission.builder()
                        .challenge(challenge)
                        .title(mission.title())
                        .verifyGuide(mission.verifyGuide())
                        .challengeMissionType(mission.challengeMissionType())
                        .build()
                )
                .toList();
    }

}
