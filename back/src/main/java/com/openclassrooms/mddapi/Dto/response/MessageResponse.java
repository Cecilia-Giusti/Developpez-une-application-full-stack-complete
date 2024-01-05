package com.openclassrooms.mddapi.Dto.response;

import lombok.Getter;
import lombok.Setter;

/**
 * Data Transfer Object (DTO) representing a generic message response.
 * This class is used to encapsulate a simple text message, typically used for sending responses to various API requests.
 */
@Getter
@Setter
public class MessageResponse {

    /**
     * The message content, conveying information about the result of an operation or any other relevant information.
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
