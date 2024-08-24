package br.com.fiap.cp4.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.fiap.cp4.model.Task;

public interface TaskRepository extends JpaRepository<Task, Long> {
}