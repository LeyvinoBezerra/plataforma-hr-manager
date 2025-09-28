package br.edu.ufersa.rh.core.service.pessoa;

import br.edu.ufersa.rh.core.repository.pessoa.PessoaRepository;
import br.edu.ufersa.rh.domain.entity.Pessoa;
import br.edu.ufersa.rh.exception.NotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

import static br.edu.ufersa.rh.utils.Constants.ID_NULO;
import static br.edu.ufersa.rh.utils.Constants.PESSOA_NOT_FOUND;

@Service
@AllArgsConstructor
public class PessoaService {

    private final PessoaRepository pessoaRepository;

    public Pessoa salvar(Pessoa pessoaToSave) {
        return pessoaRepository.save(pessoaToSave);
    }

    public Pessoa atualizar(Pessoa pessoaToUpdate) {
        var pessoa = pessoaRepository.findById(pessoaToUpdate.getId())
                .orElseThrow(() -> new NotFoundException(PESSOA_NOT_FOUND));

        pessoaToUpdate.setDataCriacao(pessoa.getDataCriacao());
        return pessoaRepository.save(pessoaToUpdate);
    }

    public void excluir(Long id) {
        validarBuscarPorId(id);
        var pessoa = pessoaRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(PESSOA_NOT_FOUND));

        pessoaRepository.delete(pessoa);
    }

    public Pessoa findById(Long id) {
        validarBuscarPorId(id);
        return pessoaRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(PESSOA_NOT_FOUND));
    }

    private static void validarBuscarPorId(Long id) {
        if(id == null) {
            throw new IllegalArgumentException(ID_NULO);
        }
    }

    public List<Pessoa> listarTodas() {
        return pessoaRepository.findAll();
    }
}