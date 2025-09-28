package br.edu.ufersa.rh.core.service.endereco;

import br.edu.ufersa.rh.core.repository.funcionario.FuncionarioRepository;
import br.edu.ufersa.rh.core.service.pessoa.PessoaService;
import br.edu.ufersa.rh.domain.entity.Funcionario;
import br.edu.ufersa.rh.exception.NotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

import static br.edu.ufersa.rh.utils.Constants.FIND_BY_ID_ERROR;
import static br.edu.ufersa.rh.utils.Constants.FUNCIONARIO_NOT_FOUND;

@Service
@AllArgsConstructor
public class FuncionarioService {

    private final FuncionarioRepository funcionarioRepository;
    private final PessoaService pessoaService;

    public Funcionario salvar(Funcionario funcionarioToSave) {
        preencherPessoaById(funcionarioToSave);
        return funcionarioRepository.save(funcionarioToSave);
    }

    public Funcionario atualizar(Funcionario funcionarioToUpdate) {
        preencherPessoaById(funcionarioToUpdate);
        var funcionario = funcionarioRepository.findById(funcionarioToUpdate.getId())
                .orElseThrow(() -> new NotFoundException(FUNCIONARIO_NOT_FOUND));

        funcionarioToUpdate.setDataCriacao(funcionario.getDataCriacao());
        return funcionarioRepository.save(funcionarioToUpdate);
    }

    public void excluir(Long id) {
        validarBuscaPorId(id);
        var funcionario = funcionarioRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(FUNCIONARIO_NOT_FOUND));

        funcionarioRepository.delete(funcionario);
    }

    public Funcionario findById(Long id) {
        validarBuscaPorId(id);
        return funcionarioRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(FUNCIONARIO_NOT_FOUND));
    }

    public List<Funcionario> listarTodos() {
        return funcionarioRepository.findAll();
    }

    private static void validarBuscaPorId(Long id) {
        if(id == null) {
            throw new IllegalArgumentException(FIND_BY_ID_ERROR);
        }
    }

    private void preencherPessoaById(Funcionario funcionarioToSave) {
        if (funcionarioToSave.getPessoa() == null || funcionarioToSave.getPessoa().getId() == null) {
            throw new IllegalArgumentException("Pessoa não pode ser nula");
        }

        var pessoa = pessoaService.findById(funcionarioToSave.getPessoa().getId());
        funcionarioToSave.setPessoa(pessoa);
    }
}
