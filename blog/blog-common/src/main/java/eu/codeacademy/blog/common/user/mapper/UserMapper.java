package eu.codeacademy.blog.common.user.mapper;

import eu.codeacademy.blog.common.user.dto.UserDto;
import eu.codeacademy.blog.common.user.dto.UserRoleDto;
import eu.codeacademy.blog.jpa.user.entity.Authority;
import eu.codeacademy.blog.jpa.user.entity.User;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import java.util.function.Function;
import java.util.stream.Collectors;

@Component
public class UserMapper {

    public UserRoleDto toDto(User userEntity) {
        return UserRoleDto.builder()
                .user(UserDto.builder()
                        .email(userEntity.getEmail())
                        .name(userEntity.getName())
                        .surname(userEntity.getSurname())
                        .password(userEntity.getPassword())
                        .phoneNumber(userEntity.getPhoneNumber())
                        .zipCode(userEntity.getZipCode())
                        .build())
                .roles(
                        userEntity.getAuthorities().stream()
                                .map(getAuthority())
                                .map(SimpleGrantedAuthority::new)
                                .collect(Collectors.toUnmodifiableSet())
                )
                .build();
    }

    private Function<Authority, String> getAuthority() {

        return authority -> "ROLE_" + authority.getName();
    }
}

