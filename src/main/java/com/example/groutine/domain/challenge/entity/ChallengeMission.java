package com.example.groutine.domain.challenge.entity;

import com.example.groutine.domain.participation.entity.MissionVerification;
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
    private MissionType missionType;

    @Setter
    private String verifyGuide;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "challenge_id")
    private Challenge challenge;

    @OneToMany
    private List<MissionVerification> missionVerificationList = new ArrayList<>();

    @Builder
    public ChallengeMission(String title, MissionType missionType, String verifyGuide) {
        this.title = title;
        this.missionType = missionType;
        this.verifyGuide = verifyGuide;
    }
}
