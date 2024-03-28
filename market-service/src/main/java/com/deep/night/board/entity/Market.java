package com.deep.night.board.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.Comment;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Entity
@Table(name = "DN_MARKET")
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Market {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "DN_MARKET_ID")
    @Comment("기본키")
    private int dnBoardId;

    @Column(name = "TITLE", nullable = false)
    private String title;

    @Column(name = "CONTENT", nullable = false)
    private String content;

    @Column(name = "MARKET_CATEGORY", nullable = false)
    private String marketCategory;

    @CreationTimestamp
    @Column(name = "CREATE_DATE", nullable = false, updatable = false)
    private LocalDateTime createDate;

    @Column(name = "CREATE_USER_ID", nullable = false, updatable = false)
    private String createUserId;

    @UpdateTimestamp
    @Column(name = "UPDATE_DATE", insertable = false)
    private LocalDateTime updateDate;

    @Column(name = "UPDATE_USER_ID", insertable = false)
    private String updateUserId;

}
