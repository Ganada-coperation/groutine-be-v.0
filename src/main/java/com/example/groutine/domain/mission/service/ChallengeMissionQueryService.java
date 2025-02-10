package com.example.groutine.domain.mission.service;

import com.example.groutine.domain.challenge.entity.Challenge;
import com.example.groutine.domain.challenge.entity.ChallengeMember;
import com.example.groutine.domain.challenge.service.ChallengeMemberQueryService;
import com.example.groutine.domain.challenge.service.ChallengeQueryService;
import com.example.groutine.domain.member.entity.Member;
import com.example.groutine.domain.mission.Mapper.ChallengeMissionMapper;
import com.example.groutine.domain.mission.dto.response.MissionListResponseDto;
import com.example.groutine.domain.mission.entity.ChallengeMission;
import com.example.groutine.domain.mission.entity.ChallengeMissionVerification;
import com.example.groutine.domain.mission.repository.ChallengeMissionRepository;
import com.example.groutine.domain.mission.repository.ChallengeMissionVerificationRepository;
import com.example.groutine.domain.mission.status.ChallengeMissionErrorStatus;
import com.example.groutine.global.common.exception.RestApiException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ChallengeMissionQueryService {

    private final ChallengeMissionRepository challengeMissionRepository;

    private final ChallengeQueryService challengeQueryService;
    private final ChallengeMemberQueryService challengeMemberQueryService;

    // 미션 찾기 (미션이 존재하지 않으면 예외 발생)
    public ChallengeMission findChallengeMission(Long missionId){
        return challengeMissionRepository.findById(missionId)
                .orElseThrow(() -> new RestApiException(ChallengeMissionErrorStatus.MISSION_NOT_FOUND));
    }

    // 오늘의 미션 조회 (없으면 없는 대로 반환) TODO:모듈화 시키기
    public MissionListResponseDto getMissionsByDate(Member member){
        // 지금 시간
        LocalDateTime now = LocalDateTime.now();

        // 챌린지 멤버 찾기
        List<ChallengeMember> challengeMembers = member.getChallengeMemberList();

        // 챌린지 찾기
        List<Challenge> challenges = challengeMembers.stream()
                .map(ChallengeMember::getChallenge)
                .toList();

        // 아직 끝나지 않은 챌린지에 대한 모든 미션 가져오기
        List<ChallengeMission> challengeMissions = challenges.stream()
                .filter(challenge -> challenge.getEndDate().isAfter(now))
                .map(Challenge::getChallengeMissionList)
                .flatMap(List::stream)
                .toList();


        // 오늘 날짜에 대한 내 미션 인증 엔티티 찾기

        // 오늘 날짜의 00:00:00
        LocalDateTime startOfToday = now.toLocalDate().atStartOfDay();

        // 오늘 날짜의 23:59:59.999999 (마지막 순간)
        LocalDateTime endOfToday = now.truncatedTo(ChronoUnit.DAYS).plusDays(1).minusNanos(1);


        List<ChallengeMissionVerification> challengeMissionVerifications = challengeMembers.stream()
                .map(ChallengeMember::getChallengeMissionVerificationList)
                .flatMap(List::stream)
                .filter(challengeMissionVerification -> {
                    LocalDateTime verificationDate = challengeMissionVerification.getCreatedAt();
                    return verificationDate.isAfter(startOfToday) && verificationDate.isBefore(endOfToday);
                })
                .toList();

        // 미션 인증 엔티티와 미션을 응답값으로 변환 (해당 날짜에 미션 인증 엔티티가 존재하면 미션 인증을 한 것)
        return ChallengeMissionMapper.toMissionListResponseDto(challengeMissions, challengeMissionVerifications);
    }

    // 챌린지에 대한 미션 조회 TODO:모듈화 시키기
    public MissionListResponseDto getMissionsByDate(
            Member member, Long challengeId, LocalDateTime date
    ){
        // 챌린지 찾기
        Challenge challenge = challengeQueryService.findChallengeById(challengeId);

        // 아직 끝나지 않은 챌린지에 대한 모든 미션 가져오기
        if(challenge.getEndDate().isBefore(date)){
            throw new RestApiException(ChallengeMissionErrorStatus.CHALLENGE_NOT_STARTED);
        }
        //다 끝난 날짜 이후를 조회하려 하면
        if(challenge.getStartDate().isAfter(date)){
            throw new RestApiException(ChallengeMissionErrorStatus.CHALLENGE_FINISHED);
        }
        List<ChallengeMission> challengeMissions = challenge.getChallengeMissionList();

        // 챌린지 멤버 찾기
        ChallengeMember challengeMember = challengeMemberQueryService.findChallengeMember(member, challenge);

        // 오늘 날짜에 대한 내 미션 인증 엔티티 찾기

        // 오늘 날짜의 00:00:00
        LocalDateTime startOfToday = date.toLocalDate().atStartOfDay();

        // 오늘 날짜의 23:59:59.999999 (마지막 순간)
        LocalDateTime endOfToday = date.truncatedTo(ChronoUnit.DAYS).plusDays(1).minusNanos(1);

        // 날짜에 맞는 미션 인증 엔티티 찾기
        List<ChallengeMissionVerification> challengeMissionVerifications = challengeMember.getChallengeMissionVerificationList().stream()
                .filter(verification -> {
                    LocalDateTime verificationDate = verification.getCreatedAt();
                    return !verificationDate.isBefore(startOfToday) && !verificationDate.isAfter(endOfToday);
                })
                .toList();

        // 미션 인증 엔티티와 미션을 응답값으로 변환 (해당 날짜에 미션 인증 엔티티가 존재하면 미션 인증을 한 것)
        return ChallengeMissionMapper.toMissionListResponseDto(challengeMissions, challengeMissionVerifications);

    }

    //

}
