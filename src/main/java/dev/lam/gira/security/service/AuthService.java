package dev.lam.gira.security.service;

import dev.lam.gira.common.exception.GiraBusinessException;
import dev.lam.gira.security.dto.LoginDTO;
import dev.lam.gira.security.jwt.JwtUtils;
import dev.lam.gira.user.model.User;
import dev.lam.gira.user.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

public interface AuthService {
    String login(LoginDTO loginDTO);
}

@Service
class AuthServiceImpl implements AuthService {
    private final PasswordEncoder passwordEncoder;

    private final UserRepository userRepository;

    private final JwtUtils jwtUtils;

    AuthServiceImpl(PasswordEncoder passwordEncoder, UserRepository userRepository, JwtUtils jwtUtils) {
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
        this.jwtUtils = jwtUtils;
    }

    @Override
    public String login(LoginDTO loginDTO) {
        User user = userRepository.findByUsername(loginDTO.getUsername())
                .orElseThrow(
                        () -> new GiraBusinessException("Gira is not existed")
                );

        if (passwordEncoder.matches(loginDTO.getPassword(), user.getPassword())) {
            return jwtUtils.generateJwt(user.getUsername());
        }

        throw new GiraBusinessException("Password is not correct");
    }
}

