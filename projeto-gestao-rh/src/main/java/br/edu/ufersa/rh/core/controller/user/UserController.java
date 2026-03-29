package br.edu.ufersa.rh.core.controller.user;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.logging.Logger;

@Tag(name = "User Controller", description = "Endpoints for user and admin access")
@RequestMapping("/api")
@RestController
public class UserController {
    Logger logger = Logger.getLogger(UserController.class.getName());

    @GetMapping("/user")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public String sayHello(){
        logger.info("User endpoint accessed");
        return "Hello User!!";
    }

    // want to authenticate only admin users
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/admin")
    public String sayHelloToAdmin(){
        logger.info("Admin endpoint accessed");
        return "Hello Admin!!";
    }
}
