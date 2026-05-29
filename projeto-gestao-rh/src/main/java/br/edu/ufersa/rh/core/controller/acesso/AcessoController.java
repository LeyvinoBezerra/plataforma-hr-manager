package br.edu.ufersa.rh.core.controller.acesso;

import br.edu.ufersa.rh.core.service.acesso.AcessoService;
import br.edu.ufersa.rh.domain.dtos.acesso.AcessoDto;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/acessos")
@Tag(name = "2. Access Management", description = "Gestão de acessos e atribuição de permissões a perfis")
@SecurityRequirement(name = "bearer-jwt")
public class AcessoController {

    private final AcessoService acessoService;

    public AcessoController(AcessoService acessoService) {
        this.acessoService = acessoService;
    }

    @PostMapping("/conceder")
    @PreAuthorize("hasRole('ADMIN')")
    @Operation(summary = "Conceder acesso (permissão a um perfil)")
    public ResponseEntity<AcessoDto> concederAcesso(@RequestParam Long perfilId, @RequestParam Long permissaoId) {
        AcessoDto acesso = acessoService.concederAcesso(perfilId, permissaoId);
        return ResponseEntity.status(HttpStatus.CREATED).body(acesso);
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    @Operation(summary = "Obter acesso por ID")
    public ResponseEntity<AcessoDto> obterAcesso(@PathVariable Long id) {
        AcessoDto acesso = acessoService.obterAcessoPorId(id);
        return ResponseEntity.ok(acesso);
    }

    @GetMapping
    @PreAuthorize("hasRole('ADMIN')")
    @Operation(summary = "Listar todos os acessos")
    public ResponseEntity<List<AcessoDto>> listarAcessos() {
        List<AcessoDto> acessos = acessoService.listarAcessos();
        return ResponseEntity.ok(acessos);
    }

    @GetMapping("/perfil/{perfilId}")
    @PreAuthorize("hasRole('ADMIN')")
    @Operation(summary = "Listar acessos de um perfil")
    public ResponseEntity<List<AcessoDto>> listarAcessosPorPerfil(@PathVariable Long perfilId) {
        List<AcessoDto> acessos = acessoService.listarAcessosPorPerfil(perfilId);
        return ResponseEntity.ok(acessos);
    }

    @GetMapping("/perfil/{perfilId}/ativos")
    @PreAuthorize("hasRole('ADMIN')")
    @Operation(summary = "Listar acessos ativos de um perfil")
    public ResponseEntity<List<AcessoDto>> listarAcessosAtivos(@PathVariable Long perfilId) {
        List<AcessoDto> acessos = acessoService.listarAcessosAtivos(perfilId);
        return ResponseEntity.ok(acessos);
    }

    @GetMapping("/permissao/{permissaoId}")
    @PreAuthorize("hasRole('ADMIN')")
    @Operation(summary = "Listar acessos de uma permissão")
    public ResponseEntity<List<AcessoDto>> listarAcessosPorPermissao(@PathVariable Long permissaoId) {
        List<AcessoDto> acessos = acessoService.listarAcessosPorPermissao(permissaoId);
        return ResponseEntity.ok(acessos);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    @Operation(summary = "Atualizar acesso")
    public ResponseEntity<AcessoDto> atualizarAcesso(@PathVariable Long id, @RequestBody AcessoDto acessoDto) {
        AcessoDto atualizado = acessoService.atualizarAcesso(id, acessoDto);
        return ResponseEntity.ok(atualizado);
    }

    @PostMapping("/{id}/revogar")
    @PreAuthorize("hasRole('ADMIN')")
    @Operation(summary = "Revogar acesso de um perfil")
    public ResponseEntity<Void> revogarAcesso(@PathVariable Long id) {
        acessoService.revogarAcesso(id);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    @Operation(summary = "Deletar acesso")
    public ResponseEntity<Void> deletarAcesso(@PathVariable Long id) {
        acessoService.deletarAcesso(id);
        return ResponseEntity.noContent().build();
    }
}
