package com.example.securityprac02.jwt;

import com.example.securityprac02.user.JwtUserService;
import com.example.securityprac02.user.UserDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
@RestController
public class JwtController {

    private final JwtUserService jwtUserService;

    @GetMapping("/test")
    public ResponseEntity<String> test(){
        return new ResponseEntity<>("Hi!", HttpStatus.OK);
    }


    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody UserDTO userDTO){
        jwtUserService.save(userDTO);
        return new ResponseEntity<>("Success Register " + userDTO.toString(), HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<JwtResponse> login(@RequestBody UserDTO userDTO){
        Map<String, String> token = jwtUserService.login(userDTO);
        userDTO.setId(jwtUserService.findUserId(userDTO));
        return new ResponseEntity<>(new JwtResponse(token, userDTO), HttpStatus.OK);
    }
}
