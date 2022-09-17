package dev.lam.gira;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
public class WelcomeResource {

    @GetMapping("/welcome")
    public String welcome(HttpServletRequest request){
        String welcomeStr = "Welcome %s to Gira Application";
        return String.format(welcomeStr, request.getRemoteAddr());
    }
}
