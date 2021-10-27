package ru.mrsash.testtask.appsmart.configuration;

import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.configurers.ExpressionUrlAuthorizationConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.util.Arrays;
import java.util.List;

@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        final List<String> ignorePaths = Arrays.asList("/auth/**");
        final List<HttpMethod> ignoreMethods = Arrays.asList(HttpMethod.GET);
        final ExpressionUrlAuthorizationConfigurer<HttpSecurity>.ExpressionInterceptUrlRegistry httpConfigurer =
                http.csrf().disable()
                        .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                        .and()
                        .authorizeRequests()
                        .antMatchers(ignorePaths.toArray(new String[0])).permitAll();
        ignoreMethods.forEach(httpMethod -> httpConfigurer.antMatchers(httpMethod).permitAll());
        httpConfigurer
                .anyRequest().authenticated()
                .and()
                .addFilterBefore(
                        new JwtAuthenticationFilter(ignorePaths, ignoreMethods),
                        UsernamePasswordAuthenticationFilter.class
                );
    }
}
