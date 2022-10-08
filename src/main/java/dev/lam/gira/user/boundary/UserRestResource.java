package dev.lam.gira.user.boundary;

import dev.lam.gira.common.util.ResponseUtils;
import dev.lam.gira.user.dto.UserDTO;
import dev.lam.gira.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("api/v1/users")
public class UserRestResource {

    @Autowired
    private UserService userService;

    @GetMapping
    public Object findAll() {
        return ResponseUtils.get(
                userService.findAllDTO(UserDTO.class),
                HttpStatus.OK
        );
    }

    @GetMapping("/paging")
    public Object findAllDTOPaging(@RequestParam("size") int size, @RequestParam("index") int index) {
        return ResponseUtils.get(
                userService.findAllDTO(Pageable.ofSize(size).withPage(index), UserDTO.class),
                HttpStatus.OK
        );
    }

    @PostMapping
    public Object save(@RequestBody @Valid UserDTO userDTO) {
        return ResponseUtils.get(
                userService.createUser(userDTO),
                HttpStatus.CREATED
        );
    }
}
