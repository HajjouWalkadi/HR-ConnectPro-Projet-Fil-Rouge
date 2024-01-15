package com.example.hrconnectpro.auth;


import com.example.hrconnectpro.dto.request.AuthenticationRequest;
import com.example.hrconnectpro.dto.request.RefreshTokenRequestDTO;
import com.example.hrconnectpro.dto.request.RegisterRequest;
import com.example.hrconnectpro.dto.response.AuthenticationResponse;
import com.example.hrconnectpro.dto.response.RefreshTokenResponseDTO;
import com.example.hrconnectpro.service.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthenticationService authenticationService;

    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponse> register(@RequestBody RegisterRequest request){
        return ResponseEntity.ok(authenticationService.register(request));
    }

    @PostMapping("/authenticate")
    public ResponseEntity<AuthenticationResponse> authenticate(@RequestBody AuthenticationRequest request){
        return ResponseEntity.ok(authenticationService.authenticate(request));
    }

    @PostMapping("/refresh-token")
    public ResponseEntity<RefreshTokenResponseDTO> refreshToken(
            @RequestBody RefreshTokenRequestDTO refreshTokenRequest) {
        RefreshTokenResponseDTO response = authenticationService.refreshToken(refreshTokenRequest);
        return ResponseEntity.ok(response);
    }
}
