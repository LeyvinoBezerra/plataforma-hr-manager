package br.edu.ufersa.rh.core.controller.perfil;


import br.edu.ufersa.rh.core.service.perfil.PerfilService;
import br.edu.ufersa.rh.domain.dtos.perfil.response.PerfilGetResponse;
import br.edu.ufersa.rh.domain.dtos.perfil.response.PerfilPostResponse;
import br.edu.ufersa.rh.domain.dtos.perfil.response.PerfilPutResponse;
import br.edu.ufersa.rh.domain.dtos.perfil.resquest.PerfilPostRequest;
import br.edu.ufersa.rh.domain.dtos.perfil.resquest.PerfilPutRequest;
import br.edu.ufersa.rh.domain.mappers.PerfilMapper;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(PerfilController.API_V1_PERFIS)
@AllArgsConstructor
@Log4j2
public class PerfilController {

    public static final String API_V1_PERFIS = "/api/v1/perfis";
    private static final String BUSCAR_POR_ID = "buscarPorId";
    private static final String LISTAR_TODOS = "listarTodos";

    private final PerfilService perfilService;
    private final PerfilMapper perfilMapper;

    @PostMapping
    public ResponseEntity<PerfilPostResponse> salvar(@RequestBody @Valid PerfilPostRequest perfil) {
        log.info("Salvando perfil {}", perfil);

        var perfilToSave = perfilMapper.toPerfil(perfil);
        var perfilSaved = perfilService.salvar(perfilToSave);
        var response = perfilMapper.toPerfilPostResponse(perfilSaved);

        log.info("Perfil salvo com sucesso: {}", perfilSaved);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PutMapping
    public ResponseEntity<PerfilPutResponse> atualizar(@RequestBody @Valid PerfilPutRequest perfil) {
        log.info("Atualizando perfil {}", perfil);

        var perfilToUpdate = perfilMapper.toPerfil(perfil);
        var perfilUpdated = perfilService.atualizar(perfilToUpdate);
        var response = perfilMapper.toPerfilPutResponse(perfilUpdated);

        log.info("Perfil atualizado com sucesso: {}", perfilUpdated);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @DeleteMapping
    public ResponseEntity<Void> excluir(Long id) {
        log.info("Excluir Perfl {}", id);

        perfilService.excluir(id);
        log.info("Perfil excluído com sucesso: {}", id);

        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @GetMapping(BUSCAR_POR_ID)
    public ResponseEntity<PerfilGetResponse> findById(Long id) {
        log.info("Buscando Perfil {}", id);
        PerfilGetResponse perfilGetResponse;

        var perfil = perfilService.findById(id);
        perfilGetResponse = perfilMapper.toPerfilGetResponse(perfil.get());

        return ResponseEntity.ok(perfilGetResponse);
    }

    @GetMapping(LISTAR_TODOS)
    public ResponseEntity<List<PerfilGetResponse>> listarTodos() {
        log.info("Listando Perfis");

        var perfis = perfilService.listarTodos();
        var response = perfilMapper.toPerfilGetResponseList(perfis);

        log.info("Perfis listados com sucesso: {}", perfis.size());
        return ResponseEntity.ok(response);
    }
}
