package com.example.groutine.domain.challenge.service;

import com.example.groutine.domain.challenge.dto.request.ChallengeRequestDto;
import com.example.groutine.domain.challenge.dto.response.ChallengeIdResponseDto;
import com.example.groutine.domain.challenge.entity.Challenge;
import com.example.groutine.domain.challenge.entity.ChallengeMember;
import com.example.groutine.domain.participation.entity.ChallengeMission;
import com.example.groutine.domain.challenge.mapper.ChallengeMapper;
import com.example.groutine.domain.challenge.mapper.ChallengeMissionMapper;
import com.example.groutine.domain.challenge.repository.ChallengeRepository;
import com.example.groutine.domain.member.entity.Member;
import com.example.groutine.domain.participation.repository.ChallengeMemberRepository;
import com.example.groutine.domain.participation.status.ChallengeParticipationErrorStatus;
import com.example.groutine.global.common.exception.RestApiException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class ChallengeCommandService {

    private final ChallengeQueryService challengeQueryService;
    private final ChallengeRepository challengeRepository;
    private final ChallengeMemberRepository challengeMemberRepository;

    // 챌린지 생성
    public ChallengeIdResponseDto createChallenge(Member member, ChallengeRequestDto request) {

        // 챌린지 생성 (꼭 필요한 값이 안오면 예외처리 하기)
        Challenge challenge = ChallengeMapper.toChallenge(member, request);

        // ChallengeMissionRequestDto를 이용하여 챌린지 미션 생성
        List<ChallengeMission> challengeMissionList = ChallengeMissionMapper.toChallengeMissionList(challenge, request.missionList());

        // 챌린지와 미션 연결
        challengeMissionList.forEach(challenge::addChallengeMission);

        // todo: 유저가 챌린지를 생성할 수 있다면 생성한 유저는 바로 챌린지 참여할 수 있도록

        // 챌린지 저장
        challengeRepository.save(challenge);

        // 챌린지 아이디 반환
        return new ChallengeIdResponseDto(challenge.getId());
    }

    // 챌린지 수정 (유저가 만든 챌린지가 맞는 지는 앞단에서 검증됨)
    // todo:  챌린지 미션 수정은 따로 만들어야 함 (기존 미션 있으면 그대로, 추가하면 추가, 없어지면 삭제)
    public ChallengeIdResponseDto updateChallenge(Long challengeId, ChallengeRequestDto request) {

        // 챌린지 찾기
        Challenge challenge = challengeQueryService.findChallengeById(challengeId);

        // 챌린지 수정
        challenge.updateChallenge(request);

        // 챌린지 아이디 반환
        return new ChallengeIdResponseDto(challenge.getId());
    }

    // 챌린지 삭제 (유저가 만든 챌린지가 맞는 지는 앞단에서 검증됨)
    public ChallengeIdResponseDto deleteChallenge(Long challengeId) {

        // 챌린지 찾기
        Challenge challenge = challengeQueryService.findChallengeById(challengeId);

        // 챌린지 삭제
        challengeRepository.delete(challenge);

        // 챌린지 아이디 반환
        return new ChallengeIdResponseDto(challenge.getId());
    }

    // 챌린지 참여
    public ChallengeIdResponseDto joinChallenge(Member member, Long challengeId) {

        // 챌린지 조회
        Challenge challenge = challengeQueryService.findChallengeById(challengeId);

        // 멤버가 참여하지 않은 챌린지가 맞는 지 확인
        if (challengeMemberRepository.existsByMemberAndChallenge(member, challenge)) {
            throw new RestApiException(ChallengeParticipationErrorStatus.ALREADY_PARTICIPATED_CHALLENGE);
        }

        challengeMemberRepository.save(
                ChallengeMember.builder()
                        .member(member)
                        .challenge(challenge)
                        .build()
        );

        return new ChallengeIdResponseDto(challenge.getId()); // todo: 어떤 응답 값을 줄지 고민 ㄱㄱ
    }

    // 챌린지 참여 취소
    public ChallengeIdResponseDto joinCancelChallenge(Member member, Long challengeId) {

        // 챌린지 조회
        Challenge challenge = challengeQueryService.findChallengeById(challengeId);

        // 멤버가 참여하고 있는 챌린지가 맞는지 확인
        ChallengeMember challengeMember = challengeMemberRepository
                .findByMemberAndChallenge(member, challenge)
                .orElseThrow(() -> new RestApiException(ChallengeParticipationErrorStatus.NOT_PARTICIPATED_CHALLENGE));

        return new ChallengeIdResponseDto(challengeMember.getId()); // todo: 어떤 응답 값을 줄지 고민 ㄱㄱ
    }
}
