package com.example.groutine.domain.mission.Mapper;

import com.example.groutine.domain.mission.dto.response.MissionListResponseDto;
import com.example.groutine.domain.mission.entity.ChallengeMission;
import com.example.groutine.domain.mission.entity.ChallengeMissionType;
import com.example.groutine.domain.mission.entity.ChallengeMissionVerification;

import java.util.List;

public class ChallengeMissionMapper {

    // 미션 인증 엔티티와 미션을 응답값으로 변환하는 메서드
    public static MissionListResponseDto toMissionListResponseDto(
            List<ChallengeMission> challengeMissions,
            List<ChallengeMissionVerification> todayVerifications) {

        return MissionListResponseDto.builder()
                .RequiredMissionList(toMissionResponseDtoList(challengeMissions, todayVerifications, ChallengeMissionType.REQUIRED))
                .OptionalMissionList(toMissionResponseDtoList(challengeMissions, todayVerifications, ChallengeMissionType.OPTIONAL))
                .build();
    }

    // 필수 및 선택 미션 리스트 변환 메서드
    public static List<MissionListResponseDto.MissionResponseDto> toMissionResponseDtoList(
            List<ChallengeMission> challengeMissions,
            List<ChallengeMissionVerification> todayVerifications,
            ChallengeMissionType missionType) {

        return challengeMissions.stream()
                .filter(mission -> mission.getChallengeMissionType() == missionType)
                .map(mission -> toMissionResponseDto(mission, todayVerifications))
                .toList();
    }

    // 단일 MissionResponseDto 변환 메서드
    private static MissionListResponseDto.MissionResponseDto toMissionResponseDto(
            ChallengeMission mission,
            List<ChallengeMissionVerification> todayVerifications) {

        boolean completed = todayVerifications.stream()
                .anyMatch(verification -> verification.getChallengeMission().equals(mission));

        return new MissionListResponseDto.MissionResponseDto(mission.getId(), mission.getTitle(), completed);
    }
}
