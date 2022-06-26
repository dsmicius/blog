package eu.codeacademy.blog.common.user.service;

import eu.codeacademy.blog.common.user.dto.UserDto;
import eu.codeacademy.blog.jpa.user.entity.Authority;
import eu.codeacademy.blog.jpa.user.entity.User;
import eu.codeacademy.blog.jpa.user.repository.AuthorityRepository;
import eu.codeacademy.blog.jpa.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserRegistrationService {

    private final UserRepository userRepository;
    private final AuthorityRepository authorityRepository;
    private final PasswordEncoder passwordEncoder;

    public void register(UserDto userDto) {
        Set<Authority> authorities = authorityRepository.findAll().stream()
                .filter(authority -> authority.getName().equals("USER"))
                .collect(Collectors.toUnmodifiableSet());

        userRepository.save(User.builder()
                .email(userDto.getEmail())
                .name(userDto.getName())
                .surname(userDto.getSurname())
                .password(passwordEncoder.encode(userDto.getPassword()))
                .phoneNumber(userDto.getPhoneNumber())
                .zipCode(userDto.getZipCode())
                .authorities(authorities)
                .build());
    }
}
