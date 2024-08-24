package br.com.fiap.cp4.repository;

import br.com.fiap.cp4.model.User;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByLogin(String login);
}
