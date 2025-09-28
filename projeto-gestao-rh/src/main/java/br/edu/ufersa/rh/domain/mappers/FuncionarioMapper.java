package br.edu.ufersa.rh.domain.mappers;

import br.edu.ufersa.rh.domain.dtos.funcionario.response.FuncionarioGetResponse;
import br.edu.ufersa.rh.domain.dtos.funcionario.response.FuncionarioPostResponse;
import br.edu.ufersa.rh.domain.dtos.funcionario.response.FuncionarioPutResponse;
import br.edu.ufersa.rh.domain.dtos.funcionario.resquest.FuncionarioPostRequest;
import br.edu.ufersa.rh.domain.dtos.funcionario.resquest.FuncionarioPutRequest;
import br.edu.ufersa.rh.domain.entity.Funcionario;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING, unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface FuncionarioMapper {

    Funcionario toFuncionario(FuncionarioPostRequest request);

    Funcionario toFuncionario(FuncionarioPutRequest request);

    FuncionarioPostResponse toFuncionarioPostResponse(Funcionario funcionario);

    FuncionarioGetResponse toFuncionarioGetResponse(Funcionario funcionario);

    FuncionarioPutResponse toFuncionarioPutResponse(Funcionario funcionario);

    List<FuncionarioGetResponse> toFuncionarioGetResponseList(List<Funcionario> funcionario);
}
