package br.edu.ufersa.rh.core.controller.permissao;

import br.edu.ufersa.rh.core.service.permissao.PermissaoService;
import br.edu.ufersa.rh.domain.dtos.permissao.PermissaoDto;
import br.edu.ufersa.rh.domain.enums.PermissaoTipoEnum;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/permissoes")
@Tag(name = "Permission Controller", description = "Endpoints for permission management")
@SecurityRequirement(name = "bearer-jwt")
public class PermissaoController {

    private final PermissaoService permissaoService;

    public PermissaoController(PermissaoService permissaoService) {
        this.permissaoService = permissaoService;
    }

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    @Operation(summary = "Criar nova permissão")
    public ResponseEntity<PermissaoDto> criarPermissao(@RequestBody PermissaoDto permissaoDto) {
        PermissaoDto criada = permissaoService.criarPermissao(permissaoDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(criada);
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    @Operation(summary = "Obter permissão por ID")
    public ResponseEntity<PermissaoDto> obterPermissao(@PathVariable Long id) {
        PermissaoDto permissao = permissaoService.obterPermissaoPorId(id);
        return ResponseEntity.ok(permissao);
    }

    @GetMapping
    @PreAuthorize("hasRole('ADMIN')")
    @Operation(summary = "Listar todas as permissões")
    public ResponseEntity<List<PermissaoDto>> listarPermissoes() {
        List<PermissaoDto> permissoes = permissaoService.listarPermissoes();
        return ResponseEntity.ok(permissoes);
    }

    @GetMapping("/ativas")
    @PreAuthorize("hasRole('ADMIN')")
    @Operation(summary = "Listar permissões ativas")
    public ResponseEntity<List<PermissaoDto>> listarPermissoesAtivas() {
        List<PermissaoDto> permissoes = permissaoService.listarPermissoesAtivas();
        return ResponseEntity.ok(permissoes);
    }

    @GetMapping("/tipo/{tipo}")
    @PreAuthorize("hasRole('ADMIN')")
    @Operation(summary = "Listar permissões por tipo")
    public ResponseEntity<List<PermissaoDto>> listarPermissoesPorTipo(@PathVariable PermissaoTipoEnum tipo) {
        List<PermissaoDto> permissoes = permissaoService.listarPermissoesPorTipo(tipo);
        return ResponseEntity.ok(permissoes);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    @Operation(summary = "Atualizar permissão")
    public ResponseEntity<PermissaoDto> atualizarPermissao(@PathVariable Long id, @RequestBody PermissaoDto permissaoDto) {
        PermissaoDto atualizada = permissaoService.atualizarPermissao(id, permissaoDto);
        return ResponseEntity.ok(atualizada);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    @Operation(summary = "Deletar permissão")
    public ResponseEntity<Void> deletarPermissao(@PathVariable Long id) {
        permissaoService.deletarPermissao(id);
        return ResponseEntity.noContent().build();
    }
}
