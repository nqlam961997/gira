package dev.lam.gira.role.boundary;

import dev.lam.gira.common.util.ResponseUtils;
import dev.lam.gira.role.dto.RoleDTO;
import dev.lam.gira.role.model.Role;
import dev.lam.gira.role.service.RoleService;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping(value = "/roles")
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

    @GetMapping("/paging")
    public Object findAllDTOPaging(@RequestParam("size") int size, @RequestParam("index") int index) {
        return ResponseUtils.get(
                roleService.findAllDTO(Pageable.ofSize(size).withPage(index), RoleDTO.class),
                HttpStatus.OK
        );
    }

    @PostMapping
    public Object save(@RequestBody @Valid RoleDTO roleDTO) {
        return ResponseUtils.get(
                roleService.save(roleDTO),
                HttpStatus.CREATED
        );
    }
}
