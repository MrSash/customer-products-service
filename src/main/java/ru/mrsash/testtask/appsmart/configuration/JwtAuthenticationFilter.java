package ru.mrsash.testtask.appsmart.configuration;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.lang.NonNull;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.filter.OncePerRequestFilter;
import ru.mrsash.testtask.appsmart.util.JwtUtils;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final List<String> ignorePaths;

    private final List<HttpMethod> ignoreMethods;

    public JwtAuthenticationFilter(List<String> ignorePaths, List<HttpMethod> ignoreMethods) {
        this.ignorePaths = ignorePaths;
        this.ignoreMethods = ignoreMethods;
    }

    @Override
    protected void doFilterInternal(
            @NonNull HttpServletRequest httpServletRequest,
            @NonNull HttpServletResponse httpServletResponse,
            @NonNull FilterChain filterChain
    ) throws IOException, ServletException {
        final UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(
                JwtUtils.getUserByToken(getHeaderToken(httpServletRequest)),
                null,
                new ArrayList<>()
        );
        SecurityContextHolder.getContext().setAuthentication(token);
        filterChain.doFilter(httpServletRequest, httpServletResponse);
    }

    @Override
    protected boolean shouldNotFilter(@NonNull HttpServletRequest request) throws ServletException {
        return ignorePaths.stream().anyMatch(path -> new AntPathMatcher().match(path, request.getServletPath()))
                || ignoreMethods.stream().anyMatch(httpMethod -> request.getMethod().equals(httpMethod.name()));
    }

    private String getHeaderToken(HttpServletRequest httpServletRequest) {
        final String header = httpServletRequest.getHeader(HttpHeaders.AUTHORIZATION);
        if (header == null) {
            throw new IllegalArgumentException("Authorization header can't be null");
        }
        return header.replace("Bearer ", "");
    }

}
