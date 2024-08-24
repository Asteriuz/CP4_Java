package br.com.fiap.cp4.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.fiap.cp4.model.Status;

public interface StatusRepository extends JpaRepository<Status, Long> {
}