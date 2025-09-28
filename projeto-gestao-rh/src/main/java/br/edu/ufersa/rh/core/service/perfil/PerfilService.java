package br.edu.ufersa.rh.core.service.perfil;

import br.edu.ufersa.rh.core.repository.perfil.PerfilRepository;
import br.edu.ufersa.rh.domain.entity.Perfil;
import br.edu.ufersa.rh.exception.NotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static br.edu.ufersa.rh.utils.Constants.PERFIL_NOT_FOUND;

@Service
@AllArgsConstructor
public class PerfilService {

    private final PerfilRepository perfilRepository;

    public Optional<Perfil> salvar(Perfil perfilToSave) {

        if(perfilToSave == null) {
            throw new IllegalArgumentException("Novo perfil não pode ser nulo!");
        }

        return Optional.of(perfilRepository.save(perfilToSave));
    }

    public Optional<Perfil> atualizar(Perfil perfilToUpdate) {
        var perfil = perfilRepository.findById(perfilToUpdate.getId())
                .orElseThrow(() -> new NotFoundException(PERFIL_NOT_FOUND));

        //realizar preenchimento de campos para atualização
        perfil.builder()
                .nome(perfilToUpdate.getNome())
                .permissoes(perfilToUpdate.getPermissoes())
                .acessoGlobal(perfilToUpdate.getAcessoGlobal())
                .build();

        return Optional.of(perfilRepository.save(perfil));
    }

    public void excluir(Long id) {
        var perfil = perfilRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(PERFIL_NOT_FOUND));

        perfilRepository.delete(perfil);
    }

    public Optional<Perfil> findById(Long id) {
        return Optional.ofNullable(perfilRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(PERFIL_NOT_FOUND)));
    }

    public List<Perfil> listarTodos() {
        return perfilRepository.findAll();
    }
}
