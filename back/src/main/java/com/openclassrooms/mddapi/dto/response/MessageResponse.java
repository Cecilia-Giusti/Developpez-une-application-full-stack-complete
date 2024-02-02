package com.openclassrooms.mddapi.dto.response;

import lombok.Getter;
import lombok.Setter;

/**
 * Data Transfer Object (DTO) representing a generic message response.
 */
@Getter
@Setter
public class MessageResponse {

    /**
     * The message content
     */
    private String message;

    /**
     * Constructs a new MessageResponse object with a specified message.
     * This constructor is used to create a response object with a predefined message.
     *
     * @param message The message content to be set in the response.
     */
    public MessageResponse(String message) {
        this.message = message;
    }
}
