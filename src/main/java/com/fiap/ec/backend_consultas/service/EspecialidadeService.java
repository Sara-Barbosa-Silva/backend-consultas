package com.fiap.ec.backend_consultas.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.fiap.ec.backend_consultas.exception.DadosInvalidosException;
import com.fiap.ec.backend_consultas.exception.RecursoDuplicadoException;
import com.fiap.ec.backend_consultas.exception.RecursoNaoEncontradoException;
import com.fiap.ec.backend_consultas.model.Especialidade;
import com.fiap.ec.backend_consultas.repository.EspecialidadeRepository;

@Service
public class EspecialidadeService { // ATENÇÃO: Se o nome do seu arquivo tiver "s" no final, mude aqui para EspecialidadesService
    private final EspecialidadeRepository repository;

    public EspecialidadeService(EspecialidadeRepository repository) {
        this.repository = repository;
    }

    public Especialidade salvar(Especialidade especialidade) {
        if (especialidade.getNome() != null) {
            especialidade.setNome(especialidade.getNome().trim());
        }
        if (especialidade.getNome() == null || especialidade.getNome().isBlank()) {
            throw new DadosInvalidosException("Nome da especialidade é obrigatório.");
        }
        if (repository.existsByNomeIgnoreCase(especialidade.getNome())) {
            throw new RecursoDuplicadoException("Especialidade já cadastrada.");
        }
        return repository.save(especialidade);
    }

    public List<Especialidade> listar() {
        return repository.findAll();
    }

    public Especialidade buscarPorId(Long id) {
        return repository.findById(id)
            .orElseThrow(() -> new RecursoNaoEncontradoException("Especialidade não encontrada"));
    }

    public Especialidade atualizar(Long id, Especialidade especialidadeAtualizada) {
        Especialidade especialidadeExistente = buscarPorId(id);
        
        if (especialidadeAtualizada.getNome() != null) {
            especialidadeExistente.setNome(especialidadeAtualizada.getNome().trim());
        }
        if (especialidadeAtualizada.getDescricao() != null) {
            especialidadeExistente.setDescricao(especialidadeAtualizada.getDescricao().trim());
        }
        
        return repository.save(especialidadeExistente);
    }

    public void deletar(Long id) {
        Especialidade especialidade = buscarPorId(id);
        repository.delete(especialidade);
    }
}