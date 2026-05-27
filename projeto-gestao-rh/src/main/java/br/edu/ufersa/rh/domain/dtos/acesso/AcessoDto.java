package br.edu.ufersa.rh.domain.dtos.acesso;

import br.edu.ufersa.rh.domain.enums.AcessoStatusEnum;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AcessoDto {

    @JsonProperty("id")
    private Long id;

    @JsonProperty("perfilId")
    private Long perfilId;

    @JsonProperty("permissaoId")
    private Long permissaoId;

    @JsonProperty("status")
    private AcessoStatusEnum status;

    @JsonProperty("dataInicio")
    private LocalDateTime dataInicio;

    @JsonProperty("dataFim")
    private LocalDateTime dataFim;
}
