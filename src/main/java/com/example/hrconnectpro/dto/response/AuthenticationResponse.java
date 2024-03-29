package com.example.hrconnectpro.dto.response;

import com.example.hrconnectpro.entities.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AuthenticationResponse{
    private String token;
    private String refreshToken;
    private String name;
    private String familyName;
    private String email;
    private Role role;

}
