package com.example.securityprac02.user;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@RequiredArgsConstructor
@Service
public class JwtUserDetailService implements UserDetailsService {

    private final JwtUserRepository jwtUserRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        JwtUser user = jwtUserRepository.findByEmail(email).orElseThrow(() -> {
            throw new UsernameNotFoundException("There is no such user with : " + email);
        });
        return new User(user.getEmail(), user.getPassword(), new ArrayList<>());
    }
}
