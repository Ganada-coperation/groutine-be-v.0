package com.example.groutine.domain.mission.service;

import com.example.groutine.domain.mission.entity.ChallengeMission;
import com.example.groutine.domain.mission.repository.ChallengeMissionRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@AllArgsConstructor
public class ChallengeMissionCommandService {
    private final ChallengeMissionRepository challengeMissionRepository;

    // 챌린지 미션들 저장
    public void saveChallengeMissions(List<ChallengeMission> challengeMissions) {
        challengeMissionRepository.saveAll(challengeMissions);
    }
}
