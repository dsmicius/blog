package eu.codeacademy.blog.config;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;

import javax.sql.DataSource;

@Configuration
@RequiredArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final DataSource dataSource;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
            .authorizeRequests()
                .antMatchers("/public/**","/").permitAll()
                .anyRequest()
                .authenticated()
                .and()
            .formLogin()
                .permitAll()
                .loginPage("/login")
                .loginProcessingUrl("/login")
                .defaultSuccessUrl("/public/blogs/list", true)
                .usernameParameter("loginEmail")
                .passwordParameter("loginPassword")
                .and()
            .logout()
                .permitAll();
    }

    @Override
    public void configure(WebSecurity web) {
        web.ignoring().requestMatchers(
                PathRequest.toStaticResources().atCommonLocations(),
                PathRequest.toH2Console()
        );
    }
    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth
            .inMemoryAuthentication()
                .withUser("admin@blog.lt")
                    .password("{bcrypt}$2a$10$Y3GKDQIRG9CXCf8dLH836es1/xI9NSOQ1nUuIuZJXgwvUgBytfcOO")
                    .roles("USER","ADMIN");
        auth
           .jdbcAuthentication()
                .dataSource(dataSource)
                .usersByUsernameQuery("SELECT email AS username, password, TRUE AS enabled FROM users WHERE email = ?")
                .authoritiesByUsernameQuery("SELECT email AS username, 'USER' AS authority FROM users WHERE email = ?")
                .passwordEncoder(PasswordEncoderFactories.createDelegatingPasswordEncoder());
    }
}
