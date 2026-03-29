package br.edu.ufersa.rh.core.service.jwtservice;

import br.edu.ufersa.rh.core.repository.usuario.UsuarioRepository;
import br.edu.ufersa.rh.domain.entity.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class CustomerUserDetailsService implements UserDetailsService {

    @Autowired
    private UsuarioRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        var usuario = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with username: " + username));

        return new User(
                usuario.getUsername(),
                usuario.getPassword(),
                usuario.getAtivo(),
                true, // accountNonExpired
                true, // credentialsNonExpired
                true, // accountNonLocked
                new ArrayList<>() // authorities, you can map roles to authorities if needed
        );
    }

    public boolean userExists(String username) {
        return userRepository.existsByUsername(username);
    }

    public Usuario saveUser(Usuario user) {
        return userRepository.save(user);
    }
}
