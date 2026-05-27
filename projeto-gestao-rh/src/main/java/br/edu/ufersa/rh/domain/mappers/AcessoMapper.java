package br.edu.ufersa.rh.domain.mappers;

import br.edu.ufersa.rh.domain.dtos.acesso.AcessoDto;
import br.edu.ufersa.rh.domain.entity.Acesso;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.Mapping;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface AcessoMapper {
    @Mapping(target = "perfilId", source = "perfil.id")
    @Mapping(target = "permissaoId", source = "permissao.id")
    AcessoDto toDto(Acesso acesso);

    @Mapping(target = "perfil", ignore = true)
    @Mapping(target = "permissao", ignore = true)
    Acesso toEntity(AcessoDto acessoDto);
}
