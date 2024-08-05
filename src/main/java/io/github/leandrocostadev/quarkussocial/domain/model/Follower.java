package io.github.leandrocostadev.quarkussocial.domain.model;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@IdClass(FollowerId.class)
@Data
@Table(name = "followers")
@NamedNativeQueries({
        @NamedNativeQuery(name = "GET_FOLLOWERS", query = "SELECT u.* FROM users u JOIN followers f ON f.follower_id = u.user_id WHERE f.followed_id = :id", resultClass = User.class),
        @NamedNativeQuery(name = "GET_FOLLOWING", query = "SELECT u.* FROM users u JOIN followers f ON f.followed_id = u.user_id WHERE f.follower_id = :id", resultClass = User.class),
        @NamedNativeQuery(name = "IS_FOLLOWING", query = "SELECT COUNT(*) FROM followers WHERE follower_id = :followerId and followed_id = :followedId"),
        @NamedNativeQuery(name = "FOLLOW", query = "INSERT INTO followers (follower_id, followed_id) VALUES (:followerId, :followedId)"),
        @NamedNativeQuery(name = "UNFOLLOW", query = "DELETE FROM followers WHERE follower_id = :followerId AND followed_id = :followedId")
})
public class Follower extends PanacheEntityBase {

//    @EmbeddedId
//    private FollowerId followerId = new FollowerId();

    @Id
    @Column(name = "follower_id")
    private Long followerId;

    @Id
    @Column(name = "followed_id")
    private Long followedId;

    @ManyToOne
    @JoinColumn(name = "follower_id")
    private User follower;

    @ManyToOne
    @JoinColumn(name = "followed_id")
    private User followed;

}