package com.example.groutine.domain.challenge.mapper;

import com.example.groutine.domain.challenge.dto.request.ChallengeRequestDto;
import com.example.groutine.domain.challenge.entity.Challenge;
import com.example.groutine.domain.member.entity.Member;
import org.springframework.stereotype.Component;

@Component
public class ChallengeMapper {

    public static Challenge toChallenge(Member member, ChallengeRequestDto request) {
        return Challenge.builder()
                .title(request.title())
                .description(request.description())
                .startDate(request.startDate())
                .endDate(request.endDate())
                .writer(member)
                .build();
    }

}
