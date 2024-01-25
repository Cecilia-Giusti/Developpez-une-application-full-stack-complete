package com.openclassrooms.mddapi.dto.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProfileUpdateResponse {
    private String token;
    private String message;

    public ProfileUpdateResponse(String token, String message) {
        this.token = token;
        this.message = message;
    }

}
