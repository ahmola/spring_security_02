package com.example.securityprac02.user;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class UserService implements UserDetailsService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public Boolean save(UserDTO userDTO){
        JwtUser jwtUser = new JwtUser();
        jwtUser.setEmail(userDTO.getEmail());
        jwtUser.setPassword(passwordEncoder.encode(userDTO.getPassword()));

        userRepository.save(jwtUser);
        return true;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return null;
    }
}
