package br.edu.ufersa.rh.domain.dtos.perfil.resquest;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PerfilGetRequest {

    private Long id;

    private String nome;

    private LocalDateTime dataCriacao;
}
