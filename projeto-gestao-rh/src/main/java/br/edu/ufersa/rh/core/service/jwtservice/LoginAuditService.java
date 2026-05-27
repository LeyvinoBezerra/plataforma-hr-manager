package br.edu.ufersa.rh.core.service.jwtservice;

import br.edu.ufersa.rh.core.repository.usuario.UsuarioRepository;
import br.edu.ufersa.rh.domain.entity.Usuario;
import br.edu.ufersa.rh.domain.enums.UsuarioStatusEnum;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
public class LoginAuditService {

    private final UsuarioRepository usuarioRepository;
    private static final int MAX_FAILED_ATTEMPTS = 5;
    private static final long LOCK_DURATION_MINUTES = 30;

    public LoginAuditService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    @Transactional
    public void registrarLoginSucesso(String username) {
        usuarioRepository.findByUsername(username).ifPresent(usuario -> {
            usuario.setUltimoAcesso(LocalDateTime.now());
            usuario.setTentativasFalhas(0);
            usuario.setStatus(UsuarioStatusEnum.ATIVO);
            usuario.setDataBloqueio(null);
            usuarioRepository.save(usuario);
        });
    }

    @Transactional
    public void registrarLoginFalha(String username) {
        usuarioRepository.findByUsername(username).ifPresent(usuario -> {
            Integer tentativas = usuario.getTentativasFalhas() != null ? usuario.getTentativasFalhas() : 0;
            tentativas++;
            usuario.setTentativasFalhas(tentativas);

            if (tentativas >= MAX_FAILED_ATTEMPTS) {
                usuario.setStatus(UsuarioStatusEnum.BLOQUEADO);
                usuario.setDataBloqueio(LocalDateTime.now());
            }

            usuarioRepository.save(usuario);
        });
    }

    @Transactional
    public void desbloquearUsuario(String username) {
        usuarioRepository.findByUsername(username).ifPresent(usuario -> {
            usuario.setStatus(UsuarioStatusEnum.ATIVO);
            usuario.setTentativasFalhas(0);
            usuario.setDataBloqueio(null);
            usuarioRepository.save(usuario);
        });
    }

    @Transactional
    public boolean verificarSeEstaBloqueado(String username) {
        return usuarioRepository.findByUsername(username).map(usuario -> {
            if (usuario.getStatus() == UsuarioStatusEnum.BLOQUEADO && usuario.getDataBloqueio() != null) {
                LocalDateTime dataDesbloqueio = usuario.getDataBloqueio().plusMinutes(LOCK_DURATION_MINUTES);
                if (LocalDateTime.now().isAfter(dataDesbloqueio)) {
                    desbloquearUsuario(username);
                    return false;
                }
                return true;
            }
            return false;
        }).orElse(false);
    }

    public long getTempoRestanteDesbloqueio(String username) {
        var usuario = usuarioRepository.findByUsername(username).orElse(null);
        if (usuario != null && usuario.getStatus() == UsuarioStatusEnum.BLOQUEADO && usuario.getDataBloqueio() != null) {
            LocalDateTime dataDesbloqueio = usuario.getDataBloqueio().plusMinutes(LOCK_DURATION_MINUTES);
            return java.time.temporal.ChronoUnit.MINUTES.between(LocalDateTime.now(), dataDesbloqueio);
        }
        return 0;
    }
}
