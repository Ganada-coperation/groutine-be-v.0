package com.example.groutine.domain.test.entity;

import com.example.groutine.global.common.base.BaseEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Comment;

//DB 연결 테스트를 위한 Entity
@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Test extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    @Comment("이름")
    private String name;

    @Builder
    public Test(String name) {
        this.name = name;
    }
}
