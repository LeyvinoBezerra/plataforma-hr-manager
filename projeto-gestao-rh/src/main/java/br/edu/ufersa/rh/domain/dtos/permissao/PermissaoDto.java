package br.edu.ufersa.rh.domain.dtos.permissao;

import br.edu.ufersa.rh.domain.enums.PermissaoTipoEnum;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PermissaoDto {

    @JsonProperty("id")
    private Long id;

    @JsonProperty("nome")
    private String nome;

    @JsonProperty("descricao")
    private String descricao;

    @JsonProperty("tipo")
    private PermissaoTipoEnum tipo;

    @JsonProperty("recurso")
    private String recurso;

    @JsonProperty("ativo")
    private Boolean ativo;
}
