package com.example.hrconnectpro.service;


import com.example.hrconnectpro.dto.request.AuthenticationRequest;
import com.example.hrconnectpro.dto.request.RefreshTokenRequestDTO;
import com.example.hrconnectpro.dto.request.RegisterRequest;
import com.example.hrconnectpro.dto.response.AuthenticationResponse;
import com.example.hrconnectpro.dto.response.RefreshTokenResponseDTO;

public interface AuthenticationService {

    AuthenticationResponse register(RegisterRequest user);

    AuthenticationResponse authenticate(AuthenticationRequest user);

    RefreshTokenResponseDTO refreshToken(RefreshTokenRequestDTO refreshTokenRequest);

}

