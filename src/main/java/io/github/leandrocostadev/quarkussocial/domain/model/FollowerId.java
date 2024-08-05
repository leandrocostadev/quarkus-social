package io.github.leandrocostadev.quarkussocial.domain.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FollowerId implements Serializable {
    private Long followerId;
    private Long followedId;

}
