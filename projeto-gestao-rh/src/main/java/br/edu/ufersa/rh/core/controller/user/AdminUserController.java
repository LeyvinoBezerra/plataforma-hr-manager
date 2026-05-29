package br.edu.ufersa.rh.core.controller.user;

import br.edu.ufersa.rh.core.service.user.UserAdminService;
import br.edu.ufersa.rh.domain.entity.Usuario;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/usuarios")
@Tag(name = "Users", description = "Endpoints relacionados a usuários")
public class AdminUserController {

    private final UserAdminService userAdminService;

    public AdminUserController(UserAdminService userAdminService) {
        this.userAdminService = userAdminService;
    }

    @PostMapping("/{usuarioId}/vincular-funcionario")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Usuario> vincularFuncionario(@PathVariable Long usuarioId, @RequestParam Long funcionarioId) {
        Usuario atualizado = userAdminService.vincularFuncionarioAoUsuario(usuarioId, funcionarioId);
        return ResponseEntity.ok(atualizado);
    }
}
