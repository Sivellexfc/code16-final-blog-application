package org.code16.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id", nullable = false)
    private Long id;

    private String content;
    @Temporal(TemporalType.DATE)
    private Date publishedDate;

    private int likeCount;

    @ManyToOne()
    private Post post;

    @ManyToOne()
    private User user;

}
