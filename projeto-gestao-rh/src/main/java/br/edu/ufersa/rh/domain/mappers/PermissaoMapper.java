package br.edu.ufersa.rh.domain.mappers;

import br.edu.ufersa.rh.domain.dtos.permissao.PermissaoDto;
import br.edu.ufersa.rh.domain.entity.Permissao;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface PermissaoMapper {
    PermissaoDto toDto(Permissao permissao);
    Permissao toEntity(PermissaoDto permissaoDto);
}
