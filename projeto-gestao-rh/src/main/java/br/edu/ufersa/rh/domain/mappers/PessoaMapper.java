package br.edu.ufersa.rh.domain.mappers;

import br.edu.ufersa.rh.domain.dtos.pessoa.request.PessoaPostRequest;
import br.edu.ufersa.rh.domain.dtos.pessoa.request.PessoaPutRequest;
import br.edu.ufersa.rh.domain.dtos.pessoa.response.PessoaGetResponse;
import br.edu.ufersa.rh.domain.dtos.pessoa.response.PessoaPostResponse;
import br.edu.ufersa.rh.domain.dtos.pessoa.response.PessoaPutResponse;
import br.edu.ufersa.rh.domain.entity.Pessoa;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING, unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface PessoaMapper {

    Pessoa toPessoa(PessoaPostRequest request);

    Pessoa toPessoa(PessoaPutRequest request);

    PessoaPostResponse toPessoaPostResponse(Pessoa pessoa);

    PessoaGetResponse toPessoaGetResponse(Pessoa pessoa);

    PessoaPutResponse toPessoaPutResponse(Pessoa pessoa);

    List<PessoaGetResponse> toPessoaGetResponseList(List<Pessoa> pessoa);

}
