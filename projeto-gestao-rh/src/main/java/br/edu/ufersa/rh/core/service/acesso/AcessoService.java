package br.edu.ufersa.rh.core.service.acesso;

import br.edu.ufersa.rh.core.repository.acesso.AcessoRepository;
import br.edu.ufersa.rh.core.repository.permissao.PermissaoRepository;
import br.edu.ufersa.rh.core.repository.perfil.PerfilRepository;
import br.edu.ufersa.rh.domain.dtos.acesso.AcessoDto;
import br.edu.ufersa.rh.domain.entity.Acesso;
import br.edu.ufersa.rh.domain.entity.Perfil;
import br.edu.ufersa.rh.domain.entity.Permissao;
import br.edu.ufersa.rh.domain.enums.AcessoStatusEnum;
import br.edu.ufersa.rh.domain.mappers.AcessoMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@Transactional
public class AcessoService {

    private final AcessoRepository acessoRepository;
    private final PerfilRepository perfilRepository;
    private final PermissaoRepository permissaoRepository;
    private final AcessoMapper acessoMapper;

    public AcessoService(AcessoRepository acessoRepository, PerfilRepository perfilRepository,
                        PermissaoRepository permissaoRepository, AcessoMapper acessoMapper) {
        this.acessoRepository = acessoRepository;
        this.perfilRepository = perfilRepository;
        this.permissaoRepository = permissaoRepository;
        this.acessoMapper = acessoMapper;
    }

    public AcessoDto concederAcesso(Long perfilId, Long permissaoId) {
        Perfil perfil = perfilRepository.findById(perfilId)
                .orElseThrow(() -> new IllegalArgumentException("Perfil não encontrado: " + perfilId));

        Permissao permissao = permissaoRepository.findById(permissaoId)
                .orElseThrow(() -> new IllegalArgumentException("Permissão não encontrada: " + permissaoId));

        Acesso acesso = Acesso.builder()
                .perfil(perfil)
                .permissao(permissao)
                .status(AcessoStatusEnum.ATIVO)
                .dataInicio(LocalDateTime.now())
                .build();

        Acesso salvo = acessoRepository.save(acesso);
        return acessoMapper.toDto(salvo);
    }

    public AcessoDto obterAcessoPorId(Long id) {
        Acesso acesso = acessoRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Acesso não encontrado: " + id));
        return acessoMapper.toDto(acesso);
    }

    public List<AcessoDto> listarAcessosPorPerfil(Long perfilId) {
        return acessoRepository.findByPerfilId(perfilId).stream()
                .map(acessoMapper::toDto)
                .toList();
    }

    public List<AcessoDto> listarAcessosAtivos(Long perfilId) {
        return acessoRepository.findAcessosAtivos(perfilId, AcessoStatusEnum.ATIVO).stream()
                .map(acessoMapper::toDto)
                .toList();
    }

    public List<AcessoDto> listarAcessosPorPermissao(Long permissaoId) {
        return acessoRepository.findByPermissaoId(permissaoId).stream()
                .map(acessoMapper::toDto)
                .toList();
    }

    public List<AcessoDto> listarAcessos() {
        return acessoRepository.findAll().stream()
                .map(acessoMapper::toDto)
                .toList();
    }

    public AcessoDto atualizarAcesso(Long id, AcessoDto acessoDto) {
        Acesso acesso = acessoRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Acesso não encontrado: " + id));

        acesso.setStatus(acessoDto.getStatus());
        acesso.setDataFim(acessoDto.getDataFim());

        Acesso atualizado = acessoRepository.save(acesso);
        return acessoMapper.toDto(atualizado);
    }

    public void revogarAcesso(Long id) {
        Acesso acesso = acessoRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Acesso não encontrado: " + id));
        acesso.setStatus(AcessoStatusEnum.INATIVO);
        acesso.setDataFim(LocalDateTime.now());
        acessoRepository.save(acesso);
    }

    public void deletarAcesso(Long id) {
        acessoRepository.deleteById(id);
    }
}
