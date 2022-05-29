package eu.codeacademy.blog.user.service;

import eu.codeacademy.blog.user.dto.UserDto;
import eu.codeacademy.blog.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public void register(UserDto userDto) {

    }
}
