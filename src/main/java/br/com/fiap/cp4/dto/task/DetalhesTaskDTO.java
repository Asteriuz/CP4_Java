package br.com.fiap.cp4.dto.task;

import br.com.fiap.cp4.model.Task;

public record DetalhesTaskDTO(Long id, String title, String description, String status, String deadline, String user) {
    public DetalhesTaskDTO(Task task) {
        this(task.getId(), task.getTitle(), task.getDescription(), task.getStatus(), task.getDeadline(), task.getUser().getName());
    }
}
