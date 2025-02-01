package com.example.groutine.domain.participation.entity;

import com.example.groutine.domain.challenge.entity.ChallengeMission;
import com.example.groutine.domain.member.entity.Member;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class MissionVerification {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String imageUrl;

    private String content;

    private LocalDateTime verifyDate; //인증하 날짜

    @Enumerated(EnumType.STRING)
    private VerifiyStatus status;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "challenge_mission_id")
    private ChallengeMission challengeMission;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

}
