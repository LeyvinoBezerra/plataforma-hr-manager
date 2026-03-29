package br.edu.ufersa.rh.core.controller.authController;


import br.edu.ufersa.rh.core.service.jwtservice.AuthenticationService;
import br.edu.ufersa.rh.domain.dtos.jtw.AuthenticationRequest;
import br.edu.ufersa.rh.domain.dtos.jtw.AuthenticationResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@Tag(name = "Authentication Controller", description = "Endpoints for user authentication and token management")
public class AuthController {


    private final AuthenticationService authenticationService;

    public AuthController(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody AuthenticationRequest request) {

        AuthenticationResponse authenticationResponse =  authenticationService.login(request);
        return ResponseEntity.ok(authenticationResponse);
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody AuthenticationRequest request) {
        String message =  authenticationService.register(request);
        return ResponseEntity.ok(message);
    }
}
