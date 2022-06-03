package eu.codeacademy.blog.user.service;

import eu.codeacademy.blog.user.dto.UserDto;
import eu.codeacademy.blog.user.entity.User;
import eu.codeacademy.blog.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;

    public void register(UserDto userDto) {
        userRepository.save(User.builder()
                .email(userDto.getEmail())
                .name(userDto.getName())
                .surname(userDto.getSurname())
                .password(userDto.getPassword())
                .phoneNumber(userDto.getPhoneNumber())
                .zipCode(userDto.getZipCode())
                .build());
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findUserByEmailWithAuthorities(username)
                .orElseThrow(() -> new UsernameNotFoundException("'" + username + "' not found!"));
    }
}
