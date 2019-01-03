package com.sadsoft.communicator.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LogDto {

    @NotNull(message = "password can't be empty")
    @Size(min = 3, max = 20, message = "username length should be between 3 and 20")
    private String username;

    @NotNull(message = "password can't be empty")
    @Size(min = 5, message = "Password is too short")
    private String password;


}
