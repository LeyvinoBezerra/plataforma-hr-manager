package br.edu.ufersa.rh.domain.dtos.jtw;

import java.util.List;

public record AuthenticationRequest(String username, String password, List<String> roles) {
}