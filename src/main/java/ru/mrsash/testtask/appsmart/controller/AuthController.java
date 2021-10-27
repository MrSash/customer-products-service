package ru.mrsash.testtask.appsmart.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.mrsash.testtask.appsmart.dto.AuthDto;
import ru.mrsash.testtask.appsmart.dto.AuthRequestDto;
import ru.mrsash.testtask.appsmart.service.AuthService;

@Api(tags = "Authentication")
@Tag(name = "Authentication")
@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @ApiOperation(value = "Log in and get token")
    @PostMapping("/token")
    public AuthDto authenticate(@RequestBody @Validated AuthRequestDto authRequestDto) {
        return authService.authenticate(authRequestDto);
    }
}
