package dev.lam.gira.security.boundary;

import dev.lam.gira.common.util.ResponseUtils;
import dev.lam.gira.security.dto.LoginDTO;
import dev.lam.gira.security.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("api/v1/auth")
public class AuthRestResource {

    @Autowired
    private AuthService authService;

    @PostMapping("login")
    public Object login(@RequestBody @Valid LoginDTO loginDTO) {
        return ResponseUtils.get(authService.login(loginDTO), HttpStatus.OK);
    }
}
