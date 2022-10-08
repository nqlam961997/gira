package dev.lam.gira.role.boundary;

import dev.lam.gira.common.util.ResponseUtils;
import dev.lam.gira.role.dto.RoleDTO;
import dev.lam.gira.role.dto.RoleWithOperationDTO;
import dev.lam.gira.role.model.Role;
import dev.lam.gira.role.service.RoleService;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = "api/v1/roles")
public class RoleRestResource {
    private final RoleService roleService;

    public RoleRestResource(RoleService roleService) {
        this.roleService = roleService;
    }

    @GetMapping
    public Object findAll() {
        return ResponseUtils.get(
                roleService.findAllDTO(RoleDTO.class),
                HttpStatus.OK
        );
    }

    @GetMapping("paging")
    public Object findAllDTOPaging(@RequestParam("size") int size, @RequestParam("index") int index) {
        return ResponseUtils.get(
                roleService.findAllDTO(Pageable.ofSize(size).withPage(index), RoleDTO.class),
                HttpStatus.OK
        );
    }

    @PostMapping
    public Object save(@RequestBody @Valid RoleDTO roleDTO) {
        return ResponseUtils.get(
                roleService.save(roleDTO, Role.class, RoleDTO.class),
                HttpStatus.CREATED
        );
    }

    @PostMapping("{role-id}/add-operations")
    public Object addOperation(@RequestBody List<String> ids, @RequestParam("role-id") String roleId) {
        RoleWithOperationDTO roleWithOperationDTO = roleService.addOperations(roleId, ids);

        if (roleWithOperationDTO == null) {
            ResponseUtils.getError(
                    List.of("Role is not existed"),
                    HttpStatus.CREATED
            );
        }

        return ResponseUtils.get(
                roleWithOperationDTO,
                HttpStatus.CREATED
        );
    }
}
