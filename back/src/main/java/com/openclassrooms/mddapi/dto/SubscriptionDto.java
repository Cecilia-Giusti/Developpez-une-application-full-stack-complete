package com.openclassrooms.mddapi.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class SubscriptionDto {
    private Integer id;
    private Integer userId;
    private Integer themeId;
    private LocalDateTime createdAt;

    public SubscriptionDto(Integer id, Integer userId, Integer themeId, LocalDateTime createdAt) {
        this.id = id;
        this.userId = userId;
        this.themeId = themeId;
        this.createdAt = createdAt;
    }
}
