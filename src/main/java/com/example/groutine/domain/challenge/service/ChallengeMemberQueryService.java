package com.example.groutine.domain.challenge.service;

import com.example.groutine.domain.challenge.dto.request.ChallengeStatus;
import com.example.groutine.domain.challenge.dto.response.ChallengeActivityResponse.ChallengeActivityResponseDto;
import com.example.groutine.domain.challenge.dto.response.ChallengeProgressListResponseDto;
import com.example.groutine.domain.challenge.dto.response.ChallengeRankingInfoResponseDto;
import com.example.groutine.domain.challenge.dto.response.ChallengeRankingListResponseDto;
import com.example.groutine.domain.member.entity.Member;
import com.example.groutine.domain.mission.repository.ChallengeMemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ChallengeMemberQueryService {

    private final ChallengeMemberRepository challengeMemberRepository;

    // 내 챌린지 활동 조회 (이전 참여한 챌린지, 현재 참여 중인 챌린지)
    public ChallengeActivityResponseDto getMyChallengeActivitieList(
            Member member,
            Pageable pageable,
            ChallengeStatus status
    ) {

        return ;
    }

    // 챌린지 실시간 랭킹 조회
    public ChallengeRankingListResponseDto getChallengeRankingList(
            Long challengeId
    ) {

        return ;
    }

    // 챌린지 실시간 랭킹 기타 정보 (내 랭킹, 달성한 사람 수 등등)
    public ChallengeRankingInfoResponseDto getChallengeRankingInfo(
            Member member,
            Long challengeId
    ) {

        return ;
    }

    // 챌린지 진행 상태 조회 (날짜 별, 완료 미완료)
    public ChallengeProgressListResponseDto getChallengeProgress(
            Member member,
            Long challengeId
    ) {

        return ;
    }


}
