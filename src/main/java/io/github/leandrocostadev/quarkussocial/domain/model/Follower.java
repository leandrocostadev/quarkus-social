package io.github.leandrocostadev.quarkussocial.domain.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "followers")
public class Follower {

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "follower_id")
    private User follower;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "followed_id")
    private User followed;
}
