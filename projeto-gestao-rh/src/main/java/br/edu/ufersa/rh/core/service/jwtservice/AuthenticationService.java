package br.edu.ufersa.rh.core.service.jwtservice;

import br.edu.ufersa.rh.config.jwt.JwtUtil;
import br.edu.ufersa.rh.domain.dtos.jtw.AuthenticationRequest;
import br.edu.ufersa.rh.domain.dtos.jtw.AuthenticationResponse;
import br.edu.ufersa.rh.domain.entity.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
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

    public AuthenticationService(AuthenticationManager authenticationManager, JwtUtil jwtUtil, PasswordEncoder passwordEncoder, CustomerUserDetailsService userDetailsService) {
        this.authenticationManager = authenticationManager;
        this.jwtUtil = jwtUtil;
        this.passwordEncoder = passwordEncoder;
        this.userDetailsService = userDetailsService;
    }

    public AuthenticationResponse login(AuthenticationRequest request) {

        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.username(), request.password()));

        SecurityContextHolder.getContext().setAuthentication(authentication);

        UserDetails userDetails = (UserDetails) authentication.getPrincipal();

        String token = jwtUtil.generateToken(userDetails);

        List<String> list = userDetails.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .toList();
        return new AuthenticationResponse(token, list, request.username());
    }

    public String register(AuthenticationRequest request) {
        boolean isExist = userDetailsService.userExists(request.username());
        if (!isExist) {
            userDetailsService.saveUser(new Usuario(request.username(),
                    passwordEncoder.encode(request.password()),
                    String.join(",", request.roles()))
            );
        }
        return "User registered successfully";
    }
}
