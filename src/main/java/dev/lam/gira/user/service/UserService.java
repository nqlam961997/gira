package dev.lam.gira.user.service;

import dev.lam.gira.common.service.GenericService;
import dev.lam.gira.common.util.BaseMapper;
import dev.lam.gira.user.dto.UserDTO;
import dev.lam.gira.user.model.User;
import dev.lam.gira.user.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

public interface UserService extends GenericService<User, UserDTO, UUID> {
    UserDTO createUser(UserDTO userDTO);
}

@Service
@Transactional
class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    private final BaseMapper baseMapper;

    private final PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository userRepository,
                           BaseMapper baseMapper,
                           PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.baseMapper = baseMapper;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public JpaRepository<User, UUID> getRepository() {
        return userRepository;
    }

    @Override
    public ModelMapper getModelMapper() {
        return baseMapper;
    }

    @Override
    public UserDTO createUser(UserDTO userDTO) {
        User user = getModelMapper().map(userDTO, User.class);

        user.setPassword(passwordEncoder.encode(user.getPassword()));

        return getModelMapper().map(
                userRepository.save(user),
                UserDTO.class);
    }
}