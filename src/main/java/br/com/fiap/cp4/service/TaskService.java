package br.com.fiap.cp4.service;

import br.com.fiap.cp4.dto.task.*;
import br.com.fiap.cp4.model.Task;
import br.com.fiap.cp4.model.User;
import br.com.fiap.cp4.repository.TaskRepository;
import br.com.fiap.cp4.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import br.com.fiap.cp4.service.interfaces.ITaskService;

import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import org.springframework.security.core.Authentication;

@Service
public class TaskService implements ITaskService {

    private final TaskRepository taskRepository;
    private final UserRepository userRepository;

    public TaskService(TaskRepository taskRepository, UserRepository userRepository) {
        this.taskRepository = taskRepository;
        this.userRepository = userRepository;
    }

    private User getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || !(authentication.getPrincipal() instanceof UserDetails)) {
            throw new SecurityException("User not authenticated");
        }

        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        String username = userDetails.getUsername();
        return userRepository.findByLogin(username);
    }

    @Override
    public Page<DetalhesTaskDTO> listarTasks(Pageable paginacao) {
        return taskRepository.findAll(paginacao).map(DetalhesTaskDTO::new);
    }

    @Override
    public DetalhesTaskDTO buscarTask(Long id) throws EntityNotFoundException {
        Task task = taskRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Task não encontrada com ID: " + id));
        return new DetalhesTaskDTO(task);
    }

    @Override
    @Transactional
    public Task cadastrarTask(CadastroTaskDTO taskDTO) {
        User user = getCurrentUser();
        Task task = new Task(taskDTO);
        task.setUser(user);
        return taskRepository.save(task);
    }

    @Override
    @Transactional
    public Task atualizarTask(Long id, AtualizacaoTaskDTO atualizacaoTaskDTO) throws EntityNotFoundException {
        User user = getCurrentUser();
        Task task = taskRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Task não encontrada com ID: " + id));
        if (task.getUser().getId().equals(user.getId())) {
            task.atualizar(atualizacaoTaskDTO);
            return taskRepository.save(task);
        } else {
            throw new SecurityException("Task não pertence ao usuário autenticado");
        }
    }

    @Override
    @Transactional
    public void deletarTask(Long id) throws EntityNotFoundException, SecurityException {
        User user = getCurrentUser();
        Task task = taskRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Task não encontrada com ID: " + id));
        if (task.getUser().getId().equals(user.getId())) {
            taskRepository.delete(task);
        } else {
            throw new SecurityException("Task não pertence ao usuário autenticado");
        }
    }
}
