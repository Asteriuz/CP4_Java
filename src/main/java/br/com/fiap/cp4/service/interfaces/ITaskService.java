package br.com.fiap.cp4.service.interfaces;

import br.com.fiap.cp4.dto.task.*;
import br.com.fiap.cp4.model.Task;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ITaskService {
    Page<DetalhesTaskDTO> listarTasks(Pageable pageable);
    DetalhesTaskDTO buscarTask(Long id) throws EntityNotFoundException;
    Task cadastrarTask(CadastroTaskDTO cadastroTaskDTO);
    Task atualizarTask(Long id, AtualizacaoTaskDTO atualizacaoTaskDTO) throws EntityNotFoundException, SecurityException;
    void deletarTask(Long id) throws EntityNotFoundException, SecurityException;
}