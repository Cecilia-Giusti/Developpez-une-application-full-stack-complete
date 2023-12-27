package com.openclassrooms.mddapi.paylod.response;


import lombok.Getter;
import lombok.Setter;

/**
 * Represents the structure of the message response.
 */
@Setter
@Getter
public class MessageResponse {

    /**
     * The actual content of the confirmation message.
     * -- GETTER --
     *  Retrieves the confirmation message.
     * -- SETTER --
     *  Updates the content of the confirmation message.
     */
    private String message;

    /**
     * Constructs a new MessageResponse with the provided message.
     *
     * @param message The content of the confirmation message.
     */
    public MessageResponse(String message) {
        this.message = message;
    }

}
