package dev.lam.gira.role.boundary;

import dev.lam.gira.common.util.ResponseUtils;
import dev.lam.gira.role.dto.OperationDTO;
import dev.lam.gira.role.model.Operation;
import dev.lam.gira.role.service.OperationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("api/v1/operation")
public class OperationRestResource {

    @Autowired
    private OperationService operationService;

    @GetMapping
    public Object findAll() {
        return ResponseUtils.get(
                operationService.findAllDTO(OperationDTO.class),
                HttpStatus.OK
        );
    }

    @PostMapping
    public Object save(@RequestBody @Valid OperationDTO operationDTO) {
        return ResponseUtils.get(
                operationService.save(operationDTO, Operation.class, OperationDTO.class),
                HttpStatus.CREATED
        );
    }
}
