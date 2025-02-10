package com.example.groutine.domain.challenge.service;

import com.example.groutine.domain.challenge.dto.request.ChallengeStatus;
import com.example.groutine.domain.challenge.dto.response.ChallengeActivityResponse.ChallengeActivityListResponseDto;
import com.example.groutine.domain.challenge.dto.response.ChallengeActivityResponse.ChallengeActivityResponseDto;
import com.example.groutine.domain.challenge.dto.response.ChallengeProgressListResponseDto;
import com.example.groutine.domain.challenge.dto.response.ChallengeRankingListResponseDto;
import com.example.groutine.domain.challenge.entity.Challenge;
import com.example.groutine.domain.challenge.entity.ChallengeMember;
import com.example.groutine.domain.challenge.mapper.ChallengeMemberMapper;
import com.example.groutine.domain.member.entity.Member;
import com.example.groutine.domain.challenge.repository.ChallengeMemberRepository;
import com.example.groutine.domain.mission.entity.ChallengeMissionType;
import com.example.groutine.domain.mission.entity.ChallengeMissionVerification;
import com.example.groutine.domain.mission.service.ChallengeMissionVerificationQueryService;
import com.example.groutine.domain.mission.status.ChallengeMissionErrorStatus;
import com.example.groutine.global.common.exception.RestApiException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ChallengeMemberQueryService {

    private final ChallengeQueryService challengeQueryService;
    private final ChallengeMissionVerificationQueryService challengeMissionVerificationQueryService;

    private final ChallengeMemberRepository challengeMemberRepository;

    // 내 챌린지 활동 조회 (이전 참여한 챌린지, 현재 참여 중인 챌린지)
    public ChallengeActivityListResponseDto getMyChallengeActivitieList(
            Member member,
            Pageable pageable,
            ChallengeStatus status
    ) {

        List<ChallengeActivityResponseDto> challengeActivityResponseDtoList = new ArrayList<>();

        // 현재 시간 기준으로 상태에 따라 챌린지 조회
        List<ChallengeMember> challengeMembers = switch (status) {
            case COMPLETED -> challengeMemberRepository.findCompletedChallenges(member, pageable);
            case IN_PROGRESS -> challengeMemberRepository.findInProgressChallenges(member, pageable);
            default -> throw new IllegalArgumentException("Invalid ChallengeStatus");
        };

        // 상태에 따라 응답 DTO 매핑 (맵퍼 사용)
        for (ChallengeMember challengeMember : challengeMembers) {
            Challenge challenge = challengeMember.getChallenge();
            // todo : 여기 나중에 좀 깔끔하게 변경하기
            if (status == ChallengeStatus.COMPLETED) {
                int participantCount = challenge.getChallengeMemberList().size();
                int myAchievementRate = calculateAchievementRate(challengeMember);
                challengeActivityResponseDtoList.add(
                        ChallengeMemberMapper.toCompletedChallengeActivityResponseDto(challenge, participantCount, myAchievementRate)
                );
            } else {
                challengeActivityResponseDtoList.add(
                        ChallengeMemberMapper.toBeforeCompletedChallengeActivityResponseDto(challenge)
                );
            }
        }

        // 최종 응답 반환
        return ChallengeActivityListResponseDto.builder()
                .challengeActivityResponseDtoList(challengeActivityResponseDtoList)
                .build();
    }

    // 챌린지 실시간 랭킹 조회 (내가 참여하는 챌린지가 맞는 지는 앞단에서 검증)
    public ChallengeRankingListResponseDto getChallengeRankingListRespone(
            Member member,
            Long challengeId
    ) {
        // 챌린지 조회
        Challenge challenge = challengeQueryService.findChallengeById(challengeId);

        // 챌린지 실시간 랭킹 조회
        List<ChallengeMember> challengeMembers = challengeMemberRepository.findByChallengeOrderByScoreDesc(challenge);

        // 리스트 변환 후 순위 계산
        List<ChallengeRankingListResponseDto.ChallengeRankingResponseDto> challengeRankingList = getChallengeRankingList(challengeMembers);

        // 내 정보 계산 (랭킹, 점수, 달성률)
        ChallengeRankingListResponseDto.ChallengeRankingResponseDto myRanking = findMyRanking(challengeRankingList, member.getId());
        int myAchievementRate = calculateAchievementRate(myRanking);

        // 총 참가자 수와 총 달성자 수 계산
        int totalParticipantCount = challengeMembers.size();
        int totalAchieverCount = (int) challengeMembers.stream().filter(cm -> cm.getScore() > 0).count(); // 예: 점수가 0 이상인 경우 달성자

        // 6. 최종 응답값 생성
        return ChallengeMemberMapper.toChallengeRankingListResponseDto(
                challengeRankingList,
                myRanking,
                myAchievementRate,
                totalParticipantCount,
                totalAchieverCount
        );
    }

    // 챌린지 진행 상태 조회 (날짜 별, 완료 미완료) (내가 참여하는 챌린지가 맞는 지는 앞단에서 검증)
    public ChallengeProgressListResponseDto getChallengeProgress(
            Member member,
            Long challengeId,
            Pageable pageable
    ) {

        // 챌린지 조회
        Challenge challenge = challengeQueryService.findChallengeById(challengeId);

        // 챌린지 멤버 조회
        ChallengeMember challengeMember = findChallengeMember(member, challenge);

        // 미션 인증 관련 조회 (필수인 미션만 조회)
        List<ChallengeMissionVerification> challengeMissionVerificationList
        = challengeMissionVerificationQueryService
                .getMyMissionVerificationList(challengeMember, pageable, ChallengeMissionType.REQUIRED);

        return ChallengeMemberMapper.toChallengeProgressListResponseDto(challengeMissionVerificationList);
    }

    // ChallengeMemeber 조회
    public ChallengeMember findChallengeMember(Member member, Challenge challenge) {
        return challengeMemberRepository.findByMemberAndChallenge(member, challenge)
                .orElseThrow(() -> new RestApiException(ChallengeMissionErrorStatus.NOT_PARTICIPATED_CHALLENGE));
    }

    // 순위 계산된 챌린지 랭킹을 응답값으로 반환
    private List<ChallengeRankingListResponseDto.ChallengeRankingResponseDto> getChallengeRankingList(
            List<ChallengeMember> challengeMembers
    ) {
        return calculateRanking(ChallengeMemberMapper.toChallengeRankingList(challengeMembers));
    }

    // 순위 계산 (동점자 처리를 위해)
    private List<ChallengeRankingListResponseDto.ChallengeRankingResponseDto> calculateRanking(
            List<ChallengeRankingListResponseDto.ChallengeRankingResponseDto> challengeRankingList
    ){
        List<ChallengeRankingListResponseDto.ChallengeRankingResponseDto> rankedList = new ArrayList<>();
        int rank = 1;
        int previousScore = -1;

        for (int i = 0; i < challengeRankingList.size(); i++) {
            ChallengeRankingListResponseDto.ChallengeRankingResponseDto current = challengeRankingList.get(i);

            if (current.score() != previousScore) {
                rank = i + 1;  // 새로운 점수일 때만 rank 갱신 (같은 점수는 같은 순위)
                previousScore = current.score();
            }

            // 순위 계산 후 응답값에 추가
            rankedList.add(new ChallengeRankingListResponseDto.ChallengeRankingResponseDto(
                    current.memberId(),
                    current.name(),
                    current.score(),
                    rank
            ));
        }

        return rankedList;
    }

    // 내 랭킹 정보 (랭킹, 점수) 조회
    private ChallengeRankingListResponseDto.ChallengeRankingResponseDto findMyRanking(
            List<ChallengeRankingListResponseDto.ChallengeRankingResponseDto> rankingList, Long memberId) {
        return rankingList.stream()
                .filter(ranking -> ranking.memberId().equals(memberId))
                .findFirst()
                .orElse(null);
    }

    // todo : 달성률 계산 (지금은 일단 간단하게 점수를 통해서 100점 만점 기준으로 가정)
    // todo: 핈수 챌린지 달성률 나중에 계산행 함
    private int calculateAchievementRate(ChallengeRankingListResponseDto.ChallengeRankingResponseDto myRanking) {
        if (myRanking == null || myRanking.score() == null) {
            return 0;
        }
        return Math.min(100, (myRanking.score() * 100) / 100); // 예: score를 100점 만점 기준으로 계산
    }

    // 달성률 계산 (100점 만점 기준 예시)
    private int calculateAchievementRate(ChallengeMember challengeMember) {
        int maxScore = 100; // 임의의 최대 점수
        return Math.min(100, (challengeMember.getScore() * 100) / maxScore);
    }

}
