package com.example.groutine.domain.challenge.service;

import com.example.groutine.domain.challenge.dto.response.ChallengeBasicResponseDto;
import com.example.groutine.domain.challenge.dto.response.ChallengeDetailResponseDto;
import com.example.groutine.domain.challenge.dto.response.ChallengeListResponseDto;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
public class ChallengeQueryService {

    // 챌린지 리스트 조회
    public ChallengeListResponseDto getChallengeList(Pageable pageable) {
        return ;
    }

    // 챌린지 상세 정보 조회
    public ChallengeDetailResponseDto getChallengeDetail(Long challengeId) {
        return ;
    }

    // 챌린지 기본 정보 조회 (모든 챌린지에 동일하게 있는 정보)
    public ChallengeBasicResponseDto getChallengeBasic() {
        return ;
    }

}
