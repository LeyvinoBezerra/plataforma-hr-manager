package br.edu.ufersa.rh.core.controller.funcionario;

import br.edu.ufersa.rh.core.service.endereco.FuncionarioService;
import br.edu.ufersa.rh.domain.dtos.funcionario.response.FuncionarioGetResponse;
import br.edu.ufersa.rh.domain.dtos.funcionario.response.FuncionarioPostResponse;
import br.edu.ufersa.rh.domain.dtos.funcionario.response.FuncionarioPutResponse;
import br.edu.ufersa.rh.domain.dtos.funcionario.resquest.FuncionarioPostRequest;
import br.edu.ufersa.rh.domain.dtos.funcionario.resquest.FuncionarioPutRequest;
import br.edu.ufersa.rh.domain.mappers.FuncionarioMapper;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(FuncionarioController.API_V1_FUNCIONARIOS)
@AllArgsConstructor
@Log4j2
public class FuncionarioController {

    public static final String API_V1_FUNCIONARIOS = "/api/v1/funcionarios";
    private static final String SALVAR = "salvar";
    private static final String ATUALIZAR = "atualizar";
    private static final String EXCLUIR = "excluir";
    private static final String BUSCAR_POR_ID = "buscarPorId";
    private static final String LISTAR_TODOS = "listarTodos";

    private final FuncionarioService funcionarioService;
    private final FuncionarioMapper funcionarioMapper;

    @PostMapping( SALVAR)
    public ResponseEntity<FuncionarioPostResponse> salvar(@RequestBody @Valid FuncionarioPostRequest funcionario) {
        log.info("Salvando funcionario {}", funcionario);

        var funcionarioToSave = funcionarioMapper.toFuncionario(funcionario);
        var savedFuncionario = funcionarioService.salvar(funcionarioToSave);
        var response = funcionarioMapper.toFuncionarioPostResponse(savedFuncionario);

        log.info("Funcionario salvo com sucesso: {}", savedFuncionario);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PutMapping( ATUALIZAR)
    public ResponseEntity<FuncionarioPutResponse> atualizar(@RequestBody @Valid FuncionarioPutRequest funcionario) {
        log.info("Atualizando funcionario {}", funcionario);

        var funcionarioToUpdate = funcionarioMapper.toFuncionario(funcionario);
        var updatedFuncionario = funcionarioService.atualizar(funcionarioToUpdate);
        var response = funcionarioMapper.toFuncionarioPutResponse(updatedFuncionario);

        log.info("Funcionario atualizado com sucesso: {}", updatedFuncionario);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @DeleteMapping(EXCLUIR)
    public ResponseEntity<Void> excluir(Long id) {
        log.info("Excluir funcionario {}", id);

        funcionarioService.excluir(id);
        log.info("Funcionario excluído com sucesso: {}", id);

        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @GetMapping(BUSCAR_POR_ID)
    public ResponseEntity<FuncionarioGetResponse> findById(Long id) {
        log.info("Buscando funcionario {}", id);
        FuncionarioGetResponse funcionarioGetResponse;

        try{
            var funcionario = funcionarioService.findById(id);
            funcionarioGetResponse = funcionarioMapper.toFuncionarioGetResponse(funcionario);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        return ResponseEntity.ok(funcionarioGetResponse);
    }

    @GetMapping(LISTAR_TODOS)
    public ResponseEntity<List<FuncionarioGetResponse>> listarTodos() {
        log.info("Listando funcionarios");

        var funcionarios = funcionarioService.listarTodos();
        var response = funcionarioMapper.toFuncionarioGetResponseList(funcionarios);

        log.info("Funcionarios listados com sucesso: {}", funcionarios.size());
        return ResponseEntity.ok(response);
    }
}
