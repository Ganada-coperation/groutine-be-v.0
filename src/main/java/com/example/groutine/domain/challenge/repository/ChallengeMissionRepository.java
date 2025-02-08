package com.example.groutine.domain.challenge.repository;

import com.example.groutine.domain.participation.entity.ChallengeMission;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChallengeMissionRepository extends JpaRepository< ChallengeMission, Long> {
}
