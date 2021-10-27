package ru.mrsash.testtask.appsmart.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class AuthRequestDto {

    @NotBlank
    private String login;

    @NotBlank
    private String password;
}
