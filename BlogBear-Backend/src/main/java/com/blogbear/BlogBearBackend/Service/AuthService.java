package com.blogbear.BlogBearBackend.Service;

import com.blogbear.BlogBearBackend.Controller.AuthenticationController.AuthResponse;
import com.blogbear.BlogBearBackend.Controller.AuthenticationController.LoginRequest;
import com.blogbear.BlogBearBackend.Controller.AuthenticationController.RegisterRequest;
import com.blogbear.BlogBearBackend.Model.User.Role;
import com.blogbear.BlogBearBackend.Model.User.User;
import com.blogbear.BlogBearBackend.Repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final JwtService jwtService;

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;
    public AuthResponse login(LoginRequest loginRequest) {
        return null;
    }

    public AuthResponse register(RegisterRequest registerRequest) {
        User user = User.builder()
                .username(registerRequest.getUsername())
                .password(passwordEncoder.encode( registerRequest.getPassword()))
                .email(registerRequest.getEmail())
                .firstname(registerRequest.getFirstname())
                .lastname(registerRequest.getLastname())
                .country(registerRequest.getCountry())
                .role(Role.USER)
                .build();

        userRepository.save(user);

        return AuthResponse.builder()
                .token(jwtService.getToken(user))
                .build();
    }
}
