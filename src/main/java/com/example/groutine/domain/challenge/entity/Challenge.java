package com.example.groutine.domain.challenge.entity;

import com.example.groutine.domain.challenge.dto.request.ChallengeRequestDto;
import com.example.groutine.domain.member.entity.Member;
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

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Challenge extends BaseEntity {

    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY )
    private Long id;

    private String title;

    private String description;

    private LocalDateTime startDate;

    private LocalDateTime endDate;

    private String thumbnail;

    @ManyToOne
    @JoinColumn(name = "writer_id")
    private Member writer;

    @OneToMany(mappedBy = "challenge", cascade = CascadeType.ALL)
    private List<MemberChallenge> memberChallengeList = new ArrayList<>();

    @OneToMany(mappedBy = "challenge", cascade = CascadeType.ALL)
    private List<ChallengeMission> challengeMissionList = new ArrayList<>();


    //  챌린지의 미션 추가
    public void addChallengeMission(ChallengeMission challengeMission) {
        challengeMissionList.add(challengeMission);
    }


    public Challenge updateChallenge(ChallengeRequestDto request) {
        this.title = request.title();
        this.description = request.description();
        this.startDate = request.startDate();
        this.endDate = request.endDate();
        this.thumbnail = request.thumbnail();
        return this;
    }


    @Builder
    public Challenge(
            String title, String description, LocalDateTime startDate, LocalDateTime endDate, String thumbnail,
            Member writer, List<MemberChallenge> memberChallengeList, List<ChallengeMission> challengeMissionList
    ) {
        this.title = title;
        this.description = description;
        this.startDate = startDate;
        this.endDate = endDate;
        this.thumbnail = thumbnail;
        this.writer = writer;
        this.memberChallengeList = (memberChallengeList != null) ? memberChallengeList : new ArrayList<>();
        this.challengeMissionList = (challengeMissionList != null) ? challengeMissionList : new ArrayList<>();
    }
}
