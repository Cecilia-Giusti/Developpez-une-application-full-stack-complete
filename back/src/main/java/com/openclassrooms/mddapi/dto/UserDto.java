package com.openclassrooms.mddapi.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import java.time.LocalDateTime;
import javax.validation.constraints.Email;
import javax.validation.constraints.Size;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {
    private Long id;

    @NonNull
    @Size(max = 20)
    private String username;

    @NonNull
    @Size(max = 50)
    @Email
    private String email;

    @JsonIgnore
    @Size(max = 120)
    private String password;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;
}
