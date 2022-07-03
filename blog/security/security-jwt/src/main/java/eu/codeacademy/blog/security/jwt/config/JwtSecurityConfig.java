package eu.codeacademy.blog.security.jwt.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import eu.codeacademy.blog.security.jwt.filter.JwtAuthenticationFilter;
import eu.codeacademy.blog.security.jwt.service.JwtProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.HttpStatusEntryPoint;

@Configuration
@RequiredArgsConstructor
public class JwtSecurityConfig extends WebSecurityConfigurerAdapter {

    private final ObjectMapper objectMapper;
    private final UserDetailsService userDetailsService;
    private final JwtProvider jwtProvider;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // Disable CSRF
        http
                .csrf().disable();

        // Set session management to stateless
        http = http
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and();

        // set authorization request access
        http = http
                .authorizeRequests()
                .antMatchers(
                        "/swagger-ui/**",
                        "/swagger-resources/**",
                        "/v2/api-docs/**",  // if we want to use old swagger version
                        "/v3/api-docs/**").permitAll()
                .anyRequest().authenticated()
                .and();

        // set unauthorized requests exception handler
        http = http
                .exceptionHandling()
                .authenticationEntryPoint(new HttpStatusEntryPoint(HttpStatus.UNAUTHORIZED))
                .and();

        // set filters
        http
                .addFilter(new JwtAuthenticationFilter(authenticationManager(), objectMapper, jwtProvider));
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .userDetailsService(userDetailsService);
    }
}
