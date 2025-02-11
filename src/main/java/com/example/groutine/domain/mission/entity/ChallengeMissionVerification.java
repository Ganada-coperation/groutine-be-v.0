package com.example.groutine.domain.mission.entity;

import com.example.groutine.domain.challenge.entity.ChallengeMember;
import com.example.groutine.domain.member.entity.Member;
import com.example.groutine.global.common.base.BaseEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ChallengeMissionVerification extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String imageUrl;

    private String content;

    private LocalDateTime verifyDate; //인증한 날짜

    @Enumerated(EnumType.STRING)
    private VerifiyStatus status;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "challenge_mission_id")
    private ChallengeMission challengeMission;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "challenge_member_id")
    private ChallengeMember challengeMember;

    @Builder
    public ChallengeMissionVerification(String imageUrl, String content, LocalDateTime verifyDate, VerifiyStatus status, ChallengeMission challengeMission, ChallengeMember challengeMember) {
        this.imageUrl = imageUrl;
        this.content = content;
        this.verifyDate = verifyDate;
        this.status = status;
        this.challengeMission = challengeMission;
        this.challengeMember = challengeMember;
    }

}
