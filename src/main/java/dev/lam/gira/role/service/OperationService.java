package dev.lam.gira.role.service;

import dev.lam.gira.common.service.GenericService;
import dev.lam.gira.role.dto.OperationDTO;
import dev.lam.gira.role.model.Operation;
import dev.lam.gira.role.repository.OperationRepository;
import org.modelmapper.ModelMapper;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

public interface OperationService extends GenericService<Operation, OperationDTO, UUID> {
}

@org.springframework.stereotype.Service
@Transactional
class OperationServiceImpl implements OperationService {
    private final OperationRepository operationRepository;

    private final ModelMapper modelMapper;

    public OperationServiceImpl(OperationRepository operationRepository, ModelMapper modelMapper) {
        this.operationRepository = operationRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public JpaRepository<Operation, UUID> getRepository() {
        return operationRepository;
    }

    @Override
    public ModelMapper getModelMapper() {
        return modelMapper;
    }
}
