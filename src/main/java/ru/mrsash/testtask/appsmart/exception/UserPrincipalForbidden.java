package ru.mrsash.testtask.appsmart.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.FORBIDDEN)
public class UserPrincipalForbidden extends RuntimeException{

    public UserPrincipalForbidden(String message) {
        super(message);
    }
}
