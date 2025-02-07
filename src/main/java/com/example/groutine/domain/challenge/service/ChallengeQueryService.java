package com.example.groutine.domain.challenge.service;

import com.example.groutine.domain.challenge.dto.response.ChallengeBasicResponseDto;
import com.example.groutine.domain.challenge.dto.response.ChallengeDetailResponseDto;
import com.example.groutine.domain.challenge.dto.response.ChallengeListResponseDto;
import com.example.groutine.domain.challenge.entity.Challenge;
import com.example.groutine.domain.challenge.entity.ChallengeBasicInfo;
import com.example.groutine.domain.challenge.mapper.ChallengeMapper;
import com.example.groutine.domain.challenge.repository.ChallengeRepository;
import com.example.groutine.domain.challenge.status.ChallengeErrorStatus;
import com.example.groutine.global.common.exception.RestApiException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ChallengeQueryService {

    private final ChallengeRepository challengeRepository;

    // 챌린지 리스트 조회
    public ChallengeListResponseDto getChallengeList(Pageable pageable) {

        // 챌린지 리스트 조회
        List<Challenge> challengeList = challengeRepository.findAll(pageable).getContent();

        // 챌린지 리스트를 응답값으로 반환
        return ChallengeMapper.toChallengeListResponseDto(challengeList);
    }

    // 챌린지 상세 정보 조회
    public ChallengeDetailResponseDto getChallengeDetail(Long challengeId) {

        // 챌린지 조회
        Challenge challenge = findChallengeById(challengeId);

        // 챌린지 상세 정보를 응답값으로 반환
        return ChallengeMapper.toChallengeDetailResponseDto(challenge);
    }

    // 챌린지 기본 정보 조회 (모든 챌린지에 동일하게 있는 정보)
    public ChallengeBasicResponseDto getChallengeBasic() {
        return new ChallengeBasicResponseDto(ChallengeBasicInfo.CHALLENGE_INFO_V1.getDescription());
    }

    // 챌린지 찾기, 없다면 예외
    public Challenge findChallengeById(Long challengeId) {
        return challengeRepository.findById(challengeId).orElseThrow(()
                -> new RestApiException(ChallengeErrorStatus.NOT_FOUND_CHALLENGE)
        );
    }
}
