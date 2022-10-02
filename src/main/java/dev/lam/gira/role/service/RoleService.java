package dev.lam.gira.role.service;

import dev.lam.gira.common.service.GenericService;
import dev.lam.gira.role.dto.RoleDTO;
import dev.lam.gira.role.dto.RoleWithOperationDTO;
import dev.lam.gira.role.model.Operation;
import dev.lam.gira.role.model.Role;
import dev.lam.gira.role.repository.RoleRepository;
import org.modelmapper.ModelMapper;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface RoleService extends GenericService<Role, RoleDTO, UUID> {
    Role update(Role role, String code);
    RoleWithOperationDTO addOperations(String roleId, List<String> serviceIds);

}

@org.springframework.stereotype.Service
@Transactional
class RoleServiceImpl implements RoleService {

    private final RoleRepository roleRepository;
    private final ModelMapper modelMapper;

    private final OperationService operationService;

    public RoleServiceImpl(RoleRepository roleRepository, ModelMapper modelMapper, OperationService operationService) {
        this.roleRepository = roleRepository;
        this.modelMapper = modelMapper;
        this.operationService = operationService;
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

    @Override
    public RoleWithOperationDTO addOperations(String roleId, List<String> serviceIds) {
        Optional<Role> roleOpt = roleRepository.findById(UUID.fromString(roleId));

        if (roleOpt.isEmpty()) {
            return null;
        }

        Role role = roleOpt.get();

        List<UUID> serviceIdsStringToUUID = serviceIds
                .stream()
                .map(UUID::fromString)
                .toList();

        List<Operation> operations = operationService.findAllIds(serviceIdsStringToUUID);

        operations.forEach(
                role::addOperation
        );

        return getModelMapper().map(role, RoleWithOperationDTO.class);
    }
}
