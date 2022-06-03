package eu.codeacademy.blog.user.service;

import eu.codeacademy.blog.user.dto.UserDto;
import eu.codeacademy.blog.user.entity.User;
import eu.codeacademy.blog.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public void register(UserDto userDto) {
        userRepository.save(User.builder()
                .email(userDto.getEmail())
                .name(userDto.getName())
                .surname(userDto.getSurname())
                .password(passwordEncoder.encode(userDto.getPassword()))
                .phoneNumber(userDto.getPhoneNumber())
                .zipCode(userDto.getZipCode())
                .build());
    }
}
