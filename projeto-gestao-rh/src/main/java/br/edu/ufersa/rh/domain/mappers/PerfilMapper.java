package br.edu.ufersa.rh.domain.mappers;

import br.edu.ufersa.rh.domain.dtos.perfil.response.PerfilGetResponse;
import br.edu.ufersa.rh.domain.dtos.perfil.response.PerfilPostResponse;
import br.edu.ufersa.rh.domain.dtos.perfil.response.PerfilPutResponse;
import br.edu.ufersa.rh.domain.dtos.perfil.resquest.PerfilPostRequest;
import br.edu.ufersa.rh.domain.dtos.perfil.resquest.PerfilPutRequest;
import br.edu.ufersa.rh.domain.entity.Perfil;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;

import java.util.List;
import java.util.Optional;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING, unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface PerfilMapper {

    Perfil toPerfil(PerfilPostRequest request);

    Perfil toPerfil(PerfilPutRequest request);

    PerfilPostResponse toPerfilPostResponse(Optional<Perfil> perfil);

    PerfilGetResponse toPerfilGetResponse(Optional<Perfil> perfil);

    PerfilPutResponse toPerfilPutResponse(Optional<Perfil> perfil);

    List<PerfilGetResponse> toPerfilGetResponseList(List<Perfil> perfil);
}
