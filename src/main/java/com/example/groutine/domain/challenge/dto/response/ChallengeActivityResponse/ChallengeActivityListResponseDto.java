package com.example.groutine.domain.challenge.dto.response.ChallengeActivityResponse;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import org.hibernate.annotations.WhereJoinTable;

import java.util.List;

@Builder
@AllArgsConstructor
public class ChallengeActivityListResponseDto
{
    List<ChallengeActivityResponseDto> challengeActivityResponseDtoList;
}
