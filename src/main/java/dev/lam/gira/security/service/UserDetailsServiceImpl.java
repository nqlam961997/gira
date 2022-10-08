package dev.lam.gira.security.service;

import dev.lam.gira.common.exception.GiraBusinessException;
import dev.lam.gira.security.model.UserDetailsImpl;
import dev.lam.gira.user.model.User;
import dev.lam.gira.user.repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    private final UserRepository userRepository;

    public UserDetailsServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username)
                .orElseThrow(
                        () -> new GiraBusinessException("User is not existed")
                );

        return new UserDetailsImpl(
                user.getUsername(),
                user.getPassword(),
                Collections.emptyList()
                );
    }
}
