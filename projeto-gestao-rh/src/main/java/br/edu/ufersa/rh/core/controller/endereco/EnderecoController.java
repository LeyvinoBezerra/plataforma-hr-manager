package br.edu.ufersa.rh.core.controller.endereco;


import br.edu.ufersa.rh.core.service.funcionario.EnderecoService;
import br.edu.ufersa.rh.core.service.pessoa.PessoaService;
import br.edu.ufersa.rh.domain.dtos.endereco.response.EnderecoGetResponse;
import br.edu.ufersa.rh.domain.dtos.endereco.response.EnderecoPostResponse;
import br.edu.ufersa.rh.domain.dtos.endereco.response.EnderecoPutResponse;
import br.edu.ufersa.rh.domain.dtos.endereco.resquest.EnderecoPostRequest;
import br.edu.ufersa.rh.domain.dtos.endereco.resquest.EnderecoPutRequest;
import br.edu.ufersa.rh.domain.mappers.EnderecoMapper;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(EnderecoController.API_V_1_ENDERECOS)
@AllArgsConstructor
@Log4j2
public class EnderecoController {

    public static final String API_V_1_ENDERECOS = "/api/v1/enderecos";
    private static final String BUSCAR_POR_ID = "buscarPorId";
    private static final String LISTAR_TODOS = "listarTodos";

    private final EnderecoService enderecoService;
    private final EnderecoMapper enderecoMapper;

    @PostMapping
    public ResponseEntity<EnderecoPostResponse> salvar(@RequestBody @Valid EnderecoPostRequest endereco) {
        log.info("Salvando endereco {}", endereco);

        var enderecoToSave = enderecoMapper.toEndereco(endereco);
        var savedEndereco = enderecoService.salvar(enderecoToSave);
        var response = enderecoMapper.toEnderecoPostResponse(savedEndereco);

        log.info("Endereco salvo com sucesso: {}", savedEndereco);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PutMapping
    public ResponseEntity<EnderecoPutResponse> atualizar(@RequestBody @Valid EnderecoPutRequest endereco) {
        log.info("Atualizando Endereco {}", endereco);


        var enderecoToUpdate = enderecoMapper.toEndereco(endereco);
        var updatedEndereco = enderecoService.atualizar(enderecoToUpdate);
        var response = enderecoMapper.toEnderecoPutResponse(updatedEndereco);

        log.info("Endereco atualizado com sucesso: {}", updatedEndereco);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluir(@PathVariable Long id) {
        log.info("Excluir Endereco {}", id);

        enderecoService.excluir(id);
        log.info("Endereco excluído com sucesso: {}", id);

        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @GetMapping(BUSCAR_POR_ID)
    public ResponseEntity<EnderecoGetResponse> findById(Long id) {
        log.info("Buscando Endereco {}", id);
        EnderecoGetResponse enderecoGetResponse;

        try{
            var endereco = enderecoService.findById(id);
            enderecoGetResponse = enderecoMapper.toEnderecoGetResponse(endereco);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        return ResponseEntity.ok(enderecoGetResponse);
    }

    @GetMapping(LISTAR_TODOS)
    public ResponseEntity<List<EnderecoGetResponse>> listarTodos() {
        log.info("Listando enderecos");

        var enderecos = enderecoService.listarTodos();
        var response = enderecoMapper.toEnderecoGetResponseList(enderecos);

        log.info("Enderecos listados com sucesso: {}", enderecos.size());
        return ResponseEntity.ok(response);
    }
}
