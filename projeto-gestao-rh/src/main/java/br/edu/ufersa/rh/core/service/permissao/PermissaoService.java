package br.edu.ufersa.rh.core.service.permissao;

import br.edu.ufersa.rh.core.repository.permissao.PermissaoRepository;
import br.edu.ufersa.rh.domain.dtos.permissao.PermissaoDto;
import br.edu.ufersa.rh.domain.entity.Permissao;
import br.edu.ufersa.rh.domain.enums.PermissaoTipoEnum;
import br.edu.ufersa.rh.domain.mappers.PermissaoMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class PermissaoService {

    private final PermissaoRepository permissaoRepository;
    private final PermissaoMapper permissaoMapper;

    public PermissaoService(PermissaoRepository permissaoRepository, PermissaoMapper permissaoMapper) {
        this.permissaoRepository = permissaoRepository;
        this.permissaoMapper = permissaoMapper;
    }

    public PermissaoDto criarPermissao(PermissaoDto permissaoDto) {
        Permissao permissao = permissaoMapper.toEntity(permissaoDto);
        permissao.setAtivo(true);
        Permissao salva = permissaoRepository.save(permissao);
        return permissaoMapper.toDto(salva);
    }

    public PermissaoDto obterPermissaoPorId(Long id) {
        Permissao permissao = permissaoRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Permissão não encontrada: " + id));
        return permissaoMapper.toDto(permissao);
    }

    public List<PermissaoDto> listarPermissoes() {
        return permissaoRepository.findAll().stream()
                .map(permissaoMapper::toDto)
                .toList();
    }

    public List<PermissaoDto> listarPermissoesAtivas() {
        return permissaoRepository.findByAtivo(true).stream()
                .map(permissaoMapper::toDto)
                .toList();
    }

    public List<PermissaoDto> listarPermissoesPorTipo(PermissaoTipoEnum tipo) {
        return permissaoRepository.findByTipo(tipo).stream()
                .map(permissaoMapper::toDto)
                .toList();
    }

    public PermissaoDto atualizarPermissao(Long id, PermissaoDto permissaoDto) {
        Permissao permissao = permissaoRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Permissão não encontrada: " + id));

        permissao.setNome(permissaoDto.getNome());
        permissao.setDescricao(permissaoDto.getDescricao());
        permissao.setTipo(permissaoDto.getTipo());
        permissao.setRecurso(permissaoDto.getRecurso());
        permissao.setAtivo(permissaoDto.getAtivo());

        Permissao atualizada = permissaoRepository.save(permissao);
        return permissaoMapper.toDto(atualizada);
    }

    public void deletarPermissao(Long id) {
        permissaoRepository.deleteById(id);
    }
}
