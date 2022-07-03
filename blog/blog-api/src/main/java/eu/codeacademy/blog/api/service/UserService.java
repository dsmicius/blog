package eu.codeacademy.blog.api.service;

import eu.codeacademy.blog.api.dto.UserRoleDto;
import eu.codeacademy.blog.api.mapper.UserMapper;
import eu.codeacademy.blog.jpa.user.entity.User;
import eu.codeacademy.blog.jpa.user.repository.AuthorityRepository;
import eu.codeacademy.blog.jpa.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
//public class UserService implements UserDetailsService {
public class UserService {

    private final UserRepository userRepository;
    private final AuthorityRepository authorityRepository;
    private final UserMapper userMapper;

//    @Override
//    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        return userRepository.findUserByEmailWithAuthorities(username)
//                .map(userMapper::toDto)
//                .orElseThrow(() -> new UsernameNotFoundException("'" + username + "' not found!"));
//    }

    public Optional<User> getUserEntityByUserName(String email) {
        return userRepository.findUserByEmailWithAuthorities(email);
    }

    public List<UserRoleDto> getAllUsers() {
        return userRepository.findAll().stream()
                .map(userMapper::toDto)
                .collect(Collectors.toList());
    }

    public void updateUser(UserRoleDto userRoleDto) {
        Optional<User> userOptional = userRepository.findUserByEmailWithAuthorities(userRoleDto.getUser().getEmail());
        if(userOptional.isPresent()) {
            User user = userOptional.get().toBuilder()
                    .name(userRoleDto.getUser().getName())
                    .surname(userRoleDto.getUser().getSurname())
                    .email(userRoleDto.getUser().getEmail())
                    .phoneNumber(userRoleDto.getUser().getPhoneNumber())
                    .zipCode(userRoleDto.getUser().getZipCode())
                    .build();

            userRepository.save(user);
        }
    }
    public void deleteUser(String email) {
        Optional<User> optionalUser = userRepository.findUserByEmailWithAuthorities(email);
        if (optionalUser.isPresent()) {
            userRepository.delete(optionalUser.get());
        }
    }
}
