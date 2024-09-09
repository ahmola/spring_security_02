package com.example.securityprac02.jwt;

import com.example.securityprac02.user.UserDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashMap;
import java.util.Map;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class JwtResponse {

    Map<String, String> token = new HashMap<>();

    UserDTO user;
}
