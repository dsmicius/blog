package eu.codeacademy.blog.security.basic.config;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled = true)
@EnableConfigurationProperties(ApplicationUsersPropertyConfig.class)
@Slf4j
@RequiredArgsConstructor
public class BasicSecurityConfig extends WebSecurityConfigurerAdapter {

    private final ApplicationUsersPropertyConfig applicationsUsers;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // Disable CSRF
        http
                .csrf().disable();

        // turn on httpBasic of InMemoryUserDetailsManager used
        http = http
                .httpBasic()
                .and();

        // Set session management to stateless
        http = http
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and();

        // set authorization request access
        http = http
                .authorizeRequests()
                .anyRequest().authenticated()
                .and();
    }

    @Bean
    public InMemoryUserDetailsManager inMemoryUserDetailsManager() {
        final var manager = new InMemoryUserDetailsManager();
        log.info("Importing {} globally users:", applicationsUsers.getUsers().size());
        // loading in memory from configuration file all users 'api-users'
        applicationsUsers.getUsers().forEach(user -> {
            manager.createUser(User.withDefaultPasswordEncoder()
                    .username(user.getUsername())
                    .password(user.getPassword())
                    .roles(user.getRoles())
                    .build());
            log.info("Imported globally users {}", applicationsUsers);
        });

        return manager;
    }
}
