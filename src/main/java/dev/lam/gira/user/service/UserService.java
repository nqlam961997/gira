package dev.lam.gira.user.service;

import dev.lam.gira.common.service.GenericService;
import dev.lam.gira.common.util.BaseMapper;
import dev.lam.gira.user.dto.UserDTO;
import dev.lam.gira.user.model.User;
import dev.lam.gira.user.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

public interface UserService extends GenericService<User, UserDTO, UUID> {
}

@Service
@Transactional
class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    private final BaseMapper baseMapper;

    public UserServiceImpl(UserRepository userRepository, BaseMapper baseMapper) {
        this.userRepository = userRepository;
        this.baseMapper = baseMapper;
    }

    @Override
    public JpaRepository<User, UUID> getRepository() {
        return userRepository;
    }

    @Override
    public ModelMapper getModelMapper() {
        return baseMapper;
    }
}