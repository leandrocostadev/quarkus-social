package io.github.leandrocostadev.quarkussocial.rest.dto;

import lombok.Data;

@Data
public class IsFollowingResponse {
    private boolean isFollowing;

    public IsFollowingResponse(boolean isFollowing) {
        this.isFollowing = isFollowing;
    }
}
