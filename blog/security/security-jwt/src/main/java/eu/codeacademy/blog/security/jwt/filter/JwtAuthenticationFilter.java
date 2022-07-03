package eu.codeacademy.blog.security.jwt.filter;

import com.fasterxml.jackson.databind.ObjectMapper;
import eu.codeacademy.blog.security.jwt.dto.LoginRequest;
import eu.codeacademy.blog.security.jwt.dto.UserRoleDto;
import eu.codeacademy.blog.security.jwt.service.JwtProvider;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class JwtAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    private final ObjectMapper objectMapper;
    private final JwtProvider jwtProvider;

    public JwtAuthenticationFilter(AuthenticationManager authenticationManager,
                                   ObjectMapper objectMapper,
                                   JwtProvider jwtProvider) {
        super(authenticationManager);
        this.objectMapper = objectMapper;
        this.jwtProvider = jwtProvider;
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        try {
            LoginRequest loginDto = objectMapper.readValue(request.getReader(), LoginRequest.class);

            UsernamePasswordAuthenticationToken authRequest = new UsernamePasswordAuthenticationToken(
                    loginDto.getUsername(), loginDto.getPassword());
            // Allow subclasses to set the "details" property
            setDetails(request, authRequest);
            return this.getAuthenticationManager().authenticate(authRequest);
        } catch (IOException e) {
            throw new BadCredentialsException("Unable to parse payload credentials");
        }
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain,
                                            Authentication authResult) throws IOException, ServletException {
        SecurityContextHolder.getContext().setAuthentication(authResult);

        response.addHeader(HttpHeaders.AUTHORIZATION, jwtProvider.getToken((UserRoleDto) authResult.getPrincipal()));

        chain.doFilter(request, response);
    }
}
