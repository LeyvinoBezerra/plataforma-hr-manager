package br.edu.ufersa.rh.core.service.funcionario;

import br.edu.ufersa.rh.core.repository.endereco.EnderecoRepository;
import br.edu.ufersa.rh.core.service.pessoa.PessoaService;
import br.edu.ufersa.rh.domain.entity.Endereco;
import br.edu.ufersa.rh.domain.entity.Funcionario;
import br.edu.ufersa.rh.exception.NotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

import static br.edu.ufersa.rh.utils.Constants.*;

@Service
@AllArgsConstructor
public class EnderecoService {

    private final EnderecoRepository enderecoRepository;
    private final PessoaService pessoaService;

    public Endereco salvar(Endereco enderecoToSave) {
        preencherPessoaById(enderecoToSave);
        return enderecoRepository.save(enderecoToSave);
    }

    public Endereco atualizar(Endereco enderecoToUpdate) {
        preencherPessoaById(enderecoToUpdate);
        var endereco = enderecoRepository.findById(enderecoToUpdate.getId())
                .orElseThrow(() -> new NotFoundException(FUNCIONARIO_NOT_FOUND));

        enderecoToUpdate.setDataCriacao(endereco.getDataCriacao());
        return enderecoRepository.save(enderecoToUpdate);
    }

    public void excluir(Long id) {
        validarBuscaPorId(id);
        var endereco = enderecoRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(ENDERECO_NOT_FOUND));

        enderecoRepository.delete(endereco);
    }

    public Endereco findById(Long id) {
        validarBuscaPorId(id);
        return enderecoRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(FUNCIONARIO_NOT_FOUND));
    }

    public List<Endereco> listarTodos() {
        return enderecoRepository.findAll();
    }

    private static void validarBuscaPorId(Long id) {
        if(id == null) {
            throw new IllegalArgumentException(FIND_BY_ID_ERROR);
        }
    }

    private void preencherPessoaById(Endereco enderecoToSave) {
        if (enderecoToSave.getPessoa() == null || enderecoToSave.getPessoa().getId() == null) {
            throw new IllegalArgumentException("Pessoa não pode ser nula");
        }

        var pessoa = pessoaService.findById(enderecoToSave.getPessoa().getId());
        enderecoToSave.setPessoa(pessoa);
    }
}
