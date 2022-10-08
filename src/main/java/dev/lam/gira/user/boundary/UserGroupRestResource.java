package dev.lam.gira.user.boundary;

import dev.lam.gira.common.model.ResponseDTO;
import dev.lam.gira.common.util.ResponseUtils;
import dev.lam.gira.user.dto.UserGroupDTO;
import dev.lam.gira.user.model.UserGroup;
import dev.lam.gira.user.service.UserGroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("api/v1/user-groups")
public class UserGroupRestResource {

    @Autowired
    private UserGroupService userGroupService;

    @GetMapping
    public Object findAll() {
        return ResponseUtils.get(
                userGroupService.findAllDTO(UserGroupDTO.class),
                HttpStatus.OK
        );
    }

    @GetMapping("/paging")
    public Object findAllDTOPaging(@RequestParam("size") int size, @RequestParam("index") int index) {
        return ResponseUtils.get(
                userGroupService.findAllDTO(Pageable.ofSize(size).withPage(index), UserGroupDTO.class),
                HttpStatus.OK
        );
    }

    @GetMapping("/include-users")
    public Object findAllIncludeUsers() {
        return ResponseUtils.get(
                userGroupService.findAllDTOIncludeUsers(),
                HttpStatus.OK
        );
    }

    @PostMapping
    public Object save(@RequestBody @Valid UserGroupDTO userGroupDTO) {
        return ResponseUtils.get(
                userGroupService.save(userGroupDTO, UserGroup.class, UserGroupDTO.class),
                HttpStatus.CREATED
        );
    }

    @PostMapping("{user-group-id}/add-users")
    public ResponseEntity<ResponseDTO> addUsers(@RequestParam("user-group-id") UUID userGroupId,
                                                @Valid @RequestBody List<UUID> userIds) {
        return ResponseUtils.get(
                userGroupService.addUsers(userGroupId, userIds),
                HttpStatus.OK
        );
    };

    @PostMapping("{user-group-id}/remove-users")
    public ResponseEntity<ResponseDTO> removeUsers(@RequestParam("user-group-id") UUID userGroupId,
                                                @Valid @RequestBody List<UUID> userIds) {
        return ResponseUtils.get(
                userGroupService.removeUsers(userGroupId, userIds),
                HttpStatus.OK
        );
    };
}
