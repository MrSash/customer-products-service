package ru.mrsash.testtask.appsmart.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.mrsash.testtask.appsmart.dto.AuthDto;
import ru.mrsash.testtask.appsmart.dto.AuthRequestDto;
import ru.mrsash.testtask.appsmart.exception.UserPrincipalForbidden;
import ru.mrsash.testtask.appsmart.util.JwtUtils;

@Service
@RequiredArgsConstructor
@Slf4j
public class AuthServiceImpl implements AuthService {

    // Заглушка для иммитации авторизации
    @Override
    public AuthDto authenticate(AuthRequestDto authRequestDto) {
        final String loginStub = "admin";
        final String passwordStub = "admin";
        final String login = authRequestDto.getLogin();
        final String password = authRequestDto.getPassword();

        if (!login.equals(loginStub)) {
            throw new UserPrincipalForbidden("Incorrect login");
        }
        if (!password.equals(passwordStub)) {
            throw new UserPrincipalForbidden("Incorrect password");
        }

        return AuthDto.builder()
                .accessToken(JwtUtils.generateToken(login, authRequestDto.getPassword()))
                .build();
    }
}
