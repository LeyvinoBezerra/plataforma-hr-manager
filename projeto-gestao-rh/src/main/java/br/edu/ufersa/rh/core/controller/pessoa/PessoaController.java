package br.edu.ufersa.rh.core.controller.pessoa;

import br.edu.ufersa.rh.core.service.pessoa.PessoaService;
import br.edu.ufersa.rh.domain.dtos.pessoa.request.PessoaPostRequest;
import br.edu.ufersa.rh.domain.dtos.pessoa.request.PessoaPutRequest;
import br.edu.ufersa.rh.domain.dtos.pessoa.response.PessoaGetResponse;
import br.edu.ufersa.rh.domain.dtos.pessoa.response.PessoaPostResponse;
import br.edu.ufersa.rh.domain.dtos.pessoa.response.PessoaPutResponse;
import br.edu.ufersa.rh.domain.mappers.PessoaMapper;
import br.edu.ufersa.rh.exception.NotFoundException;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(PessoaController.API_V1_PESSOAS)
@AllArgsConstructor
@Log4j2
public class PessoaController {

    public static final String API_V1_PESSOAS = "/api/v1/pessoas";
    private static final String SALVAR = "salvar";
    private static final String ATUALIZAR = "atualizar";
    private static final String EXCLUIR = "excluir";
    private static final String BUSCAR_POR_ID = "buscarPorId";
    private static final String LISTAR_TODAS = "listarTodas";

    private final PessoaService pessoaService;
    private final PessoaMapper pessoaMapper;

    @PostMapping(SALVAR)
    public ResponseEntity<PessoaPostResponse> save(@RequestBody @Valid PessoaPostRequest pessoaToSave) {
        log.info("Iniciando o processo de salvar uma nova pessoa: {}", pessoaToSave);

        var pessoaEntity = pessoaMapper.toPessoa(pessoaToSave);
        var pessoaSaved = pessoaService.salvar(pessoaEntity);
        var pessoaPostResponse = pessoaMapper.toPessoaPostResponse(pessoaSaved);

        log.info("Pessoa salva com sucesso: {}", pessoaPostResponse);
        return ResponseEntity.status(HttpStatus.CREATED).body(pessoaPostResponse);
    }

    @PutMapping(ATUALIZAR)
    public ResponseEntity<PessoaPutResponse> update(@RequestBody @Valid PessoaPutRequest pessoaToSave) {
        log.info("Iniciando o processo de atualizar uma pessoa: {}", pessoaToSave);

        var pessoaEntity = pessoaMapper.toPessoa(pessoaToSave);
        var pessoaUpdated = pessoaService.atualizar(pessoaEntity);
        var pessoaPutResponse = pessoaMapper.toPessoaPutResponse(pessoaUpdated);

        log.info("Pessoa atualizada com sucesso: {}", pessoaPutResponse);
        return ResponseEntity.status(HttpStatus.OK).body(pessoaPutResponse);
    }
    
    @DeleteMapping(EXCLUIR)
    public ResponseEntity<Void> delete(@RequestParam @Valid Long id) {
        log.info("Iniciando o processo de exclusão da pessoa com ID: {}", id);

        pessoaService.excluir(id);

        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
    
    @GetMapping(BUSCAR_POR_ID)
    public ResponseEntity<PessoaGetResponse> findById(@RequestParam @Valid Long id) {
        log.info("Iniciando o processo de busca da pessoa com ID: {}", id);
        PessoaGetResponse pessoaGetResponse;

        try{
            var pessoa = pessoaService.findById(id);
            pessoaGetResponse = pessoaMapper.toPessoaGetResponse(pessoa);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        return ResponseEntity.status(HttpStatus.OK).body(pessoaGetResponse);
    }
    
    @GetMapping(LISTAR_TODAS)
    public ResponseEntity<List<PessoaGetResponse>> findAll() {
        log.info("Iniciando o processo de listagem de todas as pessoas");

        var pessoas = pessoaService.listarTodas();
        var listaPessoasResponse = pessoaMapper.toPessoaGetResponseList(pessoas);

        return ResponseEntity.ok(listaPessoasResponse);
    }
}
