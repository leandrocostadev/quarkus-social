package io.github.leandrocostadev.quarkussocial.domain.model;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Data
@Table(name = "posts")
@NamedNativeQueries({
        @NamedNativeQuery(name = "LIST_POSTS_BY_USER_ID", query = "SELECT * FROM posts WHERE user_id = :userId", resultClass = Post.class),
        @NamedNativeQuery(name  = "GET_POST", query = "SELECT * FROM posts WHERE post_id = :postId", resultClass = Post.class),
        @NamedNativeQuery(name = "ADD_POST", query = "INSERT INTO posts (post_text, user_id) VALUES (:postText, :userId)"),
        @NamedNativeQuery(name = "UPDATE_POST", query = "UPDATE posts SET post_text = :postText WHERE post_id = :postId"),
        @NamedNativeQuery(name = "DELETE_POST", query = "DELETE FROM posts WHERE post_id = :postId")
})
public class Post extends PanacheEntityBase {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "post_id")
    private Long id;

    @Column(name = "post_text")
    private String text;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

}
