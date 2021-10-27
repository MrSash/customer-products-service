package ru.mrsash.testtask.appsmart.service;

import ru.mrsash.testtask.appsmart.dto.AuthDto;
import ru.mrsash.testtask.appsmart.dto.AuthRequestDto;

public interface AuthService {

    AuthDto authenticate(AuthRequestDto authRequestDto);
}
