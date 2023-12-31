package com.openclassrooms.mddapi.paylod.request;

import lombok.Data;
import javax.validation.constraints.NotNull;

@Data
public class SubscriptionRequest {

    /**
     * The ID of the theme to which the user wants to subscribe.
     * This field is mandatory.
     */
    @NotNull(message = "Theme ID cannot be null")
    private Integer themeId;
}
