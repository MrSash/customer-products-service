package ru.mrsash.testtask.appsmart.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import ru.mrsash.testtask.appsmart.model.User;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;

public class JwtUtils {

    private static final String LOGIN_KEY = "login";

    private static final String PASSWORD_KEY = "password";

    private static final Algorithm ALGORITHM = Algorithm.HMAC256("secret_key");

    public static String generateToken(String login, String password) {
        return JWT.create()
                .withExpiresAt(new Date(Instant.now().plus(5, ChronoUnit.MINUTES).toEpochMilli()))
                .withClaim(LOGIN_KEY, login)
                .withClaim(PASSWORD_KEY, password)
                .sign(ALGORITHM);
    }

    public static User getUserByToken(String token) {
        final DecodedJWT decodedJWT = JWT.require(ALGORITHM).build().verify(token);
        return User.builder()
                .login(decodedJWT.getClaim(LOGIN_KEY).toString())
                .password(decodedJWT.getClaim(PASSWORD_KEY).toString())
                .build();
    }
}
