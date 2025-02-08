package com.example.groutine.domain.challenge.entity;

import com.example.groutine.domain.member.entity.Member;
import com.example.groutine.domain.mission.entity.ChallengeMissionVerification;
import com.example.groutine.global.common.base.BaseEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ChallengeMember extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // 관리의 편의를 위해 점수 필드 추가
    @Getter
    @ColumnDefault("0")
    private int score;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "challenge_id")
    private Challenge challenge;

    @OneToMany(mappedBy = "challengeMember", cascade = CascadeType.ALL)
    private List<ChallengeMissionVerification> challengeMissionVerificationList = new ArrayList<>();

    @Builder
    public ChallengeMember(Member member, Challenge challenge) {
        this.member = member;
        this.challenge = challenge;
    }

    public void addScore(int score) {
        this.score += score;
    }

    public void subtractScore(int score) {
        this.score -= score;
    }
}
