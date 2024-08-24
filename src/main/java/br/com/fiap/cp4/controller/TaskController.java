package br.com.fiap.cp4.controller;

import org.springframework.beans.factory.annotation.Autowired;


import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

import br.com.fiap.cp4.dto.task.AtualizacaoTaskDTO;
import br.com.fiap.cp4.dto.task.CadastroTaskDTO;
import br.com.fiap.cp4.dto.task.DetalhesTaskDTO;
import br.com.fiap.cp4.model.Task;
import br.com.fiap.cp4.service.TaskService;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/tasks")
public class TaskController {

    @Autowired
    private final TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @GetMapping
    public ResponseEntity<Page<DetalhesTaskDTO>> list(Pageable pageable) {
        Page<DetalhesTaskDTO> paginaTasks = taskService.listarTasks(pageable);
        return ResponseEntity.ok(paginaTasks);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DetalhesTaskDTO> search(@PathVariable Long id) {
        try {
            DetalhesTaskDTO task = taskService.buscarTask(id);
            return ResponseEntity.ok(task);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }
    
    @PostMapping
    @Transactional
    public ResponseEntity<DetalhesTaskDTO> register(@RequestBody @Valid CadastroTaskDTO taskDTO, UriComponentsBuilder uriBuilder) {
        Task task = taskService.cadastrarTask(taskDTO);
        DetalhesTaskDTO detalhesTaskDTO = new DetalhesTaskDTO(task);
        return ResponseEntity.created(uriBuilder.path("/tasks/{id}").buildAndExpand(task.getId()).toUri()).body(detalhesTaskDTO);
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<DetalhesTaskDTO> update(@PathVariable Long id, @RequestBody @Valid AtualizacaoTaskDTO taskDTO) {
        try {
            Task task = taskService.atualizarTask(id, taskDTO);
            return ResponseEntity.ok(new DetalhesTaskDTO(task));
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        } catch (SecurityException e) {
            return ResponseEntity.status(403).build();
        }
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<?> delete(@PathVariable Long id) {
        try {
            taskService.deletarTask(id);
            return ResponseEntity.ok().build();
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        } catch (SecurityException e) {
            return ResponseEntity.status(403).build();
        }
    }


}
