package br.edu.ufersa.rh.core.service.jwtservice;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class CustomUserDetails implements UserDetails {

    private final Long userId;
    private final String username;
    private final String password;
    private final boolean enabled;
    private final List<String> permissoes;
    private final List<String> roles;

    public CustomUserDetails(Long userId, String username, String password, boolean enabled, List<String> permissoes, List<String> roles) {
        this.userId = userId;
        this.username = username;
        this.password = password;
        this.enabled = enabled;
        this.permissoes = permissoes != null ? permissoes : new ArrayList<>();
        this.roles = roles != null ? roles : new ArrayList<>();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> authorities = new ArrayList<>();
        roles.forEach(role -> authorities.add(new SimpleGrantedAuthority("ROLE_" + role)));
        permissoes.forEach(perm -> authorities.add(new SimpleGrantedAuthority(perm)));
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return enabled;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return enabled;
    }

    public Long getUserId() {
        return userId;
    }

    public List<String> getPermissoes() {
        return permissoes;
    }

    public List<String> getRoles() {
        return roles;
    }
}
