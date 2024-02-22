package com.example.hrconnectpro.service.impl;

import com.example.hrconnectpro.dto.request.AuthenticationRequest;
import com.example.hrconnectpro.dto.request.RefreshTokenRequestDTO;
import com.example.hrconnectpro.dto.request.RegisterRequest;
import com.example.hrconnectpro.dto.response.AuthenticationResponse;
import com.example.hrconnectpro.dto.response.RefreshTokenResponseDTO;
import com.example.hrconnectpro.entities.Employee;
import com.example.hrconnectpro.repository.EmployeeRepository;
import com.example.hrconnectpro.security.JwtService;
import com.example.hrconnectpro.service.AuthenticationService;
import com.example.hrconnectpro.service.RoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Objects;
import java.util.Random;

@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {

    private final EmployeeRepository employeeRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    private final RoleService roleService;


    @Override
    public AuthenticationResponse register(RegisterRequest request) {
        // Check if email already exists
        if (employeeRepository.existsByEmail(request.getEmail())) {
            throw new RuntimeException("Email already exists. Please choose a different email.");
        }


        LocalDate accessDate = LocalDate.now();

        var user = Employee.builder()
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .username(request.getUsername())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(roleService.findDefaultRole().orElse(null))
                .build();

        employeeRepository.save(user);

        var jwtToken = jwtService.generateToken(user);
        return AuthenticationResponse.builder()
                .token(jwtToken)
                .email(user.getEmail())
                .role(user.getRole())
                .name(user.getFirstName())
                .familyName(user.getLastName())
                .build();
    }

    @Override
    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword())
        );

        var user = employeeRepository.findByEmail(request.getEmail()).orElseThrow();

        // Generate access token and refresh token
        var accessToken = jwtService.generateToken(user);
        var refreshToken = jwtService.generateRefreshToken(user);

        // Set the refresh token in the Member entity
        user.setRefreshToken(refreshToken);

        // Save the updated Member entity
        employeeRepository.save(user);

        return AuthenticationResponse.builder()
                .token(accessToken)
                .refreshToken(refreshToken)
                .email(user.getEmail())
                .role(user.getRole())
                .name(user.getFirstName())
                .familyName(user.getLastName())
                .build();
    }



    private int generateRandomMembershipNumber() {
        Random random = new Random();
        return random.nextInt(1000000) + 1;
    }


    @Override
    public RefreshTokenResponseDTO refreshToken(RefreshTokenRequestDTO refreshTokenRequest) {
        String refreshToken = refreshTokenRequest.getRefreshToken();

        // Extract username from the refresh token
        String username = jwtService.extractUserName(refreshToken);

        // Find the user by username
        Employee employee = employeeRepository.findByEmail(username).orElseThrow(() -> new RuntimeException("User not found"));

        // Verify if the stored refresh token matches the provided one
        if (!Objects.equals(employee.getRefreshToken(), refreshToken)) {
            throw new RuntimeException("Invalid refresh token");
        }

        // Verify if the refresh token is expired
        if (jwtService.isTokenExpired(refreshToken)) {
            throw new RuntimeException("Expired refresh token");
        }

        // Generate a new access token
        String newAccessToken = jwtService.generateToken(employee);

        // Expire the old refresh token and generate a new one
        String newRefreshToken = jwtService.generateRefreshToken(employee);

        // Update the refresh token in the Member entity
        employee.setRefreshToken(newRefreshToken);
        employeeRepository.save(employee);

        // Create and return the response DTO
        return RefreshTokenResponseDTO.builder()
                .accessToken(newAccessToken)
                .refreshToken(newRefreshToken)
                .build();
    }


}