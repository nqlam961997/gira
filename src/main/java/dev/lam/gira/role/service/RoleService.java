package dev.lam.gira.role.service;

import dev.lam.gira.common.service.GenericService;
import dev.lam.gira.role.dto.RoleDTO;
import dev.lam.gira.role.model.Role;
import dev.lam.gira.role.repository.RoleRepository;
import org.modelmapper.ModelMapper;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

public interface RoleService extends GenericService<Role, RoleDTO, UUID> {

    Role update(Role role, String code);
}

@Service
@Transactional
class RoleServiceImpl implements RoleService {

    private final RoleRepository roleRepository;
    private final ModelMapper modelMapper;

    public RoleServiceImpl(RoleRepository roleRepository, ModelMapper modelMapper) {
        this.roleRepository = roleRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public JpaRepository<Role, UUID> getRepository() {
        return this.roleRepository;
    }

    @Override
    public ModelMapper getModelMapper() {
        return this.modelMapper;
    }

    @Override
    public Role update(Role role, String code) {
        Role currentRole = roleRepository.findByCode(code);
        currentRole.setName(role.getName());
        currentRole.setDescription(role.getDescription());
        return roleRepository.save(currentRole);
    }

}
