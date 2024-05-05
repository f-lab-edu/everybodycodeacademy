package com.every.everycodeacademy.board;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import java.util.HashSet;
import java.util.Set;

@Entity
public class BoardCategory {

    public enum BoardType {
        NOTICE, // 공지사항
        FREE, // 자유 게시판
        QNA // 질문과 답변
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private BoardType name; // 'name' 필드의 타입을 Enum 타입으로 변경

    @Column(length = 1024)
    private String description; // 게시판 설명

    // 다른 필수 메타 데이터 필드들...

    // 연관 관계 매핑: 이 카테고리에 속하는 게시판들
    @OneToMany(mappedBy = "category")
    private Set<Board> boards = new HashSet<> ();

}
