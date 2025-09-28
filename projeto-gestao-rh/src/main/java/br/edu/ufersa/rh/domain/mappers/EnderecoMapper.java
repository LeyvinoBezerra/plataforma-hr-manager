package br.edu.ufersa.rh.domain.mappers;

import br.edu.ufersa.rh.domain.dtos.endereco.response.EnderecoGetResponse;
import br.edu.ufersa.rh.domain.dtos.endereco.response.EnderecoPostResponse;
import br.edu.ufersa.rh.domain.dtos.endereco.response.EnderecoPutResponse;
import br.edu.ufersa.rh.domain.dtos.endereco.resquest.EnderecoPostRequest;
import br.edu.ufersa.rh.domain.dtos.endereco.resquest.EnderecoPutRequest;
import br.edu.ufersa.rh.domain.entity.Endereco;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING, unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface EnderecoMapper {

    Endereco toEndereco(EnderecoPostRequest request);

    Endereco toEndereco(EnderecoPutRequest request);

    EnderecoPostResponse toEnderecoPostResponse(Endereco endereco);

    EnderecoGetResponse toEnderecoGetResponse(Endereco endereco);

    EnderecoPutResponse toEnderecoPutResponse(Endereco endereco);

    List<EnderecoGetResponse> toEnderecoGetResponseList(List<Endereco> endereco);
}
