package com.example.groutine.domain.mission.entity;

import com.example.groutine.domain.challenge.entity.Challenge;
import com.example.groutine.global.common.base.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ChallengeMission extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Setter
    private String title;

    @Enumerated(EnumType.STRING)
    private ChallengeMissionType challengeMissionType;

    @Setter
    private String verifyGuide;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "challenge_id")
    private Challenge challenge;

    @OneToMany
    private List<ChallengeMissionVerification> challengeMissionVerificationList = new ArrayList<>();

    @Builder
    public ChallengeMission(
            String title, ChallengeMissionType challengeMissionType, String verifyGuide, Challenge challenge
    ) {
        this.title = title;
        this.challengeMissionType = challengeMissionType;
        this.verifyGuide = verifyGuide;
        this.challenge = challenge;
    }
}
