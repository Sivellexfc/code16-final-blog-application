package org.code16.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id", nullable = false)
    private Long id;

    private String title;
    private String body;

    @Temporal(TemporalType.DATE)
    private Date publishedDate;
    @Temporal(TemporalType.DATE)
    private Date updatedDate;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id",
                referencedColumnName = "id")
    @JsonIgnore
    private User user;

    @OneToMany(mappedBy = "post")
    private List<Comment> comments = new ArrayList<>();

    public void addComment(Comment comment){
        this.comments.add(comment);
    }

}
