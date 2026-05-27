package br.edu.ufersa.rh.core.controller.auth;


import br.edu.ufersa.rh.core.service.jwtservice.AuthenticationService;
import br.edu.ufersa.rh.core.service.jwtservice.LoginAuditService;
import br.edu.ufersa.rh.domain.dtos.jtw.AuthenticationRequest;
import br.edu.ufersa.rh.domain.dtos.jtw.AuthenticationResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
@Tag(name = "Authentication Controller", description = "Endpoints for user authentication and token management")
public class AuthController {

    private final AuthenticationService authenticationService;
    private final LoginAuditService loginAuditService;

    public AuthController(AuthenticationService authenticationService, LoginAuditService loginAuditService) {
        this.authenticationService = authenticationService;
        this.loginAuditService = loginAuditService;
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

    @PostMapping("/logout")
    @SecurityRequirement(name = "bearer-jwt")
    public ResponseEntity<?> logout() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null) {
            String username = authentication.getName();
            loginAuditService.registrarLoginSucesso(username);
        }
        SecurityContextHolder.clearContext();
        return ResponseEntity.ok("Logout realizado com sucesso");
    }
}
