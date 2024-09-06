package com.example.securityprac02.user;

import com.example.securityprac02.jwt.JwtUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@RequiredArgsConstructor
@Service
public class JwtUserService {

    private final PasswordEncoder passwordEncoder;

    private final JwtUtil jwtUtil;

    private final JwtUserDetailService jwtUserDetailService;

    private final JwtUserRepository jwtUserRepository;

    public Long findUserId(UserDTO userDTO){
        return jwtUserRepository.findByEmail(userDTO.getEmail()).get().getId();
    }

    public void save(UserDTO userDTO){
        JwtUser jwtUser = new JwtUser();
        jwtUser.setPassword(passwordEncoder.encode(userDTO.getPassword()));
        jwtUser.setEmail(userDTO.getEmail());
        jwtUserRepository.save(jwtUser);
    }

    public Map<String, String> login(UserDTO userDTO) {
        UserDetails userDetails = jwtUserDetailService.loadUserByUsername(userDTO.getEmail());

        if(userDetails != null && passwordEncoder.matches(userDTO.getPassword(), userDetails.getPassword())){
            try {
                String token = jwtUtil.generateToken(userDTO.getEmail());

                Map<String, String> response = new HashMap<>();
                response.put("token", token);
                return response;
            }catch (Exception e){
                log.info("Something goes wrong while generating Token : " + e.getMessage());
            }
        }else {
            throw new RuntimeException("No such user or password is not correct " + userDetails);
        }

        return new HashMap<>();
    }
}
