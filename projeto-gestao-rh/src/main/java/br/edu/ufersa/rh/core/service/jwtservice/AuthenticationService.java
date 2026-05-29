package br.edu.ufersa.rh.core.service.jwtservice;

import br.edu.ufersa.rh.config.jwt.JwtUtil;
import br.edu.ufersa.rh.domain.dtos.jtw.AuthenticationRequest;
import br.edu.ufersa.rh.domain.dtos.jtw.AuthenticationResponse;
import br.edu.ufersa.rh.domain.entity.Usuario;
import br.edu.ufersa.rh.domain.enums.UsuarioStatusEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuthenticationService {

    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;
    private final PasswordEncoder passwordEncoder;
    private final CustomerUserDetailsService userDetailsService;
    private final LoginAuditService loginAuditService;

    public AuthenticationService(AuthenticationManager authenticationManager, JwtUtil jwtUtil, PasswordEncoder passwordEncoder, CustomerUserDetailsService userDetailsService, LoginAuditService loginAuditService) {
        this.authenticationManager = authenticationManager;
        this.jwtUtil = jwtUtil;
        this.passwordEncoder = passwordEncoder;
        this.userDetailsService = userDetailsService;
        this.loginAuditService = loginAuditService;
    }

    public AuthenticationResponse login(AuthenticationRequest request) {
        if (loginAuditService.verificarSeEstaBloqueado(request.username())) {
            long tempoRestante = loginAuditService.getTempoRestanteDesbloqueio(request.username());
            throw new DisabledException("Usuário bloqueado. Tente novamente em " + tempoRestante + " minutos.");
        }

        try {
            Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.username(), request.password()));

            SecurityContextHolder.getContext().setAuthentication(authentication);

            UserDetails userDetails = (UserDetails) authentication.getPrincipal();
            loginAuditService.registrarLoginSucesso(request.username());

            String token = jwtUtil.generateToken(userDetails);

            List<String> list = userDetails.getAuthorities().stream()
                    .map(GrantedAuthority::getAuthority)
                    .toList();
            return new AuthenticationResponse(token, list, request.username());
        } catch (AuthenticationException e) {
            loginAuditService.registrarLoginFalha(request.username());
            throw new BadCredentialsException("Credenciais inválidas", e);
        }
    }

    public Usuario register(AuthenticationRequest request) {
        boolean isExist = userDetailsService.userExists(request.username());
        if (!isExist) {
            Usuario novoUsuario = Usuario.builder()
                    .username(request.username())
                    .password(passwordEncoder.encode(request.password()))
                    .role(request.roles() != null && !request.roles().isEmpty() ? String.join(",", request.roles()) : "USER")
                    .ativo(Boolean.TRUE)
                    .status(UsuarioStatusEnum.ATIVO)
                    .tentativasFalhas(0)
                    .versao(0)
                    .build();
            return userDetailsService.saveUser(novoUsuario);
        }
        throw new IllegalArgumentException("User already exists");
    }
}
