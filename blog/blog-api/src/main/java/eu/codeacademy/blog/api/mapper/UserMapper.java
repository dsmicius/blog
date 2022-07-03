package eu.codeacademy.blog.api.mapper;

import eu.codeacademy.blog.api.dto.UserDto;
import eu.codeacademy.blog.api.dto.UserRoleDto;
import eu.codeacademy.blog.jpa.user.entity.Authority;
import eu.codeacademy.blog.jpa.user.entity.User;
import org.springframework.stereotype.Component;

import java.util.Set;
import java.util.function.Function;

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
                .roles(Set.of("ROLE_ADMIN"))
//                .roles(
//                        userEntity.getAuthorities().stream()
//                                .map(getAuthority())
//                                .map(SimpleGrantedAuthority::new)
//                                .collect(Collectors.toUnmodifiableSet())
//                )
                .build();
    }

    private Function<Authority, String> getAuthority() {

        return authority -> "ROLE_" + authority.getName();
    }
}

