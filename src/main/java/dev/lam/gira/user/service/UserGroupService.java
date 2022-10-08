package dev.lam.gira.user.service;

import dev.lam.gira.common.service.GenericService;
import dev.lam.gira.common.util.BaseMapper;
import dev.lam.gira.user.dto.UserGroupDTO;
import dev.lam.gira.user.dto.UserGroupWithUserDTO;
import dev.lam.gira.user.model.User;
import dev.lam.gira.user.model.UserGroup;
import dev.lam.gira.user.repository.UserGroupRepository;
import org.modelmapper.ModelMapper;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.ValidationException;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface UserGroupService extends GenericService<UserGroup, UserGroupDTO, UUID> {
    UserGroupDTO addUsers(UUID userGroupId, List<UUID> userIds);

    UserGroupDTO removeUsers(UUID userGroupId, List<UUID> userIds);

    List<UserGroupWithUserDTO> findAllDTOIncludeUsers();
}

@Service
@Transactional
class UserGroupServiceImpl implements UserGroupService {
    private final UserGroupRepository userGroupRepository;

    private final BaseMapper baseMapper;

    private final UserService userService;

    public UserGroupServiceImpl(UserGroupRepository userGroupRepository, BaseMapper baseMapper, UserService userService) {
        this.userGroupRepository = userGroupRepository;
        this.baseMapper = baseMapper;
        this.userService = userService;
    }

    @Override
    public JpaRepository<UserGroup, UUID> getRepository() {
        return userGroupRepository;
    }

    @Override
    public ModelMapper getModelMapper() {
        return baseMapper;
    }

    @Override
    public UserGroupDTO addUsers(UUID userGroupId, List<UUID> userIds) {
        Optional<UserGroup> userGroupOpt = userGroupRepository.findById(userGroupId);

        if (userGroupOpt.isEmpty()) {
            return null;
        }

        UserGroup userGroup = userGroupOpt.get();

        List<User> users = userService.findAllIds(userIds);

        users.forEach(userGroup::addUser);

        return getModelMapper().map(userGroup, UserGroupDTO.class);
    }

    @Override
    public UserGroupDTO removeUsers(UUID userGroupId, List<UUID> userIds) {
        UserGroup userGroup = userGroupRepository.findById(userGroupId)
                .orElseThrow(() -> new ValidationException("UserGroup is not existed"));

        List<User> users = userService.findAllIds(userIds);

        users.forEach(userGroup::removeUser);

        return getModelMapper().map(userGroup, UserGroupDTO.class);
    }

    @Override
    public List<UserGroupWithUserDTO> findAllDTOIncludeUsers() {
        return userGroupRepository.findAllWithUsers()
                .stream()
                .map(userGroup ->  getModelMapper()
                        .map(userGroup, UserGroupWithUserDTO.class))
                .toList();
    }
}

