package com.sadsoft.communicator.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RegDto {

    @NotNull
    @Size(max = 20)
    private String username;

    @NotNull
    @Size(min = 5, message = "Password confirm is too short")
    private String password;

    @NotNull
    @Size(min = 5, message = "Password confirm is too short")
    private String passwordAgain;
}
