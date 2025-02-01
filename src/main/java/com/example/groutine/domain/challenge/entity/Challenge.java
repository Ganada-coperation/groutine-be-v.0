package com.example.groutine.domain.challenge.entity;

import com.example.groutine.domain.participation.entity.MemberChallenge;
import com.example.groutine.global.common.base.BaseEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Challenge extends BaseEntity {

    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY )
    private Long id;

    private String title;

    private LocalDateTime startDate;

    private LocalDateTime endDate;

    private String profileUrl;

    @Enumerated(EnumType.STRING)
    private ChallengeCategory category;

    @OneToMany(mappedBy = "challenge", cascade = CascadeType.ALL)
    private List<MemberChallenge> memberChallengeList = new ArrayList<>();

    @OneToMany(mappedBy = "challenge", cascade = CascadeType.ALL)
    private List<ChallengeMission> challengeMissionArrayList = new ArrayList<>();

    @Builder
    public Challenge(String title, LocalDateTime startDate, LocalDateTime endDate, String profileUrl, ChallengeCategory category) {
        this.title = title;
        this.startDate = startDate;
        this.endDate = endDate;
        this.profileUrl = profileUrl;
        this.category = category;
    }


}
