package br.edu.ufersa.rh.domain.dtos.jtw;

import java.util.List;

public record AuthenticationResponse(String token, List<String> roles, String username) {
}